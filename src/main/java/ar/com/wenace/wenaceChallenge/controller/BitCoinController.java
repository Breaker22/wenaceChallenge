package ar.com.wenace.wenaceChallenge.controller;

import java.sql.Timestamp;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.wenace.wenaceChallenge.exception.ParseJsonException;
import ar.com.wenace.wenaceChallenge.exception.ServiceFailedException;
import ar.com.wenace.wenaceChallenge.service.BitCoinService;

@RestController
public class BitCoinController {

	@Autowired
	private BitCoinService bitCoinService;

	@GetMapping("/getPrice")
	public ResponseEntity<String> getPrice(@PathParam(value = "time") Timestamp time)
			throws ServiceFailedException, ParseJsonException {
		return ResponseEntity.ok(bitCoinService.getBitCoinPrice(time));
	}
	
	
	@ExceptionHandler(ServiceFailedException.class)
	public ResponseEntity<Object> serviceErrorEx() {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}
	
	@ExceptionHandler(ParseJsonException.class)
	public ResponseEntity<Object> parseJsonEx() {
		return ResponseEntity.badRequest().body("La fecha debe tener el formato de yyyy-MM-dd hh:mm:ss");
	}
}