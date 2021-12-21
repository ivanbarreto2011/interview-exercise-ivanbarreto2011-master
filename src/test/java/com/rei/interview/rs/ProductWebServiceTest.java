package com.rei.interview.rs;


import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.rei.interview.product.Product;
import com.rei.interview.rs.product.ProductDto;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductWebServiceTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestListProducts() throws Exception {

        ResponseEntity<Object> entity = this.testRestTemplate.getForEntity(
                "http://localhost:"+port+"/products",  Object.class);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    
    @Test
    public void shouldReturn200WhenSendingRequestListProductsByBrand() throws Exception {

        ResponseEntity<Object> entity = this.testRestTemplate.getForEntity(
                "http://localhost:"+port+"/products/brand/REI",  Object.class);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    
    @Test
    public void shouldReturn200WhenSendingRequestProduct() throws Exception {
        Product addItem = new Product();
        addItem.setProductId("123425");
        addItem.setBrand("REI");;
        addItem.setDescription("New Product");
        addItem.setPrice(new BigDecimal(22));

        ResponseEntity<Product> entity = this.testRestTemplate.postForEntity(
                "http://localhost:"+port+"/products", addItem, Product.class);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    
    @Test
    public void shouldReturnBadRequestCodeWhenSendingRequestProduct() throws Exception {
        Product addItem = new Product();
        addItem.setProductId("123425");
        addItem.setBrand("REI");;
        addItem.setPrice(new BigDecimal(22));

        ResponseEntity<Product> entity = this.testRestTemplate.postForEntity(
                "http://localhost:"+port+"/products", addItem, Product.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }    
    
    @Test
    public void shouldReturn200WhenSendingRequestForAdding10NewProducts() throws Exception {
    	for(int i=222222; i<=222232;i++ ) {
	        Product addItem = new Product();
	        
	        addItem.setProductId(String.valueOf(i));
	        addItem.setBrand("REI");;
	        addItem.setDescription("New Product"+String.valueOf(i));
	        addItem.setPrice(new BigDecimal(45));
	
	        ResponseEntity<Product> entity = this.testRestTemplate.postForEntity(
	                "http://localhost:"+port+"/products", addItem, Product.class);
	
	        
	        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    	}
    }
    
    
    @Test
    public void shouldReturn200WhenGetProductById() throws Exception {

        ResponseEntity<ProductDto> entity = this.testRestTemplate.getForEntity(
                "http://localhost:"+port+"/products/123456",  ProductDto.class);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    
    
}
