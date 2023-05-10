package com.telcom.ups.monolitico.util.socket;


import javax.net.ssl.SSLSocket;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ClientWebsocket {


    private static final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
    static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhcyIsImV4cCI6MTY1Mjg4MTc5OSwiaWQiOjEsImlzcyI6ImFzIiwibmJmIjoxNjUyNzk1Mzk5LCJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiJhZG1pbiJ9._eBU3QTRmBtAZapYywILPKTdqz4-bo7H0bZaNtQye_A";

    static Header header1 = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    static Header header9 = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

    public static CloseableHttpClient setViaSocketFactory() {
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                SSLContexts.createDefault(),
                new String[]{"TLSv1.2", "TLSv1.3"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        List<Header> headers = Arrays.asList(header1, header9);
        return HttpClients.custom().setDefaultHeaders(headers).setSSLSocketFactory(sslsf).build();
    }

    public CloseableHttpClient setTlsVersionPerConnection() {
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(SSLContexts.createDefault()) {

            @Override
            protected void prepareSocket(SSLSocket socket) {
                String hostname = socket.getInetAddress().getHostName();
                if (hostname.endsWith("internal.system.com")) {
                    socket.setEnabledProtocols(new String[]{"TLSv1", "TLSv1.1", "TLSv1.2", "TLSv1.3"});
                } else {
                    socket.setEnabledProtocols(new String[]{"TLSv1.3"});
                }
            }
        };

        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    public CloseableHttpClient setViaSystemProperties() {
        return HttpClients.createSystem();
    }


}
