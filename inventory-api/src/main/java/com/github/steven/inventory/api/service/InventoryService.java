package com.github.steven.inventory.api.service;

import com.github.steven.inventory.api.dto.InventoryDTO;
import com.github.steven.inventory.api.entity.InventoryDO;

public interface InventoryService {
    Boolean decrease(InventoryDTO inventoryDTO);

    InventoryDO findByProductId(String productId);
}
