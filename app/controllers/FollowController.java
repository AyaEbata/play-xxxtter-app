package controllers;

import play.mvc.Controller;
import play.mvc.*;
import services.FollowService;
import views.html.*;

public class FollowController extends Controller {

    /**
     * フォロー画面の表示
     * @param userId ユーザID
     * @return フォロー画面
     */
    @Security.Authenticated(Secured.class)
    public Result showFollow(String userId) {
        FollowService fs = new FollowService();
        return ok(follow.render(userId, fs.getFollowList(userId), fs.followCheck(session().get("userId"), userId)));
    }

    // TODO: フォローの処理
}
