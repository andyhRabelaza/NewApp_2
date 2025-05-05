package com.eval2.erpnext.model;

import java.util.List;

public class SupplierQuotationUpdateRequest {
    private List<ItemUpdate> items;

    public List<ItemUpdate> getItems() {
        return items;
    }

    public void setItems(List<ItemUpdate> items) {
        this.items = items;
    }

    public static class ItemUpdate {
        private String item_name;
        private String item_code;
        private Double qty;
        private Double rate;

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public Double getQty() {
            return qty;
        }

        public void setQty(Double qty) {
            this.qty = qty;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public String getItem_code() {
            return item_code;
        }

        public void setItem_code(String item_code) {
            this.item_code = item_code;
        }

        // Surcharge de la méthode toString() pour afficher l'objet ItemUpdate
        @Override
        public String toString() {
            return "ItemUpdate{" +
                    "item_name='" + item_name + '\'' +
                    "item_code='" + item_code + '\'' +
                    ", qty=" + qty +
                    ", rate=" + rate +
                    '}';
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SupplierQuotationUpdateRequest{items=[");

        // Vérifie si la liste des items n'est pas vide avant de l'afficher
        if (items != null && !items.isEmpty()) {
            for (ItemUpdate item : items) {
                sb.append(item.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2); // Enlever la dernière virgule et espace
        }
        sb.append("]}");
        return sb.toString();
    }
}
