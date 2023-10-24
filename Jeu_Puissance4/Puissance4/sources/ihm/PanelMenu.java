package sources.ihm;

/*----L'-import-pour-le-contrôleur----*/
import sources.Controleur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Desktop;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;


/**
	* Classe Panel Menu
	* @author 	: -
	* @version 	: 1.0
	* date 		! 08/08/2020
*/

public class PanelMenu extends JPanel implements ActionListener
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
	private JMenuBar menuBar; 					// L'attribut pour la barre de menu.
	private JMenu menuPartie; 					// L'attribut pour l'onglet partie.
	private JMenu menuAffichage;				// L'attribut pour l'affichage.
	private JMenu menuAide; 					// L'attribut pour l'onglet aide.
	private JMenuItem menuItemEnregistrer; 		// L'attribut pour l'élément enregistrer.
	private JMenuItem menuItemOuvrir; 			// L'attribut pour l'élément ouvrir.
	private JMenuItem menuItemQuitter; 			// L'attribut pour l'élément quitter.
	private JMenuItem menuItemModeSombre;		// L'attribut pour mettre le jeu en mode sombre.
	private JMenuItem menuItemRegles; 			// L'attribut pour l'élément règles du jeu.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur PanelMenu
		* @param ctrl : Le contrôleur.
	*/
	public PanelMenu(Controleur ctrl)
	{
		/*---------------------------------*/
		/* Informations sur le panel menu  */
		/*---------------------------------*/
		this.ctrl = ctrl;
		this.setLayout( new FlowLayout() );
		this.setBackground(Color.WHITE);

		/*--------------------------*/
		/* Création des  composants */
		/*--------------------------*/
		this.menuBar 		= new JMenuBar();
		this.menuPartie 	= new JMenu("Partie");
		this.menuAffichage 	= new JMenu("Affichage");
		this.menuAide 		= new JMenu("Aide");

		this.menuItemEnregistrer 	= new JMenuItem("Enregistrer");
		this.menuItemOuvrir 		= new JMenuItem("Ouvrir");
		this.menuItemQuitter		= new JMenuItem("Quitter");
		this.menuItemModeSombre 	= new JMenuItem("Mode Sombre");
		this.menuItemRegles 		= new JMenuItem("Règles du jeu"); 

		this.menuBar.setBackground(Color.WHITE);
		this.menuBar.setBorder(null);
		this.menuPartie.setBorder(null);

		/*-----------------------*/
		/* Les polices utilisées */
		/*-----------------------*/
		Font policeMenu 	= new Font("Calibri", Font.TYPE1_FONT, 15);
		Font policeMenuItem = new Font("Calibri", Font.TYPE1_FONT, 15);

		this.setModeClair();

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.menuPartie.add(this.menuItemEnregistrer);
		this.menuPartie.add(this.menuItemOuvrir);
		this.menuPartie.add(this.menuItemQuitter);
		this.menuAffichage.add(this.menuItemModeSombre);
		this.menuAide.add(this.menuItemRegles);
		this.menuBar.add(this.menuPartie);
		this.menuBar.add(this.menuAffichage);
		this.menuBar.add(this.menuAide);
		this.add(this.menuBar);

		this.menuPartie.setFont(policeMenu); 
		this.menuAffichage.setFont(policeMenu);
		this.menuAide.setFont(policeMenu);
		this.menuItemEnregistrer.setFont(policeMenuItem);
		this.menuItemOuvrir.setFont(policeMenuItem);
		this.menuItemQuitter.setFont(policeMenuItem);
		this.menuItemModeSombre.setFont(policeMenuItem);
		this.menuItemRegles.setFont(policeMenuItem);

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.menuItemEnregistrer.addActionListener(this);
		this.menuItemOuvrir.addActionListener(this);
		this.menuItemQuitter.addActionListener(this);
		this.menuItemModeSombre.addActionListener(this);
		this.menuItemRegles.addActionListener(this);

		this.setVisible(true);
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* Fixe le mode sombre.
	*/
	public void setModeSombre()
	{
		this.menuItemModeSombre.setText("Mode Clair");

		this.menuBar.setBackground(Color.DARK_GRAY);

		this.menuPartie.setForeground(Color.WHITE);
		this.menuAffichage.setForeground(Color.WHITE);
		this.menuAide.setForeground(Color.WHITE);

		this.menuItemEnregistrer.setBackground(Color.DARK_GRAY);
		this.menuItemOuvrir.setBackground(Color.DARK_GRAY);
		this.menuItemQuitter.setBackground(Color.DARK_GRAY);
		this.menuItemModeSombre.setBackground(Color.DARK_GRAY);
		this.menuItemRegles.setBackground(Color.DARK_GRAY);

		this.menuItemEnregistrer.setForeground(Color.WHITE);
		this.menuItemOuvrir.setForeground(Color.WHITE);
		this.menuItemQuitter.setForeground(Color.WHITE);
		this.menuItemModeSombre.setForeground(Color.WHITE);
		this.menuItemRegles.setForeground(Color.WHITE);

		this.setBackground(Color.DARK_GRAY);
	}

	/**
		* Fixe le mode clair.
	*/
	public void setModeClair()
	{
		this.menuItemModeSombre.setText("Mode Sombre");

		this.menuBar.setBackground(Color.WHITE);

		this.menuPartie.setForeground(Color.BLACK);
		this.menuAffichage.setForeground(Color.BLACK);
		this.menuAide.setForeground(Color.BLACK);

		this.menuItemEnregistrer.setBackground(Color.WHITE);
		this.menuItemOuvrir.setBackground(Color.WHITE);
		this.menuItemQuitter.setBackground(Color.WHITE);
		this.menuItemModeSombre.setBackground(Color.WHITE);
		this.menuItemRegles.setBackground(Color.WHITE);

		this.menuItemEnregistrer.setForeground(Color.BLACK);
		this.menuItemOuvrir.setForeground(Color.BLACK);
		this.menuItemQuitter.setForeground(Color.BLACK);
		this.menuItemModeSombre.setForeground(Color.BLACK);
		this.menuItemRegles.setForeground(Color.BLACK);

		this.setBackground(Color.WHITE);
	}

	/*----------------------------------*/
	/*			LA FERMETURE			*/
	/*----------------------------------*/

	/**
		* Ferme le PanelMenu.
	*/
	public void fermerPanelMenu()
	{
		this.removeAll();
		this.setVisible(false);
	}


	/*----------------------------------------------*/
	/*			L'ÉVÈNEMENT	ACTION-LISTENER			*/
	/*----------------------------------------------*/
	
	/**
		* Si le joueur clique sur la barre de menu.
	*/
	public void actionPerformed(ActionEvent e)
	{
		/*-----------------------------*/
		/* Enregistrement d'une partie */
		/*-----------------------------*/ 
		if ( e.getSource() == this.menuItemEnregistrer)
		{
			String nomJeu = JOptionPane.showInputDialog(this,"Entrer le nom de la partie : ", "Enregistrement d'une partie", JOptionPane.QUESTION_MESSAGE); 

			if ( nomJeu == null || nomJeu.equals("") )
			{
				nomJeu = "partie_enregistree_par_defaut";
			}

			this.ctrl.sauvegarder(nomJeu);
		}
		/*------------------------------------*/
		/* Ouverture d'une partie enregistrée */
		/*------------------------------------*/
		if ( e.getSource() == this.menuItemOuvrir) 
		{
			String[] ensJeux = this.ctrl.getJeuxEnregistres();
			if( ! (ensJeux == null) )
			{
				JComboBox<String> cbox = new JComboBox<>(ensJeux);
				cbox.setEditable(true);
				int ouvertureOk = JOptionPane.showConfirmDialog(this, cbox, "Ouverture d'une partie", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if ( ouvertureOk == JOptionPane.OK_OPTION)
				{
					Object partieEnregistre = cbox.getSelectedItem();
					this.ctrl.ouvrir(partieEnregistre.toString());
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Aucune partie enregistrée", "Ouverture d'une partie", JOptionPane.ERROR_MESSAGE);
			}
		}
		/*-------------------*/
		/* Quitter la partie */
		/*-------------------*/
		if ( e.getSource() == this.menuItemQuitter) 
		{
			this.ctrl.fermerJeu();
		}
		/*-------------*/
		/* Mode Sombre */
		/*-------------*/
		if ( e.getSource() == this.menuItemModeSombre) 
		{
			if ( ! this.ctrl.getCouleurAffichage() )
			{
				this.ctrl.setModeSombre();
			}
			else 
			{
				this.ctrl.setModeClair();
			}
		}
		/*--------------------*/
		/* Lecture des règles */
		/*-------------------*/
		if ( e.getSource() == this.menuItemRegles ) 
		{
			 JOptionPane.showMessageDialog(this, "Gagner une partie en étant le premier à aligner 4 jetons de sa couleur horizontalement, verticalement et diagonalement. \n Si lors d'une partie, tous les jetons ont été joués sans qu'il n'y est de jetons alignés, la partie est égalité.");
		}
	}
}