package models;

import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

import java.util.List;

@Entity
public class User extends Model {

    /* UserId */
    @Id
    public String userId;

    /* パスワード */
    @Constraints.Required
    public String password;

    /* フォロー */
    public String follow;


    /* ユーザ情報の取得 */
    public static Finder<Long, User> find = new Finder<>(User.class);

    public void setFollow(String follow) {
        this.follow = follow;
    }
}
