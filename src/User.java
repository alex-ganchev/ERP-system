import java.util.ArrayList;

public abstract class User {
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

    public static User validate(String username, String password) {
        ArrayList<User> users = FileHandler.readUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username) && users.get(i).password.equals(password)) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return username + ";" + password + ";" + role + ";";
    }
}
