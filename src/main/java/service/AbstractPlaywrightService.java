package service;

import java.nio.file.Paths;

import com.microsoft.playwright.*;

public abstract class AbstractPlaywrightService {

	 public Page page;

    // ✅ Constructeur vide — pour NavigationService uniquement
    public AbstractPlaywrightService() {}

    // ✅ Constructeur avec page — pour tous les autres services
    public AbstractPlaywrightService(Page page) {
        this.page = page;
    }

    public abstract void navigate(String url);
    public abstract boolean isPageLoaded();
	
}
