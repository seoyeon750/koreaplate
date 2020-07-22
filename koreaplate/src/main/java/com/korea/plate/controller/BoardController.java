package com.korea.plate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.plate.command.Board.BoardViewCommand;
import com.korea.plate.command.Board.ReviewDetailCommand;
import com.korea.plate.command.Board.ReviewWriteCommand;
import com.korea.plate.common.Command;
import com.korea.plate.dao.BoardDAO;
import com.korea.plate.dto.ReviewDTO;

@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
	private Command command;
	
	@RequestMapping(value="viewPage",method=RequestMethod.GET)
	public String viewPage(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		command = new BoardViewCommand();
		command.execute(sqlSession, model);
		return "board/viewPage";
	}
	
	// 리뷰 리스트
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="getReview", produces="application/json; charset=utf-8")
	@ResponseBody
	public ResponseEntity ajax_reviewList(HttpServletRequest request) {
		 HttpHeaders responseHeaders = new HttpHeaders();
		 ArrayList<HashMap> review_list = new ArrayList<HashMap>();
		 
		 String type = request.getParameter("type");
		 String dSaup_no = request.getParameter("dSaup_no");
		 
		 BoardDAO bdao = sqlSession.getMapper(BoardDAO.class);
		 
		 List<ReviewDTO> rdto =null ;
		 if(type.equals("all")) {
			 rdto=bdao.reviewAll(dSaup_no);
		 }else if(type.equals("ap")){
			 rdto=bdao.reviewAp(dSaup_no);
		 }else {
			 rdto=bdao.reviewNp(dSaup_no);
		 }
		 
		 if(rdto.size()>0) {
			 for(int i=0; i<rdto.size(); i++) {	
				 HashMap re = new HashMap();
				 re.put("rNo", rdto.get(i).getrNo());
				 re.put("rTitle",rdto.get(i).getrTitle());
				 re.put("rContent", rdto.get(i).getrContent());
				 re.put("rWriter_date", rdto.get(i).getrWriter_date());
				 re.put("cNickname", rdto.get(i).getcNickname());
				 re.put("cPoto", rdto.get(i).getcPhoto());
				 re.put("rPoint",rdto.get(i).getrPoint());
				 review_list.add(re);
			 }
		 }
		 JSONArray json = new JSONArray(review_list);
		 return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED); 
	}
	
	// 리뷰 누르면 리뷰 창
	@RequestMapping("reviewDetail")
	public String reviewDetail(HttpServletRequest request,Model model) {
		model.addAttribute("request", request);
		command = new ReviewDetailCommand();
		command.execute(sqlSession, model);
		return "board/reviewDetail";
	}
	
	// 리뷰 관련 Mapping
	@RequestMapping("reviewWritePage")
	public String reviewPage(HttpServletRequest request,Model model) {
		model.addAttribute("request", request);
		command= new ReviewWriteCommand();
		command.execute(sqlSession, model);
		return "board/reviewWritePage";
	}
	
}
	