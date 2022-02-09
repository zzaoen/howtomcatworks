package com.tomcat.ch3.connector.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Locale;

import static com.tomcat.ch2.Constants.WEB_ROOT;

/**
 * @author Bruce Zhao
 * @date 2022/2/9 20:29
 */
public class HttpResponse implements HttpServletResponse {
  private static final int BUFFER_SIZE = 1024;

  private HttpRequest request;
  private final OutputStream output;
  PrintWriter writer;

  public HttpResponse(OutputStream output) {
    this.output = output;
  }

  public void setRequest(HttpRequest request) {
    this.request = request;
  }

  public void sendStaticResource() {
    byte[] bytes = new byte[BUFFER_SIZE];
    FileInputStream fis = null;
    try {
      // If have root directory
      File file = new File(WEB_ROOT, request.getUri());

      // remove first "/"
      // File file = new File(request.getUri().substring(1));
      if (file.exists()) {
        fis = new FileInputStream(file);
        int ch = fis.read(bytes, 0, BUFFER_SIZE);
        while (ch != -1) {
          output.write(bytes, 0, ch);
          ch = fis.read(bytes, 0, BUFFER_SIZE);
        }
      } else {
        // file not found
        String errorMessage =
            "HTTP/1.1 404 File Not Found\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: 23\r\n"
                + "\r\n"
                + "<h1>File Not Found</h1>";
        output.write(errorMessage.getBytes());
      }
    } catch (Exception e) {
      // thrown if cannot instantiate a File object
      System.out.println(e.toString());
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void addCookie(Cookie cookie) {}

  @Override
  public boolean containsHeader(String s) {
    return false;
  }

  @Override
  public String encodeURL(String s) {
    return null;
  }

  @Override
  public String encodeRedirectURL(String s) {
    return null;
  }

  @Override
  public String encodeUrl(String s) {
    return null;
  }

  @Override
  public String encodeRedirectUrl(String s) {
    return null;
  }

  @Override
  public void sendError(int i, String s) throws IOException {}

  @Override
  public void sendError(int i) throws IOException {}

  @Override
  public void sendRedirect(String s) throws IOException {}

  @Override
  public void setDateHeader(String s, long l) {}

  @Override
  public void addDateHeader(String s, long l) {}

  @Override
  public void setHeader(String s, String s1) {}

  @Override
  public void addHeader(String s, String s1) {}

  @Override
  public void setIntHeader(String s, int i) {}

  @Override
  public void addIntHeader(String s, int i) {}

  @Override
  public void setStatus(int i) {}

  @Override
  public void setStatus(int i, String s) {}

  @Override
  public int getStatus() {
    return 0;
  }

  @Override
  public String getHeader(String s) {
    return null;
  }

  @Override
  public Collection<String> getHeaders(String s) {
    return null;
  }

  @Override
  public Collection<String> getHeaderNames() {
    return null;
  }

  @Override
  public String getCharacterEncoding() {
    return null;
  }

  @Override
  public String getContentType() {
    return null;
  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return null;
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    // autoflush is true, println() will flush,
    // but print() will not.
    writer = new PrintWriter(output, true);
    return writer;
  }

  @Override
  public void setCharacterEncoding(String s) {}

  @Override
  public void setContentLength(int i) {}

  @Override
  public void setContentLengthLong(long l) {}

  @Override
  public void setContentType(String s) {}

  @Override
  public void setBufferSize(int i) {}

  @Override
  public int getBufferSize() {
    return 0;
  }

  @Override
  public void flushBuffer() throws IOException {}

  @Override
  public void resetBuffer() {}

  @Override
  public boolean isCommitted() {
    return false;
  }

  @Override
  public void reset() {}

  @Override
  public void setLocale(Locale locale) {}

  @Override
  public Locale getLocale() {
    return null;
  }
}
