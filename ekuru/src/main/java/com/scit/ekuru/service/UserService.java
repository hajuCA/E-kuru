package com.scit.ekuru.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scit.ekuru.dao.UserDAO;
import com.scit.ekuru.vo.ChargePointVO;
import com.scit.ekuru.vo.UserVO;


@Service 
public class UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private HttpSession session;
	
	public String insertUser(UserVO vo) {
		int cnt = dao.insertUser(vo);
		String path = "";
		
		if(cnt > 0) {
			path = "redirect:/";
		}else {
			path = "redirect:/member/joinForm";
		}
		return path;
	}
	
	public String loginUser(UserVO vo) {
		UserVO Uservo = dao.loginUser(vo);
		
		String path = "";
		if(Uservo == null) {
			path = "redirect:/user/loginForm";
		}else {
			session.setAttribute("user", Uservo);
			session.setAttribute("userId", Uservo.getUserId());
			path = "redirect:/";
		}
		return path;
	}
	
	public String selectPoint() {
		String id = (String) session.getAttribute("userId");
		ArrayList<ChargePointVO> point = dao.selectPoint(id);
		session.setAttribute("chargepoint", point);
		return "user/mypage_point";
	}
}
