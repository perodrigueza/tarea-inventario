package com.store.inventory;

import com.store.inventory.api.InventoryService;
import com.store.inventory.api.StockAlertListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.Clock;

/**
 * Entry point used by our automated tests. Keep this signature exactly as it is,
 * and build your implementation here.
 */
@SpringBootApplication(
    scanBasePackages = {
            "com.store.inventory"
    }
)
public final class Inventory {

    private Inventory() {
    }

    public static InventoryService create(Clock clock, StockAlertListener alertListener) {

        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        SpringApplication.run(Inventory.class, args);
    }
}
