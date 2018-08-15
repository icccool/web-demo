package com.web.controller;

import com.web.hotswap.JavaClassExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/hotswap")
public class HotSwapController {

	private static Logger logger = LoggerFactory.getLogger(HotSwapController.class);

	@RequestMapping("/fileUpload")
	public String fileUpload() {
		return "jsp/hotswap/fileUpload";
	}

	@RequestMapping("/swap")
	@ResponseBody
	public String swap(@RequestParam("file")MultipartFile uploadFile, HttpSession session) {

		try {
			//保存到项目下
			String fileName = uploadFile.getOriginalFilename();
			logger.info("fileName = {}",fileName);
//			String leftPath = session.getServletContext().getRealPath("/tmp");
//			File file = new File(leftPath,fileName);
//			uploadFile.transferTo(file);

			//
			byte[] b = uploadFile.getBytes();
//			InputStream is = new FileInputStream(cls);
//			String cls = PathUtil.getWebRootPath() + "/WEB-INF/classes/com/web/hotswap/TestClass.class";
//			InputStream is = new FileInputStream(cls);
//			byte[] b = new byte[is.available()];
//			is.read(b);
//			is.close();

			return JavaClassExecuter.execute(b);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}
}
