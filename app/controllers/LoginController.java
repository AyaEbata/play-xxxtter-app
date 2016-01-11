package controllers;

import services.LoginService;
import views.html.*;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

public class LoginController extends Controller {

    /**
     * ログイン画面の表示.
     * @return ログイン画面の表示
     */
    public Result showLogin() {
        String userId = ctx().session().get("userId");

        if (userId != null) {
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
        DynamicForm input = Form.form().bindFromRequest(params);

        LoginService ls = new LoginService();
        String pathStr = ls.loginJudge(input);

        if (pathStr.equals("/top")) {
            session().clear();
            session("userId", input.data().get("userId"));
        }

        return redirect(pathStr);
    }
}
