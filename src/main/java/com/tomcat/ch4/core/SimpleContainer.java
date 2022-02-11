package com.tomcat.ch4.core;

import com.tomcat.ch4.ModernServlet;
import org.apache.catalina.*;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.juli.logging.Log;

import javax.management.ObjectName;
import javax.servlet.ServletException;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruce Zhao
 * @date 2022/2/11 15:04
 */
public class SimpleContainer implements Container {

  @Override
  public Log getLogger() {
    return null;
  }

  @Override
  public String getLogName() {
    return null;
  }

  @Override
  public ObjectName getObjectName() {
    return null;
  }

  @Override
  public String getDomain() {
    return null;
  }

  @Override
  public String getMBeanKeyProperties() {
    return null;
  }

  @Override
  public Pipeline getPipeline() {
    return null;
  }

  @Override
  public Cluster getCluster() {
    return null;
  }

  @Override
  public void setCluster(Cluster cluster) {}

  @Override
  public int getBackgroundProcessorDelay() {
    return 0;
  }

  @Override
  public void setBackgroundProcessorDelay(int delay) {}

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void setName(String name) {}

  @Override
  public Container getParent() {
    return null;
  }

  @Override
  public void setParent(Container container) {}

  @Override
  public ClassLoader getParentClassLoader() {
    return null;
  }

  @Override
  public void setParentClassLoader(ClassLoader parent) {}

  @Override
  public Realm getRealm() {
    return null;
  }

  @Override
  public void setRealm(Realm realm) {}

  @Override
  public void backgroundProcess() {}

  @Override
  public void addChild(Container child) {}

  @Override
  public void addContainerListener(ContainerListener listener) {}

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {}

  @Override
  public Container findChild(String name) {
    return null;
  }

  @Override
  public Container[] findChildren() {
    return new Container[0];
  }

  @Override
  public ContainerListener[] findContainerListeners() {
    return new ContainerListener[0];
  }

  @Override
  public void removeChild(Container child) {}

  @Override
  public void removeContainerListener(ContainerListener listener) {}

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {}

  @Override
  public void fireContainerEvent(String type, Object data) {}

  @Override
  public void logAccess(Request request, Response response, long time, boolean useDefault) {}

  @Override
  public AccessLog getAccessLog() {
    return null;
  }

  @Override
  public int getStartStopThreads() {
    return 0;
  }

  @Override
  public void setStartStopThreads(int startStopThreads) {}

  @Override
  public File getCatalinaBase() {
    return null;
  }

  @Override
  public File getCatalinaHome() {
    return null;
  }

  @Override
  public void addLifecycleListener(LifecycleListener listener) {}

  @Override
  public LifecycleListener[] findLifecycleListeners() {
    return new LifecycleListener[0];
  }

  @Override
  public void removeLifecycleListener(LifecycleListener listener) {}

  @Override
  public void init() throws LifecycleException {}

  @Override
  public void start() throws LifecycleException {}

  @Override
  public void stop() throws LifecycleException {}

  @Override
  public void destroy() throws LifecycleException {}

  @Override
  public LifecycleState getState() {
    return null;
  }

  @Override
  public String getStateName() {
    return null;
  }

  public void invoke(HttpRequest request, HttpResponse response) {
    ModernServlet servlet = new ModernServlet();
    try {
      servlet.service(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }
}
