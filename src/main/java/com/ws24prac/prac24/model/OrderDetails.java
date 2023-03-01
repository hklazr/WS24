package com.ws24prac.prac24.model;

import java.math.BigDecimal;

public class OrderDetails {
    
        private int id;
        private String product;
        private BigDecimal unitPrice;
        private BigDecimal discount;
        private int quantity;
        private String description;

        public OrderDetails(String description, Integer quantity) {
            this.description = description;
            this.quantity = quantity;
        }
        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getProduct() {
            return product;
        }
        public void setProduct(String product) {
            this.product = product;
        }
        public BigDecimal getUnitPrice() {
            return unitPrice;
        }
        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }
        public BigDecimal getDiscount() {
            return discount;
        }
        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }
 

        
}
