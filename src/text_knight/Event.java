package text_knight;

import java.util.ArrayList;

public class Event {
	String NAME;
	String TYPE;
	ArrayList<Integer> VALUES;
	ArrayList<String> WORDS;
	ArrayList<Item> ITEMS;
	public Event(String name, String type, ArrayList<Integer> values, ArrayList<String> words, ArrayList<Item> items){
		NAME=name;
		TYPE=type;
		VALUES=values;
		WORDS=words;
		ITEMS=items;
	}
}
