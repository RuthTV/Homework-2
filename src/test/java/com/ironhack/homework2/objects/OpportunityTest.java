package com.ironhack.homework2.objects;

import com.ironhack.homework2.enums.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpportunityTest {
    Contact contact1, contact2, contact3;
    Opportunity opportunity1,opportunity2,opportunity3;


    @BeforeEach
    void setUp() {
        contact1 = new Contact("Alex Bermejo","+34 652899076",
                "alex.bermejo@gmail.com","Accenture");
        contact2 = new Contact("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        contact3 = new Contact("Anastasia Garcia","+34 456735",
                "anastigarcA.aLMeid@accenture.com","Accenture");
        opportunity1 = new Opportunity(Product.BOX,9356, contact1);
        opportunity2 = new Opportunity(Product.FLATBED,30, contact2);
        opportunity3 = new Opportunity(Product.HYBRID,34, contact3);
    }

    @AfterEach
    void tearDown() {
        opportunity1.setOpportunityIdCounter(200000);  //reseteamos el id_counter para que no nos afecte en los test
        contact1.setContactIdCounter(100000);
    }

    @Test
    void testToString_differentOpportunities_returnStringOpportunity() {
        assertEquals("=== Opportunity 200000 ===\n- product: BOX\n- trucks quantity: 9356\n" +
                "- decision maker:  CONTACT 100000\n   - name: Alex Bermejo\n   - phone number: +34 652899076\n" +
                "   - email: alex.bermejo@gmail.com\n   - company name: Accenture\n- status: OPEN\n",
                opportunity1.toString());
        assertEquals("=== Opportunity 200001 ===\n- product: FLATBED\n- trucks quantity: 30\n" +
                "- decision maker:  CONTACT 100001\n   - name: Alejandro Hernandez\n   - phone number: +4 652899076\n" +
                "   - email: alejandrito.divertido@hotmail.com\n   - company name: Accenture\n- status: OPEN\n",
                opportunity2.toString());
        assertEquals("=== Opportunity 200002 ===\n- product: HYBRID\n- trucks quantity: 34\n" +
                "- decision maker:  CONTACT 100002\n   - name: Anastasia Garcia\n   - phone number: +34 456735\n" +
                "   - email: anastigarcA.aLMeid@accenture.com\n   - company name: Accenture\n- status: OPEN\n",
                opportunity3.toString());
    }

    @Test
    void showOpportunity_differentOpportunities_returnIdDecisionMaker() {
        assertEquals("id: 200000 -> decision maker: Alex Bermejo",opportunity1.showOpportunity());
        assertEquals("id: 200001 -> decision maker: Alejandro Hernandez",opportunity2.showOpportunity());
        assertEquals("id: 200002 -> decision maker: Anastasia Garcia",opportunity3.showOpportunity());
    }
}