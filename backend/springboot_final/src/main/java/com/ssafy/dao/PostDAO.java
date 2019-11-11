package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Post;

public interface PostDAO {
	
	public List<Post> selectAllPost();
	
	public Post selectOnePost(int p_no);
	
	public void insertPost(Post p);
	
	public void deletePost(int p_no);
	
	public void updatePost(Post post);
	
	
}
