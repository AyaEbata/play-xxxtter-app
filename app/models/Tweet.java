package models;

import java.util.Date;
import javax.persistence.*;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Tweet extends Model {

    /* ID */
    @Id
    public Long id;

    /* ツイート */
    @Constraints.Required
    public String tweet;

    /* ツイートした時間 */
    @CreatedTimestamp
    @Formats.DateTime(pattern = "yyyy/MM/dd HH:mm")
    public Date createdDate;

    @Constraints.Required
    public String userId;


    /* ツイートの取得 */
    public static Finder<Long, Tweet> find = new Finder<>(Tweet.class);
}
