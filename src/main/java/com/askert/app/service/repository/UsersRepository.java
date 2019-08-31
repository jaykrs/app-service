package com.askert.app.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.askert.app.service.entity.Users;

/**
 * @author jayant
 *
 */
public interface UsersRepository extends JpaRepository<Users, Long>{
	Users findByEmailId(String emailId);
	Users validatePwd(@Param("email") String emailId,@Param("pwd") String pwd);
	Boolean forgetPwd(@Param("email") String emailId,@Param("pwd") String pwd);
	Boolean activateUser(@Param("email") String emailId,@Param("activeInd") Boolean activeInd);
}
