package com.example.third.domain;

import com.example.third.exception.NotEnoughStockException;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Item{
    @Id // javax.persistence.Id ==> primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql auto-increment
    @Column(name = "item_id")
    private Long id;
    @Column(name="item_name" , nullable = false, length = 100)
    private String itemName;
    private Integer price;//판매가
    private Integer quantity;//재고수량

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    //재고수량에서 주문수량을 빼는 메소드
    public  void removeStock(int orderQuantity){
        if(this.quantity - orderQuantity<0){
            throw new NotEnoughStockException("재고 수량이 부족합니다.");

        }
        this.quantity -= orderQuantity;
    }

    //취소한 수량만큼 재고수량이 증가
    public void addStock(int orderCancelQuantity){
        this.quantity += orderCancelQuantity;
    }

}
