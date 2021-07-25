package com.basis.campina.xdocumentos.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {

    private final ApplicationProperties properties;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(properties.getUrl())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
        if(!existsBucket(minioClient, properties.getBucket())){
            makeBucket(minioClient, properties.getBucket());
        }
        return minioClient;
    }

    @SneakyThrows
    public boolean existsBucket(MinioClient client, String bucketName) {
        return client.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
    }

    @SneakyThrows
    public void makeBucket(MinioClient client, String bucketName) {
        client.makeBucket(MakeBucketArgs.builder()
                .bucket(bucketName)
                .build());
    }
}
