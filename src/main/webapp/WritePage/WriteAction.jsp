<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="BBS.BBSDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="BBS" class="BBS.BBSDTO" scope="page"/>
<jsp:setProperty name="BBS" property="bbsTitle"/>
<jsp:setProperty name="BBS" property="bbsContent"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String userID=null;

    if (session.getAttribute("userID")!=null){
        userID=(String) session.getAttribute("userID");
    }
    if(userID == null){
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('로그인을 하세요')");
        script.println("location.href='http://localhost:8080/MenoMeno/Login/login.jsp'");
        script.println("</script>");
    }
    else {
        if (BBS.getBbsTitle() == null || BBS.getBbsContent() == null) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('입력하지 않으신 부분이 있습니다')");
            script.println("history.back()");
            script.println("</script>");
        } else {
            BBSDAO bbsdao = new BBSDAO();
            int result = bbsdao.Write(BBS.getBbsTitle(), userID, BBS.getBbsContent());
            if (result == -1) {
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('글씨기에 실패하셨습니다.')");
                script.println("history.back()");
                script.println("</script>");
            } else {
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("location.href='http://localhost:8080/MenoMeno/ViewPage/View.jsp'");
                script.println("</script>");
            }
        }
    }
%>
</body>
</html>
