package org.uob.a1;

import java.util.Scanner; 

public class Game {
    
    private static Room forest, forestPath, cabin, cabinInner, cabinInnerMoved, cabinOpenTrapDoor, basement;
    private static Room currentRoom;
    private static Scanner scanner;
    private static Inventory inventory;
    private static Score score;
    private static Map map;
    
    
    public static void main(String args[]) {
        setup();
        start();
    }
    
    public static void setup() {
        scanner = new Scanner(System.in);   
        forest = new Room("forest","An empty glade in a forest. You are surrounded by dense forest on all sides except for a small path heading south.",'f', new Position (1,1));
        forestPath = new Room("forestpath","A small path leading south through the forest. Further along the path is a small cabin. To the north is an empty glade.",'f', new Position(1,2));
        cabin = new Room("cabin","There is a small cabin in front of you standing amongst the trees of the forest. The door is locked. There are some overgrown bushes all around the base of the cabin.",'c', new Position(1,3));
        cabinInner = new Room("cabininner","You are standing inside an old abandoned cabin. You see some old furniture and a rug covering the floor.",'c', new Position(1,3));
        cabinInnerMoved = new Room("cabininnermoved","You are standing inside an old abandoned cabin. You see some old furniture. The rug covering the floor has been moved aside to reveal a trapdoor.",'c', new Position(1,3));
                cabinOpenTrapDoor = new Room("cabinopertrapdoor","You are standing inside an old abandoned cabin. You see some old furniture. There is an open trapdoor in the floor.",'c', new Position(1,3));
        basement = new Room("basement","You are standing in a dark and musty basement. The trapdoor above is stuck",'b', new Position(1,3));
        currentRoom = forest;
        inventory = new Inventory();
        score = new Score(100);
        map = new Map(10,10);
        map.placeRoom(new Position(1,1),'f');
    }
    
    public static void start() {
        System.out.println(currentRoom.getDescription());

        System.out.print(">> ");
        String input = scanner.nextLine().toLowerCase();
        while(!input.equals("quit")) {
            String output = "";
            if(input.toLowerCase().equals("look")) {
                output = currentRoom.getDescription();
            } else if(input.toLowerCase().equals("move south") && currentRoom.equals(forest)) {
                output = "You follow the path south";
                currentRoom = forestPath;
                map.placeRoom(currentRoom.getPosition(),currentRoom.getSymbol());
                score.visitRoom();
            } else if(input.toLowerCase().equals("move south") && currentRoom.equals(forestPath)) {
                output = "You follow the path towards the cabin";
                currentRoom = cabin;
                map.placeRoom(currentRoom.getPosition(),currentRoom.getSymbol());
                score.visitRoom();
            } else if(input.toLowerCase().equals("move north") && currentRoom.equals(cabin)) {
                output = "You leave the area around the cabin and travel north.";
                currentRoom = forestPath;
                score.visitRoom();
            } else if(input.toLowerCase().equals("move north") && currentRoom.equals(forestPath)) {
                output = "You follow the path north.";
                currentRoom = forest;
                score.visitRoom();
            } else if(input.toLowerCase().equals("search bushes") && currentRoom.equals(cabin)  
                      && inventory.hasItem("key") == -1) {
                output = "You search the bushes and find an old rusty key";
                inventory.addItem("key");
                score.solvePuzzle();               
            } else if(input.toLowerCase().equals("open door") && currentRoom.equals(cabin) 
                      && inventory.hasItem("key") > -1) {
                output = "You open the door";
                currentRoom = cabinInner;
            } else if(input.toLowerCase().equals("move rug") && currentRoom.equals(cabinInner)) {
                output = "You move the rug. A wooden trapdoor is revealed with a rusty handle.";
                currentRoom = cabinInner;
             } else if(input.toLowerCase().equals("open trapdoor") && currentRoom.equals(cabinInnerMoved)) {
                output = "You lift the trap door. A ladder leads down into a dark basement.";
                currentRoom = cabinOpenTrapDoor;
            } else if(input.toLowerCase().equals("climb down") && currentRoom.equals(cabinOpenTrapDoor)) {
                output = "You lift the trap door. A ladder leads down into a dark basement.";
                currentRoom = cabinOpenTrapDoor;
                score.visitRoom();
            } else if(input.toLowerCase().equals("look rug") && currentRoom.equals(cabinInner)) {
                output = "The rug in the centre of the room looks like it has been moved recently.";
            } else if(input.toLowerCase().equals("inventory"))  {
                output = "You have: " + inventory.displayInventory();
            } else if(input.toLowerCase().equals("score"))  {
                output = "SCORE: " + score.getScore();
             } else if(input.toLowerCase().equals("help"))  {
                output = "Valid commands are: <look>, <move> <direction>, <look feature>, <look item>, <help>, <inventory>, <map>, <score> and <quit>";
            } else if(input.toLowerCase().equals("map"))  {
                output += map.display();
            } else { 
                output = "Invalid command";
            }
            
            System.out.println(output);

            System.out.print(">> ");
            input = scanner.nextLine().toLowerCase();
        }
        System.out.println("Game over");

        scanner.close();
        
    }
    
    
}