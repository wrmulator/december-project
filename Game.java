import java.util.ArrayList;
import java.util.Scanner;

public class Game{
    Scanner in = new Scanner(System.in);
    private ArrayList<Item> inv = new ArrayList<Item>(); //inventory
    private ArrayList<Item> ptr = new ArrayList<Item>(); //pantry
    private ArrayList<Item> stk = new ArrayList<Item>(); //shop stock
    private ArrayList<Item> pot = new ArrayList<Item>(); //pot
    private ArrayList<String> plc = new ArrayList<String>(); //locations (places)
    private String loc; //current location
    private int wlt = 10; //wallet

    public Game(){
        ptr.add(new Item("tooth", 1));
        ptr.add(new Item("parsley", 2));
        stk.add(new Item("bone", 1));
        stk.add(new Item("frog", 3));
        stk.add(new Item("saffron", 14));
        stk.add(new Item("quartz?", 4));
        plc.add("your pantry");
        plc.add("your kitchen");
        plc.add("the shop");
        loc = plc.get(0);
        System.out.println("> Welcome to a cooking game by Eleanor Hanley Cardozo.");
        System.out.println("> This game's command-processing function was programmed with assistance from Gabriel Cardozo Hanley, who suggested the use of the 'split' function.");
        System.out.println("> He says hello to Mr. Morris and that you should teach us Haskell.");
        System.out.println("");
        System.out.println("> You, the player, inhabit the role of a witch.");
        System.out.println("> You interact with this game by typing commands into the console.");
        System.out.println("> The commands at your disposal depend on your current location and its contents.");
        System.out.println("> There are three locations in the game: your pantry, your kitchen, and the shop.");
        System.out.println("> Use the 'help' command for more information about your current location and available commands.");
        cmd();
    }

    public void help(){
        if (loc.equals(plc.get(0))){ //if current location = pantry
            System.out.println("> You are in the pantry.");
            System.out.println("> Type 'check pantry' to check the contents of the pantry.");
            System.out.println("> Type 'take [item]' to move an item from the pantry to your inventory.");
            System.out.println("> Type 'leave [item]' to move an item from your inventory to the pantry.");
            System.out.println("> Items in the pantry can only be accessed from the pantry. Items in your inventory can be accessed from anywhere.");
            System.out.println("> Type 'go to [location]' to leave the pantry.");
        } if (loc.equals(plc.get(1))){ //if current location = kitchen
            System.out.println("> You are in the kitchen.");
            System.out.println("> Type 'check pot' to check the contents of the pot.");
            System.out.println("> Type 'take [item]' to move an item from the pot to your inventory.");
            System.out.println("> Type 'leave [item]' to move an item from your inventory to the pot.");
            System.out.println("> Items in the pot can only be accessed from the kitchen. Items in your inventory can be accessed from anywhere.");
            System.out.println("> Type 'cook' to cook the contents of the pot.");
            System.out.println("> Type 'go to [location]' to leave the kitchen.");
        } if (loc.equals(plc.get(2))){ //if current location = shop
            System.out.println("> You are in the shop.");
            System.out.println("> Type 'check stock' to check what items you can buy.");
            System.out.println("> Type 'check wallet' to check how much money you have.");
            System.out.println("> Type 'buy [item]' to buy an item from the shop.");
            System.out.println("> Type 'sell [item]' to sell an item to the shop.");
            System.out.println("> Type 'go to [location]' to leave the shop.");
        }
        cmd();
    }

