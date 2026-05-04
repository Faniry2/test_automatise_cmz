package service;

import com.microsoft.playwright.*;

public class NavigationService extends AbstractPlaywrightService{

	
	private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private static final String BASE_URL = "https://www.consomyzone.com";

    public NavigationService() {
        super(); // pas de page encore
    }

    // ✅ Crée le browser et la page
    public void init() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(500)
        );
        context = browser.newContext(
            new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
        );
        page = context.newPage(); // ✅ page créée ici
    }
    @Override
    public void navigate(String url) {
        page.navigate(BASE_URL + url);
        page.waitForLoadState();
    }

    @Override
    public boolean isPageLoaded() {
        return page.url() != null;
    }

    public void goToHome()   { navigate("/"); }
    public void goToAgenda() { navigate("/agenda"); }
    public void goBack()     { page.goBack(); }

    public String getCurrentUrl() {
        return page.url();
    }
    
    public void close() {
        if (page != null)       page.close();
        if (context != null)    context.close();
        if (browser != null)    browser.close();
        if (playwright != null) playwright.close();
    }
}
