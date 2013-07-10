package com.example.objects;

import java.util.Random;

public class NamesAndMessages {

	public static String randomName() {

		Random r = new Random();

		switch (r.nextInt(20)) {
		case (0):
			return "Walter";
		case (1):
			return "Fred";
		case (2):
			return "Albert";
		case (3):
			return "Harry";
		case (4):
			return "Frank";
		case (5):
			return "Charles";
		case (6):
			return "Clarence";
		case (7):
			return "William";
		case (8):
			return "Louis";
		case (9):
			return "Joe";
		case (10):
			return "Ida";
		case (11):
			return "Minnie";
		case (12):
			return "Margaret";
		case (13):
			return "Mary";
		case (14):
			return "Ethel";
		case (15):
			return "Florence";
		case (16):
			return "Nellie";
		case (17):
			return "Laura";
		case (18):
			return "Carrie";
		case (19):
			return "Maude";
		}

		return "pff";

	}

	public static String randomMessage() {

		Random r = new Random();

		switch (r.nextInt(20)) {
		case (0):
			return "If ignorance is bliss, you must be the happiest person alive.  ";
		case (1):
			return "I've taken dumps more impressive than you. ";
		case (2):
			return "A chop shop wouldn't take your ugly ass.";
		case (3):
			return "	Your mom must have really pissed off God. ";
		case (4):
			return "	You couldn't run a microwave. ";
		case (5):
			return " My mom apologizes for beating you up last night.";
		case (6):
			return "Penumbra would call a swat team on your ugly ass. ";
		case (7):
			return "You're an insult to the profession. Any profession. ";
		case (8):
			return "Sheeesh. ";
		case (9):
			return "	I feel great pity towards your parents. ";
		case (10):
			return "	Do you trip over cordless phones, too? ";
		case (11):
			return "	Still beta-testing that neural software? ";
		case (12):
			return "Nothing wrong with you that a nice cement enema couldn't fix. ";
		case (13):
			return "	Sleeping a little too close to your radium-dial watch? ";
		case (14):
			return "	Living proof that God has a sense of humor. ";
		case (15):
			return "	You're as bright as a solar-powered flashlight. ";
		case (16):
			return "You really ought to come with a warning label on your forehead. ";
		case (17):
			return " Living proof of de-evolution.";
		case (18):
			return "Proof of Einstein's theory that 'there's no limit to human stupidity.' ";
		case (19):
			return "If you were any dumber you'd be a green plant. ";
		}

		return "pff";

	}

}
