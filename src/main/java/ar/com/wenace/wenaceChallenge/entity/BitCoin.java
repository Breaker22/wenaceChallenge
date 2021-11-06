package ar.com.wenace.wenaceChallenge.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.wenace.wenaceChallenge.dto.BitCoinDto;

@Entity
@Table(name = "bitcoin")
public class BitCoin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String lprice;

	private String curr1;

	private String curr2;
	
	public BitCoin() {		
	}

	public BitCoin(BitCoinDto bitCoin) {
		this.lprice = bitCoin.getLprice();
		this.curr1 = bitCoin.getCurr1();
		this.curr2 = bitCoin.getCurr2();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLprice() {
		return lprice;
	}

	public void setLprice(String lprice) {
		this.lprice = lprice;
	}

	public String getCurr1() {
		return curr1;
	}

	public void setCurr1(String curr1) {
		this.curr1 = curr1;
	}

	public String getCurr2() {
		return curr2;
	}

	public void setCurr2(String curr2) {
		this.curr2 = curr2;
	}
}