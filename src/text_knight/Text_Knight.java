package text_knight;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
//start of class
public class Text_Knight {
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	public static ArrayList<Item> kills = new ArrayList<Item>();
	public static Scanner a = new Scanner(System.in);
	public static int mh = 0;
	public static int strint = 0;
	
	public static void main(String[] args){
		//items
		items.add(new Item("Air", "material", 0, false, emptyI()));
		items.add(new Item("Death", "death", 0, false, emptyI()));
		items.add(new Item("Fur", "material", 0, false, emptyI()));
		items.add(new Item("Bone", "material", 0, false, emptyI()));
		items.add(new Item("Hide", "material", 0, false, emptyI()));
		items.add(new Item("Raw Venison", "raw", 0, false, emptyI()));
		items.add(new Item("Raw Meat Cube", "raw", 0, false, emptyI()));
		items.add(new Item("Raw Meat Chunk", "raw", 0, false, emptyI()));
		items.add(new Item("Cooked Meat Cube", "food", 2, true, new ArrayList<Item>(Arrays.asList(getItem("Raw Meat Cube")))));
		items.add(new Item("Cooked Meat Chunk", "food", 5, true, new ArrayList<Item>(Arrays.asList(getItem("Raw Meat Chunk")))));
		items.add(new Item("Cooked Venison", "food", 10, true, new ArrayList<Item>(Arrays.asList(getItem("Raw Venison")))));
		items.add(new Item("Hand", "attack", 1, false, emptyI()));
		items.add(new Item("Cloths", "armor", 2, false, emptyI()));
		items.add(new Item("Body", "body", 20, false, emptyI()));
		items.add(new Item("Bone Knife", "weapon", 2, true, new ArrayList<Item>(Arrays.asList(getItem("Fur"), getItem("Bone")))));
		items.add(new Item("Fur Coat", "armor", 4, true, new ArrayList<Item>(Arrays.asList(getItem("Fur"), getItem("Fur"), getItem("Fur")))));
		items.add(new Item("Heavy Fur Coat", "armor", 8, true, new ArrayList<Item>(Arrays.asList(getItem("Fur Coat"), getItem("Hide"), getItem("Fur")))));
		//enemies
		enemies.add(new Enemy("Gray Rat", 3, 1, new ArrayList<Item>(Arrays.asList(getItem("Raw Meat Cube"))), 0, 2,new ArrayList<Item>(Arrays.asList(getItem("Fur"), getItem("Air"), getItem("Air"))), 1, 4, 1));
		enemies.add(new Enemy("Black Rat", 4, 2, new ArrayList<Item>(Arrays.asList(getItem("Raw Meat Cube"))), 0, 2,new ArrayList<Item>(Arrays.asList(getItem("Fur"), getItem("Bone"), getItem("Air"))), 2, 5, 2));
		enemies.add(new Enemy("Badger", 6, 3, new ArrayList<Item>(Arrays.asList(getItem("Raw Meat Chunk"))), 1, 2,new ArrayList<Item>(Arrays.asList(getItem("Fur"), getItem("Bone"))), 4, 7, 3));
		enemies.add(new Enemy("Deer", 8, 1, new ArrayList<Item>(Arrays.asList(getItem("Hide"),getItem("Fur"), getItem("Raw Venison"))), 2, 3,new ArrayList<Item>(Arrays.asList(getItem("Fur"), getItem("Bone"))), 5, 10, 3));
		//turns enemies into collectible items
		for(int z = enemies.size(); z>0; z--){
			items.add(new Item(enemies.get(z-1).NAME, "kill", enemies.get(z-1).EXP, false, new ArrayList<Item>()));
		}
		//file scanner
		Scanner fileIn;
		//declares things
		String name = "no name!?";
		String onleveli = "1";
		String savepath, answer, answer2;
		double v;
		int random, onlevel, attack, health, use;
		int level = 0;
		int baseHealth = 0;
		int armorHealth = 0;
		int levelHealth = 0;
		int baseAttack=0;
		int weaponAttack=0;
		int levelAttack=0;
		int lives = 0;
		int strength = 0;
		int strengthPoints=0;
		int strengthNeeded=3;
		boolean mo = false;
		boolean mo2 = false;
		boolean mo0 = false;
		boolean mo3=false;
		File save;
		File currentSave = null;
		PrintWriter writer;
		//version below (change when updated)
		v = 1.0;
		//info
		wait(1);
		java.awt.Toolkit.getDefaultToolkit().beep();
		System.out.println("Text Knight! v"+v);
		wait(1);
		System.out.println("--------------------");
		System.out.println("type stuff that");
		System.out.println("the [] suggests");
		wait(2);
		System.out.println("note that you can");
		System.out.println("use numbers instead");
		System.out.println("   of words now!");
		wait(2);
		System.out.println("--------------------");
		wait(1);
		//'start game?' loop
		while(true){
			mo=false;
			mo0=false;
			System.out.println("Welcome to Text Knight! [hi(-1)|exit(0)|start(1)|info(2)]");
			answer = lowercaseInput();
			switch(answer){
			//switches options
			case "0":
			case "exit":
				System.exit(0);
			case "1":
			case "start":
				while(mo0==false){
					System.out.println("[back(0)|new game(1)|load game(2)]");
					answer=lowercaseInput();
					switch(answer){
					case "0":
					case "back":
						mo0=true;
						mo=true;
						break;
					case "2":
					case "load game":
						System.out.println("Load what save? [name you saved as]");
						answer = a.nextLine();
						save = new File("save-"+answer+".txt");
						try{
							inventory.clear();
							savepath=save.getCanonicalPath();
							fileIn = new Scanner(new File(savepath));
							fileIn.nextLine();
							level =fileIn.nextInt();
							levelHealth=fileIn.nextInt();
							baseHealth=fileIn.nextInt();
							levelAttack=fileIn.nextInt();
							baseAttack=fileIn.nextInt();
							armorHealth=fileIn.nextInt();
							weaponAttack=fileIn.nextInt();
							lives=fileIn.nextInt();
							name=fileIn.nextLine();
							name=fileIn.nextLine();
							while(!fileIn.hasNext("stop")){
								inventory.add(getItem(fileIn.nextLine()));
							}
							fileIn.nextLine();
							while(!fileIn.hasNext("stop")){
								kills.add(getItem(fileIn.nextLine()));
							}
							fileIn.nextLine();
							strength=fileIn.nextInt();
							strengthPoints=fileIn.nextInt();
							strengthNeeded=fileIn.nextInt();
							fileIn.close();
							System.out.println("Load completed as "+name+"!");
							mo0=true;
							mo=false;
							currentSave=new File(answer);
						}
						catch(Exception e){
							System.out.println(answer+" is not a saved game");
						}
						break;
					case "1":
					case "new game":
						baseAttack=getItem("Hand").VALUE;
						baseHealth=getItem("Body").VALUE;
						armorHealth=getItem("Cloths").VALUE;
						lives=1;
						inventory.add(getItem("Body"));
						inventory.add(getItem("Hand"));
						inventory.add(getItem("Cloths"));
						System.out.println("Let the adventure begin!");
						System.out.println("------------------------");
						while(mo == false){
							//name
							System.out.println("What is your name? [your name]");
							name = a.nextLine();
							System.out.println(name+"? [yes/no]");
							answer = lowercaseInput();
							switch(answer){
							//switches yes or no for 'is your name ____?'
							case "yes":
								mo = true;
								break;
							case "no":
								mo = false;
								continue;
							default:
								System.out.println("'" + answer + "' is not yes or no");
								mo = false;
								continue;
							}
						}
						//start of story
						System.out.println("<note> to continue text, press enter (try it)");
						waitEnter();
					System.out.println("Once, in a far land,");
						System.out.println("there was a boat, floating at sea.");
						waitEnter();
						System.out.println("Then, like all stories, the boat crashed");
						System.out.println("on a rock. All but one person died.");
						waitEnter();
						System.out.println("And that person was "+name+". Now "+name);
						System.out.println("must survive on a mystical island he/she");
						System.out.println("fled to after the crash.");
						waitEnter();
						mo0=true;
						mo=false;
						//end of story
						break;
					default:
						System.out.println("'"+answer+"' is not an option");
						break;
					}
				}
				while(mo == false){
					System.out.println("What would you like to do?");
					System.out.println("[quit(0)|adventure(1)|inventory(2)|status(3)|save(4)|rps]");
					answer=lowercaseInput();
					switch(answer){
					case "rps":
						System.out.println("What do you choose? (rock/paper/scissors)");
						answer=lowercaseInput();
						if(answer.equals("rock")||answer.equals("paper")||answer.equals("scissors")||answer.equals("rock!")||answer.equals("paper!")||answer.equals("scissors!")||answer.equals("rock.")||answer.equals("paper.")||answer.equals("scissors.")){
							rps(answer);
						}else if(answer.equals("gun")){
							System.out.println("Well I had a bullet proof vest on and shot you! Now get a life and stop cheating! >:(");
							wait(1);
						}else{
							System.out.println("'"+answer+"' is not a real choice! C'mon, play for real :(");
							wait(1);
						}
						break;
					case "3":
					case "status":
						mo2=false;
						while(mo2==false){
							System.out.println("What status would you like to view?");
							System.out.println("[exit(0)|player(1)|kills(2)]");
							mo2=false;
							answer=lowercaseInput();
							switch(answer){
							case "0":
							case "exit":
								mo2=true;
								break;
							case "1":
							case "player":
								System.out.println("What status?");
								System.out.println("[exit|attack|health|strength|lives]");
								answer=lowercaseInput();
								if(answer.equals("exit")){
									
								}else if(answer.equals("lives")){
									System.out.println("You have "+lives+" lives.");
									wait(1);
								}else{
									if(answer.equals("attack")){
										System.out.println("Here are your "+answer+" status:");
										wait(1);
										System.out.println("attack: "+baseAttack);
										wait(1);
										System.out.println("weapon's attack: "+weaponAttack);
										wait(1);
										System.out.println("added attack: "+levelAttack+" (from strength)");
										wait(1);
										strint = baseAttack+weaponAttack+levelAttack;
										System.out.println("total attack: "+strint);
										wait(1);
									}else if(answer.equals("health")){
										System.out.println("Here are your "+answer+" status:");
										wait(1);
										System.out.println("base health: "+baseHealth);
										wait(1);
										System.out.println("armor's health: "+armorHealth);
										wait(1);
										System.out.println("added health: "+levelHealth+" (from strength)");
										wait(1);
										strint = baseHealth+armorHealth+levelHealth;
										System.out.println("total health: "+strint);
										wait(1);
										strint = baseHealth+armorHealth+levelHealth-mh;
										System.out.println("current health: "+strint+" (total minus health lost from battle)");
										wait(1);
									}else if(answer.equals("strength")){
										System.out.println("Here are your "+answer+" status:");
										wait(1);
										System.out.println("strength level: "+strength);
										wait(1);
										System.out.println("strength points: "+strengthPoints+"/"+strengthNeeded);
										wait(1);
									}else{
										System.out.println("'"+answer+"' is not a status");
									}
								}
								break;
							case "2":
							case "kills":
								System.out.println("Here are all the enemies you have killed:");
								wait(1);
								if(kills.isEmpty()){
									System.out.println("(you have not killed anything yet!)");
									wait(1);
								}else{
									for(int e = enemies.size(); e>0; e--){
										if(Collections.frequency(kills, getItem(enemies.get(e-1).NAME))==0){
											
										}else{
											System.out.println(enemies.get(e-1).NAME+"s: "+Collections.frequency(kills, getItem(enemies.get(e-1).NAME)));
										}
									}
									waitEnter();
								}
								break;
							default:
									System.out.println("'"+answer+"' is not an option...");
									wait(1);
									break;
							}
						}
						break;
					//saves to a file
					case "4":
					case "save":
						mo2=false;
						while(mo2==false){
							System.out.println("[exit(0)|save(1)|save as(2)|delete(3)]");
							answer=lowercaseInput();
							switch(answer){
							case "1":
							case "save":
								if(currentSave!=null){
									try{
									writer = new PrintWriter("save-"+currentSave+".txt", "UTF-8");
									writer.println(answer);
									writer.println(level);
									writer.println(levelHealth);
									writer.println(baseHealth);
									writer.println(levelAttack);
									writer.println(baseAttack);
									writer.println(armorHealth);
									writer.println(weaponAttack);
									writer.println(lives);
									writer.println(name);
									for(int s = inventory.size(); s>0; s--) {
										String str=inventory.get(s-1).NAME;
										writer.println(str);
									}
									writer.println("stop");
									for(int s = kills.size(); s>0; s--) {
										String str=kills.get(s-1).NAME;
										writer.println(str);
									}
									writer.println("stop");
									writer.println(strength);
									writer.println(strengthPoints);
									writer.println(strengthNeeded);
									writer.close();
									System.out.println("Complete! Saved as "+currentSave+".");
									}catch(Exception e){
										System.out.println("Invalid file name.");
									}
								}
								else{
									System.out.println("You do not have a saved game to save to!");
								}
								break;
							case "3":
							case "delete":
								System.out.println("Delete what save? [name you saved as]");
								answer = a.nextLine();
								save = new File("save-"+answer+".txt");
								savepath=save.getAbsolutePath();
								save=new File(savepath);
								if(save.delete()){
									System.out.println("Deleted "+answer+"!");
								}
								else{
									System.out.println(answer+" is not a saved game");
								}
								break;
							case "0":
							case "exit":
								mo2=true;
								break;
							case "2":
							case "save as":
								try{
								System.out.println("Save as what? [anything]");
								answer=a.nextLine();
								currentSave = new File(answer);
								writer = new PrintWriter("save-"+answer+".txt", "UTF-8");
								writer.println(answer);
								writer.println(level);
								writer.println(levelHealth);
								writer.println(baseHealth);
								writer.println(levelAttack);
								writer.println(baseAttack);
								writer.println(armorHealth);
								writer.println(weaponAttack);
								writer.println(lives);
								writer.println(name);
								for(int s = inventory.size(); s>0; s--) {
									String str=inventory.get(s-1).NAME;
									writer.println(str);
								}
								writer.println("stop");
								for(int s = kills.size(); s>0; s--) {
									String str=kills.get(s-1).NAME;
									writer.println(str);
								}
								writer.println("stop");
								writer.println(strength);
								writer.println(strengthPoints);
								writer.println(strengthNeeded);
								writer.close();
								System.out.println("Complete! Saved as "+answer+".");
								}catch(Exception e){
									System.out.println("Invalid file name.");
								}
								break;
							default:
								System.out.println(answer+" is not an option");
								break;
							}
						}
						break;
						//switches for 'what would you like to do?'
					case "2":
					case "inventory":
						boolean firestill = false;
						//do stuff with items
						System.out.println("------------------");
						while(mo2==false){
							System.out.println("What would you like to do?");
							System.out.println("[exit(0)|items(1)|craft(2)|equip(3)|eat(4)]");
							answer=lowercaseInput();
							switch(answer){
							case "4":
							case "eat":
								while(mo3==false){
									System.out.println("What would you like to eat? (or exit)");
									if(getItemsWithType(inventory, "food").size()==0){
										System.out.println("Food: (you have no food!)");
									}else{
										System.out.println("Food: "+printItemNames(getItemsWithType(inventory, "food")));
									}
									answer=makeProper(lowercaseInput());
									if(items.contains(getItem(answer))||answer.equals("Exit")){
										if(inventory.contains(getItem(answer))||answer.equals("Exit")){
											if(getItemsWithType(inventory, "food").contains(getItem(answer))||answer.equals("Exit")){
												if(answer.equals("Exit")){
													mo3=true;
												}else{
													mh=mh-getItem(answer).VALUE;
													if(mh<0){
														mh=0;
													}
													inventory.remove(getItem(answer));
													System.out.println("You ate "+answer+" and gained "+getItem(answer).VALUE+" health!");
													System.out.println("(you now have "+mh+" health)");
												}
											}
										}else{
											System.out.println("You do not have any "+answer+"(s)");
											wait(1);
										}
									}else{
										System.out.println("'"+answer+"'(s) aren't in the game...");
										wait(1);
									}
								}
								break;
							case "1":
							case "items":
								System.out.println("You currently have:");
								wait(1);
								System.out.println("Materials: "+printItemNames(getItemsWithType(inventory, "material")));
								wait(1);
								System.out.println("Raw Food: "+printItemNames(getItemsWithType(inventory, "raw")));
								wait(1);
								System.out.println("Weapons: "+printItemNames(getItemsWithType(inventory, "weapons")));
								wait(1);
								System.out.println("Armor: "+printItemNames(getItemsWithType(inventory, "armor")));
								wait(1);
								System.out.println("Food: "+printItemNames(getItemsWithType(inventory, "food")));
								wait(1);
								break;
							case "0":
							case "exit":
								//get out of inventory
								firestill=false;
								mo2=true;
								break;
							case "3":
							case "equip":
								//equip things
								System.out.println("What would you like to equip? (or exit)");
								System.out.println("Weapons: "+printItemNames(getItemsWithType(inventory, "weapon")));
								System.out.println("Armor: "+printItemNames(getItemsWithType(inventory, "armor")));
								answer=makeProper(a.nextLine());
								if(answer.equals("exit")||inventory.contains(getItem(answer))){
									int ce = 0;
									if(getItem(answer).TYPE=="weapon"){
										weaponAttack=getItem(answer).VALUE;
									}else if(getItem(answer).TYPE=="armor"){
										armorHealth=getItem(answer).VALUE;
									}else if(getItem(answer).TYPE=="attack"){
										baseAttack=getItem(answer).VALUE;
									}else if(getItem(answer).TYPE=="body"){
										baseHealth=getItem(answer).VALUE;
									}else{
										System.out.println("You cannot equip a "+answer+".");
										wait(1);
										ce = 1;
									}
									if(!answer.equals("exit")&&ce==0){
										System.out.println(answer+" equipped!");
										wait(1);
									}
								}
								else if(items.contains(getItem(answer))){
									System.out.println("You do not have any "+makeProper(answer)+"(s)");
									wait(1);
								}else{
									System.out.println("That doesn't even exist... (or you put random capitals)");
									wait(1);
								}
								break;
							case "2":
							case "craft":
								//craft things
								ArrayList<Item> craftables=new ArrayList<Item>();
								for(int i=items.size(); i>0; i--){
									if(items.get(i-1).CRAFTABLE){
										craftables.add(items.get(i-1));
									}
								}
								System.out.println("What would you like to craft? (or exit)");
								System.out.println("Learned Weapons: "+printItemNames(getItemsWithType(craftables, "weapon")));
								System.out.println("Learned Armor: "+printItemNames(getItemsWithType(craftables, "armor")));
								System.out.println("Learned Food: "+printItemNames(getItemsWithType(craftables, "food")));
								answer=makeProper(lowercaseInput());
								if(printItemNames(craftables).contains(answer)||answer.equals("exit")){
									if(getItem(answer).equals("food")){
										System.out.println("Cook the "+answer+"?");
									}else{
										System.out.println("Craft a "+answer+" with "+printItemNames(getItem(answer).CRAFTING)+"?");
									}
										answer2=lowercaseInput();
									if(answer2.equalsIgnoreCase("yes")){
										int cc=1;
										ArrayList<Item> invtest = inventory;
										for(int c = getItem(answer).CRAFTING.size(); c>0; c--){
											if(!invtest.remove(getItem(answer).CRAFTING.get(c-1))){
												cc=0;
											}
										}
										if(cc==1){
											if(answer!="exit"){
												inventory=invtest;
												inventory.add(getItem(answer));
												if(getItem(answer).TYPE.equals("food")){
													if(firestill){
														System.out.println("You cooked the "+printItemNames(getItem(answer).CRAFTING)+" on the fire");
													}else{
														System.out.println("You lit a fire and cooked the "+printItemNames(getItem(answer).CRAFTING)+" on it.");
														firestill=true;
													}
												}else{
													System.out.println(answer+" crafted!");
												}
												wait(1);
											}else{
												firestill=false;
											}
										}else{
											if(getItem(answer).TYPE.equals("food")){
												System.out.println("You do not have the right meat to cook.");
											}else{
												System.out.println("You do not have the materials to craft it.");
												wait(1);
											}
										}
									}
								}else{
									System.out.println("You cannot craft a "+answer+".");
									wait(1);
								}
								break;
							default:
								System.out.println("'"+answer+"' is not an option");
								break;
							}
						}
						mo2=false;
						break;
					case "0":
					case "quit":
						System.out.println("Did you save? (or don't want to) [yes|no]");
						answer=lowercaseInput();
						switch(answer){
						case "yes":
							System.out.println("-----------");
							//restarts the game
							level=1;
							mo=true;
							inventory.clear();
							break;
						case "no":
							System.out.println("Now go save!");
							break;
						default:
							System.out.println(answer+" is not yes or no");
							break;
						}
						break;
					case "1":
					case "adventure":
						//lets you play levels
						while(mo2==false){
							strint=level+1;
							if(level!=0){
								System.out.println("Go to which area? [You are between areas "+level+" and "+strint+"]");
							}else{
								System.out.println("Go to which area? [You can only travel through level 1 right now]");
							}
							onleveli = a.nextLine();
							if(isInteger(onleveli)){
								onlevel = turnToInteger(onleveli);
								if(onlevel<=0){
									System.out.println("There isn't a level 0! (or below)");
								}else{
									if(onlevel==level||onlevel==level+1){
										System.out.println("Go through area "+onlevel+"? [yes/no]");
										answer = lowercaseInput();
										switch(answer){
										case "yes":
											mo2 = true;
											//starts the level
											attack=baseAttack+weaponAttack+levelAttack;
											health=baseHealth+armorHealth+levelHealth-mh;
											System.out.println("Area "+onlevel+" started!");
											System.out.println("--------------------------");
											//
											inventory.addAll(startLevel(onlevel,attack,health, strength));
											//
											ArrayList<Item> invkills = new ArrayList<Item>();
											invkills.addAll(getItemsWithType(inventory, "kill"));
											inventory.removeAll(getItemsWithType(inventory, "kill"));
											int xp = 0;
											if(invkills.size()!=0){
												for(int k = invkills.size(); k>0; k--){
													xp = xp + random(invkills.get(k-1).VALUE-1, invkills.get(k-1).VALUE+1)*onlevel;
													if(xp==0){
														xp=xp+1;
													}
												}
											}
											invkills.clear();
											if(inventory.contains(getItem("Death"))){
												lives=lives-1;
												inventory.remove(getItem("Death"));
												System.out.println("You now have "+lives+" lives");
											}
											else{
												if(onlevel==level+1){
													level=onlevel;
												}else{
													level=onlevel-1;
												}
												use=xp;
												System.out.println("You gained "+use+" strength points!");
												wait(1);
												strengthPoints=strengthPoints+use;
												while(strengthPoints>=strengthNeeded){
													strength=strength+1;
													strengthPoints=strengthPoints-strengthNeeded;
													strengthNeeded=strengthNeeded*2;
													System.out.println("You got stronger! (+1 attack & health)");
													levelAttack=strength;
													levelHealth=strength;
													wait(1);
												}
												System.out.println("Strength Points: "+strengthPoints+"/"+strengthNeeded);
												wait(1);
												System.out.println("-----------------------");
											}
											if(lives<=0){
												mo2=true;
												mo=true;
												System.out.println("-----------");
												System.out.println(" GAME OVER ");
												System.out.println("-----------");
												inventory.clear();
												level=1;
												waitEnter();
												break;
											}
											break;
										case "no":
											mo2 = true;
											break;
										default:
											System.out.println("'" + answer + "' is not yes or no");
											mo2 = false;
											continue;
										}
									}
									else{
										System.out.println("You are not near area "+onlevel+".");
									}
								}
							}
							else{
								System.out.println("'"+onleveli+"' is not a number (or is way to high/low)");
							}
						}
						mo2=false;
						continue;
					default:
						System.out.println("'" + answer + "' is not an option");
						continue;
					}
				}
				break;
			case "-1":
			case "hi":
				//randomizes response to 'hi'
				random = random(0,2);
				if(random == 0){
					System.out.println("hi?...");
				}
				else if(random == 1){
					System.out.println("what do you want?");
				}
				else{
					System.out.println("whatever.");
				}
				break;
			case "2":
			case "info":
				System.out.println("------------------");
				System.out.println(" Text Knight v"+v);
				wait(2);
				System.out.println(" by: Bryan McShea");
				System.out.println(" !in development!");
				wait(2);
				System.out.println(" -report bugs to:");
				System.out.println("cat71602@gmail.com");
				wait(2);
				System.out.println("------------------");
				System.out.println("Note that this is");
				wait(2);
				System.out.println("a free game and if");
				System.out.println("you payed it isn't");
				wait(2);
				System.out.println("  the original!");
				System.out.println("------------------");
				wait(2);
				break;
			default:
				System.out.println("'" + answer + "' is not an option");
				break;
			}
		}
	}
	//is __ an integer? function
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	//generate a random enemy (returns as an ArrayList<Object> depending on the level)
	public static Enemy getEnemy(int level){
		//get the enemy depending on the level
		ArrayList<Enemy> ePoss = new ArrayList<Enemy>();
		for(int e=enemies.size(); e>0; e--){
			if(enemies.get(e-1).MIN_LEVEL<=level){
				ePoss.add(enemies.get(e-1));
			}
		}
		Enemy enemyChosen = ePoss.get(random(0, ePoss.size()-1));
		Enemy newEnemy = new Enemy("missing name", 0, 0, new ArrayList<Item>(), 0, 0, new ArrayList<Item>(), 0, 0, 0);
		//get some base stats
		int lootAble = random(enemyChosen.MIN_DROP,enemyChosen.MAX_DROP);
		int addedHealth = random(0,level);
		int addedAttack = random(0,level-3);
		newEnemy.NAME=enemyChosen.NAME;
		newEnemy.BASE_HEALTH=enemyChosen.BASE_HEALTH+addedHealth;
		newEnemy.BASE_DAMAGE=enemyChosen.BASE_DAMAGE+addedAttack;
		newEnemy.ALWAYS_DROP.addAll(enemyChosen.ALWAYS_DROP);
		while(lootAble>0){
			newEnemy.ALWAYS_DROP.add(enemyChosen.DROPS.get(random(0, enemyChosen.DROPS.size()-1)));
			lootAble--;
		}
		while(newEnemy.ALWAYS_DROP.contains(getItem("Air"))){
			newEnemy.ALWAYS_DROP.remove(getItem("Air"));
		}
		return newEnemy;
	}
	//generates a battle
	public static ArrayList<Item> startLevel(int level, int attack, int health, int strength){
		//set up variables
		String answer;
		ArrayList<Item> loot = new ArrayList<Item>();
		ArrayList<Item> loot2 = new ArrayList<Item>();
		int r=level+random(0,1);
		int c, ca;
		int ph = health;
		int pa = attack;
		int bh = health;
		while(r>0){
			r=r-1;
			//create enemy
			Enemy enemy = getEnemy(level);
			String en = (String)(enemy.NAME);
			int eh = (int)(enemy.BASE_HEALTH);
			int ea = (int)(enemy.BASE_DAMAGE);
			ArrayList<Item> el = new ArrayList<Item>();
			el.addAll(enemy.ALWAYS_DROP);
			//start
			boolean mo = false;
			boolean notdead = true;
			System.out.println("You currently have "+ph+" health");
			wait(1);
			System.out.println("A "+en+" appeared!");
			wait(1);
			while(notdead){
				System.out.println("What would you like to do?");
				System.out.println("[run(0)][attack(1)][wait(2)]");
				ca=0;
				while(mo==false){
					answer = lowercaseInput();
					switch(answer){
					case "0":
					case "run":
						if(level>strength){
							System.out.println("You couldn't escape!");
							ca=0;
							mo=true;
						}else if(level==strength){
							if(random(1,3)==1){
								System.out.println("You barely got away!");
								wait(1);
								return(loot);
							}else{
								System.out.println("You couldn't escape!");
								ca=0;
								mo=true;
							}
						}else if(level+1==strength){
							if(random(1,3)==1){
								System.out.println("You almost escaped!");
								ca=0;
								mo=true;
							}else{
								System.out.println("You ran away!");
								wait(1);
								return(loot);
							}
						}else if(level+2==strength){
							if(random(1,4)==1){
								System.out.println("You tripped and couldn't escape!");
								ca=0;
								mo=true;
							}else{
								System.out.println("You ran away!");
								wait(1);
								return(loot);
							}
						}else{
							System.out.println("You easily escaped.");
							wait(1);
							return(loot);
						}
						break;
					case "2":
					case "wait":
						//player wait
						System.out.println("You waited...");
						ca=0;
						mo=true;
						break;
					case "1":
					case "attack":
						mo=true;
						//player attack
						c=random(1,10);
						if(c==10){
							ca=pa*2;
							System.out.println("Critical hit! "+ca+" damage! (you)");
						}
						else if(c==1){
							ca=0;
							System.out.println("Your attack missed!");
						}
						else{
							ca=pa;
							System.out.println("You attacked and dealed "+ca+" damage.");
						}
						break;
					default:
						System.out.println("'"+answer+"' is not an option.");
						mo=false;
						break;
					}
				}
				eh=eh-ca;
				ca=0;
				wait(1);
				//has the enemy died?
				if(eh<=0){
					loot.addAll(el);
					System.out.println("You killed the "+en+"...");
					if(!el.isEmpty()){
						System.out.println("and collected "+printItemNames(el)+"!");  
						waitEnter();
					}else{
						wait(1);
					}
					loot.add(getItem(en));
					kills.add(getItem(en));
					notdead=false;
					System.out.println("---------------------");
				}
				//enemy attack
				if(notdead==true){
					c=random(1,10);
					if(c==10){
						ca=ea*2;
						System.out.println("Critical hit! "+ca+" damage! (enemy)");
					}
					else if(c==1){
						ca=0;
						System.out.println("The "+en+"'s attack missed!");
					}
					else{
						ca=ea;
						System.out.println("The "+en+" attacked and dealed "+ca+" damage.");
					}
				}
				ph=ph-ca;
				ca=0;
				//end of enemy attack
				//has the player died?
				if(ph<=0){
					System.out.println("A "+en+" killed you...");
					System.out.println("[-1 life,-all loot collected in this battle]");
					loot.clear();
					loot.add(getItem("Death"));
					notdead=false;
					r=0;
				}
				if(notdead){
					wait(1);
					System.out.println("You have "+ph+" health now...");
					wait(1);
				}
				mo=false;
			}
		}
		if(ph>0){
			System.out.println("You made it past area "+level+"...");
			loot2=getItemsWithType(loot, "kill");
			loot.removeAll(loot2);
			if(!loot.isEmpty()){
				System.out.println("and collected "+printItemNames(loot)+"!");
				waitEnter();
			}else{
				wait(1);
			}
			loot.addAll(loot2);
		}
		mh=mh+bh-ph;
		return loot;
	}
	//converts strings to integers
	public static int turnToInteger(String string){
		int value;
		try{
			value = Integer.parseInt(string);
		}catch(Exception e){
			System.out.println("Failed to use turnToInteger(), returning 0.");
			return 0;
		}
		return value;
	}
	//waits in seconds
	public static void wait(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	//wait for the player to press enter
	public static void waitEnter(){
		String answer = null;
		answer = lowercaseInput();
		if(answer.equals("stop")){
			System.out.println("Stopping...");
			wait(1);
			System.exit(0);
		}else if(answer.equals("troll")){
			System.out.println("Oh, so your gonna troll me is that it?");
			wait(1);
		}else if(answer.equals("rock")||answer.equals("paper")||answer.equals("scissors")||answer.equals("rock!")||answer.equals("paper!")||answer.equals("scissors!")||answer.equals("rock.")||answer.equals("paper.")||answer.equals("scissors.")){
			rps(answer);
		}
		else if(answer.length()!=0){
			System.out.println("You don't need to say that you know...");
			wait(1);
		}
	}
	//rename a file
	public static File reNameFile(String prefix, File file, String suffix){
		String fileName;
		fileName=prefix+file.getName()+suffix;
		return new File(fileName);
	}
	//make the first letters of each word capital in a string (and the rest lowercase)
	public static String makeProper(String str) {
		str.toLowerCase();
	    String[] words = str.split(" ");
	    StringBuilder ret = new StringBuilder();
	    for(int i = 0; i < words.length; i++) {
	        ret.append(Character.toUpperCase(words[i].charAt(0)));
	        ret.append(words[i].substring(1));
	        if(i < words.length - 1) {
	            ret.append(' ');
	        }
	    }
	    return ret.toString();
	}
	//generate a random number
	public static int random(int start, int end){
		if(start>end){
			return 0;
		}
		Random r = new Random();
		end=end-start;
		int value = r.nextInt(end+1)+start;
		return value;
	}
	//pull a specific item out of items
	public static Item getItem(String name){
		for(int i=items.size(); i>0; i--){
			if(name.equals(items.get(i-1).NAME)){
				return items.get(i-1);
			}
		}
		return null;
	}
	//pull a specific enemy out of enemies
	public static Enemy getEnemy(String name){
		for(int i=enemies.size(); i>0; i--){
			if(name==enemies.get(i-1).NAME){
				return enemies.get(i-1);
			}
		}
		return null;
	}
	//prints the name of all the items in an arraylist
	public static ArrayList<String> printItemNames(ArrayList<Item> items){
		ArrayList<String> strings = new ArrayList<String>();
		for(int s = items.size(); s>0;s--){
			strings.add(items.get(s-1).NAME);
		}
		return strings;
	}
	//gets all the items with the type specified
	public static ArrayList<Item> getItemsWithType(ArrayList<Item> items, String type){
		ArrayList<Item> typeItems = new ArrayList<Item>();
		for(int i=items.size(); i>0; i--){
			if(type.equals(items.get(i-1).TYPE)){
				typeItems.add(items.get(i-1));
			}
		}
		return typeItems;
	}
	//gets input ignoring capitals
	public static String lowercaseInput(){
		String str = a.nextLine().toLowerCase();
		return str;
	}
	public static void rps(String answer){
		int use = random(1,3);
		switch(use){
		case 1:
			System.out.println("rock! (me)");
			wait(1);
			switch(answer){
			case "rock.":
			case "rock!":
			case "rock":
				System.out.println("aww... tie.");
				break;
			case "paper.":
			case "paper!":
			case "paper":
				System.out.println("C'mon! You win...");
				break;
			case "scissors.":
			case "scissors!":
			case "scissors":
				System.out.println("Ha ha, I win!");
				break;
			}
			wait(1);
			break;
		case 2:
			System.out.println("paper! (me)");
			wait(1);
			switch(answer){
			case "rock.":
			case "rock!":
			case "rock":
				System.out.println("Ha ha, I win!");
				break;
			case "paper.":
			case "paper!":
			case "paper":
				System.out.println("aww... tie.");
				break;
			case "scissors.":
			case "scissors!":
			case "scissors":
				System.out.println("C'mon! You win...");
				break;
			}
			wait(1);
			break;
		case 3:
			System.out.println("scissors! (me)");
			wait(1);
			switch(answer){
			case "rock.":
			case "rock!":
			case "rock":
				System.out.println("C'mon! You win...");
				break;
			case "paper.":
			case "paper!":
			case "paper":
				System.out.println("Ha ha, I win!");
				break;
			case "scissors.":
			case "scissors!":
			case "scissors":
				System.out.println("aww... tie.");
				break;
			}
			wait(1);
			break;
		}
	}
	public static ArrayList<Item> emptyI(){
		return new ArrayList<Item>();
	}
	public static ArrayList<Integer> emptyN(){
		return new ArrayList<Integer>();
	}
	public static ArrayList<String> emptyS(){
		return new ArrayList<String>();
	}
}
