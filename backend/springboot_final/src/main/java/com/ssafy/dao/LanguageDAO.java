package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Language;

public interface LanguageDAO {

	Language selectLanguage(int l_no);

	List<Language> selectListLanguage();

	void insertLanguage(Language language);

	void deleteLanguage(int l_no);
	
	void updateLanguage(Language language);

}