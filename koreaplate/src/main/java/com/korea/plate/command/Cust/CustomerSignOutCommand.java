package com.korea.plate.command.Cust;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.CustomerDAO;

public class CustomerSignOutCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String cNo = request.getParameter("cNo");
		CustomerDAO lDAO = sqlSession.getMapper(CustomerDAO.class);
		
		lDAO.customerSignOut1(cNo); // 리뷰삭제
		lDAO.customerSignOut2(cNo); // 예약삭제
		lDAO.customerSignOut3(cNo); // 회원탈퇴
		
	}

}
