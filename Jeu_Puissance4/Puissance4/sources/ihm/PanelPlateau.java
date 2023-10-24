package sources.ihm;

/*----L'-import-pour-le-contrôleur----*/
import sources.Controleur;

/*----L'-import-pour-le-metier----*/
import sources.metier.Joueur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Font;
import java.awt.BasicStroke;

import javax.swing.*;
import javax.swing.BorderFactory;

import java.io.File;


/**
	* Classe Panel Plateau
	* @author 	: -
	* @version 	: 1.0
	* date 		! 08/08/2020
*/

public class PanelPlateau extends JPanel
{
	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;

	/*--------------------------------------*/
	/* Attributs pour l'affichage graphique */
	/*--------------------------------------*/
	private PanelGrille 		panelGrille; 			// L'attribut renseigne sur le PanelGrille.
	private PanelMessageJoueur 	panelMessageJoueur; 	// L'attribut renseigne sur le PanelMessageJoueur.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur PanelPlateau
		* @param ctrl : Le contrôleur.
	*/
	public PanelPlateau(Controleur ctrl)
	{
		/*-----------------------------------*/
		/* Informations sur le panel plateau */
		/*-----------------------------------*/
		this.setLayout( new BorderLayout(0,10) );
		this.setBackground(Color.WHITE);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelGrille		= new PanelGrille(ctrl);
		this.panelMessageJoueur = new PanelMessageJoueur(ctrl);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add(this.panelGrille, 			BorderLayout.CENTER);
		this.add(this.panelMessageJoueur, 	BorderLayout.SOUTH);

		this.setVisible(true);
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/
	
	/*----------------------------------*/
	/*			LA MISE À JOUR			*/
	/*----------------------------------*/

	/**
		* Met à jour le panel grille et le panel message joueur.
	*/
	public void majGraphique()
	{
		this.panelGrille.majGraphique();
		this.panelMessageJoueur.majGraphique();
	}

	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* Fixe le mode sombre.
	*/
	public void setModeSombre()
	{
		this.panelGrille.setModeSombre();
		this.panelMessageJoueur.setModeSombre();
		this.setBackground(Color.DARK_GRAY);
	}

	/**
		* Fixe le mode clair.
	*/
	public void setModeClair()
	{
		this.panelGrille.setModeClair();
		this.panelMessageJoueur.setModeClair();
		this.setBackground(Color.WHITE);
	}

	/*----------------------------------*/
	/*			LA FERMETURE			*/
	/*----------------------------------*/

	/**
		* Ferme le Panel Plateau.
	*/
	public void fermerPanelPlateau()
	{
		this.panelGrille.fermerPanelGrille();
		this.panelMessageJoueur.fermerMessageJoueur();

		this.removeAll();
		this.setVisible(false);
	}

	private class PanelGrille extends JPanel implements ActionListener
	{
		/**
			* Définition du serialVersionUID
		*/
		private static final long serialVersionUID = 1L;
		
		/*----------------------*/
		/* Attribut pour le jeu */
		/*----------------------*/
		private Controleur ctrl; 					// L'attribut renseigne sur le contrôleur.

		/*-------------------------------------*/
		/* Attribut pour l'affichage graphique */
		/*-------------------------------------*/
		private JPanel 		panelEnsBtn; 			// L'attribut renseigne sur le panel contenant les boutons.
		private JButton[] 	tabBtnJeton; 			// L'attribut renseigne sur les boutons jetons.

		/*-----------------*/
		/* Le Constructeur */
		/*-----------------*/

		/**
			* Constructeur PanelGrille
			* @param ctrl : Le contrôleur.
		*/
		public PanelGrille(Controleur ctrl)
		{
			/*----------------------------------*/
			/* Informations sur le panel grille */
			/*----------------------------------*/
			this.ctrl = ctrl;
			this.setLayout( new BorderLayout() );
			this.setBackground( Color.WHITE );

			/*-------------------------*/
			/* Création des composants */
			/*-------------------------*/
			this.panelEnsBtn = new JPanel();
			this.panelEnsBtn.setLayout( new GridLayout(1, this.ctrl.getNbColonne()) );
			this.panelEnsBtn.setBackground( Color.WHITE );

			this.tabBtnJeton = new JButton[this.ctrl.getNbColonne()];
			
			File 		fichierBtnJeton 	= new File("./ressources/images/fleche_position_jeton.png");
			ImageIcon 	imgIconBtnJeton 	= new ImageIcon(fichierBtnJeton.getPath());

			// Redimension de l'icon pour les boutons
			Image 		imgBtnJetonPrepare 	= imgIconBtnJeton.getImage();
			Image 		imgBtnJetonResize 	= imgBtnJetonPrepare.getScaledInstance(90,30, Image.SCALE_SMOOTH);
			imgIconBtnJeton 				= new ImageIcon(imgBtnJetonResize);

			for(int cptColonne=0; cptColonne<this.ctrl.getNbColonne(); cptColonne++)
			{
				this.tabBtnJeton[cptColonne] = new JButton(imgIconBtnJeton);
			}

			/*-------------------------------*/
			/* Positionnement des composants */
			/*-------------------------------*/
			for(int cptColonne=0; cptColonne<this.ctrl.getNbColonne(); cptColonne++)
			{
				this.panelEnsBtn.add(this.tabBtnJeton[cptColonne]);
			}
			this.add(this.panelEnsBtn, BorderLayout.NORTH);

			/*---------------------------*/
			/* Activation des composants */
			/*---------------------------*/
			for(int cptColonne=0; cptColonne<this.ctrl.getNbColonne(); cptColonne++)
			{
				this.tabBtnJeton[cptColonne].addActionListener(this);
			}
			
			this.setVisible(true);
		}

		/*------------------------------------------------------------------------------------------------------*/
		/* 											LES MÉTHODES 												*/
		/*------------------------------------------------------------------------------------------------------*/

		/*------------------*/
		/* Le fond du panel */
		/*------------------*/
		public void paintComponent(Graphics g)
		{
			String     sImage;
			Image      img;
			Graphics2D g2 = (Graphics2D) g;

			super.paintComponent(g);
			
			// Réactif avec les pourcentages
			int largeurEspace 	= 0;
			int hauteurEspace 	= 0;
			int tailleLargeur 	= 0;
			int tailleHauteur 	= 0;

			// Dessine les jetons
			largeurEspace 	= (int) ( this.getWidth() 	* ( (double) 4/100 	) );
			tailleLargeur 	= (int) ( this.getWidth() 	* ( (double) 13/100 ) );
			tailleHauteur 	= (int) ( this.getHeight() 	* ( (double) 15/100 ) );

			for(int cptColonne=0; cptColonne<this.ctrl.getNbColonne(); cptColonne++)
			{
				hauteurEspace 	= (int) ( this.getHeight() 	* ( (double) 9/100 	) );
				for(int cptLigne=0; cptLigne<this.ctrl.getNbLigne(); cptLigne++)
				{
					char sCouleur = this.ctrl.getJeton(cptLigne, cptColonne);
					img = getToolkit().getImage ( "./ressources/images/jeton_" + sCouleur + ".png" );

					g2.drawImage ( img, largeurEspace, hauteurEspace, tailleLargeur, tailleHauteur, this );
					hauteurEspace += (int) ( this.getHeight() * ( (double) 15/100 ) );
				}
				largeurEspace += (int) ( this.getWidth() * ( (double) 13/100 ) );
			}

			largeurEspace = 0;
			hauteurEspace = 0;
			tailleLargeur = 0;
			tailleHauteur = 0;

			// Bordure du plateau
			largeurEspace = (int) ( this.getWidth() 	* ( (double) 4/100 ) );
			hauteurEspace = (int) ( this.getHeight() 	* ( (double) 9/100 ) );
			tailleLargeur = (int) ( this.getWidth() 	* ( (double) 90/100 ) );
			tailleHauteur = (int) ( this.getHeight() 	* ( (double) 89/100 ) );

			int bordureEpaisseur = 5;

			g2.setStroke( new BasicStroke(bordureEpaisseur, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
			g2.setColor( new Color(0,41,255) );
			g2.drawRect( largeurEspace, hauteurEspace, tailleLargeur, tailleHauteur );

			// Réajuste le panel contenant les boutons
			int margeGauche = (int) ( this.getWidth() 	* ( (double) 4/100 	) );
			int margeDroite = (int) ( this.getWidth() 	* ( (double) 5/100 	) );
			this.panelEnsBtn.setBorder( BorderFactory.createEmptyBorder(0, margeGauche, 0, margeDroite) );

			// Redimensionne les boutons
			tailleLargeur 	= (int) ( this.getWidth() 	* ( (double) 5/100 ) );
			tailleHauteur 	= (int) ( this.getHeight() 	* ( (double) 6/100 ) );
			for(int cptColonne=0; cptColonne<this.ctrl.getNbColonne(); cptColonne++)
			{
				this.tabBtnJeton[cptColonne].setPreferredSize( new Dimension(tailleLargeur, tailleHauteur) );
			}
		}

		/*----------------------------------*/
		/*			LA MISE À JOUR			*/
		/*----------------------------------*/

		/**
			* Met à jour la grille.
		*/
		public void majGraphique()
		{
			this.repaint();
		}

		/*------------------------------*/
		/*			L'AFFICHAGE			*/
		/*------------------------------*/

		/**
			* Fixe le mode sombre.
		*/
		public void setModeSombre()
		{
			this.panelEnsBtn.setBackground(Color.DARK_GRAY);
			this.setBackground(Color.DARK_GRAY);
		}

		/**
			* Fixe le mode clair.
		*/
		public void setModeClair()
		{
			this.panelEnsBtn.setBackground(Color.WHITE);
			this.setBackground(Color.WHITE);
		}

		/*----------------------------------*/
		/*			LA FERMETURE			*/
		/*----------------------------------*/

		/**
			* Ferme le Panel Grille.
		*/
		public void fermerPanelGrille()
		{
			this.removeAll();
			this.setVisible(false);
		}

		/*----------------------------------------------*/
		/*			L'ÉVÈNEMENT	ACTION-LISTENER			*/
		/*----------------------------------------------*/
		
		/**
			* Si le joueur clique sur l'un des boutons des actions.
		*/
		public void actionPerformed(ActionEvent e)
		{
			for(int cptColonne=0; cptColonne<this.tabBtnJeton.length; cptColonne++)
			{
				if ( e.getSource() == this.tabBtnJeton[cptColonne] )
				{
					boolean bAction = this.ctrl.setJeton(cptColonne);

					if ( bAction )
					{
						// Mis à jour du plateau après l'action jouée
						this.ctrl.majGraphique();

						// Vérification du gagnant ou Vérification égalité
						if ( this.ctrl.estGagnant() || this.ctrl.getGrillePleine() )
						{
							if ( this.ctrl.estGagnant() )
							{
								this.optionPaneVictoire();
							}
							else
							{
								this.optionPaneEgalite();
							}	
						}
						else
						{
							// Joueur suivant
							this.ctrl.setJoueurSuivant();

							// Mis à jour du plateau pour le label message joueur
							this.ctrl.majGraphique();
						}
					}
					else
					{
						String pseudoJoueur = this.ctrl.getJoueurActif().getPseudoJoueur();
						String sMessErreur = pseudoJoueur + ", cette colonne est remplie de jetons.";
						JOptionPane.showMessageDialog(this, sMessErreur, "Action impossible", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		/**
			* JOptionPane lorsque le joueur gagne.
		*/
		private void optionPaneVictoire()
		{
			String 	messagePartie 	= "";
			String 	messageTitre 	= "";
			int 	optConfirme 	= -1;
			String 	pseudoJoueur 	= this.ctrl.getJoueurActif().getPseudoJoueur();
	
			messagePartie 	= "Bravo " + pseudoJoueur + ", vous avez gagné";
			messagePartie 	+= "\n" + "Voulez vous rejouer ?";
			messageTitre 	= "Victoire";
			optConfirme 	= JOptionPane.showConfirmDialog(this, messagePartie, messageTitre, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if ( optConfirme == JOptionPane.YES_OPTION )
			{
				this.lancerNouvellePartie();
			}
			else
			{
				this.ctrl.fermerJeu();
			}
		}

		/**
			* JOptionPane lorsque la partie est à égalité (aucuns des joueurs ne gagnent).
		*/
		private void optionPaneEgalite()
		{
			String 	messagePartie 	= "";
			String 	messageTitre 	= "";
			int 	optConfirme 	= -1;
			
			messagePartie = "Vous êtes à égalité, la grille est pleine";
			messagePartie += "\n" + "Voulez vous rejouer ?";
			messageTitre 	= "Égalité";
			optConfirme = JOptionPane.showConfirmDialog(this, messagePartie, messageTitre, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if ( optConfirme == JOptionPane.YES_OPTION)
			{
				this.lancerNouvellePartie();
			}
			else
			{
				this.ctrl.fermerJeu();
			}
		}

		private void lancerNouvellePartie()
		{
			Joueur joueur1 = null;
			Joueur joueur2 = null;

			for(int cptJoueur=0; cptJoueur<this.ctrl.getNbJoueur(); cptJoueur++)
			{
				if ( (cptJoueur % 2) == 0 )  
				{
					joueur1 = this.ctrl.getJoueur(cptJoueur);
				}
				else
				{
					joueur2 = this.ctrl.getJoueur(cptJoueur);
				}
			}

			this.ctrl.reinitialiserPartie();
			this.ctrl.relancerJeu(joueur1, joueur2);
			this.majGraphique();
		}
	}

	private class PanelMessageJoueur extends JPanel
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
		private JPanel panelLblMessageJoueur; 		// L'attribut renseigne sur le panel contenant le message pour les joueurs.
		private JLabel lblMessage; 					// L'attribut renseigne sur le message du label.
		
		/*-----------------*/
		/* Le Constructeur */
		/*-----------------*/

		/**
			* Constructeur PanelMessageJoueur
			* @param ctrl : Le contrôleur.
		*/
		public PanelMessageJoueur(Controleur ctrl)
		{
			/*------------------------------------------*/
			/* Informations sur le panel message joueur */
			/*------------------------------------------*/
			this.ctrl = ctrl;
			this.setLayout( new BorderLayout() );

			/*-------------------------*/
			/* Création des composants */
			/*-------------------------*/
			this.panelLblMessageJoueur = new JPanel();
			this.panelLblMessageJoueur.setLayout( new FlowLayout() );
			this.panelLblMessageJoueur.setBackground(Color.WHITE);

			String pseudoJoueur = "";

			this.lblMessage = new JLabel(pseudoJoueur + ", c'est à vous de jouer !");

			/*-------------------------------*/
			/* Positionnement des composants */
			/*-------------------------------*/
			this.panelLblMessageJoueur.add(this.lblMessage, JLabel.CENTER);
			this.add(this.panelLblMessageJoueur, BorderLayout.CENTER);


			this.setVisible(true);
		}

		/*------------------------------------------------------------------------------------------------------*/
		/* 											LES MÉTHODES 												*/
		/*------------------------------------------------------------------------------------------------------*/

		/*----------------------------------*/
		/*			LA MISE À JOUR			*/
		/*----------------------------------*/

		/**
			* Met à jour le lblMessage.
		*/
		public void majGraphique()
		{
			String pseudoJoueur = this.ctrl.getJoueurActif().getPseudoJoueur();

			String messageJoueur = ", c'est à vous de jouer !";

			if ( this.ctrl.estGagnant() || this.ctrl.getGrillePleine() )
			{
				if ( this.ctrl.getGrillePleine() )
				{
					pseudoJoueur 	= "";
					messageJoueur 	= "Égalité";
				}
				else
				{
					messageJoueur 	= " : Victoire";
				}	
			}

			this.lblMessage.setText(pseudoJoueur + messageJoueur);
			
			if ( this.ctrl.getJoueurActif().getCouleur() == 'J' && (! (this.ctrl.getGrillePleine()) ) )
			{
				this.lblMessage.setForeground( Color.ORANGE );
			}
			else if ( this.ctrl.getJoueurActif().getCouleur() == 'R' && (! (this.ctrl.getGrillePleine()) ) )
			{
				this.lblMessage.setForeground( Color.RED );
			}
			else
			{
				this.lblMessage.setForeground( Color.BLACK );
			}
		}

		/*------------------------------*/
		/*			L'AFFICHAGE			*/
		/*------------------------------*/

		/**
			* Fixe le mode sombre.
		*/
		public void setModeSombre()
		{
			this.panelLblMessageJoueur.setBackground(Color.DARK_GRAY);
			this.setBackground(Color.DARK_GRAY);
		}

		/**
			* Fixe le mode clair.
		*/
		public void setModeClair()
		{
			this.panelLblMessageJoueur.setBackground(Color.WHITE);
			this.setBackground(Color.WHITE);
		}

		/*----------------------------------*/
		/*			LA FERMETURE			*/
		/*----------------------------------*/

		/**
			* Ferme le Panel Message Joueur.
		*/
		public void fermerMessageJoueur()
		{
			this.removeAll();
			this.setVisible(false);
		}
	}
}