package com.github.steven.order.client;

import com.github.steven.inventory.api.dto.InventoryDTO;
import com.github.steven.inventory.api.entity.InventoryDO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory-service")
public interface InventoryClient {

    /**
     * 库存扣减
     *
     * @param inventoryDTO
     * @return
     */
    @RequestMapping("/inventory-service/inventory/decrease")
    Boolean decrese(@RequestBody InventoryDTO inventoryDTO);

    @RequestMapping("/inventory-service/inventory/findByProductId")
    InventoryDO findByProductId(@RequestParam("productId") String productId);
}
