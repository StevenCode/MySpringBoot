package com.github.steven.inventory.api.dto;

import lombok.Data;

@Data
public class InventoryDTO {
    private static final long serialVersionUID = 8229355519336565493L;

    private String productId;

    private Integer count;

}
