package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.enums.Status;
import com.ironhack.homework2.objects.Account;
import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Lead;
import com.ironhack.homework2.objects.Opportunity;

import java.util.*;

public class CRMActions {
    Scanner scanner = new Scanner(System.in);
    CreatingObject creatingObject = new CreatingObject();
    CreatingOpportunity creatingOpportunity = new CreatingOpportunity();
    CreatingAccount creatingAccount = new CreatingAccount();
    Map<Integer, Lead> leadMap = new HashMap<>();
    Map<Integer, Contact> contactMap = new HashMap<>();
    Map<Integer, Opportunity> opportunityMap = new HashMap<>();
    Map<Integer, Account> accountMap = new HashMap<>();
    List<Contact> contactList = new ArrayList<>();
    List<Opportunity> opportunityList = new ArrayList<>();

    public void newLeadCommand(Map<Integer, Lead> leadMap) {
        //Creates a new Lead and adds it to the leadMap
        this.leadMap = creatingObject.creatingLead(leadMap);
    }

    public String showLeadsCommand(Map<Integer, Lead> leadMap) {
        //Shows the id and the name of all the leads present in the leadMap
        if(leadMap.size()>=1){
            String message = "";
            for (Lead lead : leadMap.values()) {
                message += (lead.showLead()) + "\n";
            }
            return message;
        }
        return "There are no Leads yet"; //If the leadMap is empty
    }

    public String showContactsCommand(Map<Integer, Contact> contactMap) {
        //Shows the id and the name of all the contacts present in the contactMap
        if(contactMap.size()>=1){
            String message = "";
            for (Contact contact : contactMap.values()) {
                message += (contact.showContact()) + "\n";
            }
            return message;
        }
        return "There are no Contacts yet"; //If the contactMap is empty
    }

    public String showOpportunitiesCommand(Map<Integer, Opportunity> opportunityMap) {
        //Shows the id and the name of the decision maker of all the opportunities present in the opportunityMap
        if(opportunityMap.size()>=1) {
            String message = "";
            for (Opportunity opportunity : opportunityMap.values()) {
                message += opportunity.showOpportunity() + "\n";
            }
            return message;
        }
        return "There are no Opportunities yet"; //If the opportunityMap is empty
    }

    public String showAccountsCommand(Map<Integer, Account> accountMap) {
        //Shows the id and the name of the first contact of all the accounts present in the accountMap
        if(accountMap.size()>=1){
            String message = "";
            for (Account account : accountMap.values()) {
                message += account.showAccount() + "\n";
            }
            return message;
        }
        return "There are no Accounts yet"; //If the accountMap is empty
    }

    public String  lookUpLeadIdCommand(Map<Integer, Lead> leadMap, int id) {
        if(leadMap.size()<1){
            return "There are no Leads yet"; //If the Map is empty
        }
        // Check if id exists in the Map
        boolean isIdPresent = leadMap.containsKey(id);
        if (!isIdPresent) {
            return "Id not found, try again"; //If id not present in the Map
        }
        Lead lead = leadMap.get(id);
        return lead.toString();
    }

    public String lookUpAccountIdCommand(Map<Integer, Account> accountMap, Integer id) {
        if(accountMap.size()<1){
            return "There are no Accounts yet"; //If the Map is empty
        }
        // Check if id exists in the Map
        boolean isIdPresent = accountMap.containsKey(id);
        if (!isIdPresent) {
            return "Id not found, try again"; //If id not present in the Map
        }
        Account account = accountMap.get(id);
        return account.toString();
    }

    public String lookUpOpportunityIdCommand(Map<Integer, Opportunity> opportunityMap, Integer id) {
        if(opportunityMap.size()<1){
            return "There are no Opportunities yet"; //If the Map is empty
        }
        // Check if id exists in the Map
        boolean isIdPresent = opportunityMap.containsKey(id);
        if (!isIdPresent) {
            return "Id not found, try again"; //If id not present in the Map
        }
        Opportunity opportunity = opportunityMap.get(id);
        return opportunity.toString();
    }

    public String lookUpContactIdCommand(Map<Integer, Contact> contactMap, Integer id) {
        if(contactMap.size()<1){
            return "There are no Contacts yet"; //If the Map is empty
        }
        // Check if id exists in the Map
        boolean isIdPresent = contactMap.containsKey(id);
        if (!isIdPresent) {
            return "Id not found, try again"; //If id not present in the Map
        }
        Contact contact = contactMap.get(id);
        return contact.toString();
    }

