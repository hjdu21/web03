package bitcamp.pms.servlet;

import java.io.IOException;
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
    // => include() 하는 경우, 메인 서블릿의 contentType 설정을 사용한다.
    // => forward() 하는 경우, 메인 서블릿의 contentType 설정을 무시한다.
    response.setContentType("text/html;charset=UTF-8");
    
    // 5) 뷰 컴포넌트(BoardList.jsp)를 including 한다.
    RequestDispatcher rd = request.getRequestDispatcher("/board/BoardList.jsp");
    
    // BoardList.jsp를 실행하기 전에 ServletRequest 보관소에
    // 목록 조회 결과를 저장해둔다.
    request.setAttribute("list", list);
    
    // BoardList.jsp를 실행한다.
    // => 물론 including이기 때문에 실행한 후 다시 이 서블릿으로 리턴할 것이다.
    rd.include(request, response);
    
  }
}










