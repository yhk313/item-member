package com.example.third.repository;

import com.example.third.domain.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
//@Repository
@RequiredArgsConstructor// 생성자 주입
public class OrderRepository {
  private final EntityManager em;

  public void save(Orders order){
    em.persist(order);
  }

  public Orders findByOne(Long id){
    return Optional.ofNullable(em.find(Orders.class, id)).get();

  }
  public List<Orders> findAll(String name){
    return em.createQuery("select o from Orders o join o.member m where m.name= :name", Orders.class)
        .setParameter("name", name)
        .setMaxResults(100)
        .getResultList();
  }


}
