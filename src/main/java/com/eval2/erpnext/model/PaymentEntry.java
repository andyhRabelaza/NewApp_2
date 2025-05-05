package com.eval2.erpnext.model;

import java.util.List;

public class PaymentEntry {

    private int docstatus;
    private String doctype;
    private String payment_type;
    private String party_type;
    private String party;
    private String paid_from;
    private String paid_to;
    private double paid_amount;
    private double received_amount;
    private String reference_no;
    private String reference_date;
    private List<Reference> references;

    // Getters et setters

    public int getDocstatus() {
        return docstatus;
    }

    public void setDocstatus(int docstatus) {
        this.docstatus = docstatus;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getParty_type() {
        return party_type;
    }

    public void setParty_type(String party_type) {
        this.party_type = party_type;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPaid_from() {
        return paid_from;
    }

    public void setPaid_from(String paid_from) {
        this.paid_from = paid_from;
    }

    public String getPaid_to() {
        return paid_to;
    }

    public void setPaid_to(String paid_to) {
        this.paid_to = paid_to;
    }

    public double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public double getReceived_amount() {
        return received_amount;
    }

    public void setReceived_amount(double received_amount) {
        this.received_amount = received_amount;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public String getReference_date() {
        return reference_date;
    }

    public void setReference_date(String reference_date) {
        this.reference_date = reference_date;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    // Classe imbriquée
    public static class Reference {
        private String reference_doctype;
        private String reference_name;
        private double allocated_amount;

        public String getReference_doctype() {
            return reference_doctype;
        }

        public void setReference_doctype(String reference_doctype) {
            this.reference_doctype = reference_doctype;
        }

        public String getReference_name() {
            return reference_name;
        }

        public void setReference_name(String reference_name) {
            this.reference_name = reference_name;
        }

        public double getAllocated_amount() {
            return allocated_amount;
        }

        public void setAllocated_amount(double allocated_amount) {
            this.allocated_amount = allocated_amount;
        }
    }
}
