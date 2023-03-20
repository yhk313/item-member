package com.example.third.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="order_item")
@Getter
@Setter

public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_item_id")
  private Long id;
  @ManyToOne
  @JoinColumn(name="item_id")
  private Item item;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Orders order;
  private int orderPrice;
  private int orderQuantity;
  public static OrderItem createOrderItem(Item item, int orderPrice, int orderQuantity){
    OrderItem orderItem = new OrderItem();
    orderItem.setItem(item);
    orderItem.setOrderPrice(orderPrice);
    orderItem.setOrderQuantity(orderQuantity);

    item.removeStock(orderQuantity);// 주문 수량만큼 재고 감소
    return orderItem;
  }
  public void cancel(){
    this.item.addStock(orderQuantity);// 취소수량만큼 재고수량 추가
  }
  @Override
  public String toString() {
    return "OrderItem{" +
        "id=" + id +
        ", item=" + item +
        ", order=" + order +
        ", orderPrice=" + orderPrice +
        ", orderQuantity=" + orderQuantity +
        '}';
  }
}
