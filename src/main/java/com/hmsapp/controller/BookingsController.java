package com.hmsapp.controller;

import com.hmsapp.entity.Booking;
import com.hmsapp.entity.Property;
import com.hmsapp.entity.RoomAvailability;
import com.hmsapp.repository.BookingRepository;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.repository.RoomAvailabilityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingsController {
    private RoomAvailabilityRepository roomAvailabilityRepository;
    private PropertyRepository propertyRepository;
    private BookingRepository bookingRepository;
    public BookingsController(RoomAvailabilityRepository roomAvailabilityRepository, PropertyRepository propertyRepository, BookingRepository bookingRepository) {
        this.roomAvailabilityRepository = roomAvailabilityRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/search/room")
    public ResponseEntity<?> searchRoomsAndBook(@RequestParam LocalDate fromDate, @RequestParam LocalDate toDate, @RequestParam String roomType, @RequestParam long propertyId, @RequestBody Booking booking) {
        List<RoomAvailability> rooms = roomAvailabilityRepository.findAvailableRooms(roomType, fromDate, toDate);
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new IllegalArgumentException("Property not found"));
        for(RoomAvailability r:rooms){
            if(r.getTotalRooms()==0){
                return new ResponseEntity<>("No room available right now", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        booking.setProperty(property);
        bookingRepository.save(booking);
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }
}
