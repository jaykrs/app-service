package com.askert.app.service.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.askert.app.service.constant.ServiceConstants;
import com.askert.app.service.entity.Users;
import com.askert.app.service.mail.EmailInterface;
import com.askert.app.service.repository.UsersRepository;

/**
 * @author jayant
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private EmailInterface emailUtils;

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	ResponseEntity<?> getUsersById(@PathVariable Long id) {
		Optional<Users> users = usersRepository.findById(id);
		log.info("found user with id" + id);
		return users.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * @param user
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/add")
	ResponseEntity<Users> createUser(@Valid @RequestBody Users user) throws URISyntaxException {
		log.info("Request to create user: {}", user);
		user.setPwd(new String(new Base64().encode(user.getPwd().getBytes())));
		Users result = usersRepository.save(user);
		return ResponseEntity.created(new URI("/api/user/add/" + result.getId())).body(result);
	}

	/**
	 * @param user
	 * @return
	 */
	@PutMapping("/update/{id}")
	ResponseEntity<Users> updateUser(@Valid @RequestBody Users user) {
		log.info("Request to update user: {}", user);
		user.setPwd(new String(new Base64().encode(user.getPwd().getBytes())));
		Users result = usersRepository.save(user);
		return ResponseEntity.ok().body(result);
	}

	/**
	 * api/user/validate {"emailId":"yyyyyy@in.com" , "pwd":"xxxxx"}
	 * 
	 * @param json
	 * @param request
	 * @return
	 */
	@PostMapping("/validate")
	ResponseEntity<Map<String, String>> validateUser(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) {
		log.info("Request to validate user: {}", json.get(ServiceConstants.EMAILID));
		Map<String, String> response = new HashMap<String, String>();
		response.put(ServiceConstants.EMAILID, json.get(ServiceConstants.EMAILID));
		Users result = usersRepository.validatePwd(json.get(ServiceConstants.EMAILID), json.get(ServiceConstants.PWD));
		if (null != result) {
			String uuid = UUID.randomUUID().toString();
			request.getSession().setAttribute(ServiceConstants.TOKEN, uuid);
			response.put(ServiceConstants.TOKEN, uuid);
		} else {
			response.put(ServiceConstants.TOKEN, Strings.EMPTY);
		}
		return ResponseEntity.ok().body(response);
	}
	
	/**
	 * @param email
	 * @return
	 */
	@GetMapping("/resetpwd/{email}")
	ResponseEntity<Map<String, String>> resetPwd(@PathVariable String email) {
		Users user = usersRepository.findByEmailId(email);
		Map<String, String> response = new HashMap<String, String>();
		response.put(ServiceConstants.EMAILID, email);
		if (null != user) {
			log.info("found user with email" + email + "reset pwd reqest");
			String _tmpPwd = UUID.randomUUID().toString();
			Boolean _b = usersRepository.forgetPwd(email, _tmpPwd);
			emailUtils.sendPlainEmail(user.getEmailId(), ServiceConstants.TEMP_PWD_EMAIL + _tmpPwd);
			if (_b) {
				response.put(ServiceConstants.MSG, "Please check your " + email + " inbox for temporary password");
			}
		} else {
			response.put(ServiceConstants.MSG, HttpStatus.NOT_FOUND.toString());
		}
		return ResponseEntity.ok().body(response);
	}
	
	/**
	 * @param email
	 * @param activationKey
	 * @return
	 */
	@GetMapping("/activateUser/{email}")
	ResponseEntity<Map<String, String>> activateUser(@PathVariable String email, @PathVariable String activationKey) {
		Users user = usersRepository.findByEmailId(email);
		Map<String, String> response = new HashMap<String, String>();
		response.put(ServiceConstants.EMAILID, email);
		if (null != user) {
			log.info("found user with email" + email + "activate reqest");
			if (activationKey.equals("key")) {
				Boolean _b = usersRepository.activateUser(email, Boolean.TRUE);
				if (_b) {
					response.put(ServiceConstants.MSG, user.getDisplayName() + "activation successfull");
				}
			}
		} else {
			response.put(ServiceConstants.MSG, HttpStatus.NOT_FOUND.toString());
		}
		return ResponseEntity.ok().body(response);
	}
}
