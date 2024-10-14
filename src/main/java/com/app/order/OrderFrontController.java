package com.app.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Result;
import com.app.order.controller.OrderListController;
import com.app.order.controller.OrderWriteOkController;


public class OrderFrontController extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		
		// URI 가져오기
		// URI 가져와서 컨테스트패스 날리고 필요한것만 가져오기
		String target = req.getRequestURI().replace(req.getContextPath() + "/","").split("\\.")[0];	
		Result result = null;
		
		if(target.equals("write-ok")) {
			result = new OrderWriteOkController().execute(req, resp);
		}else if(target.equals("list")) {
			result = new OrderListController().execute(req, resp);
		}else if(target.equals("cancel")) {	
		}else {
			// 전부 not found 404
		}
		if(result !=null) {
			if(result.isRedirect()) {
//				리다이랙트
				resp.sendRedirect(result.getPath());
			}else {
//				포워드
				req.getRequestDispatcher(result.getPath()).forward(req, resp);
			}
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
