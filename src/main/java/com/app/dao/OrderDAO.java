package com.app.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.app.dto.OrderDTO;
import com.app.mybatis.config.MyBatisConfig;
import com.app.vo.OrderVO;


public class OrderDAO {
	public SqlSession sqlSession;
	
//	객체화(기본생성자 호출)이 될 때 마다
//	SqlSession을 통해 만들어진 SqlSession을 기본생성자에 만들어 놓고 호출될 때 마다 담게한다.
//	openSession()이 SqlSessiondmf return 해주는 메서드이다.
//	SqlSession(autoCommit) : JSP에서는 자동으로 이루어지지만 spring 에서는 트랜젝션이 관리한다.
	// sql 의 최소단위 : 트랜젝션
	
	
	public OrderDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
//	상품 주문
	public void insert(OrderVO orderVO) {
		sqlSession.insert("order.insert",orderVO);
	}
	
//	주문 내역
	public List<OrderDTO> select(Long memberId) {
		return sqlSession.selectList("order.select", memberId);
	}

	
}