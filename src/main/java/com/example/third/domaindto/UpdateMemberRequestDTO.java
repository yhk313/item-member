package com.example.third.domaindto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateMemberRequestDTO {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;


}
