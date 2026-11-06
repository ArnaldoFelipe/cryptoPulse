package com.br.cryptopulse.controller;

import com.br.cryptopulse.entities.TradeEvent;
import com.br.cryptopulse.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @GetMapping
    public List<TradeEvent> listarTrades(){
        return  tradeService.buscarTradesRecentes();
    }
}
