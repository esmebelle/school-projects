// ----------------------------------------------------------
// Assignment 4
// Written by: Esmé Bellefeuille (40280961)
// For COMP 248 Section-W – Winter 2025
// ----------------------------------------------------------
// Class for (...)
// ----------------------------------------------------------
import java.util.Arrays;
import java.util.Scanner;

public class Flight {
	// public static void main(String[] args) {}
	
		// declare private variables up here to help define methods down below 
		private String flightName;
		private String flightCode;
		private String flightPermit;
		private Person pilot;
		private Person[] flightAttend;
		private Person[] passenger;
		Scanner input=new Scanner(System.in); 
		
		// public String inFlightName;
		
		// declare constructors   
		public Flight() {
			flightName=" ";
			flightCode=" ";
			flightPermit=" ";
			pilot=null;
			flightAttend=null;
			passenger=null; 	
		} // default constructor
		
		public Flight(String inFlightName, String inFlightCode, String inFlightPermit, Person inPilot, Person[] inFlightAttend, Person[] inPassenger) {
			flightName=inFlightName; 
			flightCode=inFlightCode;
			flightPermit=inFlightPermit;
			pilot=inPilot;
			flightAttend=inFlightAttend;
			passenger=inPassenger; // do these last two need smth special because they're arrays..? 
		}  
		
		/* public Flight (String inFlightName, String inFlightCode, String inFlightPermit) { // is this three-parameter constructor needed? 
			flightName=inFlightName; 
			flightCode=inFlightCode;
			flightPermit=inFlightPermit;
		} */ 

		// declare getters (accessors) 
		public String getFlightName() { 
			return flightName;
		}
		public String getFlightCode() {
			return flightCode;
		}	
		public String getFlightPermit() {
			return flightPermit;
		}	
		public Person getPilot() {
			if (pilot!=null)
				return pilot;
			else
				return null;
		}
		public Person[] getFlightAttend() {
			// code for creating deep copy here 
			if (flightAttend!=null)
				return flightAttend;
			else 
				return null;
		} 
		public int getFlightAttendLength() {
			return flightAttend.length; 
		}
		public Person[] getPassenger() {
			// code for creating deep copy here 
			if (passenger!=null)
				return passenger;
			else 
				return null;
		}
		public int getPassengerLength() {
			return passenger.length; 
		}
		// declare setters (mutators) 
		public void setFlightName(String inFlightName) {
			flightName=inFlightName;
			// this.flightName=inFlightName;
		}
		public void setFlightCode(String inFlightCode) {
			flightCode=inFlightCode;//=flightCode
		}
		public void setFlightPermit(String inFlightPermit) {
			flightPermit=inFlightPermit; //=flightPermit
		}
		public void setPilot(Person inPilot) {
			// pilot=inPilot; //=pilot
			pilot=inPilot; 
		}
		public void setFlightAsst(Person[] inFlightAttend) {
			flightAttend=inFlightAttend; //=FlightAsst
		}
		public void setPassenger(Person[] inPerson) {
			passenger=inPerson; //=Passenger
		} 

		// override method 
		@Override 
		public String toString() { //  
			return flightName + flightCode + flightPermit + pilot; // + countAttd + countPass;
		}
		
		// declare CUSTOM METHODS 
		public void read101(Flight newFlight) {
			System.out.println("Enter the flight information (FlightName FlightCode Permit) as a single-line entry: \n");
			newFlight.flightName=input.next();
			newFlight.flightCode=input.next();
			newFlight.flightPermit=input.next();
			newFlight.pilot=null;
			// System.out.println(getFlightName() + getFlightCode() + getFlightPermit()); // just to check 
		}

		// custom method which accepts a new array of Person[] objects and appends it to the class’ existent (private) array of Person[] objects. 
		// Also, it accepts an int value which corresponds to a menu option (from the SFMS application); and it returns a String value containing 
		// all the status messages with respect to this append task. NOTE: You may modify the parameters and return-type of this method to suit your approach. */ 
		public Person [] appendToPersonArr(Person[] chrArr, int mode) { // test.appendToPersonArr(inStrToPersonArr, 103)
			if (mode==103) { // uses new array, add this array to the existing person array (im thinking just tack onto code 103 fore every iteration) 
				// (create a new array that is the combined lengths of both arrays and then use for loops to fill in the array), 
				// then use inStr custom method to validate 
				//System.out.println("getFlightAttend(): " + getFlightAttend());
				//System.out.println("chrArr: " + chrArr); 
				Person [] attendArr=new Person [flightAttend.length+chrArr.length]; 
				int a=0;
				for (a=0; a<flightAttend.length; a++) {
					attendArr [a]=flightAttend [a];
					//System.out.println(attendArr [a]);
				}
				
				for (int b=0; b<chrArr.length; b++, a++) {
					attendArr [a]=chrArr [b]; 
					//System.out.println(attendArr [a]);
				}
				//System.out.println(Arrays.toString(attendArr));
				// again, this should reference a deep copy // the error here is that they are different sizes!!! 
				return attendArr; 
			}
			if (mode==104) {
				Person [] passengerArr=new Person [passenger.length+chrArr.length]; 
				int a=0; 
				//System.out.println("passengerArr leng: " + passengerArr.length + "passenger leng: " + passenger.length + "chrArr leng: " + chrArr.length) ;
				for (a=0; a<passenger.length; a++) {
					passengerArr [a]=passenger [a];
				//	System.out.println(passengerArr [a]);
				}
				
				for (int b=0; b<chrArr.length; b++, a++) {
					passengerArr [a]=chrArr [b]; 
				//	System.out.println(passengerArr [a]);
				}// again, this should reference a deep copy // the error here is that they are different sizes!!! 
				return passengerArr; 
			}
			else
				return chrArr; //dont
		}
		
