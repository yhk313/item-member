package com.example.third.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne // 단방향 연관관계
  @JoinColumn(name = "member_id") //fk = member_id
  private Member member;

  private LocalDateTime orderDate;
  @Enumerated(EnumType.STRING)
  private OrderStatus status;
//order을 persist 하게 되면 orderItem도 강제로 perisist 진행


  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();




  public void addOrderItem(OrderItem orderItem){
    orderItems.add(orderItem);
    orderItem.setOrder(this);//orderItems에게도 추가
  }
  //orderItem 추가예정, delivery, 결제
  public static Orders createOrder(Member member, OrderItem... orderItems){ //static 오로
    Orders order = new Orders();
    order.setMember(member);
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.ORDER);
    for(OrderItem orderItem:orderItems){
      order.addOrderItem(orderItem);
    }
    return order;
  }

  public void cancel(){
    status = OrderStatus.CANCEL;
    //아이템 별 하나씩 취소
    for(OrderItem orderItem:orderItems){
      orderItem.cancel();//각 아이템별로 주문수량이 재고에 반영됨.
    }

  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", member=" + member +
        ", orderDate=" + orderDate +
        ", status=" + status +
        '}';
  }
}
