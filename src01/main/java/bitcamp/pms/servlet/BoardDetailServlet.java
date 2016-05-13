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

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.vo.Board;

@WebServlet("/board/detail.do")
public class BoardDetailServlet extends HttpServlet {
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
    Board board = boardDao.selectOne(no);
    
    // 게시물 상세 폼 출력
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head>");
    out.println("<title>게시판</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시판-상세정보</h1>");
    out.println("<form action='update.do' method='post'>");
    out.printf("<input type='hidden' name='no' value='%d'>\n", 
                board.getNo());
    out.printf("제목: <input type='text' name='title' value='%s'><br>\n", 
                board.getTitle());
    out.printf("내용: <textarea name='content' rows='5' cols='60'>%s</textarea><br>",
                board.getContent());
    out.println("<button>변경</button>");
    out.printf("<a href='delete.do?no=%d'>삭제</a>", board.getNo());
    out.println("<button type='reset'>초기화</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}










