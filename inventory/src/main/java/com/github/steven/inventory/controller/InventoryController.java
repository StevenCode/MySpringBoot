package com.github.steven.inventory.controller;

import com.github.steven.inventory.api.entity.InventoryDO;
import com.github.steven.inventory.api.dto.InventoryDTO;
import com.github.steven.inventory.api.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping("/decrease")
    public Boolean decrease(@RequestBody InventoryDTO inventoryDTO ) {
        return inventoryService.decrease(inventoryDTO);
    }

    @RequestMapping("/findByProductId")
    public InventoryDO findByProductId(@RequestParam("productId") String productId) {
        return inventoryService.findByProductId(productId);
    }
}
