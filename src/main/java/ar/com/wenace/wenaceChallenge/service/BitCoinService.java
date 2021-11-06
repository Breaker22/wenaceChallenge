package ar.com.wenace.wenaceChallenge.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
	public String getBitCoinPrice(List<Timestamp> times)
			throws ServiceFailedException, ParseJsonException, BadRequestException {
		validateRequest(times);
		BitCoinDto response = new BitCoinDto(times.get(0));

		LongStream.iterate(Calendar.getInstance().getTimeInMillis(), y -> y + 10000)
				.limit(response.getTimeRequested().getTime()).forEach(x -> {
					try {
						String lPrice = callBitCoin().getLprice();
						response.setLprice(lPrice);

						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							log.error(e.getMessage(), e);
						}
					} catch (ServiceFailedException | ParseJsonException e) {
						e.printStackTrace();
					}
				});

//
//		for (Long x = Calendar.getInstance().getTimeInMillis(); x <= times.getTime(); x++) {
//			response = callBitCoin();
//
//			x += 10000;
//
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				log.error(e.getMessage(), e);
//			}
//		}

		return response.getLprice();
	}

	/**
	 * Valida el request
	 * 
	 * @param times - lista de tiempos
	 * @throws BadRequestException
	 */
	private void validateRequest(List<Timestamp> times) throws BadRequestException {
		if (CollectionUtils.isEmpty(times)) {
			throw new BadRequestException("Debe ingresar el parametro time");
		}

		times.get(0).getTime();
		boolean isTimeOk = times.stream()
				.anyMatch(time -> Long.compare(time.getTime(), Calendar.getInstance().getTimeInMillis()) > 0);

		if (!isTimeOk) {
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