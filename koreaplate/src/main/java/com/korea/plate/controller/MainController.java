package com.korea.plate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.korea.plate.command.ListCommand;
import com.korea.plate.command.LocationListCommand;
import com.korea.plate.command.SearchCommand;
import com.korea.plate.common.Command;

@Controller
public class MainController {

	@Autowired
	private SqlSession sqlSession;
	private Command command;
	
	@RequestMapping("/")
	public String goIndex(Model model) {
		command = new ListCommand();
		command.execute(sqlSession, model);
		return "index";
	}
	
	@RequestMapping("index")
	public String goIndex() {
		return "redirect:/";
	}
	
	@RequestMapping("main")
	public String goMain(Model model) {
		command = new ListCommand();
		command.execute(sqlSession, model);
		return "main";
	}
	
	@RequestMapping("main2")
	public String goMain2(Model model) {
		command = new ListCommand();
		command.execute(sqlSession, model);
		return "main2";
	}
	
	@RequestMapping("locationlist")
	public String golocation_list(@RequestParam("location") String location, Model model) {
		model.addAttribute("location", location);
		command = new LocationListCommand();
		command.execute(sqlSession, model);
		return "board/locationListPage";
	}
	
	@RequestMapping("searchPage")
	public String search_list(HttpServletRequest request, Model model, HttpServletResponse response) {	
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		command = new SearchCommand();
		command.execute(sqlSession, model);
		return "board/searchPage";
	}
	
}
