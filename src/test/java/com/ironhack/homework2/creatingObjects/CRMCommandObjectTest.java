package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Lead;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CRMCommandObjectTest {
    Lead lead1, lead2;
    Map<Integer, Lead> leadMap1;
    Map<Integer, Lead> leadMap2;
    CRMActions crmCommand;
    Contact contact1,contact2;
    Map<Integer, Contact> contactMap1;
    Map<Integer, Contact> contactMap2;


    @BeforeEach
    void setUp() {
        lead1 = new Lead("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        lead2 = new Lead("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        leadMap1 = new HashMap<>();
        leadMap1.put(lead1.getId(), lead1);
        leadMap1.put(lead2.getId(), lead2);
        leadMap2 = new HashMap<>();
        crmCommand = new CRMActions();
        contactMap1 = new HashMap<>();
        contactMap2 = new HashMap<>();
        contact1 = new Contact("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        contact2 = new Contact("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        contactMap1.put(contact1.getId(), contact1);
        contactMap1.put(contact2.getId(), contact2);
    }

    @AfterEach
    void tearDown() {
        lead1.setLeadIdCounter(1);
        contact1.setContactIdCounter(100000);
    }

    @Test
    void showLeadsCommand_BadStament_Comment(){
        String result =  "There are no Leads yet";
        assertEquals(result,crmCommand.showLeadsCommand(leadMap2)); //no lead yet
    }

    @Test
    void showLeadsCommand_GoodStament_Comment(){
        String result =  "id: 1 -> name: Alex Bermejo" + "\n" +
                "id: 2 -> name: Alejandro Hernandez" + "\n";
        assertEquals(result,crmCommand.showLeadsCommand(leadMap1));
    }

    @Test
    void lookUpLeadIdCommand_BadStament_Comment(){
        String result =  "There are no Leads yet";
        assertEquals(result,crmCommand.lookUpLeadIdCommand(leadMap2,1)); //no opportunity yet
        String result1 =  "Id not found, try again";
        assertEquals(result1,crmCommand.lookUpLeadIdCommand(leadMap1,10));  //no opportunity with that id
    }

    @Test
    void lookUpLeadIdCommand_GoodStament_Comment(){
        String result1 = "=== Lead 1 ===\n- name: Alex Bermejo\n- phone number: +34 652899076\n" +
                    "- email: alex.bermejo@gmail.com\n- company name: Accenture\n";
        String result2 = "=== Lead 2 ===\n- name: Alejandro Hernandez\n- phone number: +4 652899076\n" +
                    "- email: alejandrito.divertido@hotmail.com\n- company name: Accenture\n";
        String string1 = crmCommand.lookUpLeadIdCommand(leadMap1,1);
        assertEquals(result1,string1);
        String string2 = crmCommand.lookUpLeadIdCommand(leadMap1,2);
        assertEquals(result2,string2);
    }


    @Test
    void showContactsCommand_BadStament_Comment(){
        String result =  "There are no Contacts yet";
        assertEquals(result,crmCommand.showContactsCommand(contactMap2)); //no lead yet
    }

    @Test
    void showContactsCommand_GoodStament_Comment(){
        String result =  "id: 100001 -> name: Alejandro Hernandez" + "\n" +
                "id: 100000 -> name: Alex Bermejo" + "\n";
        assertEquals(result,crmCommand.showContactsCommand(contactMap1));
    }

    @Test
    void lookUpContactIdCommand_BadStament_Comment(){
        String result =  "There are no Contacts yet";
        assertEquals(result,crmCommand.lookUpContactIdCommand(contactMap2,100000)); //no opportunity yet
        String result1 =  "Id not found, try again";
        assertEquals(result1,crmCommand.lookUpContactIdCommand(contactMap1,10));  //no opportunity with that id
    }

    @Test
    void lookUpContactIdCommand_GoodStament_Comment(){
        String result1 = "=== Contact 100000 ===\n- name: Alex Bermejo\n- phone number: +34 652899076\n" +
                "- email: alex.bermejo@gmail.com\n- company name: Accenture\n";
        String result2 = "=== Contact 100001 ===\n- name: Alejandro Hernandez\n- phone number: +4 652899076\n" +
                "- email: alejandrito.divertido@hotmail.com\n- company name: Accenture\n";
        String string1 = crmCommand.lookUpContactIdCommand(contactMap1,100000);
        assertEquals(result1,string1);
        String string2 = crmCommand.lookUpContactIdCommand(contactMap1,100001);
        assertEquals(result2,string2);
    }

}