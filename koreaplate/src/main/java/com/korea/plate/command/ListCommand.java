package com.korea.plate.command;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.MainDAO;
import com.korea.plate.dto.MainDTO;

public class ListCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		MainDAO ldao = sqlSession.getMapper(MainDAO.class);
		
		ArrayList<MainDTO> list = ldao.main_list();
		model.addAttribute("list", list);
		 
	}

}
