package sources;

/*----Les-imports-pour-le-metier----*/
import sources.metier.Puissance4;
import sources.metier.Joueur;

/*----Les-imports-pour-l'-ihm----*/
import sources.ihm.FrameAccueil;
import sources.ihm.FramePlateau;
import java.awt.Dimension;

/*----Les-imports-pour-la-serialisation----*/
import sources.serialisation.Jeu;
import sources.serialisation.DisquetteJeu;
import java.util.Stack;

/**
	* Classe Controleur
	* @author 	: -
	* @version 	: 1.0
	* date 		! 07/08/2020
*/

public class Controleur
{
	/*---------------*/
	/* Les Attributs */
	/*---------------*/

	/**
		* Partie Métier
	*/ 
	private Puissance4 metier; // L'attribut pour traiter le jeu de façon transparente pour l'utilisateur.

	/**
		* La partie IHM (Interface Graphique c'est l'IHM = Interface Homme Machine).
	*/
	private FrameAccueil 	ihmAccueil; 	// L'attribut de la partie ihm concernant l'accueil du jeu.

	private FramePlateau 	ihmPlateau; 	// L'attribut de la partie ihm concernant la fenêtre du jeu.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur Controleur
		* À l'instanciation d'un objet Controleur, une fenêtre d'accueil du jeu apparaît 
		* avec le nom du joueur à saisir.
	*/
	public Controleur()
	{
		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;

		dimEcran     = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		this.ihmAccueil = new FrameAccueil(this, largeurEcran, hauteurEcran);
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/


	/*--------------------------------------*/
	/* 			LANCEMENT DU JEU 			*/
	/*--------------------------------------*/

	/**
		* Lance une partie de jeu
		* @param pseudoJoueur1 : Le pseudo du joueur 1.
		* @param pseudoJoueur2 : Le pseudo du joueur 2.
	*/
	public void lancerJeu(String pseudoJoueur1, String pseudoJoueur2)
	{
		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;

		dimEcran     = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		if ( this.metier == null ) 
		{
			this.metier = new Puissance4();
		}

		this.ihmPlateau = new FramePlateau(this, largeurEcran, hauteurEcran);

		for(int cptJoueur=0; cptJoueur<this.getNbJoueur(); cptJoueur++)
		{
			if ( (cptJoueur % 2) == 0 )  
			{
				this.getJoueur(cptJoueur).setPseudo(pseudoJoueur1);
			}
			else
			{
				this.getJoueur(cptJoueur).setPseudo(pseudoJoueur2);;
			}
		}

		// Mis à jour graphique pour le pseudo du joueur
		this.majGraphique();
	}

	/**
		* Relance une partie de jeu
		* @param joueur1 	: Le joueur 1.
		* @param joueur2 	: Le joueur 2.

	*/
	public void relancerJeu(Joueur joueur1, Joueur joueur2)
	{
		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;

		dimEcran     = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		for(int cptJoueur=0; cptJoueur<this.getNbJoueur(); cptJoueur++)
		{
			if ( (cptJoueur % 2) == 0 )
			{
				this.getJoueur(cptJoueur).setPseudo(joueur1.getPseudoJoueur() 	);
				this.getJoueur(cptJoueur).setCouleur(joueur1.getCouleur() 		);
				this.getJoueur(cptJoueur).setNumJoueur(joueur1.getNumJoueur() 	);
				this.getJoueur(cptJoueur).setActifJoueur(joueur1.getActif() 	);
			}
			else
			{
				this.getJoueur(cptJoueur).setPseudo(joueur2.getPseudoJoueur() 	);
				this.getJoueur(cptJoueur).setCouleur(joueur2.getCouleur() 		);
				this.getJoueur(cptJoueur).setNumJoueur(joueur2.getNumJoueur() 	);
				this.getJoueur(cptJoueur).setActifJoueur(joueur2.getActif() 	);
			}
		}

		// Mis à jour graphique pour le pseudo du joueur
		this.majGraphique();
	}

	/*------------------------------------------------------*/
	/*				MÉTHODES DE LA PARTIE METIER 			*/
	/*------------------------------------------------------*/

	/**
		* @return Retourne le nombre de lignes.
	*/
	public int getNbLigne()
	{
		return this.metier.getNbLigne();
	}

	/**
		* @return Retourne le nombre de colonnes.
	*/
	public int getNbColonne()
	{
		return this.metier.getNbColonne();
	}

	/**
		* @return Retourne le nombre de joueur.
	*/
	public int getNbJoueur()
	{
		return this.metier.getNbJoueur();
	}

	/**
		* @return Retourne le jeton présent à l'indice de la ligne et de la colonne précisé.
		* @param numLigne 	: le numéro de la ligne.
		* @param numColonne : le numéro de la colonne.
	*/
	public char getJeton(int numLigne, int numColonne)
	{
		return this.metier.getJeton(numLigne, numColonne);
	}

	/**
		* @return Retourne true : si la partie est gagnée / false : si la partie n'est pas gagnée.
	*/
	public boolean getPartieGagne()
	{
		return this.metier.getPartieGagne();
	}

	/**
		* @return Retourne true : si la grille est pleine / false : si la grille n'est pas pleine
	*/
	public boolean getGrillePleine()
	{
		return this.metier.getGrillePleine();
	}

	/**
		* @return Retourne un joueur.
		* @param  indiceJoueur : indice du joueur qui est retourné.
	*/
	public Joueur getJoueur(int indiceJoueur)
	{
		return this.metier.getJoueur(indiceJoueur);
	}

	/**
		* @return Retourne le joueur actif
	*/
	public Joueur getJoueurActif()
	{
		return this.metier.getJoueurActif();
	}

	/**
		* @return Retourne la couleur d'affichage.
	*/
	public boolean getCouleurAffichage()
	{
		return this.metier.getCouleurAffichage();
	}

	/**
		* @return Retourne true : si le joueur a gagné / false : si le joueur n'a pas gagné. 
	*/
	public boolean estGagnant()
	{
		return this.metier.estGagnant();
	}

	/**
		* Positionne un jeton sur la grille
		* @param numColonne : le numéro de la colonne.
	*/
	public boolean setJeton(int numColonne)
	{
		return this.metier.setJeton(numColonne);
	}

	/** 
		* Indique que c'est au joueur suivant de jouer. 
	*/
	public void setJoueurSuivant()
	{
		this.metier.setJoueurSuivant();
	}

	/**
		* Réinitialise la partie.
	*/
	public void reinitialiserPartie()
	{
		this.metier.reinitialiserPartie();
	}

	/*------------------------------------------------------*/
	/*				MÉTHODES DE LA PARTIE IHM 				*/
	/*------------------------------------------------------*/

	/**
		* Met à jour le plateau.
	*/
	public void majGraphique()
	{
		this.ihmPlateau.majGraphique();
	}
		
	/**
		* Fixe le mode sombre.
	*/
	public void setModeSombre()
	{
		this.metier.setCouleurAffichage();
		this.ihmPlateau.setModeSombre();
	}

	/**
		* Fixe le mode clair.
	*/
	public void setModeClair()
	{
		this.metier.setCouleurAffichage();
		this.ihmPlateau.setModeClair();
	}

	/**
		* Ferme l'interface d'accueil du jeu.
	*/
	public void fermerAccueil()
	{
		this.ihmAccueil.fermerAccueil();
	}

	/**
		* Quitte le jeu.
	*/
	public void fermerJeu()
	{
		this.ihmAccueil 	= null;
		this.metier 		= null;

		this.ihmPlateau.fermerFramePlateau();
		this.ihmPlateau = null;
	}

	/**
		* Permet de nettoyer la partie.
	*/
	private void nettoyerJeuOuverture()
	{
		this.metier = null;

		if ( this.ihmPlateau != null )
		{
			this.ihmPlateau.fermerFramePlateau();
		}
		this.ihmPlateau = null;
	}

	/*--------------------------------------------------------------*/
	/*				MÉTHODES DE LA PARTIE SÉRIALISATION				*/
	/*--------------------------------------------------------------*/

	/**
		* @return Retourne les noms des parties enregistrées.
	*/
	public String[] getJeuxEnregistres()
	{
		Stack<Jeu> ensJeux = new DisquetteJeu().getPileJeux();
		if ( ensJeux!= null )
		{
			String[] tabNomJeu = new String[ensJeux.size()];
			for(int cpt=0; cpt<tabNomJeu.length; cpt++)
			{
				tabNomJeu[cpt] = ensJeux.get(cpt).getNom();
			}

			return tabNomJeu;
		}
		return null;
	}

	/**
		* Permet de sauvegarder une partie.
		* @param nomJeu : le nom de la partie que le joueur sauvegarde.
	*/
	public void sauvegarder(String nomJeu)
	{
		DisquetteJeu disquetteJeu = new DisquetteJeu();
		disquetteJeu.sauvegardeJeu(nomJeu, this.metier);
	}

	/**
		* Permet d'ouvrir une partie.
		* @param nomJeu : le nom de la partie que le joueur ouvre.
	*/
	public void ouvrir(String nomJeu)
	{
		this.nettoyerJeuOuverture();

		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;

		dimEcran     = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		if ( this.metier == null && this.ihmPlateau == null)
		{
			this.metier 	= new Puissance4(nomJeu);
			this.ihmPlateau = new FramePlateau(this, largeurEcran, hauteurEcran);
		}

		if ( this.metier.getCouleurAffichage() )
		{
			this.setModeSombre();
		}

		this.majGraphique();
	}

	/*--------------------------------------------------------------------------------------*/
	/*							MAIN : ÉXÉCUTION DE L'APPLICATION							*/
	/*--------------------------------------------------------------------------------------*/

	public static void main(String[] args) 
	{
		new Controleur();	
	}

}