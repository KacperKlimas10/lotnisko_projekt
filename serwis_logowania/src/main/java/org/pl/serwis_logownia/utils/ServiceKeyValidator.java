package org.pl.serwis_logownia.utils;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ServiceKeyValidator {

    private static final Map<String, String> validKeys = Map.of(
            "ServiceA", "12345-abcde-67890",
            "ServiceB", "67890-fghij-12345"
    );

    public boolean isValid(String serviceKey) {
        return validKeys.containsValue(serviceKey);
    }
}

