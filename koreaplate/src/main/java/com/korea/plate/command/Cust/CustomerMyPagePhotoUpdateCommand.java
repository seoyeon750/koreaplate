package com.korea.plate.command.Cust;

import java.io.File;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.korea.plate.common.Command;
import com.korea.plate.dao.CustomerDAO;

public class CustomerMyPagePhotoUpdateCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) map.get("mr");
		
		String cNo = mr.getParameter("cNo");
		MultipartFile cPhoto = mr.getFile("cPhoto");
		
		String originFilename = cPhoto.getOriginalFilename();
		String extName = originFilename.substring(originFilename.lastIndexOf(".") + 1);
		String saveFilename = null;
		
		try {
			saveFilename = originFilename.substring(0, originFilename.lastIndexOf(".")) +
					   "_" +
					   System.currentTimeMillis() +
					   "." + extName;
			
			String realPath = mr.getSession().getServletContext().getRealPath("/resources/storage/user_img");
			File directory = new File(realPath);
			if ( !directory.exists() ) {
				directory.mkdirs();  // mkdirs (하위 디렉토리를 모두 만든다.)
			}
			File saveFile = new File(realPath, saveFilename); // (경로, 파일명)
			cPhoto.transferTo(saveFile);
	
			CustomerDAO ldao = sqlSession.getMapper(CustomerDAO.class);
			ldao.customerPhotoUpdate(cNo, saveFilename);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
