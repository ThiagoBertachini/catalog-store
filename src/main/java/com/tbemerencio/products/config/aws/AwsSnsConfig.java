package com.tbemerencio.products.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSnsConfig {

    @Value("aws.accessKeyId")
    private String awsAccessKeyId;
    @Value("aws.secretKey")
    private String awsSecretKey;
    @Value("aws.region")
    private String awsRegion;
    @Value("aws.sns.topic.name.arn")
    private String awsCatalogTopicArn;

    @Bean
    public AmazonSNS awsSnsBuilder(){
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretKey);

        return AmazonSNSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(awsRegion)
                .build();
    }

    @Bean(name = "catalogEventsTopic")
    public Topic snsCatalogTopicBuilder(){
        return new Topic().withTopicArn(awsCatalogTopicArn);
    }

}
