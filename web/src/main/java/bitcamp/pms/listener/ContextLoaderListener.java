package bitcamp.pms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ContextLoaderListener implements ServletContextListener {
  
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 스프링 IoC 컨테이너 준비
    // 1) 스프링 설정 파일의 절대 경로 알아내기
    ServletContext servletContext = sce.getServletContext();
    String configPath = servletContext.getInitParameter("contextConfigLocation");
    
    // 2) 스프링 설정 파일의 경로를 가지고 IoC 컨테이너 객체를 준비한다.
    ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
        servletContext.getRealPath(configPath));
    
    // 3) 모든 서블릿이 스프링 IoC 컨테이너를 사용할 수 있도록 바구니에 저장한다.
    servletContext.setAttribute("iocContainer", applicationContext);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {}

}





