package com.mam.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AboutFrame extends JFrame
{
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AboutFrame()
	{
		setTitle("About Music Archive Manager");
		setBounds(100, 100, 400, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel info = new JLabel("<html><p>This application is developed by Ezgi Hacýhalil and Mehmet Akif Tütüncü as the term project for CENG 316 Software Engineering course in Ýzmir Institute of Technology.</p><br><p>It is an open source software written in Java.</p><br><p>For more information: <a href=\"https://code.google.com/p/music-archive-manager/\">https://code.google.com/p/music-archive-manager/</a></p></html>");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(info, BorderLayout.CENTER);
	}
}