package com.store.inventory.api;

import org.springframework.stereotype.Service;

@Service
public class StockAlertListenerImpl implements StockAlertListener{
    @Override
    public void onLowStock(String sku, int availableUnits) {
        // Implementar envío de correo cpn el código del producto (skr) y la cantidad existente en inventario
    }
}
