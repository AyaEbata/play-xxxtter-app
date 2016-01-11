package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class RegistrationController extends Controller {

    /**
     * 新規登録画面の表示.
     * @return 新規登録画面
     */
    public Result showRegistration() {
        return ok(registration.render());
    }

    /**
     * ユーザ登録機能.
     * @return ログイン画面
     */
    public Result userRegistration() {
        String[] params = {"userId", "password"};
        DynamicForm dForm = Form.form().bindFromRequest(params);

        User user = new User();
        user.userId = dForm.data().get("userId");
        user.password = dForm.data().get("password");
        user.save();

        return ok(login.render());
    }
}
