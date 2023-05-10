package com.telcom.ups.grpc;


import io.chirpstack.api.as.external.api.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class GrpcApplication {

	private static void login() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.19", 8080).usePlaintext().build();

		InternalServiceGrpc.InternalServiceBlockingStub stub = InternalServiceGrpc.newBlockingStub(channel);

		LoginRequest loginRequest = LoginRequest.newBuilder().setEmail("admin").setPassword("admin").build();

		Logger logger = LoggerFactory.getLogger(GrpcApplication.class);

		LoginResponse loginResponse = stub.login(loginRequest);

		String token = loginResponse.getJwt();
		String name = loginResponse.toString();
		System.out.println(token);
		System.out.println(name);

        logger.info("token={}", token);

		channel.shutdown();
	}

	public static void main(String[] args) {
		SpringApplication.run(GrpcApplication.class, args);

		System.out.println("*******************************************");
		login();
		System.out.println("*********************FIN**********************");



	}

}
