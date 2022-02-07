package com.tomcat.ch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Bruce Zhao
 * @date 2022/2/6 17:20
 */
public class ServerDemo1 {
  public static void main(String[] args) throws IOException, InterruptedException {
    //
    ServerSocket serverSocket = new ServerSocket(8080);
    Socket clientSocket = serverSocket.accept();
    System.out.println("After accept ...");
    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    String s;
    while (!(s = in.readLine()).isEmpty()) {
      System.out.println(s);
    }
    // Thread.sleep(30000);
    out.println("hello client, returned from server");
  }
}
