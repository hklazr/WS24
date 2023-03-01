package com.ws24prac.prac24.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ws24prac.prac24.model.Order;

@Repository
public class OrderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String INSERT_NEW_ORDER = "INSERT INTO orders (order_id, order_date, customer_name) VALUES (?,?,?)";
    

    public boolean insertOrder(Order order) {
        return jdbcTemplate.update
        (INSERT_NEW_ORDER, order.getOrderId(), order.getCustomerName()) > 0;
    }
}
