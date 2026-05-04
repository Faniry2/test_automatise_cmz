package service;

import com.microsoft.playwright.Page;

public class AuthService extends AbstractPlaywrightService {

	// ✅ Reçoit la page existante
    public AuthService(Page page) {
        super(page);
    }
    
	@Override
    public void navigate(String url) {
        page.navigate(url);
    }

    @Override
    public boolean isPageLoaded() {
        return page.isVisible("input[name='email']");
    }

    public void login(String email, String password) {
        page.fill("input[name='email']", email);
        page.fill("input[name='password']", password);
        page.click("button[type='submit']");
        page.waitForLoadState();
    }

    public void logout() {
        page.click("#logout-btn");
    }

    public boolean isLoggedIn() {
        return page.isVisible("#user-profile");
    }
}