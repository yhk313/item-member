//package com.example.third.service.mesages;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.NoSuchMessageException;
//
//import java.util.Locale;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class MessageSourceTest1 {
//  @Autowired
//  MessageSoure ms;
//
//  @Test
//  void helloTest(){
//    String hello = ms.getMessage("hello", null, null);
//    assertThat(hello).isEqualTo("안녕");
//  }
//  @Test
//  void helloTestWithArgs(){
//    String hello = ms.getMessage("hello.name", new Object[]{"홍길동", "김연아"}, null);
//    assertThat(hello).isEqualTo("안녕 홍길동 김연아");
//  }
//
//  @Test
//  void helloTestEng(){
//    String hello = ms.getMessage("hello", null, Locale.ENGLISH);
//    assertThat(hello).isEqualTo("hello");
//  }
//
//  @Test
//  void helloTestEng(){
//    String hello = ms.getMessage("hello.name", new Object[]{"aaa"}, Locale.ENGLISH);
//    assertThat(hello).isEqualTo("hello aaa");
//  }
//
//  @Test
//  void noCodeTest(){
//    assertThatThrownBy(()->ms.getMessage("hello2", null, null))
//        .isInstanceOf((NoSuchMessageException.class);
//  }
//
//  @Test
//  void noCodeDefaultTest(){
//    String result = ms.getMessage("hello2", null, "기본메세지 입니다.", null))
//    assertThat(result).isEqualTo("기본메세지 입니다.");
//  }
//}
