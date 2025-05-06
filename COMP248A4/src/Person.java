// ----------------------------------------------------------
// Assignment 4
// Written by: Esmé Bellefeuille (40280961)
// For COMP 248 Section-W – Winter 2025
// ----------------------------------------------------------
// Class for (...) 
// ----------------------------------------------------------
import java.util.Scanner;	

public class Person { 

	// public static void main(String[] args) {}
	
	// declare variables 
	private String entityID; 
	private String firstName;
	private String lastName;
	private double chargePercent; 
	Scanner input=new Scanner(System.in); 
	static int attdCount=0;
	
	// declare constructors 
	public Person() {
		entityID=" ";
		firstName=" ";
		lastName=" ";
	}
	public Person(String inEntityID, String inFirstName, String inLastName) {
		/* inEntityID=input.next();
		inFirstName=input.next();
		inLastName=input.next(); */ 
		entityID=inEntityID;
		firstName=inFirstName;
		lastName=inLastName;
	} 
	
	static Person duplPerson (Person Person) { // deep copy of Person class  
		return Person;
	} 
	
	// declare getters (accessors) 
	public String getEntityID() {
		if (entityID!=null)
			return entityID; // return a string 
		else
			return null;
	}
	public String getFirstName() {
		if (firstName!=null)
			return firstName; // return a string 
		else
			return null; 
	}
	public String getLastName() {
		if (lastName!=null) 
			return lastName; // return a string 
		else
			return null;
	}
	public double getChargePercent() {
		if (chargePercent!=0.00)
			return chargePercent; // return a double 
		else 
			return 0.00;
	}
	
	// declare setters (mutators) 
	public void setEntityID(String inEntityID) {
		entityID=inEntityID;// 
	}
	public void setFirstName(String inFirstName) {
		firstName=inFirstName; // 
	}
	public void setLastName(String inLastName) {
		lastName=inLastName; // 
	}
	public void setChargePercent(double inChargePercent) {
		chargePercent=inChargePercent; // 
	}
	
	// declare overloaded equals method 
	public boolean equals (Person anotherPerson) { // checks equality of two Person objects based solely on entityID value 
			return (entityID.equals(anotherPerson.entityID));
				} 
		
	// declare override method
	@Override
	public String toString() { // define how you want constructors to be returned 
		return entityID + firstName + lastName; // return a string 
	} 
	
	// declare custom methods (additional methods) 
	public void read102(Person aPilot) {	// read scanner input for code 102
		System.out.println("Enter the pilot's information (EmployeeID FirstName LastName) as a single-line entry: "); 
		
		aPilot.entityID=input.next(); 
		aPilot.firstName=input.next();
		aPilot.lastName=input.next();	
		
	} 
		
		public static String formatPerson(Person aPerson) {
			if (aPerson!=null)
				return upperCase(aPerson.getLastName()) + ", " + aPerson.getFirstName() + " (" + aPerson.getEntityID() + ")";
			else
				return " " + " " + " ";
		}
		
