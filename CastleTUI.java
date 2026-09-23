import java.util.Scanner;

public class CastleTUI {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String castleName = input.nextLine();
        int numberOfCaves = input.nextInt();

        CoralCastle castle = new CoralCastle(castleName, numberOfCaves);
        System.out.println("Castle " + castleName + " created with " + numberOfCaves + " caves. Type 'help' for commands.");

        String command;
        boolean closeLoop = false;
        while(!closeLoop){
            command = input.next();
            String name;
            switch (command) {
                case "in":
                    name = input.next();
                    int size = input.nextInt();
                    Cave cave = castle.checkIn(name, size);
                    if (cave != null){
                        System.out.println("Guest " + name + " gets cave " + cave.getNumber() + '.');
                    } else {
                        System.out.println("No suitable cave available for " + name + '.');
                    }
                    break;

                case "out":
                    name = input.next();
                    if (castle.checkOut(name)){
                        System.out.println(name + " has checked out.");
                    } else {
                        System.out.println("Guest " + name + " is not in the castle.");
                    }
                    break;

                case "cave":
                    name = input.next();
                    if (castle.getCaveByGuestName(name) != null){
                        System.out.println("Guest " + name + " is in cave " + castle.getCaveByGuestName(name).getNumber() + '.');
                    } else {
                        System.out.println("Guest " + name + " doesn't have a cave.");
                    }
                    break;

                case "print":
                    System.out.println(castle);
                    break;
                case "help":
                    System.out.println("Commands:");
                    System.out.println("in [name] [size] - Check in a guest with the given name and size");
                    System.out.println("out [name] - Check out the guest with the given name");
                    System.out.println("cave [name] - Show the cave number of the guest with the name");
                    System.out.println("print - Print the current state of the castle");
                    System.out.println("help - Show this help menu");
                    System.out.println("exit - Exit the program");
                    break;
                case "exit":
                    System.out.println("Closing the system.");
                    closeLoop = true;
                    break;

                default:
                    break;
            }
        }

        input.close();
    }

}
