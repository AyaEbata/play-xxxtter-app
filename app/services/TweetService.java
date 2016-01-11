package services;

import models.Tweet;
import java.util.List;

public class TweetService {

    /**
     * 日付が新しい順にツイートリストを並び変える.
     * @return 並び替えたツイートリスト
     */
    public List<Tweet> getTweetList() {
        List<Tweet> tweetList = Tweet.find.where()
                .orderBy("createdDate desc")
                .findList();

        return tweetList;
    }
}
