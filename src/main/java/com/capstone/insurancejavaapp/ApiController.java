package com.capstone.insurancejavaapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/policies")
public class ApiController {
    @GetMapping
    public Iterable<InsurancePolicy> getPolicies() {
        return Database.policies;
    }

    @GetMapping("/{policyNumber}")
    public Optional<InsurancePolicy> getPolicyByNumber(@PathVariable String policyNumber) {
        for (InsurancePolicy policy : Database.policies) {
            if (policy.getPolicyNumber().equals(policyNumber)) {
                return Optional.of(policy);
            }
        }

        return Optional.empty();
    }

    @GetMapping("{policyNumber}/totals")
    public Optional<List<Map<String, Object>>> getPolicyTotals(@PathVariable String policyNumber) {
        Double total = 0.00d;
        List<Map<String, Object>> policies = new ArrayList<>();
        for (InsurancePolicy policy : Database.policies) {
            if (policy.getPolicyNumber().equals(policyNumber)) {
                for (InsuranceClaim claim : policy.getClaims()) {
                    total += claim.getAmount();
                }
                policy.setClaimsTotal(total);
                Map<String, Object> policyMap = mapPolicy(policy);
                policies.add(policyMap);
            }
        }

        if (policies.size() != 0) {
            return Optional.of(policies);
        }

        return Optional.empty();
    }

    @GetMapping("/claims")
    public Iterable<Map<String, Object>> getClaims() {
        List<Map<String, Object>> claims = new ArrayList<>();
        for (InsurancePolicy policy : Database.policies) {
            for (InsuranceClaim claim : policy.getClaims()) {
                Map<String, Object> claimMap = mapClaim(claim);
                if(!claims.contains(claimMap)) {
                    claims.add(claimMap);
                }
            }
        }

        return claims;
    }

    @GetMapping("/claims/{claimNumber}")
    public Optional<Map<String, Object>> getClaimByNumber(@PathVariable String claimNumber) {
        for (InsurancePolicy policy : Database.policies) {
            for (InsuranceClaim claim : policy.getClaims()) {
                if (claim.getClaimNumber().equals(claimNumber)) {
                    return Optional.of(mapClaim(claim));
                }
            }
        }

        return Optional.empty();
    }

    @GetMapping("/claims/paid")
    public Iterable<InsurancePolicy> getPaidClaims() {
        List<InsurancePolicy> policies = new ArrayList<>();
        for (InsurancePolicy policy : Database.policies) {
            for (InsuranceClaim claim : policy.getClaims()) {
                if(claim.getIsPaid()) {
                    policies.add(policy);
                    break;
                }
            }
        }

        return policies;
    }

    private Map<String, Object> mapClaim(InsuranceClaim claim) {
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("id", claim.getId());
        claimMap.put("claimNumber", claim.getClaimNumber());
        claimMap.put("isPaid", claim.getIsPaid());
        claimMap.put("amount", claim.getAmount());
        return claimMap;
    }

    private Map<String, Object> mapPolicy(InsurancePolicy policy) {
        Map<String, Object> newPolicy = new HashMap<>();
        newPolicy.put("policyNumber", policy.getPolicyNumber());
        newPolicy.put("annualPremium", policy.getAnnualPremium());
        newPolicy.put("claimsTotals", policy.getClaimsTotal());
        return newPolicy;
    }
}
