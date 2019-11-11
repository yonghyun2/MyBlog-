package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.PostDAO;
import com.ssafy.vo.Post;

@Service
public class PostServiceImpl implements PostService {

	PostDAO postdao;
	
	
	
	@Autowired
	public PostServiceImpl(PostDAO postdao) {
		this.postdao = postdao;
	}

	@Override
	public List<Post> selectAllPost() {
		return postdao.selectAllPost();
	}

	@Override
	public Post selectOnePost(int p_no) {
		
		return postdao.selectOnePost(p_no);
	}

	@Override
	public boolean insertPost(Post p) {
		postdao.insertPost(p);
		return true;
	}

	@Override
	public boolean deletePost(int p_no) {
		postdao.deletePost(p_no);
		return true;
	}

	@Override
	public boolean updatePost(Post post) {
		postdao.updatePost(post);
		return true;
	}

}
