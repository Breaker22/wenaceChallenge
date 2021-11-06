package ar.com.wenace.wenaceChallenge.dto;

import java.sql.Timestamp;

import ar.com.wenace.wenaceChallenge.entity.BitCoin;

public class BitCoinDto extends BitCoin {

	private Timestamp timeRequested;
	
	public BitCoinDto() {		
	}
	
	public BitCoinDto(Timestamp timeRequested) {
		this.timeRequested = timeRequested;
	}

	public Timestamp getTimeRequested() {
		return timeRequested;
	}

	public void setTimeRequested(Timestamp timeRequested) {
		this.timeRequested = timeRequested;
	}
}
