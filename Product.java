package com.example.company;

import javafx.beans.property.*;

public class Product {
    //declare attributes (visibility: protected)
    protected StringProperty productName, customerName, warehouseName, type;
    protected DoubleProperty obtainCost, salePrice, purchasePrice, stockCost;
    protected IntegerProperty customerSalesVolume, warehouseVolume;
    protected Warehouse warehouse;
    protected Customer customer;

    //parameterized constructor
    public Product(String name, double obtainCost, double salePrice, double purchasePrice, double stockCost, String type,
                   Warehouse warehouse, Customer customer){
        setProductName(name);
        setObtainCost(obtainCost);
        setSalePrice(salePrice);
        setPurchasePrice(purchasePrice);
        setStockCost(stockCost);
        setType(type);
        setCustomerName(customer);
        setCustomerSalesVolume(customer);
        setWarehouseName(warehouse);
        setWarehouseVolume(warehouse);
        this.warehouse = warehouse;
        this.customer = customer;
    }

    //name property
    public StringProperty productNameProperty() {
        if (productName == null) {
            productName = new SimpleStringProperty(this, "productName");
        }
        return productName;
    }

    //setter
    public void setProductName(String value) {
        productNameProperty().set(value);
    }

    //warehouseName property
    public StringProperty warehouseNameProperty() {
        if (warehouseName == null) {
            warehouseName = new SimpleStringProperty(this, "warehouseName");
        }
        return warehouseName;
    }

    //setter
    public void setWarehouseName(Warehouse warehouse) {
        warehouseNameProperty().set(warehouse.getName());
    }

    //customerName property
    public StringProperty customerNameProperty() {
        if (customerName == null) {
            customerName = new SimpleStringProperty(this, "customerName");
        }
        return customerName;
    }

    //setter
    public void setCustomerName(Customer customer) {
        customerNameProperty().set(customer.getName());
    }

    //obtainCost property
    public DoubleProperty obtainCostProperty() {
        if (obtainCost == null) {
            obtainCost = new SimpleDoubleProperty(this, "obtainCost");
        }
        return obtainCost;
    }

    //setter
    public void setObtainCost(double value) {
        obtainCostProperty().set(value);
    }

    //salePrice property
    public DoubleProperty salePriceProperty() {
        if (salePrice == null) {
            salePrice = new SimpleDoubleProperty(this, "salePrice");
        }
        return salePrice;
    }

    //setter
    public void setSalePrice(double value) {
        salePriceProperty().set(value);
    }

    //purchasePrice property
    public DoubleProperty purchasePriceProperty() {
        if (purchasePrice == null) {
            purchasePrice = new SimpleDoubleProperty(this, "purchaseProperty");
        }
        return purchasePrice;
    }

    //setter
    public void setPurchasePrice(double value) {
        purchasePriceProperty().set(value);
    }

    //stockCost property
    public DoubleProperty stockCostProperty() {
        if (stockCost == null) {
            stockCost = new SimpleDoubleProperty(this, "stockCost");
        }
        return stockCost;
    }

    //setter
    public void setStockCost(double value) {
        stockCostProperty().set(value);
    }

    //typeIndicator property
    public StringProperty typeProperty() {
        if (type == null) {
            type = new SimpleStringProperty(this, "typeIndicator");
        }
        return type;
    }

    //setter
    public void setType(String value) {
        typeProperty().set(value);
    }

    //customerSalesVolume property
    public IntegerProperty customerSalesVolumeProperty() {
        if (customerSalesVolume == null) {
            customerSalesVolume = new SimpleIntegerProperty(this, "customerSalesVolume");
        }
        return customerSalesVolume;
    }

    //setter
    public void setCustomerSalesVolume(Customer customer) {
        customerSalesVolumeProperty().set(customer.getSalesVolume());
    }

    //warehouseVolume property
    public IntegerProperty warehouseVolumeProperty() {
        if (warehouseVolume == null) {
            warehouseVolume = new SimpleIntegerProperty(this, "warehouseVolume");
        }
        return warehouseVolume;
    }

    //setter
    public void setWarehouseVolume(Warehouse warehouse) {
        warehouseVolumeProperty().set(warehouse.getVolume());
    }

    //getter
    public String getType(){
        return type.get();
    }

    //toString method
    public String toString(){
        return productName.get() + "," + customerName.get() + "," + customerSalesVolume.get() + "," +
                warehouseName.get() + "," + warehouseVolume.get() + "," + purchasePrice.get() + "," +
                stockCost.get() + "," + salePrice.get() + "\n";
    }
}
