package com.tomcat.ch1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Bruce Zhao
 * @date 2022/2/6 17:43
 */
public class HttpServer {
  // shutdown command
  private static final String SHUTDOWN_COMMAND = "/shutdown";

  // the shutdown command received
  private boolean shutdown = false;

  public static void main(String[] args) {
    HttpServer server = new HttpServer();
    server.await();
  }

  private void await() {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(8080, 1);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }

    while (!shutdown) {
      Socket socket = null;
      InputStream input = null;
      OutputStream output = null;

      try {
        socket = serverSocket.accept();
        input = socket.getInputStream();
        output = socket.getOutputStream();

        // create Request object and parse
        Request request = new Request(input);
        request.parse();

        // create Response object
        Response response = new Response(output);
        response.setRequest(request);
        response.sendStaticResource();

        // Close the socket
        socket.close();

        //check if the previous URI is a shutdown command
        shutdown = SHUTDOWN_COMMAND.equals(request.getUri());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
