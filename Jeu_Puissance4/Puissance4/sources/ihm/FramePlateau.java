package sources.ihm;

/*----L'-import-pour-le-contrôleur----*/
import sources.Controleur;

/*----Les-imports-pour-l'-ihm----*/
import sources.ihm.PanelPlateau;
import sources.ihm.PanelMenu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.UIManager;


/**
	* Classe Frame Plateau
	* @author 	: -
	* @version 	: 1.0
	* date 		! 08/08/2020
*/

public class FramePlateau extends JFrame
{
	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;

	/*----------------------*/
	/* Attribut pour le jeu */
	/*----------------------*/
	private Controleur ctrl; 					// L'attribut renseigne sur le contrôleur.
	
	/*--------------------------------------*/
	/* Attributs pour l'affichage graphique */
	/*--------------------------------------*/
	private PanelMenu 		panelMenu; 			// L'attribut renseigne sur le PanelMenu.
	private PanelPlateau 	panelPlateau;		// L'attribut renseigne sur le PanelPlateau.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur FramePlateau
		* @param ctrl 			: Le contrôleur.
		* @param largeurEcran 	: La largeur de l'écran de l'utilisateur.
		* @param hauteurEcran 	: La hauteur de l'écran de l'utilisateur.
	*/
	public FramePlateau(Controleur ctrl, int largeurEcran, int hauteurEcran)
	{
		/*-------------------------------------*/
		/* Informations sur la Frame Plateau   */
		/*-------------------------------------*/
		this.ctrl = ctrl;
		this.setTitle("Le jeu Puissance 4");
		double coeffLargeur = (largeurEcran / (double) 700);
		double coeffHauteur = (hauteurEcran / (double) 600);

		int largeurFrameJeu = (int) (largeurEcran / coeffLargeur);
		int hauteurFrameJeu = (int) (hauteurEcran / coeffHauteur);
		
		this.setSize(largeurFrameJeu, hauteurFrameJeu); 
		this.setLocation( (largeurEcran/2)-(this.getWidth()/2), (hauteurEcran/2)-(this.getHeight()/2) );
		this.setLayout( new BorderLayout() );

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelMenu 		= new PanelMenu(ctrl);
		this.panelPlateau 	= new PanelPlateau(ctrl);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add(panelMenu, 	BorderLayout.NORTH);
		this.add(panelPlateau, 	BorderLayout.CENTER);

		/*-------------------------*/
		/* Activation du composant */
		/*-------------------------*/
		this.addWindowListener( new FermetureFrame() );

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*----------------------------------*/
	/*			LA MISE À JOUR			*/
	/*----------------------------------*/

	/**
		* Met à jour la frame plateau.
	*/
	public void majGraphique()
	{
		this.panelPlateau.majGraphique();
	}

	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* Fixe le mode sombre.
	*/
	public void setModeSombre()
	{
		this.panelMenu.setModeSombre();
		this.panelPlateau.setModeSombre();
	}

	/**
		* Fixe le mode clair.
	*/
	public void setModeClair()
	{
		this.panelMenu.setModeClair();
		this.panelPlateau.setModeClair();
	}

	/*----------------------------------*/
	/*			LA FERMETURE			*/
	/*----------------------------------*/

	/**
		* Ferme la FramePlateau.
	*/
	public void fermerFramePlateau()
	{
		this.panelMenu.fermerPanelMenu();
		this.panelPlateau.fermerPanelPlateau();
		this.dispose();
	}

	/**
		* Fermeture de la fenêtre graphique.
	*/
	private class FermetureFrame extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			FramePlateau.this.ctrl.fermerJeu();
		}
	}
}