package assassins;

import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;


public class Main {

    public static int people = 0;
    public static LinkedList<String> playerList = new LinkedList<>();
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList names = new ArrayList();
    public static Method mtd = new Method();

    public static void main(String[] args) throws IOException {

        startMenu();


    }

    public static void startMenu() throws IOException {
        System.out.println("What would you like to do?");
        System.out.println("New Game: 1");
        System.out.println("Load Game: 2");
        System.out.println("Exit Game: 3");

        String answer = scan.nextLine();

        switch (answer) {

            case "1":
                System.out.println("Are you sure you want to start a new game? y/n");
                if (scan.nextLine().equalsIgnoreCase("y")) newGame();
                else {
                    startMenu();
                }
                break;

            case "2":
                loadGame();
                break;

            case "3":
                System.out.println("Thanks for playing!");
                System.exit(0);
                break;

            default:
                System.out.println("Please enter a valid option");
                startMenu();
                break;

        }

    }

    public static void newGame() throws IOException {
        scan = new Scanner(System.in);
        System.out.print("How many people are playing?: ");
        people = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < people; i++) {
            System.out.println("What is the name of the next player?");
            names.add(scan.nextLine());
        }

        while (names.size() > 0) {
            int random = (int) (Math.random() * (names.size()));
            playerList.add((String) names.get(random));
            names.remove(random);
        }

        whatNext("newGame");

    }


    public static void loadGame() throws IOException {

        File file = new File("data.txt");

        if (!file.exists() || file.length() == 0) {
            System.out.println("You never started a game. Would you like to start one? y/n");
            String answer = scan.nextLine();
            answer.toLowerCase();
            if ("y".equals(answer)) {
                newGame();
            } else {
                System.out.println("Come again!");
                System.exit(0);
            }
        } else {
            mtd.read(playerList, file);
            whatNext("loadGame");

        }
    }

    public static void whatNext(String state) throws IOException {
        System.out.println("What do you want to do now?");

        if (state.equals("newGame")) {
            System.out.println("Add another player: 1" +
                    "\nRemove player: 2" +
                    "\nPrint current game: 3" +
                    "\nDelete current game: 4" +
                    "\nFind a player's target: 5" +
                    "\nExit and save game: 6");
            String answer = scan.nextLine();

            switch (answer) {

                case "1":
                    System.out.println("How many people will you add?");
                    people = scan.nextInt();
                    scan.nextLine();

                    for (int i = 0; i < people; i++) {
                        System.out.println("What is the name of the next player?");
                        names.add(scan.nextLine());
                    }

                    while (names.size() > 0) {
                        int random = (int) (Math.random() * (names.size()));
                        playerList.add((String) names.get(random));
                        names.remove(random);
                    }
                    whatNext("newGame");
                    break;

                case "2":
                    System.out.println("Who has died?");
                    String name = scan.nextLine();
                    mtd.remove(name, playerList);
                    whatNext("newGame");
                    break;

                case "3":
                    mtd.printPlayerList(playerList);
                    whatNext("newGame");
                    break;

                case "4":
                    boolean valid = false;
                    while (!valid) {
                        System.out.println("Are you sure you want to delete the current game? y/n");
                        String answer2 = scan.nextLine();
                        if (answer2.equalsIgnoreCase("y")) {
                            valid = true;
                            System.out.println("Okay done! You have deleted the current game.");
                            playerList.clear();
                            mtd.write(playerList);
                        } else if (answer2.equalsIgnoreCase("n")) {
                            valid = true;
                            whatNext("newGame");

                        } else {
                            System.out.println("Please enter a valid option");

                        }
                    }

                    break;

                case "5":
                    System.out.println("Who's target are you looking for? (Enter the exact name in the game)");
                    String answer3 = scan.nextLine();
                    mtd.findTarget(playerList, answer3);
                    whatNext("newGame");
                    break;

                case "6":
                    System.out.println("Come again!");
                    mtd.write(playerList);
                    System.exit(0);
                    break;

                default:
                    System.out.println("Please enter a valid option");
                    whatNext("newGame");
                    break;
            }

        } else {
            System.out.println("Remove player: 2" +
                    "\nPrint current game: 3" +
                    "\nDelete current game: 4" +
                    "\nFind a player's target: 5" +
                    "\nExit and save game: 6");
            String answer = scan.nextLine();

            switch (answer) {
                case "2":
                    System.out.println("Who has died?");
                    String name = scan.nextLine();
                    mtd.remove(name, playerList);
                    whatNext("loadGame");
                    break;

                case "3":
                    mtd.printPlayerList(playerList);
                    whatNext("loadGame");
                    break;

                case "4":
                    boolean valid = false;
                    while (!valid) {
                        System.out.println("Are you sure you want to delete the current game? y/n");
                        String answer2 = scan.nextLine();
                        if (answer2.equalsIgnoreCase("y")) {
                            valid = true;
                            System.out.println("Okay done! You have deleted the current game.");
                            playerList.clear();
                            mtd.write(playerList);
                        } else if (answer2.equalsIgnoreCase("n")) {
                            valid = true;
                            whatNext("loadGame");

                        } else {
                            System.out.println("Please enter a valid option");

                        }
                    }


                    break;

                case "5":
                    System.out.println("Who's target are you looking for? (Enter the exact name in the game)");
                    String answer3 = scan.nextLine();
                    mtd.findTarget(playerList, answer3);
                    whatNext("loadGame");
                    break;

                case "6":
                    System.out.println("Come again!");
                    mtd.write(playerList);
                    System.exit(0);
                    break;

                default:
                    System.out.println("Please enter a valid option");
                    whatNext("loadGame");
                    break;
            }
        }
    }


}

