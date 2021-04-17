package pack;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.Scanner;



public class P {
	public static void main(String[] args)throws IOException{

		FichException ex = new FichException();
	

try{
	
    System.out.println("*******************************************************************************\n");
    System.out.println(" Universite de la science et de la technologie HOUARI BOUMEDIENNE 2019/2020     ");
    System.out.println("                                MASTER: IL                        ");
    System.out.println("   Module : MODAL - Sérialisation, désérialisation et gestion des exceptions       ");
    System.out.println("                            Réalisée par: RATNI Chaima                       \n");
    System.out.println("********************************************************************************\n");
    int choix ;
	System.out.println("\n---------------------------Veuillez choisir une option---------------------------\n ");
    Scanner sc = new Scanner(System.in);
  do{
	System.out.println("\n1- Lire le contenu du fichier");
	System.out.println("2- Ajouter un étudiant au fichier");
	System.out.println("3- Sérialiser le contenu du fichier");
	System.out.println("4- Désérialiser le contenu fichier sérialisé");
	System.out.println("5- Quitter");
	System.out.print("\nVotre choix :\n ");
	choix=Integer.parseInt(sc.nextLine());
	 switch (choix) {
	 
	 case 1 : //Lire le contenu du fichier
		
		 	BufferedReader br = null; 
		 	br = new BufferedReader(new FileReader("D:\\F.txt"));
			String line=br.readLine();
			System.out.println("**Le contenu du fichier est** \n");
			while(line!=null){
				
				System.out.println(line);
				line=br.readLine();
			}
	 break;
	 case 2 ://Ajouter un étudiant au fichier

		 System.out.println("Veuillez saisir votre Prenom :");
		 String prenom = sc.nextLine();	 
		 System.out.println("Veuillez saisir votre nom  :");
		 String nom = sc.nextLine();
		 System.out.println("Veuillez saisir votre Matricule :");
		 String mat = sc.nextLine();
		 System.out.println("Veuillez saisir votre niveau d'etude :");
		 String niv = sc.nextLine();
		 System.out.println("Veuillez saisir votre Specialite :");
		 String spec = sc.nextLine();
		 
		 	Etudiant o=new Etudiant(prenom , nom, mat, niv, spec);
		 	String str=((Etudiant) o).getPrenom()+" "+((Etudiant) o).getNom()+" "+((Etudiant) o).getMat()+" "+((Etudiant) o).getNivEtude()+" "+((Etudiant) o).getSpecialite();
		 	System.out.println(str);
		 	BufferedWriter bw = null;
			
				bw = new BufferedWriter(new FileWriter("D:\\F.txt",true));
				bw.newLine();
			
			//save the information in buffer
		
				bw.write(str);
				bw.close();
		
	
			
				
			 
	 break;
	 case 3 : //3- Sérialiser le contenu du fichier
		
		 br = null; 

			br = new BufferedReader(new FileReader("D:\\F.txt"));
	      
			line=br.readLine();
			FileOutputStream fs = new FileOutputStream("D:\\serEtud.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			while(line!=null){
							
			String[] tabEtudiant= line.split(" ") ;
			line=br.readLine();
			
			Etudiant et = new Etudiant(tabEtudiant[0],tabEtudiant[1],tabEtudiant[2],tabEtudiant[3],tabEtudiant[4]);

				os.writeObject(et);
			}
			os.close();
		 System.out.println("** Votre fichier a été sérialisé **");
			
		 break; 
		 
	 case 4 : //Désérialiser le contenu fichier sérialisé

			System.out.println("**Le contenu du fichier apres désérialisation est **\n ");
		 	Deserialisation Dse=new Deserialisation();
			Dse.saveAfter();
	 break; 
	 
	 case 5 :
		 System.exit(1);
	 break;
	 
	 default : break;
	 }
	}while(choix!=0);
	}
		catch (Exception e) {
			ex.saveFile(e);
		}
		}

}	
	
