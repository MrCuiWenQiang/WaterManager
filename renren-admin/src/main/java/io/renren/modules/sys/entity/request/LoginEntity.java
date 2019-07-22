package io.renren.modules.sys.entity.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginEntity {
    @NotNull(message = "openid不能为空")
    private String openid;//vx端

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String headimgurl;
}