    public void cmd(){ //method that reads user input
        String cmd = in.nextLine();
        if (cmd.equals("help"))
            help();
        if (cmd.equals("cook"))
            cook();
        if (cmd.equals("quit"))
            System.exit(0);
        //above commands are simple; all others require specification of object
        String[] wrd = cmd.split(" "); //if user input != any of the above commands, make it an array
        //in order to analyze each part (command and object)
        if (wrd[0].equals("drink") || wrd[0].equals("ingest")){
            if (wrd.length == 2 && (wrd[1].equals("potion") || wrd[1].equals("tasty-potion"))){
                drink(wrd[1]);
            }
        } if (wrd[0].equals("check")){
            if (wrd.length == 2)
                check(wrd[1]);
            else
                System.out.println("> The 'check' command consists of two words: the command and the container.");
        } if (wrd[0].equals("go")){
            if (wrd.length == 1){
                System.out.println("> ok"); //game will then quit (being mean)
            } else if (wrd.length == 3 && wrd[1].equals("to")){ //grammatical form: 'go to place'
                go(wrd[2]);
            } else if (wrd.length == 2) { //'go place;' ungrammatical but recognizable
                go(wrd[1]);
            } else {
                System.out.println("> The 'go' command consists of two to three words: the command, the location, and the optional preposition 'to.'");
            }
        } if (wrd[0].equals("take")) {
            if (wrd.length == 2)
                take(wrd[1]);
            else
                System.out.println("> The 'take' command consists of two words: the command and the item.");
        } if (wrd[0].equals("buy")) {
            if (wrd.length == 2)
                take(wrd[1]);
            else
                System.out.println("> The 'buy' command consists of two words: the command and the item.");
        } if (wrd[0].equals("leave")) {
            if (wrd.length == 2)
                leave(wrd[1]);
            else
                System.out.println("> The 'leave' command consists of two words: the command and the item.");
        } if (wrd[0].equals("sell")) {
            if (wrd.length == 2)
                leave(wrd[1]);
            else
                System.out.println("> The 'sell' command consists of two words: the command and the item.");
        }
        System.out.println("> Invalid command."); //if none of the above, then not a valid command
        //alert user to error & provide opportunity to correct it (request new input)
        cmd();
    }

    public void cook(){
        if (loc.equals(plc.get(1))){ //if location = kitchen
            if (pot.size() > 0){ //if pot is not empty
                boolean tasty = false;
                boolean evil = false;
                for (int x = 0; x < pot.size(); x ++){
                    if (pot.get(x).nm().equals("quartz?")) //check pot for saffron
                        evil = true;
                    if (pot.get(x).nm().equals("saffron")) //check pot for saffron
                        tasty = true;
                } while (pot.size() > 0) //empty the pot
                    pot.remove(0); 
                if (evil){
                    System.out.println("> You have made... oh, no.");
                    System.out.println("> Your house was raided by cops and you were arrested on the charge of posession of a Schedule II controlled substance. Nice going, Mr. White.");
                    System.exit(0);
                } else if (tasty){
                    if (wlt < 0){
                        stk.add(new Item("tasty-potion", 50));
                        wlt += 50;
                        System.out.println("> You have made a tasty potion! Congratulations!");
                        System.out.println("> Unfortunately, it was confiscated buy the shop to cover your debt.");
                    } else {
                        inv.add(new Item("tasty-potion", 50));
                        System.out.println("> You have made a tasty potion! Congratulations! Go ahead and ingest it.");
                        System.out.println("> 'tasty-potion' was added to your inventory.");
                    }
                } else {
                    if (wlt < 0){
                        stk.add(new Item("potion", 15));
                        wlt += 15;
                        System.out.println("You have made a potion! Congratulations!");
                        System.out.println("Unfortunately, it was confiscated by the shop to cover your debt.");
                    } else {
                        inv.add(new Item("potion", 15));
                        System.out.println("> You have made a potion! Congratulations. Please do not ingest it.");
                        System.out.println("> 'potion' was added to your inventory.");
                    }
                }
            } else
                System.out.println("> There is nothing in your pot to cook.");
        } else
            System.out.println("> You cannot cook outside of your kitchen.");
        cmd();
        }

