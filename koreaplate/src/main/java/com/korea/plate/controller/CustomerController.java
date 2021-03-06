package com.korea.plate.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.korea.plate.command.VerifyRecaptcha;
import com.korea.plate.command.Cust.CustomerEmailAuthCommand;
import com.korea.plate.command.Cust.CustomerFindIdPwCommand;
import com.korea.plate.command.Cust.CustomerMyPageCommand;
import com.korea.plate.command.Cust.CustomerMyPagePhotoUpdateCommand;
import com.korea.plate.command.Cust.CustomerSignOutCommand;
import com.korea.plate.command.Cust.CustomerSignUpCommand;
import com.korea.plate.common.Command;
import com.korea.plate.dao.CustomerDAO;
import com.korea.plate.dto.CustomerDTO;

@Controller
public class CustomerController {

	@Autowired
	private SqlSession sqlSession;
	private Command command;
	
	@RequestMapping("loginChoicePage")
	public String loginChoicePage(HttpServletRequest request) {
		return "login/loginChoicePage";
	}
	
	// 일반회원 회원가입
	// 1. 회원가입 페이지 이동
	@RequestMapping("customerSignUp")
	public String goSignUpPage() {
		return "login/customerSignUpPage";
	}
	
	// 2. 아이디 중복체크
	@RequestMapping(value="idCheck", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String idCheck(@RequestParam("cId") String cId) {
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		return lDAO.idCheck(cId) + "";
	}
	
	// 3. 별명 중복체크
	@RequestMapping(value="NicknameCheck", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String NicknameCheck(@RequestParam("cNickname") String cNickname) {
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		return lDAO.NicknameCheck(cNickname) + "";
	}
	
	// 4. 이메일 중복체크
	@RequestMapping(value="emailCheck", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String emailCheck(@RequestParam("cEmail") String cEmail) {
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		return lDAO.emailCheck(cEmail) + "";
	}
	
	// 5. 이메일 인증
	@Autowired
	private JavaMailSender mailSender; // root-context.xml 의 빈 자동생성
	
	@RequestMapping("emailAuthMapping")
	@ResponseBody
	public String emailAuth(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("mailSender", mailSender);
		CustomerEmailAuthCommand command = new CustomerEmailAuthCommand();
		return command.execute(sqlSession, model) + "";
	}
	
	@RequestMapping(value="customerSignUp", method=RequestMethod.POST)
	public String customerSignUp(MultipartHttpServletRequest mr, Model model) {
		model.addAttribute("mr", mr);
		command = new CustomerSignUpCommand();
		command.execute(sqlSession, model);
		return "redirect:loginChoicePage"; 
	}
	
	// 일반회원 로그인
	// 1. 자동가입 방지 봇
	@ResponseBody
    @RequestMapping(value="VerifyRecaptcha", method=RequestMethod.POST)
    public int VerifyRecaptcha(HttpServletRequest request) {
        VerifyRecaptcha.setSecretKey("6Lfi_rEZAAAAABM3-IKPiYnd2CdgjAaiR9_SOiHw");
        String gRecaptchaResponse = request.getParameter("recaptcha");
        System.out.println(gRecaptchaResponse);
        //0 = 성공, 1 = 실패, -1 = 오류
        try {
            if(VerifyRecaptcha.verify(gRecaptchaResponse))
                return 0;
            else return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
	
	// 일반회원 로그인
	@RequestMapping(value="customerLogin", method=RequestMethod.POST,produces="text/html; charset=utf-8")
	@ResponseBody
	public String customerLogin(HttpServletRequest request) {
		String cId = request.getParameter("cId");
		String cPw = request.getParameter("cPw");
		
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		CustomerDTO cDTO = new CustomerDTO();
		cDTO = lDAO.customerLogin(cId, cPw);
		String result = "0";
		if (cDTO != null) {
			request.getSession().setAttribute("cId", cDTO.getcId());
			request.getSession().setAttribute("cNo", cDTO.getcNo());
			request.getSession().setAttribute("cNickname", cDTO.getcNickname());
			request.getSession().setAttribute("cGrede", cDTO.getcGrade());
			result = "1";
		}
		return result;
	}
	
	// 로그아웃
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		return "redirect:loginChoicePage";
	}
	
	// 일반회원 마이페이지
	@RequestMapping("myPage")
	public String myPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new CustomerMyPageCommand();
		command.execute(sqlSession, model);
		return "login/customerMyPage";
	}
	
	// 1. 닉네임체크
	// 2. 닉네임 변경
	@RequestMapping(value="nicknameUpdate", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String nicknameUpdate(@RequestParam("cNo")int cNo ,@RequestParam("cNickname") String cNickname) {
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		return lDAO.nicknameUpdate(cNickname, cNo) + "";
	}
	
	// 3. 비밀번호 변경
	@RequestMapping(value="pwUpdate", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String pwUpdate(@RequestParam("cPw") String cPw, @RequestParam("cNo")int cNo) {
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		return lDAO.pwUpdate(cPw, cNo) + "";
	}
	
	// 4. 프로필 사진 변경
	@RequestMapping(value="cPhotoUpdate", method=RequestMethod.POST)
	public String cPhotoUpdate(MultipartHttpServletRequest mr, Model model) {
		model.addAttribute("mr", mr);
		command = new CustomerMyPagePhotoUpdateCommand();
		command.execute(sqlSession, model);
		return "redirect:myPage";
	}
	
	// 일반회원 탈퇴
	@RequestMapping("customerSignOut")
	public String customerSignOut(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new CustomerSignOutCommand();
		command.execute(sqlSession, model);
		HttpSession session = request.getSession();
		
		if (session != null) {
			session.invalidate();
		}
		return "redirect:index"; 
	}
	
	// 일반회원 아이디/비밀번호 찾기 페이지
	@RequestMapping("findUserIdPw")
	public String goFindUserId() {
		return "login/customerfindUserIdPw";
	}
	
	// 일반회원 아이디 찾기 
	@RequestMapping(value="find_user_id", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String find_uId(HttpServletRequest request,Model model) {
		String cName  = request.getParameter("cName");
		String cEmail  = request.getParameter("cEmail");
		
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		String cId = lDAO.finduId(cName, cEmail);
		String message ="";
		
		if(cId !=null) {
			model.addAttribute("cId", cId);
			model.addAttribute("cName", cName);
			model.addAttribute("cEmail", cEmail);
			model.addAttribute("type", "id");
			model.addAttribute("mailSender", mailSender);
			CustomerFindIdPwCommand cmd =new CustomerFindIdPwCommand();
			cmd.execute(sqlSession, model);
			
			message ="등록된 이메일로 아이디를 보냈습니다.";
		}else {
			message ="등록된 사용자가 없습니다. 다시 확인해주세요";
		}
		return message;
	}
	
	// 일반회원 비밀번호 찾기 
	@RequestMapping(value="find_user_pw", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String find_uPw(HttpServletRequest request,Model model) {
		String cId  = request.getParameter("cId");
		String cEmail  = request.getParameter("cEmail");
		
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		int count =lDAO.finduPw(cId, cEmail);
		
		String message ="";
		if(count>0) {
			model.addAttribute("cId", cId);
			//model.addAttribute("cName", cName);
			model.addAttribute("cEmail", cEmail);
			model.addAttribute("type", "pw");
			model.addAttribute("mailSender", mailSender);
			CustomerFindIdPwCommand cmd =new CustomerFindIdPwCommand();
			String emailAuth = (String) cmd.execute(sqlSession, model);
			lDAO.UpdateTempPw(cId,emailAuth);
			message ="등록된 이메일로 임시 비밀번호를 보냈습니다.";
		}else {
			message ="등록된 사용자가 없습니다. 다시 확인해주세요";
		}
		return message;
	}
	
}
