package ar.com.wenace.wenaceChallenge.interfaces;

import java.sql.Timestamp;

import ar.com.wenace.wenaceChallenge.exception.BadRequestException;
import ar.com.wenace.wenaceChallenge.exception.ParseJsonException;
import ar.com.wenace.wenaceChallenge.exception.ServiceFailedException;

public interface BitCoinInterface {

	/**
	 * Llama al servicio que obtiene el precio del bitcoin
	 * 
	 * @param time
	 *            - tiempo
	 * @return el precio del bitcoin
	 * @throws ServiceFailedException
	 *             el servicio no respondio correctamente
	 * @throws ParseJsonException
	 *             no se pudo parsear el json
	 * @throws BadRequestException
	 *             el request esta mal armado
	 */
	String getBitCoinPrice(Timestamp time) throws ServiceFailedException, ParseJsonException, BadRequestException;

}
