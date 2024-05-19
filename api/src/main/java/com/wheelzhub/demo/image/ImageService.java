package com.wheelzhub.demo.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public Image saveImage(String fileName, String contentType, byte[] fileBytes) {
        if (!isImage(fileBytes)) {
            return null;
        }

        Image imageToSave = Image.builder()
                .name(fileName)
                .type(contentType)
                .data(fileBytes)
                .build();

        return imageRepository.save(imageToSave);
    }

    private static boolean isImage(byte[] data) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            return ImageIO.read(bis) != null;
        } catch (IOException e) {
            return false;
        }
    }
}