    public void convertIdCommand(Map<Integer, Lead> leadMap, Integer id, Map<Integer, Contact> contactMap,
                                 Map<Integer, Opportunity> opportunityMap, Map<Integer, Account> accountMap)
                                throws InterruptedException {
        if(leadMap.size()<1){
            System.out.println("There are no Leads yet"); //If the Map is empty
        } else {
            // Check if id exists in the Map
            boolean isIdPresent = leadMap.containsKey(id);
            if (isIdPresent) {
                //Create Contact and add it to the List and the Map
                Contact contact = creatingObject.creatingContact(leadMap.get(id));
                contactList = creatingObject.creatingContactList(contact, contactList);
                this.contactMap = creatingObject.creatingContactMap(contact, contactMap);
                //Create Opportunity to the List and the Map
                Thread.sleep(500);
                Opportunity opportunity = creatingOpportunity.creatingOpportunity(contact);
                opportunityList = creatingOpportunity.creatingOpportunityList(opportunity, opportunityList);
                this.opportunityMap = creatingOpportunity.creatingOpportunityMap(opportunity, opportunityMap);
                //Create Account to the Map
                Thread.sleep(500);
                this.accountMap = creatingAccount.creatingAccount(contactList, opportunityList, accountMap);
                //Remove Lead
                Thread.sleep(1000);
                leadMap.remove(id);
                System.out.println("Lead successfully converted and removed from the CRM system");
            } else {
                System.out.println("Id not found, try again"); //If id not present in the Map
            }
        }
    }

    public String closeLostIdCommand(Map<Integer, Opportunity> opportunityMap, Integer id) {
        if(opportunityMap.size()<1){
            return "There are no Opportunities yet"; //If the Map is empty
        }
        // Check if id exists in the Map
        boolean isIdPresent = opportunityMap.containsKey(id);
        if (isIdPresent) {
            opportunityMap.get(id).setStatus(Status.CLOSED_LOST);
            return "Status of Opportunity " + opportunityMap.get(id).getId() + " changed to CLOSED_LOST";
        }
        return "Id not found, try again";
    }

    public String closeWonIdCommand(Map<Integer, Opportunity> opportunityMap, Integer id) {
        if(opportunityMap.size()<1){
            return "There are no Opportunities yet"; //If the Map is empty
        }
        // Check if id exists in the Map
        boolean isIdPresent = opportunityMap.containsKey(id);
        if (isIdPresent) {
            opportunityMap.get(id).setStatus(Status.CLOSED_WON);
            return "Status of Opportunity " + opportunityMap.get(id).getId() + " changed to CLOSED_WON";
        }
        return "Id not found, try again";
    }

    public String helpCommand() {
        return ("=== Possible Commands ===\n"+
                "NEW LEAD -> Add Lead to the CRM system\n" +
                "SHOW LEADS -> Display a list of all the LEADS' id and name\n" +
                "SHOW CONTACTS -> Display a list of all the CONTACTS' id and name\n" +
                "SHOW OPPORTUNITIES -> Display a list of all the OPPORTUNITIES' id and name of the decision maker\n" +
                "SHOW ACCOUNTS -> Display a list of all the ACCOUNTS id and name of the first contact\n" +
                "LOOKUP LEAD <ID> -> Display the selected LEAD's details with the indicated Id Number\n" +
                "LOOKUP ACCOUNT <ID> -> Display the selected ACCOUNT's details with the indicated Id Number\n" +
                "LOOKUP OPPORTUNITY <ID> -> Display the selected OPPORTUNITY's details with the indicated Id Number\n" +
                "LOOKUP CONTACT <ID> -> Display the selected CONTACT's details with the indicated Id Number\n" +
                "CONVERT <ID> -> Converts the selected LEAD in CONTACT, OPPORTUNITY and ACCOUNT " +
                "and removes it from the system\n" +
                "CLOSE LOST <ID> -> Changes the selected OPPORTUNITY status to CLOSE-LOST\n" +
                "CLOSE WON <ID> -> Changes the selected OPPORTUNITY status to CLOSE-WON\n" +
                "QUIT -> Terminates the CRM system\n");
    }

    public void quitCommand() {
        System.out.println("Thank you for using the CRM system. Have a nice day.");
        scanner.close();
        System.exit(0);
    }
}
