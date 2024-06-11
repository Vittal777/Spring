package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bo.Train;
import com.trainservice.ServiceClass;

@RestController
public class TrainController {

	@Autowired
	ServiceClass service;
	
	@PostMapping("/")
	public ResponseEntity<String> regTrain(@RequestBody Train train) {
		try {
			String res = service.registerTrain(train);
			return new ResponseEntity<String>(res,HttpStatus.CREATED);
		}catch(Exception e) {
			String err = e.getMessage();
			return new ResponseEntity<String>(err,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/regAll")
	public ResponseEntity<String> regAllTrains(@RequestBody List<Train> train){
		try {
			String res = service.registerAllTrains(train);
			return new ResponseEntity<String>(res,HttpStatus.OK);
		}catch(Exception e) {
			String res = e.getMessage();
			return new ResponseEntity<String>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/del/{trainNo}")
	public ResponseEntity<String> delTrainData(@PathVariable String trainNo){
		try {
			String res = service.deleteBytrainNo(trainNo);
			return new ResponseEntity<String>(res,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Removing failed, Try again.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/by/{trainNo}")
	public ResponseEntity<String> findByTrainNo(@PathVariable String trainNo){
		try {
			Train res = service.findTrainByTrainNo(trainNo);
			return new ResponseEntity<String>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Not Found",HttpStatus.EXPECTATION_FAILED);
		}
	}
	 @GetMapping("/test/{num}")
	    public ResponseEntity<String> checkTrainNo(@PathVariable String num) {
	        boolean isExists = service.isTrainNoExists(num);
	        if (isExists) {
	            return new ResponseEntity<>("Train with Number " + num + " found", HttpStatus.FOUND);
	        } else {
	            return new ResponseEntity<>("Train with given Number " + num + " not found", HttpStatus.NOT_FOUND);
	        }
	    }
}
