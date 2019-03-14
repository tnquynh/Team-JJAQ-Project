
package View;

/**
 * This class creates the holds and organize an array list of Project objects for us in the program.
 * 
 * @author James Olmsted
 * @version Winter 2019
 */

import java.util.ArrayList;

public class Comparison {
	private ArrayList<Project> projects = new ArrayList<Project>();
	
	public Comparison() {
		
	}
	
	public Comparison(ArrayList<Project> p) {
		projects = p;
	}
	
	public void addProject(Project p) {
		projects.add(p);
	}
	
	public void removeProject(String s) {
		for(int i = 0; i<projects.size(); i++) {
			if(projects.get(i).getProjectName().equals(s)) {
				projects.remove(i);
				break;
			}
		}
	}
	
	public void sort(String s) {
		switch(s) {
		case "Space" :
			sortSpace();
			break;
		case "Cost" :
			sortCost();
			break;
		case "Time":
			sortTime();
			break;
		case "Difficulty":
			sortDifficulty();
			break;
		case "Size of Project" :
			sortSizeOfProject();
			break;
		default :
			break;
		}
	}
	
	public void sortSpace() {
		boolean change = true;
		while(change) {
			change = false;
			for(int i = 0; i<projects.size()-1; i++) {
				if(projects.get(i).getSpace() < projects.get(i+1).getSpace()) {
					Project temp = new Project();
					temp = projects.get(i);
					projects.set(i,projects.get(i+1));
					projects.set(i+1,temp );
					change = true;
				}
			}
		}	
	}
	
	public void sortCost() {
		boolean change = true;
		while(change) {
			change = false;
			for(int i = 0; i<projects.size()-1; i++) {
				if(projects.get(i).getCost() < projects.get(i+1).getCost()) {
					Project temp = new Project();
					temp = projects.get(i);
					projects.set(i,projects.get(i+1));
					projects.set(i+1,temp );
					change = true;
				}
			}
		}
	}
	
	public void sortTime() {
		boolean change = true;
		while(change) {
			change = false;
			for(int i = 0; i<projects.size()-1; i++) {
				if(projects.get(i).getTime() < projects.get(i+1).getTime()) {
					Project temp = new Project();
					temp = projects.get(i);
					projects.set(i,projects.get(i+1));
					projects.set(i+1,temp );
					change = true;
				}
			}
		}
	}
	
	public void sortDifficulty() {
		boolean change = true;
		while(change) {
			change = false;
			for(int i = 0; i<projects.size()-1; i++) {
				if(projects.get(i).getDifficultySort() < projects.get(i+1).getDifficultySort()) {
					Project temp = new Project();
					temp = projects.get(i);
					projects.set(i,projects.get(i+1));
					projects.set(i+1,temp );
					change = true;
				}
			}
		}
	}
	
	public void sortSizeOfProject() {
		boolean change = true;
		while(change) {
			change = false;
			for(int i = 0; i<projects.size()-1; i++) {
				if(projects.get(i).getSizeOfProjectSort() < projects.get(i+1).getSizeOfProjectSort()) {
					Project temp = new Project();
					temp = projects.get(i);
					projects.set(i,projects.get(i+1));
					projects.set(i+1,temp );
					change = true;
				}
			}
		}
	}
	
	public ArrayList<Project> getProjects(){
		return projects;
	}
}

