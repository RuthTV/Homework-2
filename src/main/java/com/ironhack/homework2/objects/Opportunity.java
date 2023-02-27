package com.ironhack.homework2.objects;

import com.ironhack.homework2.enums.Product;
import com.ironhack.homework2.enums.Status;

public class Opportunity {
    private static int opportunityIdCounter = 200000;
    private int id;
    private Product product;
    private int quantity;
    private Contact decisionMaker;
    private Status status;

    public Opportunity(Product product, int quantity, Contact decisionMaker) {
        this.id = opportunityIdCounter;
        opportunityIdCounter++;
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = Status.OPEN;
    }

    //getters and setters needed
    public Integer getId() {
        return id;
    }

    public static void setOpportunityIdCounter(int opportunityIdCounter) {
        Opportunity.opportunityIdCounter = opportunityIdCounter;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    //Strings to Print
    public String showOpportunity() {
        return ("id: " + id + " -> decision maker: " + decisionMaker.getName());
    }

    @Override
    public String toString() {
        return "=== Opportunity " + id + " ===\n"+
                "- product: " + product + "\n"+
                "- trucks quantity: " + quantity + "\n"+
                "- decision maker: " + decisionMaker.printDecisionMaker() +
                "- status: " + status + "\n";
    }
}
