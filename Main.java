import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SocialMediaApp app = new SocialMediaApp();
        String filePath = "users.txt"; // Path to the file with user details

        // Read and register users from the file
        readUsersFromFile(filePath, app);

        // Display registered users
        System.out.println("Registered users:");
        app.users.forEach((id, user) -> System.out.println("UserID: " + id + ", Username: " + user.username));
    }

    public static void readUsersFromFile(String filePath, SocialMediaApp app) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(" ");
                if (userDetails.length == 2) {
                    String userId = userDetails[0];
                    String username = userDetails[1];
                    System.out.println(app.registerUser(userId, username));
                } else {
                    System.out.println("Invalid user detail format: " + line);
                }
            }
        } catch (IOException e) {
            //System.out.println("Error reading file: " + e.getMessage());

            System.out.println(app.registerUser("1", "Kushagra"));
            System.out.println(app.registerUser("2", "Nishant"));

            System.out.println(app.uploadPost("1", "This is my first post. My name is Kushagra."));
            System.out.println(app.uploadPost("1", "I like to play cricket"));

            System.out.println(app.interactWithUser(InteractionType.FOLLOW, "2", "1"));

            System.out.println(app.showFeed("2"));

            System.out.println(app.interactWithPost(InteractionType.LIKE, "2", "001"));
            System.out.println(app.showFeed("2"));
        }
    }
}
