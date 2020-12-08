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
import java.util.concurrent.atomic.AtomicReference;
import java.lang.ProcessBuilder;

/**
 * This is an example greetings bot designed with Xatkit.
 * <p>
 * You can check our <a href="https://github.com/xatkit-bot-platform/xatkit/wiki">wiki</a>
 * to learn more about bot creation, supported platforms, and advanced usage.
 */
public class MdeBot {

    static String wholeContract = "";
    static String nameOfContract = "";
    static String targetPlatform = "";
    static String participants = "";
    static String assets = "";
    static String transactions = "";
    static String participantparameters = "";
    static String assetparameters = "";
    static String transactionparameters = "";
    
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

        //Obtain the name of the contract that is to be created or
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
        User tells tells that he/she wants to create a participant - Bot asks the name of the participant
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

        // Parameter------------------------------------------------------------------------------------------------
        val createParticipantParameterSetName = intent("CreateParticipantParameterSetName")
            .trainingSentence("The name of the parameter should be PARTPARANAME")
            .trainingSentence("The name of the parameter is PARTPARANAME")
            .trainingSentence("I want to name the parameter as PARTPARANAME")
            .trainingSentence("Set the name of the parameter as PARTPARANAME")
            .parameter("partparaname").fromFragment("PARTPARANAME").entity(any());

        val createParticipantParameterSetType = intent("CreateParticipantParameterSetType")
            .trainingSentence("The type of the parameter should be PARTPARATYPE")
            .trainingSentence("The type of the parameter is PARTPARATYPE")
            .trainingSentence("Set the type of the parameter as PARTPARATYPE")
            .parameter("partparatype").fromFragment("PARTPARATYPE").entity(any());

        val createParticipantAssertParameter = intent("CreateParticipantAssertParameter")
            .trainingSentence("Yes, I want to have a parameter for this participant")
            .trainingSentence("Yes, I want to have more parameters for the participant")
            .trainingSentence("I want to build more parameters for this participant");

        val createParticipantAssertRelationship = intent("CreateParticipantAssertRelationship")
            .trainingSentence("Yes, I want to build a relatiionship for this participant")
            .trainingSentence("Yes, I want to build more relationships for the participant")
            .trainingSentence("I want to build more relationships for this participant");

        val createParticipantDone = intent("CreateParticpantDone")
            .trainingSentence("No, that's all for the participant")
            .trainingSentence("This participant is completed")
            .trainingSentence("No, I don't want to change the participant anymore");

        val editParticipant = intent("EditParticipant")
            .trainingSentence("I want to edit a participant")
            .trainingSentence("Edit a participant")
            .trainingSentence("I wish to edit a participant")
            .trainingSentence("A participant should be edited");

        val deleteParticipant = intent("DeleteParticipant")
            .trainingSentence("I want to delete a participant")
            .trainingSentence("Delete a participant")
            .trainingSentence("I wish to delete a participant")
            .trainingSentence("A participant should be deleted");

        val readParticipant = intent("ReadParticipant")
            .trainingSentence("I want to read a participant")
            .trainingSentence("Read a participant")
            .trainingSentence("I wish to read a participant")
            .trainingSentence("A participant should be read");

        // Assets---------------------------------------------------------------------------------------------
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

        val editAsset = intent("EditAsset")
            .trainingSentence("I want to edit an asset")
            .trainingSentence("Edit an asset")
            .trainingSentence("I wish to edit an asset")
            .trainingSentence("An asset should be edited");

        val deleteAsset = intent("DeleteAsset")
            .trainingSentence("I want to delete an asset")
            .trainingSentence("Delete an asset")
            .trainingSentence("I wish to delete an asset")
            .trainingSentence("An asset should be deleted");

        val readAsset = intent("ReadAsset")
            .trainingSentence("I want to read an asset")
            .trainingSentence("Read an asset")
            .trainingSentence("I wish to read an asset")
            .trainingSentence("An asset should be read");

        // Transactions-----------------------------------------------------------------------------------------
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

