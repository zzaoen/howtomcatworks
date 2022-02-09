package com.tomcat.ch3;

import com.tomcat.ch3.connector.http.HttpRequest;
import com.tomcat.ch3.connector.http.HttpResponse;
import com.tomcat.ch3.connector.http.Request;
import com.tomcat.ch3.connector.http.Response;

/**
 * @author Bruce Zhao
 * @date 2022/2/9 18:27
 */
public class StaticResourceProcessor {
    public void process(HttpRequest request, HttpResponse response) {
        response.sendStaticResource();
    }
}
