package controllers;

import models.Tweet;
import services.TweetService;
import views.html.*;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

public class TopController extends Controller {

    /**
     * ツイートリストの表示.
     * @return TOP画面
     */
    @Security.Authenticated(Secured.class)
    public Result showTop() {
        TweetService ts = new TweetService();
        return ok(top.render(ts.getTweetList()));
    }

    /**
     * ツイートの保存.
     * @return リダイレクト
     */
    @Security.Authenticated(Secured.class)
    public Result saveTweet() {
        String[] params = {"tweet"};
        DynamicForm dForm = Form.form().bindFromRequest(params);

        Tweet tw = new Tweet();
        tw.tweet = dForm.data().get("tweet");
        tw.userId = session().get("userId");
        tw.save();

        return redirect("/top");
    }

    /**
     * ログアウト.
     * @return ログイン画面
     */
    @Security.Authenticated(Secured.class)
    public Result logout() {
        session().clear();
        return redirect("/login");
    }
}