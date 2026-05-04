package pages;

import com.microsoft.playwright.Page;

public class ModalWelcome {

	protected Page page; 
	public ModalWelcome(Page page) {
		this.page=page;
	}
	
	public void closeModalWelcome(String cssSelector) {
		page.click(cssSelector);
	}
}
