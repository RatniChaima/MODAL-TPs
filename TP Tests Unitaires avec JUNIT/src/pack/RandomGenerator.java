package pack;

import java.util.Random;

public class RandomGenerator {
	

	public static int randInteger(int n) 
	{
		Random rand = new Random();
		int number;
		 number =1+rand.nextInt(n);
		 return number;
	}
	
	public static float randFloat() {

	    Random rand = new Random();
	    return rand.nextFloat();

	}
	public static double randDouble() {

	    Random rand = new Random();
	    return rand.nextDouble();

	}
	
	public static Boolean randBoolean() {

	    Random rand = new Random();
	    return rand.nextBoolean();

	}

	public static String randString(int n) 
		{ 
			String chaine = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvxyz"; 
			StringBuilder sb = new StringBuilder(n); 

			for (int i = 0; i < n; i++) {  
				int index= (int)(chaine.length()* Math.random()); 
				sb.append(chaine.charAt(index)); 
			} 

			return sb.toString(); 
		} 
	
		
} 


