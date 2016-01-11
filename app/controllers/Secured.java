package controllers;

import play.mvc.*;
import play.mvc.Http.*;

public class Secured extends Security.Authenticator {

    /**
     * 現在ログインしているユーザーのユーザー名を取得.
     * @param ctx
     * @return userId（nullが返ったらonUnathorizedが実行される）
     */
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("userId");
    }

    /**
     * リクエストを中断.
     * @param ctx
     * @return ログイン画面にリダイレクト
     */
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect("/login");
    }
}