        val editTransaction = intent("EditTransaction")
            .trainingSentence("I want to edit a transaction")
            .trainingSentence("Edit a transaction")
            .trainingSentence("I wish to edit a transaction")
            .trainingSentence("A transaction should be edited");

        val deleteTransaction = intent("DeleteTransaction")
            .trainingSentence("I want to delete a transaction")
            .trainingSentence("Delete a transaction")
            .trainingSentence("I wish to delete a transaction")
            .trainingSentence("A transaction should be deleted");

        val readTransaction = intent("ReadTransaction")
            .trainingSentence("I want to read a transaction")
            .trainingSentence("Read a transaction")
            .trainingSentence("I wish to read a transaction")
            .trainingSentence("A transaction should be read");

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
        // States of creating participants
        val handleCreateParticipant = state("HandleCreateParticipant");
        val handleCreateParticipantSetName = state("HandleCreateParticipantSetName");
        val handleCreateParticipantSetList = state("HandleCreateParticipantSetList");
        val handleCreateParticipantParameterSetName = state("HandleCreateParticipantParameterSetName");
        val handleCreateParticipantParameterSetType = state("HandleCreateParticipantParameterSetType");
        val handleCreateParticipantAssertParameter = state("HandleCreateParticipantAssertParameter");
        val handleCreateParticipantAssertRelationship = state("HandleCreateParticipantAssertRelationship");
        val handleCreateParticipantDone = state("HandleCreateParticipantDone");
        // States of creating assets
        val handleCreateAsset = state("HandleCreateAsset");
        val handleCreateAssetSetType = state("HandleCreateAssetType");
        val handleCreateAssetSetName = state("HandleCreateAssetSetName");
        // States of creating transactions
        val handleCreateTransaction = state("HandleCreateTransaction");
        val handleCreateTransactionSetName = state("HandleCreateTransactionSetName");
        // This is for the parameters
        // The intent that the bot is detecting in case of parameters is same across all three elements
        val handleCreateParameterSetName = state("HandleParameterSetName");
        // The user has provided all the information from his/her side
        val handleDone = state("HandleDone");


        init
                .next()
                .when(eventIs(ReactEventProvider.ClientReady)).moveTo(awaitingInput);

        //Ask the user if they wish to create or edit a contract. This is the first line that the user says.
        awaitingInput
                .body(context -> {
                        reactPlatform.reply(context, "Hey there. I am GenoSmartCon. I am here to guide you to create and edit smart contracts. Do you want to create or edit a smart contract?");
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
                        reactPlatform.reply(context, "Wow. Great Job. So we are going to create a smart contract with the name " + contractname + ". So, what do you wish do now? Would you like to define create some elements in the smart contract?");
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
                    updateParticipants(a1);
            })
            .next()
            .when(intentIs(createParticipantSetName)).moveTo(handleCreateParticipantSetName);

        // Detect and store the name of the participant
        handleCreateParticipantSetName
            .body(context -> {
                    String partname = (String) context.getIntent().getValue("partname");
                    reactPlatform.reply(context, "Awesome. What would you like to name the list attribute of the participant?");
                    a1.set("Name" + ": " + partname + "\n");
                    updateParticipants(a1);
            })
            .next()
            .when(intentIs(createParticipantSetList)).moveTo(handleCreateParticipantSetList);

        // Detect and store the list attribute of the participant
        handleCreateParticipantSetList
            .body(context -> {
                    String partlist = (String) context.getIntent().getValue("partlist");
                    reactPlatform.reply(context, "Great!! We are moving in the right direction. What would be the name of the first parameter of the participant?");
                    a1.set("List" + ": " + partlist + "\n");
                    updateParticipants(a1);
            })
            .next()
            .when(intentIs(createParticipantParameterSetName)).moveTo(handleCreateParticipantParameterSetName);

        handleCreateParticipantParameterSetName
            .body(context -> {
                    String partparaname = (String) context.getIntent().getValue("partparaname");
                    reactPlatform.reply(context, "Great. What is the type of the parameter that you just created?");
                    a1.set("Parameter {" + "\n" + "Name: " + partparaname + "\n");
                    updateParticipants(a1);
            })
            .next()
            .when(intentIs(createParticipantParameterSetType)).moveTo(handleCreateParticipantParameterSetType);

