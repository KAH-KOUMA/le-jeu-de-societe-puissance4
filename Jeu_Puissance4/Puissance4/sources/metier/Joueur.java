package sources.metier;

/*----L'-import-pour-la-serialisation----*/
import java.io.Serializable;

/**
	* Classe Joueur
	* @author 	: -
	* @version 	: 1.0
	* date 		! 07/08/2020
*/

public class Joueur implements Serializable
{
	/*---------------*/
	/* Les Attributs */
	/*---------------*/

	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
		* Les éléments du joueur
	*/
	private static 	int 	nbJoueur; 			// L'attribut renseigne sur le nombre de joueurs (numéro séquentiel auto-incrementé).
	private 		int 	numJoueur; 			// L'attribut renseigne sur le numéro du joueur.
	private 		String 	pseudoJoueur; 		// L'attribut renseigne sur le pseudo du joueur.
	private 		char 	couleur; 			// L'attribut renseigne sur la couleur du joueur (jaune ou rouge). 
	private 		boolean estActif; 			// L'attribut renseigne si le joueur doit jouer.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur Joueur
		* À l'instanciation d'un objet Joueur(char couleur), un joueur est créé.
		* @param couleur : couleur du joueur.
	*/
	public Joueur(char couleur)
	{
		/*----------------------*/
		/* Les caractéristiques */
		/*----------------------*/
		this.numJoueur 		= ++Joueur.nbJoueur; 	// Le numéro du joueur.
		this.pseudoJoueur 	= "";					// Le joueur possède un pseudo qu'il communique à l'accueil du jeu.
		this.couleur 		= couleur; 				// La couleur du joueur (jaune ou rouge).
		this.estActif 		= false; 				// Au début de la partie, le joueur n'est pas actif.
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*----------------------------------*/
	/*			LES ACCESSEURS			*/
	/*----------------------------------*/

	/**
		* @return Retourne le numéro du joueur.
	*/
	public int getNumJoueur()
	{
		return this.numJoueur;
	}

	/**
		* @return Retourne le pseudo du joueur.
	*/
	public String getPseudoJoueur()
	{
		return this.pseudoJoueur;
	}

	/**
		* @return Retourne la couleur du joueur (jaune : 'J' - rouge : 'R').
	*/
	public char getCouleur()
	{
		return this.couleur;
	}

	/**
		* @return Retourne la couleur du joueur(jaune - rouge)
	*/
	public String getCouleurComplete()
	{
		if ( this.couleur == 'J' )
		{
			return "jaune";
		}

		if ( this.couleur == 'R' )
		{
			return "rouge";
		}

		return null;
	}

	/**
		* @return Retourne true : si le joueur est actif / false : si le joueur n'est pas actif.
	*/
	public boolean getActif()
	{
		return this.estActif;
	}

	/*--------------------------------------*/
	/*			LES MODIFICATEURS			*/
	/*--------------------------------------*/

	/**
		* Fixe le numéro du joueur lors d'une réinitialisation.
		* @param numJoueur : le numéro du joueur.
	*/
	public void setNumJoueur(int numJoueur)
	{
		this.numJoueur = Joueur.nbJoueur = numJoueur;
	}

	/**
		* Fixe le pseudo du joueur.
		* @param pseudo : le pseudo du joueur.
	*/
	public void setPseudo(String pseudo)
	{
		if ( pseudo != null )
		{
			this.pseudoJoueur = pseudo;
		}
		else
		{
			this.pseudoJoueur = "Joueur" + this.numJoueur;
		}
	}

	/**
		* Fixe la couleur du joueur.
		* @param pseudo : la couleur du joueur.
	*/
	public void setCouleur(char couleur)
	{
		if ( couleur == 'J' || couleur == 'R' )
		{
			this.couleur = couleur;
		} 
	}

	/**
		* Si le joueur n'était pas actif, il devient actif. Sinon, il devient inactif. 
	*/
	public void setActif()
	{
		if ( ! this.estActif ) 
		{
			this.estActif = true;
		}
		else 
		{ 
			this.estActif = false;
		}
	}

	/**
		* Fixe si le joueur est actif ou inactif.
		* @param bActifJoueur : l'actif du joueur.
	*/
	public void setActifJoueur(boolean bActifJoueur)
	{
		this.estActif = bActifJoueur;
	}

	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* @return Affiche les caractéristiques(numéro, pseudo, couleur et actif) du joueur.
	*/
	public String toString()
	{
		return "Joueur numéro " + this.numJoueur + " pseudo : " + this.pseudoJoueur + " couleur : " + this.couleur + " estActif : " + this.estActif;
	}
}