package fr.eni.demoSwing.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EcranMorpion extends JFrame {
	
	static final String DEFAULT = ""; 
	static final String CROIX = "X"; 
	static final String ROND = "O";
	static final String RESET = "RESET";
	static final String RESTART = "RESTART";
	
	private JTextField instruction ;
	private String nomJ1 = "J1", nomJ2 = "J2";
	private Integer count1 = 1;
	private Integer j1 = 0, j2 = 0;
	private JButton hg, hm, hd, mg, mm, md, bg, bm, bd, joueur1, joueur2;
	
	
	public EcranMorpion() {
		this.setTitle("Morpion");
		this.setSize(new Dimension(250, 250));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initIHM();
	}
	
	// Construction de la grille
	private void initIHM() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.lightGray);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		panel.add(getInstruction(), gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		hg = getBtn(DEFAULT);
		panel.add(hg, gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		hm = getBtn(DEFAULT);
		panel.add(hm, gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		hd = getBtn(DEFAULT);
		panel.add(hd, gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		mg = getBtn(DEFAULT);
		panel.add(mg, gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		mm = getBtn(DEFAULT);
		panel.add(mm, gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		md = getBtn(DEFAULT);
		panel.add(md, gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		bg = getBtn(DEFAULT);
		panel.add(bg, gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		bm = getBtn(DEFAULT);
		panel.add(bm, gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		bd = getBtn(DEFAULT);
		panel.add(bd, gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(getLabel(nomJ1), gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(restart(), gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(getLabel(nomJ2), gbc);
		
		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		joueur1 = getJoueur1();
		panel.add(joueur1, gbc);
		
		gbc.gridy = 5;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(reset(), gbc);
		
		
		gbc.gridy = 5;
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		joueur2 = getJoueur2();
		panel.add(joueur2, gbc);
		
		
		this.setContentPane(panel);
		
	}

	// Méthodes
	public JButton getJoueur1() {
		JButton btnJ = new JButton(j1.toString());
		return btnJ;
	}

	
	public JButton getJoueur2() {
		JButton btnJ = new JButton(j2.toString());
		return btnJ;
	}
	
	public JLabel getLabel(String nom) {
		JLabel lbl = new JLabel(nom.trim().toLowerCase());
		return lbl;
	}
	
	public JTextField getInstruction() {
		instruction = new JTextField(19);
		instruction.setText("Au joueur 1 de jouer.");
		return instruction;
	}

	public JButton getBtn(String s) {
		JButton btnS = new JButton(s);
		btnS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (count1 > 0 && btnS.getText().equals(DEFAULT)) {
					btnS.setText(CROIX);
					instruction.setText("Au joueur 2 de jouer");
					count1--;
				} else if (count1 == 0 && btnS.getText().equals(DEFAULT)) {
					btnS.setText(ROND);
					instruction.setText("Au joueur 1 de jouer");
					count1++;
				} else {
				
				}
				gagne();
				
			}
		});
		return btnS;
	}
	

	
	public JTextField gagne() {
		if ((hg.getText().equals(CROIX) && hm.getText().equals(CROIX) && hd.getText().equals(CROIX)) ||
			(mg.getText().equals(CROIX) && mm.getText().equals(CROIX) && md.getText().equals(CROIX)) ||
			(bg.getText().equals(CROIX) && bm.getText().equals(CROIX) && bd.getText().equals(CROIX)) ||
			(hg.getText().equals(CROIX) && mg.getText().equals(CROIX) && bg.getText().equals(CROIX)) ||
			(hm.getText().equals(CROIX) && mm.getText().equals(CROIX) && bm.getText().equals(CROIX)) ||
			(hd.getText().equals(CROIX) && md.getText().equals(CROIX) && md.getText().equals(CROIX)) ||
			(hg.getText().equals(CROIX) && mm.getText().equals(CROIX) && bd.getText().equals(CROIX)) ||
			(hd.getText().equals(CROIX) && mm.getText().equals(CROIX) && bg.getText().equals(CROIX))) {
			j1++;
			instruction.setText("Le joueur 1 gagne");
			
			
		} else if ((hg.getText().equals(ROND) && hm.getText().equals(ROND) && hd.getText().equals(ROND)) ||
				  (mg.getText().equals(ROND) && mm.getText().equals(ROND) && md.getText().equals(ROND)) ||
				  (bg.getText().equals(ROND) && bm.getText().equals(ROND) && bd.getText().equals(ROND)) ||
				  (hg.getText().equals(ROND) && mg.getText().equals(ROND) && bg.getText().equals(ROND)) ||
				  (hm.getText().equals(ROND) && mm.getText().equals(ROND) && bm.getText().equals(ROND)) ||
				  (hd.getText().equals(ROND) && md.getText().equals(ROND) && md.getText().equals(ROND)) ||
				  (hg.getText().equals(ROND) && mm.getText().equals(ROND) && bd.getText().equals(ROND)) ||
				  (hd.getText().equals(ROND) && mm.getText().equals(ROND) && bg.getText().equals(ROND))) {
			j2++;
			instruction.setText("Le joueur 2 gagne");
			
		} else {
			
		}
		return instruction;
	}
	
	public JButton reset() {
		JButton reset = new JButton(RESET);
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				j1 = 0; 
				j2 = 0;
				hg.setText(DEFAULT);
				hm.setText(DEFAULT);
				hd.setText(DEFAULT);
				mg.setText(DEFAULT);
				mm.setText(DEFAULT);
				md.setText(DEFAULT);
				bg.setText(DEFAULT);
				bm.setText(DEFAULT);
				bd.setText(DEFAULT);
				joueur1.setText(j1.toString());
				joueur2.setText(j2.toString());
				instruction.setText("Au joueur 1 de jouer");
				count1 = 1;
				
			}
		});
		return reset;
	}
	
	public JButton restart() {
		JButton restart = new JButton(RESTART);
		restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hg.setText(DEFAULT);
				hm.setText(DEFAULT);
				hd.setText(DEFAULT);
				mg.setText(DEFAULT);
				mm.setText(DEFAULT);
				md.setText(DEFAULT);
				bg.setText(DEFAULT);
				bm.setText(DEFAULT);
				bd.setText(DEFAULT);
				joueur1.setText(j1.toString());
				joueur2.setText(j2.toString());
				instruction.setText("Au joueur 1 de jouer");
				count1 = 1;
			}
		});
		return restart;
	}
}
