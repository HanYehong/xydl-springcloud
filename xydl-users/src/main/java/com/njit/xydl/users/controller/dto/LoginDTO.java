package com.njit.xydl.users.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author HanYehong
 * @date 2019/4/3 22:49
 */
@Data
public class LoginDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
