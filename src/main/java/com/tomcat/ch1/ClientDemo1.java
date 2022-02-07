package com.tomcat.ch1;

import java.io.*;
import java.net.Socket;

/**
 * @author Bruce Zhao
 * @date 2022/2/6 11:41
 * @apiNote python3 -m http.server --cgi 8080
 */
public class ClientDemo1 {
  public static void main(String[] args) throws IOException, InterruptedException {
    Socket socket = new Socket("127.0.0.1", 8080);
    // Socket socket = new Socket("www.baidu.com", 80);
    OutputStream os = socket.getOutputStream();
    PrintWriter out = new PrintWriter(os, true);
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    // send
    out.println("GET / HTTP/1.1");
    // out.println("Host: localhost:8080");
    out.println("Host: baidu.com");
    out.println("Connection: Close");
    // This line means the output is done
    out.println();

    // read
    boolean loop = true;
    StringBuilder sb = new StringBuilder(8096);
    while (loop) {
      if (in.ready()) {
        int i = 0;
        while (i != -1) {
          i = in.read();
          sb.append((char) i);
        }
        loop = false;
      }
      Thread.sleep(50);
    }

    System.out.println(sb);
    socket.close();
  }
}
