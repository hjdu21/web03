package bitcamp.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.vo.Board;

@WebServlet("/board/list.do")
public class BoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(
      HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {
    // 1) 스프링 IoC 컨테이너를 꺼낸다.
    ServletContext servletContext = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext)servletContext.getAttribute("iocContainer");
    
    // 2) IoC 컨테이너에서 BoardDao 객체를 꺼낸다.
    BoardDao boardDao = iocContainer.getBean(BoardDao.class);
    
    // 3) BoardDao 객체를 이용하여 게시물 목록을 가져온다.
    List<Board> list = boardDao.selectList();
    
    // 4) 게시물 목록을 클라이언트로 출력한다.
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head>");
    out.println("<title>게시판</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시판-목록</h1>");
    out.println("<p><a href='add.do'>새 글</a></p>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("<tr>");
    out.println("  <th>번호</th>");
    out.println("  <th>제목</th>");
    out.println("  <th>등록일</th>");
    out.println("  <th>조회수</th>");
    out.println("</tr>");
    out.println("</thead>");
    out.println("<tbody>");
    for (Board board : list) {
      out.printf("<tr><td>%d</td>", board.getNo()); 
      out.printf("<td><a href='detail.do?no=%d'>%s</a></td>", 
          board.getNo(), board.getTitle());
      out.printf("<td>%s</td>", board.getCreatedDate().toString());
      out.printf("<td>%d</td></tr>\n",board.getViews());
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }
}










