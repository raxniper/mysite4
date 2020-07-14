package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 회원 가입
	public int insert(UserVo userVo) {
		System.out.println("userDao: insert");
		return sqlSession.insert("user.insert", userVo);
	}
	
	// 로그인
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao: selectUser");
		System.out.println(userVo.toString());
		return sqlSession.selectOne("user.selectUser", userVo);
	}

	// 회원정보 조회
	public UserVo selectUser(int no) {
		System.out.println("UserDao: selectUser(int)");
		System.out.println(no);
		return sqlSession.selectOne("user.selectInfo", no);
	}
	
	// 회원정보 수정
	public int updateUser(UserVo userVo) {
		System.out.println("UserDao: update");
		return sqlSession.update("user.updateUser", userVo);
	}
	
	
	
}
