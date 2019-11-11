package com.ssafy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Member;

@Repository
public class MemberDAOImpl implements MemberDAO {
	SqlSession session;

	@Autowired
	public void setSession(SqlSession session) {
		this.session = session;
	}	
	
	@Override
	public Member selectMember(String m_email) {
		return session.selectOne("selectMember",m_email);
	}

	@Override
	public void joinMember(Member member) {
		// TODO Auto-generated method stub
		session.insert("joinMember",member);
	}

	@Override
	public void changeToVisitor(int m_no) {
		// TODO Auto-generated method stub
		session.update("changeToVisitor",m_no);
		
	}

	@Override
	public void changeToTeam(int m_no) {
		// TODO Auto-generated method stub
		session.update("changeToTeam",m_no);
		
	}

	@Override
	public void getOutMember(int m_no) {
		// TODO Auto-generated method stub
		session.delete("getOutMember", m_no);
		
	}

	@Override
	public List<Member> selectListMember() {
		
		return session.selectList("selectListMember");
	}

	@Override
	public Member selectMemberNo(int m_no) {
		// TODO Auto-generated method stub
		return session.selectOne("selectMemberNo",m_no);
	}
	
	
	

}
