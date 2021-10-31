package ar.com.wenace.wenaceChallenge.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.wenace.wenaceChallenge.client.BitCointClient;
import ar.com.wenace.wenaceChallenge.dto.BitCoinDto;
import ar.com.wenace.wenaceChallenge.entity.BitCoin;
import ar.com.wenace.wenaceChallenge.exception.BadRequestException;
import ar.com.wenace.wenaceChallenge.exception.ParseJsonException;
import ar.com.wenace.wenaceChallenge.exception.ServiceFailedException;
import ar.com.wenace.wenaceChallenge.interfaces.BitCoinInterface;
import ar.com.wenace.wenaceChallenge.repository.BitCoinRepository;

@Service
public class BitCoinService implements BitCoinInterface {

	@Autowired
	private BitCoinRepository bitCoinRepo;

	@Autowired
	private BitCointClient bitCoinClient;

	private static final Logger log = LoggerFactory.getLogger(BitCoinService.class);

	@Override
	public String getBitCoinPrice(Timestamp time) throws ServiceFailedException, ParseJsonException, BadRequestException {
		validateRequest(time);
		
		for (Long x = Calendar.getInstance().getTimeInMillis(); x <= time.getTime(); x++) {
			callBitCoin();

			x+=10000;
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
			}			
		}

		return callBitCoin().getLprice();
	}
	
	/**
	 * Valida el request
	 * 
	 * @param time - tiempo
	 * @throws BadRequestException
	 */
	private void validateRequest(Timestamp time) throws BadRequestException {
		if(time == null) {
			throw new BadRequestException("Debe ingresar el parametro time");
		}
		
		if(time.getTime() < Calendar.getInstance().getTimeInMillis()) {
			throw new BadRequestException("La fecha debe ser mayor a hoy");
		}
	}

	/**
	 * Llama al servicio de bitcoin
	 * 
	 * @return la respuesta del servicio
	 * @throws ServiceFailedException
	 * @throws ParseJsonException
	 */
	private BitCoinDto callBitCoin() throws ServiceFailedException, ParseJsonException {
		BitCoinDto bitCoin = new BitCoinDto();
		bitCoin = bitCoinClient.callBitCoinService();
		bitCoinRepo.save(new BitCoin(bitCoin));

		return bitCoin;
	}
}