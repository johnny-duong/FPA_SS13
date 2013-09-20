package de.bht.fpa.mail.s769183.fsnavigation.handlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFileHandler {

	private FileWriter wBaseDir;
	private FileWriter wHistory;
	private BufferedReader rBaseDir;
	private Scanner rHistory;

	public void savePath(String path) {
		try {
			wBaseDir = new FileWriter("baseDir.sf");
			wHistory = new FileWriter("history.sf", true);
			wBaseDir.write(path);
			wHistory.write(path + System.getProperty("line.separator"));
			wBaseDir.close();
			wHistory.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public String loadBaseDir() {
		String dirPath = "";
		try {
			rBaseDir = new BufferedReader(new FileReader("baseDir.sf"));
			dirPath = rBaseDir.readLine();
			rBaseDir.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dirPath;
	}

	public ArrayList<String> loadHistory() {
		ArrayList<String> history = new ArrayList<String>();
		try {
			rHistory = new Scanner(new FileReader("history.sf"));
			while (rHistory.hasNext()) {
				history.add(rHistory.next());
			}
			rHistory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return history;
	}
}
