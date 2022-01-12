package text_knight;

import java.util.ArrayList;

public class Enemy {
	
	String NAME;
	int BASE_HEALTH;
	int BASE_DAMAGE;
	ArrayList<Item> ALWAYS_DROP;
	int MIN_DROP;
	int MAX_DROP;
	ArrayList<Item> DROPS;
	int MIN_LEVEL;
	int MAX_LEVEL;
	int EXP;
	
	public Enemy(String name, int baseHealth, int baseDamage, ArrayList<Item> alwaysDrop, int minDrop, int maxDrop, ArrayList<Item> drops, int minLevel, int maxLevel, int exp){
		NAME=name;
		BASE_HEALTH=baseHealth;
		BASE_DAMAGE=baseDamage;
		ALWAYS_DROP=alwaysDrop;
		MIN_DROP=minDrop;
		MAX_DROP=maxDrop;
		DROPS=drops;
		MIN_LEVEL=minLevel;
		MAX_LEVEL=maxLevel;
		EXP=exp;
	}
}
