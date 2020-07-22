package com.korea.plate.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
}
