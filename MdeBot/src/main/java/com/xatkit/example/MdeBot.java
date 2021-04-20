package com.xatkit.example;

import com.xatkit.core.XatkitBot;
import com.xatkit.plugins.react.platform.ReactPlatform;
import com.xatkit.plugins.react.platform.io.ReactEventProvider;
import com.xatkit.plugins.react.platform.io.ReactIntentProvider;
import lombok.val;
import org.apache.commons.configuration2.BaseConfiguration;
import org.apache.commons.configuration2.Configuration;

import static com.xatkit.dsl.DSL.any;
import static com.xatkit.dsl.DSL.eventIs;
import static com.xatkit.dsl.DSL.fallbackState;
import static com.xatkit.dsl.DSL.intent;
import static com.xatkit.dsl.DSL.intentIs;
import static com.xatkit.dsl.DSL.model;
import static com.xatkit.dsl.DSL.state;
import java.io.FileWriter;
import java.util.concurrent.*;
import java.util.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.lang.ProcessBuilder;

public class MdeBot {

    static final int participantIndexNumber = 1;
    static final int assetIndexNumber = 2;
    static final int transactionIndexNumber = 3;
    static final int tranrelIndexNumber = 1;
    static final int assetrelIndexNumber = 2;
    // This arraylist is meant for storing all the names of the participants of the smart contract. This will be used to find the participants using their names.
    static ArrayList<String> participantNames = new ArrayList <String>();
    // This arraylist is meant for storing all the names of the assets of the smart contract. This will be used to find the assets using their names.
    static ArrayList<String> assetNames = new ArrayList <String>();
    // This arraylist is meant for storing all the names of the transactions of the smart contract. This will be used to find the transactions using their names.
    static ArrayList<String> transactionNames = new ArrayList<String>();
    // This arraylist is meant for storing all the participants that will be created in the smart contract.
    static ArrayList<Participant> contractParticipants = new ArrayList<Participant>();
    // This arraylist is meant for storing all the assets that will be created in the smart contract.
    static ArrayList<Asset> contractAssets = new ArrayList<Asset>();
    // This arraylist is meant for storing all the transactions that will be created in the smart contract.
    static ArrayList<Transaction> contractTransactions = new ArrayList<Transaction>();
    static String nameOfContract = "";
    static String wholeContract = "";
    static int indexNumber;
    static int indexNumberRelationship;
    static String targetPlatform = "";
    static String participants = "";
    static String assets = "";
    static String transactions = "";
    static String parameters = "";

    static int numberOfParticipants;
    static int numberOfAssets;
    static int numberOfTransactions;
    static int currentParticipant;
    static int currentAsset;
    static int currentTransaction;
    static int currentParticipantParameter;
    static int currentAssetParameter;
    static int currentTransactionParameter;
    static int currentTransactionTranRel;
    static int currentTransactionAssetRel;
    static int currentTransactionCondition;

    static String currentParticipantName;
    static String currentAssetName;
    static String currentTransactionName;
    static String currentConditionName;
    static String currentRelationshipName;

    public MdeBot() {
        numberOfParticipants = 0;
        numberOfAssets = 0;
        numberOfTransactions = 0;
        currentParticipant = 0;
        currentAsset = 0;
        currentTransaction = 0;
        currentParticipantParameter = 0;
        currentAssetParameter = 0;
        currentTransactionParameter = 0;
        currentTransactionTranRel = 0;
        currentTransactionAssetRel = 0;
        currentTransactionCondition = 0;
    }
    
