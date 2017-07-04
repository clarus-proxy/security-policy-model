package eu.clarussecure.datamodel;

import eu.clarussecure.datamodel.types.Usage;
import java.io.Serializable;

import java.util.Set;
import java.util.HashSet;

public class Policy implements Serializable {

    // The name of the fields MUST match the keys of the Json
    private int policyId;
    private String policyName;
    private Usage dataUsage;
    private Endpoint endpoint;
    private Protection protection;
    private Set<PolicyAttribute> attributes;

    public Policy(int id, String name) { // The commented fields might pose some problems
        this.policyId = id;
        this.policyName = name;
        this.dataUsage = Usage.COMPUTE;
        this.endpoint = new Endpoint();
        this.protection = new Protection();
        this.attributes = new HashSet<>();
    }

    public int getPolicyID() {
        return this.policyId;
    }

    public void setPolicyID(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public Usage getDataUsage() {
        return this.dataUsage;
    }

    public void setDataUsage(Usage us) {
        this.dataUsage = us;
    }

    public Endpoint getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(Endpoint e) {
        this.endpoint = e;
    }

    public Protection getProtection() {
        return this.protection;
    }

    public void setProtection(Protection p) {
        this.protection = p;
    }

    public Set<PolicyAttribute> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Set<PolicyAttribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(PolicyAttribute pa) {
        this.attributes.add(pa);
    }

    public void deleteAttribute(PolicyAttribute pa) {
        this.attributes.remove(pa);
    }

    @Override
    public String toString() {
        String s = "Policy: ID: " + this.policyId + ", name: " + this.policyName + ", " + this.endpoint + ", "
                + this.protection;
        return s;
    }

    public boolean checkPolicyIntegrity() {
        // This method should check if the policy is completely specified or not.
        // An incomplete policy SHOULD NOT be capable of deploying it
        boolean valid = false;

        // A policy must have an Endpoint
        valid = (this.endpoint != null) && valid;

        // A policy must have a Usage
        valid = (this.dataUsage != null) && valid;

        // A policy must have an protection module
        valid = (this.protection.isValid()) && valid;

        return valid;
    }
}