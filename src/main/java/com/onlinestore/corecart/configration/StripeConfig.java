package com.onlinestore.corecart.configration;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.api.secret_key}")
    String apiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = apiKey;
    }


}
