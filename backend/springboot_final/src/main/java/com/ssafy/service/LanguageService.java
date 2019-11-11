package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Language;

public interface LanguageService {

	List<Language> selectListLanguage();

	Language selectLanguage(int l_no);

	boolean insertLanguage(Language language);

	boolean deleteLanguage(int l_no);
	
	boolean updateLanguage(Language language);

}