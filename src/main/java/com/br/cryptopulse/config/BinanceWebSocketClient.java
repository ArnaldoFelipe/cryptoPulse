package com.br.cryptopulse.config;

import com.br.cryptopulse.dto.BinanceTradeDTO;
import com.br.cryptopulse.entities.TradeEvent;
import com.br.cryptopulse.repository.TradeEventRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class BinanceWebSocketClient extends TextWebSocketHandler {

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
            System.err.println("Erro ao salvar trade: " + e.getMessage());
        }
    }

}
