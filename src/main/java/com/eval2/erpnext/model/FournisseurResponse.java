package com.eval2.erpnext.model;

import java.util.List;

public class FournisseurResponse {
    private List<Fournisseur> data; // Liste des fournisseurs

    // Constructor
    public FournisseurResponse(List<Fournisseur> data) {
        this.data = data;
    }

    // Getters and Setters
    public List<Fournisseur> getData() {
        return data;
    }

    public void setData(List<Fournisseur> data) {
        this.data = data;
    }
}
