package com.tomcat.ch3.connector.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author Bruce Zhao
 * @date 2022/2/9 17:55
 */
public class HttpConnector implements Runnable {
  private boolean stopped;

  @Override
  public void run() {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(8080, 10);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }

    while (!stopped) {
      Socket socket = null;
      try {
        socket = serverSocket.accept();
      } catch (IOException e) {
        e.printStackTrace();
        continue;
      }
      HttpProcessor processor = new HttpProcessor(this);
      processor.process(socket);
    }
  }

  // why start a new thread?
  public void start() {
    new Thread(this).start();
  }
}
