package BaseTest;

import org.junit.jupiter.api.Test;

import pages.ArticleTestCase;
import pages.Boutique;

public class BoutiqueTest extends BaseTest{

	private Boutique boutique;
	@Test
	public void testTousLesCas() {
	    boutique = new Boutique(this.page, this.iframeProvider);

	    // Tester tous les cas automatiquement
	    for (ArticleTestCase cas : ArticleTestCase.values()) {
	        System.out.println("=== Test cas : " + cas.name() + " ===");
	        boutique.simulateTestCase(cas);
	    }
	}

	// Ou cas par cas
	@Test public void testSimple()          { boutique.simulateTestCase(ArticleTestCase.SIMPLE); }
	@Test public void testPublicActif()     { boutique.simulateTestCase(ArticleTestCase.PUBLIC_ACTIF); }
	@Test public void testAvecPromo()       { boutique.simulateTestCase(ArticleTestCase.AVEC_PROMO); }
	@Test public void testComplet()         { boutique.simulateTestCase(ArticleTestCase.COMPLET); }
}
