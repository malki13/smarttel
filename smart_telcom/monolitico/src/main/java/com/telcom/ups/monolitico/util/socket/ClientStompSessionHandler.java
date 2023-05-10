package com.telcom.ups.monolitico.util.socket;


public class ClientStompSessionHandler {

//    private static final Logger logger = LoggerFactory.getLogger(ClientStompSessionHandler.class);
//
//    @Override
//    public void afterConnected(StompSession session, StompHeaders headers) {
//        logger.info("Client connected: headers {}", headers);
//
////        session.subscribe("/app/subscribe", this);
////        session.subscribe("/queue/responses", this);
////        session.subscribe("/queue/errors", this);
////        session.subscribe("/topic/periodic", this);
////
////        String message = "one-time message from client";
////        logger.info("Client sends: {}", message);
////        session.send("/app/request", message);
//    }
//
//    @Override
//    public void handleFrame(StompHeaders headers, Object payload) {
//        logger.info("Client received: payload {}, headers {}", payload, headers);
//    }
//
//    @Override
//    public void handleException(StompSession session, StompCommand command,
//                                StompHeaders headers, byte[] payload, Throwable exception) {
//        logger.error("Client error: exception {}, command {}, payload {}, headers {}",
//                exception.getMessage(), command, payload, headers);
//    }
//
//    @Override
//    public void handleTransportError(StompSession session, Throwable exception) {
//        logger.error("Client transport error: error {}", exception.getMessage());
//    }
}
