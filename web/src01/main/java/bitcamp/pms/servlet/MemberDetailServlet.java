package bitcamp.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.vo.Member;

@WebServlet("/member/detail.do")
public class MemberDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(
      HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {
    
    ServletContext servletContext = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext)servletContext.getAttribute("iocContainer");
    MemberDao memberDao = iocContainer.getBean(MemberDao.class);
    
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", Integer.parseInt(request.getParameter("no")));
    
    Member member = memberDao.selectOne(paramMap);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head>");
    out.println("<title>회원관리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원관리-상세정보</h1>");
    out.println("<form action='update.do' method='post'>");
    out.printf("번호: <input type='text' name='no' value='%d' readonly><br>\n",
        member.getNo());
    out.printf("이름: <input type='text' name='name' value='%s'><br>\n",
        member.getName());
    out.printf("이메일: <input type='text' name='email' value='%s'><br>\n",
        member.getEmail());
    out.println("암호: <input type='password' name='password'><br>");
    out.printf("전화: <input type='text' name='tel' value='%s'><br>\n",
        member.getTel());
    out.println("<button>변경</button>");
    out.printf("<a href='delete.do?no=%d'>삭제</a>", member.getNo());
    out.println("<button type='reset'>초기화</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}










