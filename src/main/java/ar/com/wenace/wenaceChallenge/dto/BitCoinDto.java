package ar.com.wenace.wenaceChallenge.dto;

import java.math.BigDecimal;

import ar.com.wenace.wenaceChallenge.entity.BitCoin;
import ar.com.wenace.wenaceChallenge.exception.ParseJsonException;
import ar.com.wenace.wenaceChallenge.exception.ServiceFailedException;

public class BitCoinDto extends BitCoin {

	private BigDecimal totalPrice;

	private Long executions;

	private ServiceFailedException serviceEx;

	private ParseJsonException parseJsonEx;

	public BitCoinDto() {
	}

	public BitCoinDto(BigDecimal totalPrice, Long executions) {
		this.totalPrice = totalPrice;
		this.executions = executions;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getExecutions() {
		return executions;
	}

	public void setExecutions(Long executions) {
		this.executions = executions;
	}

	public ServiceFailedException getServiceEx() {
		return serviceEx;
	}

	public void setServiceEx(ServiceFailedException serviceEx) {
		this.serviceEx = serviceEx;
	}

	public ParseJsonException getParseJsonEx() {
		return parseJsonEx;
	}

	public void setParseJsonEx(ParseJsonException parseJsonEx) {
		this.parseJsonEx = parseJsonEx;
	}
}