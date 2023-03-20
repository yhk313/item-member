package com.example.third.domaindto;

import lombok.Data;

@Data
public class MemberResponseDTO {


    private Long id; // loginId ==> login_id 자동으로 필드 이름 변경

    public MemberResponseDTO(Long id){
        this.id = id;
    }


}
