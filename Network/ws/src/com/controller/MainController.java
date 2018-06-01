package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vo.Test;

@Controller
public class MainController {

	Logger logger = Logger.getLogger("work");
	
	@RequestMapping("/main.do")
	@ResponseBody
	protected String file(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Test t = new Test();
		String speed = request.getParameter("speed");		
		String temp = request.getParameter("temp");	
		logger.debug(speed+", "+ temp);
		
		PrintWriter out = response.getWriter();
		out.write("Ohhora");

		return "main";
	}
}
