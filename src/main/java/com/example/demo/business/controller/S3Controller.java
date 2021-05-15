package com.example.demo.business.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.demo.business.constants.Constants;
import com.example.demo.business.dto.ResponseDTO;
import com.example.demo.business.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "file", description = "파일 API")
@RequestMapping("/v1/files")
public class S3Controller {
    @Autowired
    private S3Service s3Service;

    @ApiOperation(value = "파일 목록 조회", response = S3ObjectSummary.class, responseContainer = "LIST")
    @GetMapping(value = "/list")
    public ResponseEntity<List<S3ObjectSummary>> list() {
        List<S3ObjectSummary> result = this.s3Service.getObjectList();
        ResponseDTO<S3ObjectSummary> responseDTO = new ResponseDTO<>(HttpStatus.OK.toString(), Constants.RESULT_MSG_SUCCESS_SEARCH, result);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "파일 업로드", response = ResponseDTO.class)
    @PostMapping(value = "/file")
    public ResponseEntity uploadFile(
            @ApiParam(name = "file", value = "파일", required = true)
            @RequestParam("file") MultipartFile file) throws IOException {
        String objectUrl = this.s3Service.putObject(file);
        List<String> result = new ArrayList<>();
        result.add(objectUrl);
        ResponseDTO<String> responseDTO = new ResponseDTO<>(HttpStatus.CREATED.toString(), Constants.RESULT_MSG_SUCCESS, result);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "파일 다운로드")
    @GetMapping(value = "/file")
    public ResponseEntity<Resource> downloadFile(
            @ApiParam(name = "fileName", value = "파일명", required = true)
            @RequestParam("fileName") String fileName) throws AmazonServiceException, IOException {
        S3Object s3o = this.s3Service.getObject(fileName);
        S3ObjectInputStream s3is = s3o.getObjectContent();
        Resource resource = new InputStreamResource(s3is);

        List<Charset> charsets = new ArrayList<>();
        charsets.add(StandardCharsets.UTF_8);

        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(fileName, StandardCharsets.UTF_8)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAcceptCharset(charsets);
        headers.setContentDisposition(contentDisposition);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

        ResponseEntity<Resource> responseEntity = new ResponseEntity<>(resource, headers, HttpStatus.OK);
        return responseEntity;
    }
}
