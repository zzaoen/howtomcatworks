package com.tomcat.ch4.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Bruce Zhao
 * @date 2022/2/9 17:55
 */
public class HttpConnector implements Runnable {
  private SimpleContainer container;
  private boolean stopped;
  ServerSocket serverSocket = null;

  public void setContainer(SimpleContainer container) {
    this.container = container;
  }

  @Override
  public void run() {
    while (!stopped) {
      try {
        Socket socket = serverSocket.accept();
        new Thread(() -> process(socket)).start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void process(Socket socket) {
    try {
      HttpRequest request = new HttpRequest(socket.getInputStream());
      request.parse();
      HttpResponse response = new HttpResponse(socket.getOutputStream());
      container.invoke(request, response);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void start() {
    new Thread(this).start();
  }

  public void initialize() {
    try {
      serverSocket = new ServerSocket(8080, 10);
      System.out.println("Tomdog started at: 8080");
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
