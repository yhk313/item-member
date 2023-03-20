package com.example.third.controller;

import com.example.third.controller.session.MemberSession;
import com.example.third.controller.session.SessionConst;
import com.example.third.controller.session.TestMemberSession;
import com.example.third.domain.Item;
import com.example.third.domain.Orders;
import com.example.third.service.ItemService;
import com.example.third.service.MemberService;
import com.example.third.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
  private final OrderService orderService;
  private final MemberService memberService;
  private final ItemService itemService;

  @GetMapping("/add")
  public String order(
      HttpServletRequest request, Model model){
//    HttpSession session = request.getSession(false);
//    MemberSession memberSession = (MemberSession)session.getAttribute(SessionConst.NAME);
    TestMemberSession memberSession = new TestMemberSession();
    List<Item> items = itemService.allItems();
    model.addAttribute("items", items);
    model.addAttribute("member", memberSession);

    return "order/orderForm";
  }

  @PostMapping("/add")
  public String order(
                      @RequestParam Long id,
                      @RequestParam Long itemId,
                      @RequestParam int orderQuantity){
    System.out.println("id = " + id);
    orderService.order(id,itemId,orderQuantity);
//    orderService.order(memberSession.getId());
    return "redirect:/order/orders";
  }

  @GetMapping("/orders")
  public String orderList(HttpServletRequest request, Model model){
//    HttpSession session = request.getSession(false);
//    MemberSession memberSession = (MemberSession)session.getAttribute(SessionConst.NAME);
    TestMemberSession memberSession = new TestMemberSession();
    log.info("order controller ==> memberSession: {}", memberSession);
    List<Orders> orders = orderService.findOrders(memberSession.getName());


    model.addAttribute("orders", orders);
    return "order/orderList";
  }

  @PostMapping("/cancel/{orderId}")
  public String cancelOrder(HttpServletRequest request,
                            @PathVariable Long orderId){
    //    HttpSession session = request.getSession(false);
    //    MemberSession memberSession = (MemberSession)session.getAttribute(SessionConst.NAME);
    TestMemberSession memberSession = new TestMemberSession();
    // 로그인 한 사람과 동일 한 오더인 경우에만 삭제하도록 조건문 추가
    orderService.cancelOrder(orderId);
    return "redirect:/order/orders";

  }
}