package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.Order;
import com.example.nnpia_semestralka.Entity.OrderItem;
import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Entity.StateEnum;
import com.example.nnpia_semestralka.Repository.ItemRepository;
import com.example.nnpia_semestralka.Repository.OrderRepository;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Map<Product, Integer> cart;

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public ShoppingCartServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.cart = new HashMap<>();
    }

    @Override
    public void add(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (cart.containsKey(product)) {
            cart.replace(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
    }

    @Override
    public void remove(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (cart.containsKey(product)) {
            if (cart.get(product) > 1) {
                cart.replace(product, cart.get(product) - 1);
            } else {
                cart.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getCart() {
        return cart;
    }

    @Override
    public void checkout() {
        Order order = new Order();
        order.setState(StateEnum.NEW);

        orderRepository.save(order);

        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(entry.getKey());
            orderItem.setAmount(entry.getValue());

            itemRepository.save(orderItem);
        }

        cart.clear();
    }
}
