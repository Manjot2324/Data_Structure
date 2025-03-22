import java.util.ArrayList;

class User {
    int userID;
    String name;
    int age;
    ArrayList<Integer> friendIDs;
    User next;

    public User(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.friendIDs = new ArrayList<>();
        this.next = null;
    }

    public void addFriend(int friendID) {
        if (!friendIDs.contains(friendID)) {
            friendIDs.add(friendID);
        }
    }

    public void removeFriend(int friendID) {
        friendIDs.remove(Integer.valueOf(friendID));
    }
}

class SocialMedia {
    User head;

    public SocialMedia() {
        head = null;
    }

    public void addUser(int userID, String name, int age) {
        User newUser = new User(userID, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newUser;
        }
    }

    public User searchUserByID(int userID) {
        User current = head;
        while (current != null) {
            if (current.userID == userID) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public User searchUserByName(String name) {
        User current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void addFriendship(int userID1, int userID2) {
        User user1 = searchUserByID(userID1);
        User user2 = searchUserByID(userID2);
        if (user1 != null && user2 != null) {
            user1.addFriend(userID2);
            user2.addFriend(userID1);
        }
    }

    public void removeFriendship(int userID1, int userID2) {
        User user1 = searchUserByID(userID1);
        User user2 = searchUserByID(userID2);
        if (user1 != null && user2 != null) {
            user1.removeFriend(userID2);
            user2.removeFriend(userID1);
        }
    }

    public ArrayList<Integer> findMutualFriends(int userID1, int userID2) {
        User user1 = searchUserByID(userID1);
        User user2 = searchUserByID(userID2);
        ArrayList<Integer> mutualFriends = new ArrayList<>();
        if (user1 != null && user2 != null) {
            for (int friend1 : user1.friendIDs) {
                for (int friend2 : user2.friendIDs) {
                    if (friend1 == friend2) {
                        mutualFriends.add(friend1);
                    }
                }
            }
        }
        return mutualFriends;
    }

    public void displayFriends(int userID) {
        User user = searchUserByID(userID);
        if (user != null) {
            System.out.println("Friends of " + user.name + ": ");
            if (user.friendIDs.isEmpty()) {
                System.out.println("No friends.");
            } else {
                for (int friendID : user.friendIDs) {
                    System.out.println("User ID: " + friendID);
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void countFriends(int userID) {
        User user = searchUserByID(userID);
        if (user != null) {
            System.out.println(user.name + " has " + user.friendIDs.size() + " friends.");
        } else {
            System.out.println("User not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SocialMedia socialMedia = new SocialMedia();

        socialMedia.addUser(1, "Alice", 25);
        socialMedia.addUser(2, "Bob", 28);
        socialMedia.addUser(3, "Charlie", 22);
        socialMedia.addUser(4, "David", 30);

        socialMedia.addFriendship(1, 2);
        socialMedia.addFriendship(1, 3);
        socialMedia.addFriendship(2, 3);
        socialMedia.addFriendship(3, 4);

        System.out.println("Display Friends of Alice:");
        socialMedia.displayFriends(1);

        System.out.println("\nDisplay Friends of Bob:");
        socialMedia.displayFriends(2);

        System.out.println("\nCount Friends of Charlie:");
        socialMedia.countFriends(3);

        System.out.println("\nMutual Friends between Alice and Bob:");
        ArrayList<Integer> mutualFriends = socialMedia.findMutualFriends(1, 2);
        for (int friend : mutualFriends) {
            System.out.println(friend);
        }

        System.out.println("\nRemoving Friendship between Alice and Bob...");
        socialMedia.removeFriendship(1, 2);

        System.out.println("\nDisplay Friends of Alice after removal:");
        socialMedia.displayFriends(1);

        System.out.println("\nSearch for User by Name (Charlie):");
        User charlie = socialMedia.searchUserByName("Charlie");
        if (charlie != null) {
            System.out.println("Found User: " + charlie.name + ", Age: " + charlie.age);
        }

        System.out.println("\nSearch for User by ID (4):");
        User user = socialMedia.searchUserByID(4);
        if (user != null) {
            System.out.println("Found User: " + user.name + ", Age: " + user.age);
        }
    }
}
n