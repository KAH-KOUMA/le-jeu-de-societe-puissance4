package sources.serialisation;

/*----L'-import-pour-la-serialisation----*/
import sources.serialisation.Jeu;
import java.io.Serializable;
import java.util.Stack;


/**
	* Classe DonneesJeu
	* @author 	: -
	* @version 	: 1.0
	* date 		! 07/08/2020
*/

/**
	* Explication : 
	* Enregistre les parties et les stockent sous forme de pile
*/

public class DonneesJeu implements Serializable
{
	/*---------------*/
	/* Les Attributs */
	/*---------------*/

	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;

	/**
		* L'élément d'enregistrement de jeu.
	*/
	private Stack<Jeu> pileJeu; // L'attribut renseigne sur la pile de jeux.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur DonneesJeu
		* À l'instanciation d'un objet DonneesJeu(), une pile de jeu est initialisée.
	*/
	public DonneesJeu()
	{
		this.pileJeu = new Stack<Jeu>(); // La pile de jeux qui va contenir les parties enregistrées.
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
		if ( this.pileJeu.isEmpty() )
		{
			return null;
		}
		return this.pileJeu;
	}

	/**
		* @return Retourne une partie grâce au nom de la partie.
		* @param nomJeu : le nom de la partie.
	*/
	public Jeu getJeu(String nomJeu)
	{
		for(Jeu jeu : this.pileJeu)
		{
			if ( jeu.getNom().equals( nomJeu ) ) 
			{
				return jeu;
			}
		}
		
		return null;
	}

	/*--------------------------*/
	/*			L'AJOUT			*/
	/*--------------------------*/

	/**
		* Ajoute une partie dans la pile de jeux.
		* @param jeuAjoute : le nom de la partie.
	*/
	public void ajouterJeu(Jeu jeuAjoute)
	{
		boolean bOk = false;

		for(Jeu jeu : this.pileJeu) 
		{ 
			if ( jeu.equals(jeuAjoute) ) 
			{
				bOk = true;
			}
		}

		if ( ! bOk )
		{
			this.pileJeu.push(jeuAjoute); 
		}
		else
		{
			for(int cpt=0; cpt<this.pileJeu.size(); cpt++)
			{
				if ( this.pileJeu.get(cpt).equals(jeuAjoute) )
				{
					this.pileJeu.set(cpt, jeuAjoute);
				}
			}
		}
	}

	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* @return Affiche les parties enregistrées dans la pile de jeux.
	*/
	public String toString()
	{
		String sRet = "";

		for(Jeu jeu : this.pileJeu)
		{ 
			sRet += " " + jeu.getNom() + "\n"; 
		}
		
		return sRet;
	}

}