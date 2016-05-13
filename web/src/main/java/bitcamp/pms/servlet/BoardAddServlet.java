package bitcamp.pms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.vo.Board;

@WebServlet("/board/add.do")
public class BoardAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(
      HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {
    // 게시물 입력 폼 출력
    response.setContentType("text/html;charset=UTF-8");
    RequestDispatcher rd = request.getRequestDispatcher("/board/BoardAdd.jsp");
    rd.include(request, response);
  }
  
  
  @Override
  protected void doPost(
      HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {

    ServletContext servletContext = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext)servletContext.getAttribute("iocContainer");
    
    BoardDao boardDao = iocContainer.getBean(BoardDao.class);
    
    Board board = new Board();
    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));
    
    boardDao.insert(board);
    
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
    out.println("<h1>게시판-등록 결과</h1>");
    out.println("<p>등록 성공입니다.</p>");
    out.println("<a href='list.do'>목록</a>");
    out.println("</body>");
    out.println("</html>");
    */
  }
}










