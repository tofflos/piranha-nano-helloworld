package com.example;

import cloud.piranha.nano.NanoPiranhaBuilder;
import cloud.piranha.nano.NanoRequestBuilder;
import cloud.piranha.nano.NanoResponseBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Application {

    public static void main(String[] args) throws IOException, ServletException {
        var servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                response.setContentType("text/html");
                response.getWriter().println("""
                                            <html>
                                              <body>
                                                Hello world!
                                              </body>
                                            </html>
                                             """);
                response.getWriter().close();
            }
        };

        var pirahna = new NanoPiranhaBuilder()
                .servlet("HelloWorldServlet", servlet)
                .build();


        var request = new NanoRequestBuilder().build();
        var outputStream = new ByteArrayOutputStream();
        var response = new NanoResponseBuilder()
                .outputStream(outputStream)
                .build();

        pirahna.service(request, response);

        System.out.println("Response content type: " + response.getContentType());
        System.out.println("Response body: " + System.lineSeparator() + outputStream.toString());
    }
}
