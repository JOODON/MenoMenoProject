package BBS;

import User.UserDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BBSDAO {

    private static String dburl="jdbc:mysql://localhost:3307/MenoMenoBBS";

    private static String dbID="root";

    private static String dbpassword="kkjjss103@";

        public int getNext(){
            Connection conn=null;
            PreparedStatement ps=null;
            ResultSet rs=null;

            String SQL="SELECT bbsID FROM BBS ORDER BY bbsID DESC ";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(dburl,dbID,dbpassword);
                PreparedStatement pstmt =conn.prepareStatement(SQL);
                rs=pstmt.executeQuery();
                if(rs.next()){
                    return rs.getInt(1)+1;
                }
                return 1;
            }catch (Exception e){
                e.printStackTrace();
            }
            return -1;//데이터 베이스 오류
        }

        public String getDate(){
            String SQL="SELECT NOW()";

            Connection conn=null;

            PreparedStatement ps=null;

            ResultSet rs=null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(dburl,dbID,dbpassword);
                PreparedStatement pstmt =conn.prepareStatement(SQL);
                rs=pstmt.executeQuery();
                if(rs.next()){
                    return rs.getString(1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return "";//데이터 베이스 오류
        }
        public int Wirte(BBSDTO bbsdto){
            int insertCount=0;
            Connection conn=null;
            PreparedStatement ps=null;
            try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
            String SQL="INSERT INTO BBS (bbsID,bbsTitle,userID,bbsDate,bbsContent,bbsAvailable) VALUES (?,?,?,?,?,?)";
            ps=conn.prepareStatement(SQL);

            ps.setInt(1,getNext());
            ps.setString(2,bbsdto.getBbsTitle());
            ps.setString(3,bbsdto.getUserID());
            ps.setString(4,getDate());
            ps.setString(5, bbsdto.getBbsContent());
            ps.setInt(6,1);

            insertCount=ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(ps != null && conn != null){
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
