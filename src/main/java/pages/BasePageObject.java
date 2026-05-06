package pages;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import helpers.IframeProvider;
import helpers.IframeProvider.IframeType;

public abstract class BasePageObject {

    protected Page page;
    public Frame iframe;
    protected IframeProvider iframeProvider;

    // ✅ Constructeur simple avec juste la page
    public BasePageObject(Page page) {
        this.page = page;
    }

    // ✅ Méthode pour initialiser l'iframe quand nécessaire
    public void setIframe(IframeProvider iframeProvider,
                           IframeType type, String value) {
        this.iframeProvider = iframeProvider;
        this.iframe         = iframeProvider.getMainIframe(type, value);
       
    }

    // ✅ Méthodes communes
    public String getPageTitle()  { return page.title(); }
    public String getCurrentUrl() { return page.url(); }

    public void takeScreenshot(String name) {
        page.screenshot(new Page.ScreenshotOptions()
            .setPath(java.nio.file.Paths.get("screenshots/" + name + ".png"))
            .setFullPage(true));
    }

    public abstract boolean isPageLoaded();
}