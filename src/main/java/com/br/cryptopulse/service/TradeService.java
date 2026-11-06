package com.br.cryptopulse.service;

import com.br.cryptopulse.entities.TradeEvent;
import com.br.cryptopulse.repository.TradeEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeEventRepository tradeEventRepository;

    public List<TradeEvent> buscarTradesRecentes(){
        Instant doisMinutosAtras = Instant.now().minus(2, ChronoUnit.MINUTES);
        return tradeEventRepository.findByDataCriacaoAfter(doisMinutosAtras);
    }
}
