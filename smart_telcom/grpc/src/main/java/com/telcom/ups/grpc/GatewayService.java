package com.telcom.ups.grpc;

import io.chirpstack.api.as.external.api.ApplicationServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class GatewayService {
    private final Channel channel;
    private Metadata headers;

    public GatewayService(@Value("${chirpstack.grpc.host}") String host,
                          @Value("${chirpstack.grpc.port}") int port,
                          @Value("${chirpstack.grpc.token}") String token) {
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER), "Bearer " + token);
        ApplicationServiceGrpc.ApplicationServiceStub stub = ApplicationServiceGrpc.newStub(this.channel);
        MetadataUtils.attachHeaders(stub,metadata);
    }

    public Channel getChannel() {
        return channel;
    }

    public Metadata getHeaders() {
        return headers;
    }
}
