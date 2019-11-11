package com.ssafy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.JwtService;
import com.ssafy.service.MemberService;
import com.ssafy.vo.Log;
import com.ssafy.vo.Member;

@CrossOrigin(origins = { "*" })
@RequestMapping("/jwt")
@RestController
public class MemberController {

	private JwtService jwtService;
	MemberService memberService;
	SqlSession session;

	@Autowired
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Autowired
	public void setJwtService(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/create")
	public ResponseEntity<String[]> createJwt(@RequestBody Member member) throws Exception {
		Member find = memberService.selectMember(member.getM_email());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String today = format1.format(time);

		Log log = new Log(today, member.getM_email());

		if (find != null) { // 이메일존재
			BCryptPasswordEncoder isOk = new BCryptPasswordEncoder();
			if (isOk.matches(member.getM_password(), find.getM_password())) { // 로그인 성공!!
				session.insert("insertLog", log);
				String a[] = new String[2];

				a[0] = jwtService.makeJwt(find);
				a[1] = jwtService.makeRefreshJwt(find);

				return new ResponseEntity<String[]>(a, HttpStatus.OK);
			} else {
				String a[] = new String[2];
				a[0] = "";
				a[1] = "";
				return new ResponseEntity<String[]>(a, HttpStatus.OK);
			}

		} else {
			String a[] = new String[2];
			a[0] = "";
			a[1] = "";
			return new ResponseEntity<String[]>(a, HttpStatus.OK);
		}
	}

	@PostMapping("/auth")
	public ResponseEntity<Member> authToken(@RequestHeader(value="access-token" , required=false) String token) throws Exception {
		System.out.println("access-token :" +token);
		String jwt = token;
		if (jwt == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			String email = jwtService.checkJwt(jwt);
			if (email != null) {
				Member find = memberService.selectMember(jwtService.checkJwt(jwt));
				System.out.println(find);
				if (find != null) {
					return new ResponseEntity<Member>(find, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

	// 액세스토큰 재발급 과정
	@PostMapping("/refresh")
	public ResponseEntity<String> authRefreshToken(@RequestHeader(value="refresh-token", required=false) String token) throws Exception {
		System.out.println("refresh-tkoen :"+token);

		String jwt = token;
		if (jwt == null) {
			return new ResponseEntity<String>("false", HttpStatus.OK);
		} else {
			String flag = jwtService.checkRefreshJwt(jwt);

			if (flag.equals("false")) { // refresh도 말료되어 재발급 불가능
				return new ResponseEntity<String>("false", HttpStatus.OK);

			} else { // access토큰 재발급
				Member member = memberService.selectMemberNo(Integer.parseInt(flag));
				return new ResponseEntity<String>(jwtService.makeJwt(member), HttpStatus.OK);
			}
		}
	}

	@PostMapping("/join")
	public ResponseEntity<Boolean> join(@RequestBody Member member) {
		Member find = memberService.selectMember(member.getM_email());

		if (find == null) {
			member.setM_role("visitor");
			System.out.println(member.getM_role());
			String encodingPassword = new BCryptPasswordEncoder().encode(member.getM_password());

			member.setM_password(encodingPassword);

			boolean flag = memberService.joinMember(member);
			return flag ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
					: new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		} else {
			System.out.println(find.getM_email());
			return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
		}

	}

}
