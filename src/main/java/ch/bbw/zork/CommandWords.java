package ch.bbw.zork;public class CommandWords {	private static final String validCommands[] =			{"go", "quit", "help", "back", "map",					"get", "put", "show"};	public CommandWords() {	}	public boolean isCommand(String aString) {		for (int i = 0; i < validCommands.length; i++) {			if (validCommands[i].equals(aString))				return true;		}		// if we get here, the string was not found in the commands		return false;	}	public String showAll() {		String outputStr = "";		for (int i = 0; i < validCommands.length; i++) {			outputStr += validCommands[i] + "  ";		}		return outputStr;	}}