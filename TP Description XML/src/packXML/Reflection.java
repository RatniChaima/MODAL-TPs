package packXML;

import java.lang.reflect.*;
import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.lang.reflect.Method;

public class Reflection
{
    public static void main(String[] args) throws IOException{

		FichException ex = new FichException();
    	try { 
    	
                File pathToJar = new File("D:/Eclipse/MyProject.jar");
                JarFile jarFile;
                jarFile = new JarFile(pathToJar);
                Enumeration<JarEntry> e = jarFile.entries();

                URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
                URLClassLoader cl = URLClassLoader.newInstance(urls);
                
    		 	BufferedWriter bw = null;
    			bw = new BufferedWriter(new FileWriter("D:\\FileXML.xml",true));
    			
    			
                String project = pathToJar.getName();
                int pos = project.lastIndexOf(".");
                if (pos > 0 && pos < (project.length() - 1)) { // si '.' n'est pas le premier ou dernier caractere
                	project = project.substring(0, pos); // pour enlever l'extention
                }
                
                /*
                   J'ai choisi un seul fichier XML qui représente toutes les classes du projet.
                 * Justification:
					XML est utilisé pour modéliser structurer et stocker des données afin qu'elles 
					soient lisibles aussi bien par les humains et les programmes. 
					Donc pour une structure bien formée lisible et facile à manipuler, j'ai choisi 
					qu'on fait toutes les classes dans un seul fichier XML qui contient le projet avec 
					ses classes , où la racine est notre projet et les nœuds enfants sont les classes.

                 */
                
                bw.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                bw.newLine();
                bw.write("<Project name=\""+ project +"\">");
                bw.newLine();
                bw.write("<Info_Project>");
                bw.newLine();
                int nbClass=0;
                while (e.hasMoreElements()) {
                    JarEntry je = e.nextElement();
                    if(je.isDirectory() || !je.getName().endsWith(".class"))
                    	continue; nbClass++;
                }
                bw.write("<Classes nbr=\""+nbClass+"\"/> ");
                bw.newLine();
                bw.write("<Java Version=\""+System.getProperty("java.version")+"\"/>");
                bw.newLine();
                bw.write("<OS name=\""+System.getProperty("os.name")+"\"/>"); 
                bw.newLine();
                Date d=new Date();
	    		SimpleDateFormat df=new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
	    		bw.write("<Date>");
	    		bw.write(df.format(d));
	    		bw.write("</Date>");
	            bw.newLine();
                bw.write("</Info_Project>");
                bw.newLine();
	            bw.write("<Info_Classes>");
                
                Enumeration<JarEntry> e1 = jarFile.entries();
               int cpt=0;
                while (e1.hasMoreElements()) {
                    JarEntry je = e1.nextElement();
                    if(je.isDirectory() || !je.getName().endsWith(".class")){
                        continue;
                    } cpt++;
                    // -6 pour .class
                    String className = je.getName().substring(0,je.getName().length()-6);
                    className = className.replace('/', '.');
                    Class c = cl.loadClass(className);
                  
                    Package pack = c.getPackage();
                    String packageName = pack.getName();
                    bw.newLine();
                    bw.write("<Class name=\""+c.getSimpleName()+"\">");
                    bw.newLine();
                    bw.write("<Package name=\""+packageName+"\"/>");
                    bw.newLine();
                    //les infos des attributs
                    int Attcount=0;
                    Field[] f = c.getFields();
                    String s="";
                    for (int i = 0; i < f.length;i++)
                     {	Field field = f[i];
                        Attcount = Attcount + 1;
                        s=s+field.getName()+" - "; 
                      }
                    bw.write("<Attributes>");
                 	bw.newLine();
                    bw.write("<Public_Attributes nbr=\""+Attcount+"\">");
                    bw.newLine();
                    bw.write("<List>");
                    if (s.length()>0) {
	                	 s = s.substring(0, s.length()-3);//pour enlever la derniere "-"
	                 	} 
                   
                    bw.write(s);
                    bw.write("</List>");
                    bw.newLine();
                    bw.write("<Type_Attributes>");
	                bw.newLine();
                    for (int i = 0; i < f.length;i++)
                    {	Field field = f[i];
                    bw.write("<Attribute name=\""+field.getName()+"\""+" type= \""+(Object)field.getType().getSimpleName()+"\">");
                    bw.write("</Attribute>");
                    bw.newLine();
                     }
                    bw.write("</Type_Attributes>");
                    bw.newLine();
                    bw.write("</Public_Attributes>");
                    bw.newLine();
                    int AttcountPrv=0;
                    String s1="";
                    Field[] f1 = c.getDeclaredFields();
                    
                    for (int i = 0; i < f.length;i++)
                     { Field field = f1[i];
                         if(field.getModifiers()==2)//getModifiers() retourne 2 si field est private , 1 si public
                         {
                        	 s1=s1+field.getName()+" - "; 
                        	 AttcountPrv = AttcountPrv + 1;}
                         }
                    bw.write("<Private_Attributes nbr=\""+AttcountPrv+"\">");
                    bw.newLine();
                    bw.write("<List>");
                   
                    if (s1.length()>0) {
	                	 s1 = s1.substring(0, s1.length()-3);//pour enlever la derniere "-"
	                 	}
                    bw.write(s1);
                    bw.write("</List>");
                    bw.newLine();
                    bw.write("<Type_Attributes>");
	                bw.newLine();
                    for (int i = 0; i < f.length;i++)
                    { Field field = f1[i];
                        if(field.getModifiers()==2)//getModifiers() retourne 2 si field est private , 1 si public
                        {
                        	 bw.write("<Attribute name=\""+field.getName()+"\""+" type= \""+(Object)field.getType().getSimpleName()+"\">");
                             bw.write("</Attribute>");
                             bw.newLine();
                       	 }
                        }
                    bw.write("</Type_Attributes>");
                    bw.newLine();
                    bw.write("</Private_Attributes>");
                    bw.newLine();
                    bw.write("</Attributes>");
                 	bw.newLine();
                     //liste des  constructeurs          
                 
                 	bw.write("<Constructors>");
                 	 for (Constructor constructeur : c.getDeclaredConstructors()) {
	                 	 Parameter[] temp = constructeur.getParameters();
	                 	bw.write("<Constructor name=\""+constructeur.getName()+"\">");
	                 	bw.newLine();
	                 	bw.write("<Parameters>");
	                 	bw.newLine();
	                 	       for (Parameter parameter : temp) {
	                 	    	  
	                 	    	  bw.write("<Parameter name= \""+parameter.getName()+"\""+" type= \""+parameter.getType().getSimpleName()+"\"/>");
	                 	    	  bw.newLine();
	                 	           }
	                 	      bw.write("</Parameters>");
	                 	      bw.newLine();
	                 	      bw.write("</Constructor>");
		     	                 bw.newLine();
		     	                 }
	                 	
	               
	                 bw.write("</Constructors>");
                     bw.newLine();

	                //les infos des methodes
			        int Mcount=0;
			        String s2="";
			        Method methlist[]= c.getDeclaredMethods();
	               
                    for (int i = 0; i < methlist.length;i++)
	                    {  
	                          Method methode = methlist[i];
	                          Mcount = Mcount + 1;
	                          s2=s2+methode.getName()+" - "; 
	                    }
                    
                     bw.write("<Methods nbr=\""+ Mcount+"\">");
	                 bw.newLine();
	                 bw.write("<List>");
	                
	                 if (s2.length()>0) {
	                	 s2 = s2.substring(0, s2.length()-3);//pour enlever la derniere "-"
	                 	}
	                 bw.write(s2);
	                 bw.write("</List>");
	                 bw.newLine();
	              
	                 
	               
	                 for (Method m : c.getDeclaredMethods()) {
	                 	 Parameter[] temp = m.getParameters();
	                 	bw.write("<Method name=\""+m.getName()+"\" ReturnType= \""+m.getReturnType().getSimpleName()+"\">");
	                 	bw.newLine();
	                 	bw.write("<Parameters>");
	                 	bw.newLine();
	                 	       for (Parameter parameter : temp) {
	                 	    	  
	                 	    	  bw.write("<Parameter name= \""+parameter.getName()+"\""+" type= \""+parameter.getType().getSimpleName()+"\"/>");
	                 	    	  bw.newLine();
	                 	           }
	                 	      bw.write("</Parameters>");
	                 	      bw.newLine();
	                 	      bw.write("</Method>");
		     	              bw.newLine();
		     	     }
	                 	
	               
	                 bw.write("</Methods>");
	                 bw.newLine();
	                 bw.write("</Class>"); 
                } 
                bw.newLine();
                bw.write("</Info_Classes>");
                bw.newLine();
                bw.write("</Project>");  
                bw.close();
               
            } 
          
    	 	catch (Exception e) { 
    	 		ex.saveFile(e);
         	}
            
           
} 
}


