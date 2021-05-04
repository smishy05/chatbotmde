	pragma solidity >=0.4.22 <0.7.0;

	contract MedicalRecord{
		constructor(){
			
		}
		struct Patient{
		bytes32 name;
		address patientAddress;
		 } 
		struct Record{
		bytes32 id;
		bytes32 owner;
		
		 } 
		function modifyPatientName()  public { 
		} 
		function getRecord()  public { 
		} 
		modifier onlyPatient {
		require( msg.sender == patient.patientAddress);
		_;
		}
	
	}
