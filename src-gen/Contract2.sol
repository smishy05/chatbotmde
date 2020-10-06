	pragma solidity >=0.4.22 <0.7.0;

	contract Contract2{
		constructor(){
			
		}
		struct Part1{
		bytes32 para1;
		integer para2;
		address part1Address;
		 } 
		struct Part2{
		bytes32 para3;
		address part2Address;
		 } 
		string public para4;
		string public para5;
		string public para6;
		string public para7;
		modifier onlyPart1 {
		require( msg.sender == Part1.part1Address);
		_;
		}
	
	}
