package com.rei.interview.product;

import com.rei.interview.util.Cache;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
public class ProductRepository {

    private Map<String, Product> products = new Cache<>();

    /**
     * This method allows add new product to the repository
     * @param product
     */
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    /**
     * This method allows to get a product by id
     * @param productId
     * @return a product if exist 
     */
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    /**
     * The collection of all products in the repository
     * @return
     */
    public Collection<Product> getAll() {
        return products.values();
    }
}
