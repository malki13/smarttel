package com.telcom.ups.monolitico.util.socket;

import org.springframework.context.annotation.Configuration;


@Configuration
public class ClientWebSocketSockJsStompConfig {

//    @Bean
//    public WebSocketClient webSocketClient(StompSessionHandler stompSessionHandler) {
//        final WebSocketClient client = new StandardWebSocketClient();
//
//        final WebSocketStompClient stompClient = new WebSocketStompClient(client);
//        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//
//        //final StompSessionHandler sessionHandler = new MyStompSessionHandler();
//        stompClient.connect("ws://localhost:8080", stompSessionHandler);
//        return client;
//    }

//    @Bean
//    public WebSocketStompClient webSocketStompClient(WebSocketClient webSocketClient, StompSessionHandler stompSessionHandler) {
//        //WebSocketStompClient webSocketStompClient = new WebSocketStompClient(webSocketClient);
//        WebSocketClient client = new StandardWebSocketClient();
//
//        final WebSocketStompClient stompClient = new WebSocketStompClient(client);
//        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//
//        //webSocketStompClient.setMessageConverter(new StringMessageConverter());
//        //webSocketStompClient.connect("http://localhost:8080/websocket-sockjs-stomp", stompSessionHandler);
//        stompClient.connect("ws://104.198.187.189:8080/api/devices/310fa0b502000000/events", stompSessionHandler);
//        return stompClient;
//    }
//
//    @Bean
//    public WebSocketClient webSocketClient() {
//        List<Transport> transports = new ArrayList<>();
//        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
//        transports.add(new RestTemplateXhrTransport());
//        return new SockJsClient(transports);
//    }
//
//    @Bean
//    public StompSessionHandler stompSessionHandler() {
//        return new ClientStompSessionHandler();
//    }
//    private static final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
//
//    @Bean
//    public WebSocketConnectionManager webSocketConnectionManager() {
//        HttpHeaders headersHttp = new HttpHeaders();
//
//        List<String> subprotocols = Arrays.asList("abc");
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhcyIsImV4cCI6MTY0MjI1NzM1MSwiaWQiOjEsImlzcyI6ImFzIiwibmJmIjoxNjQyMTcwOTUxLCJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiJhZG1pbiJ9.dtp_onJlxPVlYUJiWbAjjEszFO5edn4m_2AsdQrpuMo";
//        //headers.add();
//        //headers.add("Grpc-Metadata-Content-Type", "application/grpc");
//        headers.add("Authorization", "Bearer " + token);
//        //headers.add("Grpc-Metadata-Authorization", "Bearer " + token);
//        headers.setSecWebSocketProtocol(subprotocols);
//        //headersHttp.add("Authorization", "Bearer " + token);
//        WebSocketConnectionManager manager = new WebSocketConnectionManager(
//                webSocketClient(),
//                webSocketHandler(),
//                "ws://104.198.187.189:8080/api/devices/310fa0b502000000/events"
//                //"ws://localhost:8080/api/devices/310fa0b502000000/events"
//                //"ws://172.19.0.1:8080/api/devices/310fa0b502000000/events"
//        );
//        manager.setOrigin("http://localhost:8090");
//        manager.setSubProtocols(subprotocols);
//        manager.setAutoStartup(true);
//        manager.setHeaders(headersHttp);
//        return manager;
//    }
//
//    @Bean
//    public WebSocketClient webSocketClient() {
//        return new StandardWebSocketClient();
//    }
//
//    @Bean
//    public WebSocketHandler webSocketHandler() {
//        return new ClientWebSocketHandler();
//    }


}
