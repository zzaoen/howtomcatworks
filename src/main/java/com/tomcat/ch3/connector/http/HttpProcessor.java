package com.tomcat.ch3.connector.http;

import com.tomcat.ch3.ServletProcessor;
import com.tomcat.ch3.StaticResourceProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Bruce Zhao
 * @date 2022/2/9 18:00
 */
public class HttpProcessor {
  private HttpConnector httpConnector;

  public HttpProcessor(HttpConnector httpConnector) {
    this.httpConnector = httpConnector;
  }

  public void process(Socket socket) {
    InputStream input = null;
    OutputStream output = null;

    try {

      input = socket.getInputStream();
      output = socket.getOutputStream();

      HttpRequest request = new HttpRequest(input);
      request.parse();
      HttpResponse response = new HttpResponse(output);
      response.setRequest(request);

      // check request type
      if (request.getUri() != null && request.getUri().startsWith("/servlet")) {
        new ServletProcessor().process(request, response);
      } else {
        new StaticResourceProcessor().process(request, response);
      }

      // Close the socket
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
