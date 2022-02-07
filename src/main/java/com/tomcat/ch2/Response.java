package com.tomcat.ch2;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @author Bruce Zhao
 * @date 2022/2/6 21:43
 */
public class Response implements ServletResponse {
  private static final int BUFFER_SIZE = 1024;

  private Request request;
  private final OutputStream output;
  PrintWriter writer;

  public Response(OutputStream output) {
    this.output = output;
  }

  public void setRequest(Request request) {
    this.request = request;
  }

  public void sendStaticResource() {
    byte[] bytes = new byte[BUFFER_SIZE];
    FileInputStream fis = null;
    try {
      // If have root directory
      // File file = new File(HttpServer.WEB_ROOT, request.getUri());

      // remove first "/"
      File file = new File(request.getUri().substring(1));
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

  /** implementation of ServletResponse */
  @Override
  public void flushBuffer() throws IOException {}

  @Override
  public int getBufferSize() {
    return 0;
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
  public Locale getLocale() {
    return null;
  }

  public OutputStream getOutput() {
    return output;
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
  public void setCharacterEncoding(String charset) {}

  @Override
  public boolean isCommitted() {
    return false;
  }

  @Override
  public void reset() {}

  @Override
  public void resetBuffer() {}

  @Override
  public void setBufferSize(int size) {}

  @Override
  public void setContentLength(int length) {}

  @Override
  public void setContentLengthLong(long len) {}

  @Override
  public void setContentType(String type) {}

  @Override
  public void setLocale(Locale locale) {}
}
