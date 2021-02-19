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

    // This arraylist is meant for storing all the names of the participants of the smart contract. This will be used to find the participants using their names.
    static ArrayList<String> participantNames = new ArrayList <String>();
    // This arraylist is meant for storing all the names of the assets of the smart contract. This will be used to find the assets using their names.
    static ArrayList<String> assetNames = new ArrayList <String>();
    // This arraylist is meant for storing all the names of the transactions of the smart contract. This will be used to find the transactions using their names.
    static ArrayList<String> transactionNames = new ArrayList<String>();
    static ArrayList<Participant> contractParticipants = new ArrayList<Participant>();
    static ArrayList<Asset> contractAssets = new ArrayList<Asset>();
    static ArrayList<Transaction> contractTransactions = new ArrayList<Transaction>();
    static String nameOfContract = "";
    static String wholeContract = "";
    static int indexNumber;
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

    public MdeBot() {
        numberOfParticipants = 0;
        numberOfAssets = 0;
        numberOfTransactions = 0;
        currentParticipant = 0;
        currentAsset = 0;
        currentTransaction = 0;
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

        val createTransactionTranRelationshipName = intent("CreateTransactionTranRelationshipName")
            .trainingSentence("The participant for the relationship would be RELPART")
            .trainingSentence("The relationship would from the participant named RELPART")
            .parameter("relpart").fromFragment("RELPART").entity(any());

        val createTransactionAssetRelationshipName = intent("CreateTransactionAssetRelationshipName")
            .trainingSentence("The asset for the relationship would be RELASST")
            .trainingSentence("The relationship would from the asset named RELASST")
            .parameter("relasst").fromFragment("RELASST").entity(any());

        val createTransactionAssertRelationshipEvent = intent("CreateTransactionAssertRelationshipEvent")
            .trainingSentence("Yes, I want an event for the relationship")
            .trainingSentence("Yes, an event for the relationship has to be created");

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
        val createParameterSetName = intent("CreateParameterSetName")
            .trainingSentence("The name of the parameter should be PARANAME")
            .trainingSentence("The name of the parameter is PARANAME")
            .trainingSentence("I want to name the parameter as PARANAME")
            .trainingSentence("Set the name of the parameter as PARANAME")
            .parameter("paraname").fromFragment("PARANAME").entity(any());

        val createParameterSetType = intent("CreateParameterSetType")
            .trainingSentence("The type of the parameter should be PARATYPE")
            .trainingSentence("The type of the parameter is PARATYPE")
            .trainingSentence("Set the type of the parameter as PARATYPE")
            .parameter("paratype").fromFragment("PARATYPE").entity(any());

        // Complete------------------------------------------------------------------------------------------------

        val contractDone = intent("ContractDone")
            .trainingSentence("Done")
            .trainingSentence("done")
            .trainingSentence("DONE");
        
        ReactPlatform reactPlatform = new ReactPlatform();
        ReactEventProvider reactEventProvider = reactPlatform.getReactEventProvider();
        ReactIntentProvider reactIntentProvider = reactPlatform.getReactIntentProvider();

        val init = state("Init");
        val awaitingInput = state("AwaitingInput");
        val handleCreateContract = state("HandleCreateContract");
        val handleEditContract = state("HandleEditContract");
        val handleCreateContractName = state("HandleCreateContractName");
        val handleGetEditContractName = state("HandleGetEditContractName");
        // States for creating participants
        val handleCreateParticipant = state("HandleCreateParticipant");
        val handleCreateParticipantSetName = state("HandleCreateParticipantSetName");
        val handleCreateParticipantSetList = state("HandleCreateParticipantSetList");
        val handleCreateParticipantAssertRelationship = state("HandleCreateParticipantAssertRelationship");
        val handleCreateParticipantDone = state("HandleCreateParticipantDone");
        // States for editing participants
        val handleEditParticipant = state("HandleEditParticipant");
        val handleEditParticipantGetName = state("HandleEditParticipantGetName");
        val handleEditParticipantConfirm = state("HandleEditParticipantConfirm");
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
        val handleCreateAssetAssertRelationship = state("HandleCreateAssetAssertRelationship");
        val handleCreateAssetDone = state("HandleCreateAssetDone");
        // States for editing assets
        val handleEditAsset = state("HandleEditAsset");
        val handleEditAssetGetName = state("HandleEditAssetGetName");
        val handleEditAssetConfirm = state("HandleEditAssetConfirm");
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
        val handleCreateTransactionAssertRelationship = state("HandleCreateAssetAssertRelationship");
        val handleCreateTransactionAssertTranRelationship = state("HandleTransactionAssertTranRelationship");
        val handleCreateTransactionAssertAssetRelationship = state("HandleTransactionAssertAssetRelationship");
        val handleCreateTransactionTranRelationshipName = state("HandleCreateTransactionTranRelationshipName");
        val handleCreateTransactionAssetRelationshipName = state("HandleCreateTransactionAssetRelationshipName");
        val handleCreateTransactionAssertRelationshipEvent = state("HandleCreateTransactionAssertRelationshipEvent");
        val handleCreateTransactionRelationshipEventName = state("HandleCreateTransactionRelationshipEventName");
        val handleCreateTransactionRelationshipEventDescription = state("HandleCreateTransactionRelationshipEventDescription");
        val handleCreateTransactionDone = state("HandleCreateTransactionDone");
        // States for editing transactions
        val handleEditTransaction = state("HandleEditTransaction");
        val handleEditTransactionGetName = state("HandleEditTransactionGetName");
        val handleEditTransactionConfirm = state("HandleEditTransactionConfirm");
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
        val handleCreateParameterSetName = state("HandleParameterSetName");
        val handleCreateParameterSetType = state("HandleParameterSetType");
        // The user has provided all the information from his/her side
        val handleDone = state("HandleDone");


        init
            .next()
            .when(eventIs(ReactEventProvider.ClientReady)).moveTo(awaitingInput);

        //Ask the user if they wish to create or edit a contract. This is the first line that the user says.
        awaitingInput
            .body(context -> {
                    reactPlatform.reply(context, "Hey there. I am iContractBot. I am here to guide you to create and edit smart contracts. Do you want to create or edit a smart contract?");
            })
            .next()
            .when(intentIs(createContract)).moveTo(handleCreateContract)
            .when(intentIs(editContract)).moveTo(handleEditContract);

        //When the user wants to create a smart contract
        handleCreateContract
            .body(context -> {
                    reactPlatform.reply(context, "That's great. I will help you to create a smart contract. What would you like to call your contract?");
            })
            .next()
            .when(intentIs(contractName)).moveTo(handleCreateContractName);

        //When the user wants to edit a smart contract
        handleEditContract
            .body(context -> {
                    reactPlatform.reply(context, "That's great. I will help you to edit the smart contract. What's the name of the contract that you want to edit?");
            })
            .next()
            .when(intentIs(contractName)).moveTo(handleGetEditContractName);

        // After the user gives instructions to give the name to the contract
        // The user can only create elements from here.
        handleCreateContractName
            .body(context -> {
                    String contractname = (String) context.getIntent().getValue("cont");
                    reactPlatform.reply(context, "Wow. Great Job. So we are going to create a smart contract with the name " + contractname + ". So, what do you wish do now? Would you like to create some elements in the smart contract?");
                    a1.set("Contract : " + contractname + "\n");
                    setContractName(a1);
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        // Get the name of the contract that the user wants to edit
        handleGetEditContractName
            .body(context -> {
                    String contractname = (String) context.getIntent().getValue("cName");
                    reactPlatform.reply(context, "That's great. Lemme find the" + contractname + "smart contract in the contract in the directory. What would you like to do next? Do you want to create/edit/delete/read some elements in the smart contract?");
                    a1.set("Contract : " + contractname + "\n");
                    setContractName(a1);
            })
            .next()
            .moveTo(awaitingInput);
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
                    reactPlatform.reply(context, "Awesome. What would you like to name the list attribute of the participant?");
                    a1.set(partname);
                    updateParticipants(a1, 0);
            })
            .next()
            .when(intentIs(createParticipantSetList)).moveTo(handleCreateParticipantSetList);

        // Detect and store the list attribute of the participant
        handleCreateParticipantSetList
            .body(context -> {
                    String partlist = (String) context.getIntent().getValue("partlist");
                    reactPlatform.reply(context, "Great!! We are moving in the right direction. What would be the name of the first parameter of the participant?");
                    a1.set(partlist);
                    updateParticipants(a1, 1);
            })  
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateParticipantAssertParameter
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the parameter?");
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateParticipantAssertRelationship
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the relationship?");
            })
            .next()
            .moveTo(awaitingInput);

        handleCreateParticipantDone
            .body(context -> {
                    reactPlatform.reply(context, "Wow. Great Job. So we have created a participant. What do you wish do now? Would you like to define create some more elements for the smart contract?");
                    a1.set("}");
                    updateParticipants(a1, 1);
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Editing a participant***************************************************************************************************************************
        */

        handleEditParticipant
            .body(context -> {
                    reactPlatform.reply(context, "Great. Which participant would you like to edit?");
            })
            .next()
            .when(intentIs(editParticipantGetName)).moveTo(handleEditParticipantGetName);

        handleEditParticipantGetName
            .body(context -> {
                    String gepartname = (String) context.getIntent().getValue("gepartname");
                    reactPlatform.reply(context, "Okay. So you want to edit the participant named " + gepartname + ", right?");
            })
            .next()
            .when(intentIs(editParticipantConfirm)).moveTo(handleEditParticipantConfirm);

        handleEditParticipantConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Thanks for letting me know! What changes would you like to make to the participant?");
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParameterSetName);

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
                    reactPlatform.reply(context, "Okay. Which participant would you like to delete?");
            })
            .next()
            .when(intentIs(deleteParticipantGetName)).moveTo(handleDeleteParticipantGetName);

        handleDeleteParticipantGetName
            .body(context -> {
                    String gdpartname = (String) context.getIntent().getValue("gdpartname");
                    reactPlatform.reply(context, "Are you sure that you want to delete the participant named " + gdpartname + "?" );
            })
            .next()
            .when(intentIs(deleteParticipantConfirm)).moveTo(handleDeleteParticipantConfirm);

        handleDeleteParticipantConfirm
            .body(context -> {
                    reactPlatform.reply(context, "That's cool. I have completed the deletion. What would you like to do now");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Reading a participant***************************************************************************************************************************
        */

        handleReadParticipant
            .body(context -> {
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
                    reactPlatform.reply(context, "That's cool. Have a look at the participant. What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************


        /*
        Creating an asset***************************************************************************************************************************
        */

        // The user says that he/she wants to create an asset
        handleCreateAsset
            .body(context -> {
                    reactPlatform.reply(context, "Looks like we are heading somewhere :). What should be the type of the asset?");
                    funcCreateAsset();
            })
            .next()
            .when(intentIs(createAssetSetType)).moveTo(handleCreateAssetSetType);

        // Set the type attribute of the asset
        handleCreateAssetSetType
            .body(context -> {
                    String assttype = (String) context.getIntent().getValue("assttype");
                    reactPlatform.reply(context, "Perfect. What would you like to name the asset?");
                    a1.set(assttype);
                    updateAssets(a1, 0);
            })
            .next()
            .when(intentIs(createAssetSetName)).moveTo(handleCreateAssetSetName);

        // Detect and store the name of the asset
        handleCreateAssetSetName
            .body(context -> {
                    String asstname = (String) context.getIntent().getValue("asstname");
                    reactPlatform.reply(context, "Awesome. What would you like to name the list attribute of the asset?");
                    a1.set(asstname);
                    updateAssets(a1, 1);
            })
            .next()
            .when(intentIs(createAssetSetList)).moveTo(handleCreateAssetSetList);

        // Detect and store the list attribute of the asset
        handleCreateAssetSetList
            .body(context -> {
                    String asstlist = (String) context.getIntent().getValue("asstlist");
                    reactPlatform.reply(context, "Great!! We are moving in the right direction. What would be the name of the first parameter of the asset?");
                    a1.set(asstlist);
                    updateAssets(a1, 2);
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateAssetAssertParameter
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the parameter?");
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateAssetAssertRelationship
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the relationship?");
            })
            .next()
            .moveTo(awaitingInput);

        handleCreateAssetDone
                .body(context -> {
                        reactPlatform.reply(context, "Wow. Great Job. So we have created an asset. What do you wish to do now? Would you like to define create some more elements for the smart contract?");
                        a1.set("}");
                        updateAssets(a1, 1);
                })
                .next()
                .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
                .when(intentIs(createAsset)).moveTo(handleCreateAsset)
                .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
                .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Editing an asset***************************************************************************************************************************
        */

        handleEditAsset
            .body(context -> {
                    reactPlatform.reply(context, "Great. Which asset would you like to edit?");
            })
            .next()
            .when(intentIs(editAssetGetName)).moveTo(handleEditAssetGetName);

        handleEditAssetGetName
            .body(context -> {
                    String geasstname = (String) context.getIntent().getValue("geasstname");
                    reactPlatform.reply(context, "Are you sure that you want to read the asset named " + geasstname + "?" );
            })
            .next()
            .when(intentIs(editAssetConfirm)).moveTo(handleEditAssetConfirm);

        handleEditAssetConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Thanks for letting me know! What changes would you like to make to the asset?");
            })
            .next()
            .when(intentIs(createAssetAssertParameter)).moveTo(handleCreateParameterSetName);

        //*****************************************************************************************************************************************

        /*
        Deleting an asset***************************************************************************************************************************
        */

        handleDeleteAsset
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which asset would you like to delete?");
            })
            .next()
            .when(intentIs(deleteAssetGetName)).moveTo(handleDeleteAssetGetName);

        handleDeleteParticipantGetName
            .body(context -> {
                    String gdasstname = (String) context.getIntent().getValue("gdasstname");
                    reactPlatform.reply(context, "Are you sure that you want to delete the asset named " + gdasstname + "?" );
            })
            .next()
            .when(intentIs(deleteAssetConfirm)).moveTo(handleDeleteAssetConfirm);

        handleDeleteAssetConfirm
            .body(context -> {
                    reactPlatform.reply(context, "That's cool. I have completed the deletion. What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Reading an asset***************************************************************************************************************************
        */

        handleReadAsset
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which asset would you like to read?");
            })
            .next()
            .when(intentIs(readAssetGetName)).moveTo(handleReadAssetGetName);

        handleReadAssetGetName
            .body(context -> {
                    String grasstname = (String) context.getIntent().getValue("grasstname");
                    reactPlatform.reply(context, "Are you sure that you want to read the asset named " + grasstname + "?" );
            })
            .next()
            .when(intentIs(readAssetConfirm)).moveTo(handleReadAssetConfirm);

        handleReadAssetConfirm
            .body(context -> {
                    reactPlatform.reply(context, "That's cool. Have a look at the asset. What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
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
                    reactPlatform.reply(context, "Awesome. What would you like to name the list attribute of the transaction?");
                    a1.set(tranname);
                    updateTransactions(a1, 0);
            })
            .next()
            .when(intentIs(createTransactionSetList)).moveTo(handleCreateTransactionSetList);

        handleCreateTransactionSetList
            .body(context -> {
                    String tranlist = (String) context.getIntent().getValue("tranlist");
                    reactPlatform.reply(context, "Great!! We are moving in the right direction. What would be the mostrar attribute of the ?");
                    a1.set(tranlist);
                    updateTransactions(a1, 1);
            })
            .next()
            .when(intentIs(createTransactionSetMostrar)).moveTo(handleCreateTransactionSetMostrar);



        // Ask for the mostrar attribute of the transaction
        handleCreateTransactionSetMostrar
            .body(context -> {
                    String tranmost = (String) context.getIntent().getValue("tranmost");
                    reactPlatform.reply(context, "Great!! What would you like to call the first parameter of the transaction?");
                    a1.set(tranmost);
                    updateTransactions(a1, 2);
            })
            .next()
            .when(intentIs(createParameterSetName)).moveTo(handleCreateParameterSetName);

        handleCreateTransactionAssertParameter
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the parameter?");
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
                    reactPlatform.reply(context, "Great. From which asset would you like to have the relationship?");
            })
            .next()
            .when(intentIs(createTransactionAssetRelationshipName)).moveTo(handleCreateTransactionAssetRelationshipName);

        handleCreateTransactionAssertTranRelationship
            .body(context -> {
                    reactPlatform.reply(context, "Great. From which participant would you like to have the relationship?");
            })
            .next()
            .when(intentIs(createTransactionTranRelationshipName)).moveTo(handleCreateTransactionTranRelationshipName);


        handleCreateTransactionTranRelationshipName
            .body(context -> {
                    String relpart = (String) context.getIntent().getValue("relpart");
                    reactPlatform.reply(context, "Great. Would you like to have any events for the relationship?");
            })
            .next()
            .when(intentIs(createTransactionAssertRelationshipEvent)).moveTo(handleCreateTransactionAssertRelationshipEvent);

        handleCreateTransactionAssetRelationshipName
            .body(context -> {
                    String relasst = (String) context.getIntent().getValue("relasst");
                    reactPlatform.reply(context, "Great. Would you like to have any events for the relationship?");
            })
            .next()
            .when(intentIs(createTransactionAssertRelationshipEvent)).moveTo(handleCreateTransactionAssertRelationshipEvent);

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
                    a1.set("}");
                    updateTransactions(a1, 1);
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Editing a transaction***************************************************************************************************************************
        */

        handleEditTransaction
            .body(context -> {
                    reactPlatform.reply(context, "Great. Which transaction would you like to edit?");
            })
            .next()
            .when(intentIs(editTransactionGetName)).moveTo(handleEditTransactionGetName);

        handleEditTransactionGetName
            .body(context -> {
                    String getranname = (String) context.getIntent().getValue("getranname");
                    reactPlatform.reply(context, "That's great. Let me check the directory.");
            })
            .next()
            .when(intentIs(editTransactionConfirm)).moveTo(handleCreateParameterSetName);

        handleEditTransactionConfirm
            .body(context -> {
                    reactPlatform.reply(context, "Thanks for letting me know! What changes would you like to make to the transaction?");
            })
            .next()
            .when(intentIs(createTransactionAssertParameter)).moveTo(handleCreateParameterSetName);

         //*****************************************************************************************************************************************

        //*****************************************************************************************************************************************

        /*
        Deleting a transaction***************************************************************************************************************************
        */

        handleDeleteTransaction
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which transaction would you like to delete?");
            })
            .next()
            .when(intentIs(deleteTransactionGetName)).moveTo(handleDeleteTransactionGetName);

        handleDeleteTransactionGetName
            .body(context -> {
                    String gdtranname = (String) context.getIntent().getValue("gdtranname");
                    reactPlatform.reply(context, "Are you sure that you want to delete the transaction named " + gdtranname + "?" );
            })
            .next()
            .when(intentIs(deleteTransactionConfirm)).moveTo(handleDeleteTransactionConfirm);

        handleDeleteTransactionConfirm
            .body(context -> {
                    reactPlatform.reply(context, "That's cool. I have completed the deletion. What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Reading a transaction***************************************************************************************************************************
        */

        handleReadTransaction
            .body(context -> {
                    reactPlatform.reply(context, "Okay. Which transaction would you like to read?");
            })
            .next()
            .when(intentIs(readTransactionGetName)).moveTo(handleReadTransactionGetName);

        handleReadTransactionGetName
            .body(context -> {
                    String grtranname = (String) context.getIntent().getValue("grtranname");
                    reactPlatform.reply(context, "Are you sure that you want to read the transaction named " + grtranname + "?" );
            })
            .next()
            .when(intentIs(readTransactionConfirm)).moveTo(handleReadTransactionConfirm);

        handleReadTransactionConfirm
            .body(context -> {
                    reactPlatform.reply(context, "That's cool. Have a look at the transaction. What would you like to do now?");
            })
            .next()
            .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
            .when(intentIs(createAsset)).moveTo(handleCreateAsset)
            .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
            .when(intentIs(contractDone)).moveTo(handleDone);

        //*****************************************************************************************************************************************

        /*
        Creating a parameter***************************************************************************************************************************
        */
        handleCreateParameterSetName
            .body(context -> {
                    String paraname = (String) context.getIntent().getValue("paraname");
                    reactPlatform.reply(context, "Great. What is the type of the parameter that you just created?");
                    a1.set("Parameter {" + "\n" + "Name: " + paraname + "\n");
                    updateParameters(a1);
            })
            .next()
            .when(intentIs(createParameterSetType)).moveTo(handleCreateParameterSetType);

        handleCreateParameterSetType
            .body(context -> {
                    String paratype = (String) context.getIntent().getValue("partparatype");
                    reactPlatform.reply(context, "Cool. Would you like to add or delete attributes from the element?");
                    a1.set( "Type: " + paratype + "\n" + "Identifier: " + "False" + "\n" +"}" + "\n");
                    updateParameters(a1);
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
        handleDone
                .body(context -> {
                        generateWholeFile();
                        reactPlatform.reply(context, "Please wait for the file to be generated. Please check the code in the src-gen folder.");
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

        Configuration botConfiguration = new BaseConfiguration();
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
        nameOfContract = nameOfContract + str;
    }

    public static void funcCreateParticipant() {
        String tempname = "Participant_Name_None_" + Integer.toString(numberOfParticipants);
        String templist = "Participant_List_None_" + Integer.toString(numberOfParticipants);
        Participant tempparticipant = new Participant();
        tempparticipant.name = tempname;
        tempparticipant.list = templist;
        tempparticipant.creator = false;
        tempparticipant.parameters = new HashMap<String, Parameter> ();
        tempparticipant.parameterNames = new ArrayList<String> ();
        contractParticipants.add(tempparticipant);
        participantNames.add(tempname);
        currentParticipant = numberOfParticipants;
        numberOfParticipants++;
    }

    public static void updateParticipants(AtomicReference<String> str, int flag){
        // This is to set the name of the participant that is being added to the contract
        if(flag == 0)
        {
            participantNames.set(currentParticipant, "" + str);
            contractParticipants.get(currentParticipant).name = "" + str;
        }
        if(flag == 1)
        {
            contractParticipants.get(currentParticipant).list = "" + str;
        }
    }

    public static void funcCreateAsset() {
        String temptype = "Asset_Type_None_" + Integer.toString(numberOfAssets);
        String tempname = "Asset_Name_None_" + Integer.toString(numberOfAssets);
        String templist = "Asset_List_None_" + Integer.toString(numberOfAssets);
        Asset tempasset = new Asset();
        tempasset.type = temptype;
        tempasset.name = tempname;
        tempasset.list = templist;
        tempasset.parameters = new HashMap<String, Parameter> ();
        tempasset.parameterNames = new ArrayList<String> ();
        contractAssets.add(tempasset);
        assetNames.add(tempname);
        currentAsset = numberOfAssets;
        numberOfAssets++;
    }

    public static void updateAssets(AtomicReference<String> str, int flag){
        if(flag == 0)
        {
            contractAssets.get(currentAsset).type = "" + str;
        }
        if(flag == 1)
        {
            contractAssets.get(currentAsset).name = "" + str;
            assetNames.set(currentAsset, "" + str);
        }
        if(flag == 2)
        {
            contractAssets.get(currentAsset).list = "" + str;
        }
    }

    public static void funcCreateTransaction() {
        String temptype = "Transaction_Type_None_" + Integer.toString(numberOfTransactions);
        String tempname = "Transaction_Name_None_" + Integer.toString(numberOfTransactions);
        String templist = "Transaction_List_None_" + Integer.toString(numberOfTransactions);
        Transaction temptransaction = new Transaction();
        temptransaction.conditions = new HashMap <String, Condition> ();
        temptransaction.mostrar = false;
        temptransaction.name = tempname;
        temptransaction.list = templist;
        temptransaction.parameters = new HashMap <String, Parameter> ();
        temptransaction.tranRels = new HashMap <String, TranRel> ();
        temptransaction.assetRels = new HashMap <String, AssetRel> ();
        temptransaction.conditionNames = new ArrayList <String> ();
        temptransaction.parameterNames = new ArrayList <String> ();
        temptransaction.tranRelNames = new ArrayList <String> ();
        temptransaction.assetRelNames = new ArrayList <String> ();
        contractTransactions.add(temptransaction);
        transactionNames.add(tempname);
        currentTransaction = numberOfTransactions;
        numberOfTransactions++;
    }

    public static void updateTransactions(AtomicReference<String> str, int flag){
        if(flag == 0)
        {
            contractTransactions.get(currentTransaction).name = "" + str;
            transactionNames.set(currentTransaction, "" + str);
        }
        if(flag == 1)
        {
            contractTransactions.get(currentTransaction).list = "" + str;
        }
        if(flag == 2)
        {
            if(("" + str) == "False" || ("" + str) == "false")
                contractTransactions.get(currentTransaction).mostrar = false;
        }
    }

    public static void updateParameters(AtomicReference<String> str){
        parameters = parameters + str;
    }

    //Function to generate the whole file
    public static void generateWholeFile(){
        wholeContract = wholeContract + nameOfContract;
        wholeContract = wholeContract + targetPlatform;
        wholeContract = wholeContract + participants;
        wholeContract = wholeContract + assets;
        wholeContract = wholeContract + transactions;

        String filename = "./src-gen/" + nameOfContract + ".scdsl";

        try{
                FileWriter fw = new FileWriter(filename);
                fw.write(wholeContract);
                fw.close();
                // ProcessBuilder pb = new ProcessBuilder("java", "-jar", "run.jar", filename);
                // Process p = pb.start();
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
    public HashMap <String, Event> events;
    public ArrayList <String> eventNames;
}

class AssetRel
{
    public String from;
    public String to;
    public String name;
    public String participant;
    public boolean create;
    public boolean read;
    public boolean update;
    public boolean delete;
    public boolean all;
    public HashMap <String, Event> events;
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
    public HashMap <String, Parameter> parameters;
    public ArrayList <String> parameterNames;
}

class Asset
{
    public String type;
    public String name;
    public String list;
    public HashMap <String, Parameter> parameters;
    public ArrayList <String> parameterNames;
}

class Transaction
{
    public HashMap <String, Condition> conditions;
    public boolean mostrar;
    public String name;
    public String list;
    public HashMap <String, Parameter> parameters;
    public HashMap <String, TranRel> tranRels;
    public HashMap <String, AssetRel> assetRels;
    public ArrayList <String> conditionNames;
    public ArrayList <String> parameterNames;
    public ArrayList <String> tranRelNames;
    public ArrayList <String> assetRelNames;
}