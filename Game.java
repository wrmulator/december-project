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

    public Test(){
        ptr.add(new Item("TOOTH", 3));
        stk.add(new Item("FROG", 3));
        plc.add("your pantry");
        plc.add("your kitchen");
        plc.add("the shop");
        loc = plc.get(0);
        System.out.println("> Welcome to the cooking game.");
        System.out.println("> This game was programmed with help from Gabriel Cardozo Hanley, who says hello to Mr. Morris and that you should teach us Haskell.");
        System.out.println("> He also sent me an image of a wailing face with a Haskell logo on it.");
        System.out.println("> You, the player, may act by typing commands into the console.");
        System.out.println("> Through these commands, you may move between locations and interact with lists and the items they hold.");
        System.out.println("> The three available locations are the kitchen, the pantry, and the shop.");
        System.out.println("> From the pantry, you may interact with its contents; from the kitchen, you may interact with the pot; you may interact with your wallet and inventory from anywhere.");
        System.out.println("> Type 'go to [location]' to change locations.");
        System.out.println("> Type 'check [list]' to check the contents of your pot or pantry.");
        System.out.println("> Type 'help' for more information.");
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
            System.out.println("> Type 'check wallet' to check how much money you have.");
            System.out.println("> Type 'buy [item]' to buy an item from the shop.");
            System.out.println("> Type 'leave [item]' to sell an item to the shop.");
            System.out.println("> Type 'go to [location]' to leave the shop.");
        }
        cmd();
    }

    public void cmd(){
        String cmd = in.nextLine();
        if (cmd.equals("help"))
            help();
        if (cmd.equals("quit")){}
        String[] wrd = cmd.split(" ");
        if (wrd[0].equals("check")){
            check(wrd[1]);
        } if (wrd[0].equals("go")){
            if (wrd.length == 1){
                System.out.println("> ok");
            } else if (wrd[1].equals("to")){
                go(wrd[2]);
            } else {
                go(wrd[1]);
            }
        }
        System.out.println("> Invalid request.");
        cmd();
    }

    public void go(String s){
        if (s.equals("pantry")){
            loc = plc.get(0);
            System.out.println("> You are in " + loc + ".");
        } else if (s.equals("kitchen")){
            loc = plc.get(1);
            System.out.println("> You are in " + loc + ".");
        } else if (s.equals("shop")){
            loc = plc.get(2);
            System.out.println("> You are in " + loc + ".");
            System.out.println("> Here you can buy new ingredients with which to cook.");
            System.out.println("> Available items include:");
            for (Item e: stk)
                System.out.println("> " + e.nm() + " - " + e.prc() + " dabloons");
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
                    System.out.println("> " + e.nm());
            }
        } else if (s.equals("pantry")){
            if (loc.equals(plc.get(0))){
                if (ptr.size() == 0){
                    System.out.println("> Your pantry is empty.");
                } else {
                    System.out.println("> Pantry contents:");
                    for (Item e: ptr)
                        System.out.println("> " + e.nm());
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
                        System.out.println("> " + e.nm());
                }
            } else {
                System.out.println("> You cannot access your pot from " + loc + ".");
            }
        } else if (s.equals("wallet")){
            System.out.println("> You have " + wlt + " dabloons.");
        } else {
            System.out.println("> Invalid request.");
        }
        cmd();
    }
}