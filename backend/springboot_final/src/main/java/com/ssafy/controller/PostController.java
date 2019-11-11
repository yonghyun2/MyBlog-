package com.ssafy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.PostService;
import com.ssafy.vo.Post;

@CrossOrigin(origins= {"*"})
@RequestMapping("/rest")
@RestController
public class PostController {
	PostService postservice;
	
	@Autowired
	public PostController(PostService postservice) {
		
		this.postservice = postservice;
	}

	@GetMapping("/Post")
	public ResponseEntity<List<Post>> selectAllPost() {
		List<Post> list = postservice.selectAllPost();
		
		if(list.size()==0) {
			return new ResponseEntity<List<Post>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/Post/{p_no}")
	public ResponseEntity<Post> selectPost(@RequestBody int p_no) {
		Post Post = postservice.selectOnePost(p_no);
		
		if(Post == null) {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Post>(Post, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/Post")
	public ResponseEntity<Boolean> insertPost(@RequestBody Post p) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String today = format1.format(time);
		p.setP_date(today);
		
		return postservice.insertPost(p)? new ResponseEntity<Boolean>(true, HttpStatus.OK) : new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("Post/{p_no}")
	public ResponseEntity<Boolean> deletePost(@PathVariable int p_no) {
		return postservice.deletePost(p_no) ? new ResponseEntity<Boolean>(true,HttpStatus.OK) : new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/post")
	public ResponseEntity<Boolean> updatePost(@RequestBody Post post) {
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String today = format1.format(time);
		post.setP_date(today);
		
		return postservice.updatePost(post) ? new ResponseEntity<Boolean>(true,HttpStatus.OK) 
				: new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}
}
