package com.eval2.erpnext.model;

public class Fournisseur {
    private String name; // Nom du fournisseur
    private String supplierGroup; // Groupe de fournisseur, si tu veux cette info aussi

    // Constructor
    public Fournisseur(String name, String supplierGroup) {
        this.name = name;
        this.supplierGroup = supplierGroup;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplierGroup() {
        return supplierGroup;
    }

    public void setSupplierGroup(String supplierGroup) {
        this.supplierGroup = supplierGroup;
    }
}
