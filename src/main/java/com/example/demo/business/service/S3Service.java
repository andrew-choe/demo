package com.example.demo.business.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class S3Service {
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private AmazonS3 s3Client;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public List<S3ObjectSummary> getObjectList() {
        ListObjectsV2Result result = this.s3Client.listObjectsV2(this.bucket);
        return result.getObjectSummaries();
    }

    public String putObject(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        this.s3Client.putObject(new PutObjectRequest(this.bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return this.s3Client.getUrl(this.bucket, fileName).toString();
    }

    public S3Object getObject(String objectName) throws AmazonServiceException, IOException {
        S3Object s3o = s3Client.getObject(bucket, objectName);
        return s3o;
    }
}
