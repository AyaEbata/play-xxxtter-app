package services;

import models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FollowService {

    /**
     * ユーザIDからフォローリスト抽出.
     * @param userId ユーザID
     * @return フォローリスト
     */
    public List<String> getFollowList(String userId) {
        User user = User.find.where()
                .eq("userId", userId)
                .findUnique();

        List<String> followList = new ArrayList<>();

        if (user.follow != null ) {
            followList = Arrays.asList(user.follow.split(",", 0));
        }

        return followList;
    }

    /**
     * フォローしてるかどうかのチェック.
     * @param sessionUserId ログインしているuserId
     * @param userId 表示しているuserId
     * @return フォローしてるかどうか（フォローしてたらfollowってかえす）
     */
    public String followCheck(String sessionUserId, String userId) {
        FollowService fs = new FollowService();

        for (String f : fs.getFollowList(sessionUserId)) {
            if (f.equals(userId)) {
                return "follow";
            }
        }

        return "";
    }
}
