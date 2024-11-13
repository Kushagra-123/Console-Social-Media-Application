import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    String userId;
    String username;
    Set<String> following;
    List<Post> posts;

    User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.following = new HashSet<>();
        this.posts = new ArrayList<>();
    }
}

