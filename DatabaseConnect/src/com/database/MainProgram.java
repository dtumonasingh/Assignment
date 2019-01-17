//Advanced Java Assignment

package com.database;

import java.sql.*;
import java.util.ArrayList;
public class MainProgram{
public static void main(String args[]) {
        try{  
        	
        	String dbDriver = "com.mysql.cj.jdbc.Driver";      //Creating the connection
            String dbURL = "jdbc:mysql://sql12.freemysqlhosting.net/"; 
            String dbName ="sql12273536"; 
            String dbUsername = "sql12273536"; 
            String dbPassword = "accolite"; 
            Class.forName(dbDriver); 
            Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);   
            Statement stmt = con.createStatement();
            
            
            ArrayList<String> list = new ArrayList<>();     //Retrieving the columns of the table
            ResultSet rs = stmt.executeQuery("SHOW FIELDS FROM emp");
            while(rs.next()) {
            	list.add(rs.getString(1));
            }
            
            
            String file1="Employee.xml";     				 //Writing it to the XML file
            WriteToXML.writeToXML(stmt,rs,list,file1);
            
            
            String queryInsert;             				//Inserting one new row in the table
            queryInsert="INSERT INTO emp (";
            for(int i=0;i<list.size();i++)
            {
            	 queryInsert+=list.get(i)+",";
            	 
            }
            queryInsert = queryInsert.substring(0, queryInsert.length() - 1);
            queryInsert+=") VALUES(1023,'Mona Singh','Developer',123,'12-12-12',78,67,344);";
            stmt.executeUpdate(queryInsert);
            
            
            String file2="EmployeeNew.xml";          		 //Writing the updated table to the new XML file
            WriteToXML.writeToXML(stmt,rs,list,file2);
            con.close();
            CompareXMLFiles.compareXMLFiles(file1,file2);
            }
        catch(Exception e){ 
        	System.out.println(e);
           }           
    }
}
