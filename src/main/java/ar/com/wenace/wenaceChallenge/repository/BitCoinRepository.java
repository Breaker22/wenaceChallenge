package ar.com.wenace.wenaceChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.wenace.wenaceChallenge.entity.BitCoin;

@Repository
public interface BitCoinRepository extends JpaRepository<BitCoin, Long> {

}
