package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Opportunity;
import com.ironhack.homework2.enums.Product;

import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class CreatingOpportunity {
    Scanner scanner = new Scanner(System.in);

    //CREATION METHODS
    public Opportunity creatingOpportunity(Contact contact){
        Opportunity opportunity = new Opportunity(selectProduct(), writeQuantity(), contact);
        System.out.println("New OPPORTUNITY created with id " + opportunity.getId());
        return opportunity;
    }

    public List<Opportunity> creatingOpportunityList(Opportunity opportunity, List<Opportunity> opportunityList){
        opportunityList.clear();
        opportunityList.add(opportunity);

        return opportunityList;
    }

    public Map<Integer, Opportunity> creatingOpportunityMap(Opportunity opportunity, Map<Integer,
            Opportunity> opportunityMap){
        opportunityMap.put(opportunity.getId(), opportunity);

        return opportunityMap;
    }

    //READ INPUT METHODS
    public Product selectProduct() {
        System.out.println("Please write the product type: HYBRID, FLATBED, or BOX");
        scanner = new Scanner(System.in);
        String product = scanner.nextLine();
        product = product.trim().toUpperCase();
        while (!selectProductBoolean(product)){
            System.out.println("Please write a correct product type");
            product = scanner.nextLine();
            product = product.trim().toUpperCase();
        }
        return Product.valueOf(product);
    }

    public int writeQuantity() {
        System.out.println("Please write the number of trucks being considered for purchase");
        scanner = new Scanner(System.in);
        String quantity = scanner.nextLine();
        quantity = quantity.trim();
        while (!writeQuantityBoolean(quantity)){
            System.out.println("Please write a correct quantity");
            quantity = scanner.nextLine();
        }
        return Integer.parseInt(quantity);
    }

    //VALIDATE INPUT METHODS
    public boolean selectProductBoolean(String product){
        product = product.trim().toUpperCase();
        if (!product.equals("HYBRID") && !product.equals("FLATBED") && !product.equals("BOX")){
            return false;
        }
        return true;
    }

    public boolean writeQuantityBoolean(String quantity){
        quantity = quantity.trim();
        if (!quantity.matches("\\d+")){
            return false;
        }
        return true;
    }
}
