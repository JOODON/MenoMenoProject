package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserDAO {
    private static String dburl="jdbc:mysql://localhost:3307/MenoMenoUser";
    private static String dbID="root";
    private static String dbpassword="kkjjss103@";

    public int addUser(UserDTO userDTO){
        int insertCount=0;
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(dburl,dbID,dbpassword);
            String SQL="INSERT INTO MenoMenoUser (UserName,UserGender,UserID,UserPassword,UserEmail,UserPhoneNumber) VALUES (?,?,?,?,?,?)";
            ps=conn.prepareStatement(SQL);

            ps.setString(1,userDTO.getUserName());
            ps.setString(2, userDTO.getUserGender());
            ps.setString(3,userDTO.getUserPassword());
            ps.setString(4, userDTO.getUserPassword());
            ps.setString(5,userDTO.getUserEmail());
            ps.setString(6,userDTO.getUserPhoneNumber());

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(ps != null && conn !=null){
                try {
                    ps.close();
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        return insertCount;
    }
}
