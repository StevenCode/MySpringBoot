package com.github.steven.inventory.mapper;

import com.github.steven.inventory.api.entity.InventoryDO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface InventoryMapper {

    @Update("update inventory set total_inventory= #{totalInventory}" +
            " where product_id =#{productId} and total_inventory>0")
    int decrease(InventoryDO inventoryDO);

    @Select("select * from inventory where product_id = #{productId}")
    InventoryDO findByProductId(String productId);
}