    public static void main(String[] args) {

        // Variables that are responsible for storing the values temporarily
        AtomicReference<String> a1 = new AtomicReference<>();

        //Intent to find if the user wants to create a contract
        val createContract = intent("CreateContract")
            .trainingSentence("I want to create a contract")
            .trainingSentence("Create a contract")
            .trainingSentence("I wish to make a contract")
            .trainingSentence("I wish to develop a contract");

        //Intent to find if the user wants to edit a contract
        val editContract = intent("EditContract")
            .trainingSentence("I want to edit a contract")
            .trainingSentence("Edit a contract")
            .trainingSentence("I wish to update a contract")
            .trainingSentence("I wish to change a contract");

        //Obtain the name of the contract that is to be created or that is to be edited
        val contractName = intent("ContractName")
            .trainingSentence("The name of the contract is CONT")
            .trainingSentence("Name of the contract is CONT")
            .trainingSentence("I want to call the contract as CONT")
            .trainingSentence("Contract's name is CONT")
            .trainingSentence("Name of the contract is CONT")
            .trainingSentence("Contract = CONT")
            .trainingSentence("Contract is CONT")
            .parameter("cont").fromFragment("CONT").entity(any());

        val createElement = intent("CreateElement")
            .trainingSentence("I want to build an element")
            .trainingSentence("I want to create an element")
            .trainingSentence("I want to make an element")
            .trainingSentence("Create an element")
            .trainingSentence("Make an element for the contract");

        val editElement = intent("EditElement")
            .trainingSentence("I want to edit an element")
            .trainingSentence("I want to update an element")
            .trainingSentence("I want to change an element")
            .trainingSentence("Make changes to an element")
            .trainingSentence("Edit an element for the contract");

        val deleteElement = intent("DeleteElement")
            .trainingSentence("I want to delete an element")
            .trainingSentence("I want to remove an element")
            .trainingSentence("Delete an element")
            .trainingSentence("Remove an element for the contract");

        val readElement = intent("ReadElement")
            .trainingSentence("I want to read an element")
            .trainingSentence("I want you to show me an element")
            .trainingSentence("Show me an element");


        // Participants----------------------------------------------------------------------------------------

        // Creating a participant

        /*
        User tells that he/she wants to create a participant - Bot asks the name of the participant
        User tells the name of the participant - Bot asks for the list attribute of the participant
        User tells the list attribute of the participant - Bot asks if they want want to have parameters
        User says yes for creating parameters - Bot asks for the parameter's name
        User says no for creating parameters - Bot asks if the user wants to create any relationship
        User tells the name - Bot asks the type of the parameter
        User tells the type of the parameter - Bot asks if the user wants to create any more parameters
        */

        val createParticipant = intent("CreateParticipant")
            .trainingSentence("I want to create a participant")
            .trainingSentence("Create a participant")
            .trainingSentence("I wish to create a participant")
            .trainingSentence("A participant should be created");

        val createParticipantSetName = intent("CreateParticipantSetName")
            .trainingSentence("The name of the participant should be PARTNAME")
            .trainingSentence("The name of the participant is PARTNAME")
            .trainingSentence("I want to name the participant as PARTNAME")
            .trainingSentence("Set the name of the participant as PARTNAME")
            .parameter("partname").fromFragment("PARTNAME").entity(any());

        val createParticipantSetList = intent("createParticipantSetList")
            .trainingSentence("The list attribute of the participant is PARTLIST")
            .trainingSentence("The list for the participant is PARTLIST")
            .parameter("partlist").fromFragment("PARTLIST").entity(any());

        val createParticipantAssertParameter = intent("CreateParticipantAssertParameter")
            .trainingSentence("Yes, I want to have a parameter for this participant")
            .trainingSentence("Yes, I want to have more parameters for the participant")
            .trainingSentence("I want to build more parameters for this participant")
            .trainingSentence("I want to add more parameters to the ");

        val createParticipantAssertRelationship = intent("CreateParticipantAssertRelationship")
            .trainingSentence("Yes, I want to build a relationship for this participant")
            .trainingSentence("Yes, I want to build more relationships for the participant")
            .trainingSentence("I want to build more relationships for this participant");

        val createParticipantDone = intent("CreateParticpantDone")
            .trainingSentence("No, that's all for the participant")
            .trainingSentence("This participant is completed")
            .trainingSentence("No, I don't want to change the participant anymore");

        val createParticipantSetIdentifier = intent("CreateParticipantSetIdentifier")
            .trainingSentence("I want PARTID to be the identifier for the participant")
            .trainingSentence("PARTID should be the identifier for the identifier")
            .trainingSentence("Make PARTID the identifier for the participant")
            .parameter("partid").fromFragment("PARTID").entity(any());

        //***************************************************************************************************************************

        // Editing a participant

        val editParticipant = intent("EditParticipant")
            .trainingSentence("I want to edit a participant")
            .trainingSentence("Edit a participant")
            .trainingSentence("I wish to edit a participant")
            .trainingSentence("A participant should be edited");

        val editParticipantGetName = intent("EditParticipantGetName")
            .trainingSentence("The name of the participant that has to be edited is GEPARTNAME")
            .trainingSentence("The participant to be edited is GEPARTNAME")
            .trainingSentence("I want to edit the participant named GEPARTNAME")
            .parameter("gepartname").fromFragment("GEPARTNAME").entity(any());

        val editParticipantConfirm = intent("EditParticipantConfirm")
            .trainingSentence("Yes, edit this participant")
            .trainingSentence("Yes, I want to edit this participant");

        val editParticipantChangeName = intent("EditParticipantChangeName")
            .trainingSentence("I want to change the name of the participant")
            .trainingSentence("I want to edit the name of the participant")
            .trainingSentence("I want to rename the participant");

        val editParticipantChangeIdentifier = intent("EditParticipantChangeIdentifier")
            .trainingSentence("I want to change the identifier for the participant")
            .trainingSentence("I want to update the identifier for the participant");

        val editParticipantEditParameter = intent("EditParticipantEditParameter")
            .trainingSentence("I want to edit a parameter for the participant")
            .trainingSentence("I want to update a parameter for the participant")
            .trainingSentence("Edit a parameter of the participant");

        val editParticipantDeleteParameter = intent("EditParticipantDeleteParameter")
            .trainingSentence("I want to delete a parameter for the participant")
            .trainingSentence("I want to remove a parameter for the participant")
            .trainingSentence("Delete a parameter of the participant");

        //***************************************************************************************************************************

        // Deleting a participant

        val deleteParticipant = intent("DeleteParticipant")
            .trainingSentence("I want to delete a participant")
            .trainingSentence("Delete a participant")
            .trainingSentence("I wish to delete a participant")
            .trainingSentence("A participant should be deleted");

        val deleteParticipantGetName = intent("DeleteParticipantGetName")
            .trainingSentence("The name of the participant that has to be deleted is GDPARTNAME")
            .trainingSentence("The participant to be deleted is GDPARTNAME")
            .trainingSentence("I want the participant named GDPARTNAME to be deleted")
            .parameter("gdpartname").fromFragment("GDPARTNAME").entity(any());

        val deleteParticipantConfirm = intent("DeleteParticipantConfirm")
            .trainingSentence("Yes, delete this participant")
            .trainingSentence("Yes, you will have to delete this participant");

        //***************************************************************************************************************************

        // Reading a participant

        val readParticipant = intent("ReadParticipant")
            .trainingSentence("I want to read a participant")
            .trainingSentence("Read a participant")
            .trainingSentence("I wish to read a participant")
            .trainingSentence("A participant should be read");

        val readParticipantGetName = intent("ReadParticipantGetName")
            .trainingSentence("The name of the participant that has to be read is GRPARTNAME")
            .trainingSentence("The participant to be read is GRPARTNAME")
            .trainingSentence("I want to read the participant named GRPARTNAME")
            .trainingSentence("The name of the participant that I want to read is GRPARTNAME")
            .parameter("grpartname").fromFragment("GRPARTNAME").entity(any());

        val readParticipantConfirm = intent("ReadParticipantConfirm")
            .trainingSentence("Yes, I want to read this participant")
            .trainingSentence("Yes, show me this participant");

        //***************************************************************************************************************************

        // Assets---------------------------------------------------------------------------------------------

        // Creating an asset
        val createAsset = intent("CreateAsset")
            .trainingSentence("I want to create an asset")
            .trainingSentence("Create an asset")
            .trainingSentence("I wish to create an asset")
            .trainingSentence("An asset should be created");

        val createAssetSetType = intent("CreateAssetSetType")
            .trainingSentence("The type of the asset should be ASSTTYPE")
            .trainingSentence("The type of the asset is ASSTTYPE")
            .trainingSentence("I want the type of the asset to be ASSTTYPE")
            .trainingSentence("Set the type of the asset as ASSTTYPE")
            .parameter("assttype").fromFragment("ASSTTYPE").entity(any());

        val createAssetSetName = intent("CreateAssetSetName")
            .trainingSentence("The name of the asset should be ASSTNAME")
            .trainingSentence("The name of the asset is ASSTNAME")
            .trainingSentence("I want to name the asset as ASSTNAME")
            .trainingSentence("Set the name of the asset as ASSTNAME")
            .parameter("asstname").fromFragment("ASSTNAME").entity(any());

        val createAssetSetList = intent("CreateAssetSetList")
            .trainingSentence("The list attribute of the asset is ASSTLIST")
            .trainingSentence("The list for the asset is ASSTLIST")
            .parameter("asstlist").fromFragment("ASSTLIST").entity(any());

        val createAssetAssertParameter = intent("CreateAssetAssertParameter")
            .trainingSentence("Yes, I want to have a parameter for this asset")
            .trainingSentence("Yes, I want to have more parameters for the asset")
            .trainingSentence("I want to build more parameters for this asset");

        val createAssetAssertRelationship = intent("CreateAssetAssertRelationship")
            .trainingSentence("Yes, I want to build a relationship for this asset")
            .trainingSentence("Yes, I want to build more relationships for the asset")
            .trainingSentence("I want to build more relationships for this asset");

        val createAssetDone = intent("CreateAssetDone")
            .trainingSentence("No, that's all for the asset")
            .trainingSentence("This asset is completed")
            .trainingSentence("No, I don't want to change the asset anymore");

        val createAssetSetIdentifier = intent("CreateAssetSetIdentifier")
            .trainingSentence("I want ASSTID to be the identifier for the asset")
            .trainingSentence("ASSTID should be the identifier for the asset")
            .trainingSentence("Make ASSTID the identifier for the asset")
            .parameter("asstid").fromFragment("ASSTID").entity(any());

        //***************************************************************************************************************************

        // Editing an asset

        val editAsset = intent("EditAsset")
            .trainingSentence("I want to edit an asset")
            .trainingSentence("Edit an asset")
            .trainingSentence("I wish to edit an asset")
            .trainingSentence("An asset should be edited");

        val editAssetGetName = intent("EditAssetGetName")
            .trainingSentence("The name of the asset that has to be edited is GEASSTNAME")
            .trainingSentence("The asset to be edited is GEASSTNAME")
            .trainingSentence("I want to edit the asset named GEASSTNAME")
            .parameter("geasstname").fromFragment("GEASSTNAME").entity(any());

        val editAssetConfirm = intent("EditAssetConfirm")
            .trainingSentence("Yes, edit this asset")
            .trainingSentence("Yes, I want to edit this asset");

        val editAssetChangeName = intent("EditAssetChangeName")
            .trainingSentence("I want to change the name of the asset")
            .trainingSentence("I want to edit the name of the asset")
            .trainingSentence("I want to rename the asset");

        val editAssetChangeIdentifier = intent("EditAssetChangeIdentifier")
            .trainingSentence("I want to change the identifier for the asset")
            .trainingSentence("I want to update the identifier for the asset");

        val editAssetEditParameter = intent("EditAssetEditParameter")
            .trainingSentence("I want to edit a parameter for the asset")
            .trainingSentence("I want to update a parameter for the asset")
            .trainingSentence("Edit a parameter of the asset");

        val editAssetDeleteParameter = intent("EditAssetDeleteParameter")
            .trainingSentence("I want to delete a parameter for the asset")
            .trainingSentence("I want to remove a parameter for the asset")
            .trainingSentence("Delete a parameter of the asset");

        //***************************************************************************************************************************

        // Deleting an asset

        val deleteAsset = intent("DeleteAsset")
            .trainingSentence("I want to delete an asset")
            .trainingSentence("Delete an asset")
            .trainingSentence("I wish to delete an asset")
            .trainingSentence("An asset should be deleted");

        val deleteAssetGetName = intent("DeleteAssetGetName")
            .trainingSentence("The name of the asset that has to be deleted is GDASSTNAME")
            .trainingSentence("The asset to be deleted is GDASSTNAME")
            .trainingSentence("I want the participant named GDASSTNAME to be deleted")
            .parameter("gdasstname").fromFragment("GDASSTNAME").entity(any());

        val deleteAssetConfirm = intent("DeleteAssetConfirm")
            .trainingSentence("Yes, delete this asset")
            .trainingSentence("Yes, you will have to delete this asset");

        //***************************************************************************************************************************

        // Reading an asset

        val readAsset = intent("ReadAsset")
            .trainingSentence("I want to read an asset")
            .trainingSentence("Read an asset")
            .trainingSentence("I wish to read an asset")
            .trainingSentence("An asset should be read");

        val readAssetGetName = intent("ReadAssetGetName")
            .trainingSentence("The name of the asset that has to be read is GRASSTNAME")
            .trainingSentence("The asset to be read is GRASSTNAME")
            .trainingSentence("I want to read the asset named GRASSTNAME")
            .trainingSentence("The name of the asset that I want to read is GRASSTNAME")
            .parameter("grasstname").fromFragment("GRASSTNAME").entity(any());

        val readAssetConfirm = intent("ReadAssetConfirm")
            .trainingSentence("Yes, I want to read this asset")
            .trainingSentence("Yes, show me this asset");

        //***************************************************************************************************************************

        // Transactions-----------------------------------------------------------------------------------------

        // Creating a transaction

        val createTransaction = intent("CreateTransaction")
            .trainingSentence("I want to create a transaction")
            .trainingSentence("Create a transaction")
            .trainingSentence("I wish to create a transaction")
            .trainingSentence("A transaction should be created");

        val createTransactionSetName = intent("CreateTransactionSetName")
            .trainingSentence("The name of the transaction should be TRANNAME")
            .trainingSentence("The name of the transaction is TRANNAME")
            .trainingSentence("I want to name the transaction as TRANNAME")
            .trainingSentence("Set the name of the transaction as TRANNAME")
            .parameter("tranname").fromFragment("TRANNAME").entity(any());

        val createTransactionSetList = intent("CreateTransactionSetList")
            .trainingSentence("The list attribute of the asset is TRANLIST")
            .trainingSentence("The list for the asset is TRANLIST")
            .parameter("tranlist").fromFragment("TRANLIST").entity(any());

        val createTransactionCondition = intent("CreateTransactionCondition")
            .trainingSentence("Yes, I would like to have condition for the transaction")
            .trainingSentence("Yes, I want conditions for thr transaction");

        val createTransactionConditionName = intent("CreateTransactionConditionName")
            .trainingSentence("The name of the condition is TRANCONDNAME")
            .trainingSentence("Name the conditon as TRANCONDNAME")
            .trainingSentence("The name of the condition should be TRANCONDNAME")
            .parameter("trancondname").fromFragment("TRANCONDNAME").entity(any());

        val createTransactionConditionCondition =  intent("CreateTransactionConditionCondition")
            .trainingSentence("The condition is TRANCONDCOND")
            .trainingSentence("The condition for the transaction should be TRANCONDCOND")
            .trainingSentence("The condition would be TRANCONDCOND")
            .parameter("trancondcond").fromFragment("TRANCONDCOND").entity(any());

        val createTransactionConditionType = intent("CreateTransactionConditionType")
            .trainingSentence("The condition would be of type PREPOST")
            .trainingSentence("It is a PREPOST")
            .parameter("prepost").fromFragment("PREPOST").entity(any());

        val createTransactionAssertCondition = intent("CreateTransactionAssertCondition")
            .trainingSentence("Yes, I want to have another conditon for this transaction")
            .trainingSentence("Yes, I want to have more conditions for the transaction")
            .trainingSentence("I want to build more conditions for this transaction");

        val createTransactionDenyCondition = intent("CreateTransactionAssertCondition")
            .trainingSentence("No, I don't want to have another conditon for this transaction")
            .trainingSentence("I don't want to have any more conditions for the transaction")
            .trainingSentence("I don't want any more transactions");

        val createTransactionConditionDone = intent("CreateTransactionConditionDone")
            .trainingSentence("That's all for the conditions for the transaction")
            .trainingSentence("I don't want to have any more conditions for the transaction");

        val createTransactionSetMostrar = intent("CreateTransactionSetMostrar")
            .trainingSentence("The attribute mostrar should be TRANMOST")
            .trainingSentence("Mostrar for the transaction is TRANMOST")
            .parameter("tranmost").fromFragment("TRANMOST").entity(any());

        val createTransactionAssertParameter = intent("CreateTransactionAssertParameter")
            .trainingSentence("Yes, I want to have a parameter for this transaction")
            .trainingSentence("Yes, I want to have more parameters for the transaction")
            .trainingSentence("I want to build more parameters for this transaction");

        val createTransactionAssertRelationship = intent("CreateTransactionAssertRelationship")
            .trainingSentence("Yes, I want to build a relationship for this transaction")
            .trainingSentence("Yes, I want to build more relationships for the transaction")
            .trainingSentence("I want to build more relationships for this transaction");

        val createTransactionAssertTranRelationship = intent("CreateTransactionAssertTranRelationship")
            .trainingSentence("It will be from a participant")
            .trainingSentence("The relationship will be from a participant");

        val createTransactionAssertAssetRelationship = intent("CreateTransactionAssertAssetRelationship")
            .trainingSentence("It will be from an asset")
            .trainingSentence("The relationship will be from an asset");

        val createTransactionRelationshipName = intent("CreateTransactionRelationshipName")
            .trainingSentence("The name of the relationship is RELNAME")
            .trainingSentence("Name the relationship as RELNAME")
            .trainingSentence("The name of the relationship should be RELNAME")
            .parameter("relname").fromFragment("RELNAME").entity(any());

        val createTransactionTranRelationshipFrom = intent("CreateTransactionTranRelationshipFrom")
            .trainingSentence("The participant for the relationship would be RELPART")
            .trainingSentence("The relationship would from the participant named RELPART")
            .parameter("relpart").fromFragment("RELPART").entity(any());

        val createTransactionAssetRelationshipFrom = intent("CreateTransactionAssetRelationshipFrom")
            .trainingSentence("The asset for the relationship would be RELASST")
            .trainingSentence("The relationship would from the asset named RELASST")
            .parameter("relasst").fromFragment("RELASST").entity(any());

        val createTransactionAssertRelationshipEvent = intent("CreateTransactionAssertRelationshipEvent")
            .trainingSentence("Yes, I want an event for the relationship")
            .trainingSentence("Yes, an event for the relationship has to be created");

        val createTransactionDenyRelationshipEvent = intent("CreateTransactionDenyRelationshipEvent")
            .trainingSentence("No, I don't want any event for the relationship")
            .trainingSentence("No event is required for the relationship")
            .trainingSentence("I don't want anymore of events for the relationship");

        val createTransactionRelationshipEventName = intent("CreateTransactionRelationshipEventName")
            .trainingSentence("The name of the event is EVENTNAME")
            .trainingSentence("Name of the event should be EVENTNAME")
            .parameter("eventname").fromFragment("EVENTNAME").entity(any());

        val createTransactionRelationshipEventDescription = intent("CreateTransactionRelationshipEventDescription")
            .trainingSentence("The event can be described by EVENTDESC")
            .trainingSentence("Event description - EVENTDESC")
            .trainingSentence("The description of the event is - EVENTDESC")
            .parameter("eventdesc").fromFragment("EVENTDESC").entity(any());

        val createTransactionDone = intent("CreateTransactionDone")
            .trainingSentence("No, that's all for the transaction")
            .trainingSentence("This transaction is completed")
            .trainingSentence("No, I don't want to change the transaction anymore");

        //***************************************************************************************************************************

        // Editing a transaction

        val editTransaction = intent("EditTransaction")
            .trainingSentence("I want to edit a transaction")
            .trainingSentence("Edit a transaction")
            .trainingSentence("I wish to edit a transaction")
            .trainingSentence("A transaction should be edited");

        val editTransactionGetName = intent("EditTransactionGetName")
            .trainingSentence("The name of the transaction that has to be edited is GETRANNAME")
            .trainingSentence("The transaction to be edited is GETRANNAME")
            .trainingSentence("I want to edit the transaction named GETRANNAME")
            .parameter("getranname").fromFragment("GETRANNAME").entity(any());

        val editTransactionConfirm = intent("EditTransactionConfirm")
            .trainingSentence("Yes, edit this transaction")
            .trainingSentence("Yes, I want to edit this transaction");

        val editTransactionChangeName = intent("EditTransactionChangeName")
            .trainingSentence("I want to change the name of the transaction")
            .trainingSentence("I want to edit the name of the transaction")
            .trainingSentence("I want to rename the transaction");

        val editTransactionEditParameter = intent("EditTransactionEditParameter")
            .trainingSentence("I want to edit a parameter for the transaction")
            .trainingSentence("I want to update a parameter for the transaction")
            .trainingSentence("Edit a parameter of the transaction");

        val editTransactionDeleteParameter = intent("EditTransactionDeleteParameter")
            .trainingSentence("I want to delete a parameter for the transaction")
            .trainingSentence("I want to remove a parameter for the transaction")
            .trainingSentence("Delete a parameter of the transaction");

        val editTransactionEditRelationship = intent("EditTransactionEditRelationship")
            .trainingSentence("I want to edit a relationship for the transaction")
            .trainingSentence("I want to update a relationship for the transaction")
            .trainingSentence("Edit a relationship of the transaction")
            .trainingSentence("I want to edit a relationship");

        val editTransactionDeleteRelationship = intent("EditTransactionDeleteRelationship")
            .trainingSentence("I want to delete a relationship for the transaction")
            .trainingSentence("I want to remove a relationship for the transaction")
            .trainingSentence("Delete a relationship of the transaction")
            .trainingSentence("I want to delete a transaction");

        val editTransactionEditCondition = intent("EditTransactionEditCondition")
            .trainingSentence("I want to edit a condition for the transaction")
            .trainingSentence("I want to update a condition for the transaction")
            .trainingSentence("Edit a condition of the transaction")
            .trainingSentence("I want to edit a condition");

        val editTransactionDeleteCondition = intent("EditTransactionDeleteCondition")
            .trainingSentence("I want to delete a condition for the transaction")
            .trainingSentence("I want to remove a condition for the transaction")
            .trainingSentence("Delete a condition of the transaction")
            .trainingSentence("I want to delete a condition");

        val editRelationshipGetName = intent("EditRelationshipGetName")
            .trainingSentence("The name of the relationship that has to be edited is GERELNAME")
            .trainingSentence("The relationship to be edited is GERELNAME")
            .trainingSentence("I want the relationship named GERELNAME to be edited")
            .parameter("gerelname").fromFragment("GERELNAME").entity(any());

        val editRelationshipConfirm = intent("EditRelationshipConfirm")
            .trainingSentence("Yes, edit this relationship")
            .trainingSentence("Yes, I want to edit this relationship")
            .trainingSentence("Yes, you will have to edit this relationship");

        val editRelationshipChangeName = intent("EditRelationshipChangeName")
            .trainingSentence("I want to change the name of the relationship")
            .trainingSentence("I want to edit the name of the relationship")
            .trainingSentence("I want to rename the relationship");

        val editRelationshipChangeFrom = intent("EditRelationshipChangeFrom")
            .trainingSentence("I want to change from where the relationship is directed")
            .trainingSentence("I want to change the from attribute of the relationship");

        val deleteRelationshipGetName = intent("DeleteRelationshipGetName")
            .trainingSentence("The name of the relationship that has to be deleted is GDRELNAME")
            .trainingSentence("The relationship to be deleted is GDRELNAME")
            .trainingSentence("I want the relationship named GDRELNAME to be deleted")
            .parameter("gdrelname").fromFragment("GDRELNAME").entity(any());

        val deleteRelationshipConfirm = intent("DeleteRelationshipConfirm")
            .trainingSentence("Yes, delete this relationship")
            .trainingSentence("Yes, you will have to delete this relationship");

        val editConditionGetName = intent("EditConditionGetName")
            .trainingSentence("The name of the condition that has to be edited is GECONDNAME")
            .trainingSentence("The condition to be edited is GECONDNAME")
            .trainingSentence("I want the condition named GECONDNAME to be edited")
            .parameter("gecondname").fromFragment("GECONDNAME").entity(any());

        val editConditionConfirm = intent("EditConditionConfirm")
            .trainingSentence("Yes, edit this condition")
            .trainingSentence("Yes, I want to edit this condition")
            .trainingSentence("Yes, you will have to edit this condition");

        val editConditionChangeName = intent("EditConditionChangeName")
            .trainingSentence("I want to change the name of the condition")
            .trainingSentence("I want to edit the name of the condition")
            .trainingSentence("I want to rename the condition");

        val editConditionChangeCondition = intent("EditConditionChangeCondition")
            .trainingSentence("I want to change the relation in the condition")
            .trainingSentence("I want to change the condition stated here");

        val deleteConditionGetName = intent("DeleteConditionGetName")
            .trainingSentence("The name of the condition that has to be deleted is GDCONDNAME")
            .trainingSentence("The condition to be deleted is GDCONDNAME")
            .trainingSentence("I want the condition named GDCONDNAME to be deleted")
            .parameter("gdcondname").fromFragment("GDCONDNAME").entity(any());

        val deleteConditionConfirm = intent("DeleteConditionConfirm")
            .trainingSentence("Yes, delete this condition")
            .trainingSentence("Yes, you will have to delete this condition");

        //***************************************************************************************************************************

        // Deleting a transaction

        val deleteTransaction = intent("DeleteTransaction")
            .trainingSentence("I want to delete a transaction")
            .trainingSentence("Delete a transaction")
            .trainingSentence("I wish to delete a transaction")
            .trainingSentence("A transaction should be deleted");

        val deleteTransactionGetName = intent("DeleteTransactionGetName")
            .trainingSentence("The name of the transaction that has to be deleted is GDTRANNAME")
            .trainingSentence("The transaction to be deleted is GDTRANNAME")
            .trainingSentence("I want the transaction named GDTRANNAME to be deleted")
            .parameter("gdtranname").fromFragment("GDTRANNAME").entity(any());

        val deleteTransactionConfirm = intent("DeleteTransactionConfirm")
            .trainingSentence("Yes, delete this transaction")
            .trainingSentence("Yes, you will have to delete this transaction");

        //***************************************************************************************************************************

        // Reading a transaction
        val readTransaction = intent("ReadTransaction")
            .trainingSentence("I want to read a transaction")
            .trainingSentence("Read a transaction")
            .trainingSentence("I wish to read a transaction")
            .trainingSentence("A transaction should be read");

        val readTransactionGetName = intent("ReadTransactionGetName")
            .trainingSentence("The name of the transaction that has to be read is GRTRANNAME")
            .trainingSentence("The transaction to be read is GRTRANNAME")
            .trainingSentence("I want to read the transaction named GRTRANNAME")
            .trainingSentence("The name of the transaction that I want to read is GRTRANNAME")
            .parameter("grtranname").fromFragment("GRTRANNAME").entity(any());

        val readTransactionConfirm = intent("ReadTransactionConfirm")
            .trainingSentence("Yes, I want to read this transaction")
            .trainingSentence("Yes, show me this transaction");

        //***************************************************************************************************************************

        // Parameter------------------------------------------------------------------------------------------------

        val createAssertParameter = intent("CreateParameterAssert")
            .trainingSentence("I want to create a parameter")
            .trainingSentence("I want to build a parameter")
            .trainingSentence("I want to have another parameter");

        val createParameterSetName = intent("CreateParameterSetName")
            .trainingSentence("The name of the parameter should be PARANAME")
            .trainingSentence("The name of the parameter is PARANAME")
            .trainingSentence("I want to name the parameter as PARANAME")
            .trainingSentence("Set the name of the parameter as PARANAME")
            .trainingSentence("The parameter is PARANAME")
            .parameter("paraname").fromFragment("PARANAME").entity(any());

        val createParameterSetType = intent("CreateParameterSetType")
            .trainingSentence("The type of the parameter should be PARATYPE")
            .trainingSentence("The type of the parameter is PARATYPE")
            .trainingSentence("Set the type of the parameter as PARATYPE")
            .parameter("paratype").fromFragment("PARATYPE").entity(any());

        val deleteParameterGetName = intent("DeleteParameterGetName")
            .trainingSentence("The name of the parameter that has to be deleted is GDPARANAME")
            .trainingSentence("The parameter to be deleted is GDPARANAME")
            .trainingSentence("I want the parameter named GDPARANAME to be deleted")
            .parameter("gdparaname").fromFragment("GDPARANAME").entity(any());

        val deleteParameterConfirm = intent("DeleteParameterConfirm")
            .trainingSentence("Yes, delete this parameter")
            .trainingSentence("Yes, you will have to delete this parameter");

        val editParameterGetName = intent("EditParameterGetName")
            .trainingSentence("The name of the parameter that has to be edited is GEPARANAME")
            .trainingSentence("The parameter to be edited is GEPARANAME")
            .trainingSentence("I want the parameter named GEPARANAME to be edited")
            .parameter("geparaname").fromFragment("GEPARANAME").entity(any());

        val editParameterConfirm = intent("EditParameterConfirm")
            .trainingSentence("Yes, edit this parameter")
            .trainingSentence("Yes, I want to edit this parameter")
            .trainingSentence("Yes, you will have to edit this parameter");

        val editParameterChangeName = intent("EditParameterChangeName")
            .trainingSentence("I want to change the name of the parameter")
            .trainingSentence("I want to edit the name of the parameter")
            .trainingSentence("I want to rename the parameter");

        val editParameterChangeType = intent("EditParameterChangeType")
            .trainingSentence("I want to change the type of the parameter")
            .trainingSentence("I want to edit the type of the parameter")
            .trainingSentence("I want to update the parameter");

        //***************************************************************************************************************************

        //***************************************************************************************************************************
        // Complete------------------------------------------------------------------------------------------------

        val contractDone = intent("ContractDone")
            .trainingSentence("That's all for the contract")
            .trainingSentence("The contract is done")
            .trainingSentence("That's all the information I wish to give for contract")
            .trainingSentence("The Smart Contract is done")
            .trainingSentence("Done")
            .trainingSentence("done")
            .trainingSentence("DONE");

        //***************************************************************************************************************************

        // Get the target platform for the code generation

        val getTargetPlatform = intent("GetTargetPlatform")
            .trainingSentence("The platform should be TARGPLAT")
            .trainingSentence("The smart contract must be genetated in TARGPLAT")
            .trainingSentence("The language of the smart contract must be TARGPLAT")
            .parameter("targplat").fromFragment("TARGPLAT").entity(any());

        val getCreator = intent("GetCreator")
            .trainingSentence("The creator must be CREA")
            .trainingSentence("The creator of the smart contract is CREA")
            .parameter("crea").fromFragment("CREA").entity(any()); 
        
        ReactPlatform reactPlatform = new ReactPlatform();
        ReactEventProvider reactEventProvider = reactPlatform.getReactEventProvider();
        ReactIntentProvider reactIntentProvider = reactPlatform.getReactIntentProvider();

        val init = state("Init");
        val awaitingInput = state("AwaitingInput");
        val handleCreateContract = state("HandleCreateContract");
        val handleEditContract = state("HandleEditContract");
        val handleCreateContractName = state("HandleCreateContractName");
        val handleGetEditContractName = state("HandleGetEditContractName");
        val handleNeutral = state("HandleNeutral");
        val handleCreateElement = state("HandleCreateElement");
        val handleEditElement = state("HandleEditElement");
        val handleDeleteElement = state("HandleDeleteElement");
        val handleReadElement = state("HandleReadElement");
        // States for creating participants
        val handleCreateParticipant = state("HandleCreateParticipant");
        val handleCreateParticipantSetName = state("HandleCreateParticipantSetName");
        val handleCreateParticipantSetList = state("HandleCreateParticipantSetList");
        val handleCreateParticipantDone = state("HandleCreateParticipantDone");
        val handleCreateParticipantSetIdentifier = state("HandleCreateParticipantSetIdentifier");
        val handleCreateParticipantAssertRelationship = state("HandleCreateParticipantAssertRelationship");
        // States for editing participants
        val handleEditParticipant = state("HandleEditParticipant");
        val handleEditParticipantGetName = state("HandleEditParticipantGetName");
        val handleEditParticipantConfirm = state("HandleEditParticipantConfirm");
        val handleEditParticipantChangeName = state("HandleEditParticipantChangeName");
        val handleEditParticipantSetName = state("HandleEditParticipantSetName");
        val handleEditParticipantChangeIdentifier = state("HandleEditParticipantChangeIdentifier");
        val handleEditParticipantEditParameter = state("HandleEditParticipantEditParameter");
        val handleEditParticipantDeleteParameter = state("HandleEditParticipantDeleteParameter");
        // States for deleting participants
        val handleDeleteParticipant = state("HandleDeleteParticipant");
        val handleDeleteParticipantGetName = state("HandleDeleteParticipantGetName");
        val handleDeleteParticipantConfirm = state("HandleDeleteParticipantConfirm");
        // States for reading participants
        val handleReadParticipant = state("HandleReadParticipant");
        val handleReadParticipantGetName = state("HandleReadParticipantGetName");
        val handleReadParticipantConfirm = state("HandleReadParticipantConfirm");
        // States for creating assets
        val handleCreateAsset = state("HandleCreateAsset");
        val handleCreateAssetSetType = state("HandleCreateAssetType");
        val handleCreateAssetSetName = state("HandleCreateAssetSetName");
        val handleCreateAssetSetList = state("HandleCreateAssetSetList");
        val handleCreateAssetDone = state("HandleCreateAssetDone");
        val handleCreateAssetSetIdentifier = state("HandleCreateAssetSetIdentifier");
        val handleCreateAssetAssertRelationship = state("HandleCreateAssetAssertRelationship");
        // States for editing assets
        val handleEditAsset = state("HandleEditAsset");
        val handleEditAssetGetName = state("HandleEditAssetGetName");
        val handleEditAssetConfirm = state("HandleEditAssetConfirm");
        val handleEditAssetChangeName = state("HandleEditAssetChangeName");
        val handleEditAssetSetName = state("HandleEditAssetSetName");
        val handleEditAssetChangeIdentifier = state("HandleEditAssetChangeIdentifier");
        val handleEditAssetEditParameter = state("HandleEditAssetEditParameter");
        val handleEditAssetDeleteParameter = state("HandleEditAssetDeleteParameter");
        // States for deleting assets
        val handleDeleteAsset = state("HandleDeleteAsset");
        val handleDeleteAssetGetName = state("HandleDeleteAssetGetName");
        val handleDeleteAssetConfirm = state("HandleDeleteAssetConfirm");
        // States for reading participants
        val handleReadAsset = state("HandleReadAsset");
        val handleReadAssetGetName = state("HandleReadAssetGetName");
        val handleReadAssetConfirm = state("HandleReadAssetConfirm");
        // States for creating transactions
        val handleCreateTransaction = state("HandleCreateTransaction");
        val handleCreateTransactionSetName = state("HandleCreateTransactionSetName");
        val handleCreateTransactionSetList = state("HandleCreateTransactionSetList");
        val handleCreateTransactionSetMostrar = state("HandleCreateTransactionSetMostrar");
        val handleCreateTransactionCondition = state("HandleCreateTransactionCondition");
        val handleCreateTransactionConditionName = state("HandleCreateTransactionConditionName");
        val handleCreateTransactionConditionCondition = state("HandleCreateTransactionConditionCondition");
        val handleCreateTransactionConditionType = state("HandleCreateTransactionConditionType");
        val handleCreateTransactionAssertCondition = state("HandleCreateTransactionAssertCondition");
        val handleCreateTransactionDenyCondition = state("HandleCreateTransactionDenyCondition");
        val handleCreateTransactionConditionDone = state("HandleCreateTransactionConditionDone");
        val handleCreateTransactionAssertRelationship = state("HandleCreateAssetAssertRelationship");
        val handleCreateTransactionAssertTranRelationship = state("HandleTransactionAssertTranRelationship");
        val handleCreateTransactionAssertAssetRelationship = state("HandleTransactionAssertAssetRelationship");
        val handleCreateTransactionTranRelationshipFrom = state("HandleCreateTransactionTranRelationshipFrom");
        val handleCreateTransactionAssetRelationshipFrom = state("HandleCreateTransactionAssetRelationshipFrom");
        val handleCreateTransactionRelationshipName = state("HandleCreateTransactionRelationshipName");
        val handleCreateTransactionDenyRelationshipEvent = state("HandleCreateTransactionDenyRelationshipEvent");
        val handleCreateTransactionAssertRelationshipEvent = state("HandleCreateTransactionAssertRelationshipEvent");
        val handleCreateTransactionRelationshipEventName = state("HandleCreateTransactionRelationshipEventName");
        val handleCreateTransactionRelationshipEventDescription = state("HandleCreateTransactionRelationshipEventDescription");
        val handleCreateTransactionDone = state("HandleCreateTransactionDone");
        // States for editing transactions
        val handleEditTransaction = state("HandleEditTransaction");
        val handleEditTransactionGetName = state("HandleEditTransactionGetName");
        val handleEditTransactionConfirm = state("HandleEditTransactionConfirm");
        val handleEditTransactionChangeName = state("HandleEditTransactionChangeName");
        val handleEditTransactionSetName = state("HandleEditTransactionSetName");
        val handleEditTransactionEditParameter = state("HandleEditTransactionEditParameter");
        val handleEditTransactionDeleteParameter = state("HandleEditTransactionDeleteParameter");
        val handleEditTransactionEditRelationship = state("HandleEditTransactionEditRelationship");
        val handleEditTransactionDeleteRelationship = state("HandleEditTransactionDeleteRelationship");
        val handleEditTransactionEditCondition = state("HandleEditTransactionEditCondition");
        val handleEditTransactionDeleteCondition = state("HandleEditTransactionDeleteCondition");
        val handleEditRelationshipGetName = state("HandleEditRelationshipGetName");
        val handleEditRelationshipConfirm = state("HandleEditRelationshipConfirm");
        val handleEditRelationshipChangeName = state("HandleEditRelationshipChangeName");
        val handleEditRelationshipSetName = state("HandleEditRelationshipSetName");
        val handleEditRelationshipChangeFrom = state("HandleEditRelationshipChangeFrom");
        val handleEditAssetRelationshipSetFrom = state("HandleEditAssetRelationshipSetFrom");
        val handleEditTranRelationshipSetFrom = state("HandleEditTranRelationshipSetFrom");
        val handleDeleteRelationshipGetName = state("HandleDeleteRelationshipGetName");
        val handleDeleteRelationshipConfirm = state("HandleDeleteRelationshipConfirm");
        val handleEditConditionGetName = state("HandleEditConditionGetName");
        val handleEditConditionConfirm = state("HandleEditConditionConfirm");
        val handleEditConditionChangeName = state("HandleEditConditionChangeName");
        val handleEditConditionSetName = state("HandleEditConditionSetName");
        val handleEditConditionChangeCondition = state("HandleEditConditionChangeCondition");
        val handleEditConditionSetCondition = state("HandleEditConditionSetCondition");
        val handleDeleteConditionGetName = state("HandleDeleteConditionGetName");
        val handleDeleteConditionConfirm = state("HandleDeleteConditionConfirm");
        // States for deleting transactions
        val handleDeleteTransaction = state("HandleDeleteTransaction");
        val handleDeleteTransactionGetName = state("HandleDeleteTransactionGetName");
        val handleDeleteTransactionConfirm = state("HandleDeleteTransactionConfirm");
        // States for reading transactions
        val handleReadTransaction = state("HandleReadTransaction");
        val handleReadTransactionGetName = state("HandleReadTransactionGetName");
        val handleReadTransactionConfirm = state("HandleReadTransactionConfirm");
        // This is for the parameters
        // The intent that the bot is detecting in case of parameters is same across all three elements
        val handleCreateParticipantAssertParameter = state("HandleCreateParticipantAssertParameter");
        val handleCreateAssetAssertParameter = state("HandleCreateAssetAssertParameter");
        val handleCreateTransactionAssertParameter = state("HandleCreateAssetAssertParameter");
        val handleCreateAssertParameter = state("HandleCreateAssertParameter"); 
        val handleCreateParameterSetName = state("HandleParameterSetName");
        val handleCreateParameterSetType = state("HandleParameterSetType");
        val handleDeleteParameterGetName = state("HandleDeleteParameterGetName");
        val handleDeleteParameterConfirm = state("HandleDeleteParameterConfirm");
        val handleEditParameterGetName = state("HandleEditParamterGetName");
        val handleEditParameterConfirm = state("HandleEditParameterConfirm");
        val handleEditParameterChangeName = state("HandleEditParameterChangeName");
        val handleEditParameterChangeType = state("HandleEditParameterChangeType");
        val handleEditParameterSetName = state("HandleEditParameterSetName");
        val handleEditParameterSetType = state("HandleEditParameterSetType");
        // The user has provided all the information from his/her side
        val handleDone = state("HandleDone");
        val handleGetTargetPlatform = state("HandleGetTargetPlatform");
        val handleGetCreator = state("HandleGetCreator");
        val handleComplete = state("handleComplete");


        init
            .next()
            .when(eventIs(ReactEventProvider.ClientReady)).moveTo(awaitingInput);

        //Ask the user if they wish to create or edit a contract. This is the first line that the user says.
        awaitingInput
            .body(context -> {
                    reactPlatform.reply(context, "Hey there!! I am iContractBot. I am here to guide you to create and edit smart contracts. Do you want to create a smart contract?");
            })
            .next()
            .when(intentIs(createContract)).moveTo(handleCreateContract);

        //When the user wants to create a smart contract
        handleCreateContract
            .body(context -> {
                    reactPlatform.reply(context, "That's great. I will help you to create a smart contract. What would you like to call your contract?");
            })
            .next()
            .when(intentIs(contractName)).moveTo(handleCreateContractName);

        //When the user wants to edit a smart contract
        // handleEditContract
        //     .body(context -> {
        //             reactPlatform.reply(context, "That's great. I will help you to edit the smart contract. What's the name of the contract that you want to edit?");
        //     })
        //     .next()
        //     .when(intentIs(contractName)).moveTo(handleGetEditContractName);

        // After the user gives instructions to give the name to the contract
        // The user can only create elements from here.
        handleCreateContractName
            .body(context -> {
                    String contractname = (String) context.getIntent().getValue("cont");
                    reactPlatform.reply(context, "Wow. Great Job. So we are going to create a smart contract with the name " + contractname + ".");
                    reactPlatform.reply(context, "So, what do you wish do now? Would you like to create some elements in the smart contract?");
                    a1.set(contractname);
                    setContractName(a1);
            })
            .next()
            .when(intentIs(createElement)).moveTo(handleCreateElement)
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        handleCreateElement
            .body(context -> {
                    reactPlatform.reply(context, "That's perfect. Which element would would you like to create now? Do you want to create a participant or an asset or a transaction?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction);

        handleEditElement
            .body(context -> {
                    reactPlatform.reply(context, "Okay! That's great! Which element would would you like to edit now? Do you want to edit a participant or an asset or a transaction?");
            })
            .next()
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction);

        handleDeleteElement
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which element would would you like to delete now? Do you want to delete a participant or an asset or a transaction?");
            })
            .next()
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction);

