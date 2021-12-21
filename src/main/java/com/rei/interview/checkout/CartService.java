package com.rei.interview.checkout;


import com.rei.interview.exception.NotInventoryException;
import com.rei.interview.exception.NotValidProductException;
import com.rei.interview.inventory.InventoryService;
import com.rei.interview.product.Product;
import com.rei.interview.product.ProductService;
import com.rei.interview.rs.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartService {

    private ProductService productService;
    private InventoryService inventoryService;
    private CartRepository cartRepository;

    @Autowired
    public CartService(ProductService productService, InventoryService inventoryService, CartRepository cartRepository) {
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.cartRepository = cartRepository;
    }

    /**
     * This method allow to add a new Cart instance to the Cart Repository on memory  
     * @param cartId
     * @param product
     * @param quantity
     * @param location
     * @return a Cart entity with a new identifier
     * @throws NotInventoryException
     * @throws NotValidProductException
     */
    public Cart addToCart(String cartId, Product product, int quantity, Location location) throws NotInventoryException, NotValidProductException {

        Cart cart;

        // do we have a valid product?
        if (productService.isValidProduct(product)) {
        	// do we have inventory for this product?	
        	if(inventoryService.hasInventory(product, quantity, location)) {
                // is there already a cart for this customer?
        		cart = cartRepository.getCart(cartId);
                if (cart == null) {
                    cart = new Cart();
                    cartRepository.addCart(cart);
                }
                cart.setProduct(product, quantity);
            }else {
            	throw new NotInventoryException(product.getProductId());
            }
        }else {
            	throw new NotValidProductException(product.getProductId());
        }
            
        return cart;
    }
    
    /**
     * This method allow remove a product from the cart
     * @param cartId
     * @param product
     * @return a Cart entity with a new identifier
     */
    public Cart removeProduct(String cartId, Product product) {
    	Cart cart;
    	cart = cartRepository.getCart(cartId);
    	cart.removeProduct(product);
    	return cart;
    	
    }
    
    /**
     * This method allow update the quantity of a product in the cart
     * @param cartId
     * @param product
     * @param quantity
     * @param location
     * @return A Cart with a new product quantity 
     * @throws NotInventoryException
     */
    public Cart updateProductQuantity(String cartId, Product product, int quantity, Location location) throws NotInventoryException{
    	Cart cart;
    	if(inventoryService.hasInventoryOnline(product, quantity) || inventoryService.hasInventoryInCurrentLocation(product, quantity, location)){
    		cart = cartRepository.getCart(cartId);
	    	cart.upodateProduct(product, quantity);
    	}else {
            throw new NotInventoryException(product.getProductId());
        }
    	return cart;
    }

}

