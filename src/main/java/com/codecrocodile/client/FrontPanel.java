/*
 * @(#)FrontPanel.java 25 Feb 2014
 */
package com.codecrocodile.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.codecrocodile.NumeralConverter;
import com.codecrocodile.NumeralConverterException;

/**
 * @author Chris Hatton
 */
@SuppressWarnings("serial")
public class FrontPanel extends JPanel {
	
	private NumeralConverter numeralConverter = new NumeralConverter();
	
	private JTextField romanTxt;
	
	private JTextField arabicTxt;
	
	private JButton convertToRomanBtn;
	
	private JButton convertToArabicBtn;
	
	private JLabel messageLbl;

	
	public FrontPanel() {
		this.setLayout(new BorderLayout());
    	this.setOpaque(true);
    	this.setBackground(new Color(0x66a1d2));
    	this.add(buildInputPanel(), BorderLayout.NORTH);
    	this.add(buildMessagePanel(), BorderLayout.CENTER);
    }
    
    private JPanel buildInputPanel() {
    	JPanel panel = new JPanel(new GridLayout(1, 2));
    	panel.setOpaque(false);
    	panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    	panel.add(buildArabicPanel());
    	panel.add(buildRomanPanel());
    	
    	return panel;
    }
    
    private JPanel buildArabicPanel() {
    	JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
    	panel.setOpaque(false);
    	JLabel arabicLbl = new JLabel("Arabic Numerals");
    	arabicTxt = new JTextField();
    	arabicTxt.setDocument(new IntFilterDocument());
    	convertToRomanBtn = new JButton("Convert >>");
    	convertToRomanBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				romanTxt.setText("");
				messageLbl.setText("");
				String arabicText = arabicTxt.getText();
				
				try {
	                String conversion = numeralConverter.convertToRomanNumerals(Integer.valueOf(arabicText));
	                romanTxt.setText(conversion);
                } catch (NumberFormatException e1) {
	                e1.printStackTrace();
                } catch (NumeralConverterException e1) {
	                messageLbl.setText(e1.getMessage());
                }
				
			}
		});
    	
    	panel.add(arabicLbl);
    	panel.add(arabicTxt);
    	panel.add(convertToRomanBtn);
    	
    	return panel;
    }
    
    private JPanel buildRomanPanel() {
    	JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
    	panel.setOpaque(false);
    	JLabel romanLbl = new JLabel("Roman Numerals");
    	romanTxt = new JTextField();
    	convertToArabicBtn = new JButton("<< Convert");
    	convertToArabicBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				arabicTxt.setText("");
				messageLbl.setText("");
				String romanText = romanTxt.getText();
				
				try {
	                int conversion = numeralConverter.convertToArabicNumerals(romanText);
	                arabicTxt.setText(String.valueOf(conversion));
                } catch (NumeralConverterException e1) {
	                messageLbl.setText(e1.getMessage());
                }
			}
		});
    	
    	panel.add(romanLbl);
    	panel.add(romanTxt);
    	panel.add(convertToArabicBtn);
    	return panel;
    }
    
    private JPanel buildMessagePanel() {
    	JPanel panel = new JPanel(new BorderLayout());
    	panel.setOpaque(false);
    	panel.setBorder(BorderFactory.createCompoundBorder(
    			BorderFactory.createEmptyBorder(15, 15, 15, 15), 
    			BorderFactory.createTitledBorder("Convert Messages")));
    	
    	messageLbl = new JLabel("Enter Arabic numerals or Roman numerals and press button to convert.");
    	messageLbl.setHorizontalAlignment(SwingConstants.CENTER);
    	panel.add(messageLbl, BorderLayout.CENTER);
    	
    	return panel;
    }
    
    private class IntFilterDocument extends PlainDocument {

        @Override
        public void insertString( int offs, String str, AttributeSet a )
                throws BadLocationException {

            if ( str == null ) {
                return;
            }

            char[] chars = str.toCharArray();
            boolean ok = true;

            for ( int i = 0; i < chars.length; i++ ) {
                try {
                    Integer.parseInt( String.valueOf( chars[i] ) );
                } catch ( NumberFormatException exc ) {
                    ok = false;
                    break;
                }
            }
            
            if (ok && chars.length + offs < 10) {
            	super.insertString( offs, new String( chars ), a );
            }
        }
    }

}
