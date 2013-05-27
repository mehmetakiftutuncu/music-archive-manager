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
import javax.swing.JCheckBox;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private JPanel contentPane;
	
	private JProgressBar progressBar;
	private JTextArea outputLog;
	private JButton runButton;
	
	private JTextField browsedPathField;
	
	private JCheckBox autoTagOptionUppercase;
	private JCheckBox autoTagOptionTryFileName;
	private JTextField autoTagOptionCustomArtist;
	private JTextField autoTagOptionCustomAlbum;
	private JTextField autoTagOptionCustomTitle;
	
	private JCheckBox autoNameOptionUppercase;
	private JCheckBox autoNameOptionIncludeAlbum;
	private JTextField autoNameOptionCustomArtist;
	private JTextField autoNameOptionCustomAlbum;
	private JTextField autoNameOptionCustomTitle;
	
	private JCheckBox autoFileOptionUppercase;
	private JCheckBox autoFileOptionGenerateAlbum;
	private JTextField autoFileOptionCustomArtist;
	private JTextField autoFileOptionCustomAlbum;

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setTitle("Music Archive Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
		
		JPanel autoTagOptionsPanel = new JPanel();
		
		JSplitPane autoTagSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, autoTagScrollPane, autoTagOptionsPanel);
		autoTagSplitPane.setResizeWeight(1.0);
		GridBagLayout gbl_autoTagOptionsPanel = new GridBagLayout();
		gbl_autoTagOptionsPanel.columnWidths = new int[]{0, 0};
		gbl_autoTagOptionsPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_autoTagOptionsPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_autoTagOptionsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		autoTagOptionsPanel.setLayout(gbl_autoTagOptionsPanel);
		
		autoTagOptionUppercase = new JCheckBox("Convert each word to upper-case");
		autoTagOptionUppercase.setToolTipText("If this is checked, the first letter of each word will be converted to upper-case while tagging music files.");
		autoTagOptionUppercase.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_autoTagOptionUppercase = new GridBagConstraints();
		gbc_autoTagOptionUppercase.insets = new Insets(0, 0, 5, 0);
		gbc_autoTagOptionUppercase.gridx = 0;
		gbc_autoTagOptionUppercase.gridy = 0;
		autoTagOptionsPanel.add(autoTagOptionUppercase, gbc_autoTagOptionUppercase);
		
		autoTagOptionTryFileName = new JCheckBox("Try file name first");
		autoTagOptionTryFileName.setToolTipText("If this is checked, information derived from the file name will be checked first instead of location of the file to generate tags.");
		autoTagOptionTryFileName.setSelected(true);
		GridBagConstraints gbc_autoTagOptionTryFileName = new GridBagConstraints();
		gbc_autoTagOptionTryFileName.gridx = 0;
		gbc_autoTagOptionTryFileName.gridy = 1;
		autoTagSplitPane.setDividerLocation(0.5f);
		autoTagOptionsPanel.add(autoTagOptionTryFileName, gbc_autoTagOptionTryFileName);
		
		JPanel autoTagOptionsCustomTexts = new JPanel();
		autoTagOptionsCustomTexts.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Custom Texts For Unknown Tags", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_autoTagOptionsCustomTexts = new GridBagConstraints();
		gbc_autoTagOptionsCustomTexts.insets = new Insets(0, 0, 5, 0);
		gbc_autoTagOptionsCustomTexts.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoTagOptionsCustomTexts.gridx = 0;
		gbc_autoTagOptionsCustomTexts.gridy = 2;
		autoTagOptionsPanel.add(autoTagOptionsCustomTexts, gbc_autoTagOptionsCustomTexts);
		GridBagLayout gbl_autoTagOptionsCustomTexts = new GridBagLayout();
		gbl_autoTagOptionsCustomTexts.columnWidths = new int[]{0, 0, 0};
		gbl_autoTagOptionsCustomTexts.rowHeights = new int[]{0, 0, 0};
		gbl_autoTagOptionsCustomTexts.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_autoTagOptionsCustomTexts.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		autoTagOptionsCustomTexts.setLayout(gbl_autoTagOptionsCustomTexts);
		
		JLabel lblAutoTagOptionsCustomArtist = new JLabel("Artist");
		GridBagConstraints gbc_lblAutoTagOptionsCustomArtist = new GridBagConstraints();
		gbc_lblAutoTagOptionsCustomArtist.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutoTagOptionsCustomArtist.anchor = GridBagConstraints.EAST;
		gbc_lblAutoTagOptionsCustomArtist.gridx = 0;
		gbc_lblAutoTagOptionsCustomArtist.gridy = 0;
		autoTagOptionsCustomTexts.add(lblAutoTagOptionsCustomArtist, gbc_lblAutoTagOptionsCustomArtist);
		
		autoTagOptionCustomArtist = new JTextField();
		autoTagOptionCustomArtist.setText("Unknown Artist");
		GridBagConstraints gbc_autoTagOptionCustomArtist = new GridBagConstraints();
		gbc_autoTagOptionCustomArtist.insets = new Insets(0, 0, 5, 0);
		gbc_autoTagOptionCustomArtist.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoTagOptionCustomArtist.gridx = 1;
		gbc_autoTagOptionCustomArtist.gridy = 0;
		autoTagOptionsCustomTexts.add(autoTagOptionCustomArtist, gbc_autoTagOptionCustomArtist);
		autoTagOptionCustomArtist.setColumns(10);
		
		JLabel lblAutoTagOptionsCustomAlbum = new JLabel("Album");
		GridBagConstraints gbc_lblAutoTagOptionsCustomAlbum = new GridBagConstraints();
		gbc_lblAutoTagOptionsCustomAlbum.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutoTagOptionsCustomAlbum.anchor = GridBagConstraints.EAST;
		gbc_lblAutoTagOptionsCustomAlbum.gridx = 0;
		gbc_lblAutoTagOptionsCustomAlbum.gridy = 1;
		autoTagOptionsCustomTexts.add(lblAutoTagOptionsCustomAlbum, gbc_lblAutoTagOptionsCustomAlbum);
		
		autoTagOptionCustomAlbum = new JTextField();
		autoTagOptionCustomAlbum.setText("Unknown Album");
		GridBagConstraints gbc_autoTagOptionCustomAlbum = new GridBagConstraints();
		gbc_autoTagOptionCustomAlbum.insets = new Insets(0, 0, 5, 0);
		gbc_autoTagOptionCustomAlbum.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoTagOptionCustomAlbum.gridx = 1;
		gbc_autoTagOptionCustomAlbum.gridy = 1;
		autoTagOptionsCustomTexts.add(autoTagOptionCustomAlbum, gbc_autoTagOptionCustomAlbum);
		autoTagOptionCustomAlbum.setColumns(10);
		
		JLabel lblAutoTagOptionsCustomTitle = new JLabel("Title");
		GridBagConstraints gbc_lblAutoTagOptionsCustomTitle = new GridBagConstraints();
		gbc_lblAutoTagOptionsCustomTitle.anchor = GridBagConstraints.EAST;
		gbc_lblAutoTagOptionsCustomTitle.insets = new Insets(0, 0, 0, 5);
		gbc_lblAutoTagOptionsCustomTitle.gridx = 0;
		gbc_lblAutoTagOptionsCustomTitle.gridy = 2;
		autoTagOptionsCustomTexts.add(lblAutoTagOptionsCustomTitle, gbc_lblAutoTagOptionsCustomTitle);

		autoTagOptionCustomTitle = new JTextField();
		autoTagOptionCustomTitle.setText("Unknown Title");
		GridBagConstraints gbc_autoTagOptionCustomTitle = new GridBagConstraints();
		gbc_autoTagOptionCustomTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoTagOptionCustomTitle.gridx = 1;
		gbc_autoTagOptionCustomTitle.gridy = 2;
		autoTagOptionsCustomTexts.add(autoTagOptionCustomTitle, gbc_autoTagOptionCustomTitle);
		autoTagOptionCustomTitle.setColumns(10);
		
		autoTagSplitPane.setOneTouchExpandable(true);
		autoTagPanel.add(autoTagSplitPane, BorderLayout.CENTER);
		
		JPanel autoNamePanel = new JPanel();
		tabbedPane.addTab("Auto Name", null, autoNamePanel, null);
		autoNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel infoAutoName = new JLabel("<html>\r\n<p>Auto Name lets you rename your music files based on their ID3 tags and where they are located.</p>\r\n<br>\r\n<p>Auto Name will assume that:\r\n<ul>\r\n<li>Folders inside the selected music archive are named as the <strong>Artist/Band Name</strong>.</li>\r\n<li>Folders inside each artist folder are named as the <strong>Album Name</strong>. If there is no folder inside the artist folder, album name will be considered as <strong>Unknown</strong>.</li>\r\n<li>ID3 tags have priority over the derived information from the file location that is, if the file has ID3 tags, those will be used to rename the file first.</li>\r\n<ul>\r\n</html>");
		infoAutoName.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane autoNameScrollPane = new JScrollPane(infoAutoName);

		JPanel autoNameOptionsPanel = new JPanel();

		JSplitPane autoNameSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, autoNameScrollPane, autoNameOptionsPanel);
		autoNameSplitPane.setResizeWeight(1.0);
		GridBagLayout gbl_autoNameOptionsPanel = new GridBagLayout();
		gbl_autoNameOptionsPanel.columnWidths = new int[]{0, 0};
		gbl_autoNameOptionsPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_autoNameOptionsPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_autoNameOptionsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		autoNameOptionsPanel.setLayout(gbl_autoNameOptionsPanel);

		autoNameOptionUppercase = new JCheckBox("Convert each word to upper-case");
		autoNameOptionUppercase.setToolTipText("If this is checked, the first letter of each word will be converted to upper-case while renaming music files.");
		autoNameOptionUppercase.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_autoNameOptionUppercase = new GridBagConstraints();
		gbc_autoNameOptionUppercase.insets = new Insets(0, 0, 5, 0);
		gbc_autoNameOptionUppercase.gridx = 0;
		gbc_autoNameOptionUppercase.gridy = 0;
		autoNameOptionsPanel.add(autoNameOptionUppercase, gbc_autoNameOptionUppercase);

		autoNameOptionIncludeAlbum = new JCheckBox("Include album name");
		autoNameOptionIncludeAlbum.setToolTipText("If this is checked, album name will be added as well while renaming music files.");
		autoNameOptionIncludeAlbum.setSelected(true);
		GridBagConstraints gbc_autoNameOptionIncludeAlbum = new GridBagConstraints();
		gbc_autoNameOptionIncludeAlbum.gridx = 0;
		gbc_autoNameOptionIncludeAlbum.gridy = 1;
		autoNameSplitPane.setDividerLocation(0.5f);
		autoNameOptionsPanel.add(autoNameOptionIncludeAlbum, gbc_autoNameOptionIncludeAlbum);
		
		JPanel autoNameOptionsCustomTexts = new JPanel();
		autoNameOptionsCustomTexts.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Custom Texts For Unknown Tags", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_autoNameOptionsCustomTexts = new GridBagConstraints();
		gbc_autoNameOptionsCustomTexts.insets = new Insets(0, 0, 5, 0);
		gbc_autoNameOptionsCustomTexts.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoNameOptionsCustomTexts.gridx = 0;
		gbc_autoNameOptionsCustomTexts.gridy = 2;
		autoNameOptionsPanel.add(autoNameOptionsCustomTexts, gbc_autoNameOptionsCustomTexts);
		GridBagLayout gbl_autoNameOptionsCustomTexts = new GridBagLayout();
		gbl_autoNameOptionsCustomTexts.columnWidths = new int[]{0, 0, 0};
		gbl_autoNameOptionsCustomTexts.rowHeights = new int[]{0, 0, 0};
		gbl_autoNameOptionsCustomTexts.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_autoNameOptionsCustomTexts.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		autoNameOptionsCustomTexts.setLayout(gbl_autoNameOptionsCustomTexts);

		JLabel lblAutoNameOptionsCustomArtist = new JLabel("Artist");
		GridBagConstraints gbc_lblAutoNameOptionsCustomArtist = new GridBagConstraints();
		gbc_lblAutoNameOptionsCustomArtist.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutoNameOptionsCustomArtist.anchor = GridBagConstraints.EAST;
		gbc_lblAutoNameOptionsCustomArtist.gridx = 0;
		gbc_lblAutoNameOptionsCustomArtist.gridy = 0;
		autoNameOptionsCustomTexts.add(lblAutoNameOptionsCustomArtist, gbc_lblAutoNameOptionsCustomArtist);

		autoNameOptionCustomArtist = new JTextField();
		autoNameOptionCustomArtist.setText("Unknown Artist");
		GridBagConstraints gbc_autoNameOptionCustomArtist = new GridBagConstraints();
		gbc_autoNameOptionCustomArtist.insets = new Insets(0, 0, 5, 0);
		gbc_autoNameOptionCustomArtist.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoNameOptionCustomArtist.gridx = 1;
		gbc_autoNameOptionCustomArtist.gridy = 0;
		autoNameOptionsCustomTexts.add(autoNameOptionCustomArtist, gbc_autoNameOptionCustomArtist);
		autoNameOptionCustomArtist.setColumns(10);

		JLabel lblAutoNameOptionsCustomAlbum = new JLabel("Album");
		GridBagConstraints gbc_lblAutoNameOptionsCustomAlbum = new GridBagConstraints();
		gbc_lblAutoNameOptionsCustomAlbum.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutoNameOptionsCustomAlbum.anchor = GridBagConstraints.EAST;
		gbc_lblAutoNameOptionsCustomAlbum.gridx = 0;
		gbc_lblAutoNameOptionsCustomAlbum.gridy = 1;
		autoNameOptionsCustomTexts.add(lblAutoNameOptionsCustomAlbum, gbc_lblAutoNameOptionsCustomAlbum);

		autoNameOptionCustomAlbum = new JTextField();
		autoNameOptionCustomAlbum.setText("Unknown Album");
		GridBagConstraints gbc_autoNameOptionCustomAlbum = new GridBagConstraints();
		gbc_autoNameOptionCustomAlbum.insets = new Insets(0, 0, 5, 0);
		gbc_autoNameOptionCustomAlbum.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoNameOptionCustomAlbum.gridx = 1;
		gbc_autoNameOptionCustomAlbum.gridy = 1;
		autoNameOptionsCustomTexts.add(autoNameOptionCustomAlbum, gbc_autoNameOptionCustomAlbum);
		autoNameOptionCustomAlbum.setColumns(10);

		JLabel lblAutoNameOptionsCustomTitle = new JLabel("Title");
		GridBagConstraints gbc_lblAutoNameOptionsCustomTitle = new GridBagConstraints();
		gbc_lblAutoNameOptionsCustomTitle.insets = new Insets(0, 0, 0, 5);
		gbc_lblAutoNameOptionsCustomTitle.anchor = GridBagConstraints.EAST;
		gbc_lblAutoNameOptionsCustomTitle.gridx = 0;
		gbc_lblAutoNameOptionsCustomTitle.gridy = 2;
		autoNameOptionsCustomTexts.add(lblAutoNameOptionsCustomTitle, gbc_lblAutoNameOptionsCustomTitle);

		autoNameOptionCustomTitle = new JTextField();
		autoNameOptionCustomTitle.setText("Unknown Title");
		GridBagConstraints gbc_autoNameOptionCustomTitle = new GridBagConstraints();
		gbc_autoNameOptionCustomTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoNameOptionCustomTitle.gridx = 1;
		gbc_autoNameOptionCustomTitle.gridy = 2;
		autoNameOptionsCustomTexts.add(autoNameOptionCustomTitle, gbc_autoNameOptionCustomTitle);
		autoNameOptionCustomTitle.setColumns(10);

		autoNameSplitPane.setOneTouchExpandable(true);
		autoNamePanel.add(autoNameSplitPane, BorderLayout.CENTER);
		
		JPanel autoFilePanel = new JPanel();
		tabbedPane.addTab("Auto File", null, autoFilePanel, null);
		autoFilePanel.setLayout(new BorderLayout(0, 0));

		JLabel infoAutoFile = new JLabel("<html>\r\n<p>Auto File lets you relocate your music files into organized folders based on their ID3 tags and their file names.</p>\r\n<br>\r\n<p>Auto File will assume that:\r\n<ul>\r\n<li>ID3 tags have priority over the derived information from the file name that is, if the file has ID3 tags, those will be used to locate the file first.</li>\r\n</ul><p>\r\n<br>\r\n<p>The new organization of the folders will be as following:\r\n<ul>\r\n<li>Folders, named as artist name information from ID3 tags (or derived from file name if there is no ID3 tag), inside the selected music archive</li>\r\n<li>Folders, named as album name information from ID3 tags (or considered as unknown) inside each artist folder</li>\r\n<li>Music files inside each album folder</li>\r\n<ul>\r\n</html>");
		infoAutoFile.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane autoFileScrollPane = new JScrollPane(infoAutoFile);

		JPanel autoFileOptionsPanel = new JPanel();

		JSplitPane autoFileSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, autoFileScrollPane, autoFileOptionsPanel);
		autoFileSplitPane.setResizeWeight(1.0);
		GridBagLayout gbl_autoFileOptionsPanel = new GridBagLayout();
		gbl_autoFileOptionsPanel.columnWidths = new int[]{0, 0};
		gbl_autoFileOptionsPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_autoFileOptionsPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_autoFileOptionsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		autoFileOptionsPanel.setLayout(gbl_autoFileOptionsPanel);

		autoFileOptionUppercase = new JCheckBox("Convert each word to upper-case");
		autoFileOptionUppercase.setToolTipText("If this is checked, the first letter of each word for the folder names will be converted to upper-case while relocating music files.");
		autoFileOptionUppercase.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_autoFileOptionUppercase = new GridBagConstraints();
		gbc_autoFileOptionUppercase.insets = new Insets(0, 0, 5, 0);
		gbc_autoFileOptionUppercase.gridx = 0;
		gbc_autoFileOptionUppercase.gridy = 0;
		autoFileOptionsPanel.add(autoFileOptionUppercase, gbc_autoFileOptionUppercase);

		autoFileOptionGenerateAlbum = new JCheckBox("Generate album folder");
		autoFileOptionGenerateAlbum.setToolTipText("If this is checked, folders for each album is going to be generated inside each artist. If not, all songs for an artist will be in the same directory.");
		autoFileOptionGenerateAlbum.setSelected(true);
		GridBagConstraints gbc_autoFileOptionGenerateAlbum = new GridBagConstraints();
		gbc_autoFileOptionGenerateAlbum.gridx = 0;
		gbc_autoFileOptionGenerateAlbum.gridy = 1;
		autoFileSplitPane.setDividerLocation(0.5f);
		autoFileOptionsPanel.add(autoFileOptionGenerateAlbum, gbc_autoFileOptionGenerateAlbum);
		
		JPanel autoFileOptionsCustomTexts = new JPanel();
		autoFileOptionsCustomTexts.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Custom Texts For Unknown Tags", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_autoFileOptionsCustomTexts = new GridBagConstraints();
		gbc_autoFileOptionsCustomTexts.insets = new Insets(0, 0, 5, 0);
		gbc_autoFileOptionsCustomTexts.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoFileOptionsCustomTexts.gridx = 0;
		gbc_autoFileOptionsCustomTexts.gridy = 2;
		autoFileOptionsPanel.add(autoFileOptionsCustomTexts, gbc_autoFileOptionsCustomTexts);
		GridBagLayout gbl_autoFileOptionsCustomTexts = new GridBagLayout();
		gbl_autoFileOptionsCustomTexts.columnWidths = new int[]{0, 0, 0};
		gbl_autoFileOptionsCustomTexts.rowHeights = new int[]{0, 0, 0};
		gbl_autoFileOptionsCustomTexts.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_autoFileOptionsCustomTexts.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		autoFileOptionsCustomTexts.setLayout(gbl_autoFileOptionsCustomTexts);

		JLabel lblAutoFileOptionsCustomArtist = new JLabel("Artist");
		GridBagConstraints gbc_lblAutoFileOptionsCustomArtist = new GridBagConstraints();
		gbc_lblAutoFileOptionsCustomArtist.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutoFileOptionsCustomArtist.anchor = GridBagConstraints.EAST;
		gbc_lblAutoFileOptionsCustomArtist.gridx = 0;
		gbc_lblAutoFileOptionsCustomArtist.gridy = 0;
		autoFileOptionsCustomTexts.add(lblAutoFileOptionsCustomArtist, gbc_lblAutoFileOptionsCustomArtist);

		autoFileOptionCustomArtist = new JTextField();
		autoFileOptionCustomArtist.setText("Unknown Artist");
		GridBagConstraints gbc_autoFileOptionCustomArtist = new GridBagConstraints();
		gbc_autoFileOptionCustomArtist.insets = new Insets(0, 0, 5, 0);
		gbc_autoFileOptionCustomArtist.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoFileOptionCustomArtist.gridx = 1;
		gbc_autoFileOptionCustomArtist.gridy = 0;
		autoFileOptionsCustomTexts.add(autoFileOptionCustomArtist, gbc_autoFileOptionCustomArtist);
		autoFileOptionCustomArtist.setColumns(10);

		JLabel lblAutoFileOptionsCustomAlbum = new JLabel("Album");
		GridBagConstraints gbc_lblAutoFileOptionsCustomAlbum = new GridBagConstraints();
		gbc_lblAutoFileOptionsCustomAlbum.insets = new Insets(0, 0, 0, 5);
		gbc_lblAutoFileOptionsCustomAlbum.anchor = GridBagConstraints.EAST;
		gbc_lblAutoFileOptionsCustomAlbum.gridx = 0;
		gbc_lblAutoFileOptionsCustomAlbum.gridy = 1;
		autoFileOptionsCustomTexts.add(lblAutoFileOptionsCustomAlbum, gbc_lblAutoFileOptionsCustomAlbum);

		autoFileOptionCustomAlbum = new JTextField();
		autoFileOptionCustomAlbum.setText("Unknown Album");
		GridBagConstraints gbc_autoFileOptionCustomAlbum = new GridBagConstraints();
		gbc_autoFileOptionCustomAlbum.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoFileOptionCustomAlbum.gridx = 1;
		gbc_autoFileOptionCustomAlbum.gridy = 1;
		autoFileOptionsCustomTexts.add(autoFileOptionCustomAlbum, gbc_autoFileOptionCustomAlbum);
		autoFileOptionCustomAlbum.setColumns(10);

		autoFileSplitPane.setOneTouchExpandable(true);
		autoFilePanel.add(autoFileSplitPane, BorderLayout.CENTER);
		
		JPanel clearTagsPanel = new JPanel();
		tabbedPane.addTab("Clear Tags", null, clearTagsPanel, null);
		clearTagsPanel.setLayout(new BorderLayout(0, 0));

		JLabel infoClearTags = new JLabel("<html>\r\n<p>Auto File lets you relocate your music files into organized folders based on their ID3 tags and their file names.</p>\r\n<br>\r\n<p>Auto File will assume that:\r\n<ul>\r\n<li>ID3 tags have priority over the derived information from the file name that is, if the file has ID3 tags, those will be used to locate the file first.</li>\r\n</ul><p>\r\n<br>\r\n<p>The new organization of the folders will be as following:\r\n<ul>\r\n<li>Folders, named as artist name information from ID3 tags (or derived from file name if there is no ID3 tag), inside the selected music archive</li>\r\n<li>Folders, named as album name information from ID3 tags (or considered as unknown) inside each artist folder</li>\r\n<li>Music files inside each album folder</li>\r\n<ul>\r\n</html>");
		infoClearTags.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane clearTagsScrollPane = new JScrollPane(infoClearTags);

		clearTagsPanel.add(clearTagsScrollPane, BorderLayout.CENTER);
		
		outputLog = new JTextArea();
		outputLog.setRows(10);
		outputLog.setEditable(false);
		
		JScrollPane logScrollPane = new JScrollPane(outputLog);
		centerPanel.add(logScrollPane, BorderLayout.SOUTH);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 5));
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		bottomPanel.add(progressBar, BorderLayout.SOUTH);
		
		runButton = new JButton("RUN");
		runButton.setToolTipText("Run the current process");
		runButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(!browsedPathField.getText().equals(""))
				{
					MAM mam = new MAM(browsedPathField.getText(), progressBar, outputLog, runButton);
					
					switch(tabbedPane.getSelectedIndex())
					{
						case 0:
							mam.autoTag(autoTagOptionUppercase.isSelected(),
										autoTagOptionTryFileName.isSelected(),
										autoTagOptionCustomArtist.getText(),
										autoTagOptionCustomAlbum.getText(),
										autoTagOptionCustomTitle.getText());
							break;
							
						case 1:
							mam.autoName(	autoNameOptionUppercase.isSelected(),
											autoNameOptionIncludeAlbum.isSelected(),
											autoNameOptionCustomArtist.getText(),
											autoNameOptionCustomAlbum.getText(),
											autoNameOptionCustomTitle.getText());
							break;
							
						case 2:
							mam.autoFile(	autoFileOptionUppercase.isSelected(),
											autoFileOptionGenerateAlbum.isSelected(),
											autoFileOptionCustomArtist.getText(),
											autoFileOptionCustomAlbum.getText());
							break;
						
						case 3:
							mam.clearTags();
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