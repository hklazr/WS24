package com.ws24prac.prac24.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ws24prac.prac24.model.Order;
import com.ws24prac.prac24.model.OrderDetails;

@Repository
public class OrderDetailsRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static String SQL_INSERT_ORDER_DETAILS = "insert into line_item(description, quantity, order_id) values (?, ?, ?)";


    public void addOrderItems(Order order) {
        addOrderItems(order.getOrderDetails(), order.getOrderId());
    }
    public void addOrderItems(List<OrderDetails> orderDetails, String orderId) {
        List<Object[]> data = orderDetails.stream()
                .map(od -> {
                    Object[] l = new Object[3];
                    l[0] = od.getDescription();
                    l[1] = od.getQuantity();
                    l[2] = orderId;
                    return l;
                })
                .toList();

        // Batch update
        jdbcTemplate.batchUpdate(SQL_INSERT_ORDER_DETAILS, data);
    }
}
