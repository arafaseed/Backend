package com.example.easybusiness.Controler;


import com.example.easybusiness.Repository.CustomerRepo;
import com.example.easybusiness.Repository.LicenseRepo;
import com.example.easybusiness.model.Customer;
import com.example.easybusiness.model.License;
import com.example.easybusiness.model.Staff;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class LicenseAPI {

    @Autowired
    public LicenseRepo licenseRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping("/addLicense")
    public ResponseEntity<?> addLicense(@RequestBody License license){
        try{
            License license1 = licenseRepo.save(license);
            return new ResponseEntity<>("InfAdded", HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Inf doesn't Added",HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getByID{Lic_id}")
    public ResponseEntity<?> getLicense(@PathVariable int Lic_id){
        try{
            Optional<License> optionalLicense  = licenseRepo.findById(Lic_id);
            if (optionalLicense.isPresent()){
                return new ResponseEntity<>(optionalLicense,HttpStatus.OK);
            }else {
                return  new ResponseEntity<>("License not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return  new ResponseEntity<>("Error occur",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllLicense")
    public ResponseEntity<?> getAllLicense() {
        try {
            List<License> licenseList = licenseRepo.findAll();
            if (licenseList.isEmpty()) {
                return new ResponseEntity<>("Staff not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(licenseList, HttpStatus.OK);}
        } catch (Exception exception) {
            return new ResponseEntity<>("Error Occur",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateLicense/{lic_id}")
    public ResponseEntity<?> updateLicense(@RequestBody License license, @PathVariable("lic_id") int lic_id) {
        try {
            Optional<License> existingLicense = licenseRepo.findById(lic_id);
            if (existingLicense.isPresent()) {
                license.setLic_id(lic_id);
                License UpdateLinces = new License();
                Customer customer = customerRepo.findById(license.getCustomer().getCust_id())
                        .orElseThrow(() -> new EntityNotFoundException("Customer with this Id " + license.getCustomer().getCust_id()));
                UpdateLinces.setDate(license.getDate());
                UpdateLinces.setAddress(license.getAddress());
                UpdateLinces.setName(license.getName());
                UpdateLinces.setRegion(license.getRegion());
                UpdateLinces.setCustomer(new Customer(license.getCustomer().getCust_id()));
                License updatedLicense = licenseRepo.save(license);
                return new ResponseEntity<>(updatedLicense, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("License with ID " + lic_id + " not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




//    @PutMapping("/updateLicense{Lic_id}")
//    public ResponseEntity<?> updateLicense(@PathVariable int Lic_id,@RequestBody License license){
//        try {
//            if (licenseRepo.findById(Lic_id).isPresent()){
//                license.setLic_id(Lic_id);
//                License license1 = licenseRepo.save(license);
//                return new ResponseEntity<>(license1,HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>("License not found",HttpStatus.NOT_FOUND);
//            }
//        }catch (Exception exception){
//            return new ResponseEntity<>("Error occur",HttpStatus.BAD_REQUEST);
//        }
//    }

//    @DeleteMapping("/deleteLicense{lic_id}")
//    public ResponseEntity<?> deleteLicense(@PathVariable int lic_id){
//        try {
//            licenseRepo.deleteById(lic_id);
//            return new ResponseEntity<>("License deleted",HttpStatus.OK);
//        }catch (Exception exception){
//            return new ResponseEntity<>("Error occur",HttpStatus.BAD_REQUEST);
//        }
//    }
    @DeleteMapping("/deleteLicense/{lic_id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable("lic_id") int lic_id) {
        if (licenseRepo.existsById(lic_id)) {
            licenseRepo.deleteById(lic_id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
