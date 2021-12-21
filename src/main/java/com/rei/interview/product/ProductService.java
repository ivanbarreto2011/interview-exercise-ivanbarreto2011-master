package com.rei.interview.product;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rei.interview.exception.NotValidProductException;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Check is product is valid product
     * @param product
     * @return
     */
    public boolean isValidProduct(Product product) {
    	return StringUtils.isNumeric(product.getProductId()) && product.getProductId().length() == 6 && !product.getBrand().isEmpty() && !product.getDescription().isEmpty() && product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Obtain a product given its id	
     * @param productId
     * @return a Product
     */
    public Product getProduct(String productId) {
        return productRepository.getProduct(productId);
    }
    
    /**
     * This method allows obtain the list of products for a given brand
     * @param brand
     * @return
     */
    public List<Product> getAllProductsByBrand(String brand) {
        return productRepository.getAll().stream().filter(entry->brand.equals(entry.getBrand())).collect(Collectors.toList());
    }
    
    /**
     * This methods allows to add a new product to the repository if is a valid product
     * @param product
     * @return
     */
    public Product save(Product product) throws NotValidProductException{
    	if(isValidProduct(product)) {
    	productRepository.addProduct(product);
    	}else {
    		throw  new NotValidProductException(product.getProductId()); 
    	}
    	return product;
    }
    
    /**
     * List of all products in the repository
     * @return
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(productRepository.getAll());
    }

    /**
     * Populates the product repository with data from products.csv
     * @throws IOException
     */
    @PostConstruct
    public void populateProducts() throws IOException {
        try(Reader in = new InputStreamReader(getClass().getResourceAsStream("/products.csv"))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("productId", "brand", "description", "price")
                    .withFirstRecordAsHeader()
                    .parse(in);

            for (CSVRecord recordProduct : records) {
                Product product = new Product();
                product.setProductId(recordProduct.get("productId"));
                product.setBrand(recordProduct.get("brand"));
                product.setDescription(recordProduct.get("description"));
                product.setPrice(new BigDecimal(recordProduct.get("price")));
                productRepository.addProduct(product);
            }
        }

        logger.info("Products loaded into product repository");
    }

}
