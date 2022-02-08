package com.tomcat.ch2;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Bruce Zhao
 * @date 2022/2/7 12:50
 */
public class SimplePrimitiveServlet implements Servlet {

  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("init");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    System.out.println("from simple service");
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

  @Override
  public void destroy() {
    System.out.println("destroy");
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }
}
