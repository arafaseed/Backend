package com.example.easybusiness.Controler;

import com.example.easybusiness.Repository.PaymentRepo;
import com.example.easybusiness.model.License;
import com.example.easybusiness.model.Payment;
import com.example.easybusiness.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentAPI {

    @Autowired
    public PaymentRepo paymentRepo;

    @PostMapping("/addPayment")
    public ResponseEntity<?> AddPayment (@RequestBody Payment payment){
        try{
            Payment payment1 = paymentRepo.save(payment);
            return new ResponseEntity<>("Payment successful", HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Payment unsuccessful",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByID{P_id}")
    public ResponseEntity<?> getPayment(@PathVariable int P_id){
        try{
            Optional<Payment> optionalPayment  = paymentRepo.findById(P_id);
            if (optionalPayment.isPresent()){
                return new ResponseEntity<>(optionalPayment,HttpStatus.OK);
            }else {
                return  new ResponseEntity<>("Payment record not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return  new ResponseEntity<>("Error occur",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllPayment")
    public ResponseEntity<?> getAllPayment() {
        try {
            List<Payment> paymentList = paymentRepo.findAll();
            if (paymentList.isEmpty()) {
                return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(paymentList, HttpStatus.OK);}
        } catch (Exception exception) {
            return new ResponseEntity<>("Error Occur",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updatePayment{P_id}")
    public ResponseEntity<?> updatePayment(@PathVariable int P_id,@RequestBody Payment payment){
        try {
            if (paymentRepo.findById(P_id).isPresent()){
                payment.setP_id(P_id);
                Payment payment1 = paymentRepo.save(payment);
                return new ResponseEntity<>(payment1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Payment not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Error occur",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deletePayment/{P_id}")
    public ResponseEntity<?> deletePayment(@PathVariable int P_id){
        try {
            paymentRepo.deleteById(P_id);
            return new ResponseEntity<>("Payment deleted ",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Error occur",HttpStatus.BAD_REQUEST);
        }
    }
}
