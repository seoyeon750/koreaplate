package com.korea.plate.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.korea.plate.command.Admin.AdminDeptAcceptCommand;
import com.korea.plate.command.Admin.AdminDeptAcceptListCommand;
import com.korea.plate.command.Admin.AdminDeptAcceptViewCommand;
import com.korea.plate.command.Admin.AdminDeptDeleteCommand;
import com.korea.plate.command.Admin.AdminDeptViewCommand;
import com.korea.plate.command.Admin.AdminPageCommand;
import com.korea.plate.command.Admin.AdminSearchQueryCusInfo;
import com.korea.plate.command.Admin.AdminSearchQueryDeptAcceptInfo;
import com.korea.plate.command.Admin.AdminSearchQueryDeptInfo;
import com.korea.plate.command.Admin.AdminUpdateDepartmentCommand;
import com.korea.plate.command.Admin.AdminUpdateUserCommand;
import com.korea.plate.common.Command;
import com.korea.plate.dao.AdminDAO;

@Controller
public class AdminController {

	@Autowired
	private SqlSession sqlSession;
	private Command command;
	
	// 관리자 메인 페이지 (회원 리스트)
	@RequestMapping("adminmanagePage") 
	public String adminmagePage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command=new AdminPageCommand();
		command.execute(sqlSession, model);
		return "admin/adminPage";	
	}
	
	// 회원 검색
	@RequestMapping(value="searchQueryCusInfo", method=RequestMethod.GET)
	public String searchQueryCusInfo(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new AdminSearchQueryCusInfo();
		command.execute(sqlSession, model);
		return "admin/adminPage";
	}
	
	// 회원 정보 수정
	@RequestMapping(value="UpdateUserPage", method=RequestMethod.GET)
	public String UpdateUserPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command=new AdminUpdateUserCommand();
		command.execute(sqlSession, model);
		return "admin/updateUserPage";
	}
	
	@RequestMapping("UpdateUser")
	public String UpdateUser(HttpServletRequest request,Model model) {
		String cGrade = request.getParameter("cGrade");
		String cNo =request.getParameter("cNo");
		AdminDAO aDAO = sqlSession.getMapper(AdminDAO.class);
		aDAO.UpdateUser(cGrade,cNo);
		return "redirect:adminmanagePage";
	}
	
	// 회원 삭제
	@RequestMapping(value="deleteUser", method=  RequestMethod.POST)
	public String deleteBtnuser(HttpServletRequest request, Model model) {
		String[] cNo = request.getParameterValues("cNo");
		System.out.println(cNo.length);
		AdminDAO aDAO = sqlSession.getMapper(AdminDAO.class);
		for (int i=0, len=cNo.length; i<len; i++) {
			aDAO.deleteReview(cNo[i]);
			aDAO.deleteUser(cNo[i]);			
		}
		return "redirect:adminmanagePage";
	}
	
	// 업체 리스트
	@RequestMapping("departmentView")
	public String departmentView(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command=new AdminDeptViewCommand();
		command.execute(sqlSession, model);
		return "admin/departmentViewPage";	
	}
	
	// 업체 검색
	@RequestMapping(value="searchQueryDeptInfo", method=RequestMethod.GET)
	public String searchQueryDeptInfo(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new AdminSearchQueryDeptInfo();
		command.execute(sqlSession, model);
		return "admin/departmentViewPage";
	}
	
	// 업체정보 수정
	@RequestMapping(value="UpdateDepartment", method=RequestMethod.GET)
	public String UpdateDepartment(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command=new AdminUpdateDepartmentCommand();
		command.execute(sqlSession, model);
		return "admin/updateDepartmentPage";
	}
	
	// 업체정보 삭제
	@RequestMapping(value="deleteDept", method=RequestMethod.POST)
	public String deleDept(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new AdminDeptDeleteCommand();
		command.execute(sqlSession, model);
		return "redirect:departmentView";
	}
	
	// 승인 대기 업체 리스트
	@RequestMapping(value="deptAccpetPage")
	public String deptAccpetPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new AdminDeptAcceptListCommand();
		command.execute(sqlSession, model);
		return "admin/deptAcceptPage";
	}
	
	// 승인 대기 업체 검색
	@RequestMapping(value="searchQueryDeptAcceptInfo", method=RequestMethod.GET)
	public String searchQueryDeptAcceptInfo(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new AdminSearchQueryDeptAcceptInfo();
		command.execute(sqlSession, model);
		return "admin/deptAcceptPage";
	}
	
	// 승인 대기 업체 View
	@RequestMapping(value="deptAcceptView")
	public String deptAcceptView(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new AdminDeptAcceptViewCommand();
		command.execute(sqlSession, model);
		return "admin/deptAcceptViewPage";
	}
	
	// 업체 승인
	@RequestMapping(value="deptAccept", method=RequestMethod.POST)
	public String deptAccept(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new AdminDeptAcceptCommand();
		command.execute(sqlSession, model);
		return "redirect:deptAccpetPage";
	}
	
	// 업체 거절
	@RequestMapping(value="deptReject", method=RequestMethod.POST)
	public String deptReject(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new AdminDeptDeleteCommand();
		command.execute(sqlSession, model);
		return "redirect:deptAccpetPage";
	}
	
}
