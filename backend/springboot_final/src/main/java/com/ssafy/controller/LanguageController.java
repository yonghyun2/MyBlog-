package com.ssafy.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.service.LanguageService;
import com.ssafy.vo.Language;

@CrossOrigin(origins = { "*" })
@RequestMapping("/rest")
@RestController
public class LanguageController {
	LanguageService languageService;

	@Autowired
	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	@Autowired
	private Environment environment;

	@GetMapping("/languages")
	public ResponseEntity<List<Language>> selectListLanguage() {
		List<Language> list = languageService.selectListLanguage();

		if (list.size() == 0) {
			return new ResponseEntity<List<Language>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Language>>(list, HttpStatus.OK);
		}

	}

	@GetMapping("/language/{l_no}")
	public ResponseEntity<Language> selectLanguage(@RequestBody int l_no) {
		Language language = languageService.selectLanguage(l_no);

		if (language == null) {
			return new ResponseEntity<Language>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Language>(language, HttpStatus.OK);
		}

	}

	@PostMapping("/imgUpload")
	public ResponseEntity<String> getImagePath(@RequestParam(value="image" , required=false) MultipartFile file, HttpServletRequest request)
			throws IOException {
		// 서버 IP주소 가져오기
//		String ip = InetAddress.getLocalHost().getHostAddress();
		String ip = request.getRemoteAddr();
		
		
		// 서버 PORT 가져오기
//		String port = environment.getProperty("local.server.port");
		String port = Integer.toString(request.getRemotePort());
		System.out.println(environment.getProperty("local.server.port"));
		System.out.println(environment.getProperty("server.ip"));
		
		if(file!=null) {
			// 업로드 이미지 프로젝트에 저장!
			System.out.println(request.getSession().getServletContext().getRealPath("/"));
			System.out.println(request.getSession().getServletContext().getRealPath("/WEP-INF/"));
			System.out.println(request.getSession().getServletContext());
			String filePath = Paths.get("images", file.getOriginalFilename()).toString();
			File convertFile = new File(filePath);
			
			// File convertFile = new File(request.getRealPath("/") + "//imges//" + file.getOriginalFilename());
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			// 파일 네임
			String sourceFileName = file.getOriginalFilename();
			System.out.println("파일이름"+sourceFileName);
			String result = "http://" + "52.78.3.193" + "/images/" + sourceFileName;
			
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("http://" + "52.78.3.193" + "/images/ddd.jpg" ,HttpStatus.OK);
		}

	}

	@PostMapping("/language")
	public ResponseEntity<Boolean> insertLanguage(@RequestBody Language language) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String today = format1.format(time);
		language.setL_date(today);
		return languageService.insertLanguage(language) ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
				: new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/language/{l_no}")
	public ResponseEntity<Boolean> deleteLanguage(@PathVariable int l_no) {
		System.out.println("삭제!");
		return languageService.deleteLanguage(l_no) ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
				: new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/language")
	public ResponseEntity<Boolean> updateLanguage(@RequestBody Language language) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date time = new Date();
		String today = format1.format(time);
		language.setL_date(today);
		
		return languageService.updateLanguage(language) ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
				: new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}

}
