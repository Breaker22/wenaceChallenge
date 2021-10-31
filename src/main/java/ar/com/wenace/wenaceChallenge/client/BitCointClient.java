package ar.com.wenace.wenaceChallenge.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.wenace.wenaceChallenge.dto.BitCoinDto;
import ar.com.wenace.wenaceChallenge.exception.ParseJsonException;
import ar.com.wenace.wenaceChallenge.exception.ServiceFailedException;

@Component
public class BitCointClient {

	@Value("${bitcoin.service.url}")
	private String bitCoinUrl;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger log = LoggerFactory.getLogger(BitCointClient.class);

	/**
	 * Llama al servicio de bitCoin
	 * 
	 * @return la respuesta del servicio
	 * @throws ServiceFailedException el servicio no respondio correctamente
	 * @throws ParseJsonException     no se pudo parsear el json
	 */
	public BitCoinDto callBitCoinService() throws ServiceFailedException, ParseJsonException {
		String response = new String();

		try {
			response = restTemplate.exchange(bitCoinUrl, HttpMethod.GET, null, String.class).getBody();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new ServiceFailedException(ex.getMessage());
		}

		log.info(response);

		ObjectMapper objectMapper = new ObjectMapper();
		BitCoinDto bitCointResponse = new BitCoinDto();

		try {
			bitCointResponse = objectMapper.readValue(response, BitCoinDto.class);
		} catch (IOException ex) {
			log.error(ex.getMessage(), ex);
			throw new ParseJsonException(ex.getMessage());
		}

		return bitCointResponse;
	}

}
