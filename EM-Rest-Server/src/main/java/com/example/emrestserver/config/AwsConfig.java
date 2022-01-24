package com.example.emrestserver.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


@PropertySource("classpath:application.properties")
@Configuration
public class AwsConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessSKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String accessSecret;
    @Value("${cloud.aws.region.static}")
    private String region;



    @Bean
    AmazonS3 s3Client(){
        AWSCredentials credentials = new BasicAWSCredentials(accessSKey,accessSecret);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region).build();
    }
}
