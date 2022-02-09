package com.tomcat.ch3.startup;

import com.tomcat.ch3.connector.http.HttpConnector;

/**
 * @author Bruce Zhao
 * @date 2022/2/9 17:54
 */
public class Bootstrap {
  public static void main(String[] args) {
    //
    new HttpConnector().start();
  }
}
