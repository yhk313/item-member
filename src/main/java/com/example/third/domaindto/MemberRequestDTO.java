package com.example.third.domaindto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberRequestDTO {

    @NotEmpty
    private String loginId; // loginId ==> login_id 자동으로 필드 이름 변경
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;


}
