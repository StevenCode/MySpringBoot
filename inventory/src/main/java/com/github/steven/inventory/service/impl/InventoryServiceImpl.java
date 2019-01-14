package com.github.steven.inventory.service.impl;

import com.github.steven.inventory.api.dto.InventoryDTO;
import com.github.steven.inventory.api.entity.InventoryDO;
import com.github.steven.inventory.api.service.InventoryService;
import com.github.steven.inventory.mapper.InventoryMapper;
import com.steven.transaction.annotation.Transaction;
import com.steven.transaction.common.exception.TransactionRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private final InventoryMapper inventoryMapper;

    @Autowired(required = false)
    public InventoryServiceImpl(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }
    @Override
    @Transaction(destination = "inventory")
    @Transactional(rollbackFor = Exception.class)
    public Boolean decrease(InventoryDTO inventoryDTO) {
        LOGGER.info("==========springcloud调用扣减库存decrease==========");
        final InventoryDO inventoryDO = inventoryMapper.findByProductId(inventoryDTO.getProductId());
        if (inventoryDO.getTotalInventory() < inventoryDTO.getCount()) {
           throw new TransactionRuntimeException("spring cloud inventory-service 库存不足!");
        }
        inventoryDO.setTotalInventory(inventoryDO.getTotalInventory() - inventoryDTO.getCount());
        final int decrease = inventoryMapper.decrease(inventoryDO);
        if (decrease != 1) {
            throw new TransactionRuntimeException("spring cloud inventory-service 库存不足!");

        }
        return true;
    }

    @Override
    public InventoryDO findByProductId(String productId) {
        return inventoryMapper.findByProductId(productId);
    }
}
