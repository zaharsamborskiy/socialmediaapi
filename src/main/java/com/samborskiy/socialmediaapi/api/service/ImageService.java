package com.samborskiy.socialmediaapi.api.service;

import com.samborskiy.socialmediaapi.api.utils.ImageUtils;
import com.samborskiy.socialmediaapi.store.entities.Image;
import com.samborskiy.socialmediaapi.store.repositories.ImageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageService {
    ImageRepository imageRepository;
    public Image createNewImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImageData(ImageUtils.compressImage(file.getBytes()));
        return image;
    }
    public byte[] downloadImage(String fileName){
        Optional<Image> dbImageData = imageRepository.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }
}
