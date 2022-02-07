package com.tomcat.ch1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Bruce Zhao
 * @date 2022/2/6 21:43
 */
public class Response {
  private static final int BUFFER_SIZE = 1024;

  private final OutputStream output;
  private Request request;

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
}
