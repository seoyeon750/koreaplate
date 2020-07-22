package com.korea.plate.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.korea.plate.command.ListCommand;
import com.korea.plate.common.Command;

@Controller
public class MainController {

	@Autowired
	private SqlSession sqlSession;
	private Command command;
	
	@RequestMapping("/")
	public String goMain(Model model) {
		command = new ListCommand();
		command.execute(sqlSession, model);
		return "index";
	}
	
}
