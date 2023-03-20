package com.example.third.repository;

import com.example.third.domain.Member;
import com.example.third.domaindto.UpdateMemberRequestDTO;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public Optional<Member> findById(Long id);
    public Optional<Member> findByLoginId(String loginId);
    public List<Member> findAll();
    public void update(Long id, Member member);

    public void updateV2(UpdateMemberRequestDTO member);

    public Long remove(Long id);


}
