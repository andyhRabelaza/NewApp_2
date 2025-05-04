package com.eval2.erpnext.model;

public class SupplierQuotation {
    private String name; // Le nom de la demande de devis
    private String supplier; // Le fournisseur associé
    private String creation; // La date de création de la demande

    // Getter et Setter
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
}
