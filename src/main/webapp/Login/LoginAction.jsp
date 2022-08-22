<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="User.UserDAO" %>
<%@ page import="User.UserDTO" %>
<%@ page import="java.io.PrintWriter" %>

<%
    String UserID=null;
    String UserPassword=null;

    if (session.getAttribute("UserID")!=null){
        UserID=(String) session.getAttribute("UserID");
    }
    if(UserID!=null){
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('이미 로그인이 되어있습니다.')");
        script.println("location.href='homepage.jsp'");
        script.println("</script>");
    }
    UserDAO userDAO=new UserDAO();
    UserDTO userDTO=new UserDTO(UserID,UserPassword);

    int result = userDAO.login(userDTO);

    if(result==1){
        session.setAttribute("UserID",userDTO.getUserID());//로그인한 인원들에 한에서 세션을 부여함
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("location.href='homepage.jsp'");
        script.println("</script>");
    }
    else if (result==0) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('비밀번호가 틀립니다.')");
        script.println("history.back()");
        script.println("</script>");
    }
    else if (result==-1) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('존재하지 않는 아이디입니다.')");
        script.println("history.back()");
        script.println("</script>");
    }
    else if (result==-2) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('데이터베이스 오류가 발생했습니다.')");
        script.println("history.back()");
        script.println("</script>");
    }
%>