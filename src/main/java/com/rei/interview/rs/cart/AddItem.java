package com.rei.interview.rs.cart;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.rei.interview.rs.Location;

public class AddItem {

	@NotBlank(message = "ProductId is mandatory")
    private String productId;
	@Min(1)
    private int quantity;
	@NotNull
    private Location location;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
