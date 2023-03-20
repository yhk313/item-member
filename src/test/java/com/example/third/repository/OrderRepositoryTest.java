package com.example.third.repository;

import com.example.third.domain.Member;
import com.example.third.domain.Orders;
import com.example.third.domain.OrderStatus;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class OrderRepositoryTest {
  @Autowired EntityManager em;
  @Autowired OrderRepository orderRepository;


  @Test
  void save() {
   Member member = em.find(Member.class, 1L);
   Orders order=new Orders();
   order.setMember(member);
   order.setStatus((OrderStatus.ORDER));

   orderRepository.save(order);
    Orders findOrder = orderRepository.findByOne((order.getId()));
    Assertions.assertThat(order).isEqualTo(findOrder);

  }
}