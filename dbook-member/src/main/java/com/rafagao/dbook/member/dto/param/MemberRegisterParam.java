package com.rafagao.dbook.member.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 用户注册所需要的参数
 *
 * @author rafa gao
 */
@Data
public class MemberRegisterParam {

    /**
     * 用户名
     */
    @JsonProperty("username")
    private String username;

    /**
     * 密码
     */
    @JsonProperty("password")
    private String password;

}
