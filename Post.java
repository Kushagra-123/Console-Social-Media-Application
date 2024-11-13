import java.util.Date;

public class Post {
    String postId;
    String userId;
    String content;
    Date timestamp;
    int likes;
    int dislikes;

    Post(String postId, String userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.timestamp = new Date();
        this.likes = 0;
        this.dislikes = 0;
    }
}