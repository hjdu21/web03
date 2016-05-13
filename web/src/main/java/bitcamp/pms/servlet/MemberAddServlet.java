package bitcamp.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.vo.Member;

@WebServlet("/member/add.do")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(
      HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head>");
    out.println("<title>회원관리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원관리-새 회원</h1>");
    out.println("<form action='add.do' method='post'>");
    out.println("이름: <input type='text' name='name'><br>");
    out.println("이메일: <input type='text' name='email'><br>");
    out.println("암호: <input type='password' name='password'><br>");
    out.println("전화: <input type='text' name='tel'><br>");
    out.println("<button>등록</button>");
    out.println("<button type='reset'>초기화</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
  
  
  @Override
  protected void doPost(
      HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {

    ServletContext servletContext = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext)servletContext.getAttribute("iocContainer");
    
    MemberDao memberDao = iocContainer.getBean(MemberDao.class);
    
    Member member = new Member();
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setTel(request.getParameter("tel"));
    
    memberDao.insert(member);
    
    response.sendRedirect("list.do");
  }
}










