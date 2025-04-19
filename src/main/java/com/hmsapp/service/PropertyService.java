package com.hmsapp.service;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.PropertyImage;
import com.hmsapp.repository.PropertyImageRepository;
import com.hmsapp.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;
    private CloudinaryService cloudinaryService;
    private PropertyImageRepository propertyImageRepository;

    public PropertyService(PropertyRepository propertyRepository, CloudinaryService cloudinaryService, PropertyImageRepository propertyImageRepository) {
        this.propertyRepository = propertyRepository;
        this.cloudinaryService = cloudinaryService;
        this.propertyImageRepository = propertyImageRepository;
    }

    public Property addProperty(Property property) {
        Property c = propertyRepository.save(property);
        return c;
    }

    public Property findPropertyById(Long id) {
        Property c = propertyRepository.findById(id).orElse(null);
        return c;
    }

    public List<Property> getAllProperties() {
        List<Property> property = propertyRepository.findAll();
        List<Property> c = new ArrayList<>(property);
        return c;
    }

    public void deletePropertyById(Long id) {
        propertyRepository.deleteById(id);
    }

    public String updateProperty(Long id, Property property) {
        Optional<Property> existingProperty = propertyRepository.findById(id);
        if (existingProperty.isPresent()) {
            property.setId(id);
            propertyRepository.save(property);
            return "Property Updated";
        }
        return "No Property Found with id " + id;
    }

    public List<Property> searchProperty(String searchParam) {
        return propertyRepository.searchProperty(searchParam);
    }

    public List<String> uploadMultiplePropertyImages(Long propertyId, MultipartFile[] files) throws IOException {
        // Validate property existence
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        List<String> imageUrls = new ArrayList<>();

        // Process each file
        for (MultipartFile file : files) {
            Map uploadResult = cloudinaryService.uploadFile(file);
            String imageUrl = uploadResult.get("secure_url").toString();

            // Save image URL in the PropertyImage table
            PropertyImage propertyImage = new PropertyImage();
            propertyImage.setUrl(imageUrl);
            propertyImage.setProperty(property);
            propertyImageRepository.save(propertyImage);

            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }

    public List<String> getPropertyImages(Long propertyId) {
        // Validate property existence
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        // Fetch all images related to the property
        List<PropertyImage> propertyImages = propertyImageRepository.findByProperty(property);

        // Extract URLs and return
        return propertyImages.stream()
                .map(PropertyImage::getUrl)
                .collect(Collectors.toList());
    }

//    public List<Property> searchProperty(String searchParam) {
//        return propertyRepository.searchProperty(searchParam);
//    }
}
