package assassins;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;


public class Main {

    public static int people = 0;
    public static LinkedList<String> playerList = new LinkedList<String>();
    public static Scanner scan;
    public static ArrayList names = new ArrayList();

    public static void main(String[] args) {

        startMenu();

        printPlayerList();

        System.out.println("Who has died?");
        String name = scan.nextLine();
        remove(name);

        if ( playerList.size() == 1){
        	System.out.println(playerList.get(0) + " is the winner!");
		}
    }

    public static void startMenu() {
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
            System.out.println("random" + random);
            playerList.add((String) names.get(random));
            names.remove(random);
        }
    }

    public static void printPlayerList() {
        System.out.println(playerList);
        for (int i = 0; i < playerList.size(); i++) {
            if (i == playerList.size() - 1) {
                System.out.println(playerList.get(i) + "'s target is " + playerList.get(0));
            } else {
                System.out.println(playerList.get(i) + "'s target is " + playerList.get(i + 1));
            }
        }
    }

    public static void remove(String name) {

    	String answer = "";
        while (!playerList.contains(name)) {
            System.out.println("Player not found. Input a current player.");
            name = scan.nextLine();
        }
        int index = playerList.indexOf(name);
        playerList.remove(index);
		if ( playerList.size() == 1){
			return;
		}
        printPlayerList();

        System.out.println("Did someone else die?: Y/N");
        answer = scan.nextLine();
        while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
        	System.out.println ("Please provide a valid input");
        	answer = scan.nextLine();
		}
        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("Who has died?");
            name = scan.nextLine();
            remove(name);
        }
    }


}
