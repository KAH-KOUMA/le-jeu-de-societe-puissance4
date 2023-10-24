package sources.metier;

/*----L'-import-pour-le-metier----*/
import sources.metier.Joueur;

/*----Les-imports-pour-la-serialisation----*/
import java.io.Serializable;
import sources.serialisation.Jeu;
import sources.serialisation.DisquetteJeu;

import java.util.Random;

/**
	* Classe Puissance4
	* @author 	: -
	* @version 	: 1.0
	* date 		! 07/08/2020
*/

public class Puissance4 implements Serializable
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
	private static final int NB_LIGNE_PAR_DEFAUT 	= 6; // L'attribut renseigne sur le nombre de lignes par défaut.
	private static final int NB_COLONNE_PAR_DEFAUT 	= 7; // L'attribut renseigne sur le nombre de colonnes par défaut.

	private char[][] 		grille; 					// L'attribut renseigne sur la grille du Puissance4.
	private int 			nbLigne; 					// L'attribut renseigne sur le nombre de lignes.
	private int 			nbColonne; 					// L'attribut renseigne sur le nombre de colonnes.
	private boolean 		partieGagne; 				// L'attribut renseigne sur si la partie est gagnante ou non gagnante.
	private Joueur[]		tabJoueur;					// L'attribut renseigne sur les joueurs.
	private boolean 		couleurAffichage; 			// L'attribut renseigne sur la couleur d'affichage.	


	/*-------------------*/
	/* Les Constructeurs */
	/*-------------------*/

	/**
		* Constructeur Puissance4
		* À l'instanciation d'un objet Puissance4(), une partie de jeu est initialisée de manière non graphique.
	*/
	public Puissance4()
	{
		/*------------------------*/
		/* Les conditions de jeux */
		/*------------------------*/
		this.nbLigne 			= Puissance4.NB_LIGNE_PAR_DEFAUT;			// Ligne : 6
		this.nbColonne 			= Puissance4.NB_COLONNE_PAR_DEFAUT;			// Colonne : 7
		this.grille 			= new char[this.nbLigne][this.nbColonne]; 	// Création d'une grille 6x7.
		this.partieGagne 		= false; 									// Au début du jeu, aucuns des joueurs ne gagnent.
		this.tabJoueur 			= new Joueur[2]; 							// Le jeu se joue à deux joueurs.
		this.couleurAffichage 	= false;									// Au début du jeu, l'affichage est clair.
		

		/*-------------------------------*/
		/* L'initialisation de la grille */
		/*-------------------------------*/
		for(int cptLigne=0; cptLigne<this.nbLigne; cptLigne++)
		{
			for(int cptColonne=0; cptColonne<this.nbColonne; cptColonne++)
			{
				this.grille[cptLigne][cptColonne] = 'V'; 					// La grille est initialisée avec des 'V'.
			}
		}

		/*------------------------------*/
		/* L'initialisation des joueurs */
		/*------------------------------*/
		this.initJoueurs();
	}

	/**
		* Constructeur Puissance4(String nomJeu)
		* À l'instanciation d'un objet Puissance4(String nomJeu), une partie est alors chargé avec le nom de la partie renseignée.
		* @param nomJeu : le nom de la partie renseignée pour être chargé.
	*/
	public Puissance4(String nomJeu)
	{
		Jeu jeu = new DisquetteJeu().chargerJeu(nomJeu); 	// Chargement d'une partie.

		/*--------------------------------------------------------*/
		/* Adaptation des attributs de Puissance4 à partir de jeu */
		/*--------------------------------------------------------*/
		this.nbLigne 			= jeu.getJeu().getNbLigne(); 				// Récupère le nombre de lignes de la partie chargé.
		this.nbColonne 			= jeu.getJeu().getNbColonne(); 				// Récupère le nombre de colonnes de la partie chargé.
		this.grille 			= new char[this.nbLigne][this.nbColonne];   // Création d'une grille avec le nombre de lignes x le nombre de colonnes.
		this.partieGagne 		= jeu.getJeu().getPartieGagne(); 			// Récupère la valeur booléenne vrai si la partie est gagné par l'un des joueurs et false si la partie est non gagnante de la partie chargé.
		this.tabJoueur 			= new Joueur[2];							// Le jeu se joue à deux joueurs.
		this.couleurAffichage 	= jeu.getJeu().getCouleurAffichage(); 		// Récupère la couleur d'affichage.

		// Récupère la grille
		for(int cptLigne=0; cptLigne<this.nbLigne; cptLigne++)
		{
			for(int cptColonne=0; cptColonne<this.nbColonne; cptColonne++)
			{
				this.grille[cptLigne][cptColonne] = jeu.getJeu().getJeton(cptLigne, cptColonne); // Recupère le jeton de la grille de la partie chargé.
			}
		}

		// Récupère la grille
		for(int cptJoueur = 0; cptJoueur<this.tabJoueur.length; cptJoueur++)
		{
			this.tabJoueur[cptJoueur] = jeu.getJeu().getJoueur(cptJoueur);
		}
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*----------------------------------*/
	/*			LES ACCESSEURS			*/
	/*----------------------------------*/
		
	/**
		* @return Retourne le nombre de lignes.
	*/
	public int getNbLigne()
	{
		return this.nbLigne;
	}

	/**
		* @return Retourne le nombre de colonnes.
	*/
	public int getNbColonne()
	{
		return this.nbColonne;
	}

	/**
		* @return Retourne le nombre de joueur.
	*/
	public int getNbJoueur()
	{
		return this.tabJoueur.length;
	}

	/**
		* @return Retourne le jeton présent à l'indice de la ligne et de la colonne précisé.
		* @param numLigne 	: le numéro de la ligne.
		* @param numColonne : le numéro de la colonne.
	*/
	public char getJeton(int numLigne, int numColonne)
	{
		if ( (numLigne >= 0 && numLigne < this.nbLigne) && (numColonne >= 0 && numColonne < this.nbColonne) )
		{
			return this.grille[numLigne][numColonne];
		}

		return 'V';
	}

	/**
		* @return Retourne true : si la partie est gagnée / false : si la partie n'est pas gagnée.
	*/
	public boolean getPartieGagne()
	{
		return this.partieGagne;
	}

	/**
		* @return Retourne true : si la grille est pleine / false : si la grille n'est pas pleine
	*/
	public boolean getGrillePleine()
	{
		for(int cptLigne=0; cptLigne<this.nbLigne; cptLigne++)
		{
			for(int cptColonne=0; cptColonne<this.nbColonne; cptColonne++)
			{
				if ( this.grille[cptLigne][cptColonne] == 'V' )
				{
					return false;
				}
			}
		}

		return true;
	}

	/**
		* @return Retourne un joueur.
		* @param  indiceJoueur : indice du joueur qui est retourné.
	*/
	public Joueur getJoueur(int indiceJoueur)
	{
		return this.tabJoueur[indiceJoueur];
	}

	/**
		* @return Retourne le joueur actif
	*/
	public Joueur getJoueurActif()
	{
		for(int cptJoueur=0; cptJoueur<this.tabJoueur.length; cptJoueur++)
		{
			if ( this.tabJoueur[cptJoueur].getActif() )
			{
				return this.getJoueur(cptJoueur);
			}
		}

		return null;
	}

	/**
		* @return Retourne la couleur d'affichage.
	*/
	public boolean getCouleurAffichage()
	{
		return this.couleurAffichage;
	}

	/*--------------------------------------*/
	/*			LES MODIFICATEURS			*/
	/*--------------------------------------*/

	/**
		* @return Retourne true : si le jeton a été positionné sur la grille / false : si le jeton n'a pas été positionné sur la grille.
		* @param numColonne : le numéro de la colonne.
	*/
	public boolean setJeton(int numColonne)
	{
		// Vérification de la colonne
		if ( numColonne < 0 || numColonne > this.nbColonne )
		{
			return false;
		}

		// Vérification que la colonne n'est pas pleine
		if ( this.grille[0][numColonne] != 'V' )
		{
			return false;
		}

		int cptLigne = this.nbLigne - 1;
		while( this.grille[cptLigne][numColonne] != 'V' && cptLigne >= 0)
		{
			cptLigne--;
		}
		this.grille[cptLigne][numColonne] = this.getJoueurActif().getCouleur();

		return true;
	}

	/** 
		* Indique que c'est au joueur suivant de jouer. 
	*/
	public void setJoueurSuivant()
	{
		for (int cptJoueur=0; cptJoueur<this.tabJoueur.length; cptJoueur++) 
		{
			this.tabJoueur[cptJoueur].setActif();
		}
	}

	
	/**
		* Fixe la couleur d'affichage.
	*/
	public void setCouleurAffichage()
	{
		if ( this.couleurAffichage )
		{
			this.couleurAffichage = false;
		}
		else
		{
			this.couleurAffichage = true;
		}
	}

	/**
		* Réinitialise la partie.
	*/
	public void reinitialiserPartie()
	{
		// Réinitialisation du plateau
		for(int cptLigne=0; cptLigne<this.nbLigne; cptLigne++)
		{
			for(int cptColonne=0; cptColonne<this.nbColonne; cptColonne++)
			{
				this.grille[cptLigne][cptColonne] = 'V';
			}
		}
	}
	
	/*--------------------------------------*/
	/*			LES INITIALISATIONS			*/
	/*--------------------------------------*/
	/**
		* Initialise les joueurs.
	*/
	private void initJoueurs()
	{
		char[] tabCouleur = {'J', 'R'};

		int joueurCouleur 	= (int) (Math.random() * this.getNbJoueur());

		for (int cpt=0; cpt<this.tabJoueur.length; cpt++)
		{
			if ( cpt%2 == 0 )
			{
				this.tabJoueur[cpt] = new Joueur(tabCouleur[joueurCouleur]);
			}
			else
			{
				if ( joueurCouleur < cpt )
				{
					joueurCouleur++;
				}
				else
				{
					joueurCouleur--;
				}
				this.tabJoueur[cpt] = new Joueur(tabCouleur[joueurCouleur]);
			}
		}

		for (int cpt=0; cpt<this.tabJoueur.length; cpt++) 
		{
			if ( this.tabJoueur[cpt].getNumJoueur() > this.getNbJoueur() )
			{
				this.tabJoueur[cpt].setNumJoueur(cpt+1);
			}
		}

		// Initialisation du joueur actif
		int joueurActif = (int) (Math.random() * this.getNbJoueur());
		this.tabJoueur[joueurActif].setActif();
	}
	
	/*------------------------------------------*/
	/*			VÉRIFICATION DU GAGNANT			*/
	/*------------------------------------------*/

	/**
		* @return Retourne true : si le joueur a gagné / false : si le joueur n'a pas gagné. 
	*/
	public boolean estGagnant()
	{
		final int ligMax = this.nbLigne; 
		final int colMax = this.nbColonne;

		for(int indiceLig=0; indiceLig<this.nbLigne; indiceLig++)
			{
				for(int indiceCol=0; indiceCol<this.nbColonne; indiceCol++)
				{
					if ( ! (this.grille[indiceLig][indiceCol] == 'V') )
					{
						if ( indiceCol<=colMax 	&& compterJeton(this.grille, indiceLig, indiceCol, 1, 1) == 4
					 				// diagonale : vers le bas et à droite 
							|| indiceCol<=colMax 	&& compterJeton(this.grille, indiceLig, indiceCol, -1, 1) == 4
									// vers le haut et à droite
							|| indiceLig<=ligMax  	&& compterJeton(this.grille, indiceLig, indiceCol, 0, 1) == 4
									// horizontal vers la droite
							|| indiceLig<= ligMax	&& compterJeton(this.grille, indiceLig, indiceCol, 1, 0) == 4
								// vertical du haut vers le bas
							)
						{
							return true;
						}
					}
				}
			}

		return false;
	}

	/**
		* @return Retourne le nombre de jeton aligné dans une direction précise.
		* @param grille 	: la grille.
		* @param lig 		: indice de la ligne du plateau.
		* @param col 		: indice de la colonne du plateau.
		* @param ligDir 	: direction de la ligne (nord=-1 ou sud=1) pour compter les jetons. 
		* @param colDir 	: direction de la colonne (ouest=-1 ou est=1) pour compter les jetons.
	*/
	private int compterJeton(char[][] grille, int lig, int col, int ligDir, int colDir)
	{

		int cpt 	=	0; 		// compte le nombre de jeton aligné
		int ligCpt 	=	lig;	// s'occupe de la direction de la ligne (nord ou sud) du comptage des jetons
		int colCpt 	=	col; 	// s'occupe de la direction la colonne (ouest ou est) du comptage des jetons

		while(  ligCpt >= 0 && ligCpt < this.nbLigne && colCpt >= 0 && colCpt < this.nbColonne 
				&& grille[ligCpt][colCpt] == grille[lig][col] )
		{
			ligCpt += ligDir; 
			colCpt += colDir;

			cpt++;

		}

		return cpt;
	}
}