package BaseTest;

import service.AuthService;
import service.NavigationService;
import org.junit.jupiter.api.*;

import com.microsoft.playwright.Page;

import helpers.IframeProvider;
import helpers.WaitHelper;
import pages.ModalWelcome;
public abstract class BaseTest {

	
	public Page page;
    protected NavigationService navigationService;
    protected AuthService authService;
    protected WaitHelper waitHelper;
    protected ModalWelcome modalWelcome; 
	protected IframeProvider iframeProvider;

    @BeforeEach
    public void setup() {

        // 1️⃣ Créer le browser + la page
        navigationService = new NavigationService();
        navigationService.init();

        // 2️⃣ Naviguer vers l'accueil
        navigationService.navigate("");
        page = navigationService.page; // ✅ récupérer la page
        
     // 3️⃣ Partager la page avec WaitHelper
        waitHelper = new WaitHelper(page);
        
        waitHelper.waitForElement(".close-fa-Ma");
        
        modalWelcome=new ModalWelcome(page);
        modalWelcome.closeModalWelcome(".close-fa-Ma");

        
        waitHelper.waitForElement(".content_entete_cta_connexion");

        // 4️⃣ Partager la MÊME page avec AuthService ✅
        authService = new AuthService(page);
        authService.navigate("https://www.consomyzone.com/connexion");
        authService.login("faniryandriamihaingo@gmail.com", "Vitale123+");
        
        iframeProvider=new IframeProvider(this.page);
    }

    @AfterEach
    public void tearDown() {
        //navigationService.close(); // ✅ ferme tout proprement
    }
   
}