	// declare custom methods (required methods) 
	// create a 2D array which stores the different person arrays, 
	// and when i need to append one to the cumulative others, i just run up the list add their length and values in a for loop 
	
		
	public static Person[] inStrToPersonArr(String inStr, int numPeople, Person [] driverAttdsArr, Flight theFlight, int mode) {
		
		if (mode==103) {
			
			String takeApart=inStr;
			String takeApartStorage=inStr;	
			String gettingSmaller=inStr;
			String gettingSmallerStorage=inStr;
			int count=numPeople; 	
			
			do {
				gettingSmaller=takeApart.substring(0,takeApart.indexOf(";")+1);
				count++;
				takeApart=takeApart.substring(takeApart.indexOf(";")+1);
			} while (gettingSmaller.indexOf(";")>0); 
				
			driverAttdsArr=new Person[count];

			// resetting my strings as the input after i extracted them for the count loop above 
			takeApart=takeApartStorage;
			gettingSmaller=gettingSmallerStorage; 
		
			for (int i=0;i<count;i++) {
				if (takeApart.indexOf(";")<1) {
					Person flightAttdObj=new Person(); 
					// ok so this is key to merging the two...String accessor=flightAttdObj.getEntityID(); 
					
					flightAttdObj.entityID=takeApart.substring(0,takeApart.indexOf(","));
					
					gettingSmaller=takeApart.substring(takeApart.indexOf(",")+1);
					
					flightAttdObj.firstName=gettingSmaller.substring(0,gettingSmaller.indexOf(",")); 
					
					gettingSmaller=gettingSmaller.substring(gettingSmaller.indexOf(",")); 
					
					flightAttdObj.lastName=gettingSmaller.substring(gettingSmaller.indexOf(",")+1);
					driverAttdsArr[i]=flightAttdObj; 
				}
			
				else {
					Person flightAttdObj=new Person(); // every iteration create a new instance of Person called flightAttdObj
				
					gettingSmaller=takeApart.substring(0,takeApart.indexOf(";")+1);

					flightAttdObj.entityID=gettingSmaller.substring(0,gettingSmaller.indexOf(","));
					
					flightAttdObj.entityID=gettingSmaller.substring(0,gettingSmaller.indexOf(",")); // id is first value until first comma 
			
					gettingSmaller=gettingSmaller.substring(gettingSmaller.indexOf(",")+1); // gS restarts after first comma
		
					flightAttdObj.firstName=gettingSmaller.substring(0,gettingSmaller.indexOf(",")); // fname is value until second comma 
			
					flightAttdObj.lastName=gettingSmaller.substring(gettingSmaller.indexOf(",")+1,gettingSmaller.length()-1);//.substring((entityID.length()+firstName.length()));
			
					driverAttdsArr[i]=flightAttdObj;
				}
				
				int m=0;
					do  { // validates against previous entries in the array, if valid->add to the array, else->error message and set element to null 
						if(m!=i) {	// ie. if this isnt the first element in the array 	
						// case 1: already exists 
							if (driverAttdsArr[i].equals(driverAttdsArr[m])) {
								System.out.println("Already Exists: " + formatPerson(driverAttdsArr[i])); 
								driverAttdsArr[i]=null;	// remove from array 
								i--;
								count--;
								break; 
							}
							else
								if (theFlight.getFlightAttend()!=null) {
									for (int a=0; a<theFlight.getFlightAttendLength(); a++) {
										if (theFlight.getFlightAttend()[a]!=null) {
											if (driverAttdsArr[i].equals(theFlight.getFlightAttend()[a])) {
												System.out.println("Already Exists: " + formatPerson(driverAttdsArr[i])); 
												driverAttdsArr[i]=null;	// remove from array 
												i--;
												count--;
												break; 
											}
											else if (a==theFlight.getFlightAttendLength()-1) {
												System.out.println("Successfully Added1: " + formatPerson(driverAttdsArr[i])); 
												break;
											}
										}
									}
								break; 
								}
						
						// case 2: successfully added 
							else { 
								if (m==i-1) {
									if (theFlight.getFlightAttend()!=null) {
										for (int a=0; a<theFlight.getFlightAttendLength(); a++) {
											if (theFlight.getFlightAttend()[a]!=null) {
												if (driverAttdsArr[i].equals(theFlight.getFlightAttend()[a])) {
													System.out.println("Already Exists: " + formatPerson(driverAttdsArr[i])); 
													driverAttdsArr[i]=null;	// remove from array 
													i--;
													count--;
													break; 
												}
											}
										} 
									}
									else
										System.out.println("Successfully Added2: " + formatPerson(driverAttdsArr[i])); 
								}
							}
						}
						
					// case 3: the element is the same, but only because the index is the same as well (comparing the exact same element) 
						else if (driverAttdsArr[i].equals(driverAttdsArr[m])) {//here to2
							if (theFlight.getFlightAttend()!=null) { // if this is a second array, its possible that its first element exists in the previous array
								for (int a=0; a<theFlight.getFlightAttendLength(); a++) {
									if (theFlight.getFlightAttend()[a]!=null) {
										if (driverAttdsArr[i].equals(theFlight.getFlightAttend()[a])) {
											System.out.println("Already Exists: " + formatPerson(driverAttdsArr[i])); 

											driverAttdsArr[i]=null;	// remove from array 
											i--;
											count--;
											break; 
										}
										else if (a==theFlight.getFlightAttendLength()-1) {
											System.out.println("Successfully Added5: " + formatPerson(driverAttdsArr[i]));
											break; 
										}
									}
									else {
										System.out.println("Successfully Added3: " + formatPerson(driverAttdsArr[i]));
											break; 
											}
								}
							}
							else { // otherwise, its mostly likely that this is just the first element in the array (the first time the array is filled) and so there's nothing i can compare it with 
								System.out.println("Successfully Added4: " + formatPerson(driverAttdsArr[i]));
							}
						}
		
				m++;  
					} while (m<i); 
			
			takeApart=takeApart.substring(takeApart.indexOf(";")+1); // restarts the takeApart string after "removing" this iteration's attendant's value 
				}	
			} 	
		else if (mode==104) {
			String information=inStr;
			String informationStorage=inStr;	
			String gettingSmaller=inStr;
			String gettingSmallerStorage=inStr;
			int count=numPeople; 	
			
			do {
				gettingSmaller=information.substring(0,information.indexOf(";")+1);
				count++;
				information=information.substring(information.indexOf(";")+1);
			} while (gettingSmaller.indexOf(";")>0); 
				
			driverAttdsArr=new Person[count];

			// resetting my strings as the input after i extracted them for the count loop above 
			information=informationStorage;
			gettingSmaller=gettingSmallerStorage; 
		
			for (int i=0;i<count;i++) { // this is the outer most loop of the process: extract person, extract info, add to array, and check for duplicates 
				if (information.indexOf(";")<1) { // this catches the last person which does not end with a semicolon
					Person passengerObj=new Person(); 
					
					passengerObj.entityID=information.substring(0,information.indexOf(","));
					
					gettingSmaller=information.substring(information.indexOf(",")+1);
					
					passengerObj.firstName=gettingSmaller.substring(0,gettingSmaller.indexOf(",")); 
					
					gettingSmaller=gettingSmaller.substring(gettingSmaller.indexOf(",")); 
					
					passengerObj.lastName=gettingSmaller.substring(gettingSmaller.indexOf(",")+1);
					
					driverAttdsArr[i]=passengerObj; 
				}
			
				else {  
					Person passengerObj=new Person(); // every iteration create a new instance of Person called flightAttdObj
				
					gettingSmaller=information.substring(0,information.indexOf(";")+1);

					passengerObj.entityID=gettingSmaller.substring(0,gettingSmaller.indexOf(","));
					
					passengerObj.entityID=gettingSmaller.substring(0,gettingSmaller.indexOf(",")); // id is first value until first comma 
			
					gettingSmaller=gettingSmaller.substring(gettingSmaller.indexOf(",")+1); // gS restarts after first comma
		
					passengerObj.firstName=gettingSmaller.substring(0,gettingSmaller.indexOf(",")); // fname is value until second comma 
			
					passengerObj.lastName=gettingSmaller.substring(gettingSmaller.indexOf(",")+1,gettingSmaller.length()-1);//.substring((entityID.length()+firstName.length()));
			
					driverAttdsArr[i]=passengerObj;
				}
				
				int m=0; 
					do  { 

						if(m!=i) { // ie. if this isnt the first element in the array 	
							for (m=0; m<i; m++) { // check element for duplicates in its own array up until current element 
								
								if (driverAttdsArr[i]!=null&&driverAttdsArr[m]!=null) { 
									//System.out.println("Here we are..."); 
									System.out.println("M: " + m + " i: " + i); 
									
									//1.1
									if (driverAttdsArr [i].equals(driverAttdsArr [m])) {
										System.out.println("Already Exists1: " + formatPerson(driverAttdsArr[i])); 
										driverAttdsArr[i]=null;	// remove from array 
										
										
										//if (i!=driverAttdsArr.length-1)
										//i--;
										count--;
										break; 
									}
									
									//1.2
									// this one catches the last element of the array--if there's a previous array->one more round of comparison, else->success
									else if (m==driverAttdsArr.length-1) { // ok so this one element isn't equal? don't stop comparing to your own array until you're done 
										System.out.println("Oh. we actually made it in. Time to check length."); // there IS a previous array?
										
										// got to last element of own array but there IS a previous array? 
										if (theFlight.getPassenger()!=null) { 
											// System.out.println("btw..." + theFlight.getFlightAttend()[0] + " " + theFlight.getFlightAttend()[1]); 
											for (m=0; m<theFlight.getPassengerLength(); m++) {
												if (driverAttdsArr[i]!=null&&theFlight.getPassenger()[m]!=null) {
													if (driverAttdsArr [i].equals(theFlight.getPassenger()[m])) { // then compare the current and previous arrays  
														System.out.println("Already Exists2: " + formatPerson(driverAttdsArr[i])); 
														driverAttdsArr[i]=null;	// remove from array 
														if (m!=theFlight.getPassengerLength()-1)
														i--;
														count--;
														break; 
													}
												}		
												else if (m==theFlight.getPassengerLength()-1) { // there was a previous array, but made it to the end of the comparison without finding a duplicate! success! 
													System.out.println("Successfully Added1: " + formatPerson(driverAttdsArr[i])); 
													break;
												}
											}	
										} 
										
										// got to last element of own array & there is NOT a previous array? 
										else { // NO previous array & this element didnt break from the comparison loop to its own array? success! 
											System.out.println("Successfully Added2: " + formatPerson(driverAttdsArr[i])); 
											break;
										}
									//break; // makes sure that the second array's elements don't cause an infinite loop 
									
									}		
									
									//else if (m==driverAttdsArr.length-1) { // there was a previous array, but made it to the end of the comparison without finding a duplicate! success! 
									
									
									/*else if (m==i-1) {	
										if (theFlight.getPassenger()!=null) { 
											// System.out.println("btw..." + theFlight.getFlightAttend()[0] + " " + theFlight.getFlightAttend()[1]); 
											for (m=0; m<theFlight.getPassengerLength(); m++) {
												if (driverAttdsArr[i]!=null&&theFlight.getPassenger()[m]!=null) {
													if (driverAttdsArr [i].equals(theFlight.getPassenger()[m])) { // then compare the current and previous arrays  
														System.out.println("Already Exists3: " + formatPerson(driverAttdsArr[i])); 
														driverAttdsArr[i]=null;	// remove from array 
														//if (m!=theFlight.getPassengerLength()-1)
														//	i--;
														count--;
														break; 
													}
												}		
												//else if (m==theFlight.getPassengerLength()-1) { // there was a previous array, but made it to the end of the comparison without finding a duplicate! success! 
												//	System.out.println("Successfully Added3: " + formatPerson(driverAttdsArr[i])); 
												//	break;
												//}
											}	
										} 
										else 
											System.out.println("Successfully Added4: " + formatPerson(driverAttdsArr[i])); 
									} */  
									
									
									
									//	break;
									//}
									//System.out.println("Yeah..." + i + m); 
									//else if (m==theFlight.getPassengerLength()-1) { // there was a previous array, but made it to the end of the comparison without finding a duplicate! success! 
										//System.out.println("Successfully Added1: " + formatPerson(driverAttdsArr[i])); 
										//break;
									//}
									
								} // element you're comparing to is null? keep going, next iteration->next element 
							} //break; // makes sure that the ELSE right above this doesn't loop forever 
						} 
					
					// case 2: what if indices are the same? (catches first element of an array) 
					else { // a) first element of first array -> success! or b) first element of a following array -> possibly a duplicate  
						if (theFlight.getPassenger()!=null) { // is there a previous array? 
							// System.out.println("btw..." + theFlight.getFlightAttend()[0] + " " + theFlight.getFlightAttend()[1]); 
								for (m=0; m<theFlight.getPassengerLength(); m++) {
									if (driverAttdsArr[i]!=null&&theFlight.getPassenger()[m]!=null) {
										if (driverAttdsArr [i].equals(theFlight.getPassenger()[m])) { // then compare the current and previous arrays  
											System.out.println("Already Exists4: " + formatPerson(driverAttdsArr[i])); 
											driverAttdsArr[i]=null;	// remove from array 
											//if (m!=theFlight.getPassengerLength()-1)
											//	i--;
											count--;
											break; 
										}
										
										// have you made it to the last element in the array? if so, you're good to go! 
										else if (m==theFlight.getPassengerLength()-1) { // there was a previous array, but made it to the end of the comparison without finding a duplicate! success! 
											System.out.println("Successfully Added5: " + formatPerson(driverAttdsArr[i])); 
											break;
										}
										//else 
										//	System.out.println("Yeah, that's what I thought"); 
									}
									if (m==theFlight.getPassengerLength()-1) { // there was a previous array, but made it to the end of the comparison without finding a duplicate! success! 
										System.out.println("Successfully Added6: " + formatPerson(driverAttdsArr[i])); 
										break;
									}
								} 
							}
						
						else { // NO previous array & this element didnt break from the comparison loop to its own array? success! 
							System.out.println("Successfully Added7: " + formatPerson(driverAttdsArr[i])); 
							break;
						}
					}
						
				} while (m<i); 
					
			information=information.substring(information.indexOf(";")+1); // restarts the takeApart string after "removing" this iteration's attendant's value 	
			} //closes most outer loop of element comparison loops 
		
		} //closes mode
		
		return driverAttdsArr;
	}//closes method
			
					
		
	
		
	
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
							/*else { // first element of an array 
								if (m==i-1) {
									if (theFlight.getPassenger()!=null) {
										for (int a=0; a<theFlight.getPassengerLength(); a++) {
											if (theFlight.getPassenger()[a]!=null) {
												if (driverAttdsArr[i].equals(theFlight.getPassenger()[a])) {
													System.out.println("Already Exists: " + formatPerson(driverAttdsArr[i])); 
													driverAttdsArr[i]=null;	// remove from array 
													i--;
													count--;
													break; 
												}
											}
										} 
									}
									else
										System.out.println("Successfully Added2: " + formatPerson(driverAttdsArr[i])); 
								}
							}
						}
						
					// case 3: the element is the same, but only because the index is the same as well (comparing the exact same element) 
						else if (driverAttdsArr[i].equals(driverAttdsArr[m])) {//here to2
							if (theFlight.getPassenger()!=null) { // if this is a second array, its possible that its first element exists in the previous array
								for (int a=0; a<theFlight.getPassengerLength(); a++) {
									if (theFlight.getPassenger()[a]!=null) {
										if (driverAttdsArr[i].equals(theFlight.getPassenger()[a])) {
											System.out.println("Already Exists: " + formatPerson(driverAttdsArr[i])); 

											driverAttdsArr[i]=null;	// remove from array 
											i--;
											count--;
											break; 
										}
										else if (a==theFlight.getPassengerLength()-1) {
											System.out.println("Successfully Added5: " + formatPerson(driverAttdsArr[i]));
											break; 
										}
									}
									else {
										System.out.println("Successfully Added3: " + formatPerson(driverAttdsArr[i]));
											break; 
											}
								}
							}
							else { // otherwise, its mostly likely that this is just the first element in the array (the first time the array is filled) and so there's nothing i can compare it with 
								System.out.println("Successfully Added4: " + formatPerson(driverAttdsArr[i]));
							}
						}
		
				m++;  */
					
	
	public static String upperCamelCase(String inStr) {
		return; // return a string 
	} 
	
	public static String multipUpperCamelCase(String inStr) {
		return; // return a string 
	}
	
	public static String upperCase(String inStr) {
		return inStr.toUpperCase(); // idek if this works or where to use it yet. i think for a full sentence type shit cause otherwise theres the built-in method 
	}
	
	
	
}