package com.askert.app.service.service;

import java.util.List;

import com.askert.app.service.entity.Article;

/**
 * @author jayant
 *
 */
public interface ArticleService {

	public List<Article> getAllArticle();
	
	public Article findArticleById(int id);
	
	public void addArticle(Article article);
	
	public void saveArticle(Article article);
	
	public boolean deleteArticle(int id);
	
}
