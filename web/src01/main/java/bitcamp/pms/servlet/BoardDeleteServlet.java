package bitcamp.pms.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import bitcamp.pms.dao.BoardDao;

@WebServlet("/board/delete.do")
public class BoardDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(
      HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {

    ServletContext servletContext = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext)servletContext.getAttribute("iocContainer");
    
    BoardDao boardDao = iocContainer.getBean(BoardDao.class);
    
    int no = Integer.parseInt(request.getParameter("no"));
    boardDao.delete(no);
    
    // redirect 적용
    response.sendRedirect("list.do");
    
    /*
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head>");
    out.println("<title>게시판</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시판-삭제 결과</h1>");
    out.println("<p>삭제 성공입니다.</p>");
    out.println("<a href='list.do'>목록</a>");
    out.println("</body>");
    out.println("</html>");
    */
  }
}










