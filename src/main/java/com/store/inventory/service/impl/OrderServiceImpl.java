package com.store.inventory.service.impl;

import com.store.inventory.dto.OrderDTO;
import com.store.inventory.entity.Order;
import com.store.inventory.entity.Product;
import com.store.inventory.repository.OrderRepository;
import com.store.inventory.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Integer Id) {
        return orderRepository.findById(Id).orElseThrow(UnsupportedOperationException::new);
    }

    @Override
    public List<Order> findByPurcharseOrder(LocalDate purchaseDate) {
        return orderRepository.findByPurchaseDate(purchaseDate);
    }

    @Override
    public Order registerOrder(OrderDTO orderDto) {
        Order order = new Order();
        order.setPurchaseDate(orderDto.getPurchaseDate());
        order.setOrderStatus(1);
        order.setProductList(orderDto.getProductList());
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrder(OrderDTO orderDto) {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setPurchaseDate(orderDto.getPurchaseDate());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setProductList(orderDto.getProductList());
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
