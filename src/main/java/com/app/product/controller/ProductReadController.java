package com.app.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Action;
import com.app.Result;
import com.app.dao.ProductDAO;
import com.app.vo.ProductVO;

public class ProductReadController implements Action{
	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Result result = new Result();
		ProductDAO productDAO = new ProductDAO();
		
		
//		optional = 오류날수도 있다.NPE방지								오류가 발생하면(null 값이면)
//		productDAO.select(Long.parseLong(req.getParameter("id"))).orElseThrow(() -> {
//			throw new RuntimeException();
//		});
		
//		null 안뜨면 productVO , null 뜨면 오류
		req.setAttribute("product", productDAO.select(Long.parseLong(req.getParameter("id"))).orElseThrow(() -> {
			throw new RuntimeException();
		}));
		result.setPath("read.jsp");
		
		return result;
	}
}
