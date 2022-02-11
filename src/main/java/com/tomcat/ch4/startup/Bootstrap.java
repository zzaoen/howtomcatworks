package com.tomcat.ch4.startup;


import com.tomcat.ch4.core.HttpConnector;
import com.tomcat.ch4.core.SimpleContainer;
import org.apache.catalina.connector.Connector;

/**
 * @author Bruce Zhao
 * @date 2022/2/11 14:59
 */
public class Bootstrap {
  public static void main(String[] args) {
    //
    try {
      SimpleContainer container = new SimpleContainer();
      HttpConnector connector = new HttpConnector();
      connector.setContainer(container);

      try {
        connector.initialize();
        connector.start();
      }
      catch (Exception e) {
        e.printStackTrace();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
