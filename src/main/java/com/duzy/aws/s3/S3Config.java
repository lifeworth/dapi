package com.duzy.aws.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * @author zhiyuandu
 * @since 2022/8/28 20:20
 * @description
 */
@Configuration()
public class S3Config {
    @Value("${aws.credentials.accessKey}")
    private String awsAccessKey;
    @Value("${aws.credentials.secretKey}")
    private String awsSecretKey;
    @Value("${aws.region}")
    private String region;

    @Bean
    public S3Client s3client() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);

        return S3Client.builder()
                       .region(Region.of(region))
                       .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                       .build();
    }
}
