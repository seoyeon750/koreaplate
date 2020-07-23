package com.korea.plate.controller;

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

import com.korea.plate.command.Dept.DepartmentMyPageCommand;
import com.korea.plate.command.Dept.DepartmentUpdateCommand;
import com.korea.plate.command.Dept.DeptFindIdPwCommand;
import com.korea.plate.command.Dept.DeptSignOutCommand;
import com.korea.plate.command.Dept.DeptSignUpCommand;
import com.korea.plate.common.Command;
import com.korea.plate.dao.DepartmentDAO;
import com.korea.plate.dto.DepartmentDTO;

@Controller
public class DepartmentController {

	@Autowired
	private SqlSession sqlSession;
	private Command command;
	
	// 업체회원 회원가입
	// 1. 회원가입 페이지 이동
	@RequestMapping("deptSignUpPage")
	public String godeptSingUpPage() {
		return "login/deptSignUpPage";
	}
	
	// 2. 아이디 중복체크
	@RequestMapping(value="deptidCheck", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String deptidCheck(@RequestParam("dId") String dId) {
		DepartmentDAO lDAO = sqlSession.getMapper(DepartmentDAO.class);
		return lDAO.deptidCheck(dId) + "";
	}
	
	// 3. 사업자번호 중복체크
	@RequestMapping(value="dSaup_noCheck", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String dSaup_noCheck(@RequestParam("dSaup_no") String dSaup_no) {
		DepartmentDAO lDAO = sqlSession.getMapper(DepartmentDAO.class);
		return lDAO.dSaup_noCheck(dSaup_no) + "";
	}
	
	@RequestMapping(value="deptSignUp", method=RequestMethod.POST)
	public String deptSignUp(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new DeptSignUpCommand();
		command.execute(sqlSession, model);
		return "redirect:loginChoicePage";
	}
	
	// 업체회원 로그인
	@RequestMapping(value="departmentLogin", method=RequestMethod.POST,produces="text/html; charset=utf-8")
	@ResponseBody
	public String departmentLogin(HttpServletRequest request) {
		
		String dId = request.getParameter("dId");
		String dPw = request.getParameter("dPw");
		
		DepartmentDAO lDAO = sqlSession.getMapper(DepartmentDAO.class);
		DepartmentDTO dDTO = new DepartmentDTO();
		dDTO = lDAO.departmentLogin(dId, dPw);
		String result = "0";
		if (dDTO != null) {
			request.getSession().setAttribute("dId", dDTO.getdId());
			request.getSession().setAttribute("dSaup_no", dDTO.getdSaup_no());
			request.getSession().setAttribute("dPw", dDTO.getdPw());
			result = "1";
		}
		return result;
	}
	
	// 업체회원 마이페이지
	@RequestMapping("deptmyPage")
	public String deptmyPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new DepartmentMyPageCommand();
		command.execute(sqlSession, model);
		return "login/deptMyPage";
	}
	
	// 비밀번호 변경
	@RequestMapping(value="deptpwUpdate", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String deptpwUpdate(@RequestParam("dPw") String dPw, @RequestParam("dSaup_no")String dSaup_no) {
		DepartmentDAO lDAO = sqlSession.getMapper(DepartmentDAO.class);
		return lDAO.deptpwUpdate(dPw, dSaup_no) + "";
	}
	
	// 업체정보 변경
	@RequestMapping(value="deptUpdate", method=RequestMethod.POST)
	public String deptUpdate(MultipartHttpServletRequest mr, Model model) {
		model.addAttribute("mr", mr);
		command = new DepartmentUpdateCommand();
		command.execute(sqlSession, model);
		return "redirect:deptmyPage"; 
	}
	
	// 업체회원 탈퇴
	@RequestMapping("deptSignOut")
	public String deptSignOut(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession session = request.getSession();
		command = new DeptSignOutCommand();
		command.execute(sqlSession, model);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:index";
	}
	
	// 업체회원 아이디/비밀번호 찾기 페이지
	@RequestMapping("findDeptIdPw")
	public String findDeptIdPw() {
		return "login/deptfindIdPw";
	}
		
	@Autowired
	private JavaMailSender mailSender;
	
	// 업체회원 아이디 찾기
	@RequestMapping(value="find_dept_id", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String find_dept_id(HttpServletRequest request,Model model) {
		String dId =null;
		String message =null;
		String dSaup_no = request.getParameter("dSaup_no");
		String dPhone =request.getParameter("dPhone");
		String dEmail=request.getParameter("dEmail");
		System.out.println(dEmail);
		DepartmentDAO ldao = sqlSession.getMapper(DepartmentDAO.class);
		dId =ldao.find_dept_id(dSaup_no, dPhone);
		System.out.println(dId);
		if(dId !=null) {
			model.addAttribute("dSaup_no", dSaup_no);
			model.addAttribute("dId", dId);
			model.addAttribute("dEmail", dEmail);
			model.addAttribute("type", "id");
			model.addAttribute("mailSender", mailSender);
			DeptFindIdPwCommand cmd =new DeptFindIdPwCommand();
			 cmd.execute(sqlSession, model);
			message = "입력하신 메일에서 아이디를 확인해주세요.";
		}else {
			message = "입력하신 정보는 없습니다 .";
		}
		return message;
	}
	
	// 업체회원 비밀번호 찾기
	@RequestMapping(value="find_dept_pw", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	@ResponseBody
	public String find_deptPw(HttpServletRequest request,Model model) {
		String dId = request.getParameter("dId");
		String dSaup_no = request.getParameter("dSaup_no");
		String dEmail = request.getParameter("dEmail");
		String message ="";
		DepartmentDAO ldao = sqlSession.getMapper(DepartmentDAO.class);
		int count =ldao.findDeptPw(dId, dSaup_no);
		if(count>0) {
			model.addAttribute("dId", dId);
			//model.addAttribute("cName", cName);
			model.addAttribute("dEmail", dEmail);
			model.addAttribute("type", "pw");
			model.addAttribute("mailSender", mailSender);
			DeptFindIdPwCommand cmd =new DeptFindIdPwCommand();
			String emailAuth = (String) cmd.execute(sqlSession, model);
			ldao.deptpwUpdate(emailAuth, dSaup_no);
			message ="등록된 이메일로 임시 비밀번호를 보냈습니다.";
		}else {
			message ="등록된 정보가 틀립니다. 다시 확인해주세요";
		}
		return message;
	}
	
}
