package helpers;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;

public class WaitHelper {

	private Page page;

    public WaitHelper(Page page) {
        this.page = page;
    }

    public void waitForElement(String selector) {
        page.waitForSelector(selector);
    }
    
    // ✅ Attendre un élément dans un iframe
    public void waitForElementInFrame(Frame iframe, String selector) {
        iframe.waitForSelector(selector);
    }

    public void waitForUrl(String url) {
        page.waitForURL(url);
    }

    public void waitSeconds(int seconds) {
        page.waitForTimeout(seconds * 1000);
    }
}
