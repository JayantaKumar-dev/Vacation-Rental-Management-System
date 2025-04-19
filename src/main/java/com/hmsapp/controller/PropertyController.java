package com.hmsapp.controller;

import com.hmsapp.entity.Property;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    ///api/property/addProperty
//    @PostMapping("/addProperty")
//    public String addProperty(){
//
//        return "added successfully";
//    }
//
//    @DeleteMapping("/deleteProperty")
//    public String deleteProperty(){
//
//        return "deleted successfully";
//    }


    private PropertyService propertyService;
    private PropertyRepository propertyRepository;

    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property p = propertyService.addProperty(property);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> findPropertyById(@PathVariable Long propertyId) {
        Property p = propertyService.findPropertyById(propertyId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("/allProperty")
    public ResponseEntity<List<Property>> getAllProperties(){
        List<Property> propertyList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @DeleteMapping("/deletePropertyById/{propertyId}")
    public ResponseEntity<String> deletePropertyById(@PathVariable Long propertyId) {
        propertyService.deletePropertyById(propertyId);
        return new ResponseEntity<>("Property Deleted", HttpStatus.OK);
    }

    @PutMapping("/updateProperty/{propertyId}")
    public ResponseEntity<String> updateProperty(@PathVariable Long propertyId, @RequestBody Property property) {
        String result= propertyService.updateProperty(propertyId, property);
        if(result.equals("Property Updated")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500 for server error
        }
    }


    //search mechanism for properties by users
    @GetMapping("/search/{searchParam}")
    public ResponseEntity<?> searchProperty(@PathVariable String searchParam){
        List<Property> propertyList = propertyService.searchProperty(searchParam);
        if(propertyList.isEmpty()){
            return new ResponseEntity<>("No Property Found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(propertyList, HttpStatus.OK);
        }
    }

    @PostMapping("/upload-image/{propertyId}")
    public ResponseEntity<?> uploadMultiplePropertyImages(
            @PathVariable Long propertyId,
            @RequestParam("files") MultipartFile[] files) {
        try {
            List<String> imageUrls = propertyService.uploadMultiplePropertyImages(propertyId, files);
            return ResponseEntity.ok("Images uploaded successfully: " + imageUrls);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Image upload failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/images/{propertyId}")
    public ResponseEntity<?> getPropertyImages(@PathVariable Long propertyId) {
        try {
            List<String> imageUrls = propertyService.getPropertyImages(propertyId);
            return ResponseEntity.ok(imageUrls);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
