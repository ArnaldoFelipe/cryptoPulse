package com.br.cryptopulse.repository;

import com.br.cryptopulse.entities.TradeEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface TradeEventRepository extends MongoRepository<TradeEvent, String> {
    List<TradeEvent> findByDataCriacaoAfter(Instant dataLimite);
}
