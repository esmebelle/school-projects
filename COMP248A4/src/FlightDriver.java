// ----------------------------------------------------------
// Assignment 4
// Written by: Esmé Bellefeuille (40280961)
// For COMP 248 Section-W – Winter 2025
// ----------------------------------------------------------
// Demo class for Flight.java; defines the codes 101-110 
// for the user 
// ----------------------------------------------------------
import java.util.Arrays;
import java.util.Scanner; // import scanner 

public class FlightDriver {

	public static void main(String[] args) { 

				// variable declarations
				Scanner input=new Scanner(System.in);
				
				int Code=0;
				Flight flight;
				String FlightName="";
				String FlightCode="";
				String Permit=""; 
				int i=0; 
				Flight test = new Flight(); 
				Person thePilot = null;
				test.setPilot(thePilot); 
				Person [] flightAttd = null; 
				Person [] passengers = null; 
				int count=0; 
				
				String Banner="""
						+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
						| Simple Flights Management System (SFMS)               |
						+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
						""";
				
				String Desc="""
						Code -> Description
						+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
						 101 -> Define the Flight
						 102 -> Add Pilot to the Flight
						 103 -> Add Flight Attendants to the Flight 
						 104 -> Register Passenger to the Flight
						 105 -> Deregister Flight Attendant(s) and/or Passenger(s)
						 106 -> Enter/Update Passenger(s) Charges
						 107 -> Display Passengers' Statistics 
						 108 -> Display Flight Statistics 
						 109 -> Display Chargesheet
						 110 -> Exit
						""";
				
				// display banner and instructions 
				System.out.println(Banner); 
				System.out.println(Desc);
				System.out.print("Please enter a Code, from the aforementioned, that corresponds to your task: "); 
				Code=input.nextInt();
				
				while (Code>100&&Code<111) { // do i have to have a catch-all for invalid input or no...like an if-else around this or no........
					
					switch (Code) {
						case 101: { // code 101: define flight
							// test: YULCDG 102CANFLY VALID2022-2029 
							System.out.println("\n\n...Define Flight...\n.........................");
							test.read101(test);// invoke a method that reads input 
						
							//System.out.println(test.getFlightName() + test.getFlightCode() + test.getFlightPermit()); // just to check 
							System.out.println(test);
							System.out.print("Successful operation! Kindly continue by entering a Code, from the menu above, that corresponds to your task: "); 
							Code=input.nextInt();
							break;
						}
						//test: 40566443 Milo Russer
						case 102: { // code 102: add pilot
							thePilot=new Person();
							System.out.println("\n\n...Add the Pilot to the Flight...\n........................."); 

							// creates a new instance of object Person called thePilot, and initialise its arguments as the succeeding input 
							thePilot.read102(thePilot);
							test.setPilot(thePilot);

							System.out.println("getPilot(): " + test.getPilot()); 
							
							System.out.print("Successful operation! Kindly continue by entering a Code, from the menu above, that corresponds to your task: "); 
							Code=input.nextInt();
							break;
						} 
						
						// test values for code 103: // CHECK 103 FOR ALREADY EXISTS 
						// 40280961,Esme,Bellefeuille;40250967,Norah,Wilcox;70980654,Alessio,Mazza
						// 30450671,Mariia,Prosukova;60750439,Nolan,Sturk;80976878,Sarah,Amri
						// 40280961,Esme,Bellefeuille;40250967,Norah,Wilcox;30450671,Mariia,Prosukova;60750439,Nolan,Sturk;80976878,Sarah,Amri;70980654,Alessio,Mazza;30450671,Mariia,Prosukova;60750439,Nolan,Sturk;30255564,Dante,Kierstead
						case 103: { // code 103: add flight attendants
							System.out.println("\n\n...Add Flight Attendants to the Flight...\n.........................................");
							//Person [] flightAttd=new Person[i];
							// flightAttd=new Person [count];
							
							System.out.println("Enter Flight Attendant(s) information (ID1,FirstName1,LastName1;ID2,FirstName2,LastName2): "); 
							String takeApart=input.next();
							if (test.getFlightAttend()!=null) {
								//System.out.println("There's something here already..."); // here, redirect to an alt 103 method 
							// and then append to the previously created array (whether or not that one is itself a combination of appended arrays) 
							// something like (although i think at the end of the alt method, not in the driver) test.appendToPersonArr(Person[], int)
							// and then re-set test.setFlightAsst to the new array that is a combination of both 
							// then just append 
							//test.appendToPersonArr(Person.inStrToPersonArr(takeApart, count, flightAttd, test), 103);
							//System.out.println("Appending complete."); 
							//0System.out.println(test.getFlightAttend());
							
							test.setFlightAsst(test.appendToPersonArr(Person.inStrToPersonArr(takeApart, count, flightAttd, test, 103), 103));
							//System.out.println(test.getFlightAttend()[4]); 
							//System.out.println(Arrays.toString(test.getFlightAttend())); 

							}
							else {
								test.setFlightAsst(Person.inStrToPersonArr(takeApart, count, flightAttd, test, 103)); // stored in flightAttend ARRAY 
							} 
							
							System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: "); 
							Code=input.nextInt();
							break;
						}
						
						case 104: { // code 104: register passengers 
							// test: 33378910,Chrissiah,DeCastro;45645630,Elli,Jobe;1234251,Henry,Leitch
							// test: 54095468,Rosa,Melendez-Barteaux;90977634,Margareta,Zabyrina;32344151,Melissa,Wehbe
							// test: 33378910,Chrissiah,DeCastro;76755010,Cael,Dixon;90977634,Margareta,Zabyrina
							//passengers=new Person [];
							System.out.println("\n\n...Register Passenger(s) at Flight...\n.........................");
							System.out.println("Enter Passengers information (ID1,FirstName1,LastName1;ID2,FirstName2,LastName2): "); 
							String information=input.next();
							
							if (test.getPassenger()!=null) {
								test.setPassenger(test.appendToPersonArr(Person.inStrToPersonArr(information, count, passengers, test, 104), 104));
								System.out.println(Arrays.toString(test.getPassenger())); 
							}
						
							else {
								
								test.setPassenger(Person.inStrToPersonArr(information, count, passengers, test, 104)); 
							}
							
							System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: "); 
							Code=input.nextInt();
							break;
							
						}
						
						case 105: { // code 105: deregister flight attendant(s) or passenger(s) 
							System.out.println("\n\n...Deregister Flight Attendant(s) or Passenger(s)...\n.........................");
							
							System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: "); 
							Code=input.nextInt();
							break;
						}
						
						case 106: { // code 106: enter or update passenger charges 
							System.out.println("\n\n...Enter or Update Passenger Charges...\n.........................");
							
							System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: "); 
							Code=input.nextInt();
							break;
						}
						
						case 107: { // code 107: display passenger statistics 
							System.out.println("\n\n...Display Passenger Statistics...\n.........................");
							
							System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: "); 
							Code=input.nextInt();
							break;
						}
						
						case 108: { // code 108: display flight statistics 
							System.out.println("\n\n.........................Flight Statistics...........................\n..........................................................................."); 
							test.flightStats(); 
						
							System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: "); 
							Code=input.nextInt();
							break;
						}
						
						case 109: { // code 109: display charge sheet 
							System.out.println("\n\n...Display Charge Sheet...\n.........................");
					
							System.out.print("\nKindly continue by entering a Code, from the menu above, that corresponds to your task: "); 
							Code=input.nextInt();
							break;
						}
						
						default: {} // no break so that it continues to the next case which is an exit case anyways 
						
						case 110: { // code 110: exit // ERROR HERE: FIX INFINITE LOOP WHILE MAINTAINING 110 AS VALID INPUT 
							System.out.println("Simple SFMS >>> Exiting...\n\nThank you for fostering our Simple Flights Management System  (SFMS).");
							System.exit(0);
						}
								
					}
				} 
				
		input.close();
	}

	private static char[] flightStats() {
		// TODO Auto-generated method stub
		return null;
	}

}
