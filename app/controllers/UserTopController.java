package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import services.FollowService;
import services.UserService;
import views.html.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTopController extends Controller {

    /**
     * ユーザTOP画面の表示.
     * @param userId userID
     * @return ユーザTOP画面
     */
    @Security.Authenticated(Secured.class)
    public Result showUserTop(String userId) {
        UserService us = new UserService();
        FollowService fs = new FollowService();

        return ok(userTop.render(us.getUserTweetList(userId), fs.followCheck(session().get("userId"), userId)));
    }

    /**
     * フォローボタン押した時の処理.
     * @return ユーザTOP画面
     */
    @Security.Authenticated(Secured.class)
    public Result clickFollowButton() {
        String[] params = {"userId", "followJudge"};
        DynamicForm dForm = Form.form().bindFromRequest(params);

        UserService us = new UserService();
        User userInfo = us.userInfo(session().get("userId"));

        // こっち、フォローしたとき
        if ("".equals(dForm.data().get("followJudge"))) {
            if (userInfo.follow != null) {
                // これ、ここに書くの微妙（下にも同じのあるから）
                List<String> followList = new ArrayList<>(Arrays.asList(userInfo.follow.split(",", 0)));

                followList.add(dForm.data().get("userId"));
                userInfo.setFollow(String.join(",", followList));
            } else {
                userInfo.setFollow(dForm.data().get("userId"));
            }

        // こっち、フォロー外されたとき
        } else {
            List<String> followList = new ArrayList<>(Arrays.asList(userInfo.follow.split(",", 0)));

            followList.removeIf(s -> s.equals(dForm.data().get("userId")));
            userInfo.setFollow(String.join(",", followList));
        }
        userInfo.update();

        return redirect("/userTop/" + dForm.data().get("userId"));
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
