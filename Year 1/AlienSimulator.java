import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
/* An Alien Pet Simulator Game
*By Melojan Rangithathas
*/

class MiniProject{
	
	public static void main(String args []) throws IOException {

		Scanner input = new Scanner(System.in);

		//Showing Introduction Message at the very Start!
		printInstruction();

		// Declaring variables
		String name = "";
		int hunger = 0;
		int leaderhappy = 0;
		String [] aliens = null;
		int [] hungers = null;
		int [] happy = null;

		// loading saves
		System.out.println("Do you want to load your file?");
		String loadFileQuestion = input.nextLine();
		
		if(loadFileQuestion.equalsIgnoreCase("yes")) { // load existing save
			// Read all of file
			String fileContents = readFile("save.txt");

			// checking if file is valid
			if (fileContents.length() == 0 || !fileContents.contains(",")) {
				System.out.println("Save file with name 'save.txt' is corrupted.");
				return;
			}

			// Split file into lines
			String[] lines = fileContents.split("\n");
			int alienCount = lines.length - 1;

			hungers = new int[alienCount];
			happy = new int [alienCount];
			aliens = new String [alienCount];

			// handle each line
			for (int i = 0; i < lines.length; i++) {
				String[] entry = lines[i].split(",");
				
				// two cases: leader or not
				if (i == 0) { // leader since first entry
					name = entry[0];
					hunger = Integer.parseInt(entry[1]);
					leaderhappy = Integer.parseInt(entry[2]);
				} else { // squad member
					int idx = i - 1; // adjust index to correspond the squad one

					aliens[idx] = entry[0];
					hungers[idx] = Integer.parseInt(entry[1]);
					happy[idx] = Integer.parseInt(entry[2]);
				}
			}
		} else { // no load, so we ask the user to create new aliens
			//Creating an alien pet leader
			name = getPetName();
			hunger = getRandomStat();
			CheckHunger(name, hunger);

			leaderhappy = getRandomStat();
			CheckHappy(name,leaderhappy);
		
			int AlienAmount = AlienPlanet(name); //Getting how many aliens the user wants to make
			aliens = NamingAliens(AlienAmount); //Getting an array of each aliens name
			hungers = new int[AlienAmount]; //Making an array to hold each other aliens hunger level
			happy = new int[AlienAmount]; 
		
			//Generating an initial hunger level for each alien and showing it
			Generatehungerlevels(hungers,aliens);
			Generatehappinesslevels(happy,aliens);
		}

		// simulation logic
		while(true) {
			System.out.println("A new Round begins, The possible actions are save,stop,feed and play!");

			sortAliensByName(hungers, aliens, happy);

			// print current hunger levels
			System.out.println("List of aliens:");
			System.out.println(name + " (leader) hunger level is " +hunger+ " and his happiness is " + leaderhappy);
			printAliens(aliens,hungers,happy);

			// get user action
			String answer = input.nextLine();
			answer = answer.toLowerCase();
  
			// perform user action
			if(answer.equals("stop")){
				break;
			} else if (answer.equals("feed")){ // feed action
				int response = feed(input, aliens, hungers, name, hunger);

				// if response=0: nothing has to be done, its done in the method
				if (response == -2) { // if response=-2: continue
					continue;
				} else if (response > 0) { // 	if response in range [1,10]: append leader hunger
					hunger = response;
				}
			} else if (answer.equals("play")) {	
				int response = play(input, aliens, happy, name, leaderhappy);

				// if response=0: nothing has to be done, its done in the method
				if (response == -2) { // if response=-2: continue
					continue;
				} else if (response > 0) { // 	if response in range [1,10]: append leader hunger
					leaderhappy = response;
				}
				
			}else if(answer.equals("save")) {
				saveFile(name,hunger,leaderhappy,aliens,hungers,happy,"save.txt");
				continue;
			} else {
				System.out.println("Invalid command");
				continue;
			}

			// increase hunger
			// begin with leader
			if (hunger < 8) {
				hunger = hunger + 2;
				CheckHunger(name, hunger);
			}
			// this check leader`s happiness
			if (leaderhappy < 8) {
				leaderhappy = leaderhappy + 2;
				CheckHappy(name, leaderhappy);
			}

			// now for squad
			increaseAliensHunger(aliens, hungers);
			increaseAliensHappy(aliens, happy);

			// distinguish rounds
			System.out.println("");
		}
		System.exit(0);
	}

	
	public static void saveFile(String leaderName, int leaderHunger, int leaderHappy, String[] aliens, int[] hungers, int[] happy, String fileName) throws IOException {

		PrintWriter pw = new PrintWriter(new File(fileName));

		// leader
		pw.print(leaderName + "," + leaderHunger + "," + leaderHappy);
		pw.println();
		
		// squad
		for (int i = 0; i < aliens.length; i++) {
			pw.print(aliens[i] + "," + hungers[i] + "," + happy[i]);
			pw.println();
		}
		pw.flush();
		pw.close();
	}