    public void drink(String s){
        if (s.equals("potion") || s.equals("tasty-potion")){ //object is drinkable
            boolean b = false; //is object in inventory?
            for (int x = 0; x < inv.size() && !b; x ++){
                if (inv.get(x).nm().equals(s))
                    b = true;
            } if (b && s.equals("potion")){ //object (potion) is in inventory
                System.out.println("> I told you not to do that.");
                System.exit(0);
            } else if (b && s.equals("tasty-potion")){ //object (tasty-potion) is in inventory
                System.out.println("> I actually lied. You're not supposed to ingest that, either.");
                System.exit(0);
            } else { //object is not in inventory
                System.out.println("> Invalid command.");
            }
        } else { //object is not drinkable
            System.out.println("> Invalid command.");
        }
    }

    public void take(String s){ //parameter: name of item to take
        if (loc.equals(plc.get(0))){ //if location = pantry
            boolean b = false; //is item in pantry?
            for (int x = 0; x < ptr.size(); x ++){
                if (ptr.get(x).nm().equals(s)){
                    b = true;
                    Item i = ptr.remove(x);
                    inv.add(i);
                    x = ptr.size();
                    System.out.println("> '" + i.nm() + "' was added to your inventory.");
                }
            } if(!b)
                System.out.println("> That item is not in this room or does not exist.");
        } if (loc.equals(plc.get(1))){ //if location = kitchen
            boolean b = false; //is item in pot?
            for (int x = 0; x < pot.size(); x ++){
                if (ptr.get(x).nm().equals(s)){
                    b = true;
                    Item i = pot.remove(x);
                    inv.add(i);
                    x = pot.size() * 2;
                    System.out.println("> '" + i.nm() + "' was added to your inventory.");
                }
            } if (!b)
                System.out.println("> That item is not in this room or does not exist.");
        } if (loc.equals(plc.get(2))){ //if location = shop
            boolean b = false; //is item in stock?
            for (int x = 0; x < stk.size(); x ++){
                if (stk.get(x).nm().equals(s)){
                    b = true;
                    if (s.equals("saffron") && wlt < 14){
                        System.out.println("> You need more money. Saffron cannot be bought on credit.");
                    } else {
                        Item i = stk.get(x);
                        if (x < 5) //if item was part of original stock
                            inv.add(stk.get(x)); //do NOT remove from stock
                        else //if tem was sold to shop by player
                            inv.add(stk.remove(x)); //remove from stock
                        wlt -= i.prc();
                        if (i.prc() == 1)
                            System.out.println("> '" + i.nm() + "' was added to your inventory for the price of 1 dabloon.");
                        else
                            System.out.println("> '" + i.nm() + "' was added to your inventory for the price of " + i.prc() + " dabloons.");
                        x = stk.size();
                    }
                }
            } if (!b)
                System.out.println("> That item is not available to buy.");
        }
        cmd();
    }

    public void leave(String s){ //parameter: name of item to leave
        if (loc.equals(plc.get(0))){ //if location = pantry
            boolean b = false; //is item in inventory?
            for (int x = 0; x < inv.size() && !b; x ++){
                if (inv.get(x).nm().equals(s)){
                    Item i = inv.remove(x);
                    ptr.add(i);
                    b = true;
                    System.out.println("> '" + i.nm() + "' was added to your pantry."); //print message
                }
            } if (!b)
                System.out.println("> That item is not in your inventory.");
        } if (loc.equals(plc.get(1))){ //if location = kitchen
            boolean b = false; //is item in inventory?
            for (int x = 0; x < inv.size() && !b; x ++){
                if (inv.get(x).nm().equals(s)){
                    b = true;
                    Item i = inv.get(x);
                    pot.add(inv.remove(x));
                    x = inv.size();
                    System.out.println("> '" + i.nm() + "' was added to your pot.");
                }
            } if (!b)
                System.out.println("> That item is not in your iventory.");
        } if (loc.equals(plc.get(2))){ //if location = shop
            boolean c = false; //is item in stock?
            boolean b = false; //is item in inventory?
            for (int x = 5; x < stk.size(); x ++){
                if (stk.get(x).nm().equals(s))
                    c = true;
            }
            for (int x = 0; x < inv.size(); x ++){
                if (inv.get(x).nm().equals(s)){
                    b = true;
                    if (c){ //if item is in both inventory and stock
                        Item i = inv.remove(x); //do not add to stock
                        wlt += i.prc();
                        x = inv.size();
                        if (i.prc() == 1)
                            System.out.println("> '" + i.nm() + "' was removed from your inventory for the price of 1 dabloon.");
                        else
                            System.out.println("> '" + i.nm() + "' was removed from your inventory for the price of " + i.prc() + " dabloons.");
                    } else { //if item is in inventory but not stock
                        Item i = inv.remove(x);
                        wlt += i.prc();
                        stk.add(i); //add to stock
                        x = inv.size();
                        if (i.prc() == 1)
                            System.out.println("> '" + i.nm() + "' was removed from your inventory for the price of 1 dabloon.");
                        else
                            System.out.println("> '" + i.nm() + "' was removed from your inventory for the price of " + i.prc() + " dabloons.");
                    }
                }
            } if (!b)
                System.out.println("> That item is not in your inventory.");
        }
        cmd();
    }

