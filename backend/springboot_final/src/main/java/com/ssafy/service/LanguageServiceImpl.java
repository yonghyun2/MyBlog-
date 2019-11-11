package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.LanguageDAO;
import com.ssafy.vo.Language;

@Service
public class LanguageServiceImpl implements LanguageService {
	LanguageDAO languageDao;

	@Autowired
	public void setLanguageDao(LanguageDAO languageDao) {
		this.languageDao = languageDao;
	}

	@Override
	public List<Language> selectListLanguage() {
		return languageDao.selectListLanguage();
	}
	@Override
	public Language selectLanguage(int l_no) {
		return languageDao.selectLanguage(l_no);
	}
	@Override
	public boolean insertLanguage(Language language) {
		languageDao.insertLanguage(language);
		return true;
	}
	@Override
	public boolean deleteLanguage(int l_no) {
		languageDao.deleteLanguage(l_no);
		return true;
	}

	@Override
	public boolean updateLanguage(Language language) {
		languageDao.updateLanguage(language);
		return true;
	}

}
