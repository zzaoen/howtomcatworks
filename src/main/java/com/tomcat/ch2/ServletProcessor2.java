package com.tomcat.ch2;

import com.tomcat.ch3.SimplePrimitiveServlet;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author Bruce Zhao
 * @date 2022/2/7 12:58
 */
public class ServletProcessor2 {
  public void process(Request request, Response response) {
    String uri = request.getUri();
    String servletName = uri.substring(uri.lastIndexOf("/") + 1);
    SimplePrimitiveServlet servlet = new SimplePrimitiveServlet();
    RequestFacade requestFacade = new RequestFacade(request);
    ResponseFacade responseFacade = new ResponseFacade(response);
    try {
      servlet.service(requestFacade, responseFacade);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }

    /*
    URLClassLoader loader = null;
    try {
      URL[] urls = new URL[1];
      URLStreamHandler streamHandler = null;
      File classPath = new File("webroot");
      // the forming of repository is taken from the
      // createClassLoader method in
      // org.apache.catalina.startup.ClassLoaderFactory
      String repository =
          (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
      // the code for forming the URL is taken from
      // the addRepository method in
      // org.apache.catalina.loader.StandardClassLoader.
      urls[0] = new URL(null, repository, streamHandler);
      loader = new URLClassLoader(urls);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Class myClass = null;
    try {
      myClass = loader.loadClass(servletName);
    } catch (ClassNotFoundException e) {
      System.out.println(e.toString());
    }

    Servlet servlet = null;

    try {
      servlet = (Servlet) myClass.newInstance();
      servlet.service(request, response);
    } catch (Throwable e) {
      e.printStackTrace();
      System.out.println(e.toString());
    }
     */
  }
}
