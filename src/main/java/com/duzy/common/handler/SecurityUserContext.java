package com.duzy.common.handler;

import com.duzy.model.UserModel;
import org.springframework.core.NamedThreadLocal;

public class SecurityUserContext {

    private static final ThreadLocal<UserModel> threadLocal = new NamedThreadLocal<>("userInfo");

    public static void addUserInfo(UserModel user) {
        threadLocal.set(user);
    }

    public static UserModel getUserInfo() {
        return threadLocal.get();
    }

    public static void removeUser() {
        threadLocal.remove();
    }

}
