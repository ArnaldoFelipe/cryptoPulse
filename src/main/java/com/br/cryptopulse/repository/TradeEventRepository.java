package com.br.cryptopulse.repository;

import com.br.cryptopulse.entities.TradeEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeEventRepository extends MongoRepository<TradeEvent, String> {
}
