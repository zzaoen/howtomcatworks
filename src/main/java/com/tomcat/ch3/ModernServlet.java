package com.tomcat.ch3;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import static com.tomcat.ch2.Constants.WEB_ROOT;

/**
 * @author Bruce Zhao
 * @date 2022/2/9 20:23
 */
public class ModernServlet extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.print("ModernServlet init");
    super.init(config);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    // out.print("HTTP/1.1 200 OK");

    String errorMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
    out.print(errorMessage);

    out.print("<html>");
    out.print("<head>");
    out.print("<title>Modern Servlet</title>");
    out.print("</head>");
    out.print("<body>");

    out.print("<h2>Headers</h2");
    Enumeration headers = request.getHeaderNames();
    if (headers != null) {
      while (headers.hasMoreElements()) {
        String header = (String) headers.nextElement();
        out.print("<br>" + header + " : " + request.getHeader(header));
      }
    }

    out.print("<br><h2>Method</h2");
    out.print("<br>" + request.getMethod());

    out.print("<br><h2>Parameters</h2");
    Enumeration parameters = request.getParameterNames();
    if (parameters != null) {
      while (parameters.hasMoreElements()) {
        String parameter = (String) parameters.nextElement();
        out.print("<br>" + parameter + " : " + request.getParameter(parameter));
      }
    }

    out.print("<br><h2>Query String</h2");
    out.print("<br>" + request.getQueryString());

    out.print("<br><h2>Request URI</h2");
    out.print("<br>" + request.getRequestURI());

    out.print("</body>");
    out.print("</html>");

    out.println();
  }
}
