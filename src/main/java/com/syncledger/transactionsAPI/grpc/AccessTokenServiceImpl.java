package com.syncledger.transactionsAPI.grpc;

import org.springframework.stereotype.Service;
import com.syncledger.grpc.AccessTokens.*;
import io.grpc.stub.StreamObserver;

@Service
public class AccessTokenServiceImpl extends GetAccessTokensGrpc.GetAccessTokensImplBase {

    @Override
    public void getTokens(com.syncledger.grpc.TokenRequest request, StreamObserver<com.syncledger.grpc.TokenReply> responseObserver) {
        // Here, implement the logic to handle the request and generate the response.
        // Example response:
        com.syncledger.grpc.TokenReply reply = com.syncledger.grpc.TokenReply.newBuilder()
                .setItemId("12345")
                .setAccessToken("access_token_value")
                .setTransactionsCursor("cursor_here")
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
