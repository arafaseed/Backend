package com.example.easybusiness.Controler;

import com.example.easybusiness.DTO.AddStaffRequest;
import com.example.easybusiness.DTO.CustomerRequest;
import com.example.easybusiness.DTO.LoginRequest;
import com.example.easybusiness.DTO.StaffRequest;
import com.example.easybusiness.Repository.LoginRepo;
import com.example.easybusiness.Repository.StaffRepo;
import com.example.easybusiness.model.*;
import com.example.easybusiness.model.Enums.Role;
import com.example.easybusiness.model.Enums.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class StaffAPI {


    @Autowired
    private StaffRepo staffRepo;

    //This repo for security(As a user)
    @Autowired
   private LoginRepo loginRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addStaff")
    public ResponseEntity<?> addStaff(@RequestBody AddStaffRequest request) {
        try {
            LoginRequest loginRequest = request.getLoginRequest();
            StaffRequest staffRequest = request.getStaffRequest();

            // Save login details
            Login login = new Login();
            login.setUsername(loginRequest.getUsername());
            login.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            login.setStatus(Status.REGISTER);
            login.setRole(Role.Staff);
            Login savedLogin = loginRepo.save(login);

            // Save staff details
            Staff staff = new Staff();
            staff.setAddress(staffRequest.getAddress());
            staff.setGender(staffRequest.getGender());
            staff.setName(staffRequest.getName());
            staff.setPhone(staffRequest.getPhone());
            staff.setUserId(savedLogin.getUserID());
            Staff savedStaff = staffRepo.save(staff);

            return new ResponseEntity<>("Staff added", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Staff not added", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getByID/{S_id}")
    public ResponseEntity<?> getStaff(@PathVariable Long S_id){
        try{
            Optional<Staff> optionalStaff  = staffRepo.findById(S_id);
            if (optionalStaff.isPresent()){
                return new ResponseEntity<>(optionalStaff,HttpStatus.OK);
            }else {
                return  new ResponseEntity<>("Staff found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return  new ResponseEntity<>("Error occur",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllStaff")
    public ResponseEntity<?> getAllStaff() {
        try {
            List<Staff> staffList = staffRepo.findAll();
            if (staffList.isEmpty()) {
                return new ResponseEntity<>("Staff not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(staffList, HttpStatus.OK);}
        } catch (Exception exception) {
            return new ResponseEntity<>("Error Occur",HttpStatus.BAD_REQUEST);
            }
        }

    @PutMapping("updateStaff/{S_id}")
    public ResponseEntity<?> updateStaff(@PathVariable Long S_id,@RequestBody Staff staff){
        try {
            if (staffRepo.findById(S_id).isPresent()){
                staff.setS_id(S_id);
                Staff staff1 = staffRepo.save(staff);
                return new ResponseEntity<>(staff1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Staff not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Error occur",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteStaff{S_id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long S_id){
        try {
            staffRepo.deleteById(S_id);
            return new ResponseEntity<>("Staff deleted ",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Error occur",HttpStatus.BAD_REQUEST);
        }
    }
    }

