package com.mam.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mam.controller.MAM;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private JPanel contentPane;
	private JTextField browsedPathField;

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setTitle("Music Archive Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setMinimumSize(new Dimension(640, 480));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 5));
		
		JLabel applicationTitle = new JLabel("Music Archive Manager");
		applicationTitle.setHorizontalAlignment(SwingConstants.CENTER);
		applicationTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		topPanel.add(applicationTitle, BorderLayout.NORTH);
		
		JPanel browsePanel = new JPanel();
		topPanel.add(browsePanel, BorderLayout.SOUTH);
		GridBagLayout gbl_browsePanel = new GridBagLayout();
		gbl_browsePanel.columnWidths = new int[]{0, 0};
		gbl_browsePanel.rowHeights = new int[]{0, 0};
		gbl_browsePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_browsePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		browsePanel.setLayout(gbl_browsePanel);
		
		browsedPathField = new JTextField();
		browsedPathField.setToolTipText("Path of your music archive folder");
		GridBagConstraints gbc_browsedPathField = new GridBagConstraints();
		gbc_browsedPathField.insets = new Insets(0, 0, 0, 5);
		gbc_browsedPathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_browsedPathField.gridx = 0;
		browsePanel.add(browsedPathField, gbc_browsedPathField);
		browsedPathField.setColumns(10);
		
		final JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Choose your music archive folder");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
			    int returnVal = chooser.showOpenDialog(browseButton);
			    if(returnVal == JFileChooser.APPROVE_OPTION)
			    {
			       browsedPathField.setText(chooser.getSelectedFile().getAbsolutePath());
			    }
			}
		});
		browseButton.setToolTipText("Browse for your music archive folder");
		GridBagConstraints gbc_browseButton = new GridBagConstraints();
		gbc_browseButton.gridx = 1;
		browsePanel.add(browseButton, gbc_browseButton);
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 5));
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centerPanel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel autoTagPanel = new JPanel();
		tabbedPane.addTab("Auto Tag", null, autoTagPanel, null);
		autoTagPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel infoAutoTag = new JLabel("<html>\r\n<p>Auto Tag lets you update ID3 tags of your music files based on where they are located and their names.</p>\r\n<br>\r\n<p>Auto Tag will assume that:\r\n<ul>\r\n<li>Folders inside the selected music archive are named as the <strong>Artist/Band Name</strong>.</li>\r\n<li>Folders inside each artist folder are named as the <strong>Album Name</strong>. If there is no folder inside the artist folder, album name will be considered as <strong>Unknown</strong>.</li>\r\n<li>Files with <strong>.mp3</strong> extension have names with <strong>&lt;Artist Name&gt; - &lt;Title&gt;.mp3</strong> format.</li>\r\n<ul>\r\n</html>");
		infoAutoTag.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane autoTagScrollPane = new JScrollPane(infoAutoTag);
		autoTagPanel.add(autoTagScrollPane, BorderLayout.CENTER);
		
		JPanel autoNamePanel = new JPanel();
		tabbedPane.addTab("Auto Name", null, autoNamePanel, null);
		autoNamePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel infoAutoName = new JLabel("<html>\r\n<p>Auto Name lets you rename your music files based on their ID3 tags and where they are located.</p>\r\n<br>\r\n<p>Auto Name will assume that:\r\n<ul>\r\n<li>Folders inside the selected music archive are named as the <strong>Artist/Band Name</strong>.</li>\r\n<li>Folders inside each artist folder are named as the <strong>Album Name</strong>. If there is no folder inside the artist folder, album name will be considered as <strong>Unknown</strong>.</li>\r\n<li>ID3 tags have priority over the derived information from the file location that is, if the file has ID3 tags, those will be used to rename the file first.</li>\r\n<ul>\r\n</html>");
		infoAutoName.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane autoNameScrollPane = new JScrollPane(infoAutoName);
		autoNamePanel.add(autoNameScrollPane, BorderLayout.CENTER);
		
		JPanel autoFilePanel = new JPanel();
		tabbedPane.addTab("Auto File", null, autoFilePanel, null);
		autoFilePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel infoAutoFile = new JLabel("<html>\r\n<p>Auto File lets you relocate your music files into organized folders based on their ID3 tags and their file names.</p>\r\n<br>\r\n<p>Auto File will assume that:\r\n<ul>\r\n<li>ID3 tags have priority over the derived information from the file name that is, if the file has ID3 tags, those will be used to locate the file first.</li>\r\n</ul><p>\r\n<br>\r\n<p>The new organization of the folders will be as following:\r\n<ul>\r\n<li>Folders, named as artist name information from ID3 tags (or derived from file name if there is no ID3 tag), inside the selected music archive</li>\r\n<li>Folders, named as album name information from ID3 tags (or considered as unknown) inside each artist folder</li>\r\n<li>Music files inside each album folder</li>\r\n<ul>\r\n</html>");
		infoAutoFile.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane autoFileScrollPane = new JScrollPane(infoAutoFile);
		autoFilePanel.add(autoFileScrollPane, BorderLayout.CENTER);
		
		final JTextArea outputLog = new JTextArea();
		outputLog.setRows(10);
		outputLog.setEditable(false);
		
		JScrollPane logScrollPane = new JScrollPane(outputLog);
		centerPanel.add(logScrollPane, BorderLayout.SOUTH);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 5));
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		bottomPanel.add(progressBar, BorderLayout.SOUTH);
		
		JButton runButton = new JButton("RUN");
		runButton.setToolTipText("Run the current process");
		runButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(!browsedPathField.getText().equals(""))
				{
					MAM mam = new MAM(progressBar, outputLog);
					
					progressBar.setValue(0);
					
					switch(tabbedPane.getSelectedIndex())
					{
						case 0:
							mam.autoTag(browsedPathField.getText());
							break;
							
						case 1:
							mam.autoName(browsedPathField.getText());
							break;
							
						case 2:
							mam.autoFile(browsedPathField.getText());
							break;
					}
				}
				else
				{
					outputLog.append("Please select your music archive first.\n");
					System.out.println("Please select your music archive first.");
				}
			}
		});
		bottomPanel.add(runButton, BorderLayout.NORTH);
	}
}