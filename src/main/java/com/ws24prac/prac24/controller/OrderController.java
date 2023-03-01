package com.ws24prac.prac24.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ws24prac.prac24.exception.OrderException;
import com.ws24prac.prac24.model.Order;
import com.ws24prac.prac24.model.OrderDetails;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
    
    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @PostMapping
    public String postOrder(@RequestBody MultiValueMap<String, String> form, Model model, HttpSession session)
        throws OrderException {

        List<OrderDetails> orderDetails = (List<OrderDetails>) session.getAttribute("cart"); 

        if (null == orderDetails) {
            System.out.println("This is a new session");
            System.out.printf("session id = %s\n", session.getId());
            orderDetails = new LinkedList<>();
            session.setAttribute("cart", orderDetails);
        }
        String item = form.getFirst("item");
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        orderDetails.add(new OrderDetails(item, quantity));
        Order ord = new Order();
        ord.setCustomerName(form.getFirst("name"));
        for (OrderDetails od : orderDetails)
            System.out.printf("description: %s, quantity: %d\n", od.getDescription(), od.getQuantity());
        ord.setOrderDetails(orderDetails);

        session.setAttribute("checkoutCart", ord);
        model.addAttribute("orderDetails", orderDetails);

        return "ordercart";
    }
}
