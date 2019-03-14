package View;

/**
 * This class creates the SizeOfProject object for use by the Project and Comparison class.
 * 
 * @author James Olmsted
 * @version Winter 2019
 */

public class SizeOfProject {
	private String displayValue;
	private Double sortValue;
	
	public SizeOfProject() {
		
	}
	

	public SizeOfProject(Double s, Double c, Double t) {
		sortValue = (s*t+c)/100;
		
		if(sortValue <= 10) {
			displayValue = "Low";
		}else if(sortValue <= 20) {
			displayValue = "Medium";
		}else {
			displayValue = "High";
		}
	}
	
	public String getDisplay() {
		return displayValue;
	}
	
	//space, time, cost

	public void update(Double s, Double t, Double c) {
		sortValue = (s*t+c)/100;
		
		if(sortValue <= 10) {
			displayValue = "Low";
		}else if(sortValue <= 20) {
			displayValue = "Medium";
		}else {
			displayValue = "High";
		}
	}
	
	public Double getSort() {
		return sortValue;
	}

}
