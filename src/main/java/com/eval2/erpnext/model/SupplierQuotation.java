package com.eval2.erpnext.model;

import java.util.List;

public class SupplierQuotation {
    private String name;
    private String supplier;
    private String creation;
    private List<SupplierQuotationItem> items;

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public List<SupplierQuotationItem> getItems() {
        return items;
    }

    public void setItems(List<SupplierQuotationItem> items) {
        this.items = items;
    }
}
