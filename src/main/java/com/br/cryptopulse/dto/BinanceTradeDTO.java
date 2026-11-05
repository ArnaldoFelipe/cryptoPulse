package com.br.cryptopulse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BinanceTradeDTO(
        @JsonProperty("s") String simbolo,
        @JsonProperty("p") BigDecimal preco,
        @JsonProperty("q") BigDecimal quantidade,
        @JsonProperty("E") Long timestamp
) {}
