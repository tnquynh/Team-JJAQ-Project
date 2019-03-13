package View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class creates the Project and holds the necessary data for the program.
 * 
 * @author James Olmsted
 * @version Winter 2019
 */

public class Project {
	private String projectName; 
	private String projectType;
	private Double length;
	private Double width;
	private Double space;
	private Double time;
	private Double cost;
	private boolean setCost = false;
	private Difficulty difficulty;
	private SizeOfProject sizeOfProject;
	
	public Project() {
		
	}
	
	public Project(String pn ,String pt, Double l, Double w) {
		projectName = pn;
		projectType = pt;
		length = l;
		width = w;
		space = length*width;
		time = space/750;  /*Time calculated using average and in number of days assuming
		 feet used for length and width*/
		cost = 10*space; //High end cost of wood flooring per square foot
		difficulty = new Difficulty(space,time);
		sizeOfProject = new SizeOfProject(space,time,cost);
	}
	
	//If the user knows/set the cost of the project
	public Project(String pn ,String pt, Double l, Double w, Double c) {
		projectName = pn;
		projectType = pt;
		length = l;
		width = w;
		space = length*width;
		time = space/750;
		cost = c;
		setCost = true;
		difficulty = new Difficulty(space,time);
		sizeOfProject = new SizeOfProject(space,time,cost);
	}
	
	public void setProjectName(String pn) {
		projectName = pn;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectType(String pt) {
		projectType = pt;
	}
	
	public String getProjectType() {
		return projectType;
	}
	
	public void setLength(Double l) {
		length = l;
		space = length*width;
		difficulty.update(space, time);
		sizeOfProject.update(space, time, cost);
	}
	
	public Double getLength() {
		return length;
	}
	
	public void setWidth(Double w) {
		width = w;
		space = length*width;
		difficulty.update(space, time);
		sizeOfProject.update(space, time, cost);
	}
	
	public Double getWidth() {
		return width;
	}
	
	public Double getSpace() {
		return space;
	}
	
	public Double getTime() {
		return time;
	}
	
	public void setCost(Double c) {
		cost = c;
		sizeOfProject.update(space, time, cost);
		setCost = true;
	}
	
	public Double getCost() {
		return cost;
	}
	
	public String getDifficulty() {
		return difficulty.getDisplay();
	}
	
	public Double getDifficultySort() {
		return difficulty.getSort();
	}
	
	
	public String getSizeOfProject() {
		return sizeOfProject.getDisplay();
	}
	
	public Double getSizeOfProjectSort() {
		return sizeOfProject.getSort();
	}
	
	public void writeProjectFile() throws IOException {
		File directory = new File(".");
		String fileName =	directory.getCanonicalPath() + File.separator + projectName +".txt";
		File file = new File(fileName);
		FileWriter fw = new FileWriter(file );
		fw.write(projectName);
		fw.write(System.lineSeparator());
		fw.write(projectType);
		fw.write(System.lineSeparator());
		fw.write(length.toString());
		fw.write(System.lineSeparator());
		fw.write(width.toString());
		fw.write(System.lineSeparator());
		if(setCost) {
			fw.write(cost.toString());
			fw.write(System.lineSeparator());
		}
		fw.close();
	}
}
