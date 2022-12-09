import java.util.ArrayList;
import java.util.Scanner;

public class Game{
    Scanner in = new Scanner(System.in);
    private ArrayList<Item> inv = new ArrayList<Item>();
    private ArrayList<Item> ptr = new ArrayList<Item>();
    private ArrayList<Item> stk = new ArrayList<Item>();
    private ArrayList<Item> pot = new ArrayList<Item>();
    private ArrayList<String> plc = new ArrayList<String>();
    private String loc;
    private int wlt = 10;

    public Game(){
        ptr.add(new Item("tooth", 1));
        // ptr.add(new Item("normal rock", 1));
        ptr.add(new Item("parsley", 2));
        stk.add(new Item("bone", 1));
        stk.add(new Item("quartz", 2));
        stk.add(new Item("rosemary", 2));
        stk.add(new Item("saffron", 5));
        // stk.add(new Item("shiny rock", 5));
        stk.add(new Item("frog", 3));
        plc.add("your pantry");
        plc.add("your kitchen");
        plc.add("the shop");
        loc = plc.get(0);
        System.out.println("> Welcome to the cooking game.");
        System.out.println("> This game was programmed with assistance from Gabriel Cardozo Hanley, who says hello to Mr. Morris and that you should teach us Haskell.");
        System.out.println("");
        System.out.println("> You, the player, interact with this game by typing commands into the console.");
        System.out.println("> Through these commands, you may move between the pantry, kitchen, and shop, and interact with the items inside.");
        System.out.println("> Use the 'help' command for more information about what you can do in your current location.");
        System.out.println("");
        System.out.println("> You are a cook, and you are in your pantry.");
        System.out.println("> What shall you do?");
        cmd();
    }

    public void help(){
        if (loc.equals(plc.get(0))){
            System.out.println("> You are in the pantry.");
            System.out.println("> Type 'check pantry' to check the contents of the pantry.");
            System.out.println("> Type 'take [item]' to move an item from the pantry to your inventory.");
            System.out.println("> Type 'leave [item]' to move an item from your inventory to the pantry.");
            System.out.println("> Items in the pantry can only be accessed from the pantry. Items in your inventory can be accessed from anywhere.");
            System.out.println("> Type 'go to [location]' to leave the pantry.");
        } if (loc.equals(plc.get(1))){
            System.out.println("> You are in the kitchen.");
            System.out.println("> Type 'check pot' to check the contents of the pot.");
            System.out.println("> Type 'take [item]' to move an item from the pot to your inventory.");
            System.out.println("> Type 'leave [item]' to move an item from your inventory to the pot.");
            System.out.println("> Items in the pot can only be accessed from the kitchen. Items in your inventory can be accessed from anywhere.");
            System.out.println("> Type 'cook' to cook the contents of the pot.");
            System.out.println("> Type 'go to [location]' to leave the kitchen.");
        } if (loc.equals(plc.get(2))){
            System.out.println("> You are in the shop.");
            System.out.println("> Type 'check stock' to check what items you can buy.");
            System.out.println("> Type 'check wallet' to check how much money you have.");
            System.out.println("> Type 'buy [item]' to buy an item from the shop.");
            System.out.println("> Type 'sell [item]' to sell an item to the shop.");
            System.out.println("> Type 'go to [location]' to leave the shop.");
        }
        cmd();
    }

