package View;

/*
 * TCSS 360 � Winter 2019
 * 
 * 
 * WANT TO ADD A "ARE YOU SURE" WHEN YOU CLICK CANCEL FOR THE CREATING NEW PROJECT PAGE
 * 
 * WANT TO ADD A CHECK FOR WHEN A USER INPUTS NOTHING FOR A PROJECT NAME
 * 
 * 1. Make calculate panel
 * 2. Calculate the cost yourself when calculate button is hit
 * 3. Cancel button goes back to home (should go back to previous new project though)
 * 
 * 
 * ** HAVE ALL USER INPUTS BE ON ONE PAGE, NAME, LENGTH, WIDTH, AND THEN AS SOON AS THEY CALCULATE, CREATE THE OBJECT THEN
 * 
 * ** For SizeOfProject/Difficulty call update and then use getDisplay to return the value you want to display
 * 
=======
 * TCSS 360 – Winter 2019
 * Test
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

//*********************************************************	

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

//*********************************************************	

/**
 * This class creates the GUI for the program.
 * 
 * @author Jacob Dickson
 * @version Winter 2019
 */
public class MainGUI {
	private VersionInfo myVersion;
	private static final Dimension SIZE = new Dimension(460, 450);
	private JFrame myFrame;
	private JPanel myMainPanel;
	private JPanel myNewPPanel;
	private String myName;
	private String myType;

	// *********************************************************

	// Test for project object to store user project
	private Project myFirstProject;

	// JPanel for open project panel
	private JPanel myOpenProjectPanel;

	// JPaenl for user to input their data
	private JPanel myUserDataForNewProjectPanel;

	// JPanel to display calculated user data.
	private JPanel myCalculatedUserDataPanelNonEdit;

	// *********************************************************

	/**
	 * Is the main constructor for the GUI.
	 */
	public MainGUI() {
		myVersion = new VersionInfo();
		myFrame = new JFrame("JJAQ Program");
		myMainPanel = new JPanel(new GridLayout(0, 3));
		myNewPPanel = new JPanel(new GridLayout(0, 3));

		// *********************************************************

		// Initiating project object for testing purposes
		myFirstProject = new Project();

		// Initiating JPanel for open project panel
		myOpenProjectPanel = new JPanel(new GridLayout(0, 3));

		// Initiating JPanel for user inputs panel
		myUserDataForNewProjectPanel = new JPanel(new GridLayout(3, 3));

		myCalculatedUserDataPanelNonEdit = new JPanel(new GridLayout(3, 3));

		myName = "";
		myType = "";
		// *********************************************************

	}

	/**
	 * Starts the GUI.
	 */
	public void start() {

		// Make new project panel
		makeNewProjectPanel();

		// Make main page
		makePanel();

		// *********************************************************

		// Make open project page
		makeOpenProjectPanel();

		// Make user input page
		makePanelForUserInput(true);

		//

		// *********************************************************

		myFrame.setPreferredSize(SIZE);

		myFrame.setJMenuBar(menuBar());

		myFrame.pack();
		myFrame.setMinimumSize(myFrame.getSize());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}

