package View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class VersionInfo {
	private String myVersion;

	public VersionInfo() {
		myVersion = "0";
		getInfo();
	}
	private void getInfo() {
		Properties prop = new Properties();
		InputStream is = this.getClass().getResourceAsStream("app.config");
		try {
			prop.load(is);
		} catch(IOException e2) {
			System.out.println(e2);
		}
		myVersion = prop.getProperty("app.version");
	}
    String getVersion() {
		return myVersion;
	}
}

