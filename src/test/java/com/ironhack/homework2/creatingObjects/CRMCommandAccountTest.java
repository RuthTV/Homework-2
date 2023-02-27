package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.enums.Industry;
import com.ironhack.homework2.enums.Product;
import com.ironhack.homework2.objects.Account;
import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Opportunity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CRMCommandAccountTest {
    Industry industry1;
    Industry industry2;
    Contact contact1,contact2;
    List<Contact> contactList;
    List<Contact> contactList2;
    Opportunity opportunity1,opportunity2;
    List<Opportunity> opportunityList;
    List<Opportunity> opportunityList2;
    Account account1;
    Account account2;
    CRMActions crmCommand;
    Map<Integer, Account> accountMap1;
    Map<Integer, Account> accountMap2;

    @BeforeEach
    void setUp() {
        industry1 = Industry.ECOMMERCE;
        industry2 = Industry.OTHER;
        contact1 = new Contact("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        contact2 = new Contact("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        contactList = List.of(contact1);
        contactList2 = List.of(contact2);
        crmCommand = new CRMActions();
        opportunity1 = new Opportunity(Product.BOX,9356, contact1);
        opportunity2 = new Opportunity(Product.FLATBED,0, contact2);
        opportunityList = List.of(opportunity1);
        opportunityList2 = List.of(opportunity2);
        accountMap1 = new HashMap<>();
        accountMap2 = new HashMap<>();
        account1 = new Account(industry1,456,"Barcelona","España",contactList,opportunityList);
        account2 = new Account(industry2,56,"Roma","Italia",contactList2,opportunityList2);
        accountMap1.put(account1.getId(), account1);
        accountMap1.put(account2.getId(), account2);

    }

    @AfterEach
    void tearDown() {
        account1.setAccountIdCounter(300000); //reseteamos el id_counter para que no nos afecte en los test
        opportunity1.setOpportunityIdCounter(200000);
        contact1.setContactIdCounter(100000);
    }

    @Test
    void showAccountsCommand_BadStament_Comment(){
        String result =  "There are no Accounts yet";
        assertEquals(result,crmCommand.showAccountsCommand(accountMap2)); //no lead yet
    }

    @Test
    void showAccountsCommand_GoodStament_Comment(){
        String result =  "id: 300000 -> name of first contact: Alex Bermejo\n"+
                "id: 300001 -> name of first contact: Alejandro Hernandez\n";
        assertEquals(result,crmCommand.showAccountsCommand(accountMap1));
    }

    @Test
    void lookUpAccountIdCommand_BadStament_Comment(){
        String result =  "There are no Accounts yet";
        assertEquals(result,crmCommand.lookUpAccountIdCommand(accountMap2,300000)); //no opportunity yet
        String result1 =  "Id not found, try again";
        assertEquals(result1,crmCommand.lookUpAccountIdCommand(accountMap1,10));  //no opportunity with that id
    }

    @Test
    void lookUpAccountIdCommand_GoodStament_Comment(){
        String result1 = "=== Account 300000 ===\n- industry: ECOMMERCE\n- employee count: 456\n- city: " +
                "Barcelona\n- country: España\n- " +
                "id's of the contacts: \n100000\n- id's of the opportunities: \n200000\n";
        String result2 = "=== Account 300001 ===\n- industry: OTHER\n- employee count: 56\n- city: " +
                "Roma\n- country: Italia\n- " +
                "id's of the contacts: \n100001\n- id's of the opportunities: \n200001\n";
        String string1 = crmCommand.lookUpAccountIdCommand(accountMap1,300000);
        assertEquals(result1,string1);
        String string2 = crmCommand.lookUpAccountIdCommand(accountMap1,300001);
        assertEquals(result2,string2);
    }
    @Test
    void lookUpAccountIdCommand() {
    }

    @Test
    void helpCommand() {
        String string = "=== Possible Commands ===\n"+
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
                "QUIT -> Terminates the CRM system\n";
        assertEquals(string, crmCommand.helpCommand());
    }
}