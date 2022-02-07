import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Bruce Zhao
 * @date 2022/2/7 12:50
 * @implNote javac -classpath
 *     /Users/zhenzhao/.m2/repository/javax/servlet/javax.servlet-api/4.0.1/javax.servlet-api-4.0.1.jar
 *     PrimitiveServlet.java
 */
public class PrimitiveServlet implements Servlet {

  public void init(ServletConfig config) throws ServletException {
    System.out.println("init");
  }

  public void service(ServletRequest request, ServletResponse response)
          throws ServletException, IOException {
    System.out.println("from service");
    PrintWriter out = response.getWriter();
    String errorMessage =
            "HTTP/1.1 200 File Not Found\r\n"
                    + "Content-Type: text/html\r\n"
                    + "Content-Length: 23\r\n"
                    + "\r\n"
                    + "<h1>File AAA Found</h1>";
    out.println(errorMessage);
    out.print("Violets are blue.");
  }

  public void destroy() {
    System.out.println("destroy");
  }

  public String getServletInfo() {
    return null;
  }
  public ServletConfig getServletConfig() {
    return null;
  }

}
