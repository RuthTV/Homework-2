package com.ironhack.homework2.creatingObjects;

import com.ironhack.homework2.enums.Command;
import com.ironhack.homework2.objects.Account;
import com.ironhack.homework2.objects.Contact;
import com.ironhack.homework2.objects.Lead;
import com.ironhack.homework2.objects.Opportunity;

import java.util.*;

public class CRMMenu {
    Scanner scanner = new Scanner(System.in);
    CRMActions crmActions = new CRMActions();
    Map<Integer, Lead> leadMap = new HashMap<>();
    Map<Integer, Contact> contactMap = new HashMap<>();
    Map<Integer, Opportunity> opportunityMap = new HashMap<>();
    Map<Integer, Account> accountMap = new HashMap<>();

    public void menu () throws InterruptedException {
        System.out.println("\n=== Welcome to the CRM system ===\n");
        System.out.println("Introduce a command, if you don't know what to introduce you can choose the HELP command");
        Command commandEnum;
        //It keeps waiting for an input until the introduced command is QUIT
        do {
            System.out.println(">>>");
            String command = scanner.nextLine();

            //Get the String part of the introduced command and later check if is one of the possible commands
            String commandString = getCommandString(command);
            //Get the numeric part of the introduced command and use it as the id of the Object
            int id = getCommandId(command);

            //Checking if the introduced command is one of the possible commands
            commandEnum = Command.CONTINUE; //To mantain inside the loop and not restart the menu if wrong command
            try {
                commandEnum = Command.valueOf(commandString);
                if (commandEnum.equals(Command.CONTINUE)){
                    System.out.println("This is not a correct command"); //To indicate that continue is established
                                                                         // for the loop but it has no functionality
                }
            } catch (IllegalArgumentException e) {
                System.out.println("This is not a correct command");
                continue;
            }

            //Call the different possible actions depending on the command and the id introduced
            menuActions(commandEnum,id);

        scanner = new Scanner(System.in); //To clean the scanner before the next loop
        } while (!commandEnum.equals(Command.QUIT));
    }

    public String getCommandString(String command) {
        command = command.trim();
        String commandString = command.replaceAll("[^A-Za-z ]", ""); //To get only the string part
        commandString = commandString.trim().replace(" ", "_").toUpperCase();
        return commandString;
    }

    public int getCommandId(String command) {
        command = command.trim();
        String commandId = command.replaceAll("[^0-9]", ""); //To get only the numeric part (id)
        commandId = commandId.trim();
        int id = -1;
        try {
            id = Integer.parseInt(commandId);
        } catch (Exception e) {
            id = -1;
        }
        return id;
    }

    public void menuActions(Command commandEnum, int id) throws InterruptedException {
        //It calls all the different methods needed depending on the introduced command
        switch (commandEnum) {
            case NEW_LEAD:
                crmActions.newLeadCommand(leadMap);
                break;
            case SHOW_LEADS:
                System.out.println(crmActions.showLeadsCommand(leadMap));
                break;
            case SHOW_CONTACTS:
                System.out.println(crmActions.showContactsCommand(contactMap));
                break;
            case SHOW_OPPORTUNITIES:
                System.out.println(crmActions.showOpportunitiesCommand(opportunityMap));
                break;
            case SHOW_ACCOUNTS:
                System.out.println(crmActions.showAccountsCommand(accountMap));
                break;
            case LOOKUP_LEAD:
                System.out.println(crmActions.lookUpLeadIdCommand(leadMap, id));
                break;
            case LOOKUP_ACCOUNT:
                System.out.println(crmActions.lookUpAccountIdCommand(accountMap, id));
                break;
            case LOOKUP_OPPORTUNITY:
                System.out.println(crmActions.lookUpOpportunityIdCommand(opportunityMap, id));
                break;
            case LOOKUP_CONTACT:
                System.out.println(crmActions.lookUpContactIdCommand(contactMap, id));
                break;
            case CONVERT:
                crmActions.convertIdCommand(leadMap, id, contactMap, opportunityMap, accountMap);
                break;
            case CLOSE_LOST:
                System.out.println(crmActions.closeLostIdCommand(opportunityMap, id));
                break;
            case CLOSE_WON:
                System.out.println(crmActions.closeWonIdCommand(opportunityMap, id));
                break;
            case HELP:
                System.out.println(crmActions.helpCommand());
                break;
            case QUIT:
                crmActions.quitCommand();
                break;
        }
    }
}