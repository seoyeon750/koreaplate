package com.korea.plate.command.Cust;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.CustomerDAO;
import com.korea.plate.dto.AppointmentDTO;
import com.korea.plate.dto.ReviewDTO;

public class CustomerMyPageCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); 
		
		int cNo = (Integer) session.getAttribute("cNo");
		
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		
		model.addAttribute("cDTO", lDAO.selectBycNo(cNo));
		model.addAttribute("list", lDAO.customerMyReview(cNo));
		ArrayList<ReviewDTO> list = lDAO.customerMyReview(cNo);
		
		ArrayList<String> dNames =new ArrayList<String>();
		for(int i =0 ; i<list.size(); i++) {
			dNames.add( lDAO.deptName(list.get(i).getdSaup_no()));
		}
		model.addAttribute("dList", dNames);
		
		model.addAttribute("list2", lDAO.customerMyAppointment(cNo));
		ArrayList<AppointmentDTO> list2 = lDAO.customerMyAppointment(cNo);
		ArrayList<String> dNames2 = new ArrayList<String>();
		for(int i =0 ; i<list2.size(); i++) {
			dNames2.add( lDAO.deptName(list2.get(i).getdSaup_no()));
			dNames2.add( lDAO.deptPhone(list2.get(i).getdSaup_no()));
		}
		model.addAttribute("dList2", dNames2);
	}

}
