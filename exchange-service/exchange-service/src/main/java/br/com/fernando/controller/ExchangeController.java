package br.com.fernando.controller;

import br.com.fernando.environment.InstanceInformationService;
import br.com.fernando.model.Exchange;
import br.com.fernando.repository.ExchangeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Tag(name = "Exchange Endpoint")
@RestController
@RequestMapping("exchange-service")
public class ExchangeController {

    private Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    ExchangeRepository repository;

    //http://localhost:8000/exchange-service/5/USD/BRL
    @Operation(summary = "Get an exchange from amount to currency")
    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(
           @PathVariable BigDecimal amount,
           @PathVariable String from,
           @PathVariable String to){

            logger.info("getExchange is called with -> {}, {} and {}", amount, from, to);

            Exchange exchange = repository.findByFromAndTo(from, to);

            if(exchange == null) throw new RuntimeException("Currency Unsupported");

            BigDecimal conversionFactor = exchange.getConversionFactor();
            BigDecimal convertedValue = conversionFactor.multiply(amount);
            exchange.setConvertedValue(convertedValue);
            exchange.setEnvironment("PORT: "+informationService.retrieveServerPort());

            return exchange;
    }

    //http://localhost:8000/exchange-service/5/USD/BRL
    /*@GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to){

        return new Exchange(1L, from, to, BigDecimal.ONE,
                BigDecimal.ONE, "PORT: "+informationService.retrieveServerPort());
    }*/
}
