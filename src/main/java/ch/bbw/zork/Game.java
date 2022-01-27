package ch.bbw.zork;import java.util.ArrayList;import java.util.HashSet;import java.util.Stack;public class Game {		private Parser parser;	private Room currentRoom;	private Room outside, lab, tavern, gblock, office, coldRoom, suspiciousRoom, alley, indoorGarden, restingRoom, secondAlley;	private Item weirdSubstance, flower, cup, shinyStone;	private ArrayList<Room> map;	private HashSet<Item> bp; // backpack	private Stack<Room> previousRooms;	public Game() {				parser = new Parser();				// Create all the rooms and link their exits together.		outside = new Room("outside G block on Peninsula campus");		lab = new Room("lab, a lecture theatre in A block");		tavern = new Room("the Seahorse Tavern (the campus pub)");		gblock = new Room("the G Block");		office = new Room("the computing admin office");		coldRoom = new Room("a cold room");		suspiciousRoom = new Room("a very suspicious room, you squint so you could see in the dark");		alley = new Room ("an alley, the only way is forward or back into the previous room");		secondAlley = new Room("another alley, the only exit is around the corner or the previous room behind you");		indoorGarden = new Room("the indoor Garden of the tavern, beautiful flowers and butterflies everywhere!");		restingRoom = new Room(" a big room with a comfy bed right in the middle.");		// initialise room exits		outside.put(null, lab, alley, tavern);		lab.put(null, null, coldRoom, outside);		coldRoom.put(lab, null, null, null);		tavern.put(null, outside, null, null);		indoorGarden.put(tavern, null, null, null);		alley.put(outside, null, gblock,null);		gblock.put(alley, office, suspiciousRoom, null);		suspiciousRoom.put(gblock, null, secondAlley, null);		secondAlley.put(suspiciousRoom, restingRoom, null, null);		restingRoom.put(null, null, null, secondAlley);		office.put(null, null, null, gblock);		currentRoom = outside; // start game outside		previousRooms = new Stack<>();				map = new ArrayList<>();		map.add(outside);		map.add(lab);		map.add(tavern);		map.add(gblock);		map.add(office);		map.add(coldRoom);		map.add(suspiciousRoom);		map.add(alley);		map.add(secondAlley);		map.add(indoorGarden);		map.add(restingRoom);				weirdSubstance = new Item();		weirdSubstance.setName("Weird Substance");		weirdSubstance.setWeight(2.5f);				flower = new Item();		flower.setName("Flower");		flower.setWeight(0.93f);		cup = new Item();		cup.setName("Cup");		cup.setWeight(500f);		shinyStone = new Item();		shinyStone.setName("Shiny Stone");		shinyStone.setWeight(6035f);  // TODO: Weight in item as float				lab.add(weirdSubstance);		outside.add(flower);		tavern.add(cup);		indoorGarden.add(flower);		alley.add(shinyStone);				bp = new HashSet<>();	}	public void play() {		printWelcome();		// Enter the main command loop.  Here we repeatedly read commands and		// execute them until the game is over.		boolean finished = false;		while (!finished) {			Command command = parser.get(); // reads a command			finished = processCommand(command);		}		System.out.println("Thank you for playing.  Good bye.");	}	private void printWelcome() {		System.out.println();		System.out.println("Welcome to Zork!");		System.out.println("Zork is a simple adventure game.");		System.out.println("Type 'help' if you need help.");		System.out.println();		System.out.println(currentRoom.longDescription());	}	private boolean processCommand(Command command) {		if (command.isUnknown()) {			System.out.println("I don't know what you mean...");			return false;		}		String commandWord = command.getCommandWord();		if (commandWord.equals("help")) {			printHelp();		} else if (commandWord.equals("go")) {			goRoom(command);			// Gewonnen?			if (currentRoom == restingRoom) {				System.out.println("ahhh finally, it's been a nice journey. now take a rest, you deserved it!");				System.out.println();				System.out.println("Thank you for playing Zork!");				return true;			}		} else if (commandWord.equals("map")) {			System.out.println("Map (all rooms)");			System.out.println("---------------");			for (Room room : map) {				System.out.print("- ");				if (room == currentRoom) {					System.out.print(">>> ");				}				System.out.println(room.shortDescription());			}		} else if (commandWord.equals("get")) {			if (command.hasSecondWord()) {				Item item = currentRoom.getItem(command.getSecondWord());				bp.add(item);			}		} else if (commandWord.equals("put")) {			Item itemToPut = null;			for (Item item : bp) {				if (item.getName().equals(command.getSecondWord())) {					itemToPut = item;					break;				}			}			if (itemToPut != null) {				currentRoom.add(itemToPut);				bp.remove(itemToPut);			}		} else if (commandWord.equals("show")) {			System.out.println("Backpack content");			System.out.println("----------------");			for (Item item : bp)				System.out.println("- " + item.getName());		} else if (commandWord.equals("back")) {			if (!previousRooms.isEmpty()) {				currentRoom = previousRooms.pop();				System.out.println(currentRoom.longDescription());			} else				System.out.println("Don't know where to go :(");		} else if (commandWord.equals("quit")) {			if (command.hasSecondWord())				System.out.println("Quit what?");			else				return true; // signal that we want to quit		}		return false;	}	/* implementations of user commands:	 */	private void printHelp() {		System.out.println("You are lost. You are alone. You wander");		System.out.println("around at Monash Uni, Peninsula Campus.");		System.out.println();		System.out.println("Your command words are:");		System.out.println(parser.showCommands());	}	private void goRoom(Command command) {		// if there is no second word, we don't know where to go...		if (!command.hasSecondWord()) {			System.out.println("Go where?");		} else {						String direction = command.getSecondWord();				// Try to leave current room.			Room nextRoom = currentRoom.nextRoom(direction);				if (nextRoom == null)				System.out.println("There is no door!");			else {				previousRooms.push(currentRoom);				currentRoom = nextRoom;				System.out.println(currentRoom.longDescription());							}			if (nextRoom == coldRoom) {				System.out.println("the window is open, brrrrrrr");			}		}	}}