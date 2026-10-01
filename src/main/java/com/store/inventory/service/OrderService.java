package com.store.inventory.service;

import com.store.inventory.dto.OrderDTO;
import com.store.inventory.entity.Order;
import com.store.inventory.entity.Product;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Integer id);
    List<Order> findByPurcharseOrder(LocalDate purcharseDate);
    Order registerOrder(OrderDTO orderDto);
    Order updateOrder(OrderDTO orderDto);
    void deleteOrder(Integer orderId);
}
