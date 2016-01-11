package services;

import models.User;
import play.data.DynamicForm;

public class LoginService {

    /**
     * ログイン判定.
     * @return 遷移先のパス
     */
    public String loginJudge(DynamicForm dForm) {

        UserService us = new UserService();
        User userInfo = us.userInfo(dForm.data().get("userId"));
        if (userInfo != null && dForm.data().get("password").equals(userInfo.password)) {
            return "/top";
        }

        return "/login";
    }
}
