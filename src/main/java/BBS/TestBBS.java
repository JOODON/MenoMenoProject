package BBS;

public class TestBBS {
    public static void main(String[] args) {
        String bbsTitle="작성 테스트";
        String userID="admin";
        String bbsContent="백엔드작성 테스트";


        BBSDTO bbsdto=new BBSDTO(bbsTitle,userID,bbsContent);
        BBSDAO bbsdao=new BBSDAO();
    }

}
