/*
 * @(#)MainFrame.java 25 Feb 2014
 */
package com.codecrocodile.client;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * @author Chris Hatton
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private WindowListener windowListener;
	
	private FrontPanel frontPanel;
	
	
	public MainFrame() {
		super("Numeral Converter");
		this.setSize(500, 250);
		this.setResizable(false);
		
		this.windowListener = new WindowListener();
		this.addWindowListener(windowListener);
		this.frontPanel = new FrontPanel();
		this.add(frontPanel, BorderLayout.CENTER);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private class WindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exitApplication();
		}
	}

	private void exitApplication() {
		this.dispose();
		System.exit(0);
	}
}
