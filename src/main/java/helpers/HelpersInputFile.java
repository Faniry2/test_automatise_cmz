package helpers;

import java.nio.file.Paths;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;

public class HelpersInputFile {

	
	
	public static void clearInputFile() {
		iframe.setInputFiles("#monInput", new Path[0]);
	}
	
	public static void putFile(String inputFileSelector, String path,Frame iframe) {
		iframe.setInputFiles(inputFileSelector, Paths.get(path));
	}
	
	public static void putFile(String inputFileSelector, String path,Page page) {
		page.setInputFiles(inputFileSelector, Paths.get(path));
	}
}
