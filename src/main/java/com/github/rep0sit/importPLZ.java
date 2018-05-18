package com.github.rep0sit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class importPLZ {
	private importPLZ() {}
	private static final String FILE = "plz.data";
	
	private static List<String> params = new ArrayList<>();
	
	public static void importFile(){
		try(BufferedReader bf = new BufferedReader(new FileReader(FILE))){
			try {
				String line = bf.readLine();
				Scanner scanner = new Scanner(line);
			} catch (IOException e) {e.printStackTrace();}
			
		} 
		catch (FileNotFoundException e1) {e1.printStackTrace();} 
		catch (IOException e1) {e1.printStackTrace();}
	}
}
