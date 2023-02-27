package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.enums.Product;
import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Opportunity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreatingOpportunityTest {
    CreatingOpportunity creatingOpportunity;
    Opportunity opportunity1;
    Opportunity opportunity2;
    Contact contact1;
    Contact contact2;
    List<Opportunity> opportunityList = new ArrayList<>();
    Map<Integer, Opportunity> opportunityMap = new HashMap<>();
    List<Opportunity> opportunityList1 = new ArrayList<>();
    Map<Integer, Opportunity> opportunityMap1 = new HashMap<>();

    @BeforeEach
    void setUp() {
        creatingOpportunity = new CreatingOpportunity();
        contact1 = new Contact("Alex Bermejo","+34 652899076","alex.bermejo@gmail.com",
                "Accenture");
        contact2 = new Contact("Alejandro Hernandez","+4 652899076",
                "alejandrito.divertido@hotmail.com","Accenture");
        opportunity1 = new Opportunity(Product.BOX,9356, contact1);
        opportunity2 = new Opportunity(Product.FLATBED,30, contact2);
        opportunityList1.add(opportunity1);
        opportunityMap1.put(opportunity1.getId(),opportunity1);
    }

    @AfterEach
    void tearDown() {
        opportunity1.setOpportunityIdCounter(200000);
        contact1.setContactIdCounter(100000);
    }

    @Test
    void selectProductBoolean_notProductValue_False() {
        assertFalse(creatingOpportunity.selectProductBoolean("String product"));
        assertFalse(creatingOpportunity.selectProductBoolean("Hel1lo"));
        assertFalse(creatingOpportunity.selectProductBoolean("1246%"));
    }

    @Test
    void selectProductBoolean_productValue_True() {
        assertTrue(creatingOpportunity.selectProductBoolean("hybrid"));
        assertTrue(creatingOpportunity.selectProductBoolean("FLATBED"));
        assertTrue(creatingOpportunity.selectProductBoolean("Box"));
    }

    @Test
    void writeQuantityBoolean_notInt_False() {
        assertFalse(creatingOpportunity.writeQuantityBoolean("String product"));
        assertFalse(creatingOpportunity.writeQuantityBoolean("-12"));
        assertFalse(creatingOpportunity.writeQuantityBoolean("!1246%"));
    }

    @Test
    void writeQuantityBoolean_int_True() {
        assertTrue(creatingOpportunity.writeQuantityBoolean("4"));
        assertTrue(creatingOpportunity.writeQuantityBoolean("1234"));
    }

    @Test
    void creatingOpportunityList_voidOpportunityList_ReturnListOpportunityWithOneThing() {
        opportunityList = creatingOpportunity.creatingOpportunityList(opportunity1,opportunityList);
        assertTrue(opportunityList.contains(opportunity1));
        assertEquals(1,opportunityList.size());
    }

    @Test
    void creatingOpportunityList_opportunityListNotVoid_ReturnListOpportunityWithOneThing() {
        opportunityList1 = creatingOpportunity.creatingOpportunityList(opportunity2,opportunityList1);
        assertTrue(opportunityList1.contains(opportunity2));
        assertEquals(1,opportunityList1.size());
    }

    @Test
    void creatingOpportunityMap_voidOpportunityMap_ReturnOpportunityMapWithOneThing() {
        opportunityMap = creatingOpportunity.creatingOpportunityMap(opportunity1,opportunityMap);
        assertTrue(opportunityMap.containsKey(opportunity1.getId()));
        assertTrue(opportunityMap.containsValue(opportunity1));
        assertEquals(1,creatingOpportunity.creatingOpportunityMap(opportunity1,opportunityMap).size());
    }
    @Test
    void  creatingOpportunityMap_notVoidOpportunityMap_ReturnAllOpportunityMap() {
        opportunityMap1 = creatingOpportunity.creatingOpportunityMap(opportunity2,opportunityMap1);
        assertTrue(opportunityMap1.containsKey(opportunity2.getId()));
        assertTrue(opportunityMap1.containsValue(opportunity2));
        assertEquals(2,creatingOpportunity.creatingOpportunityMap(opportunity2,opportunityMap1).size());
    }
}