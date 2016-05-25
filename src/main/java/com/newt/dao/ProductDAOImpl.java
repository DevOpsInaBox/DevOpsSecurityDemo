package com.newt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.newt.dao.model.Product;
import com.newt.dao.repository.ProductMapper;
import com.newt.dao.util.MyBatisSqlSessionFactory;

@Service
public class ProductDAOImpl {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public List<Product> findAllProducts() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
			return productMapper.selectAllProduct();
		} finally {
			// If sqlSession is not closed
			// then database Connection associated this sqlSession will not be
			// returned to pool
			// and application may run out of connections.
			sqlSession.close();
		}
	}

	public Product findProductById(Integer id) {
		logger.debug("Select Product By ID :{}", id);
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
			return productMapper.selectProductById(id);
		} finally {
			sqlSession.close();
		}
	}

	public boolean createProduct(Product orderDetail) {
		boolean flag = false;
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
			productMapper.insertProduct(orderDetail);
			sqlSession.commit(true);
			flag = true;
		} finally {
			sqlSession.close();
		}
		return flag;
	}

	public boolean createOrUpdateProduct(Product orderDetail) {
		boolean flag = false;
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
			Product cus = productMapper.selectProductById(orderDetail.getId());
			if (cus != null)
				productMapper.updateProduct(orderDetail);
			else
				productMapper.insertProduct(orderDetail);
			sqlSession.commit(true);
			flag = true;
		} finally {
			sqlSession.close();
		}
		return flag;
	}

	public boolean deleteProductById(Integer id) {
		boolean flag = false;
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
			productMapper.deleteProduct(id);
			flag = true;
			sqlSession.commit(true);

		} finally {
			sqlSession.close();
		}
		return flag;
	}
}
