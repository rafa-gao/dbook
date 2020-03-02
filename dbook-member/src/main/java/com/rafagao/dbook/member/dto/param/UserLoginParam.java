package com.rafagao.dbook.member.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author rafa gao
 */

@Data
public class UserLoginParam {

    private String username;

    private String password;
}
