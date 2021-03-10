package com.scit.ekuru.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.scit.ekuru.dao.UserDAO;
import com.scit.ekuru.util.MailUtils;
import com.scit.ekuru.util.Tempkey;
import com.scit.ekuru.vo.ChargePointVO;
import com.scit.ekuru.vo.PointProductVO;
import com.scit.ekuru.vo.UserVO;


@Service
public class UserService {

	@Autowired
	private UserDAO dao;

	@Autowired
	private JavaMailSender mailSender;	
	
	@Autowired
	private HttpSession session;

	public String insertUser(UserVO vo) {
		int cnt = dao.insertUser(vo);
		String path = "";

		if(cnt > 0) {
			path = "redirect:/";
		}else {
			path = "redirect:/user/joinForm";
		}
		return path;
	}
	
	public String loginUser(UserVO vo) {
		UserVO Uservo = dao.loginUser(vo);
		
		String path = "";
		if(Uservo == null) {
			path = "redirect:/user/loginForm";
		}else {

	        session.setAttribute("userNm", Uservo.getUserNm());
	        session.setAttribute("userId", Uservo.getUserId());
			path = "redirect:/";
		}
		return path;
	}
	
	public UserVO selectUserTest(String id) {
		UserVO Uservo = dao.selectUser(id);
		return Uservo;
	}
	
	public void logout() {
		session.removeAttribute("userNm");
		session.removeAttribute("userId");
	}
	
	public String modifyUser(UserVO vo) {
		vo.setUserId((String)session.getAttribute("userId"));
		int count = dao.modifyUser(vo);
		String path = "";
		if(count == 0) {
			path = "redirect:/user/mypage_InfoForm";
		}else {
			path = "redirect:/user/mypage_Info";
		}
		return path;
	}

	public ArrayList<HashMap<String, Object>> selectPoint() {
		String id = (String) session.getAttribute("userId");
		ArrayList<HashMap<String, Object>> list = dao.selectPoint(id);

		return list;
	}

	public HashMap<Object, Object> selectUser(String id) {
		UserVO Uservo = dao.selectUser(id);

		System.out.println(Uservo);

		String path = "";
		HashMap<Object, Object> hash = null;

		if(Uservo == null){
			System.out.println("유저 VO를 가져오지 못했습니다");
		}else {

			String text = Uservo.getUserAddr();
			hash = new HashMap<Object, Object>();

	        if(text != null && text.length() != 0) {
	        	String addr[] = text.split("/");
	        	hash.put("state", addr[0]);
		        hash.put("address1", addr[1]);
		        hash.put("address2", addr[2]);
		        hash.put("user", Uservo);
		        hash.put("confirm", Uservo.getUserConfirm());
	        }

		}
        return hash;
	}
	
	public ArrayList<HashMap<String, Object>> selectCart(){
		ArrayList<HashMap<String, Object>> list = dao.selectCart((String)session.getAttribute("userId"));
		//System.out.println(list);
		return list;
	}
	
	
	
	
	@Transactional
	public void Mailcreate() throws Exception {
		String id = (String) session.getAttribute("userId");
		UserVO vo = dao.selectUser(id);
		System.out.println(vo);
		// 임의의 authkey 생성
		String authkey = new Tempkey().getKey(50, false);
//		
		System.out.println(authkey);
		vo.setAuthkey(authkey);
		dao.modifyAuthkey(vo);

		// mail 작성 관련 
		MailUtils sendMail = new MailUtils(mailSender);

		sendMail.setSubject("회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
				.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:8888/user/mailConfirm?"
						+ "userId="+ vo.getUserId() 
						+ "&authkey=" + authkey 
						+ "' target='_blenk'>이메일 인증 확인</a>")
				.toString());
		sendMail.setFrom("meojong@gmail.com", "머종");
		sendMail.setTo("인증메일 보낼 이메일 입력 == 인증하기 버튼 누른사용자");
		sendMail.send();
	}
	
	
	public String updateConfirm(UserVO vo) {
		int count = dao.updateConfirm(vo);
		String path = "";
		if(count == 0) {
			path = "redirect:/user/mypage_InfoForm";
		}else {
			path = "redirect:/user/mypage_Info";
		}
		return path;
	}
	
	public PointProductVO selectPointPricing(int pointProdNum) {
		return dao.selectPointPricing(pointProdNum);
	}
}
