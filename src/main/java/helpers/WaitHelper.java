package helpers;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

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
    
 // ✅ Attendre qu'un élément disparaisse
    public void waitForElementToDisappear(Frame iframe, String selector) {
        iframe.waitForSelector(selector,
            new Frame.WaitForSelectorOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(15000)
        );
    }

    // ✅ Version page principale
    public void waitForElementToDisappear(String selector) {
        page.waitForSelector(selector,
            new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(15000)
        );
    }
}
