package domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/29/14
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
@NamedQueries({
        @NamedQuery(name = "findUserByUsernameAndPassword", query = "from User u where u.username = :username and u.password = :password")
})
@Entity
@Table (name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column (name = "user_id")
    private long userId;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> questions;

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (userId ^ (userId >>> 32));
    }
}
