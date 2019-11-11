package com.ssafy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.JwtService;
import com.ssafy.service.LanguageService;
import com.ssafy.service.MemberService;
import com.ssafy.service.PostService;
import com.ssafy.vo.Log;
import com.ssafy.vo.Member;

@CrossOrigin(origins = { "*" })
@RequestMapping("/admin")
@RestController
public class AdminController {

	MemberService memberService;
	JwtService jwtService;
	SqlSession session;
	LanguageService languageService;
	PostService postService;

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Autowired
	public void setJwtService(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Autowired
	public void setSession(SqlSession session) {
		this.session = session;
	}
	@Autowired
	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}
	
	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<String> createJwt(@RequestBody Member member) throws Exception {
		Member find = memberService.selectMember(member.getM_email());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String today = format1.format(time);

		Log log = new Log(today, member.getM_email());

		if (find != null) { // 이메일존재
			if (find.getM_role().equals("admin")) { //관리자일때
				BCryptPasswordEncoder isOk = new BCryptPasswordEncoder();
				if (isOk.matches(member.getM_password(), find.getM_password())) { // 로그인 성공!!
					session.insert("insertLog", log);

					return new ResponseEntity<String>(jwtService.makeJwt(member), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("false", HttpStatus.NO_CONTENT);
				}
			} else { //관리자가 아닐때
				return new ResponseEntity<String>("false", HttpStatus.NO_CONTENT);
				
			}
		} else {
			return new ResponseEntity<String>("false", HttpStatus.NO_CONTENT);
		}
	}
	
	
	// 권환변경(visitor)
	@GetMapping("/visitor/{m_email}")
	public ResponseEntity<Boolean> changeToVisitor(@PathVariable String m_email){
		Member find = memberService.selectMember(m_email);
		
		if(find != null) { // 찾은거
			boolean flag = memberService.changeToVisitor(find.getM_no());
			if(flag) { //성공
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			} else { //실패
				return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
			}
			
			
		} else { //못찾은거
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
		}
	}
	
	//권한변경(team)
	@GetMapping("/team/{m_email}")
	public ResponseEntity<Boolean> changeToTeam(@PathVariable String m_email) {
		Member find = memberService.selectMember(m_email);
		
		if(find != null) {
			boolean flag = memberService.changeToTeam(find.getM_no());
			
			if(flag) {
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
				
			}
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
		}
	}

	// 회원탈퇴
	@GetMapping("/getout/{m_email}")
	public ResponseEntity<Boolean> getOutMember(@PathVariable String m_email) {
		Member find = memberService.selectMember(m_email);
		
		if(find != null) {
			boolean flag = memberService.getOutMember(find.getM_no());
			
			if(flag) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
			}
			
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
		}
	}
	
	//langaue삭제
	
	
	//post삭제	
	
	//회원리스트 admin제외
	@GetMapping("/memberList")
	public ResponseEntity<List<Member>> selectListMember(){
		List<Member> list = memberService.selectListMember();
		
		if(list.size()==0) {
			return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
		}
		
	}
}
