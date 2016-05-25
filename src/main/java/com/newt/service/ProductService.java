package com.newt.service;

import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.newt.dao.ProductDAOImpl;
import com.newt.dao.model.Product;

@Service

public class ProductService {

	Logger log = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductDAOImpl ProductDAO;

	@Async
	public Future<List<Product>> findAllProducts() {

		try {
			//countMillion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new AsyncResult<List<Product>>(ProductDAO.findAllProducts());
	}

	@Async
	public Future<Product> findProductById(Integer ProductId) {
		try {
			//countMillion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new AsyncResult<Product>(ProductDAO.findProductById(ProductId));
	}

	@Async
	public Future<Boolean> createProduct(Product Product) throws InterruptedException {

		// countMillion();
		Boolean result = ProductDAO.createProduct(Product);
		return new AsyncResult<Boolean>(result);
	}

	@Async
	public Future<Boolean> createOrUpdateProduct(Product Product) throws Exception {

		// countMillion();
		Boolean result = ProductDAO.createOrUpdateProduct(Product);
		return new AsyncResult<Boolean>(result);
	}

	@Async

	public Future<Boolean> deleteProductById(Integer ProductId) {

		try {
			//countMillion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new AsyncResult<Boolean>(ProductDAO.deleteProductById(ProductId));
	}

	@Async
	public Boolean deleteProductByIdFail(Integer ProductId) {

		try {
			//countMillion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ProductDAO.deleteProductById(null);
	}

	public void countMillion() {
		for (int i = 0; i < 100000; i++) {
			log.debug("Current i is " + i);
		}
	}

}
