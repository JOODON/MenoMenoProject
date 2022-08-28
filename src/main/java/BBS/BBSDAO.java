//package BBS;
//
//import User.UserDTO;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//public class BBSDAO {
//
//    private static String dburl="jdbc:mysql://localhost:3307/MenoMenoBBS";
//
//    private static String dbID="root";
//
//    private static String dbpassword="kkjjss103@";
//
//    public BBSDAO() {
//        Connection conn=null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//        public int getNext(){
//            Connection conn=null;
//            PreparedStatement ps=null;
//            ResultSet rs=null;
//
//            String SQL="SELECT bbsID FROM BBS ORDER BY bbsID DESC ";
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                conn = DriverManager.getConnection(dburl,dbID,dbpassword);
//                PreparedStatement pstmt =conn.prepareStatement(SQL);
//                rs=pstmt.executeQuery();
//                if(rs.next()){
//                    return rs.getInt(1)+1;
//                }
//                return 1;
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return -1;//데이터 베이스 오류
//        }
//
//        public String getDate(){
//            String SQL="SELECT NOW()";
//
//            Connection conn=null;
//
//            PreparedStatement ps=null;
//
//            ResultSet rs=null;
//
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                conn = DriverManager.getConnection(dburl,dbID,dbpassword);
//                PreparedStatement pstmt =conn.prepareStatement(SQL);
//                rs=pstmt.executeQuery();
//                if(rs.next()){
//                    return rs.getString(1);
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "";//데이터 베이스 오류
//        }
//        public int Wirte(String bbsTitle,String userID,String bbsContent){
//            int insertCount=0;
//            Connection conn=null;
//            PreparedStatement ps=null;
//            try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
//            String SQL="INSERT INTO BBS (bbsID,bbsTitle,userID,bbsDate,bbsContent,bbsAvailable) VALUES (?,?,?,?,?,?)";
//            ps=conn.prepareStatement(SQL);
//
//            ps.setInt(1,getNext());
//            ps.setString(2,bbsTitle);
//            ps.setString(3,userID);
//            ps.setString(4,getDate());
//            ps.setString(5,bbsContent);
//            ps.setInt(6,1);
//
//            insertCount=ps.executeUpdate();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            if(ps != null && conn != null){
//                try {
//                    ps.close();
//                    conn.close();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        return insertCount;
//    }
//    public ArrayList<BBSDTO> getList(int pageNumber){
//        String SQL="SELECT * FROM BBS WHERE bbsID <? AND bbsAvailable =1 ORDER  BY bbsID DESC LIMIT 10";
//        Connection conn=null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//
//        ArrayList<BBSDTO> list=new ArrayList<>();
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
//            PreparedStatement pstmt =conn.prepareStatement(SQL);
//            pstmt.setInt(1,getNext() - (pageNumber - 1 ) * 10);
//            rs=pstmt.executeQuery();
//            while (rs.next()){
//                BBSDTO bbsdto=new BBSDTO();
//                bbsdto.setBbsID(rs.getInt(1));
//                bbsdto.setBbsTitle(rs.getString(2));
//                bbsdto.setUserID(rs.getString(3));
//                bbsdto.setBbsDate(rs.getString(4));
//                bbsdto.setBbsContent(rs.getString(5));
//                bbsdto.setBbsAvailable(rs.getInt(6));
//                list.add(bbsdto);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return list;//데이터 베이스 오류
//    }
//    public boolean nextPage (int pageNumber){
//        int insertCount=0;
//        Connection conn=null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//        String SQL="SELECT * FROM BBS WHERE bbsID <? AND bbsAvailable =1";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
//            ps=conn.prepareStatement(SQL);
//            ps.setInt(1,getNext() - (pageNumber - 1 ) * 10);
//            rs=ps.executeQuery();
//            if (rs.next()){
//                return true;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;//데이터 베이스 오류
//    }
//    public BBSDTO getBBS(int bbsID){
//        String SQL="SELECT * FROM BBS WHERE bbsID =? ";
//
//        Connection conn=null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
//            PreparedStatement pstmt =conn.prepareStatement(SQL);
//            pstmt.setInt(1,bbsID);
//            rs=pstmt.executeQuery();
//            if (rs.next()){
//                BBSDTO bbsdto =new BBSDTO();
//                bbsdto.setBbsID(rs.getInt(1));
//                bbsdto.setBbsTitle(rs.getString(2));
//                bbsdto.setUserID(rs.getString(3));
//                bbsdto.setBbsDate(rs.getString(4));
//                bbsdto.setBbsContent(rs.getString(5));
//                bbsdto.setBbsAvailable(rs.getInt(6));
//                return bbsdto;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;//데이터 베이스 오류
//    }
//    public int update(BBSDTO bbsdto){
//        int insertCount=0;
//        Connection conn=null; //접속하는 부분 설정
//        PreparedStatement ps=null;//호출시켜주는 부분 설정
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn=DriverManager.getConnection(dburl,dbID,dbpassword);
//            String sql="UPDATE bbs SET bbsTitle =? ,bbsContent =? WHERE bbsID=?";
//            ps=conn.prepareStatement(sql);
//
//            ps.setString(1, bbsdto.getBbsTitle());
//            ps.setString(2, bbsdto.getBbsContent());
//            ps.setInt(3,bbsdto.getBbsID());
//
//            insertCount = ps.executeUpdate();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if(ps != null && conn != null){
//                try {
//                    ps.close();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        return insertCount;
//    }
//    public int delete(int bbsID) {
//        int insertCount=0;
//        Connection conn=null; //접속하는 부분 설정
//        PreparedStatement ps=null;//호출시켜주는 부분 설정
//        String SQL="UPDATE BBS SET bbsAvailable=0 WHERE bbsID=?";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn=DriverManager.getConnection(dburl,dbID,dbpassword);
//            PreparedStatement pstmt =conn.prepareStatement(SQL);
//            pstmt.setInt(1,bbsID);
//            return pstmt.executeUpdate();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return -1;//데이터 베이스 오류
//    }
//}
package BBS;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BBSDAO {



    private static String dburl="jdbc:mysql://localhost:3307/MenoMenobbs";
    private static String dbID="root";
    private static String dbpassword="kkjjss103@";
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    public BBSDAO(){

        try {
            conn = DriverManager.getConnection(dburl,dbID,dbpassword);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getDate(){
        String SQL="SELECT NOW()";
        try {
            PreparedStatement ps =conn.prepareStatement(SQL);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";//데이터 베이스 오류
    }
    public int getNext(){
        String SQL="SELECT bbsID FROM BBS ORDER BY bbsID DESC ";
        try {
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
    public int Write(String bbsTitle,String userID,String bbsContent){
        String SQL="INSERT INTO BBS VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt =conn.prepareStatement(SQL);
            pstmt.setInt(1,getNext());
            pstmt.setString(2,bbsTitle);
            pstmt.setString(3,userID);
            pstmt.setString(4,getDate());
            pstmt.setString(5,bbsContent);
            pstmt.setInt(6,1);
            return pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;//데이터 베이스 오류
    }
    public ArrayList<BBSDTO> getList(int pageNumber){
        String SQL="SELECT * FROM BBS WHERE bbsID <? AND bbsAvailable =1 ORDER  BY bbsID DESC LIMIT 10";
        ArrayList<BBSDTO> list=new ArrayList<>();
        try {
            PreparedStatement ps =conn.prepareStatement(SQL);
            ps.setInt(1,getNext() - (pageNumber - 1 ) * 10);
            rs=ps.executeQuery();
            while (rs.next()){
                BBSDTO bbs =new BBSDTO();
                bbs.setBbsID(rs.getInt(1));
                bbs.setBbsTitle(rs.getString(2));
                bbs.setUserID(rs.getString(3));
                bbs.setBbsDate(rs.getString(4));
                bbs.setBbsContent(rs.getString(5));
                bbs.setBbsAvailable(rs.getInt(6));
                list.add(bbs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;//데이터 베이스 오류
    }
    public boolean nextPage (int pageNumber){
        String SQL="SELECT * FROM BBS WHERE bbsID <? AND bbsAvailable = 1";
        try {
            PreparedStatement pstmt =conn.prepareStatement(SQL);
            pstmt.setInt(1,getNext() - (pageNumber - 1 ) * 10);
            rs=pstmt.executeQuery();
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
        try {
            PreparedStatement pstmt =conn.prepareStatement(SQL);
            pstmt.setInt(1,bbsID);
            rs=pstmt.executeQuery();
            if (rs.next()){
                BBSDTO bbs =new BBSDTO();
                bbs.setBbsID(rs.getInt(1));
                bbs.setBbsTitle(rs.getString(2));
                bbs.setUserID(rs.getString(3));
                bbs.setBbsDate(rs.getString(4));
                bbs.setBbsContent(rs.getString(5));
                bbs.setBbsAvailable(rs.getInt(6));
                return bbs;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;//데이터 베이스 오류
    }
    public int update(int bbsID,String bbsTitle,String bbsContent){
        String SQL="UPDATE BBS SET bbsTitle =? ,bbsContent =? WHERE bbsID=?";
        try {
            PreparedStatement pstmt =conn.prepareStatement(SQL);
            pstmt.setString(1,bbsTitle);
            pstmt.setString(2,bbsContent);
            pstmt.setInt(3,bbsID);
            return pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;//데이터 베이스 오류
    }
    public int delete(int bbsID) {
        String SQL="UPDATE BBS SET bbsAvailable=0 WHERE bbsID=?";
        try {
            PreparedStatement pstmt =conn.prepareStatement(SQL);
            pstmt.setInt(1,bbsID);
            return pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;//데이터 베이스 오류
    }
}
