package com.br.cryptopulse.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document()
public class TradeEvent {

    @Id
    private String id;

    private String simbolo;
    private BigDecimal preco;
    private BigDecimal quantidade;
    private Long timestamp;
}
