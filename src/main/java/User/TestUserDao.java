package User;

public class TestUserDao {
    public static void main(String[] args) {

        String UserName="admin";
        String UserGender="남";
        String UserID="admin";
        String UserPassword="123456";
        String UserEmail="launcher37@naver.com";
        String UserPhoneNumber="010-7761-8482";

        UserDTO userDTO =new UserDTO(UserName,UserGender,UserID,UserPassword,UserEmail,UserPhoneNumber);
        UserDAO userDAO=new UserDAO();

        int insertCount= userDAO.addUser(userDTO);
    }
}
