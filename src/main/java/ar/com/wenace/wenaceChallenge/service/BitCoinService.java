package ar.com.wenace.wenaceChallenge.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.wenace.wenaceChallenge.client.BitCointClient;
import ar.com.wenace.wenaceChallenge.dto.BitCoinDto;
import ar.com.wenace.wenaceChallenge.entity.BitCoin;
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
	public String getBitCoinPrice(Timestamp time) throws ServiceFailedException, ParseJsonException {
		List<Long> listFafa = Arrays.asList(time.getTime());

		listFafa.stream().forEach((p) -> {
			try {
				callBitCoin();
			} catch (ServiceFailedException | ParseJsonException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
			}
		});

		return callBitCoin().getLprice();
	}

	private BitCoinDto callBitCoin() throws ServiceFailedException, ParseJsonException {
		BitCoinDto bitCoin = new BitCoinDto();
		bitCoin = bitCoinClient.callBitCoinService();
		bitCoinRepo.save(new BitCoin(bitCoin));

		return bitCoin;
	}

}
