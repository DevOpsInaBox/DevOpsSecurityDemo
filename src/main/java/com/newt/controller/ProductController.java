package com.newt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.dao.model.Product;
import com.newt.exception.ProductException;
import com.newt.service.ProductService;

@RestController
@Api(value="Products", description="Products")
public class ProductController {

	private static Logger log = LoggerFactory.getLogger(ProductController.class);

	//@Autowired
	@Autowired
        private ProductService productService;
        

	@ApiOperation(value = "Get all the products")        
	@RequestMapping(value = "/product", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public List<Product> getAllProducts() {
		log.info("List out all the products");
		try {
			Future<List<Product>> result = productService.findAllProducts();
			return result.get();
		} catch (Exception e) {
			log.error("Exception in ProductController:findAllProducts:" + e);
			// TODO: handle exception
		}
		return null;
	}

	public List<Product> findAllProductsFail() {

		return null;
	}

	@ApiOperation(value = "Find the Product")
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public Product findProduct(@PathVariable Integer productId) {

		if (productId < 0) {
			throw new ProductException(new FieldError("ProductService", "ProductId", "Invalid Product value"));
		}

		try {
			Future<Product> result = productService.findProductById(productId);
			return result.get();
		} catch (Exception e) {
			log.error("Exception in ProductController:findAllProducts:" + e);
			// TODO: handle exception
		}
		return null;
	}

	public Product findProductByIdFail(Integer productId) {

		return null;
	}

	@ApiOperation(value = "Delete the Product")
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE, produces = { "application/json",
			"application/xml" })
	// @HystrixCommand(fallbackMethod="deleteProductByIdFail")
	public Boolean deleteProduct(@PathVariable Integer productId) {

		if (productId < 0) {
			throw new ProductException(new FieldError("Invalid Product Input", "Error", "Error"));
		}
		try {
			Future<Boolean> result = productService.deleteProductById(productId);
			return result.get();
		} catch (Exception e) {
			log.error("Exception in ProductController:findAllProducts:" + e);
			// TODO: handle exception
		}
		return null;
	}

	public Boolean deleteProductByIdFail(Integer productId) {

		return false;
	}

	@ApiOperation(value = "Create a new Product")
	@RequestMapping(value = "/product", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public Boolean createProducts(@RequestBody Product product) {

		log.info("Entering ProductController: createProducts");
		if (product == null) {
			throw new ProductException(new FieldError("Invalid Product Input", "Error", "Error"));
		}

		try {
			Future<Boolean> result = productService.createProduct(product);
			return result.get();
		} catch (Exception e) {
			log.error("Exception in ProductController:findAllProducts:" + e);
			//throw new ProductException(new FieldError("Product Error", "Error", "Error"));
		}
		return null;
	}

	public Boolean createProductFail(Product product) throws InterruptedException {

		return false;
	}

	@ApiOperation(value = "Update the Product")
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public Boolean createOrupdateProducts(@RequestBody Product product) {

		log.info("Entering ProductController: createOrupdateProducts");
		if (product == null) {
			throw new ProductException(new FieldError("Invalid Product Input", "Error", "Error"));
		}

		try {
			Future<Boolean> result = productService.createOrUpdateProduct(product);
			return result.get();
		} catch (Exception e) {
			log.error("Exception in ProductController:createOrupdateProducts:" + e);
			// TODO: handle exception
		}
		return null;
	}

	public Boolean createOrUpdateProductFail(Product product) throws InterruptedException {

		return false;
	}

}
