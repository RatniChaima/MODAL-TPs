package pack;

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
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.lang.reflect.Method;
import org.junit.*;
import org.junit.Test;
//import pack.RandomGenerator;
import static org.junit.Assert.*;
import java.util.Random;

public class Reflection
{
    public static void main(String[] args) {

		FileException ex = new FileException();
    	try{
    		
    		 File pathToJar = new File("D:/EclipseP/MyProject.jar");

             JarFile jarFile;
             jarFile = new JarFile(pathToJar);
             Enumeration<JarEntry> en = jarFile.entries();

             URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
             URLClassLoader cl = URLClassLoader.newInstance(urls);
             
            
    	 System.out.println("*******************************************************************************\n");
		    System.out.println(" Universite de la science et de la technologie HOUARI BOUMEDIENNE 2019/2020     ");
		    System.out.println("                           MASTER 1 : IL                        ");
		    System.out.println("             Module : MODAL - Reflexion , XML , Tests JUnit       ");
		    System.out.println("                        Réalisée par: RATNI Chaima                       \n");
		    System.out.println("********************************************************************************\n");
		    int choix ;
			System.out.println("\n---------------------------Veuillez choisir une option---------------------------\n ");
		    Scanner sc = new Scanner(System.in);
		  do{ 
			System.out.println("\n1- Générer la description textuelle du projet.");
			System.out.println("2- Générer la description XML du projet.");
			System.out.println("3- Générer les classes et les méthodes de tests de projet.");
		
			System.out.println("4- Quitter");
			System.out.print("\nVotre choix :\n ");
			choix=Integer.parseInt(sc.nextLine());

			 switch (choix) {
			 
			 case 1 : 
		    	try { 
		    		
	    		 	BufferedWriter bw = null;
	    			bw = new BufferedWriter(new FileWriter("D:\\FileReflection.txt",true));
	    				
    				bw.write("_____________________________________________________________________________________________________________________________________________________________");
    				bw.newLine();
    				bw.newLine();
    	         
    	            bw.write("Description textuelle -- Date : " );
    	    		Date d=new Date();
    	    		SimpleDateFormat df=new SimpleDateFormat( "dd MMMM yyyy", Locale.FRANCE);
    	    		bw.write(df.format(d));
    	            bw.newLine();
    				bw.write("_____________________________________________________________________________________________________________________________________________________________");
    				bw.newLine();bw.newLine();
    	            bw.write("Information projet ");bw.newLine();
    	        	bw.write("_____________________________________________________________________________________________________________________________________________________________");
    	        	bw.newLine();bw.newLine();
                String project = pathToJar.getName();
                int pos = project.lastIndexOf(".");
                if (pos > 0 && pos < (project.length() - 1)) { // si '.' n'est pas le premier ou dernier caractere
                	project = project.substring(0, pos); // pour enlever l'extention
                }
                bw.write("Nom du projet : "+project); bw.newLine();
                
                int nbClass=0;
                while (en.hasMoreElements()) {
                    JarEntry je = en.nextElement();
                    if(je.isDirectory() || !je.getName().endsWith(".class"))
                    	continue; nbClass++;
                }
                bw.write("Nombre de classes : "+nbClass); bw.newLine();
                bw.write("Java Version : " + System.getProperty("java.version")); bw.newLine();
                bw.write("OS : " + System.getProperty("os.name")); bw.newLine();
                bw.write("_____________________________________________________________________________________________________________________________________________________________");
                bw.newLine();bw.newLine();
	            bw.write("Information classes ");bw.newLine();
                bw.write("_____________________________________________________________________________________________________________________________________________________________");
                bw.newLine();bw.newLine();
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
                    
                    bw.write("Classe : "+ cpt+"\n");bw.newLine();
            
                    Class c = cl.loadClass(className);
                  
                    Package pack = c.getPackage();
                    String packageName = pack.getName();
                    bw.write("Nom du package : " + packageName);bw.newLine();
                    bw.write("Nom du classe  : " + c.getSimpleName());bw.newLine();
                  
                    //les infos des attributs
                    int Attcount=0;
                    Field[] f = c.getFields();
                    ArrayList<String> listattPub = new ArrayList<String>();
                    for (int i = 0; i < f.length;i++)
                     {	Field field = f[i];
                        Attcount = Attcount + 1;
                        listattPub.add(field.getName()+" - "+(Object)field.getType().getSimpleName());
                      }
                    bw.write("Nombre des attributs publics : " + Attcount);bw.newLine();
                    bw.write("Liste et Types des attributs publics : " + listattPub );bw.newLine();
                    
                    int AttcountPrv=0;
                    Field[] f1 = c.getDeclaredFields();
                    ArrayList<String> listattPrv = new ArrayList<String>();
                    for (int i = 0; i < f.length;i++)
                     { Field field = f1[i];
                         if(field.getModifiers()==2)//getModifiers() retourne 2 si field est private , 1 si public
                         {
                        	 listattPrv.add(field.getName()+" - "+(Object)field.getType().getSimpleName());
                        	 AttcountPrv = AttcountPrv + 1;}
                         }
                    bw.write("Nombre des attributs privés : " + AttcountPrv);bw.newLine();
                    bw.write("Liste et Types des attributs privés : " + listattPrv );bw.newLine();
                    
                     //liste des  constructeurs          
                    Constructor[] constructeurs =c.getDeclaredConstructors();
                    ArrayList<String> listconst = new ArrayList<String>();
                    for(Constructor constructeur : constructeurs)
                    { listconst.add(constructeur.getName());  }
                    bw.write("Liste des constructeurs : "+listconst);bw.newLine();

	                //les infos des methodes
			        int Mcount=0;
			        Method methlist[]= c.getDeclaredMethods();
	                ArrayList<String> listmeth = new ArrayList<String>();
	                ArrayList<String> listmethType = new ArrayList<String>();
	                ArrayList<String> listmethTypeParam = new ArrayList<String>();
	                ArrayList<String> parameterNames = new ArrayList<>();

                    for (int i = 0; i < methlist.length;i++)
	                    {  
	                          Method methode = methlist[i];
	                          Mcount = Mcount + 1;
	                          listmeth.add(methode.getName());
	                          listmethType.add(methode.getName()+" : "+methode.getReturnType().getSimpleName());
	                         
	                      }
	                 bw.write("Nombre de méthodes : " + Mcount);bw.newLine();
	                 bw.write("Liste des méthodes : "+listmeth);bw.newLine();
	                 bw.write("Type de retour des méthodes : "+listmethType);bw.newLine();
	                 bw.write("Paramètres et types des méthodes : ");
	                 String str = "";
	                 for (Method m : c.getDeclaredMethods()) {
	                 	 Parameter[] temp = m.getParameters();
	                 	 String s = m.getName()+"[ ";
	                 	       for (Parameter parameter : temp) {
	                 	         String inf= parameter.getName()+" : "+parameter.getType().getSimpleName()+" , ";
	                 	         s=s+inf;
	                 	           } 
	                 	 if(s.charAt(s.length()-2)!='[')   { s = s.substring(0, s.length()-2);}
	                 	 s= s + "]"+" , ";
	                     str = str + s;
	                 }
	                 if (str.length()>0) {
	                	 str = str.substring(0, str.length()-2);//pour enlever la derniere virgule
	                 	} 
	                 bw.write(str);
	                 bw.newLine();bw.newLine(); 
	                           
	                 bw.write("_____________________________________________________________________________________________________________________________________________________________");
	                 bw.newLine();
	                 bw.newLine();
                }
                bw.close();
                System.out.println ("1-File generated ...");
                
    	    }catch (Exception e) { 
    	 		ex.saveFile(e);}
    	
		    break;
    	 	 
			 

	case 2 :
		
	 	try { 
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
             while (en.hasMoreElements()) {
                 JarEntry je = en.nextElement();
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
             bw.newLine();
             bw.close();

			 System.out.println ("2-File generated ...");
            
         } 
       
 	 	catch (Exception e) { 
 	 		ex.saveFileXML(e);
      	}
  
		            

			 break; 
			 
   case 3 :
	    try {
		
			Enumeration<JarEntry> e1 = jarFile.entries();
			
			 while (e1.hasMoreElements()) {
			     JarEntry je = e1.nextElement();
			     if(je.isDirectory() || !je.getName().endsWith(".class")){
			         continue;
			     } 
			    
			     String className = je.getName().substring(0,je.getName().length()-6);
			     className = className.replace('/', '.');
			     Class c = cl.loadClass(className);
			     String clsName=c.getSimpleName();
			     String S="Test"; String java=".java";
			     String cName=S.concat(clsName);
			     String classExt=cName.concat(java);
			     File f = new File ("D:\\Tests\\"+classExt); 
			     
			 	BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
			 	bw.write("import org.junit.*;");
			 	bw.newLine();
			 	bw.write("import org.junit.Test;");
			 	bw.newLine();
			 	bw.write("import static org.junit.Assert.*;");
			 	bw.newLine();
			 	bw.newLine();
			   
				bw.write("public class "+cName+" {"); 
				bw.newLine();
				bw.newLine();
				
			    String type = ""; 
			     for (Method m : c.getDeclaredMethods()) {
			         
			    	 String S1=m.getName();
			           String methNameTest=S.concat(S1);
			  
			           bw.write("@Test");
			       	   bw.newLine();
			           bw.write("void "+methNameTest+"()");
			           type=type+m.getReturnType().getSimpleName();
			           type="?";
			    	 
			        	 String s ="";
		               	 Parameter[] temp = m.getParameters();
		               	 s = s+m.getName()+"(";
		               	       for (Parameter parameter : temp) {
		               	         String inf=parameter.getType().getSimpleName();

		               	         if(inf.equals("String"))
		            	         {inf=RandomGenerator.randString(6);}
		               	         else if (inf.equals("int"))
		               	         {inf=Integer.toString(RandomGenerator.randInteger(6));}
		               	         else if(inf.equals("float"))
		            	         {inf=String.valueOf(RandomGenerator.randFloat());}
		               	         else if(inf.equals("boolean"))
		               	         {inf=String.valueOf(RandomGenerator.randBoolean());}
		               	         else if(inf.equals("double"))
		               	         {inf=String.valueOf(RandomGenerator.randDouble());}
		               	         else if(!inf.equals("String[]")){
		               	        	 inf= "new "+inf+"()";}
		               	         
		               	         s=s+inf+",";

		               	           } 
		               	    if (s.length()>0 && s.charAt(s.length()-1)!='(') {
		                     	 s = s.substring(0, s.length()-1);
		                      	}
		               	 
		               	   s=s+")";
		               	bw.write("{");
		               	bw.write("assertEquals(");
		               	bw.write(type);
		               	bw.write(",");
		               	bw.write(s);
		               	bw.write(")");
		               	bw.write(";}");
		       	        bw.newLine();
		       			bw.newLine();
			           }
		     
			 bw.write("}");
			 bw.close();
			} 
			 System.out.println ("3-File generated ...");
			
			}catch (Exception e) { 
		 		ex.saveFile(e);
		 	}
				
				 
			 break;
			 case 4 :
				 System.exit(1);
			 break;
			 
			 default : break;
			 }
			}while(choix!=0);
			
        } catch (Exception e) { 
	 		ex.saveFile(e);
	 	}
    } 	 
} 


