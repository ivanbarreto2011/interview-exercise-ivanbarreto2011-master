package com.rei.interview.rs.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rei.interview.product.Product;
import com.rei.interview.product.ProductService;

@RestController
public class ProductWebService {
	
    private ProductService productService;
    
    @Autowired
    public ProductWebService(ProductService productService) {
    	this.productService=productService;
    }
	

    @GetMapping("/products")
    @ResponseBody
    public List<Product> all() {
		    return productService.getAllProducts();
    }
    
	@PostMapping("/products")
	public Product newProduct(@Valid @RequestBody Product newProduct) {
	    return productService.save(newProduct);
	}
	
	@GetMapping("/products/{id}")
	public ProductDto one(@PathVariable String id) {
		 Product product= productService.getProduct(id);
		 if(product!=null) {
			 return transform(product);
		 }else {
			throw new ProductNotFoundException(id);
		 }
	}
	
	@GetMapping("/products/brand/{brand}")
	public List<Product> allBrand(@PathVariable String brand) {
		 List<Product> products= productService.getAllProductsByBrand(brand); 
	  if(!products.isEmpty()) {	   
		  return products;
	  }else{
		  throw new ProductNotFoundException(brand);
	  }
	}
	
	/**
	 * 
	 * @param product
	 * @return
	 */
	private ProductDto transform(Product product) {
	        ProductDto dto = new ProductDto();
	        dto.setProductId(product.getProductId());
	        dto.setDescription(product.getDescription());
	        dto.setBrand(product.getBrand());
	        dto.setPrice(product.getPrice());
	        return dto;
	}
	
}
