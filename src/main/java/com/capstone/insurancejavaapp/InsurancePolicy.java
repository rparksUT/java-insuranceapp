package com.capstone.insurancejavaapp;

import java.util.ArrayList;

public class InsurancePolicy {
    private Integer id;
    private String policyNumber;
    private String insuredLastName;
    private Double annualPremium;
    private ArrayList<InsuranceClaim> claims;
    private Double claimsTotal;

    public InsurancePolicy(Integer id, String policyNumber, String insuredLastName, Double annualPremium) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.insuredLastName = insuredLastName;
        this.annualPremium = annualPremium;
        this.claims = new ArrayList<>();
        this.claimsTotal = 0.00d;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    
    public String getInsuredLastName() {
        return insuredLastName;
    }
    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public Double getAnnualPremium() {
        return annualPremium;
    }
    public void setAnnualPremium(Double annualPremium) {
        this.annualPremium = annualPremium;
    }

    public ArrayList<InsuranceClaim> getClaims() {
        return claims;
    }
    public void setClaims(ArrayList<InsuranceClaim> claims) {
        this.claims = claims;
    }

    public Integer getClaimCount() {
        return claims.size();
    }

    public Double getClaimsTotal() {
        return claimsTotal;
    }
    public void setClaimsTotal(Double claimsTotal) {
        this.claimsTotal = claimsTotal;
    }
}
