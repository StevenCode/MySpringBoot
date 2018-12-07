package com.github.steven.inventory.api.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryDO implements Serializable {
    private static final long serialVersionUID = 6957734749389133832L;

    private Integer id;

    private Integer productId;

    private Integer totalInventory;
}
