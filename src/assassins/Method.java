package assassins;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Paths;
public class Method {

    private static Scanner scan;


    public void remove(String name, List playerList) throws IOException {
        scan = new Scanner(System.in);
        String answer;
        while (!playerList.contains(name)) {
            System.out.println("Player not found. Input a current player.");
            name = scan.nextLine();
        }
        playerList.remove(name);
        if (playerList.size() == 1) {
            return;
        }
        printPlayerList(playerList);

        System.out.println("Did someone else die?: Y/N");
        answer = scan.nextLine();
        while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
            System.out.println("Please provide a valid input");
            answer = scan.nextLine();
        }
        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("Who has died?");
            name = scan.nextLine();
            remove(name, playerList);
        }
        if (winnerFound(playerList)) {
            System.out.println("The winner is " + playerList.get(0));
            playerList.clear();
            write(playerList);
            System.out.println("Game Over");
            System.exit(0);
        }
    }

    public void write(List<String> list) throws IOException {
        Path file = Paths.get("data.txt");
        Files.write(file, list);
    }

    public void printPlayerList(List playerList) {
        System.out.println("----------------------------\n" +playerList);
        for (int i = 0; i < playerList.size(); i++) {
            if (i == playerList.size() - 1) {
                System.out.println(playerList.get(i) + "'s target is " + playerList.get(0));
            } else {
                System.out.println(playerList.get(i) + "'s target is " + playerList.get(i + 1));
            }
        }
        System.out.println("----------------------------");
    }

    public void read(List playerList, File f) throws IOException {
        scan = new Scanner(f);

        while(scan.hasNextLine()){
            playerList.add(scan.nextLine());
        }
    }

    public boolean winnerFound(List playerList) {
        return playerList.size() == 1;
    }

    public void findTarget(List playerList, String chaserName){
        String targetName;
        int chaserNameIndex = playerList.indexOf(chaserName);
        int targetNameIndex = chaserNameIndex + 1;

        if(targetNameIndex == playerList.size()){
            targetNameIndex = 0;
        }

        targetName = (String) playerList.get(targetNameIndex);


        System.out.println(chaserName + "'s target is " + targetName + "\n------------------------");

    }

}
