package sources.ihm;

/*----L'-import-pour-le-contrôleur----*/
import sources.Controleur;

/*----L'-import-pour-l'-ihm----*/
import sources.ihm.PanelAccueil;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

/**
	* Classe Frame Accueil
	* @author 	: -
	* @version 	: 1.0
	* date 		! 07/08/2020
*/

public class FrameAccueil extends JFrame
{
	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/*-------------------------------------*/
	/* Attribut pour l'affichage graphique */
	/*-------------------------------------*/
	private PanelAccueil panelAccueil; // L'attribut renseigne sur le Panel d'Accueil.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur FrameAccueil
		* @param ctrl : Le contrôleur.
		* @param largeurEcran : La largeur de l'écran de l'utilisateur.
		* @param hauteurEcran : La hauteur de l'écran de l'utilisateur.
	*/

	public FrameAccueil(Controleur ctrl, int largeurEcran, int hauteurEcran)
	{
		/*---------------------------------------*/
		/* Informations sur la Frame d'Accueil   */
		/*---------------------------------------*/
		this.setTitle("Accueil - Puissance 4");

		double coeffLargeur = (largeurEcran / (double) 850);
		double coeffHauteur = (hauteurEcran / (double) 470);
		int largeurFrameAccueil = (int) (largeurEcran / coeffLargeur);
		int hauteurFrameJoueur = (int) (hauteurEcran / coeffHauteur);

		this.setSize(largeurFrameAccueil,hauteurFrameJoueur);
		this.setLocation((largeurEcran/2)-(this.getWidth()/2),(hauteurEcran/2)-(this.getHeight()/2));
		this.setLayout( new BorderLayout() );

		/*------------------------*/
		/* Création du composant  */
		/*-------------------------*/
		this.panelAccueil = new PanelAccueil(ctrl);

		/*-----------------------------*/
		/* Positionnement du composant */
		/*-----------------------------*/
		this.add(panelAccueil);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/*--------------------------------------------------------------------------------------------------*/
	/* 											LA MÉTHODE 												*/
	/*--------------------------------------------------------------------------------------------------*/

	/*----------------------------------*/
	/*			LA FERMETURE			*/
	/*----------------------------------*/
	
	/**
		* Ferme la Frame d'Accueil.
	*/
	public void fermerAccueil()
	{
		this.panelAccueil.fermerAccueil();
		this.removeAll();
		this.setVisible(false);
		this.dispose();
	}
}