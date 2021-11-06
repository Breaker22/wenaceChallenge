package ar.com.wenace.wenaceChallenge.controller;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.wenace.wenaceChallenge.exception.BadRequestException;
import ar.com.wenace.wenaceChallenge.exception.ParseJsonException;
import ar.com.wenace.wenaceChallenge.exception.ServiceFailedException;
import ar.com.wenace.wenaceChallenge.service.BitCoinService;

@RestController
public class BitCoinController {

	@Autowired
	private BitCoinService bitCoinService;

	@GetMapping("/price")
	public ResponseEntity<String> getPrice(@Valid @RequestParam("time") Timestamp time)
			throws ServiceFailedException, ParseJsonException, BadRequestException {
		return ResponseEntity.ok(bitCoinService.getBitCoinPrice(Arrays.asList(time)));
	}

	@GetMapping("/prices")
	public ResponseEntity<String> getPrices(@Valid @RequestParam("time") Timestamp time,
			@Valid @RequestParam("time2") Timestamp time2)
			throws ServiceFailedException, ParseJsonException, BadRequestException {
		return ResponseEntity.ok(bitCoinService.getBitCoinPrice(Arrays.asList(time, time2)));
	}

	@ExceptionHandler(ServiceFailedException.class)
	private ResponseEntity<String> serviceErrorEx() {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("El servicio de bitcoin fallo");
	}

	@ExceptionHandler(ParseJsonException.class)
	private ResponseEntity<String> parseJsonEx() {
		return ResponseEntity.badRequest().body("La fecha debe tener el formato de yyyy-MM-dd hh:mm:ss");
	}

	@ExceptionHandler(BadRequestException.class)
	private ResponseEntity<String> badRequestEx(BadRequestException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}