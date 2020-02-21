package assassins;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		Random rand = new Random();

		System.out.println("How many people are playing?");
		int people = scan.nextInt();

		ArrayList names = new ArrayList();

		for (int i = 0; i < people; i++) {
			System.out.println("What is the name of the next player?");
			names.add(scan.next());
		}

		LinkedList<String> loop = new LinkedList<String>();

		while (names.size() > 0) {
			int random = rand.nextInt(names.size());
			loop.add((String) names.get(random));
			names.remove(random);
		}
		System.out.println(loop);
		for (int i = 0; i < loop.size(); i++) {
			if (i == loop.size() - 1) {
				System.out.println(loop.get(i) + "'s target is " + loop.get(0));
			} else {
				System.out.println(loop.get(i) + "'s target is " + loop.get(i + 1));
			}

		}
	}

}