        handleCreateParticipantParameterSetType
            .body(context -> {
                    String partparatype = (String) context.getIntent().getValue("partparatype");
                    reactPlatform.reply(context, "Cool. Would you like to add or delete attributes from the parameter?");
                    a1.set( "Type: " + partparatype + "\n" + "Identifier: " + "False" + "\n" +"}" + "\n");
                    updateParticipants(a1);
            })
            .next()
            .when(intentIs(createParticipantAssertParameter)).moveTo(handleCreateParticipantAssertParameter)
            .when(intentIs(createParticipantAssertRelationship)).moveTo(handleCreateParticipantAssertRelationship)
            .when(intentIs(createParticipantDone)).moveTo(handleCreateParticipantDone);

        handleCreateParticipantAssertParameter
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the parameter?");
            })
            .next()
            .when(intentIs(createParticipantParameterSetName)).moveTo(handleCreateParticipantParameterSetName);

        handleCreateParticipantAssertRelationship
            .body(context -> {
                    reactPlatform.reply(context, "Great. What would you like to name the parameter?");
            })
            .next()
            .moveTo(awaitingInput);

        handleCreateParticipantDone
                .body(context -> {
                        reactPlatform.reply(context, "Wow. Great Job. So we have created a participant. What do you wish do now? Would you like to define create some more elements for the smart contract?");
                })
                .next()
                .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
                .when(intentIs(createAsset)).moveTo(handleCreateAsset)
                .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
                .when(intentIs(contractDone)).moveTo(handleDone);

        /*
        Creating an asset***************************************************************************************************************************
        */

        // The user says that he/she wants to create an asset
        handleCreateAsset
            .body(context -> {
                    reactPlatform.reply(context, "Looks like we are heading somewhere :). What should be the type of the asset?");
                    a1.set("Asset" + "\n");
                    updateAssets(a1);
            })
            .next()
            .when(intentIs(createAssetSetType)).moveTo(handleCreateAssetSetType);

        // Set the type attribute of the asset
        handleCreateAssetSetType
            .body(context -> {
                    String assttype = (String) context.getIntent().getValue("assttype");
                    reactPlatform.reply(context, "Perfect. What would you like to name the asset?");
                    a1.set("Type: " + assttype + " {");
                    updateAssets(a1);
            })
            .next()
            .when(intentIs(createAssetSetName)).moveTo(handleCreateAssetSetName);

        // Detect and store the name of the asset
        handleCreateAssetSetName
            .body(context -> {
                    String asstname = (String) context.getIntent().getValue("asstname");
                    reactPlatform.reply(context, "Awesome. What would you like to name the list attribute of the asset?");
                    a1.set("Name" + ": " + asstname);
                    updateAssets(a1);
            })
            .next()
            .moveTo(awaitingInput);

        /*
        Creating a transaction***************************************************************************************************************************
        */

        // The user says that he/she wants to create a transaction
        handleCreateTransaction
            .body(context -> {
                    reactPlatform.reply(context, "Looks like we are heading somewhere :). What would you like to name the transaction?");
                    a1.set("Transaction {");
                    updateTransactions(a1);
            })
            .next()
            .when(intentIs(createTransactionSetName)).moveTo(handleCreateTransactionSetName);

        // Detect and store the name of the transaction
        handleCreateTransactionSetName
            .body(context -> {
                    String tranname = (String) context.getIntent().getValue("tranname");
                    reactPlatform.reply(context, "Awesome. What would you like to name the list attribute of the participant?");
                    a1.set("Name" + ": " + tranname);
                    updateTransactions(a1);
            })
            .next()
            .moveTo(awaitingInput);

        /*
        Creating a parameter***************************************************************************************************************************
        */

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

    public static void updateParticipants(AtomicReference<String> str){
        participants = participants + str;
    }

    public static void updateAssets(AtomicReference<String> str){
        assets = assets + str;
    }

    public static void updateTransactions(AtomicReference<String> str){
        transactions = transactions + str;
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
                FileWriter fw=new FileWriter(filename);
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
