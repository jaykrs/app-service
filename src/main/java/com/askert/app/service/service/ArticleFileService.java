package com.askert.app.service.service;

import java.util.List;

import com.askert.app.service.entity.ArticleFile;

/**
 * @author jayant
 *
 */
public interface ArticleFileService {

	public List<ArticleFile> getAllArticleFiles();
	
	public ArticleFile findArticleFileById(int articleFileId);
	
	public void addArticleFile(ArticleFile articleFile);
	
	public void saveArticleFile(ArticleFile articleFile);
	
	public boolean deleteArticleFile(int articleFileId);
	
}