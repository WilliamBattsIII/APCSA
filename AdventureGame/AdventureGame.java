// AdventureGame.java
// William Batts III | 2024
import java.util.*;

public class AdventureGame {
  // I suspect many kilobytes of memory will be gobbled by this
  // [y][x] -> [x] is the x-coord, [y] is the y-coord
  public static String[][] placedesc = {
    {"Your eyes reveal that you are in a damp cavern. You can go east or south.","You approach a small opening, with several boulders and rocks. You may travel west, south, or east.","The air is rather humid. The cave is otherwise unremarkable. You may travel: W,S,E","Stalagmites interrupt the perfectly smooth floor. You may travel: W,S,E","As you approach a corner, the only way out of this area is either where you returned, or around a corner. You may travel: W,S"},
    {"The passage seems to broaden slightly. Stalactites have formed, and are precariously hanging above an underground river.\nYou may travel: N,E,S","You spot a crevasse among the walls of rock. Within it, a skeleton's arm sticks out, holding a shiny key. You may travel: N,E,S,W","The cavern narrows as you walk along the dark passage. You may travel: N,E,S,W","You stumble into a vast opening. The smell of salt fills the air. You may travel: N,E,S,W","You've stumbled upon a dead end. You may travel: N,W,S"},
    {"A small wooden chest sits leaned against the carvern wall. There are veins of salt among the dark walls. You may travel: N,E,S","Water slowly drips from the ceiling, collecting in a puddle. There are several diverging passages. You may travel: N,E,S,W","The passage becomes rather cramped, and quite dark. You may travel: N,E,S,W","The narrowing passages spanning out from here contrast with the wide opening produced by the caverns. You may travel: N,E,S,W","More and more cave continues into the dark. You may travel: N,W,S"},
    {"Beams of light peer in from a crack in the cave ceiling. You may travel: N,E,S","Snakes coil in a corner of the opening, near a puddle. You may travel: N,E,S,W","You may travel: N,E,S,W","A barely-perceptible glow emanates from one of the rocks. You may travel: N,E,S,W","The narrow cave contains a sulfurous smell. You may travel: N,W,S"},
    {"You approach a dimly-lit corner. You may travel: N,E","The cave continues onwards. There is a faint glimmering in the corner of your eye.\nYou may travel east, west, or north.","It is totally pitch black. You may travel: N,E,W","The cave contains puddles of water dripping from the ceiling. There are mushrooms.\nYou may travel: N,E,W","A faint beam of light comes from a hole in the ceiling. You can hear water streaming in the distance.\nYou may travel: N,W"}
  };
  //[y][x]
  public static String[][] keytext = { // figured this was a better choice than randomly picking text to show
  {"You wander around, searching for a key. There is no key.",//0
"Buried in the mud, you spot a gold key!",//1
"Despite your quest to find the keys, you fail to unearth one in this part of the caverns.",//2
"There is no key.",//3
"You can't find anything."},//4

  {"You comb the floor, pan through the river, and inspect every little crack - but you fail to recover a key.",//0
"You slowly walk closer to inspect the key. As it turns out, it's just an ordinary (albeit shiny) house key - how disappointing.",//1
"You don't find anything.","There are no keys in sight.",//2
"Unfortunately, you can't find any keys."},//3

  {"Although the rotting wood of the chest nearly fell apart, you managed to find a key, hidden within!", //0
"You struggle to see anything. Ultimately, you couldn't find a key.", //1
"A piece of parchment on the floor reads 'THERE IS NO KEY.' Oh well.", //2
"Lying right in front of you (on top of a rock) is a key! What luck!",//3
"You overturn every rock you see - none of them have keys beneath."},//4

  {"There is no key, despite your efforts.",//0
"You can't find anything.",//1
"Keys? You wish. You complete your search empty-handed.",//2
"You are unsuccessful in your search.",//3
"You found a key, inside of a pristine microwave oven. How'd that get down here?"},//4

  {"You struggled to find anything but rocks. Despite your efforts, a cavern and rocks does not a key make.",//0
"Hanging from the ceiling is a golden key! The rope was nearly rotted through - plucking it down is trivial.",//1
"No key, bad luck!",//2
"You can't find any keys here.",//3
"In this isolated corner of the caverns, no keys exist!"}//4
  };
  public static int[][] keyloc = {
    {0,1,0,0,0},
    {0,0,0,0,0},
    {1,0,0,1,0},
    {0,0,0,0,1},
    {0,1,0,0,0}
  };
  public static boolean[][] visited = {
    {false,false,false,false,false},
    {false,false,false,false,false},
    {false,false,false,false,false},
    {false,false,false,false,false},
    {false,false,false,false,false}
  };
  public static int x = 0, y = 0; // starting coordinates
  public static int keys = 0; // key inventory
  public static void main(String[] args) {
    boolean keysleft = true; // fairly simple
    Scanner input = new Scanner(System.in); // set up input
    String command;
    intro(input);
    while(keysleft) {
      if(!visited[y][x]){System.out.println(placedesc[y][x]);visited[y][x]=true;}// only print place desc if not visited spot yet
      System.out.println("(inverse coordinates) x="+x+", y="+y+" || You have "+keys+"/5 keys!"); // info (this was originally going to be a debug setting, but I found it useful)
      System.out.println("Would you like to go (north/east/south/west)? Or do you want to look around (search) for a key?"); // prompt
      command = input.nextLine(); // I think it works because it's changing the reference to the one from the Scanner object
      parseCommand(command, input); // this also should handle game stuff, too
      if(keys == 5) {keysleft = false;System.out.println("Congratulations, you found all the keys and beat the game!\n...I forgot which doors in particular they go to, but regardless - thanks for finding them!");} // win condition
    }
  }
// if x=0, can't go west
// if x=4, can't go east
// if x=0, can't go north
// if y=4, can't go south
  public static void parseCommand(String cmd, Scanner input) {
    if(cmd.toLowerCase().equals("search")) {
      if(keyloc[y][x] == 1) {
        keyloc[y][x] = 2;
        keys++;
        System.out.println("YOU FOUND A KEY"); // debugging print
        System.out.println(keytext[y][x]);
      }
      else if(keyloc[y][x] == 2) {
        System.out.println("You already found a key here. However, you continue your search, rechecking the room. Alas, there seems to be only one key per room.");
      }
      else {
        System.out.println(keytext[y][x]);
      }
    }
    else if(cmd.toLowerCase().equals("north")) {
        if(y == 0) {
          System.out.println("You can't go this way!");
        }
        else {y -= 1;} // IMPORTANT: uses inverse coordinates - 0 is "higher" than 4
    }
    else if(cmd.toLowerCase().equals("south")) {
        if(y == 4) {
          System.out.println("You can't go this way!");
        }
        else {y += 1;}
    }
    else if(cmd.toLowerCase().equals("east")) {
        if(x == 4) {
          System.out.println("You can't go this way!");
        }
        else {x += 1;}
    }
    else if(cmd.toLowerCase().equals("west")) {
        if(x == 0) {
          System.out.println("You can't go this way!");
        }
        else {x -= 1;}
    }
    else {System.out.println("You entered an invalid direction!");}
  }
  public static void intro(Scanner input) {
    System.out.println("You are advised to make your terminal window as big as possible before playing. Press enter to continue.\n");
    input.nextLine();
    System.out.println("Welcome to William Batts III's (not so) Mini-Venture!");
    System.out.println("The goal of this game is to find all 5 of the hidden keys!");
    System.out.println("Enter 'east,' 'west,' 'north,' or 'south' to travel in that direction.\nEnter 'search' to look for a key in a room.\n\n");
  }
}
