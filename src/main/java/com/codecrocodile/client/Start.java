/*
 * @(#)Start.java 25 Feb 2014
 */
package com.codecrocodile.client;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * @author Chris Hatton
 */
public class Start {
	
	public static void main(String[] args) { 
		initLookAndFeel();
		createAndShowGUI();
    }
	
	private static void initLookAndFeel() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
	} 

	private static void createAndShowGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}

}
