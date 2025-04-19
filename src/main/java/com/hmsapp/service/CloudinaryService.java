package com.hmsapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Map uploadFile(MultipartFile file) throws IOException {
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "folder", "airbnb-project", // Organize images in a folder
                "tags", "property,image"    // Add tags to the image
        );

        return cloudinary.uploader().upload(file.getBytes(), uploadParams);
    }
}