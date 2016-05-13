package bitcamp.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
    RequestDispatcher rd = request.getRequestDispatcher("/board/BoardDetail.jsp");
    
    request.setAttribute("board", board);
    rd.include(request, response);
  }
}










