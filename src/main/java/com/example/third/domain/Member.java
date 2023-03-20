package com.example.third.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본키 생성을 데이터베이스에 위임.
    @Column(name = "member_id")
    private Long id;
    @Column(name = "login_id", length = 10, nullable = false)
    private String loginId; // loginId ==> login_id 자동으로 필드 이름 변경
    @Column(length=45)
//        nullable = false
    private String name;
    @Column(length=10)
//        nullable=false)
    private String password;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
