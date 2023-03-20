package com.example.third.domaindto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
public class MemberDTO {


    private String loginId; // loginId ==> login_id 자동으로 필드 이름 변경
    @NotEmpty
    private String name;


}
