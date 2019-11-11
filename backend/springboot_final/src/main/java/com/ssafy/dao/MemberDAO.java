package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Member;

public interface MemberDAO {

	Member selectMember(String m_email);
	Member selectMemberNo(int m_no);
	List<Member> selectListMember();
	void joinMember(Member member);
	void changeToVisitor(int m_no);
	void changeToTeam(int m_no);
	void getOutMember(int m_no);

}