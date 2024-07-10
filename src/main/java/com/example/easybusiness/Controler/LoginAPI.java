package com.example.easybusiness.Controler;

import com.example.easybusiness.DTO.LoginRequest;
import com.example.easybusiness.Repository.LoginRepo;
import com.example.easybusiness.model.Customer;
import com.example.easybusiness.model.Enums.Status;
import com.example.easybusiness.model.Login;
import com.example.easybusiness.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class LoginAPI {

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //CREATE USER AUTHENTICATION
    @PostMapping("/login")
    public ResponseEntity <Login> authentication(@RequestBody LoginRequest loginRequest) {
        Optional<Login> userLogin = loginRepo.findByUsername(loginRequest.getUsername());
        if (userLogin.isPresent()) {
            Login login = userLogin.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), login.getPassword())) {
                return ResponseEntity.ok(login);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }







    @PostMapping("/addUser")
    public ResponseEntity<?> AddUser (@RequestBody Login login){
        try{
            Login login1 = new Login();
            login1.setUsername(login.getUsername());
            login1.setRole(login.getRole());
            login1.setStatus(Status.ACTIVE);
            login1.setPassword(passwordEncoder.encode(login.getPassword()));
            loginRepo.save(login1);

            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Login unsuccessful",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
        try {
            List<Login> userList = loginRepo.findAll();
            if (userList.isEmpty()) {
                return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userList, HttpStatus.OK);}
        } catch (Exception exception) {
            return new ResponseEntity<>("Error Occur",HttpStatus.BAD_REQUEST);
        }
    }
}