        handleReadElement
            .body(context -> {
                    reactPlatform.reply(context, "That's cool. Which element would would you like to see now? Do you want to read a participant or an asset or a transaction?");
            })
            .next()
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction);

        // Get the name of the contract that the user wants to edit
        // handleGetEditContractName
        //     .body(context -> {
        //             String contractname = (String) context.getIntent().getValue("cName");
        //             reactPlatform.reply(context, "That's great. Lemme find the" + contractname + "smart contract in the contract in the directory. What would you like to do next? Do you want to create/edit/delete/read some elements in the smart contract?");
        //             a1.set("Contract : " + contractname + "\n");
        //             setContractName(a1);
        //     })
        //     .next()
        //     .moveTo(awaitingInput);
            // .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            // .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            // .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            // .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            // .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            // .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            // .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            // .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            // .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            // .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            // .when(intentIs(editAsset)).moveTo(handleEditAsset)
            // .when(intentIs(readAsset)).moveTo(handleReadAsset);

        /*
        Creating a participant***************************************************************************************************************************
        */

        // The user says that he/she wants to create a participant
        // The creator attribute is being set to FALSE by default. The bot will ask in which participant is to be made the creator or owner of the smart contract
        handleCreateParticipant
            .body(context -> {
                    reactPlatform.reply(context, "Looks like we are heading somewhere :). What would you like to name the participant?");
                    a1.set("Participant"+"\n"+"(Creator: False) {\n");
                    funcCreateParticipant();
            })
            .next()
            .when(intentIs(createParticipantSetName)).moveTo(handleCreateParticipantSetName);

        // Detect and store the name of the participant
        handleCreateParticipantSetName
            .body(context -> {
                    String partname = (String) context.getIntent().getValue("partname");
                    reactPlatform.reply(context, "Awesome. So, a participant needs to have at least one parameter. Hence, our next step would be creating a parameter for the participant.");
                    reactPlatform.reply(context, "So, what would you like to name the parameter?");
                    a1.set(partname);
                    updateParticipants(a1, 0);
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        // Detect and store the list attribute of the participant
        // handleCreateParticipantSetList
        //     .body(context -> {
        //             String partlist = (String) context.getIntent().getValue("partlist");
        //             reactPlatform.reply(context, "Great!! We are moving in the right direction. What would be the name of the first parameter of the participant?");
        //             a1.set(partlist);
        //             updateParticipants(a1, 1);
        //             funcCreateParameter();
        //     })  
        //     .next()
        //     .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateParticipantAssertParameter
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the parameter?");
                    funcCreateParameter();
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateParticipantDone
            .body(context -> {
                    reactPlatform.reply(context, "Wow. Great Job. So we are almost there. Which parameter should be the identifier of the participant?");
                    // a1.set("}");
                    // updateParticipants(a1, 1);
            })
            .next()
            .when(intentIs(createParticipantSetIdentifier)).moveTo(handleCreateParticipantSetIdentifier);

        handleCreateParticipantSetIdentifier
            .body(context -> {
                    String partid = (String) context.getIntent().getValue("partid");
                    reactPlatform.reply(context, "And.... we have created a participant. What would you like to do now? Would you like to define create some more elements for the smart contract?");
                    a1.set(partid);
                    updateParticipants(a1, 2);
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Editing a participant***************************************************************************************************************************
        */

        handleEditParticipant
            .body(context -> {
                    reactPlatform.reply(context, "Great. Which participant would you like to edit?");
                    updateElementIndexNumber(participantIndexNumber);
            })
            .next()
            .when(intentIs(editParticipantGetName)).moveTo(handleEditParticipantGetName);

        handleEditParticipantGetName
            .body(context -> {
                    String gepartname = (String) context.getIntent().getValue("gepartname");
                    String closestName = findClosestName(gepartname, 1);
                    reactPlatform.reply(context, "Okay. So you want to edit the participant named " + gepartname + ", right?");
                    a1.set(closestName);
                    updateParticipants(a1, 3);
            })
            .next()
            .when(intentIs(editParticipantConfirm)).moveTo(handleEditParticipantConfirm);

        handleEditParticipantConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Thanks for letting me know! What changes would you like to make to the participant?");
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParameterSetName)
            .when(intentIs(editParticipantChangeName)).moveTo(handleEditParticipantChangeName)
            .when(intentIs(editParticipantChangeIdentifier)).moveTo(handleEditParticipantChangeIdentifier)
            .when(intentIs(editParticipantDeleteParameter)).moveTo(handleEditParticipantDeleteParameter);

        handleEditParticipantChangeName
            .body(context -> {
                    reactPlatform.reply(context, "That's cool. What should be the new name of the participant?");
            })
            .next()
            .when(intentIs(createParticipantSetName)).moveTo(handleEditParticipantSetName);

        handleEditParticipantSetName
            .body(context -> {
                    String partname = (String) context.getIntent().getValue("partname");
                    reactPlatform.reply(context, "Awesome. The name of the participant has been changed to " + partname + ". What would you like to do now? Would you like to continue editing the participant?");
                    a1.set(partname);
                    updateParticipants(a1, 0);
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        handleEditParticipantChangeIdentifier
            .body(context -> {
                    reactPlatform.reply(context, "That's great. Which parameter should be the identifier of the participant?");
            })
            .next()
            .when(intentIs(createParticipantSetIdentifier)).moveTo(handleCreateParticipantSetIdentifier);

        handleEditParticipantEditParameter
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which parameter would you like to edit?");
            })
            .next()
            .when(intentIs(editParameterGetName)).moveTo(handleEditParameterGetName);

        handleEditParticipantDeleteParameter
            .body(context -> {
                    reactPlatform.reply(context, "Okay... Which parameter would you like to like to delete?");
            })
            .next()
            .when(intentIs(deleteParameterGetName)).moveTo(handleDeleteParameterGetName);


        //*****************************************************************************************************************************************

        /*
        Deleting a participant***************************************************************************************************************************
        */

        /*
        We can use here one technique. We can ask the user for one confirmation. It would help us to do a spell-check. This would ensure that when we are dealing 
        with the correct spelling when we are contacting the model.
        */

        handleDeleteParticipant
            .body(context -> {
                    updateElementIndexNumber(participantIndexNumber);
                    reactPlatform.reply(context, "Okay. Which participant would you like to delete?");
            })
            .next()
            .when(intentIs(deleteParticipantGetName)).moveTo(handleDeleteParticipantGetName);

        handleDeleteParticipantGetName
            .body(context -> {
                    String gdpartname = (String) context.getIntent().getValue("gdpartname");
                    String closestName = findClosestName(gdpartname, 1);
                    reactPlatform.reply(context, "Are you sure that you want to delete the participant named " + gdpartname + "?" );
                    a1.set(closestName);
                    updateParticipants(a1, 3);
            })
            .next()
            .when(intentIs(deleteParticipantConfirm)).moveTo(handleDeleteParticipantConfirm);

        handleDeleteParticipantConfirm
            .body(context -> {
                    funcDeleteParticipant();
                    reactPlatform.reply(context, "That's cool. I have completed the deletion. What would you like to do now");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Reading a participant***************************************************************************************************************************
        */

        handleReadParticipant
            .body(context -> {
                updateElementIndexNumber(participantIndexNumber);
                    reactPlatform.reply(context, "Okay. Which participant would you like to read?");
            })
            .next()
            .when(intentIs(readParticipantGetName)).moveTo(handleReadParticipantGetName);

        handleReadParticipantGetName
            .body(context -> {
                    String grpartname = (String) context.getIntent().getValue("grpartname");
                    reactPlatform.reply(context, "Are you sure that you want to read the participant named " + grpartname + "?" );
            })
            .next()
            .when(intentIs(readParticipantConfirm)).moveTo(handleReadParticipantConfirm);

        handleReadParticipantConfirm
            .body(context -> {
                    String rpartname = funcReadParticipant();
                    reactPlatform.reply(context, "That's cool. Have a look at the participant." + rpartname + "What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************


        /*
        Creating an asset***************************************************************************************************************************
        */

        // The user says that he/she wants to create an asset
        handleCreateAsset
            .body(context -> {
                    reactPlatform.reply(context, "Looks like we are heading somewhere :). What should be the name of the asset?");
                    funcCreateAsset();
            })
            .next()
            .when(intentIs(createAssetSetName)).moveTo(handleCreateAssetSetName);

        // Set the type attribute of the asset
        // handleCreateAssetSetType
        //     .body(context -> {
        //             String assttype = (String) context.getIntent().getValue("assttype");
        //             reactPlatform.reply(context, "Perfect. What would you like to name the asset?");
        //             a1.set(assttype);
        //             updateAssets(a1, 0);
        //     })
        //     .next()
        //     .when(intentIs(createAssetSetName)).moveTo(handleCreateAssetSetName);

        // Detect and store the name of the asset
        handleCreateAssetSetName
            .body(context -> {
                    String asstname = (String) context.getIntent().getValue("asstname");
                    reactPlatform.reply(context, "Awesome. So, an asset needs to have at least one parameter. Hence, out next step would be creating a parameter for the asset.");
                    reactPlatform.reply(context, "So, what should be the name of the parameter?");
                    a1.set(asstname);
                    updateAssets(a1, 1);
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        // Detect and store the list attribute of the asset
        // handleCreateAssetSetList
        //     .body(context -> {
        //             String asstlist = (String) context.getIntent().getValue("asstlist");
        //             reactPlatform.reply(context, "Great!! We are moving in the right direction. What would be the name of the first parameter of the asset?");
        //             a1.set(asstlist);
        //             updateAssets(a1, 2);
        //             funcCreateParameter();
        //     })
        //     .next()
        //     .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateAssetAssertParameter
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the parameter?");
                    funcCreateParameter();
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateAssetDone
            .body(context -> {
                    reactPlatform.reply(context, "Wow. Great Job. So we are almost there. Which parameter should be the identifier of the asset?");
                    // a1.set("}");
                    // updateParticipants(a1, 1);
            })
            .next()
            .when(intentIs(createParticipantSetIdentifier)).moveTo(handleCreateParticipantSetIdentifier);

        handleCreateAssetSetIdentifier
            .body(context -> {
                    String asstid = (String) context.getIntent().getValue("asstid");
                    reactPlatform.reply(context, "And.... we have created an asset. What would you like to do now? Would you like to define create some more elements for the smart contract?");
                    a1.set(asstid);
                    updateAssets(a1, 3);
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Editing an asset***************************************************************************************************************************
        */

        handleEditAsset
            .body(context -> {
                    reactPlatform.reply(context, "Great. Which asset would you like to edit?");
                    updateElementIndexNumber(assetIndexNumber);
            })
            .next()
            .when(intentIs(editAssetGetName)).moveTo(handleEditAssetGetName);

        handleEditAssetGetName
            .body(context -> {
                    String geasstname = (String) context.getIntent().getValue("geasstname");
                    String closestName = findClosestName(geasstname, 2);
                    reactPlatform.reply(context, "Are you sure that you want to read the asset named " + geasstname + "?" );
                    a1.set(closestName);
                    updateAssets(a1, 4);
            })
            .next()
            .when(intentIs(editAssetConfirm)).moveTo(handleEditAssetConfirm);

        handleEditAssetConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Thanks for letting me know! What changes would you like to make to the asset?");
            })
            .next()
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateParameterSetName)
            .when(intentIs(editAssetChangeName)).moveTo(handleEditAssetChangeName)
            .when(intentIs(editAssetChangeIdentifier)).moveTo(handleEditAssetChangeIdentifier)
            .when(intentIs(editAssetDeleteParameter)).moveTo(handleEditAssetDeleteParameter);

            handleEditAssetChangeName
            .body(context -> {
                    reactPlatform.reply(context, "That's cool. What should be the new name of the asset?");
            })
            .next()
            .when(intentIs(createAssetSetName)).moveTo(handleEditAssetSetName);

        handleEditAssetSetName
            .body(context -> {
                    String asstname = (String) context.getIntent().getValue("asstname");
                    reactPlatform.reply(context, "Awesome. The name of the asset has been changed to " + asstname + ". What would you like to do now? Would you like to continue editing the participant?");
                    a1.set(asstname);
                    updateAssets(a1, 1);
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        handleEditAssetChangeIdentifier
            .body(context -> {
                    reactPlatform.reply(context, "That's great. Which parameter should be the identifier of the asset?");
            })
            .next()
            .when(intentIs(createAssetSetIdentifier)).moveTo(handleCreateAssetSetIdentifier);

        handleEditAssetEditParameter
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which parameter would you like to edit?");
            })
            .next()
            .when(intentIs(editParameterGetName)).moveTo(handleEditParameterGetName);

        handleEditAssetDeleteParameter
            .body(context -> {
                    reactPlatform.reply(context, "Okay... Which parameter would you like to like to delete?");
            })
            .next()
            .when(intentIs(deleteParameterGetName)).moveTo(handleDeleteParameterGetName);

        //*****************************************************************************************************************************************

        /*
        Deleting an asset***************************************************************************************************************************
        */

        handleDeleteAsset
            .body(context -> {
                    updateElementIndexNumber(assetIndexNumber);
                    reactPlatform.reply(context, "Okay. Which asset would you like to delete?");
            })
            .next()
            .when(intentIs(deleteAssetGetName)).moveTo(handleDeleteAssetGetName);

        handleDeleteAssetGetName
            .body(context -> {
                    String gdasstname = (String) context.getIntent().getValue("gdasstname");
                    String closestName = findClosestName(gdasstname, 2);
                    reactPlatform.reply(context, "Are you sure that you want to delete the asset named " + gdasstname + "?" );
                    a1.set(closestName);
                    updateAssets(a1, 4);
            })
            .next()
            .when(intentIs(deleteAssetConfirm)).moveTo(handleDeleteAssetConfirm);

        handleDeleteAssetConfirm
            .body(context -> {
                    funcDeleteAsset();
                    reactPlatform.reply(context, "That's cool. I have completed the deletion. What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Reading an asset***************************************************************************************************************************
        */

        handleReadAsset
            .body(context -> {
                    updateElementIndexNumber(assetIndexNumber);
                    reactPlatform.reply(context, "Okay. Which asset would you like to read?");
            })
            .next()
            .when(intentIs(readAssetGetName)).moveTo(handleReadAssetGetName);

        handleReadAssetGetName
            .body(context -> {
                    String grasstname = (String) context.getIntent().getValue("grasstname");
                    String closestName = findClosestName(grasstname, 2);
                    reactPlatform.reply(context, "Are you sure that you want to read the asset named " + grasstname + "?" );
                    a1.set(closestName);
                    updateAssets(a1, 4);
            })
            .next()
            .when(intentIs(readAssetConfirm)).moveTo(handleReadAssetConfirm);

        handleReadAssetConfirm
            .body(context -> {
                    String rasstname = funcReadAsset();
                    reactPlatform.reply(context, "That's cool. Have a look at the asset." + rasstname + "What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

         //*****************************************************************************************************************************************

        /*
        Creating a transaction***************************************************************************************************************************
        */

        // The user says that he/she wants to create a transaction
        handleCreateTransaction
            .body(context -> {
                    reactPlatform.reply(context, "Looks like we are heading somewhere :). What would you like to name the transaction?");
                    funcCreateTransaction();
            })
            .next()
            .when(intentIs(createTransactionSetName)).moveTo(handleCreateTransactionSetName);

        // Detect and store the name of the transaction
        handleCreateTransactionSetName
            .body(context -> {
                    String tranname = (String) context.getIntent().getValue("tranname");
                    reactPlatform.reply(context, "Awesome. We are moving in the right direction. Would you like to have conditions for the transaction?");
                    a1.set(tranname);
                    updateTransactions(a1, 0);
            })
            .next()
            .when(intentIs(createTransactionAssertCondition)).moveTo(handleCreateTransactionAssertCondition)
            .when(intentIs(createTransactionDenyCondition)).moveTo(handleCreateTransactionDenyCondition);

        // handleCreateTransactionSetList
        //     .body(context -> {
        //             String tranlist = (String) context.getIntent().getValue("tranlist");
        //             reactPlatform.reply(context, "Great!! We are moving in the right direction. What would be the mostrar attribute of the ?");
        //             a1.set(tranlist);
        //             updateTransactions(a1, 1);
        //     })
        //     .next()
        //     .when(intentIs(createTransactionSetMostrar)).moveTo(handleCreateTransactionSetMostrar);



        // Ask for the mostrar attribute of the transaction
        // handleCreateTransactionSetMostrar
        //     .body(context -> {
        //             String tranmost = (String) context.getIntent().getValue("tranmost");
        //             reactPlatform.reply(context, "Great!! Would you like to have conditions for the transaction?");
        //             a1.set(tranmost);
        //             updateTransactions(a1, 2);
        //     })
        //     .next()
        //     .when(intentIs(createTransactionAssertCondition)).moveTo(handleCreateTransactionAssertCondition);

        handleCreateTransactionCondition
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the condition?");
            })
            .next()
            .when(intentIs(createTransactionConditionName)).moveTo(handleCreateTransactionConditionName);

        handleCreateTransactionConditionName
            .body(context -> {
                    String trancondname = (String) context.getIntent().getValue("trancondname");
                    reactPlatform.reply(context, "We are heading in the right direction. What is the condition?");
                    a1.set(trancondname);
                    updateConditions(a1, 0);
            })
            .next()
            .when(intentIs(createTransactionConditionCondition)).moveTo(handleCreateTransactionConditionCondition);

        handleCreateTransactionConditionCondition
            .body(context -> {
                    String trancondcond = (String) context.getIntent().getValue("trancondcond");
                    reactPlatform.reply(context, "Great. What would type the condition would be - will it be a precondition or a postcondition?");
                    a1.set(trancondcond);
                    updateConditions(a1, 1);
            })
            .next()
            .when(intentIs(createTransactionConditionType)).moveTo(handleCreateTransactionConditionType);

        handleCreateTransactionConditionType
            .body(context -> {
                    String prepost = (String) context.getIntent().getValue("prepost");
                    reactPlatform.reply(context, "Done. Would you like to have more conditions for the transaction?");
                    a1.set(prepost);
                    updateConditions(a1, 2);
            })
            .next()
            .when(intentIs(createTransactionAssertCondition)).moveTo(handleCreateTransactionAssertCondition)
            .when(intentIs(createTransactionDenyCondition)).moveTo(handleCreateTransactionDenyCondition);

        handleCreateTransactionDenyCondition
            .body(context -> {
                    reactPlatform.reply(context, "Ohh okay. So what would you like to do now? Would you like to have parameters or relationships for the transaction?");
            })
            .next()
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship);


        handleCreateTransactionAssertCondition
            .body(context -> {
                    reactPlatform.reply(context, "Great. What should be the name of the condition?");
                    funcCreateCondition();
            })
            .next()
            .when(intentIs(createTransactionConditionName)).moveTo(handleCreateTransactionConditionName);

        handleCreateTransactionConditionDone
            .body(context -> {
                    reactPlatform.reply(context, "Cool. What would you like to do now?");
            })
            .next()
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter);

        handleCreateTransactionAssertParameter
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the parameter?");
                    funcCreateParameter();
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateTransactionAssertRelationship
            .body(context -> {
                    reactPlatform.reply(context, "Great. Will the relationship from a participant or from an asset?");
            })
            .next()
            .when(intentIs(createTransactionAssertAssetRelationship)).moveTo(handleCreateTransactionAssertAssetRelationship)
            .when(intentIs(createTransactionAssertTranRelationship)).moveTo(handleCreateTransactionAssertTranRelationship);

        handleCreateTransactionAssertAssetRelationship
            .body(context -> {
                    reactPlatform.reply(context, "Great. To which asset would you like to have the relationship?");
                    funcCreateAssetRel();
            })
            .next()
            .when(intentIs(createTransactionAssetRelationshipFrom)).moveTo(handleCreateTransactionAssetRelationshipFrom);

        handleCreateTransactionAssertTranRelationship
            .body(context -> {
                    reactPlatform.reply(context, "Great. From which participant would you like to have the relationship?");
                    funcCreateTranRel();
            })
            .next()
            .when(intentIs(createTransactionTranRelationshipFrom)).moveTo(handleCreateTransactionTranRelationshipFrom);


        handleCreateTransactionTranRelationshipFrom
            .body(context -> {
                    String relpart = (String) context.getIntent().getValue("relpart");
                    reactPlatform.reply(context, "Great. Would you like to have any events for the relationship?");
                    a1.set(relpart);
                    funcUpdateRelationships(a1, 0);
            })
            .next()
            .when(intentIs(createTransactionAssertRelationshipEvent)).moveTo(handleCreateTransactionAssertRelationshipEvent);

        handleCreateTransactionAssetRelationshipFrom
            .body(context -> {
                    String relasst = (String) context.getIntent().getValue("relasst");
                    reactPlatform.reply(context, "Great. What should be the name of the relationship?");
                    a1.set(relasst);
                    funcUpdateRelationships(a1, 0);
            })
            .next()
            .when(intentIs(createTransactionRelationshipName)).moveTo(handleCreateTransactionRelationshipName);

        handleCreateTransactionRelationshipName
            .body(context -> {
                    String relname = (String) context.getIntent().getValue("relname");
                    reactPlatform.reply(context, "Great. Would you like to have any events for the relationship?");
                    a1.set(relname);
                    funcUpdateRelationships(a1, 1);
            })
            .next()
            .when(intentIs(createTransactionAssertRelationshipEvent)).moveTo(handleCreateTransactionAssertRelationshipEvent)
            .when(intentIs(createTransactionDenyRelationshipEvent)).moveTo(handleCreateTransactionDenyRelationshipEvent);

        handleCreateTransactionDenyRelationshipEvent
            .body(context -> {
                    reactPlatform.reply(context, "Cool. What do you wish to do now? Would you like to create more relationships for the transaction or are you done with the transaction?");
            })
            .next()
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);


        handleCreateTransactionAssertRelationshipEvent
            .body(context -> {
                    reactPlatform.reply(context, "Great. What should be the name of the event?");
            })
            .next()
            .when(intentIs(createTransactionRelationshipEventName)).moveTo(handleCreateTransactionRelationshipEventName);

        handleCreateTransactionRelationshipEventName
            .body(context -> {
                    String eventname = (String) context.getIntent().getValue("eventname");
                    reactPlatform.reply(context, "Great. Please describe the event.");
            })
            .next()
            .when(intentIs(createTransactionRelationshipEventDescription)).moveTo(handleCreateTransactionRelationshipEventDescription);

        handleCreateTransactionRelationshipEventDescription
            .body(context -> {
                    String eventdesc = (String) context.getIntent().getValue("eventdesc");
                    reactPlatform.reply(context, "Great. What do you wish to do now?");
            })
            .next()
            .when(intentIs(createTransactionAssertRelationshipEvent)).moveTo(handleCreateTransactionAssertRelationshipEvent)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        handleCreateTransactionDone
            .body(context -> {
                    reactPlatform.reply(context, "Wow. Great Job. So we have created a transaction. What do you wish to do now? Would you like to define create some more elements for the smart contract?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Editing a transaction***************************************************************************************************************************
        */

        handleEditTransaction
            .body(context -> {
                    reactPlatform.reply(context, "Great. Which transaction would you like to edit?");
                    updateElementIndexNumber(transactionIndexNumber);
            })
            .next()
            .when(intentIs(editTransactionGetName)).moveTo(handleEditTransactionGetName);

        handleEditTransactionGetName
            .body(context -> {
                    String getranname = (String) context.getIntent().getValue("getranname");
                    String closestName = findClosestName(getranname, 3);
                    reactPlatform.reply(context, "Okay. So you want to edit the transaction named " + getranname + ", right?");
                    a1.set(closestName);
                    updateTransactions(a1, 3);
            })
            .next()
            .when(intentIs(editTransactionConfirm)).moveTo(handleEditTransactionConfirm);

        handleEditTransactionConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Thanks for letting me know! What changes would you like to make to the transaction?");
            })
            .next()
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateParameterSetName)
            .when(intentIs(editTransactionChangeName)).moveTo(handleEditTransactionChangeName)
            .when(intentIs(editTransactionDeleteParameter)).moveTo(handleEditTransactionDeleteParameter);

        handleEditTransactionChangeName
            .body(context -> {
                    reactPlatform.reply(context, "That's cool. What should be the new name of the transaction?");
            })
            .next()
            .when(intentIs(createTransactionSetName)).moveTo(handleEditTransactionSetName);

        handleEditTransactionSetName
            .body(context -> {
                    String tranname = (String) context.getIntent().getValue("tranname");
                    reactPlatform.reply(context, "Awesome. The name of the transaction has been changed to " + tranname + ". What would you like to do now? Would you like to continue editing the transaction?");
                    a1.set(tranname);
                    updateTransactions(a1, 0);
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        handleEditTransactionEditParameter
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which parameter would you like to edit?");
            })
            .next()
            .when(intentIs(editParameterGetName)).moveTo(handleEditParameterGetName);

        handleEditTransactionDeleteParameter
            .body(context -> {
                    reactPlatform.reply(context, "Okay... Which parameter would you like to like to delete?");
            })
            .next()
            .when(intentIs(deleteParameterGetName)).moveTo(handleDeleteParameterGetName);

        handleEditTransactionEditRelationship
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which relationship would you like to edit?");
            })
            .next()
            .when(intentIs(editRelationshipGetName)).moveTo(handleEditRelationshipGetName);

        handleEditRelationshipGetName
            .body(context -> {
                    String gerelname = (String) context.getIntent().getValue("gerelname");
                    String closestName = findClosestName(gerelname, 4);
                    reactPlatform.reply(context, "Are you sure that you want to edit the relationship named " + gerelname + "?");
                    a1.set(closestName);
                    updateParameters(a1, 2);
            })
            .next()
            .when(intentIs(editRelationshipConfirm)).moveTo(handleEditRelationshipConfirm);

        handleEditRelationshipConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Cool. What do you want to change in the relationship?");
            })
            .next()
            .moveTo(awaitingInput);

        handleEditTransactionDeleteRelationship
            .body(context -> {
                    reactPlatform.reply(context, "Okay... Which relationship would you like to like to delete?");
            })
            .next()
            .when(intentIs(deleteRelationshipGetName)).moveTo(handleDeleteRelationshipGetName);

        handleEditTransactionEditCondition
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which condition would you like to edit?");
            })
            .next()
            .when(intentIs(editConditionGetName)).moveTo(handleEditConditionGetName);

        handleEditTransactionDeleteCondition
            .body(context -> {
                    reactPlatform.reply(context, "Okay... Which condition would you like to like to delete?");
            })
            .next()
            .when(intentIs(deleteConditionGetName)).moveTo(handleDeleteConditionGetName);

        handleEditRelationshipGetName
            .body(context -> {
                    String gerelname = (String) context.getIntent().getValue("gerelname");
                    String closestName = findClosestName(gerelname, 5);
                    reactPlatform.reply(context, "Are you sure that you want to edit the relationship named " + closestName + "?");
                    a1.set(closestName);
                    funcUpdateRelationships(a1, 2);
            })
            .next()
            .when(intentIs(editRelationshipConfirm)).moveTo(handleEditRelationshipConfirm);

        handleEditRelationshipConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Cool. What do you want to change in the relationship?");
            })
            .next()
            .when(intentIs(editRelationshipChangeName)).moveTo(handleEditRelationshipChangeName)
            .when(intentIs(editRelationshipChangeFrom)).moveTo(handleEditRelationshipChangeFrom);

        handleEditRelationshipChangeName
            .body(context -> {
                    reactPlatform.reply(context, "Great. What should be the new name of the relationship?");
            })
            .next()
            .when(intentIs(createTransactionRelationshipName)).moveTo(handleEditRelationshipSetName);

        handleEditRelationshipSetName
            .body(context -> {
                    String relname = (String) context.getIntent().getValue("relname");
                    reactPlatform.reply(context, "The name of the relationship has been changed!! What would you like to do now?");
                    a1.set(relname);
                    funcUpdateRelationships(a1, 1);
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantAssertRelationship)).moveTo(handleCreateParticipantAssertRelationship)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetAssertRelationship)).moveTo(handleCreateAssetAssertRelationship)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        handleEditRelationshipChangeFrom
            .body(context -> {
                    reactPlatform.reply(context, "Great. What should be the from attribute of the relationship?");
            })
            .next()
            .when(intentIs(createTransactionTranRelationshipFrom)).moveTo(handleEditTranRelationshipSetFrom)
            .when(intentIs(createTransactionAssetRelationshipFrom)).moveTo(handleEditAssetRelationshipSetFrom);

        handleEditTranRelationshipSetFrom
            .body(context -> {
                    String relpart = (String) context.getIntent().getValue("relpart");
                    reactPlatform.reply(context, "The from attribute of the relationship has been changed!! What would you like to do now?");
                    a1.set(relpart);
                    funcUpdateRelationships(a1, 0);
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantAssertRelationship)).moveTo(handleCreateParticipantAssertRelationship)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetAssertRelationship)).moveTo(handleCreateAssetAssertRelationship)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        handleEditAssetRelationshipSetFrom
            .body(context -> {
                    String relasst = (String) context.getIntent().getValue("relasst");
                    reactPlatform.reply(context, "The from attribute of the relationship has been changed!! What would you like to do now?");
                    a1.set(relasst);
                    funcUpdateRelationships(a1, 0);
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantAssertRelationship)).moveTo(handleCreateParticipantAssertRelationship)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetAssertRelationship)).moveTo(handleCreateAssetAssertRelationship)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        handleDeleteRelationshipGetName
            .body(context -> {
                    String gdrelname = (String) context.getIntent().getValue("gdrelname");
                    String closestName = findClosestName(gdrelname, 5);
                    reactPlatform.reply(context, "Are you sure that you want to delete the relationship named " + closestName + "?");
                    a1.set(closestName);
                    funcUpdateRelationships(a1, 2);
            })
            .next()
            .when(intentIs(deleteRelationshipConfirm)).moveTo(handleDeleteRelationshipConfirm);

        handleDeleteRelationshipConfirm
            .body(context -> {
                    funcDeleteRelationship();
                    reactPlatform.reply(context, "Cool. The relationship has been deleted. What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantAssertRelationship)).moveTo(handleCreateParticipantAssertRelationship)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetAssertRelationship)).moveTo(handleCreateAssetAssertRelationship)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        handleEditConditionGetName
            .body(context -> {
                    String gecondname = (String) context.getIntent().getValue("gecondname");
                    String closestName = findClosestName(gecondname, 6);
                    reactPlatform.reply(context, "Are you sure that you want to edit the condition named " + closestName + "?");
                    a1.set(closestName);
                    updateConditions(a1, 3);
            })
            .next()
            .when(intentIs(editConditionConfirm)).moveTo(handleEditConditionConfirm);

        handleEditConditionConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Cool. What do you want to change in the condition?");
            })
            .next()
            .when(intentIs(editConditionChangeName)).moveTo(handleEditConditionChangeName)
            .when(intentIs(editConditionChangeCondition)).moveTo(handleEditConditionChangeCondition);

        handleEditConditionChangeName
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the condition?");
            })
            .next()
            .when(intentIs(createTransactionConditionName)).moveTo(handleEditConditionSetName);

        handleEditConditionSetName
            .body(context -> {
                    String trancondname = (String) context.getIntent().getValue("gecondname");
                    reactPlatform.reply(context, "The name of the condition has been changed!! What would you like to do now?");
                    a1.set(trancondname);
                    updateConditions(a1, 0);
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantAssertRelationship)).moveTo(handleCreateParticipantAssertRelationship)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetAssertRelationship)).moveTo(handleCreateAssetAssertRelationship)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        handleEditConditionChangeCondition
            .body(context -> {
                    reactPlatform.reply(context, "Great. What should be the new condition?");
            })
            .next()
            .when(intentIs(createTransactionConditionCondition)).moveTo(handleEditConditionSetCondition);

        handleEditConditionSetCondition
            .body(context -> {
                    String trancondcond = (String) context.getIntent().getValue("gecondname");
                    reactPlatform.reply(context, "The condition has been changed!! What would you like to do now?");
                    a1.set(trancondcond);
                    updateConditions(a1, 1);
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantAssertRelationship)).moveTo(handleCreateParticipantAssertRelationship)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetAssertRelationship)).moveTo(handleCreateAssetAssertRelationship)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        handleDeleteConditionGetName
            .body(context -> {
                    String gdcondname = (String) context.getIntent().getValue("gdcondname");
                    String closestName = findClosestName(gdcondname, 6);
                    reactPlatform.reply(context, "Are you sure that you want to edit the condition named " + closestName + "?");
                    a1.set(closestName);
                    updateConditions(a1, 3);
            })
            .next()
            .when(intentIs(deleteConditionConfirm)).moveTo(handleDeleteConditionConfirm);

        handleDeleteConditionConfirm
            .body(context -> {
                    funcDeleteCondition();
                    reactPlatform.reply(context, "Cool. The condition has been deleted. What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantAssertRelationship)).moveTo(handleCreateParticipantAssertRelationship)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetAssertRelationship)).moveTo(handleCreateAssetAssertRelationship)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

         //*****************************************************************************************************************************************

        //*****************************************************************************************************************************************

        /*
        Deleting a transaction***************************************************************************************************************************
        */

        handleDeleteTransaction
            .body(context -> {
                    updateElementIndexNumber(transactionIndexNumber);
                    reactPlatform.reply(context, "Okay. Which transaction would you like to delete?");
            })
            .next()
            .when(intentIs(deleteTransactionGetName)).moveTo(handleDeleteTransactionGetName);

        handleDeleteTransactionGetName
            .body(context -> {
                    String gdtranname = (String) context.getIntent().getValue("gdtranname");
                    String closestName = findClosestName(gdtranname, 3);
                    reactPlatform.reply(context, "Are you sure that you want to delete the transaction named " + gdtranname + "?" );
                    a1.set(closestName);
                    updateTransactions(a1, 3);
            })
            .next()
            .when(intentIs(deleteTransactionConfirm)).moveTo(handleDeleteTransactionConfirm);

        handleDeleteTransactionConfirm
            .body(context -> {
                    funcDeleteTransaction();
                    reactPlatform.reply(context, "That's cool. I have completed the deletion. What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Reading a transaction***************************************************************************************************************************
        */

        handleReadTransaction
            .body(context -> {
                    updateElementIndexNumber(transactionIndexNumber);
                    reactPlatform.reply(context, "Okay. Which transaction would you like to read?");
            })
            .next()
            .when(intentIs(readTransactionGetName)).moveTo(handleReadTransactionGetName);

        handleReadTransactionGetName
            .body(context -> {
                    String grtranname = (String) context.getIntent().getValue("grtranname");
                    String closestName = findClosestName(grtranname, 3);
                    reactPlatform.reply(context, "Are you sure that you want to read the transaction named " + grtranname + "?" );
                    a1.set(closestName);
                    updateTransactions(a1, 3);
            })
            .next()
            .when(intentIs(readTransactionConfirm)).moveTo(handleReadTransactionConfirm);

        handleReadTransactionConfirm
            .body(context -> {
                    String rtrannname = funcReadTransaction();
                    reactPlatform.reply(context, "That's cool. Have a look at the transaction." + rtrannname + "What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
            .when(intentIs(editAsset)).moveTo(handleEditAsset)
            .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
            .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
            .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
            .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
            .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
            .when(intentIs(readAsset)).moveTo(handleReadAsset)
            .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Creating a parameter***************************************************************************************************************************
        */
        handleCreateParameterSetName
            .body(context -> {
                    String paraname = (String) context.getIntent().getValue("paraname");
                    reactPlatform.reply(context, "Great. What is the type of the parameter that you just created?");
                    a1.set(paraname);
                    updateParameters(a1, 0);
            })
            .next()
            .when(intentIs(createParameterSetType)).moveTo(handleCreateParameterSetType);

        handleCreateParameterSetType
            .body(context -> {
                    String paratype = (String) context.getIntent().getValue("paratype");
                    reactPlatform.reply(context, "Cool. Would you like to add or delete attributes from the element?");
                    a1.set(paratype);
                    updateParameters(a1, 1);
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(editParticipantChangeName)).moveTo(handleEditParticipantChangeName)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(editAssetChangeName)).moveTo(handleEditAssetChangeName)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        //*****************************************************************************************************************************************

        /*
        Editing a parameter***************************************************************************************************************************
        */
        handleEditParameterGetName
            .body(context -> {
                    String geparaname = (String) context.getIntent().getValue("geparaname");
                    String closestName = findClosestName(geparaname, 4);
                    reactPlatform.reply(context, "Are you sure that you want to edit the transaction named " + geparaname + "?");
                    a1.set(closestName);
                    updateParameters(a1, 2);
            })
            .next()
            .when(intentIs(editParameterConfirm)).moveTo(handleEditParameterConfirm);

        handleEditParameterConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Cool. Do you want to change the name or the type of the parameter?");
            })
            .next()
            .when(intentIs(editParameterChangeName)).moveTo(handleEditParameterChangeName)
            .when(intentIs(editParameterChangeType)).moveTo(handleEditParameterChangeType);

        handleEditParameterChangeName
            .body(context -> {
                    reactPlatform.reply(context, "Great. What should be the new name of the parameter?");
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleEditParameterSetName);

        handleEditParameterSetName
            .body(context -> {
                    String paraname = (String) context.getIntent().getValue("paraname");
                    reactPlatform.reply(context, "The name of the parameter has been changed!! What would you like to do now?");
                    a1.set(paraname);
                    updateParameters(a1, 0);
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        handleEditParameterChangeType
            .body(context -> {
                    reactPlatform.reply(context, "Great. What should be the new type of the parameter?");
            })
            .next()
            .when(intentIs(createParameterSetType)).moveTo(handleEditParameterSetType);

        handleEditParameterSetType
            .body(context -> {
                    String paratype = (String) context.getIntent().getValue("paratype");
                    reactPlatform.reply(context, "The type of the parameter has been changed!! What would you like to do now?");
                    a1.set(paratype);
                    updateParameters(a1, 1);
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        //*****************************************************************************************************************************************

        /*
        Deleting a parameter***************************************************************************************************************************
        */
        handleDeleteParameterGetName
            .body(context -> {
                    String gdparaname = (String) context.getIntent().getValue("gdparaname");
                    String closestName = findClosestName(gdparaname, 4);
                    reactPlatform.reply(context, "Are you sure that you want to delete the transaction named " + gdparaname + "?");
                    a1.set(closestName);
                    updateParameters(a1, 2);
            })
            .next()
            .when(intentIs(deleteParameterConfirm)).moveTo(handleDeleteParameterConfirm);

        handleDeleteParameterConfirm
            .body(context -> {
                    funcDeleteParameter();
                    reactPlatform.reply(context, "Done. What would you like to do now? Would you like to add more elements to the smart contract or continue editing this element?");
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone)
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateAssetAssertParameter)
            .when(intentIs(createAssetDone)).moveTo(handleCreateAssetDone)
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateTransactionAssertParameter)
            .when(intentIs(createTransactionAssertRelationship)).moveTo(handleCreateTransactionAssertRelationship)
            .when(intentIs(createTransactionDone)).moveTo(handleCreateTransactionDone);

        //*****************************************************************************************************************************************



        //***************************************************************************************************************************************** 
        handleDone
            .body(context -> {
                    String listofparticipants = funcGetAllListOfParticipants(); 
                    reactPlatform.reply(context, "Cool. We just need to complete two more steps. Which participant should be the creator of the smart contract? Here is the list: \n" + listofparticipants);
                    updateElementIndexNumber(participantIndexNumber);
            })
            .next()
            .when(intentIs(getCreator)).moveTo(handleGetCreator);

        handleGetCreator
            .body(context -> {
                    String crea = (String) context.getIntent().getValue("crea");
                    reactPlatform.reply(context, "That's amazing. Just one more. In which platform must the smart contract be generated? Hyperledger Composer or Azure or Ethereum?");
                    String closestName = findClosestName(crea, 1);
                    a1.set(closestName);
                    updateParticipants(a1, 3);
                    updateParticipants(a1, 4);
            })
            .next()
            .when(intentIs(getTargetPlatform)).moveTo(handleGetTargetPlatform);

        handleGetTargetPlatform
            .body(context -> {
                    String targplat = (String) context.getIntent().getValue("targplat");
                    reactPlatform.reply(context, "Great!! It's all done. The smart contract will be in the src-gen folder inside the src-gen-dsl.");
                    a1.set(targplat);
                    setTargetPlatform(a1);
                    generateWholeFile();
            })
            .next()
            .moveTo(handleComplete);

        handleComplete
            .body(context -> {
                    reactPlatform.reply(context, "Thanks for using iContractBot. Have a good day.");
            })
            .next()
            .moveTo(awaitingInput);


        /*
         * The state that is executed if the engine doesn't find any navigable transition in a state and the state
         * doesn't contain a fallback.
         * <p>
         * The default fallback state is typically used to answer generic error messages, while state fallback can
         * benefit from contextual information to answer more precisely.
         * <p>
         * Note that every Xatkit bot needs a default fallback state.
         */
        val defaultFallback = fallbackState()
                .body(context -> reactPlatform.reply(context, "Sorry, I didn't, get it"));

        /*
         * Creates the bot model that will be executed by the Xatkit engine.
         * <p>
         * A bot model contains:
         * - A list of platforms used by the bot. Xatkit will take care of starting and initializing the platforms
         * when starting the bot.
         * - A list of providers the bot should listen to for events/intents. As for the platforms Xatkit will take
         * care of initializing the provider when starting the bot.
         * - The entry point of the bot (a.k.a init state). The other states will be automatically collected by analyzing the state machine
         * - The default fallback state: the state that is executed if the engine doesn't find any navigable
         * transition in a state and the state doesn't contain a fallback.
         */
        val botModel = model()
                .usePlatform(reactPlatform)
                .listenTo(reactEventProvider)
                .listenTo(reactIntentProvider)
                .initState(init)
                .defaultFallbackState(defaultFallback);

        // Setting up the chatbot for intent recognition using DialogFlow
        Configuration botConfiguration = new BaseConfiguration();
        botConfiguration.setProperty("xatkit.dialogflow.projectId", "sc-mde-nymmpx");
        botConfiguration.setProperty("xatkit.dialogflow.credentials.path", "./sc-mde-nymmpx-d0311abe8e74.json");
        botConfiguration.setProperty("xatkit.dialogflow.language", "en-US");
        // botConfiguration.setProperty("xatkit.dialogflow.clean_on_startup", true);
// xatkit.dialogflow.credentials.path = <Path to the downloaded JSON credential file>
// xatkit.dialogflow.language = en-US
// xatkit.dialogflow.clean_on_startup = true
        /*
         * Add configuration properties (e.g. authentication tokens, platform tuning, intent provider to use).
         * Check the corresponding platform's wiki page for further information on optional/mandatory parameters and
         * their values.
         */

        XatkitBot xatkitBot = new XatkitBot(botModel, botConfiguration);
        xatkitBot.run();
        /*
         * The bot is now started, you can check http://localhost:5000/admin to test it.
         * The logs of the bot are stored in the logs folder at the root of this project.
         */
    }

    // Function for setting the name of the contract
    public static void setContractName(AtomicReference<String> str){
        nameOfContract = "" + str;
    }

    public static void setTargetPlatform(AtomicReference<String> str){
        String strString = "" + str;
        if(strString.toLowerCase().contains("ibm") || strString.toLowerCase().contains("composer"))
        {
            targetPlatform = "IBM";
        }
        if(strString.toLowerCase().contains("azure"))
        {
            targetPlatform = "Azure";
        }
        if(strString.toLowerCase().contains("solidity") || strString.toLowerCase().contains("Ethereum"))
        {
            targetPlatform = "Solidity";
        }
    }

    //*****************************************************************************************************************************************

    /*
    Participant***************************************************************************************************************************
    */

    // Function to create a participant
    public static void funcCreateParticipant() {
        String tempname = "Participant_Name_None_" + Integer.toString(numberOfParticipants);
        String templist = "Participant_List_None_" + Integer.toString(numberOfParticipants);
        Participant tempparticipant = new Participant();
        tempparticipant.name = tempname;
        tempparticipant.list = templist;
        tempparticipant.creator = false;
        tempparticipant.parameters = new ArrayList<Parameter> ();
        tempparticipant.parameterNames = new ArrayList<String> ();
        contractParticipants.add(tempparticipant);
        participantNames.add(tempname);
        currentParticipant = numberOfParticipants;
        numberOfParticipants++;
        indexNumber = 1;
    }

    // Function to update participants
    public static void updateParticipants(AtomicReference<String> str, int flag){
        Participant tempparticipant = new Participant();
        tempparticipant = contractParticipants.get(currentParticipant);
        String strString = "" + str;
        // When user states or changes the name of a participant
        if(flag == 0)
        {
            participantNames.set(currentParticipant, "" + str);
            tempparticipant.name = "" + str;
            contractParticipants.set(currentParticipant, tempparticipant);
        }
        // When user changes the list parameter of the participant
        if(flag == 1)
        {
            tempparticipant.list = "" + str;
            contractParticipants.set(currentParticipant, tempparticipant);
        }
        // When user sets or changes the identifier for the participant
        if(flag == 2)
        {
            int indexofpara = tempparticipant.parameterNames.indexOf(strString);
            Parameter tempparameter;
            tempparameter = tempparticipant.parameters.get(indexofpara);
            tempparameter.identifier = true;
            tempparticipant.parameters.set(indexofpara, tempparameter);
            contractParticipants.set(currentParticipant, tempparticipant);
        }
        // Changing the current participant, when a participant is to be edited, deleted or read
        if(flag == 3)
        {
            currentParticipant = participantNames.indexOf(strString);
        }
        // This is for setting the creator of the smart contract
        if(flag == 4)
        {
            tempparticipant.creator = true;
            contractParticipants.set(currentParticipant, tempparticipant);
        }

    }

    // Function to delete a participant
    // The index number of the participant that has to be deleted has been set already
    public static void funcDeleteParticipant()
    {
        participantNames.remove(currentParticipant);
        contractParticipants.remove(currentParticipant);
    }

    // This function is read participant
    // The index number of the participant that has to be read has been set already
    public static String funcReadParticipant()
    {
        String participantReadString;
        Participant tempparticipant = new Participant();
        tempparticipant = contractParticipants.get(currentParticipant);
        participantReadString = "Name: " + tempparticipant.name + "\n";
        for(int i = 0; i < tempparticipant.parameterNames.size(); i++)
        {
            if(tempparticipant.parameters.get(i).identifier == true)
            {
                participantReadString = participantReadString + "Identifier: " + tempparticipant.parameters.get(i).name + "\n";
                break;
            }
        }
        participantReadString = "Parameters: \n";
        for(int i = 0; i < tempparticipant.parameterNames.size(); i++)
        {
            participantReadString = participantReadString + tempparticipant.parameters.get(i).type + " " + tempparticipant.parameters.get(i).name + "\n";
        }
        return participantReadString;
    }

    // This function gives name of all the participants. This comes in handy when we are asking the user to set the creator of the smart contract.
    public static String funcGetAllListOfParticipants()
    {
        String templ = "";
        for(int i = 0; i < participantNames.size(); i++)
        {
            templ = templ + participantNames.get(i);
        }
        return templ;
    }

    //*****************************************************************************************************************************************

    //*****************************************************************************************************************************************

    /*
    Asset***************************************************************************************************************************
    */

    // This function is meant for creating a new asset
    public static void funcCreateAsset() {
        String temptype = "Asset_Type_None_" + Integer.toString(numberOfAssets);
        String tempname = "Asset_Name_None_" + Integer.toString(numberOfAssets);
        String templist = "Asset_List_None_" + Integer.toString(numberOfAssets);
        Asset tempasset = new Asset();
        tempasset.type = temptype;
        tempasset.name = tempname;
        tempasset.list = templist;
        tempasset.parameters = new ArrayList<Parameter> ();
        tempasset.parameterNames = new ArrayList<String> ();
        contractAssets.add(tempasset);
        assetNames.add(tempname);
        currentAsset = numberOfAssets;
        numberOfAssets++;
        indexNumber = 2;
    }

    // This function is meant for updating the assets
    public static void updateAssets(AtomicReference<String> str, int flag){
        Asset tempasset = new Asset();
        tempasset = contractAssets.get(currentAsset);
        String strString = "" + str;
        // Set the type of the asset
        if(flag == 0)
        {
            tempasset.type = "" + str;
            contractAssets.set(currentAsset, tempasset);
        }
        // Set the name of the asset
        if(flag == 1)
        {
            assetNames.set(currentAsset, "" + str);
            tempasset.name = "" + str;
            contractAssets.set(currentAsset, tempasset);
        }
        // Set the list parameter of the asset
        if(flag == 2)
        {
            tempasset.list = "" + str;
            contractAssets.set(currentAsset, tempasset);
        }
        // Set the identifier of the asset
        if(flag == 3)
        {
            int indexofpara = tempasset.parameterNames.indexOf(strString);
            Parameter tempparameter;
            tempparameter = tempasset.parameters.get(indexofpara);
            tempparameter.identifier = true;
            tempasset.parameters.set(indexofpara, tempparameter);
            contractAssets.set(currentAsset, tempasset);
        }
        // Set the index of the asset that will be acted upon
        if(flag == 4)
        {
            currentAsset = assetNames.indexOf(strString);
        }
    }

    // Function for deleting an asset
    // The index number of the asset that will be deleted has been set already
    public static void funcDeleteAsset()
    {
        assetNames.remove(currentAsset);
        contractAssets.remove(currentAsset);
    }

    // Function for readin an asset
    // The index number number of the asset, that has to be read, has been set already
    public static String funcReadAsset()
    {
        String assetReadString;
        Asset tempasset = new Asset();
        tempasset = contractAssets.get(currentAsset);
        assetReadString = "Name: " + tempasset.name + "\n";
        for(int i = 0; i < tempasset.parameterNames.size(); i++)
        {
            if(tempasset.parameters.get(i).identifier == true)
            {
                assetReadString = assetReadString + "Identifier: " + tempasset.parameters.get(i).name + "\n";
                break;
            }
        }
        assetReadString = "Parameters: \n";
        for(int i = 0; i < tempasset.parameterNames.size(); i++)
        {
            assetReadString = assetReadString + tempasset.parameters.get(i).type + " " + tempasset.parameters.get(i).name + "\n";
        }
        return assetReadString;
    }

    //*****************************************************************************************************************************************

    //*****************************************************************************************************************************************

    /*
    Transaction***************************************************************************************************************************
    */

    // Function to create a transaction
    public static void funcCreateTransaction() {
        String temptype = "Transaction_Type_None_" + Integer.toString(numberOfTransactions);
        String tempname = "Transaction_Name_None_" + Integer.toString(numberOfTransactions);
        String templist = "Transaction_List_None_" + Integer.toString(numberOfTransactions);
        Transaction temptransaction = new Transaction();
        temptransaction.conditions = new ArrayList <Condition> ();
        temptransaction.mostrar = false;
        temptransaction.name = tempname;
        temptransaction.list = templist;
        temptransaction.parameters = new ArrayList <Parameter> ();
        temptransaction.tranRels = new ArrayList <TranRel> ();
        temptransaction.assetRels = new ArrayList <AssetRel> ();
        temptransaction.conditionNames = new ArrayList <String> ();
        temptransaction.parameterNames = new ArrayList <String> ();
        temptransaction.tranRelNames = new ArrayList <String> ();
        temptransaction.assetRelNames = new ArrayList <String> ();
        contractTransactions.add(temptransaction);
        transactionNames.add(tempname);
        currentTransaction = numberOfTransactions;
        numberOfTransactions++;
        indexNumber = 3;
    }

    public static void updateTransactions(AtomicReference<String> str, int flag){
        Transaction temptransaction = new Transaction();
        temptransaction = contractTransactions.get(currentTransaction);
        String strString = "" + str;
        // User states or changes the name of the transaction
        if(flag == 0)
        {
            transactionNames.set(currentTransaction, "" + str);
            temptransaction.name = "" + str;
            contractTransactions.set(currentTransaction, temptransaction);
        }

        if(flag == 1)
        {
            temptransaction.list = "" + str;
            contractTransactions.set(currentTransaction, temptransaction);
        }
        if(flag == 2)
        {
            if(("" + str) == "False" || ("" + str) == "false")
                temptransaction.mostrar = false;
                contractTransactions.set(currentTransaction, temptransaction);
        }
        if(flag == 3)
        {
            currentTransaction = transactionNames.indexOf(strString);
        }

    }

    public static void funcDeleteTransaction()
    {
        transactionNames.remove(currentTransaction);
        contractTransactions.remove(currentTransaction);
    }

    public static String funcReadTransaction()
    {
        String transactionReadString;
        Transaction temptransaction = new Transaction();
        temptransaction = contractTransactions.get(currentTransaction);
        transactionReadString = "Name: " + temptransaction.name + "\n";

        transactionReadString = "Parameters: \n";
        for(int i = 0; i < temptransaction.parameterNames.size(); i++)
        {
            transactionReadString = transactionReadString + temptransaction.parameters.get(i).type + " " + temptransaction.parameters.get(i).name + "\n";
        }

        transactionReadString = "Relationships: \n";
        for(int i = 0; i < temptransaction.tranRelNames.size(); i++)
        {
            transactionReadString = transactionReadString + "Name: " + temptransaction.tranRels.get(i).name + ", From: " + temptransaction.tranRels.get(i).from + ", To: " + temptransaction.tranRels.get(i).to + "/n";
        }
        for(int i = 0; i < temptransaction.assetRelNames.size(); i++)
        {
            transactionReadString = transactionReadString + "Name: " + temptransaction.assetRels.get(i).name + ", From: " + temptransaction.assetRels.get(i).from + ", To: " + temptransaction.assetRels.get(i).to + "/n";
        }
        return transactionReadString;
    }

    //*****************************************************************************************************************************************

    //*****************************************************************************************************************************************

    /*
    Parameter***************************************************************************************************************************
    */

    public static void funcCreateParameter(){
        Parameter tempparameter = new Parameter();
        String tempparaname = "";
        String tempparatype = "";

        if (indexNumber == 1) {
            Participant tempparticipant = new Participant();
            tempparticipant = contractParticipants.get(currentParticipant);
            tempparaname = "Parameter_Name_None_" + Integer.toString(tempparticipant.parameterNames.size());
            tempparatype = "Parameter_Type_None_" + Integer.toString(tempparticipant.parameterNames.size());
            tempparameter.name = tempparaname;
            tempparameter.type = tempparatype;
            tempparameter.identifier = false;
            tempparticipant.parameterNames.add(tempparameter.name);
            tempparticipant.parameters.add(tempparameter);
            contractParticipants.set(currentParticipant, tempparticipant);
            currentParticipantParameter = tempparticipant.parameterNames.size() - 1;
        }

        if (indexNumber == 2) {
            Asset tempasset = new Asset();
            tempasset = contractAssets.get(currentAsset);
            tempparaname = "Parameter_Name_None_" + Integer.toString(tempasset.parameterNames.size());
            tempparatype = "Parameter_Type_None_" + Integer.toString(tempasset.parameterNames.size());
            tempparameter.name = tempparaname;
            tempparameter.type = tempparatype;
            tempparameter.identifier = false;
            tempasset.parameterNames.add(tempparameter.name);
            tempasset.parameters.add(tempparameter);
            contractAssets.set(currentAsset, tempasset);
            currentAssetParameter = tempasset.parameterNames.size() - 1;
        }

        if (indexNumber == 3) {
            Transaction temptransaction = new Transaction();
            temptransaction = contractTransactions.get(currentTransaction);
            tempparaname = "Parameter_Name_None_" + Integer.toString(temptransaction.parameterNames.size());
            tempparatype = "Parameter_Type_None_" + Integer.toString(temptransaction.parameterNames.size());
            tempparameter.name = tempparaname;
            tempparameter.type = tempparatype;
            tempparameter.identifier = false;
            temptransaction.parameterNames.add(tempparameter.name);
            temptransaction.parameters.add(tempparameter);
            contractTransactions.set(currentTransaction, temptransaction);
            currentTransactionParameter = temptransaction.parameterNames.size() - 1;
        }
    }

    public static void updateParameters(AtomicReference<String> str, int flag){
        Parameter tempparameter = new Parameter();
        String strString = "" + str;

        if (indexNumber == 1) 
        {
            Participant tempparticipant = new Participant();
            tempparticipant = contractParticipants.get(currentParticipant);
            
            if(flag == 0)
            {
                tempparticipant.parameterNames.set(currentParticipantParameter, strString);
                tempparameter.name = strString;
                tempparameter.type = tempparticipant.parameters.get(currentParticipantParameter).type;
                tempparameter.identifier = false;
                tempparticipant.parameters.set(currentParticipantParameter, tempparameter);
                contractParticipants.set(currentParticipant, tempparticipant);
            }
            if(flag == 1)
            {
                tempparameter.name = tempparticipant.parameters.get(currentParticipantParameter).name;
                tempparameter.type = strString;
                tempparameter.identifier = false;
                tempparticipant.parameters.set(currentParticipantParameter, tempparameter);
                contractParticipants.set(currentParticipant, tempparticipant);
            }
            if(flag == 2)
            {
                currentParticipantParameter = tempparticipant.parameterNames.indexOf(strString);
            }

        }

        if (indexNumber == 2) 
        {
            Asset tempasset = new Asset();
            tempasset = contractAssets.get(currentAsset);
            
            if(flag == 0)
            {
                tempasset.parameterNames.set(currentAssetParameter, strString);
                tempparameter.name = strString;
                tempparameter.type = tempasset.parameters.get(currentAssetParameter).type;
                tempparameter.identifier = false;
                tempasset.parameters.set(currentAssetParameter, tempparameter);
                contractAssets.set(currentAsset, tempasset);
            }
            if(flag == 1)
            {
                tempparameter.name = tempasset.parameters.get(currentAssetParameter).name;
                tempparameter.type = strString;
                tempparameter.identifier = false;
                tempasset.parameters.set(currentAssetParameter, tempparameter);
                contractAssets.set(currentAsset, tempasset);
            }
            if(flag == 2)
            {
                currentAssetParameter = tempasset.parameterNames.indexOf(strString);
            }
        }

        if (indexNumber == 3) 
        {
            Transaction temptransaction = new Transaction();
            temptransaction = contractTransactions.get(currentTransaction);
            
            if(flag == 0)
            {
                temptransaction.parameterNames.set(currentTransactionParameter, strString);
                tempparameter.name = strString;
                tempparameter.type = temptransaction.parameters.get(currentTransactionParameter).type;
                tempparameter.identifier = temptransaction.parameters.get(currentTransactionParameter).identifier;
                temptransaction.parameters.set(currentTransactionParameter, tempparameter);
                contractTransactions.set(currentTransaction, temptransaction);
            }
            if(flag == 1)
            {
                tempparameter.name = temptransaction.parameters.get(currentTransactionParameter).name;
                tempparameter.type = strString;
                tempparameter.identifier = temptransaction.parameters.get(currentTransactionParameter).identifier;
                temptransaction.parameters.set(currentTransactionParameter, tempparameter);
                contractTransactions.set(currentTransaction, temptransaction);
            }
            if(flag == 2)
            {
                currentTransactionParameter = temptransaction.parameterNames.indexOf(strString);
            }
        }
    }

    public static void funcDeleteParameter()
    {
        if(indexNumber == participantIndexNumber)
        {
            contractParticipants.get(currentParticipant).parameterNames.remove(currentParticipantParameter);
            contractParticipants.get(currentParticipant).parameters.remove(currentParticipantParameter);
        }

        if(indexNumber == assetIndexNumber)
        {
            contractAssets.get(currentAsset).parameterNames.remove(currentAssetParameter);
            contractAssets.get(currentAsset).parameters.remove(currentAssetParameter);
        }

        if(indexNumber == transactionIndexNumber)
        {
            contractTransactions.get(currentTransaction).parameterNames.remove(currentTransactionParameter);
            contractTransactions.get(currentTransaction).parameters.remove(currentTransactionParameter);
        }
    }

    //*****************************************************************************************************************************************

    //*****************************************************************************************************************************************

    public static void updateElementIndexNumber(int i)
    {
        indexNumber = i;
    }

    //*****************************************************************************************************************************************

    //*****************************************************************************************************************************************

    /*
    Condition***************************************************************************************************************************
    */

    public static void funcCreateCondition()
    {
        Condition tempcondition = new Condition();
        Transaction temptransaction = new Transaction();
        temptransaction = contractTransactions.get(currentTransaction);
        tempcondition.name = "Condition_Name_None_" + Integer.toString(temptransaction.conditionNames.size());
        tempcondition.condition = "Condition_Condition_None_" + Integer.toString(temptransaction.conditionNames.size());
        tempcondition.pre_or_post = 0;
        temptransaction.conditionNames.add(tempcondition.name);
        temptransaction.conditions.add(tempcondition);
        contractTransactions.set(currentTransaction, temptransaction);
        currentTransactionCondition = temptransaction.conditionNames.size() - 1;
    }

    public static void updateConditions(AtomicReference<String> str, int flag)
    {
        Condition tempcondition = new Condition();
        Transaction temptransaction = new Transaction();
        temptransaction = contractTransactions.get(currentTransaction);
        String strString = "" + str;

        if(flag == 0)
        {
            temptransaction.conditionNames.set(currentTransactionCondition, strString);
            tempcondition.name = strString;
            tempcondition.condition = temptransaction.conditions.get(currentTransactionCondition).condition;
            tempcondition.pre_or_post = temptransaction.conditions.get(currentTransactionCondition).pre_or_post;
            temptransaction.conditions.set(currentTransactionCondition, tempcondition);
            contractTransactions.set(currentTransaction, temptransaction);
        }

        if(flag == 1)
        {
            tempcondition.name = temptransaction.conditions.get(currentTransactionCondition).name;
            tempcondition.condition = strString;
            tempcondition.pre_or_post = temptransaction.conditions.get(currentTransactionCondition).pre_or_post;
            temptransaction.conditions.set(currentTransactionCondition, tempcondition);
            contractTransactions.set(currentTransaction, temptransaction);
        }

        if(flag == 2)
        {
            tempcondition.name = temptransaction.conditions.get(currentTransactionCondition).name;
            tempcondition.condition = temptransaction.conditions.get(currentTransactionCondition).condition;
            if(strString.toLowerCase().contains("post"))
            {
                tempcondition.pre_or_post = 1;
            }
            else
            {
                tempcondition.pre_or_post = 0;
            }
            temptransaction.conditions.set(currentTransactionCondition, tempcondition);
            contractTransactions.set(currentTransaction, temptransaction);
        }

        if(flag == 3)
        {
            currentTransactionCondition = contractTransactions.get(currentTransaction).conditionNames.indexOf(strString);
        }
    }

    public static void funcDeleteCondition()
    {
        contractTransactions.get(currentTransaction).conditionNames.remove(currentTransactionCondition);
        contractTransactions.get(currentTransaction).conditions.remove(currentTransactionCondition);
    }

    //*****************************************************************************************************************************************

    //*****************************************************************************************************************************************

    /*
    Relationship***************************************************************************************************************************
    */

    public static void funcCreateTranRel()
    {
        TranRel temptranrel = new TranRel();
        Transaction temptransaction = new Transaction();
        temptransaction = contractTransactions.get(currentTransaction);
        temptranrel.name = "Tranrel_Name_None_" + Integer.toString(temptransaction.tranRelNames.size());
        temptranrel.from = "Tranrel_From_None_" + Integer.toString(temptransaction.tranRelNames.size());
        temptranrel.to = temptransaction.name;
        temptranrel.selected = true;
        temptranrel.events = new ArrayList <Event> ();
        temptranrel.eventNames = new ArrayList <String> ();
        temptransaction.tranRelNames.add(temptranrel.name);
        temptransaction.tranRels.add(temptranrel);
        contractTransactions.set(currentTransaction, temptransaction);
        currentTransactionTranRel = temptransaction.tranRelNames.size() - 1;
        indexNumberRelationship = tranrelIndexNumber;
    }
    

    public static void funcCreateAssetRel()
    {
        AssetRel tempassetrel = new AssetRel();
        Transaction temptransaction = new Transaction();
        temptransaction = contractTransactions.get(currentTransaction);
        tempassetrel.name = "Assetrel_Name_None_" + Integer.toString(temptransaction.assetRelNames.size());
        tempassetrel.from = temptransaction.name;
        tempassetrel.to = "Assetrel_To_None_" + Integer.toString(temptransaction.assetRelNames.size());
        tempassetrel.create = false;
        tempassetrel.read = false;
        tempassetrel.update = false;
        tempassetrel.delete = false;
        tempassetrel.all = false;
        tempassetrel.events = new ArrayList <Event> ();
        tempassetrel.eventNames = new ArrayList <String> ();
        temptransaction.assetRelNames.add(tempassetrel.name);
        temptransaction.assetRels.add(tempassetrel);
        contractTransactions.set(currentTransaction, temptransaction);
        currentTransactionAssetRel = temptransaction.assetRelNames.size() - 1;
        indexNumberRelationship = assetrelIndexNumber;
    }

    public static void funcUpdateRelationships(AtomicReference<String> str, int flag)
    {
        String strString = "" + str;
        Transaction temptransaction = new Transaction();
        temptransaction = contractTransactions.get(currentTransaction);
        if(indexNumberRelationship == tranrelIndexNumber)
        {
            TranRel temptranrel = new TranRel();
            if (flag == 0)
            {
                temptranrel = temptransaction.tranRels.get(currentTransactionTranRel);
                temptranrel.from = strString;
                temptransaction.tranRels.set(currentTransactionTranRel, temptranrel);
                contractTransactions.set(currentTransaction, temptransaction);
            }
            
            if (flag == 1)
            {
                temptranrel = temptransaction.tranRels.get(currentTransactionTranRel);
                temptranrel.name = strString;
                temptransaction.tranRelNames.set(currentTransactionTranRel, temptranrel.name);
                temptransaction.tranRels.set(currentTransactionTranRel, temptranrel);
                contractTransactions.set(currentTransaction, temptransaction);
            }

            if(flag == 2)
            {
                currentTransactionTranRel = contractTransactions.get(currentTransaction).tranRelNames.indexOf(strString);
            }
            
        }

        if(indexNumberRelationship == assetrelIndexNumber)
        {
            AssetRel tempassetrel = new AssetRel();
            if (flag == 0)
            {
                tempassetrel = temptransaction.assetRels.get(currentTransactionAssetRel);
                tempassetrel.to = strString;
                temptransaction.assetRels.set(currentTransactionAssetRel, tempassetrel);
                contractTransactions.set(currentTransaction, temptransaction);
            }
            if (flag == 1)
            {
                tempassetrel = temptransaction.assetRels.get(currentTransactionAssetRel);
                tempassetrel.name = strString;
                temptransaction.assetRelNames.set(currentTransactionAssetRel, tempassetrel.name);
                temptransaction.assetRels.set(currentTransactionAssetRel, tempassetrel);
                contractTransactions.set(currentTransaction, temptransaction);
            }
            if(flag == 2)
            {
                currentTransactionAssetRel = contractTransactions.get(currentTransaction).assetRelNames.indexOf(strString);
            }
        }
    }

    public static void funcDeleteRelationship()
    {
        if(indexNumberRelationship == tranrelIndexNumber)
        {
            contractTransactions.get(currentTransaction).tranRelNames.remove(currentTransactionTranRel);
            contractTransactions.get(currentTransaction).tranRels.remove(currentTransactionTranRel);
        }

        if(indexNumberRelationship == assetrelIndexNumber)
        {
            contractTransactions.get(currentTransaction).assetRelNames.remove(currentTransactionAssetRel);
            contractTransactions.get(currentTransaction).assetRels.remove(currentTransactionAssetRel);   
        }
    }

    //*****************************************************************************************************************************************

    public static String findClosestName(String str, int i)
    {
        String cN = "";
        if(i == 1)
        {
            int tempindex = findClosestStringIndex(str, participantNames);
            cN = participantNames.get(tempindex);
        }
        if(i == 2)
        {
            int tempindex = findClosestStringIndex(str, assetNames);
            cN = assetNames.get(tempindex);
        }
        if(i == 3)
        {
            int tempindex = findClosestStringIndex(str, transactionNames);
            cN = transactionNames.get(tempindex);
        }
        if(i == 4)
        {
            if(indexNumber == participantIndexNumber)
            {
                int tempindex = findClosestStringIndex(str, contractParticipants.get(currentParticipant).parameterNames);
                cN = contractParticipants.get(currentParticipant).parameterNames.get(tempindex);
            }

            if(indexNumber == assetIndexNumber)
            {
                int tempindex = findClosestStringIndex(str, contractAssets.get(currentAsset).parameterNames);
                cN = contractAssets.get(currentAsset).parameterNames.get(tempindex);
            }

            if(indexNumber == transactionIndexNumber)
            {
                int tempindex = findClosestStringIndex(str, contractTransactions.get(currentTransaction).parameterNames);
                cN = contractTransactions.get(currentTransaction).parameterNames.get(tempindex);
            }
        }
        if(i == 5)
        {
            int tempindex1 = findClosestStringIndex(str, contractTransactions.get(currentTransaction).tranRelNames);
            int tempindex2 = findClosestStringIndex(str, contractTransactions.get(currentTransaction).assetRelNames);
            int dist1 = EditDistDP(str, contractTransactions.get(currentTransaction).tranRelNames.get(tempindex1));
            int dist2 = EditDistDP(str, contractTransactions.get(currentTransaction).assetRelNames.get(tempindex2));
            if(dist1 < dist2)
            {
                cN = contractTransactions.get(currentTransaction).tranRelNames.get(tempindex1);
                indexNumberRelationship = tranrelIndexNumber;
            }
            else
            {
                cN = contractTransactions.get(currentTransaction).assetRelNames.get(tempindex2);
                indexNumberRelationship = assetrelIndexNumber;
            }
        }
        if(i == 6)
        {
            int tempindex = findClosestStringIndex(str, contractTransactions.get(currentTransaction).conditionNames);
            cN = contractTransactions.get(currentTransaction).conditionNames.get(tempindex);
        }
        return cN;
    }

    public static int findClosestStringIndex(String str, ArrayList<String> strlist)
    {
        int distance = 10000;
        int tempdist;
        int index1 = 0;
        for(int i = 0; i < strlist.size(); i++)
        {
            tempdist = EditDistDP(str, strlist.get(i));
            if(tempdist < distance)
                index1 = i;
        }
        return index1;
    }

    public static int EditDistDP(String str1, String str2)
    {
        int len1 = str1.length();
        int len2 = str2.length();
     
        // Create a DP array to memoize result
        // of previous computations
        int [][]DP = new int[2][len1 + 1];
     
     
        // Base condition when second String
        // is empty then we remove all characters
        for (int i = 0; i <= len1; i++)
            DP[0][i] = i;
     
        // Start filling the DP
        // This loop run for every
        // character in second String
        for (int i = 1; i <= len2; i++) 
        {
           
            // This loop compares the char from
            // second String with first String
            // characters
            for (int j = 0; j <= len1; j++)
            {
               
                // if first String is empty then
                // we have to perform add character
                // operation to get second String
                if (j == 0)
                    DP[i % 2][j] = i;
     
                // if character from both String
                // is same then we do not perform any
                // operation . here i % 2 is for bound
                // the row number.
                else if (str1.charAt(j - 1) == str2.charAt(i - 1)) {
                    DP[i % 2][j] = DP[(i - 1) % 2][j - 1];
                }
     
                // if character from both String is
                // not same then we take the minimum
                // from three specified operation
                else {
                    DP[i % 2][j] = 1 + Math.min(DP[(i - 1) % 2][j],
                                           Math.min(DP[i % 2][j - 1],
                                               DP[(i - 1) % 2][j - 1]));
                }
            }
        }
     
        // after complete fill the DP array
        // if the len2 is even then we end
        // up in the 0th row else we end up
        // in the 1th row so we take len2 % 2
        // to get row
        return DP[len2 % 2][len1];
    }

    //Function to generate the whole file - to generate the smart contract
    public static void generateWholeFile(){
        String stringTempParticipant = "";
        String stringTempAsset = "";
        String stringTempTransaction = "";

        for(int i = 0; i < contractParticipants.size(); i++)
        {
            if(contractParticipants.get(i).creator == true)
            {
                stringTempParticipant = stringTempParticipant + "Participant" + "\n" + "Creator: T {" + "\n";
            }
            else
            {
                stringTempParticipant = stringTempParticipant + "Participant " + "{" + "\n";
            }
            stringTempParticipant = stringTempParticipant + "Name: " + contractParticipants.get(i).name + "\n";
            stringTempParticipant = stringTempParticipant + "List: " + contractParticipants.get(i).list + "\n";
            for(int j = 0; j < contractParticipants.get(i).parameters.size(); j++)
            {
                stringTempParticipant = stringTempParticipant + "Parameter " + "{" + "\n";
                stringTempParticipant = stringTempParticipant + "Name: " + contractParticipants.get(i).parameters.get(j).name + "\n";
                stringTempParticipant = stringTempParticipant + "Type: " + contractParticipants.get(i).parameters.get(j).type + "\n";
                if(contractParticipants.get(i).parameters.get(j).identifier == true)
                {
                    stringTempParticipant = stringTempParticipant + "Identifier: T" + "\n";
                }
                stringTempParticipant = stringTempParticipant + "}" + "\n";
            }
            stringTempParticipant = stringTempParticipant + "}" + "\n";
        }

        for(int i = 0; i < contractAssets.size(); i++)
        {
            stringTempAsset = stringTempAsset + "Asset" + "\n" + "Type: " + contractAssets.get(i).type + "{" + "\n";
            stringTempAsset = stringTempAsset + "Name: " + contractAssets.get(i).name + "\n";
            stringTempAsset = stringTempAsset + "Type: " + contractAssets.get(i).type + "\n";
            for(int j = 0; j < contractAssets.get(i).parameters.size(); j++)
            {
                stringTempAsset = stringTempAsset + "Parameter" + "{" + "\n";
                stringTempAsset = "Name: " + contractAssets.get(i).parameters.get(j).name + "\n";
                stringTempAsset = "Type: " + contractAssets.get(i).parameters.get(j).type + "\n";
                if(contractAssets.get(i).parameters.get(j).identifier == true)
                {
                    stringTempAsset = stringTempAsset + "Identifier: T" + "\n";
                }
                stringTempAsset = stringTempAsset + "}" + "\n";
            }
            stringTempAsset = stringTempAsset + "}" + "\n";
        }

        for(int i = 0; i < contractTransactions.size(); i++)
        {
            stringTempTransaction = stringTempTransaction + "Transaction" + "{" + "\n";
            for(int j = 0; j < contractTransactions.get(i).conditions.size(); j++)
            {
                stringTempTransaction = stringTempTransaction + "Condition" + "\n" + "{";
                stringTempTransaction = stringTempTransaction + "Name: " + contractTransactions.get(i).conditions.get(j).name + "\n";
                stringTempTransaction = stringTempTransaction + "Condition: " + contractTransactions.get(i).conditions.get(j).condition + "\n";
                if(contractTransactions.get(i).conditions.get(j).pre_or_post == 0)
                {
                    stringTempTransaction = stringTempTransaction + "ConditionType: " + "Pre" + "\n";
                }
                if(contractTransactions.get(i).conditions.get(j).pre_or_post == 1)
                {
                    stringTempTransaction = stringTempTransaction + "ConditionType: " + "Post" + "\n";
                }
                stringTempTransaction = stringTempTransaction + "}" + "\n";
            }

            stringTempTransaction = stringTempParticipant + "{" + "\n";
            for(int j = 0; j < contractTransactions.get(i).parameters.size(); j++)
            {
                stringTempTransaction = stringTempTransaction + "Parameter" + "{" + "\n";
                stringTempTransaction = stringTempTransaction + "Name: " + contractTransactions.get(i).parameters.get(j).name + "\n";
                stringTempTransaction = stringTempTransaction + "Type: " + contractTransactions.get(i).parameters.get(j).type + "\n";
                stringTempTransaction = stringTempTransaction + "}" + "\n";
            }

            for(int j = 0; j < contractTransactions.get(i).tranRels.size(); j++)
            {
                stringTempTransaction = stringTempTransaction + "Relationship" + "\n";
                stringTempTransaction = stringTempTransaction + "TranRel" + "{" + "\n";
                stringTempTransaction = stringTempTransaction + "Name: " + contractTransactions.get(i).tranRels.get(j).name + "\n";
                stringTempTransaction = stringTempTransaction + "From: " + contractTransactions.get(i).tranRels.get(j).from + "\n";
                stringTempTransaction = stringTempTransaction + "To: " + contractTransactions.get(i).tranRels.get(j).to + "\n";
                stringTempTransaction = stringTempTransaction + "}" + "\n";
            }

            for(int j = 0; j < contractTransactions.get(i).assetRels.size(); j++)
            {
                stringTempTransaction = stringTempTransaction + "Relationship" + "\n";
                stringTempTransaction = stringTempTransaction + "TranRel" + "{" + "\n";
                stringTempTransaction = stringTempTransaction + "Name: " + contractTransactions.get(i).assetRels.get(j).name + "\n";
                stringTempTransaction = stringTempTransaction + "From: " + contractTransactions.get(i).assetRels.get(j).from + "\n";
                stringTempTransaction = stringTempTransaction + "To: " + contractTransactions.get(i).assetRels.get(j).to + "\n";
                stringTempTransaction = stringTempTransaction + "}" + "\n";
            }

            stringTempTransaction = stringTempTransaction + "}" + "\n";
        }

        wholeContract = wholeContract + "Contract: " + nameOfContract + "\n";
        wholeContract = wholeContract + "Platform: " + targetPlatform + "\n";
        wholeContract = wholeContract + stringTempParticipant;
        wholeContract = wholeContract + stringTempAsset;
        wholeContract = wholeContract + stringTempTransaction;

        String filename = "./" + nameOfContract + ".scdsl";

        try{
                FileWriter fw = new FileWriter(filename);
                fw.write(wholeContract);
                fw.close();
                ProcessBuilder pb = new ProcessBuilder("java", "-jar", "run.jar", filename);
                Process p = pb.start();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}

// Defining the different types needed in the smart contract
class Parameter
{
    public String name;
    public String type;
    public boolean identifier;
}

class Event
{
    public String name;
    public String description;
}

class TranRel
{
    public String from;
    public String to;
    public String name;
    public boolean selected;
    public ArrayList <Event> events;
    public ArrayList <String> eventNames;
}

class AssetRel
{
    public String from;
    public String to;
    public String name;
    // public String participant;
    public boolean create;
    public boolean read;
    public boolean update;
    public boolean delete;
    public boolean all;
    public ArrayList <Event> events;
    public ArrayList <String> eventNames;
}

class Condition
{
    public String name;
    public String condition;
    public int pre_or_post;
}

class Participant
{
    public String name;
    public String list;
    public boolean creator;
    public ArrayList <Parameter> parameters;
    public ArrayList <String> parameterNames;
}

class Asset
{
    public String type;
    public String name;
    public String list;
    public ArrayList <Parameter> parameters;
    public ArrayList <String> parameterNames;
}

class Transaction
{
    public ArrayList <Condition> conditions;
    public boolean mostrar;
    public String name;
    public String list;
    public ArrayList <Parameter> parameters;
    public ArrayList <TranRel> tranRels;
    public ArrayList <AssetRel> assetRels;
    public ArrayList <String> conditionNames;
    public ArrayList <String> parameterNames;
    public ArrayList <String> tranRelNames;
    public ArrayList <String> assetRelNames;
}