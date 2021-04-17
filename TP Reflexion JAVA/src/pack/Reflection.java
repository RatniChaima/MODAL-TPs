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
                File pathToJar = new File("D:/MyProject.jar");

                JarFile jarFile;
                jarFile = new JarFile(pathToJar);
                Enumeration<JarEntry> e = jarFile.entries();

                URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
                URLClassLoader cl = URLClassLoader.newInstance(urls);
                
    		 	BufferedWriter bw = null;
    			bw = new BufferedWriter(new FileWriter("D:\\FileReflection.txt",true));
    			
    			System.out.println("********************NOTES********************");
    			System.out.println("Les informations de votre projet sont dans le fichier \" FileReflection.txt \" .");
    			System.out.println("Les exceptions sont dans le fichier \" FileException.txt \" (au cas ou il y'a des exceptions) .");
        		
    				
    				bw.write("_____________________________________________________________________________________________________________________________________________________________");
    				bw.newLine();bw.newLine();
    	         
    	            bw.write("Description textuelle -- Date :" );
    	    		Date d=new Date();
    	    		SimpleDateFormat df=new SimpleDateFormat( " dd MMMM yyyy", Locale.FRANCE);
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
                while (e.hasMoreElements()) {
                    JarEntry je = e.nextElement();
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
	                          Parameter[] parameters = methode.getParameters();
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
	                 bw.newLine();bw.newLine();
                }
                bw.close();
               
            }
          
    	 	catch (Exception e) { 
    	 		ex.saveFile(e);
         	}
            
           
} 
}