	/**
	 * Makes the MAIN page user comes to.
	 */
	private void makePanel() {

		JPanel buttonPanel = new JPanel(new GridLayout(3, 0, 3, 10));
		JButton newP = new JButton("New Project");
		JButton oldP = new JButton("Open Project");
		JButton compareP = new JButton("Compare");
		newP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToNew();
				myFrame.revalidate();
				myFrame.repaint();

			}
		});

		// Adds listener for when button is clicked and will transition to open project
		// page
		oldP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File directory = new File(".");

				JFileChooser fileChooser = null;
				try {
					fileChooser = new JFileChooser(directory.getCanonicalPath());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				int result = fileChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					String file = null;
					try {
						file = fileChooser.getSelectedFile().getCanonicalPath();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					myFirstProject = new Project(file);
					makePanelForUserInput(false);

					goToNextPanelFromCurrentPanel(myMainPanel, myUserDataForNewProjectPanel);
				}

				System.out.print(myFirstProject.getProjectName());
				myFrame.revalidate();
				myFrame.repaint();

			}
		});

		buttonPanel.add(newP);
		buttonPanel.add(oldP);
		buttonPanel.add(compareP);
		myMainPanel.add(new JPanel());
		myMainPanel.add(new JPanel());
		myMainPanel.add(new JPanel());
		myMainPanel.add(new JPanel());

		myMainPanel.add(buttonPanel);
		myMainPanel.add(new JPanel());

		myMainPanel.add(new JPanel());

		myFrame.add(myMainPanel);
	}

	private JButton homeButton(JPanel myPanel) {
		JButton home = new JButton("Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToNextPanelFromCurrentPanel(myPanel, myMainPanel);
				myFrame.revalidate();
				myFrame.repaint();

			}
		});
		return home;
	}

	private void goToNew() {
		myFrame.remove(myMainPanel);
		myFrame.add(myNewPPanel);
		System.out.println("Test");
	}

	/*
	 * This makes the new project panel
	 */
	private void makeNewProjectPanel() {
		myNewPPanel = new JPanel(new GridLayout(3, 3));
		JPanel inputPanel = new JPanel();
		JPanel buttonPanel = new JPanel(new GridLayout(3, 0, 3, 10));
		JButton makeP = new JButton("Make Project");
		JButton cancelP = new JButton("Cancel");

		// Textfield for name of project
		JTextField newP = new JTextField(10);

		// Label used for text field
		inputPanel.add(new JLabel("New Project:"));
		inputPanel.add(newP);

		// ********************************************************************************************************
		// ********************************************************************************************************

		// Label used for user to put in project type
		inputPanel.add(new JLabel("Project Type:"));

		// Dropdown menu for user to choose project type
		String[] choicesForDropdown = { "Flooring", "No time", "Please give us mercy" };

		JComboBox<String> dropdownMenu = new JComboBox<String>(choicesForDropdown);

		inputPanel.add(dropdownMenu);

		// ********************************************************************************************************
		// ********************************************************************************************************

		buttonPanel.add(makeP);
		buttonPanel.add(cancelP);

		// Currently not being used
		JButton home = new JButton("Home");

		myNewPPanel.add(new JPanel());
		myNewPPanel.add(new JPanel());
		myNewPPanel.add(new JPanel().add(homeButton(myNewPPanel)));
		myNewPPanel.add(new JPanel());

		myNewPPanel.add(inputPanel);
		myNewPPanel.add(new JPanel());
		myNewPPanel.add(new JPanel());

		myNewPPanel.add(buttonPanel);

		// ********************************************************************************************************
		// ********************************************************************************************************

		// Adds listener for when cancel button is clicked to bring back to home page.
		cancelP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Function to bring back to home page
				goToNextPanelFromCurrentPanel(myNewPPanel, myMainPanel);
				myFrame.revalidate();
				myFrame.repaint();

			}
		});

		// Adds listener for when make project button is clicked, it will bring user to
		// input their data
		makeP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Captures what is inside the text field and stores it into the project object.
				myName = newP.getText();
				myType = (String) dropdownMenu.getSelectedItem();
				// System.out.println((String) dropdownMenu.getSelectedItem());

				// Captures what user chose for project type
				// Have to cast because getSelectedItem() returns an object
				// myFirstProject.setProjectType( (String) dropdownMenu.getSelectedItem());

				// Function to bring back to home page
				makePanelForUserInput(true);

				goToNextPanelFromCurrentPanel(myNewPPanel, myUserDataForNewProjectPanel);
				myFrame.revalidate();
				myFrame.repaint();

				// Check to see if name of the project gets stored
				System.out.println("Project name is: " + myFirstProject.getProjectName());
				// Check to see if type of the project gets stored
				System.out.println("Project type is: " + myFirstProject.getProjectType());

			}
		});

		// ********************************************************************************************************
		// ********************************************************************************************************

		// *********************************************************
		// *********************************************************

	}

	/**
	 * First argument is the current panel you're on, the second argument is going
	 * to be the panel you to want to transition to.
	 * 
	 * 
	 * @param theCurrentPanelDisplayed
	 * @param theNextPanelDisplayed
	 */
	private void goToNextPanelFromCurrentPanel(JPanel theCurrentPanelDisplayed, JPanel theNextPanelDisplayed) {
		myFrame.remove(theCurrentPanelDisplayed);
		myFrame.add(theNextPanelDisplayed);

	}

	// *********************************************************
	// *********************************************************

	/**
	 * 
	 * ** CURRENTLY UNUSED ** ** CURRENTLY UNUSED ** ** CURRENTLY UNUSED **
	 * 
	 * Creates the UI for the open project page
	 */
	private void makeOpenProjectPanel() {

		/*
		 * JButton button1 = new JButton("Button 1"); JButton button2 = new
		 * JButton("Button 2"); JButton button3 = new JButton("Button 3"); JButton
		 * button4 = new JButton("Button 4"); JButton button5 = new JButton("Button 5");
		 * JButton button6 = new JButton("Button 6"); JButton button7 = new
		 * JButton("Button 7"); JButton button8 = new JButton("Button 8"); JButton
		 * button9 = new JButton("Button 9");
		 * 
		 * myOpenProjectPanel.add(button1); myOpenProjectPanel.add(button2);
		 * myOpenProjectPanel.add(button3); myOpenProjectPanel.add(button4);
		 * myOpenProjectPanel.add(button5); myOpenProjectPanel.add(button6);
		 * myOpenProjectPanel.add(button7); myOpenProjectPanel.add(button8);
		 * myOpenProjectPanel.add(button9); //
		 */

	}

	// *********************************************************
	// *********************************************************

	/**
	 * Panel for user to input their numbers to calculate.
	 */
	private void makePanelForUserInput(boolean newP) {
		myUserDataForNewProjectPanel = new JPanel(new GridLayout(3, 3));
		// 5 Rows, 2 columns; Length, Width, Height, Thickness, Pre-existing material.
		// Last 3 rows are optional
		// 1st column for labels, 2nd column for text fields.
		// JPanel userDataPanel = new JPanel(new GridLayout(5, 2, 0, 0));

		// Default JPanel to hold text fields and labels
		JPanel userDataPanel = new JPanel();

		// Creates all labels needed to display the calculated numbers when the user
		// inputs
		// values and clicks the calculate button
		JPanel calculatedDataPanel = new JPanel(new GridLayout(0, 1));
		JLabel costLabel = new JLabel("Cost: ");
		JLabel costLabelCalculated = new JLabel();
		// Padding for formatting issues...
		costLabelCalculated.setBorder(new EmptyBorder(0, 0, 0, 40));
		JLabel timeLabel = new JLabel("Time: ");
		JLabel timeLabelCalculated = new JLabel();
		JLabel difficultyLabel = new JLabel("Difficulty: ");
		JLabel difficultyLabelCalculated = new JLabel();
		JLabel sizeOfProjectLabel = new JLabel("Size of project: ");
		JLabel sizeOfProjectLabelCalculated = new JLabel();

		// Panel to hold cancel and calculate buttons
		JPanel buttonPanel = new JPanel(new GridLayout(3, 0, 3, 10));

		// Instruction label
		JLabel instructionLabel = new JLabel("Input values in FEET");

		// Length label
		JLabel lengthLabel = new JLabel("Length (Feet):");

		// Length text field for user to type in
		JTextField lengthField = new JTextField(10);

		// Width label
		JLabel widthLabel = new JLabel("Width (Feet):");

		// Width text field for user to type in
		JTextField widthField = new JTextField(10);

		if (!newP) {
			System.out.println("Diff: " + myFirstProject.getDifficulty());
			lengthField.setText(Double.toString(myFirstProject.getLength()));
			widthField.setText(Double.toString(myFirstProject.getWidth()));
			// Set text for labels with the respective getters, and some need a string to
			// double conversion.
			costLabelCalculated.setText(Double.toString(myFirstProject.getCost()));
			timeLabelCalculated.setText(Double.toString(myFirstProject.getTime()));
			difficultyLabelCalculated.setText(myFirstProject.getDifficulty());
			sizeOfProjectLabelCalculated.setText(myFirstProject.getSizeOfProject());
		}

		// Adds all components into the JPanel designated for all labels and fields.
		userDataPanel.add(instructionLabel);
		userDataPanel.add(lengthLabel);
		userDataPanel.add(lengthField);
		userDataPanel.add(widthLabel);
		userDataPanel.add(widthField);
		// Adds all labels to the panel created above specifically to hold all the
		// calculated labels
		calculatedDataPanel.add(costLabel);
		calculatedDataPanel.add(costLabelCalculated);
		calculatedDataPanel.add(timeLabel);
		calculatedDataPanel.add(timeLabelCalculated);
		calculatedDataPanel.add(difficultyLabel);
		calculatedDataPanel.add(difficultyLabelCalculated);
		calculatedDataPanel.add(sizeOfProjectLabel);
		calculatedDataPanel.add(sizeOfProjectLabelCalculated);
		// Creates calculate and cancel button
		JButton calculateButton = new JButton("Calculate");
		JButton cancelButton = new JButton("Cancel");

		// Adds buttons to the panel made just to hold the buttons
		buttonPanel.add(calculateButton);
		buttonPanel.add(cancelButton);

		// Adds listener for when cancel button is clicked, brings you to home.
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myFirstProject = new Project(myName, myType, Double.parseDouble(lengthField.getText()),
						Double.parseDouble(widthField.getText()));
				// Captures what is inside of the text field and stores into a temp variable
				double tempLength = Double.parseDouble(lengthField.getText());
				double tempWidth = Double.parseDouble(widthField.getText());

				// Sets length/width of project object
				/*
				 * myFirstProject.setLength(tempLength); myFirstProject.setWidth(tempWidth);
				 */

				// Set cost of project object
				myFirstProject.setCost(myFirstProject.getCost());
				// Set time of project object
				myFirstProject.setTime(myFirstProject.getTime());

				// Set text for labels with the respective getters, and some need a string to
				// double conversion.
				costLabelCalculated.setText(Double.toString(myFirstProject.getCost()));
				timeLabelCalculated.setText(Double.toString(myFirstProject.getTime()));
				difficultyLabelCalculated.setText(myFirstProject.getDifficulty());
				sizeOfProjectLabelCalculated.setText(myFirstProject.getSizeOfProject());

				// Double checking that length/width are properly stored
				System.out.println("Length: " + tempLength);
				System.out.println("Width: " + tempWidth);

			}
		});

		// Adds listener for when cancel button is clicked, brings you to home.
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				goToNextPanelFromCurrentPanel(myUserDataForNewProjectPanel, myMainPanel);
				myFrame.revalidate();
				myFrame.repaint();

			}
		});

		// Adds panel that holds the labels and text fields into the outermost panel
		myUserDataForNewProjectPanel.add(userDataPanel);

		// FILLS IN THE GRID SPACES FOR FORMATTING REASONS (3 X 3 GRID)
		myUserDataForNewProjectPanel.add(new JPanel());

		// myUserDataForNewProjectPanel.add(new JPanel());
		myUserDataForNewProjectPanel.add(calculatedDataPanel);

		// FILLS IN THE GRID SPACES FOR FORMATTING REASONS (3 X 3 GRID)
		myUserDataForNewProjectPanel.add(new JPanel());
		myUserDataForNewProjectPanel.add(new JPanel());
		myUserDataForNewProjectPanel.add(new JPanel());
		myUserDataForNewProjectPanel.add(new JPanel());

		// Adds panel that holds the calculate and cancel button into the outermost grid
		myUserDataForNewProjectPanel.add(buttonPanel);

	}

	/**
	 * ** CURRENTLY UNUSED ** ** CURRENTLY UNUSED ** ** CURRENTLY UNUSED **
	 * 
	 */
	private void makeCalculatedPanelNonEdit() {
		// Default JPanel to hold text fields and labels
		JPanel userDataPanel = new JPanel();

		// Panel to hold cancel and calculate buttons
		JPanel buttonPanel = new JPanel(new GridLayout(3, 0, 3, 10));

		JLabel costLabel = new JLabel("Cost: ");

		JLabel timeLabel = new JLabel("Time: ");

		JLabel difficultyLabel = new JLabel("Difficulty: ");

		JLabel sizeOfProjectLabel = new JLabel("Size of project: ");

	}

	// *********************************************************
	// *********************************************************

	/**
	 * Creates a menu bar.
	 * 
	 * @return is a created menu bar.
	 */
	private JMenuBar menuBar() {
		final JMenuBar menuBar = new JMenuBar();
		final JMenu optionsMenu = new JMenu("Options");
		final JMenu fileMenu = new JMenu("File");

		final JMenuItem j = new JMenuItem("About...");
		j.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theEvent) {
				JOptionPane.showMessageDialog(null,
						"Team JJAQ\n" + "Team members: Anthony Trang, Jacob Dickson, " + "James Olmsted, Quynh Trinh\n"
								+ "Version number: " + myVersion.getVersion() + "\n",
						"About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		final JMenuItem j2 = new JMenuItem("Save");
		j2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theEvent) {
				try {
					myFirstProject.writeProjectFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		optionsMenu.add(j);
		fileMenu.add(j2);
		menuBar.add(fileMenu);

		menuBar.add(optionsMenu);

		return menuBar;
	}

	/**
	 * Sets the look and feel.
	 */

	private static void setLookAndFeel() {
		try {

			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (final UnsupportedLookAndFeelException e) {
			System.out.println("UnsupportedLookAndFeelException");
		} catch (final ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
		} catch (final InstantiationException e) {
			System.out.println("InstantiationException");
		} catch (final IllegalAccessException e) {
			System.out.println("IllegalAccessException");
		}
	}

	/**
	 * The main method.
	 * 
	 * @param theArgs is the command line arguments.
	 */
	public static void main(final String... theArgs) {
		setLookAndFeel();
		new MainGUI().start();
	}

}