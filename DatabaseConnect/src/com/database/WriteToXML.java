package com.database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class WriteToXML {
	public static void writeToXML(Statement stmt, ResultSet rs,  ArrayList<String> list,String file) {
		try {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("Accolite");
        document.appendChild(root);
        
        rs=stmt.executeQuery("SELECT *FROM emp");
        int k=0;
        int Count = list.size();
        while(rs.next()) {
        	Element employee = document.createElement("employee");
            root.appendChild(employee);
            k=0;
        	for(int i = 1 ; i <= Count; i++){
        		System.out.print(rs.getString(i)+" ");
        		
        		String name=list.get(k);
        		k++;
        		Element x = document.createElement(name);
        		x.appendChild(document.createTextNode(rs.getString(i)));
        		employee.appendChild(x);
        		}
        	System.out.println();
        	}
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(file));
        transformer.transform(domSource, streamResult);

        System.out.println("\nDone creating XML the File "+file);
		}
		catch(Exception e) {
			
		}
	}

}