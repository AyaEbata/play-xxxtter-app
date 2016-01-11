package services;

import models.User;
import play.data.DynamicForm;

public class LoginService {

    /**
     * ログイン判定.
     * @return 遷移先
     */
    public String loginJudge(DynamicForm input) {

        User user = User.find.where()
                .eq("userId", input.data().get("userId"))
                .findUnique();

        if (user != null && input.data().get("password").equals(user.password)) {
            return "/top";
        }

        return "/login";
    }
}
