package User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {
    private static String dburl="jdbc:mysql://localhost:3307/MenoMenoUser";
    private static String dbID="root";
    private static String dbpassword="kkjjss103@";

    public int addUser(UserDTO userDTO){
        int insertCount=0;
        Connection conn=null;
        PreparedStatement ps=null;
        
    }
}
