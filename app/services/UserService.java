package services;

import models.Tweet;
import models.User;

import java.util.List;

public class UserService {

    /**
     * ユーザで絞ったツイート
     * @param userId ユーザID
     * @return ツイートリスト
     */
    public List<Tweet> getUserTweetList(String userId) {
        List<Tweet> tweetList = Tweet.find.where()
                .eq("userId", userId)
                .orderBy("createdDate desc")
                .findList();

        return tweetList;
    }

    /**
     * 指定したユーザ情報.
     * @param userId ユーザID
     * @return ユーザ情報
     */
    public User userInfo(String userId) {
        User userInfo = User.find.where()
                .eq("userId", userId)
                .findUnique();

        return userInfo;
    }
}
