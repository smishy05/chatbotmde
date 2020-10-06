package com.xatkit.example;

import com.xatkit.core.XatkitCore;
import com.xatkit.plugins.react.platform.ReactPlatform;
import com.xatkit.plugins.react.platform.io.ReactEventProvider;
import com.xatkit.plugins.react.platform.io.ReactIntentProvider;
import lombok.val;
import org.apache.commons.configuration2.BaseConfiguration;
import org.apache.commons.configuration2.Configuration;

import static com.xatkit.dsl.DSL.eventIs;
import static com.xatkit.dsl.DSL.any;
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

public class GreetingsBot {
        static String wholeContract = "";
        static String nameOfContract = "";
        static String targetPlatform = "";
        static String participants = "";
        static String assets = "";
        static String transactions = "";
        static String parameters = "";

        public static void main(String[] args) {


                // val platform = mapping("Platform")
                //         .entry()
                //         .value("IBM")
                //         .value("Azure")
                //         .value("Solidity");

                AtomicReference<String> a1 = new AtomicReference<>();
                AtomicReference<String> n = new AtomicReference<>();
                String name = "";
                
                //Intent to find if the user wants to create a contract
                val createContract = intent("CreateContract")
                        .trainingSentence("I want to create a contract")
                        .trainingSentence("Create a contract")
                        .trainingSentence("I wish to make a contract")
                        .trainingSentence("I wish to develop a contract")
                        .context("createC");

                //Intent to find if the user wants to edit a contract
                val editContract = intent("EditContract")
                        .trainingSentence("I want to edit a contract")
                        .trainingSentence("Edit a contract")
                        .trainingSentence("I wish to update a contract")
                        .trainingSentence("I wish to change a contract")
                        .context("updateC");

                //Obtain the name of the contract that is to be created or
                val contractName = intent("ContractName")
                        .trainingSentence("The name of the contract is CONT")
                        .trainingSentence("Name of the contract is CONT")
                        .trainingSentence("I want to call the contract as CONT")
                        .trainingSentence("Contract's name is CONT")
                        .trainingSentence("Name of the contract is CONT")
                        .trainingSentence("Contract = CONT")
                        .trainingSentence("Contract is CONT")
                        .context("contract")
                        .parameter("cName").fromFragment("CONT").entity(any());

                //This is to obtain the target platform for the smart contract
                val targetPlatform = intent("TargetPlatform")
                        .trainingSentence("The target platform is PLAT")
                        .trainingSentence("Target Platform is PLAT")
                        .trainingSentence("The platform is PLAT")
                        .trainingSentence("I want the contract in PLAT")
                        .trainingSentence("The contract should be in PLAT")
                        .context("platform")
                        .parameter("plat").fromFragment("PLAT").entity(any());

                val createParticipant = intent("CreateParticipant")
                    .trainingSentence("I want to create a participant")
                    .trainingSentence("Create a participant")
                    .trainingSentence("I wish to create a participant")
                    .trainingSentence("A participant should be created")
                    .context("creaPart");

                val createAsset = intent("CreateAsset")
                    .trainingSentence("I want to create an asset")
                    .trainingSentence("Create an asset")
                    .trainingSentence("I wish to create an asset")
                    .trainingSentence("An asset should be created")
                    .context("creaAsse");

                val createTransaction = intent("CreateTransaction")
                    .trainingSentence("I want to create a transaction")
                    .trainingSentence("Create a transaction")
                    .trainingSentence("I wish to create a transaction")
                    .trainingSentence("A transaction should be created")
                    .context("creaTran");

                val editParticipant = intent("EditParticipant")
                    .trainingSentence("I want to edit a participant")
                    .trainingSentence("Edit a participant")
                    .trainingSentence("I wish to edit a participant")
                    .trainingSentence("A participant should be edited")
                    .context("editPart");

                val editAsset = intent("EditAsset")
                    .trainingSentence("I want to edit an asset")
                    .trainingSentence("Edit an asset")
                    .trainingSentence("I wish to edit an asset")
                    .trainingSentence("An asset should be edited")
                    .context("editAsse");

                val editTransaction = intent("EditTransaction")
                    .trainingSentence("I want to edit a transaction")
                    .trainingSentence("Edit a transaction")
                    .trainingSentence("I wish to edit a transaction")
                    .trainingSentence("A transaction should be edited")
                    .context("editTran");
                
                val deleteParticipant = intent("DeleteParticipant")
                    .trainingSentence("I want to delete a participant")
                    .trainingSentence("Delete a participant")
                    .trainingSentence("I wish to delete a participant")
                    .trainingSentence("A participant should be deleted")
                    .context("delePart");

                val deleteAsset = intent("DeleteAsset")
                    .trainingSentence("I want to delete an asset")
                    .trainingSentence("Delete an asset")
                    .trainingSentence("I wish to delete an asset")
                    .trainingSentence("An asset should be deleted")
                    .context("deleAsse");

                val deleteTransaction = intent("DeleteTransaction")
                    .trainingSentence("I want to delete a transaction")
                    .trainingSentence("Delete a transaction")
                    .trainingSentence("I wish to delete a transaction")
                    .trainingSentence("A transaction should be deleted")
                    .context("deleTran");
                
                val readParticipant = intent("ReadParticipant")
                    .trainingSentence("I want to read a participant")
                    .trainingSentence("Read a participant")
                    .trainingSentence("I wish to read a participant")
                    .trainingSentence("A participant should be read")
                    .context("readPart");

                val readAsset = intent("ReadAsset")
                    .trainingSentence("I want to read an asset")
                    .trainingSentence("Read an asset")
                    .trainingSentence("I wish to read an asset")
                    .trainingSentence("An asset should be read")
                    .context("readAsse");

                val readTransaction = intent("ReadTransaction")
                    .trainingSentence("I want to read a transaction")
                    .trainingSentence("Read a transaction")
                    .trainingSentence("I wish to read a transaction")
                    .trainingSentence("A transaction should be read")
                    .context("readTran");

                val participantName = intent("ParticipantName")
                        .trainingSentence("P = PART")
                        .trainingSentence("Participant is PART")
                        .trainingSentence("Name of the participant is PART")
                        .context("participant")
                        .parameter("pName").fromFragment("PART").entity(any());

                val assetName = intent("AssetName")
                        .trainingSentence("A = AS")
                        .trainingSentence("Asset is AS")
                        .trainingSentence("Name of the asset is AS")
                        .context("asset")
                        .parameter("aName").fromFragment("AS").entity(any());

                val parameterName = intent("ParameterName")
                        .trainingSentence("X = PARA")
                        .trainingSentence("Parameter is PARA")
                        .trainingSentence("Name of the parameter is PARA")
                        .context("parameter")
                        .parameter("paName").fromFragment("PARA").entity(any());

                val contractDone = intent("ContractDone")
                        .trainingSentence("Done")
                        .trainingSentence("done")
                        .trainingSentence("DONE")
                        .context("done");

                ReactPlatform reactPlatform = new ReactPlatform();
                ReactEventProvider reactEventProvider = new ReactEventProvider(reactPlatform);
                ReactIntentProvider reactIntentProvider = new ReactIntentProvider(reactPlatform);

                val init = state("Init");
                val awaitingInput = state("AwaitingInput");
                val handleCreateContract = state("HandleCreateContract");
                val handleEditContract = state("HandleEditContract");
                val handleCreateContractName = state("HandleCreateContractName");
                val handleEditContractName = state("HandleEditContractName");
                val handleCreateParticipant = state("HandleCreateParticipant");
                val handleEditParticipant = state("HandleEditParticipant");
                val handleDeleteParticipant = state("HandleDeleteParticipant");
                val handleReadParticipant = state("HandleReadParticipant");
                val handleCreateAsset = state("HandleCreateAsset");
                val handleEditAsset = state("HandleEditAsset");
                val handleDeleteAsset = state("HandleDeleteAsset");
                val handleReadAsset = state("HandleReadAsset");
                val handleCreateTransaction = state("HandleCreateTransaction");
                val handleEditTransaction = state("HandleEditTransaction");
                val handleDeleteTransaction = state("HandleDeleteTransaction");
                val handleReadTransaction = state("HandleReadTransaction");
                val handleContract = state("HandleContract");
                val handlePlatform = state("HandlePlatform");
                val handleParticipant = state("HandleParticipant");
                val handleAsset = state("HandleAsset");
                val handleParameter = state("HandleParameter");
                val handleDone = state("HandleDone");

                //Start the chatbot
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
                        .when(intentIs(contractName)).moveTo(handleEditContractName);

                handleCreateContractName
                        .body(context -> {
                                String contractname = (String) context.getNlpContext().get("contract").get("cName");
                                reactPlatform.reply(context, "Wow. Great Job. So we are going to create a smart contract with the name " + contractname + ". So, what do you wish do now? Would you like to define create some elements in the smart contract?");
                                a1.set("Contract : " + contractname + "\n");
                                add(a1);
                                n.set(contractname);
                        })
                        .next()
                        .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
                        .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
                        .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
                        .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
                        .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
                        .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
                        .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
                        .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
                        .when(intentIs(createAsset)).moveTo(handleCreateAsset)
                        .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
                        .when(intentIs(editAsset)).moveTo(handleEditAsset)
                        .when(intentIs(readAsset)).moveTo(handleReadAsset);

                handleEditContractName
                        .body(context -> {
                                String contractname = (String) context.getNlpContext().get("contract").get("cName");
                                reactPlatform.reply(context, "That's great. Lemme find the" + contractname + "smart contract in the contract in the directory. What would you like to do next? Do you want to create/edit/delete/read some elements in the smart contract?");
                                a1.set("Contract : " + contractname + "\n");
                                add(a1);
                                n.set(contractname);
                                setContractName(n);
                        })
                        .next()
                        .when(intentIs(createParticipant)).moveTo(handleCreateParticipant)
                        .when(intentIs(deleteParticipant)).moveTo(handleDeleteParticipant)
                        .when(intentIs(editParticipant)).moveTo(handleEditParticipant)
                        .when(intentIs(readParticipant)).moveTo(handleReadParticipant)
                        .when(intentIs(createTransaction)).moveTo(handleCreateTransaction)
                        .when(intentIs(deleteTransaction)).moveTo(handleDeleteTransaction)
                        .when(intentIs(editTransaction)).moveTo(handleEditTransaction)
                        .when(intentIs(readTransaction)).moveTo(handleReadTransaction)
                        .when(intentIs(createAsset)).moveTo(handleCreateAsset)
                        .when(intentIs(deleteAsset)).moveTo(handleDeleteAsset)
                        .when(intentIs(editAsset)).moveTo(handleEditAsset)
                        .when(intentIs(readAsset)).moveTo(handleReadAsset);

                handlePlatform
                        .body(context ->{
                                String platform = (String) context.getNlpContext().get("platform").get("plat");
                                reactPlatform.reply(context, platform);
                                a1.set("Platform : " + platform + "\n");
                                add(a1);
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleCreateParticipant
                        .body(context -> {
                                reactPlatform.reply(context, "That's great. We are heading in the perfect direction. What would be the name of the participant?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleEditParticipant
                        .body(context -> {
                                reactPlatform.reply(context, "That's great. We are heading in the right direction. Which participant do you want to edit?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleDeleteParticipant
                        .body(context -> {
                                reactPlatform.reply(context, "That sounds good. Which participant do you want to delete?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);
                
                handleCreateAsset
                        .body(context -> {
                                reactPlatform.reply(context, "That's great. We are heading in the perfect direction. What would be the name of the asset?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleEditAsset
                        .body(context -> {
                                reactPlatform.reply(context, "That's great. We are heading in the right direction. Which asset do you want to edit?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleDeleteAsset
                        .body(context -> {
                                reactPlatform.reply(context, "That sounds good. Which asset do you want to delete?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleReadAsset
                        .body(context -> {
                                reactPlatform.reply(context, "That's great. Which asset do you want to read?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);
                
                handleCreateTransaction
                        .body(context -> {
                                reactPlatform.reply(context, "That's great. We are heading in the perfect direction. What would be the name of the transaction?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleEditTransaction
                        .body(context -> {
                                reactPlatform.reply(context, "That's great. We are heading in the right direction. Which transaction do you want to edit?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleDeleteTransaction
                        .body(context -> {
                                reactPlatform.reply(context, "That sounds good. Which transaction do you want to delete?");
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleParticipant
                        .body(context -> {
                                String participantname = (String) context.getNlpContext().get("participant").get("pName");
                                reactPlatform.reply(context, participantname);
                                a1.set("Participant \n Creator : True { \n Name : " + participantname + "\n List : No \n } \n");
                                add(a1);
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleAsset
                        .body(context -> {
                                String assetname = (String) context.getNlpContext().get("asset").get("aName");
                                reactPlatform.reply(context, assetname);
                                a1.set("Asset \n Type : string { \n Name : " + assetname + "\n List : No \n } \n");
                                add(a1);
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleParameter
                        .body(context -> {
                                String parametername = (String) context.getNlpContext().get("parameter").get("paName");
                                reactPlatform.reply(context, parametername);
                                a1.set("Parameter { \n Name : " + parametername + " \n Type : integer \n Identifier : True \n } \n");
                                add(a1);
                        })
                        .next()
                        .when(intentIs(assetName)).moveTo(handleAsset)
                        .when(intentIs(participantName)).moveTo(handleParticipant)
                        .when(intentIs(parameterName)).moveTo(handleParameter)
                        .when(intentIs(contractDone)).moveTo(handleDone);

                handleDone
                        .body(context -> {
                                generatefile(n);
                                reactPlatform.reply(context, "Wait for the file to be generated.");
                        })
                        .next()
                        .moveTo(awaitingInput);

                // handleFunction
                // .body(context -> {
                //         String functionname = (String) context.getNlpContext().get("function").get("fName");
                //         reactPlatform.reply(context, functionname);
                // })
                // .next()
                // .moveTo(awaitingInput);

                val defaultFallback = fallbackState()
                        .body(context -> reactPlatform.reply(context, "Sorry, I didn't, get it"));

                val botModel = model()
                        .useIntent(createContract)
                        .useIntent(editContract)
                        .useIntent(contractName)
                        .useIntent(targetPlatform)
                        .useIntent(createParticipant)
                        .useIntent(editParticipant)
                        .useIntent(deleteParticipant)
                        .useIntent(readParticipant)
                        .useIntent(createAsset)
                        .useIntent(editAsset)
                        .useIntent(deleteAsset)
                        .useIntent(readAsset)
                        .useIntent(createTransaction)
                        .useIntent(editTransaction)
                        .useIntent(deleteTransaction)
                        .useIntent(readTransaction)
                        .useIntent(participantName)
                        .useIntent(assetName)
                        .useIntent(parameterName)
                        .useIntent(contractDone)
                        .usePlatform(reactPlatform)
                        .listenTo(reactEventProvider)
                        .listenTo(reactIntentProvider)
                        .state(awaitingInput)
                        .state(handleCreateContract)
                        .state(handleEditContract)
                        .state(handleCreateContractName)
                        .state(handleEditContractName)
                        .state(handleContract)
                        .state(handlePlatform)
                        .state(handleCreateParticipant)
                        .state(handleEditParticipant)
                        .state(handleDeleteParticipant)
                        .state(handleReadParticipant)
                        .state(handleCreateAsset)
                        .state(handleEditAsset)
                        .state(handleDeleteAsset)
                        .state(handleReadAsset)
                        .state(handleCreateTransaction)
                        .state(handleEditTransaction)
                        .state(handleDeleteTransaction)
                        .state(handleReadTransaction)
                        .state(handleParticipant)
                        .state(handleAsset)
                        .state(handleParameter)
                        .state(handleDone)
                        .initState(init)
                        .defaultFallbackState(defaultFallback);

                Configuration botConfiguration = new BaseConfiguration();

                XatkitCore xatkitCore = new XatkitCore(botModel, botConfiguration);
                xatkitCore.run();
        }

        public static void add(AtomicReference<String> str){
                wholeContract = wholeContract + str;
        }

        public static void setContractName(AtomicReference<String> str){
                nameOfContract = nameOfContract + str;
        }

        public static void generatefile(AtomicReference<String> name){
                String filename = "./" + name + ".scdsl";
                try{
                        FileWriter fw=new FileWriter(filename);
                        fw.write(wholeContract);
                        fw.close();
                        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "run.jar", filename);
                        Process p = pb.start();
                }
                catch(Exception e){System.out.println(e);}

        }
}