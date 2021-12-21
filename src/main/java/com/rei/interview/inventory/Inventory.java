package com.rei.interview.inventory;

import java.util.Objects;

public class Inventory {
    private String productId;
    private String location;
    private Integer quantity;
    
    public String getKey() {
        return productId + "-"+ location;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return productId.equals(inventory.productId) && location.equals(inventory.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId,location);
    }

}
