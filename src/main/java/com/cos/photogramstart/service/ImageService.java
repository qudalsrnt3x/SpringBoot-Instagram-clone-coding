package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}") // application.yml 의 file.path 를 가져옴
    private String uploadFolder;

    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID(); // uuid

        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); // 실제 파일이름 // 1.jpg
        // 파일 이름이 중복되는 것을 막기 위해 UUID 사용
        System.out.println("imageFileName = " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName); // 저장할 곳(application.yml 에 적은 file 경로를 가져오기
        // 경로 + 파일명

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // image 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); // 377067bd-978b-4ca3-bf21-2936875c461f_스크린샷 2021-07-08 19.39.00.png
        Image imageEntity = imageRepository.save(image);

        System.out.println("imageEntity = " + imageEntity);

    }
}
