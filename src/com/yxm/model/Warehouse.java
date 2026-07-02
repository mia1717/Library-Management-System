package com.yxm.model;

/**
 * 仓库实体
 */
public class Warehouse {

    private int id; // 编号
    private String warehouseName; // 仓库名称
    private String warehouseLocation; // 仓库位置
    private int warehouseCapacity; // 仓库容量
    private int currentStock; // 当前库存总量

    public Warehouse() {
        super();
    }

    public Warehouse(String warehouseName) { // 添加的构造器
        this.warehouseName = warehouseName;
    }

    public Warehouse(String warehouseName, String warehouseLocation, int warehouseCapacity, int currentStock) {
        super();
        this.warehouseName = warehouseName;
        this.warehouseLocation = warehouseLocation;
        this.warehouseCapacity = warehouseCapacity;
        this.currentStock = currentStock;
    }

    public Warehouse(int id, String warehouseName, String warehouseLocation, int warehouseCapacity, int currentStock) {
        super();
        this.id = id;
        this.warehouseName = warehouseName;
        this.warehouseLocation = warehouseLocation;
        this.warehouseCapacity = warehouseCapacity;
        this.currentStock = currentStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public int getWarehouseCapacity() {
        return warehouseCapacity;
    }

    public void setWarehouseCapacity(int warehouseCapacity) {
        this.warehouseCapacity = warehouseCapacity;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
}