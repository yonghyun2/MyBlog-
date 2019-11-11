package com.ssafy.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Language;

@Repository
public class LanguageDAOImpl implements LanguageDAO {
	private SqlSession session;
	
	@Autowired
	public void setSession(SqlSession session) {
		this.session = session;
	}	
	@Override
	public Language selectLanguage(int l_no) {
		return session.selectOne("selectLanguage", l_no);
	}	
	@Override
	public List<Language> selectListLanguage() {
		return session.selectList("selectListLanguage");
	}
	@Override
	public void insertLanguage(Language language) {
		session.insert("insertLanguage", language);
	}
	@Override
	public void deleteLanguage(int l_no) {
		session.delete("deleteLanguage", l_no);
	}
	@Override
	public void updateLanguage(Language language) {		
		// TODO Auto-generated method stub
		session.update("updateLanguage",language);
		
	}	
}
