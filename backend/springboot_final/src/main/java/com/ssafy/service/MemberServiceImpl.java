package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.MemberDAO;
import com.ssafy.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	MemberDAO memberDAO;
	
	@Autowired
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public Member selectMember(String m_email) {
		return memberDAO.selectMember(m_email);
	}

	@Override
	public Boolean joinMember(Member member) {
		// TODO Auto-generated method stub
		memberDAO.joinMember(member);
		return true;
	}

	@Override
	public Boolean changeToVisitor(int m_no) {
		// TODO Auto-generated method stub
		memberDAO.changeToVisitor(m_no);
		return true;
	}

	@Override
	public Boolean changeToTeam(int m_no) {
		memberDAO.changeToTeam(m_no);
		return true;
	}

	@Override
	public Boolean getOutMember(int m_no) {
		// TODO Auto-generated method stub
		memberDAO.getOutMember(m_no);
		return true;
	}

	@Override
	public List<Member> selectListMember() {
		// TODO Auto-generated method stub
		List<Member> list = memberDAO.selectListMember();
		return list;
	}

	@Override
	public Member selectMemberNo(int m_no) {
		// TODO Auto-generated method stub
		return memberDAO.selectMemberNo(m_no);
	}
	

}
