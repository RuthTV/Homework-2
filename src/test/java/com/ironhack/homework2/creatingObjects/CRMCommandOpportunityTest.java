package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.enums.Product;
import com.ironhack.homework2.enums.Status;
import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Opportunity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CRMCommandOpportunityTest {
    Opportunity opportunity1, opportunity2;
    Contact contact1, contact2;
    Map<Integer, Opportunity> opportunityMap1;
    Map<Integer, Opportunity> opportunityMap2;
    CRMActions crmCommand;

    @BeforeEach
    void setUp() {
        contact1 = new Contact("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        contact2 = new Contact("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        opportunity1 = new Opportunity(Product.BOX,9356, contact1);
        opportunity2 = new Opportunity(Product.FLATBED,30, contact2);
        opportunityMap1 = new HashMap<>();
        opportunityMap1.put(opportunity1.getId(),opportunity1);
        opportunityMap1.put(opportunity2.getId(),opportunity2);
        opportunityMap2 = new HashMap<>();
        crmCommand = new CRMActions();


    }

    @AfterEach
    void tearDown() {
        opportunity1.setOpportunityIdCounter(200000);  //reseteamos el id_counter para que no nos afecte en los test
        contact1.setContactIdCounter(100000);
    }

    @Test
    void closeWonIdCommand_BadStament_Comment(){
        String result =  "There are no Opportunities yet";
        assertEquals(result,crmCommand.closeWonIdCommand(opportunityMap2,100000)); //no opportunity yet
        String result1 =  "Id not found, try again";
        assertEquals(result1,crmCommand.closeWonIdCommand(opportunityMap1,1));  //no opportunity with that id
    }

    @Test
    void closeWonIdCommand_GoodStament_StatusCloseWon(){
        Status result = Status.CLOSED_WON;
        String string1 = crmCommand.closeWonIdCommand(opportunityMap1,200000);
        String stringResult1 = "Status of Opportunity 200000 changed to CLOSED_WON";
        assertEquals(stringResult1,string1);
        Status opportunityStatus1 = opportunity1.getStatus();
        assertEquals(result,opportunityStatus1);
        String string2 = crmCommand.closeWonIdCommand(opportunityMap1,200001);
        String stringResult2 = "Status of Opportunity 200001 changed to CLOSED_WON";
        assertEquals(stringResult2,string2);
        Status opportunityStatus2 = opportunity2.getStatus();
        assertEquals(result,opportunityStatus2);
    }

    @Test
    void closeLostIdCommand_BadStament_Comment(){
        String result =  "There are no Opportunities yet";
        assertEquals(result,crmCommand.closeLostIdCommand(opportunityMap2,200000)); //no opportunity yet
        String result1 =  "Id not found, try again";
        assertEquals(result1,crmCommand.closeLostIdCommand(opportunityMap1,1));  //no opportunity with that id
    }

    @Test
    void closeLostIdCommand_GoodStament_StatusCloseWon(){
        Status result = Status.CLOSED_LOST;
        String string1 = crmCommand.closeLostIdCommand(opportunityMap1,200000);
        String stringResult1 = "Status of Opportunity 200000 changed to CLOSED_LOST";
        assertEquals(stringResult1,string1);
        Status opportunityStatus1 = opportunity1.getStatus();
        assertEquals(result,opportunityStatus1);
        String string2 = crmCommand.closeLostIdCommand(opportunityMap1,200001);
        String stringResult2 = "Status of Opportunity 200001 changed to CLOSED_LOST";
        assertEquals(stringResult2,string2);
        Status opportunityStatus2 = opportunity2.getStatus();
        assertEquals(result,opportunityStatus2);
    }

    @Test
    void showOpportunitiesCommand_BadStament_Comment(){
        String result =  "There are no Opportunities yet";
        assertEquals(result,crmCommand.showOpportunitiesCommand(opportunityMap2)); //no opportunity yet
    }

    @Test
    void showOpportunitiesCommand_GoodStament_Comment(){
        String result =  "id: 200001 -> decision maker: Alejandro Hernandez" + "\n" +
                "id: 200000 -> decision maker: Alex Bermejo" + "\n";
        assertEquals(result,crmCommand.showOpportunitiesCommand(opportunityMap1));
    }

    @Test
    void lookUpOpportunityIdCommand_BadStament_Comment(){
        String result =  "There are no Opportunities yet";
        assertEquals(result,crmCommand.lookUpOpportunityIdCommand(opportunityMap2,100000)); //no opportunity yet
        String result1 =  "Id not found, try again";
        assertEquals(result1,crmCommand.lookUpOpportunityIdCommand(opportunityMap1,1));//no opportunity with that id
    }

    @Test
    void lookUpOpportunityIdCommand_GoodStament_Comment(){
        String result2 = "=== Opportunity 200000 ===\n- product: BOX\n- trucks quantity: 9356\n" +
                "- decision maker:  CONTACT 100000\n   - name: Alex Bermejo\n   - phone number: +34 652899076\n" +
                "   - email: alex.bermejo@gmail.com\n   - company name: Accenture\n- status: OPEN\n";

        String result = "=== Opportunity 200001 ===\n- product: FLATBED\n- trucks quantity: 30\n" +
                "- decision maker:  CONTACT 100001\n   - name: Alejandro Hernandez\n   - phone number: +4 652899076\n" +
                "   - email: alejandrito.divertido@hotmail.com\n   - company name: Accenture\n- status: OPEN\n";
        String string1 = crmCommand.lookUpOpportunityIdCommand(opportunityMap1,200000);
        assertEquals(result2,string1);
        String string2 = crmCommand.lookUpOpportunityIdCommand(opportunityMap1,200001);
        assertEquals(result,string2);
    }
}