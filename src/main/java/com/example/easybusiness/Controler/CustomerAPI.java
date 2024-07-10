package com.example.easybusiness.Controler;


import com.example.easybusiness.DTO.AddCustomerRequest;
import com.example.easybusiness.DTO.CustomerRequest;
import com.example.easybusiness.DTO.CustomerResponse;
import com.example.easybusiness.DTO.LoginRequest;
import com.example.easybusiness.Repository.CustomerRepo;
import com.example.easybusiness.Repository.LoginRepo;
import com.example.easybusiness.model.Customer;
import com.example.easybusiness.model.Enums.Role;
import com.example.easybusiness.model.Enums.Status;
import com.example.easybusiness.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerAPI {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private LoginRepo loginRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/AddCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody AddCustomerRequest request) {
        try {
            // Extract login and customer details from the request
            LoginRequest loginRequest = request.getLoginRequest();
            CustomerRequest customerRequest = request.getCustomerRequest();

            // Save login details
            Login login = new Login();
            login.setUsername(loginRequest.getUsername());
            login.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            login.setStatus(Status.REGISTER);
            login.setRole(Role.Customer);
            Login savedLogin = loginRepo.save(login);

            // Save customer details
            Customer customer = new Customer();
            customer.setName(customerRequest.getName());
            customer.setGender(customerRequest.getGender());
            customer.setPhone(customerRequest.getPhone());
            customer.setZan_id(customerRequest.getZan_id());
            customer.setAddress(customerRequest.getAddress());
            customer.setUserId(savedLogin.getUserID());

            Customer savedCustomer = customerRepo.save(customer);

            return new ResponseEntity<>("Customer Added", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Customer not added", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getCustomerById/{Cust_id}")
    public ResponseEntity<?> getCust(@PathVariable Long  Cust_id){
        try{
            Optional<Customer> customerOptional = customerRepo.findById(Cust_id);
            if (customerOptional.isPresent()) {
                return new ResponseEntity<>(customerOptional, HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Customer not found",HttpStatus.NOT_FOUND);}

        }catch (Exception exception){
            return  new ResponseEntity<>("Error occur",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<?> getAllCustomer() {
        try {
                List<Customer> customerList = customerRepo.findAll();
            if (customerList.isEmpty()) {
                return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(customerList, HttpStatus.OK);}
        } catch (Exception exception) {
            return new ResponseEntity<>("Error Occur",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateCustomer{Cus_id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long Cust_id,@RequestBody Customer customer){
        try {
            if (customerRepo.findById(Cust_id).isPresent()){
                customer.setCust_id(Cust_id);
                Customer customer1 = customerRepo.save(customer);
                return new ResponseEntity<>(customer1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Customer not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Error occur",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteCustomer{Cus_id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long Cus_id){
        try {
            customerRepo.deleteById(Cus_id);
            return new ResponseEntity<>("Customer deleted ",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/changestatus/{custId}")
    public ResponseEntity<?> changeStatus(@PathVariable Long custId) {
        try {
            List<Customer> customerList = customerRepo.findByCustId(custId);
            if (customerList.isEmpty()) {
                return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
            } else {
                List<CustomerResponse> responses = customerList.stream()
                        .map(customer -> {
                            // Assuming you want to change some status on each customer object.
                            // customer.setStatus(newStatus);
                            customerRepo.save(customer); // Save the updated customer back to the repository.
                            return new CustomerResponse(customer);
                        })
                        .collect(Collectors.toList());

                return new ResponseEntity<>(responses, HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Error Occurred", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/customer/userId/{userId}")
    public Optional<Customer> getById(@PathVariable Long userId){
        return customerRepo.findByUserId(userId);
    }


}