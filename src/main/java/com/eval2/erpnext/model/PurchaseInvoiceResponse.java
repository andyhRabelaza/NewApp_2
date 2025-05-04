package com.eval2.erpnext.model;

import java.util.List;

public class PurchaseInvoiceResponse {

    private List<PurchaseInvoice> data;

    // Getter et Setter
    public List<PurchaseInvoice> getData() {
        return data;
    }

    public void setData(List<PurchaseInvoice> data) {
        this.data = data;
    }
}
