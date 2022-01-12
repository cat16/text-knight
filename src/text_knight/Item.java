package text_knight;

import java.util.ArrayList;
public class Item {
	
	String NAME;
	String TYPE;
	int VALUE;
	boolean CRAFTABLE;
	ArrayList<Item> CRAFTING;

	public Item(String name, String type, int value, boolean craftable, ArrayList<Item> crafting){
		NAME = name;
		TYPE = type;
		VALUE = value;
		CRAFTABLE = craftable;
		CRAFTING = crafting;
	}
}