    public void cmd(){
        String cmd = in.nextLine();
        if (cmd.equals("help"))
            help();
        if (cmd.equals("cook"))
            cook();
        if (cmd.equals("quit")){}
        if (cmd.equals("drink") || cmd.equals("drink potion") || cmd.equals("ingest potion") || cmd.equals("ingest"))
            drink();
        String[] wrd = cmd.split(" ");
        if (wrd[0].equals("check")){
            if (wrd.length == 2)
                check(wrd[1]);
            else
                System.out.println("> The 'check' command consists of two words: the command and the container.");
        } if (wrd[0].equals("go")){
            if (wrd.length == 1){
                System.out.println("> ok");
            } else if (wrd.length == 3 && wrd[1].equals("to")){
                go(wrd[2]);
            } else if (wrd.length == 2) {
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
        System.out.println("> Invalid command.");
        cmd();
    }

    public void cook(){
        if (loc.equals(plc.get(1))){
            while (pot.size() > 0)
                pot.remove(0);
            inv.add(new Item("potion", 20));
            System.out.println("> You have made a potion! Congratulations. Please do not ingest it.");
            System.out.println("> 'Potion' was added to your inventory.");
            cmd();
        }
    }

    public void drink(){
        boolean b = false;
        for (int x = 0; x < inv.size() && !b; x ++){
            if (inv.get(x).nm().equals("potion"))
                b = true;
        } if (b){
            System.out.println("> I told you not to do that.");
            System.exit(0);
        } else {
            System.out.println("Invalid command.");
        }
    }

    public void take(String s){
        if (loc.equals(plc.get(0))){
            boolean b = false;
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
        } if (loc.equals(plc.get(1))){
            boolean b = false;
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
        } if (loc.equals(plc.get(2))){
            boolean b = false;
            for (int x = 0; x < stk.size(); x ++){
                if (stk.get(x).nm().equals(s)){
                    b = true;
                    inv.add(stk.get(x));
                    wlt -= stk.get(x).prc();
                    if (stk.get(x).prc() == 1)
                        System.out.println("> '" + stk.get(x).nm() + "' was added to your inventory at the price of 1 dabloon.");
                    else
                        System.out.println("> '" + stk.get(x).nm() + "' was added to your inventory at the price of " + stk.get(x).prc() + " dabloons.");
                    x = stk.size();
                }
            } if (!b)
                System.out.println("> That item is not available to buy.");
        }
        cmd();
    }

    public void leave(String s){
        if (loc.equals(plc.get(0))){
            boolean b = false;
            for (int x = 0; x < inv.size() && !b; x ++){
                if (inv.get(x).nm().equals(s)){
                    Item i = inv.remove(x);
                    ptr.add(i);
                    b = true;
                    System.out.println("> '" + i.nm() + "' was added to your pantry."); //print message
                }
            } if (!b)
                System.out.println("> That item is not in your inventory.");
        } if (loc.equals(plc.get(1))){
            boolean b = false;
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
        } if (loc.equals(plc.get(2))){
            boolean b = false;
            boolean c = false;
            for (int x = 0; x < stk.size(); x ++){
                if (stk.get(x).nm().equals(s))
                    b = true;
            }
            for (int x = 0; x < inv.size(); x ++){
                if (inv.get(x).nm().equals(s)){
                    c = true;
                    if (b){
                        Item i = inv.remove(x);
                        wlt += i.prc();
                        x = inv.size();
                        if (i.prc() == 1)
                            System.out.println("> '" + i.nm() + "' was removed from your inventory for the price of 1 dabloon.");
                        else
                            System.out.println("> '" + i.nm() + "' was removed from your inventory for the price of " + i.prc() + " dabloons.");
                    } else {
                        Item i = inv.remove(x);
                        wlt += i.prc();
                        stk.add(i);
                        x = inv.size();
                        if (i.prc() == 1)
                            System.out.println("> '" + i.nm() + "' was removed from your inventory for the price of 1 dabloon.");
                        else
                            System.out.println("> '" + i.nm() + "' was removed from your inventory for the price of " + i.prc() + " dabloons.");
                    }
                }
            } if (!c)
                System.out.println("> Thatitem is not in your inventory.");
        }
        cmd();
    }

    public void go(String s){
        if (s.equals("pantry")){
            loc = plc.get(0);
            System.out.println("> You are in " + loc + ".");
            System.out.println("> Here you can access your stored ingredients.");
        } else if (s.equals("kitchen")){
            loc = plc.get(1);
            System.out.println("> You are in " + loc + ".");
            System.out.println("> Here you can cook ingredients in your pot.");
        } else if (s.equals("shop")){
            loc = plc.get(2);
            System.out.println("> You are in " + loc + ".");
            System.out.println("> Here you can buy new ingredients with which to cook.");
        } else
            System.out.println("> That location does not exist.");
        cmd();
    }

    public void check(String s){
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