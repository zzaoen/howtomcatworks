package com.tomcat.ch2;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author Bruce Zhao
 * @date 2022/2/7 12:58
 */
public class ServletProcessor1 {
  public void process(Request request, Response response) {
    String uri = request.getUri();
    String servletName = uri.substring(uri.lastIndexOf("/") + 1);
    /*SimpleServlet servlet = new SimpleServlet();
    try {
      servlet.service(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }*/

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
  }
}