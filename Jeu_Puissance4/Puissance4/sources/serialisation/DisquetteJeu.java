package sources.serialisation;

/*----Les-imports-pour-le-metier----*/
import sources.metier.Puissance4;

/*----Les-imports-pour-la-serialisation----*/
import sources.serialisation.DonneesJeu;
import java.util.Stack;

/*----Les-imports-pour-lecture-et-écriture-de-fichier---*/
import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

/**
	* Classe DisquetteJeu
	* @author 	: -
	* @version 	: 1.0
	* date 		! 07/08/2020
*/

/**
	* Explication : 
	* Enregistre ou Charge une partie.
*/

public class DisquetteJeu
{
	/*-------------*/
	/* L' Attribut */
	/*-------------*/

	/**
		* L'élément des parties de jeux. 
	*/
	private DonneesJeu donneesJeu; // L'attribut renseigne sur les parties stockées.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur DisquetteJeu
		* À l'instanciation d'un objet DisquetteJeu(), les parties sont enregistrées dans la pile de jeu.
	*/
	public DisquetteJeu()
	{
		this.donneesJeu = new DonneesJeu(); // La pile de jeu est initialisée.

		// Enregistrement des parties dans la pile de jeu.
			// Si la liste des fichiers n'est pas vide, lecture des fichiers puis ajoute les fichiers dans la pile de jeu.
		File fichier = new File("./ressources/data/sauvegarde");
		if ( ! fichier.exists() ) 
		{
			fichier.mkdirs();
		}
		if ( fichier.list().length > 0  )
		{
			for(String str : fichier.list())
			{
				if ( str.contains(".ser") )
				{
					try
					{
						ObjectInputStream in = new ObjectInputStream( new FileInputStream("./ressources/data/sauvegarde/" + str) );
						this.donneesJeu.ajouterJeu( (Jeu) in.readObject() );
						in.close();
					}
					catch(Exception e ) { e.printStackTrace(); }
				}
			}
		}
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*----------------------------------*/
	/*			LES ACCESSEURS			*/
	/*----------------------------------*/

	/**
		* @return Retourne la pile de jeux.
	*/
	public Stack<Jeu> getPileJeux()
	{
		return this.donneesJeu.getPileJeux();
	}

	/**
		* @return Retourne une partie de la pile de jeux.
		* @param nomJeu : le nom de la partie.
	*/
	public Jeu chargerJeu(String  nomJeu)
	{
		return this.donneesJeu.getJeu(nomJeu);
	}


	/*----------------------------------------------*/
	/*			ENREGISTREMENT D'UNE PARTIE			*/
	/*----------------------------------------------*/

	/**
		* Sauvegarde une partie de jeu.
		* @param nomJeu : le nom de la partie.
		* @param metier : la partie qui doit être enregistrée.
	*/
	public void sauvegardeJeu(String nomJeu, Puissance4 metier)
	{
		Jeu jeu = new Jeu(nomJeu, metier);
		this.donneesJeu.ajouterJeu(jeu);
		
		File fichier = new File("./ressources/data/sauvegarde");

		if ( ! fichier.exists() )
		{
			fichier.mkdirs();
		}

		try
		{
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("./ressources/data/sauvegarde/"+nomJeu+".ser") );
			out.writeObject(jeu);
			out.close();
		}
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}
	}
}