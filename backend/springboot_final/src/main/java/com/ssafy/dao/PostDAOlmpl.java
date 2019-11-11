package com.ssafy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Post;

@Repository
public class PostDAOlmpl implements PostDAO{
	
	private SqlSession session;

	@Autowired
	public PostDAOlmpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<Post> selectAllPost() {
		return session.selectList("selectAllPost");
	}

	@Override
	public Post selectOnePost(int p_no) {		
		return session.selectOne("selectPost", p_no);
	}

	@Override
	public void insertPost(Post p) {
		session.insert("insertPost", p);
	}

	@Override
	public void deletePost(int p_no) {
		session.delete("deletePost", p_no);
		
	}

	@Override
	public void updatePost(Post post) {
		session.update("updatePost",post);
	}
	
	
}
