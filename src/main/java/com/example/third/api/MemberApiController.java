package com.example.third.api;

import com.example.third.domain.Member;
import com.example.third.domaindto.MemberRequestDTO;
import com.example.third.domaindto.MemberResponseDTO;
import com.example.third.domaindto.UpdateMemberRequestDTO;
import com.example.third.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {
  private final MemberService memberService;

  //@PostMapping
  public Member addMemberV1(@RequestBody @Valid Member member) {//http://localhost:8080/api/v1/members (json으로 전달)
//        필수 값인데 안들어간거,
    // 재고는 0보다 커야 한다.
//        if (member.getLoginId !=)
//
//
    Long join = memberService.join(member);
    Member member1 = memberService.findMemberById(join).get();
    return member1;
  }

  //@GetMapping
  public List<Member> listMemberV1() {  //http://localhost:8080/api/v1/members
    return memberService.findMembers();
  }


  @GetMapping("/{id}")
  public String inquiryMemberV3(//http://localhost:8080/api/v1/members/1
                                @PathVariable Long id) {
    Member member = memberService.findMemberById(id).get();
    return member.getName();
  }


  @DeleteMapping("/{id}")
  public String deleteMember(//http://localhost:8080/api/v1/members/1
                             @PathVariable Long id) {
    Long aLong = memberService.deleteMember(id);
    return aLong.toString();
  }

  @PutMapping("/{id}") // 수정 필요
  public MemberResponseDTO editMemberV2(
      @RequestBody @Valid UpdateMemberRequestDTO memberDTO,
      @PathVariable Long id) {  //http://localhost:8080/api/v1/members (json으로 전달) = DTO 사용
    memberService.updateMember(memberDTO);
    Member member = memberService.findMember(memberDTO.getLoginId()).get();
    System.out.println("member = " + member);
    return new MemberResponseDTO(member.getId());
  }

  @GetMapping
  public Results listMemberV2() {  //http://localhost:8080/api/v1/members
    List<Member> members = memberService.findMembers();
    List<MemberResponseDTO> response = members.stream()
        .map(m -> new MemberResponseDTO(m.getId()))
        .collect(Collectors.toList());
    return new Results(response);
  }

  @Data
  @AllArgsConstructor
  static class Results<T> {
    private T result;
  }
  //@GetMapping
  public String inquiryMemberV1( //http://localhost:8080/api/v1/members?loginId=aaa
                                 @RequestParam String loginId) {

    return loginId;
  }

  //@GetMapping    // get 형식의 요청에서 쿼리 문자열을 전달하기 위한 방법, 어떤 요청값이 들어올지 모르는 경우
  public String inquiryMemberV2(//http://localhost:8080/api/v1/members?loginId=aaa&name=aaa&password=aaa
                                @RequestParam Map<String, String> param) {

    StringBuilder sb = new StringBuilder();
    param.entrySet().forEach(map -> {
      sb.append(map.getKey() + ":" + map.getValue() + "\n");
    });
    return sb.toString();
  }

  //@GetMapping    // get 형식의 요청, 쿼리 문자열 전달시, 받아야 하는 파라미터가 많은 경우 DTO 객체 를 사용하는 경우
  public String inquiryMemberV3(//http://localhost:8080/api/v1/members?loginId=aaa&name=aaa&password=aaa
                                MemberRequestDTO memberRequestDTO) {
    return memberRequestDTO.toString();
  }

  @PostMapping
  public MemberResponseDTO addMemberV2(
      @RequestBody @Valid MemberRequestDTO memberDTO){//http://localhost:8080/api/v1/members (json으로 전달) = DTO 사용
    Member member = new Member();
    member.setLoginId(memberDTO.getLoginId());
    member.setName(memberDTO.getName());
    member.setPassword(memberDTO.getPassword());
    Long join = memberService.join(member);
    return new MemberResponseDTO(join);
  }
}