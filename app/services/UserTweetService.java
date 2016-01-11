package services;

import models.Tweet;
import models.User;

import java.util.List;

public class UserTweetService {

    /**
     * ユーザで絞ったツイート
     * @param user userID
     * @return ツイートリスト
     */
    public List<Tweet> getUserTweetList(String userId) {
        List<Tweet> tweetList = Tweet.find.where()
                .eq("userId", userId)
                .orderBy("createdDate desc")
                .findList();

        return tweetList;
    }
}
