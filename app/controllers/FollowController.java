package controllers;

import play.mvc.Controller;
import play.mvc.*;
import services.FollowListService;
import views.html.*;

public class FollowController extends Controller {

    /**
     * フォロー表示
     * @param userId ユーザID
     * @return フォロー画面
     */
    @Security.Authenticated(Secured.class)
    public Result showFollow(String userId) {
        FollowListService fs = new FollowListService();
        return ok(follow.render(userId, fs.getFollowList(userId), fs.followChack(session().get("userId"), userId)));
    }
}
