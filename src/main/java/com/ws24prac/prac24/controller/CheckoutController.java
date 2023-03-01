package com.ws24prac.prac24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ws24prac.prac24.exception.OrderException;
import com.ws24prac.prac24.model.Order;
import com.ws24prac.prac24.model.OrderDetails;
import com.ws24prac.prac24.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public String postCheckOut (Model model, HttpSession session) throws OrderException {

        List<OrderDetails> od = (List<OrderDetails>) session.getAttribute("cart");
        Order order = (Order) session.getAttribute("checkout");

        session.invalidate();
        orderService.createNewOrder(order);
        model.addAttribute("total", od.size());

        return "checkout";

    }
}
