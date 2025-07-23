package com.example.securenotes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.*;
import java.util.Base64;

@Service
public class KmsService {

    @Value("${aws.keyId}")
    private String keyId;

    private final KmsClient kmsClient;

    public KmsService(KmsClient kmsClient) {
        this.kmsClient = kmsClient;
    }

    public String encrypt(String plaintext) {
        EncryptRequest encryptRequest = EncryptRequest.builder()
                .keyId(keyId)
                .plaintext(SdkBytes.fromUtf8String(plaintext))
                .build();

        EncryptResponse response = kmsClient.encrypt(encryptRequest);
        return Base64.getEncoder().encodeToString(response.ciphertextBlob().asByteArray());
    }

    public String decrypt(String ciphertext) {
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);

        DecryptRequest decryptRequest = DecryptRequest.builder()
                .ciphertextBlob(SdkBytes.fromByteArray(decodedBytes))
                .build();

        DecryptResponse response = kmsClient.decrypt(decryptRequest);
        return response.plaintext().asUtf8String();
    }
}

