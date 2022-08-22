package User;

public class TestUserDao {
    public static void main(String[] args) {


        String UserID="joodong";
        String UserPassword="kkjjss103";

        UserDAO userDAO=new UserDAO();
        int result = userDAO.login(UserID,UserPassword);

        System.out.println(result);
    }
}
