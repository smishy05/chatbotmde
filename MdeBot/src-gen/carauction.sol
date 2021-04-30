	pragma solidity >=0.4.22 <0.7.0;

	contract carauction{
		constructor(){
			
		}
		struct Owner{
		bytes32 name;
		address ownerAddress;
		 } 
		struct Bidder{
		bytes32 name;
		address bidderAddress;
		 } 
		struct Car{
		bytes32 carnumber;
		
		 } 
		function placeBid()  public { 
		} 
		modifier onlyBidder {
		require( msg.sender == Bidder.BidderAddress);
		_;
		}
	
	}
