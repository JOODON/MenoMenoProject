<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="BBS.BBSDTO" %>
<%@ page import="BBS.BBSDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%
        request.setCharacterEncoding("UTF-8");

        String bbsTitle=null;
        String bbsContent=null;
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
            if (request.getParameter("bbsTitle") != null) {
                bbsTitle=(String) request.getParameter("bbsTitle");
            }
            if (request.getParameter("bbsContent") != null) {
                bbsContent=(String) request.getParameter("bbsContent");
            }
            if(bbsTitle.equals("")|| bbsContent.equals("")){
                PrintWriter script=response.getWriter();
                script.println("<script>");
                script.println("alert('입력 안된 사항이 있습니다!')");
                script.println("location.href='history.back()'");
                script.println("</script>");
                script.close();
                return;
            }
            BBSDTO bbsdto=new BBSDTO(bbsContent,userID,bbsTitle);
            BBSDAO bbsdao=new BBSDAO();
            int insertCount= bbsdao.Wirte(bbsdto);

            if(insertCount==1){
                PrintWriter script=response.getWriter();
                script.println("<script>");
                script.println("alert('글쓰기에 성공하셨습니다')");
                script.println("location.href='http://localhost:8080/MenoMeno/ViewPage/View.jsp'");
                script.println("</script>");
                script.close();
                return;
            }
        }
%>