	public static String readFile(String fileName) throws IOException {

		String content = "";

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();

		while (line != null) {
			content = content + line + "\n";
			line = br.readLine();
		}
		content = content.trim(); // removing last '\n'
		return content;
	}

	public static int feed(Scanner input, String[] aliens, int[] hungers, String leaderName, int leaderHunger) {

		System.out.println("What Alien do you want to feed");
		String keyName = input.nextLine();
		int id = GetAlienNumber(aliens, leaderName, keyName);

		if (id == -1) { // leader
			if (leaderHunger > 5) {
				leaderHunger = leaderHunger - 5;
			} else {
				leaderHunger = 1;
			}
			System.out.println("You fed the Leader");
			return leaderHunger; // hunger is from 1-10
		} else if (id >= 0) { // squad member
			if (hungers[id] > 5) {
				hungers[id] = hungers[id] - 5;
			} else {
				hungers[id] = 1;
			}
			System.out.println("You fed one of the squad members");
			return 0; // FOUND SQUAD MEMBER!
		} else { // not found, i.e. id=-2
			System.out.println("Alien was not found");
			return -2; // CONTINUE!
		}
	}

	public static int play(Scanner input, String[] aliens, int[] happy, String leaderName, int leaderhappy) {
		
		System.out.println("What Alien do you want to play with?");
		String keyName = input.nextLine();
		int id = GetAlienNumber(aliens, leaderName, keyName);

		if (id == -1) { // leader
			if (leaderhappy > 5) {
				leaderhappy = leaderhappy - 5;
			} else {
				leaderhappy = 1;
			}
			System.out.println("You played with the Leader");
			return leaderhappy; // happy is from 1-10
		} else if (id >= 0) { // squad member
			if (happy[id] > 5) {
				happy[id] = happy[id] - 5;
			} else {
				happy[id] = 1;
			}
			System.out.println("You played one of the squad members");
			return 0; // FOUND SQUAD MEMBER!
		} else { // not found, i.e. id=-2
			System.out.println("Alien was not found");
			return -2; // CONTINUE!
		}
	}
		
	public static void increaseAliensHunger(String[] aliens, int[] hungers) {
		for(int i = 0; i < aliens.length ; i++){
			if (hungers[i] < 8) {
				hungers[i] = hungers[i] + 2;
				CheckHunger(aliens[i], hungers[i]);
			}
		}
	}

	public static void increaseAliensHappy(String[] aliens, int[] happy) {
		for(int i = 0; i < aliens.length ; i++){
			if (happy[i] < 8) {
				happy[i] = happy[i] + 2;
				CheckHappy(aliens[i], happy[i]);
			}
		}
	}	

	public static void printInstruction() {  			// Print Instructions

		System.out.println("AlienPet Version 1.0 - Make an Alien Planet and Control Aliens");
	}

	public static void Generatehungerlevels(int[] hungers, String [] aliens) { 	// This method generates Hunger level		
		for( int i = 0; i<hungers.length ; i++){
			String CurrentAlienName = aliens[i];
			hungers[i] = getRandomStat();
			CheckHunger(CurrentAlienName,hungers[i]);		
		}   
	}
	
