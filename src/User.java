import java.util.ArrayList;

public abstract class User {
    public static User activeUser;
    private String name;
    private String username;
    private String password;
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean validateLogin(String username, String password) {
        ArrayList<User> users = FileHandler.readUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username) && users.get(i).password.equals(password)) {
                activeUser = users.get(i);
                return true;
            }
        }
        return false;
    }
    public static boolean validateNewUser(String username, String name) {
        ArrayList<User> users = FileHandler.readUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equalsIgnoreCase(username) || users.get(i).name.equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return name + ";" + username + ";" + password + ";" + role;
    }
}
