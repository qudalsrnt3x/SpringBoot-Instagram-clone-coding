package com.cos.photogramstart.web.dto.image;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ImageUploadDto {

    private MultipartFile file; // 파일

    private String caption; // 캡션
}
