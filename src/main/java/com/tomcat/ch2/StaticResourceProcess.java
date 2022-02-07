package com.tomcat.ch2;

/**
 * @author Bruce Zhao
 * @date 2022/2/7 12:59
 */
public class StaticResourceProcess {
    public void process(Request request, Response response) {
        response.sendStaticResource();
    }
}
