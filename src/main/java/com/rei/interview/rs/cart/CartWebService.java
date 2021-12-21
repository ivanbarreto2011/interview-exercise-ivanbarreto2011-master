package com.rei.interview.rs.cart;

import com.rei.interview.checkout.Cart;
import com.rei.interview.checkout.CartService;
import com.rei.interview.exception.CartException;
import com.rei.interview.product.Product;
import com.rei.interview.product.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartWebService {

    private ProductService productService;
    private CartService cartService;

    @Autowired
    public CartWebService(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    @ResponseBody
    public String test() {
        return "test";
    }

    @PostMapping("/{cartId}")
    @ResponseBody
    public CartDto addToCart(@PathParam("cartId") String cartId,
                                                    @Valid @RequestBody AddItem addItem) throws CartException {

        Product product = productService.getProduct(addItem.getProductId());
        Cart cart = cartService.addToCart(cartId, product, addItem.getQuantity(), addItem.getLocation());

        return transform(cart);
    }
    
    @PostMapping("/{cartId}/{productId}")
    @ResponseBody
    public CartDto removeToCart(@PathParam("cartId") String cartId,
    		@PathParam("productId") String productId) throws CartException {

    	Product product = productService.getProduct(productId);
        Cart cart = cartService.removeProduct(cartId, product); 
        
        return transform(cart);
    }
    
    
    @PatchMapping("/{cartId}")
    @ResponseBody
    public CartDto update(@PathParam("cartId") String cartId,
                                                    @RequestBody AddItem addItem) throws CartException {
        Product product = productService.getProduct(addItem.getProductId());
        Cart cart = cartService.updateProductQuantity(cartId, product, addItem.getQuantity(), addItem.getLocation());

        return transform(cart);
    }


    private CartDto transform(Cart cart) {
        CartDto dto = new CartDto();
        dto.setCartId(cart.getCartId().toString());

        List<CartItem> dtoItems = new ArrayList<>();

        for(Map.Entry<Product,Integer> entry : cart.getProducts().entrySet()) {
            dtoItems.add(new CartItem(entry.getKey().getProductId(), entry.getValue()));
        }

        dto.setItems(dtoItems);

        return dto;
    }
    


}
