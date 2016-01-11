package controllers;

import services.LoginService;
import views.html.*;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

public class LoginController extends Controller {

    /**
     * ログイン画面の表示.
     * @return ログイン画面
     */
    public Result showLogin() {
        String sessionUserId = ctx().session().get("userId");

        if (sessionUserId != null) {
            return redirect("/top");
        }

        return ok(login.render());
    }

    /**
     * ログイン機能.
     * @return 指定された画面に遷移
     */
    public Result login() {
        String[] params = {"userId", "password"};
        DynamicForm dForm = Form.form().bindFromRequest(params);

        LoginService ls = new LoginService();
        String pathStr = ls.loginJudge(dForm);

        if (pathStr.equals("/top")) {
            session().clear();
            session("userId", dForm.data().get("userId"));
        }

        return redirect(pathStr);
    }
}
