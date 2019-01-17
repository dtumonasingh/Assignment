package com.database;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.xml.sax.SAXException;

public class CompareXMLFiles {
	public static void compareXMLFiles(String file1, String file2) throws
	SAXException, IOException{
		String first = "", second = "";
		Scanner input1 = new Scanner(new File(file1));			//read first file
		Scanner input2 = new Scanner(new File(file2));			//read second file
		
		int flag=0;
		
		System.out.println("\n\"*************************************************\nDifferences are as follows \n"
				);
		
		while (input1.hasNextLine()||input2.hasNextLine()) {
			if(input1.hasNextLine()) {
				first = input1.nextLine();
				}
			if(input2.hasNextLine()) {
				second = input2.nextLine();
				}
			if (!first.equals(second)) {
				flag++;
				System.out.println(second);
			}
			}
		
		
		if(flag==0){
	        	System.out.println("No Differences Found");
		}
		
		
		input1.close();
		input2.close();
	}
}
