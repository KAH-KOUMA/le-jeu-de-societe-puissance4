package sources.serialisation;

/*----L'-import-pour-le-metier----*/
import sources.metier.Puissance4;

/*----L'-import-pour-la-serialisation----*/
import java.io.Serializable;

/**
	* Classe Jeu
	* @author 	: -
	* @version 	: 1.0
	* date 		! 07/08/2020
*/

/**
	* Explication : 
	* Enregistre une seule partie
*/

public class Jeu implements Serializable
{
	/*---------------*/
	/* Les Attributs */
	/*---------------*/

	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;

	/**
		* Les éléments du jeu
	*/
	private String 		nomJeu; 	// L'attribut renseigne sur le nom de la partie.
	private Puissance4 	metier; 	// L'attribut renseigne sur la partie de jeu.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur Jeu
		* À l'instanciation d'un objet Jeu(String nomJeu, Puissance4 metier), une partie de jeu est insérée et enregistrée dans l'objet Jeu.
		* @param nomJeu : le nom de la partie.
		* @param metier : la partie enregistrée.
	*/
	public Jeu(String nomJeu, Puissance4 metier)
	{
		this.nomJeu = nomJeu; // Le nom de la partie.
		this.metier = metier; // La partie enregistrée.
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*----------------------------------*/
	/*			LES ACCESSEURS			*/
	/*----------------------------------*/

	/**
		* @return Retourne le nom de la partie.
	*/
	public String getNom()
	{
		return this.nomJeu;
	}

	/**
		* @return Retourne la partie.
	*/
	public Puissance4 getJeu()
	{
		return this.metier;
	}

	/*------------------------------*/
	/*			COMPARAISON			*/
	/*------------------------------*/

	/**
		* @return Retourne true : si cette partie est la même que la partie comparée / false : si cette partie n'est pas la même que la partie comparée.
		* @param jeu : le jeu qui est comparé.
	*/
	public boolean equals(Jeu jeu)
	{
		return this.nomJeu.equals(jeu.getNom());
	}

	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* @return Affiche le nom d'une partie.
	*/
	public String toString()
	{
		String sRet="";
		sRet += " Nom de jeu de la partie enregistrée : " + this.nomJeu + "\n";

		return sRet;
	}
}