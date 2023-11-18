public class Admin extends User{
    public Admin(String username, String password) {
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(Role.admin);
    }
}
