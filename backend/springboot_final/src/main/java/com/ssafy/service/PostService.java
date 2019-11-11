package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Post;

public interface PostService {

	public List<Post> selectAllPost();
	public Post selectOnePost(int p_no);
	public boolean insertPost(Post p);
	public boolean deletePost(int p_no);
	public boolean updatePost(Post post);
}
