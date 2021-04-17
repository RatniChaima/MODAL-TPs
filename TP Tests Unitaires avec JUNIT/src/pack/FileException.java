package pack;


import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileException {
	
	public void saveFile(Exception e) {
		try {
		//create file
		File f = new File("D:\\FileException.txt");
		//get exception information
		Date d = new Date();
		int line = e.getStackTrace()[0].getLineNumber();
		String packname = e.getStackTrace()[0].getClass().getPackage().getName();
		String cl = e.getStackTrace()[0].getClassName();
		String meth = e.getStackTrace()[0].getMethodName();
		
		//create new buffer
		    BufferedWriter bw = null;
			bw = new BufferedWriter(new FileWriter(f,true));
			bw.newLine();
	
		//save the information in buffer
		
		String str = d+"\t"+packname+"\t"+cl+"\t"+meth+"\t"+line+"\t"+e.toString();
	
			bw.write(str);
			bw.close();
		
		
		}
		catch(FileNotFoundException e1)
				{
					//System.out.println("Le fichier d'exception n'existe pas.");
					Date d = new Date();
					int line = e1.getStackTrace()[0].getLineNumber();
					String packname = e.getStackTrace()[0].getClass().getPackage().getName();
					String cl = e1.getStackTrace()[0].getClassName();
					String meth = e1.getStackTrace()[0].getMethodName();
					System.out.println(d+"\t"+packname+"\t"+cl+"\t"+meth+"\t"+line+"\t"+e1.toString());
					}
				catch(IOException e1)
				{
					//System.out.println("Problème d'entrée sortie dans le fichier d'exception.");
					Date d = new Date();
					int line = e1.getStackTrace()[0].getLineNumber();
					String packname = e.getStackTrace()[0].getClass().getPackage().getName();
					String cl = e1.getStackTrace()[0].getClassName();
					String meth = e1.getStackTrace()[0].getMethodName();
					System.out.println(d+"\t"+packname+"\t"+cl+"\t"+meth+"\t"+line+"\t"+e1.toString());
					}
				
				catch(Exception e1)
				{
					Date d = new Date();
					int line = e1.getStackTrace()[0].getLineNumber();
					String packname = e.getStackTrace()[0].getClass().getPackage().getName();
					String cl = e1.getStackTrace()[0].getClassName();
					String meth = e1.getStackTrace()[0].getMethodName();
					System.out.println(d+"\t"+packname+"\t"+cl+"\t"+meth+"\t"+line+"\t"+e1.toString());
					}

}
	public void saveFileXML(Exception e) {
		try {
		//create file
		File f = new File("D:\\FileXMLException.xml");
		//get exception information
		Date d = new Date();
		int line = e.getStackTrace()[0].getLineNumber();
		String packname = e.getStackTrace()[0].getClass().getPackage().getName();
		String classname = e.getStackTrace()[0].getClassName();
		String meth = e.getStackTrace()[0].getMethodName();
		
		//create new buffer
			BufferedWriter bw = null;
			bw = new BufferedWriter(new FileWriter(f,true));
			bw.newLine();
	
			bw.write("<Exceptions>");
            bw.newLine();
            bw.write("<Exception>");
            bw.newLine();
            bw.write("<Date>"+d);
            bw.write("</Date>");
            bw.newLine();
            bw.write("<ClassPackage>"+packname);
            bw.write("</ClassPackage>");
            bw.newLine();
            bw.write("<ClassName>"+classname);
            bw.write("</ClassName>");
            bw.newLine();
            bw.write("<MethodName>"+meth);
            bw.write("</MethodName>");
            bw.newLine();
            bw.write("<LigneNumber>"+line);
            bw.write("</LigneNumber>");
            bw.newLine();
            bw.write("</Exception>");
            bw.newLine();
            bw.write("</Exceptions>");
            bw.close();
		
		
		}
		catch(FileNotFoundException e1)
				{
					//System.out.println("Le fichier d'exception n'existe pas.");+
			Date d = new Date();
			int line = e1.getStackTrace()[0].getLineNumber();
			String packname = e.getStackTrace()[0].getClass().getPackage().getName();
			String cl = e1.getStackTrace()[0].getClassName();
			String meth = e1.getStackTrace()[0].getMethodName();
			System.out.println(d+"\t"+packname+"\t"+cl+"\t"+meth+"\t"+line+"\t"+e1.toString());
			}
			
		
		
				
				catch(IOException e1)
				{
					//System.out.println("Problème d'entrée sortie dans le fichier d'exception.");
					Date d = new Date();
					int line = e1.getStackTrace()[0].getLineNumber();
					String packname = e.getStackTrace()[0].getClass().getPackage().getName();
					String cl = e1.getStackTrace()[0].getClassName();
					String meth = e1.getStackTrace()[0].getMethodName();
					System.out.println(d+"\t"+packname+"\t"+cl+"\t"+meth+"\t"+line+"\t"+e1.toString());
					}
				
				catch(Exception e1)
				{
					Date d = new Date();
					int line = e1.getStackTrace()[0].getLineNumber();
					String packname = e.getStackTrace()[0].getClass().getPackage().getName();
					String cl = e1.getStackTrace()[0].getClassName();
					String meth = e1.getStackTrace()[0].getMethodName();
					System.out.println(d+"\t"+packname+"\t"+cl+"\t"+meth+"\t"+line+"\t"+e1.toString());
				}
	}
}

