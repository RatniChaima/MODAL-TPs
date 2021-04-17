package pack;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Deserialisation {
	
	public void saveAfter()
	{
		FichException ex = new FichException();
		try {
			FileInputStream fis = new FileInputStream("D:\\serEtud.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object o = null;
			while((o = ois.readObject()) != null)
			{
				if (o instanceof Etudiant)
					System.out.println(((Etudiant) o).getPrenom()+" "+((Etudiant) o).getNom()+" "+((Etudiant) o).getMat()+" "+((Etudiant) o).getNivEtude()+" "+((Etudiant) o).getSpecialite());
			}
				
		}
		catch(EOFException e) {
			System.out.println("End of file");
			ex.saveFile(e);
		}
		catch(Exception e)
		{
			ex.saveFile(e);
		}
	}	
}

