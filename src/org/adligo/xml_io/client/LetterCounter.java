package org.adligo.xml_io.client;

public class LetterCounter {
	public static final String letters = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWYXZ";
	
	private String lastId;
	
	public synchronized String getNextId() {
		if (lastId == null) {
			lastId = "a";
		} else {
			char [] chars = lastId.toCharArray();
			boolean allZ = true;
			for (int i = 0; i < chars.length; i++) {
				char c = chars[i];
				if (c != 'Z') {
					allZ = false;
					break;
				}
			}
			if (allZ) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i <= chars.length; i++) {
					sb.append("a");
				}
				lastId = sb.toString();
			} else {
				
				int digitToChange = findDigitToChange(chars);
				if (digitToChange == 0) {
					char lastChar = chars[chars.length - 1];
					chars[chars.length - 1] = getNextChar(lastChar);
				} else {
					chars[chars.length - 1] = 'a';
					int index = chars.length -1 -digitToChange;
					char current = chars[index];
					char next = getNextChar(current);
					chars[index] = next;
				}
				lastId = new String(chars);
			}
		}
		//System.out.println(lastId);
		return lastId;
	}
	
	int findDigitToChange(char [] chars) {
		int counter = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			char c = chars[i];
			if (c != 'Z') {
				return counter;
			}
			counter ++;
		}
		return counter - 1;
	}
	
	private char getNextChar(char c) {
		if (c == 'Z') {
			return 'a';
		}
		int index = letters.indexOf(c);
		return letters.charAt(index + 1);
	}
}
