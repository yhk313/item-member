package com.example.third.service;

import com.example.third.domain.Item;
import com.example.third.domain.Member;
import com.example.third.domain.OrderItem;
import com.example.third.domain.Orders;
import com.example.third.repository.ItemRepository;
import com.example.third.repository.MemberRepository;
import com.example.third.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
//@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final MemberRepository memberRepository;

  private final ItemRepository itemRepository;

  @Transactional
  public Long order(Long member_id, Long id, int orderQuantity){
    Member member = memberRepository.findById(member_id).get();
    Item item = itemRepository.findById(id).get();
    // order + orderItem + item

    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderQuantity);
    Orders order = Orders.createOrder(member, orderItem);

    orderRepository.save(order);
    return order.getId();
  }

  @Transactional
  public void cancelOrder(Long orderId){
    Orders order = orderRepository.findByOne(orderId);
    // jpa 가 dirty checking을 해서 자동으로 db에 update를 해줌.
    order.cancel();
  }
  public List<Orders> findOrders(String name){
    return orderRepository.findAll(name);
  }
}