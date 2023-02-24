package com.duzy.common.handler;

import com.duzy.model.SysUserModel;
import org.springframework.core.NamedThreadLocal;

public class SecurityUserContext {

    private static final ThreadLocal<SysUserModel> threadLocal = new NamedThreadLocal<>("userInfo");

    public static void addUserInfo(SysUserModel user) {
        threadLocal.set(user);
    }

    public static SysUserModel getUserInfo() {
        return threadLocal.get();
    }

    public static void removeUser() {
        threadLocal.remove();
    }

}