	public static void Generatehappinesslevels(int [] happy,String [] aliens) {	// This method generates happiness level
	
		for( int i = 0; i<happy.length ; i++){
			String CurrentAlienName = aliens[i];
			happy[i] = getRandomStat();
			CheckHappy(CurrentAlienName,happy[i]);
		}   	
	}

	public static int GetAlienNumber(String[] name, String Leadername , String InputName){ 	 		
		for(int i = 0 ; i < name.length ; i++){
			if(name[i].equals(InputName)){
				return i;
			}
		}

		if(Leadername.equals(InputName)){
			return -1;
		}
		return -2;
	}

	public static String getPetName(){
		Scanner input = new Scanner(System.in);
		System.out.println("What is the name of your Alien Leader Pet?"); // This asks you to name your alien pet.
		String name = input.nextLine();
		System.out.println("The name "+name+" sounds GROSS!");
		return name;
	}
	
	public static int getRandomStat(){
		Random rn = new Random();
		int answer = rn.nextInt(10)+1;
		return answer;
		//On a Scale 1-10 , 10 being `Very Hungry` and 1 being `Not Hungry`.
	}
	
	public static int CheckHappy(String name, int happy){
		System.out.println("On a Scale of 1-10,Alien Leader "+name+"`s happiness is "+happy); // It will check the pet`s hunger level.
		
		if( happy<4){
			System.out.println("Calm"); 
		}else if(happy<6){
			System.out.println("Tetchy");
		}else if(happy<=10){
			System.out.println(name+" is looking dangerous...GET OUTA THERE NOW!!!");
		}
		return happy;
	}
		// Checks Hunger rate and prints out their mood they are in. 
	public static int CheckHunger(String name, int hunger){
		System.out.println("On a Scale of 1-10,Alien Leader "+name+"`s hunger rate is "+hunger); // It will check the pet`s hunger level.
		
		if( hunger<4){
			System.out.println("Calm"); 
		}else if(hunger<6){
			System.out.println("Tetchy");
		}else if(hunger<=10){
			System.out.println(name+" is looking dangerous...GET OUTA THERE NOW!!!");
		}
		return hunger;
	}

	public static int AlienPlanet(String name){
		
		Scanner input = new Scanner(System.in);	
		
		System.out.println("Leader "+name+" is ready to control Aliens");
		System.out.println("How many Aliens do you want to create?");	
		int amount = input.nextInt(); //We get an Int response since we used nextInt().
		
		return amount; //since I am returning an integer you had to change AlienPlanet to type int.

	}

	public static String[] NamingAliens(int amount){

		Scanner input = new Scanner(System.in);
		String [] AlienNames = new String[amount];

		for(int z = 0; z < AlienNames.length ; z++){
			//Tells the user to enter the names
			System.out.println("You are naming # "+ (z + 1) +" , what do you want to call it?");

			String name = input.nextLine();
			AlienNames[z] = name;

		}

		return AlienNames;
	}
		// This method check Alien`s Hunger level and Happy level	
	public static void printAliens(String[] aliens, int[] hungers,int [] happy) {			
		for(int i = 0; i < aliens.length ; i++){
			System.out.println(aliens[i] +" hunger level is " + hungers[i] + " and happy level is " + happy[i]);
		}
	}

	public static void sortAliensByName(int [] hungers,String [] Aliens,int [] happy) {
		int n = hungers.length;

		for(int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (hungers[j] > hungers[j+1]) {
					// swap hunger pos
					int temp = hungers[j];
					hungers[j] = hungers[j+1];
					hungers[j+1] = temp;

					// swap alien name pos
					String tempName = Aliens[j];
					Aliens[j] = Aliens[j+1];
					Aliens[j+1] = tempName;
				
					// swap happy pos
					int temp2 = happy[j];
					happy[j] = happy[j+1];
					happy[j+1] = temp2;
				}
			}
		}
	}
}
	

			
	
	
