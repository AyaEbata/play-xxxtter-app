package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import services.FollowListService;
import services.UserTweetService;
import views.html.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTopController extends Controller {

    /**
     * ユーザのtop画面.
     * @param userId userID
     * @return ユーザのツイート
     */
    @Security.Authenticated(Secured.class)
    public Result showUserTop(String userId) {
        UserTweetService us = new UserTweetService();
        FollowListService fs = new FollowListService();

        return ok(userTop.render(us.getUserTweetList(userId), fs.followChack(session().get("userId"), userId)));
    }

    /**
     * フォローボタン押した時の処理.
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result follow() {
        String[] params = {"userId", "followJudge"};
        DynamicForm input = Form.form().bindFromRequest(params);

        // こっち、フォローしたとき
        if (input.data().get("followJudge") == "") {
            User user = User.find.where()
                    .eq("userId", session().get("userId"))
                    .findUnique();

            if (user.follow != null) {
                List<String> followList = new ArrayList<>(Arrays.asList(user.follow.split(",", 0)));
                followList.add(input.data().get("userId"));
                user.setFollow(String.join(",", followList));
            } else {
                user.setFollow(input.data().get("userId"));
            }
            user.update();

        // こっち、フォロー外されたとき
        } else {
            User user = User.find.where()
                    .eq("userId", session().get("userId"))
                    .findUnique();

            List<String> followList = new ArrayList<>(Arrays.asList(user.follow.split(",", 0)));
            followList.removeIf(s -> s.equals(input.data().get("userId")));
            user.setFollow(String.join(",", followList));
            user.update();
        }

        return redirect("/userTop/" + input.data().get("userId"));
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
