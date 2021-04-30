	pragma solidity >=0.4.22 <0.7.0;

	contract HotelRoom{
		constructor(){
			
		}
		struct Customer{
		bytes32 name;
		address customerAddress;
		 } 
		struct Room{
		bytes32 roomnumber;
		
		 } 
		function bookRoom()  public { 
		} 
		modifier onlyCustomer {
		require( msg.sender == Customer.CustomerAddress);
		_;
		}
	
	}
