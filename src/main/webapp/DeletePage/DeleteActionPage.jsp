<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="BBS.BBSDTO" %>
<%@ page import="BBS.BBSDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%
    request.setCharacterEncoding("UTF-8");

    int bbsID=0;
    String userID=null;

    if (session.getAttribute("UserID")!=null){
        userID=(String) session.getAttribute("UserID");
    }
    if(userID == null){
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('로그인을 하세요')");
        script.println("location.href='http://localhost:8080/MenoMeno/Login/login.jsp'");
        script.println("</script>");
    }
    else {

        BBSDTO bbsdto=new BBSDTO(bbsID);
        BBSDAO bbsdao=new BBSDAO();
        int insertCount= bbsdao.Wirte(bbsdto);

        if(insertCount==1){
            PrintWriter script=response.getWriter();
            script.println("<script>");
            script.println("alert('글삭제에 성공하셨습니다')");
            script.println("location.href='http://localhost:8080/MenoMeno'");
            script.println("</script>");
            script.close();
            return;
        }
    }
%>
