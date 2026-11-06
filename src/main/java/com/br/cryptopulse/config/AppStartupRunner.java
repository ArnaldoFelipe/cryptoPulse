package com.br.cryptopulse.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Component
@RequiredArgsConstructor
public class AppStartupRunner {

    private final BinanceWebSocketClient binanceWebSocketClient;
    private final String BINANCE_WS_URL = "wss://stream.binance.com:9443/ws/btcusdt@trade";

    @EventListener(ApplicationReadyEvent.class)
    public void iniciarWebSocketAposBanco(){
        System.out.println("✅ Banco MongoDB pronto! Iniciando conexão com a Binance...");
        WebSocketClient client = new StandardWebSocketClient();
        client.execute(binanceWebSocketClient, BINANCE_WS_URL);
    }
}
