package com.booking.userservice.service.helper;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserServiceHelper {

    private final Storage storage;
    @Value("${user-service.bucket-name}")
    private String bucketName;

    public Mono<Void> uploadImage(String imagesURL, FilePart filePart, DataBuffer dataBuffer) {
        final String IMAGE_URL = imagesURL + "/" + filePart.filename();
        final BlobId blobId = BlobId.of(bucketName,  IMAGE_URL);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] bytes = dataBuffer.asByteBuffer().array();
        storage.create(blobInfo, bytes);
        return Mono.empty();
    }
}
