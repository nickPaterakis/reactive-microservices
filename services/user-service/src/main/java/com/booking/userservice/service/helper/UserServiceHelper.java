package com.booking.userservice.service.helper;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserServiceHelper {

    private final Storage storage;

    public Mono<Void> uploadImage(String imagesURL, FilePart filePart, DataBuffer dataBuffer) {
        final BlobId blobId = BlobId.of("booking-project",  imagesURL + "/" + filePart.filename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] bytes = dataBuffer.asByteBuffer().array();
        storage.create(blobInfo, bytes);
        return Mono.empty();
    }
}
