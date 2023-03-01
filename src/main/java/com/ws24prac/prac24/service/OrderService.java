package com.ws24prac.prac24.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws24prac.prac24.exception.OrderException;
import com.ws24prac.prac24.model.Order;
import com.ws24prac.prac24.repo.OrderDetailsRepo;
import com.ws24prac.prac24.repo.OrderRepo;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailsRepo ODRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void createNewOrder(Order ord) throws OrderException {

        // Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        System.out.printf(">>>> OrderId: %s\n", orderId);

        ord.setOrderId(orderId);

        // Create the purchaseOrder
        orderRepo.insertOrder(ord);
        System.out.printf(">>>> order quantity: %s\n", ord.getOrderDetails().size());
        if (ord.getOrderDetails().size() > 5)
            throw new OrderException("Cannot order more than 5 items");
        // Create the associated line items
        ODRepo.addOrderItems(ord.getOrderDetails(), orderId);

    }
}
