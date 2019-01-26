package com.njit.xydl.users.entity.Vo;

public class UsersWxVo {
    private String sessionKey;
    private String openId;

    public UsersWxVo(String sessionKey, String openId) {
        this.sessionKey = sessionKey;
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
