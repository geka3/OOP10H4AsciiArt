package net.ukr.geka3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Character, ArrayList<String>> letter = new HashMap<>();
		int maxRows;
		maxRows = getLetters("asciiart.txt", letter);
		
		showText("HelloWord", letter);

	}

	public static void showText(String text,HashMap<Character, ArrayList<String>> letter) {
		text = text.toUpperCase();
		StringBuilder sb = new StringBuilder();
		//exit from cycle is realised by Exception
		for (int i = 0;; i++) {
			for (char ch : text.toCharArray()) {

				ArrayList<String> ALtemp = letter.get(ch);

				try {
					sb.append(ALtemp.get(i)).append("   ");
				} catch (IndexOutOfBoundsException e) {
					System.out.println(sb.toString());
					return;

				}

			}
			sb.append(System.lineSeparator());
		}

	}
	//for testing
	public static void showLetters(HashMap<Character, ArrayList<String>> letter) {
		for (Character aChar : letter.keySet()) {
			System.out.println(aChar);
			for (String str : letter.get(aChar)) {
				System.out.println(str);
			}
		}
	}
	//get letters from file retern Max num of rows
	public static int getLetters(String addres,
			HashMap<Character, ArrayList<String>> letter) {
		File file = new File(addres);
		if (!file.isFile()) {
			System.out.println("there is not file");
			return 0;
		}
		int numRow = 0;
		try (BufferedReader bf = new BufferedReader(new FileReader(addres))) {
			String temp;
			char ch = ' ';
			char prevCh = ' ';
			ArrayList<String> arrayLetter = new ArrayList<String>();
			int i = 0;

			for (; (temp = bf.readLine()) != null;) {
				i++;
				ch = temp.trim().charAt(0);
				if (prevCh != ch) {

					arrayLetter = new ArrayList<String>();
					letter.put(ch, arrayLetter);
					prevCh = ch;

					numRow = (i > numRow ? i : numRow);
					i = 0;

				}
				arrayLetter.add(temp);
			}

		} catch (IOException e) {
			System.out.println(e);
		}

		return numRow;
	}

}
