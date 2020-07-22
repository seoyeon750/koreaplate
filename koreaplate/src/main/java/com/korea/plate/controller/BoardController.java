package com.korea.plate.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.korea.plate.command.Board.BoardViewCommand;
import com.korea.plate.command.Board.ReviewWriteCommand;
import com.korea.plate.common.Command;

@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
	private Command command;
	
	@RequestMapping(value="viewPage",method=RequestMethod.GET)
	public String viewPage(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		command= new BoardViewCommand();
		command.execute(sqlSession, model);
		return "board/viewPage";
	}
	
	@RequestMapping("reviewWritePage")
	public String reviewPage(HttpServletRequest request,Model model) {
		model.addAttribute("request", request);
		command= new ReviewWriteCommand();
		command.execute(sqlSession, model);
		return "board/reviewWritePage";
	}
	
}
	