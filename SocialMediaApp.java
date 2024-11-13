import java.util.*;

public class SocialMediaApp {
    public Map<String, User> users;
    private Map<String, Post> posts;
    private int postIdCounter;

    public SocialMediaApp() {
        users = new HashMap<>();
        posts = new HashMap<>();
        postIdCounter = 1;
    }

    public String registerUser(String userId, String userName) {
        if (users.containsKey(userId)) {
            return "User ID already taken!";
        }
        users.put(userId, new User(userId, userName));
        return userName + " Registered!!";
    }

    public String uploadPost(String userId, String content) {
        if (!users.containsKey(userId)) {
            return "User does not exist!";
        }
        String postId = String.format("%03d", postIdCounter++);
        Post post = new Post(postId, userId, content);
        users.get(userId).posts.add(post);
        posts.put(postId, post);
        return "Upload Successful with post id: " + postId;
    }

    public String interactWithUser(InteractionType type, String userId1, String userId2) {
        User user1 = users.get(userId1);
        User user2 = users.get(userId2);
        if (user1 == null || user2 == null) return "User not found!";

        if (type == InteractionType.FOLLOW) {
            user1.following.add(userId2);
            return "Followed " + user2.username + "!!";
        } else if (type == InteractionType.UNFOLLOW) {
            user1.following.remove(userId2);
            return "Unfollowed " + user2.username + "!!";
        }
        return "Invalid interaction type!";
    }

    public String showFeed(String userId) {
        User user = users.get(userId);
        if (user == null) return "User not found!";

        List<Post> feed = new ArrayList<>();

        for (User u : users.values()) {
            feed.addAll(u.posts);
        }

        feed.sort((p1, p2) -> {
            boolean p1Followed = user.following.contains(p1.userId);
            boolean p2Followed = user.following.contains(p2.userId);
            if (p1Followed != p2Followed) return p1Followed ? -1 : 1;
            return p2.timestamp.compareTo(p1.timestamp);
        });

        StringBuilder output = new StringBuilder();
        for (Post post : feed) {
            output.append("UserName - ").append(users.get(post.userId).username).append("\n")
                    .append("Post - ").append(post.content).append("\n")
                    .append("Post time - ").append(getRelativeTime(post.timestamp)).append("\n\n");
        }
        return output.toString();
    }

    private String getRelativeTime(Date postTime) {
        long diffMillis = new Date().getTime() - postTime.getTime();
        long minutes = diffMillis / 60000;
        long hours = minutes / 60;
        long days = hours / 24;
        if (minutes < 60) return minutes + "m ago";
        if (hours < 24) return hours + "h ago";
        return days + "d ago";
    }

    public String interactWithPost(InteractionType type, String userId, String postId) {
        Post post = posts.get(postId);
        if (post == null) return "Post not found!";

        if (type == InteractionType.LIKE) {
            post.likes++;
            return "Post Liked!!";
        } else if (type == InteractionType.DISLIKE) {
            post.dislikes++;
            return "Post Disliked!!";
        }
        return "Invalid interaction type!";
    }
}

