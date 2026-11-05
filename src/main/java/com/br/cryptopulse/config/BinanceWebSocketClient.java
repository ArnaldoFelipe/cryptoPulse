package com.br.cryptopulse.config;

import com.br.cryptopulse.dto.BinanceTradeDTO;
import com.br.cryptopulse.entities.TradeEvent;
import com.br.cryptopulse.repository.TradeEventRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;


import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BinanceWebSocketClient extends TextWebSocketHandler {

    private final String BINANCE_WS_URL = "wss://stream.binance.com:9443/ws/btcusdt@trade";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TradeEventRepository tradeEventRepository;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage text){

        BinanceTradeDTO dto = objectMapper.readValue(text.getPayload(), BinanceTradeDTO.class);

        try {
            TradeEvent event = TradeEvent.builder()
                    .simbolo(dto.simbolo())
                    .preco(dto.preco())
                    .quantidade(dto.quantidade())
                    .timestamp(dto.timestamp())
                    .build();

            tradeEventRepository.save(event);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostConstruct
    public void iniciarConexao(){
        WebSocketClient client = new StandardWebSocketClient();
        client.execute(this, BINANCE_WS_URL);
    }

}
