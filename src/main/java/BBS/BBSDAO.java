package BBS;

import User.UserDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    public ArrayList<BBSDTO> getList(int pageNumber){
        int insertCount=0;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<BBSDTO> list=new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
            String SQL="SELECT *FROM BBS WHERE bbsID <? AND bbsAvailable =1 ORDER  BY bbsID DESC LIMIT 10";
            ps=conn.prepareStatement(SQL);
            ps.setInt(1,getNext() -(pageNumber - 1 )* 10);
            rs=ps.executeQuery();
            while (rs.next()){
                BBSDTO bbsdto=new BBSDTO();
                bbsdto.setBbsID(rs.getInt(1));
                bbsdto.setBbsTitle(rs.getString(2));
                bbsdto.setUserID(rs.getString(3));
                bbsdto.setBbsDate(rs.getString(4));
                bbsdto.setBbsContent(rs.getString(5));
                bbsdto.setBbsAvailable(rs.getInt(6));
                list.add(bbsdto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    return list;
    }
    public boolean nextPage (int pageNumber){
        int insertCount=0;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String SQL="SELECT * FROM BBS WHERE bbsID <? AND bbsAvailable =1";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
            ps=conn.prepareStatement(SQL);
            ps.setInt(1,getNext() - (pageNumber - 1 ) * 10);
            rs=ps.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;//데이터 베이스 오류
    }
    public BBSDTO getBBS(int bbsID){
        String SQL="SELECT * FROM BBS WHERE bbsID =? ";
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
            PreparedStatement pstmt =conn.prepareStatement(SQL);
            pstmt.setInt(1,bbsID);
            rs=pstmt.executeQuery();
            if (rs.next()){
                BBSDTO bbsdto =new BBSDTO();
                bbsdto.setBbsID(rs.getInt(1));
                bbsdto.setBbsTitle(rs.getString(2));
                bbsdto.setUserID(rs.getString(3));
                bbsdto.setBbsDate(rs.getString(4));
                bbsdto.setBbsContent(rs.getString(5));
                bbsdto.setBbsAvailable(rs.getInt(6));
                return bbsdto;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;//데이터 베이스 오류
    }
}
