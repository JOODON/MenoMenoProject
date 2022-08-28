package BBS;

import java.util.ArrayList;

public class TestBBS {
    public static void main(String[] args) {

        BBSDAO bbsdao = new BBSDAO();
        BBSDTO bbsdto = new BBSDTO();
        ArrayList<BBSDTO> list = bbsdao.getList(0);
        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i).getBbsID());
        }
    }
}