    public void go(String s){ //parameter: name of location to go
        if (s.equals("pantry")){
            if (loc == plc.get(0))
                System.out.println("> You are already in " + loc + ".");
            else {
                loc = plc.get(0); //current location = place 0: pantry
                System.out.println("> You are in " + loc + ".");
                System.out.println("> Here you can access your stored ingredients.");
            }
        } else if (s.equals("kitchen")){
            if (loc == plc.get(1))
                System.out.println("> You are already in " + loc + ".");
            else {
                loc = plc.get(1); //current location = place 1: kitchen
                System.out.println("> You are in " + loc + ".");
                System.out.println("> Here you can cook ingredients in your pot.");
            }
        } else if (s.equals("shop")){
            if (loc == plc.get(2))
                System.out.println("> You are already in " + loc + ".");
            else {
                loc = plc.get(2); //current location = place 2: shop
                System.out.println("> You are in " + loc + ".");
                System.out.println("> Here you can buy new ingredients with which to cook.");
            }
        } else
            System.out.println("> That location does not exist.");
        cmd();
    }

    public void check(String s){ //parameter: container to check
        if (s.equals("inventory")){
            if (inv.size() == 0){
                System.out.println("> Your inventory is empty.");
            } else {
                System.out.println("> Inventory contents:");
                for (Item e: inv)
                    System.out.println(">> " + e.nm());
            }
        } else if (s.equals("pantry")){
            if (loc.equals(plc.get(0))){
                if (ptr.size() == 0){
                    System.out.println("> Your pantry is empty.");
                } else {
                    System.out.println("> Pantry contents:");
                    for (Item e: ptr)
                        System.out.println(">> " + e.nm());
                }
            } else {
                System.out.println("> You cannot access your pantry from " + loc + ".");
            }
        } else if (s.equals("pot")){
            if (loc.equals(plc.get(1))){
                if (pot.size() == 0){
                    System.out.println("> Your pot is empty.");
                } else {
                    System.out.println("> Pot contents:");
                    for (Item e: pot)
                        System.out.println(">> " + e.nm());
                }
            } else {
                System.out.println("> You cannot access your pot from " + loc + ".");
            }
        } else if (s.equals("stock") || s.equals("shop")){
            if (loc.equals(plc.get(2))){
                System.out.println("> Shop stock:");
                for (Item e: stk){
                    if (e.prc() == 1)
                        System.out.println(">> " + e.nm() + " - 1 dabloon");
                    else
                        System.out.println(">> " + e.nm() + " - " + e.prc() + " dabloons");
                }
            } else {
                System.out.println("> You cannot access the shop stock from " + loc + ".");
            }
        } else if (s.equals("wallet")){
            System.out.println("> You have " + wlt + " dabloons.");
        } else {
            System.out.println("> Invalid request.");
        }
        cmd();
    }
}