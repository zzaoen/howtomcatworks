package com.tomcat.ch2;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * @author Bruce Zhao
 * @date 2022/2/8 13:33
 */
public class ResponseFacade implements ServletResponse {
  private ServletResponse response;

  public ResponseFacade(ServletResponse response) {
    this.response = response;
  }

  @Override
  public void flushBuffer() throws IOException {
    response.flushBuffer();
  }

  @Override
  public int getBufferSize() {
    return response.getBufferSize();
  }

  @Override
  public String getCharacterEncoding() {
    return response.getCharacterEncoding();
  }

  @Override
  public String getContentType() {
    return response.getContentType();
  }

  @Override
  public Locale getLocale() {
    return response.getLocale();
  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return response.getOutputStream();
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    return response.getWriter();
  }

  @Override
  public void setCharacterEncoding(String charset) {}

  @Override
  public boolean isCommitted() {
    return response.isCommitted();
  }

  @Override
  public void reset() {
    response.reset();
  }

  @Override
  public void resetBuffer() {
    response.resetBuffer();
  }

  @Override
  public void setBufferSize(int size) {
    response.setBufferSize(size);
  }

  @Override
  public void setContentLength(int length) {
    response.setContentLength(length);
  }

  @Override
  public void setContentLengthLong(long len) {}

  @Override
  public void setContentType(String type) {
    response.setContentType(type);
  }

  @Override
  public void setLocale(Locale locale) {
    response.setLocale(locale);
  }
}