		// The deleteFromPersonArr(String, int) is a custom method which accepts a String value of several entityID 
		// separated via a semicolon(;), and for each entityID it deletes the corresponding Person (identified by the entityID) 
		// from the class’ existent (private) array of Person[] objects. Also, it accepts an int value which corresponds to a
		// menu option (from the Simple SFMS application); and it returns a String value containing 
		// all the status messages with respect to this deletion task 
		public String deleteFromPersonArr(String inStr, int mode) {
			if (mode==105103) { // code 105 then typed in 103 
				return "Flight Attendant Deregistered";
			}
			if (mode==105104) { // code 105 then typed in 104
				return "Passenger Deregistered"; 
			}
			else
				return " ";
		}
		
		public String updatePersonCharge(String inStr, int mode) {
			return; // return a string 
		}
		
		public void returnAttds108 (Person [] personArr) { // make sure to change this and other methods that return arrays reference a deep copy of the original array and not the actual array 
			if (personArr!=null) {
				for (int n=0, m=0; n<personArr.length; n++, m++) {
					if (personArr[n]!=null) {
						System.out.print(". Flight Attendant\t" + (m+1) + ": "); 
							addWhiteSpace(Person.formatPerson(getFlightAttend()[n]),2);
					}
					else {
						m--;  // ignore null values when numbering the flight attendants in the display 
					}
				}
			}
			else {
				return; 
				} 
		}
		
		public int countPass (Person [] personArr) {
			int count=0; 
			if (personArr!=null) {
				for (int n=0; n<personArr.length; n++) {
					if (personArr[n]!=null) 
						count++;	
				}
				return count; 
			}
			else 
				return 0;
		}
		
		public void addWhiteSpace (String formattedPerson, int mode) {
			int spaceWeHave=formattedPerson.length();
			int spaceWeNeed=0;
			
			if (mode==1) //if pilot (because six extra characters for "capt. ") 
				spaceWeNeed=41-spaceWeHave;
			
			if (mode==2) //if flight attendant or flight information 
				spaceWeNeed=47-spaceWeHave; 
			
			System.out.print(formattedPerson);
			for (int i=0; i<spaceWeNeed; i++) {
				System.out.print(" "); 
			} 
			System.out.println("."); 
		}
		
			public void addWhiteSpaceToNum (int count) {
				int spaceWeHave=0;
				int spaceWeNeed=0;
				if (count<10)
					spaceWeHave=1;
				else if (count>=10&&count<100)
					spaceWeHave=2;
				else if (count>=100&&count<1000)
					spaceWeHave=3;
				else
					System.out.println("Too many passengers!"); 
				
				System.out.println("."); 
				if (spaceWeHave>0) { // if integer 
					
					spaceWeNeed=47-spaceWeHave;
					System.out.print(count);
					
					for (int i=0; i<spaceWeNeed; i++) {
						System.out.print(" "); 
					} 
					System.out.println("."); 
				
				}
		}
		
		public void flightStats() { // code 108 
			System.out.print(". Flight Name\t\t " + ": ");  
				addWhiteSpace(getFlightName(),2);
			System.out.print(". Flight Code\t\t " + ": "); 
				addWhiteSpace(getFlightCode(),2);
			System.out.print(". Permit\t\t " + ": "); 
				addWhiteSpace(getFlightPermit(),2); 
			System.out.print(". pilot Name\t\t " + ": " + "Capt. "); 
				addWhiteSpace(Person.formatPerson(getPilot()),1); 
			returnAttds108(getFlightAttend()); 
			System.out.println(". Registered passengers: " + countPass(getPassenger())); 
				addWhiteSpaceToNum(countPass(getPassenger())); 
			System.out.println("..........................................................................."); 
			// return action, prob print 
		} 
		
		public void chargeSheet() {
			// return action, prob print 
		}
		
}
