package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Member;

public interface MemberService {

	Member selectMember(String m_email);
	Member selectMemberNo(int m_no);
	List<Member> selectListMember();
	Boolean joinMember(Member member);
	Boolean changeToVisitor(int m_no);
	Boolean changeToTeam(int m_no);
	Boolean getOutMember(int m_no);

}