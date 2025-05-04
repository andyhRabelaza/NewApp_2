package com.eval2.erpnext.model;

import java.util.List;

public class SupplierQuotationResponse {
    private List<SupplierQuotation> data;

    // Getter et Setter
    public List<SupplierQuotation> getData() {
        return data;
    }

    public void setData(List<SupplierQuotation> data) {
        this.data = data;
    }
}
