package BaseTest;

import org.junit.jupiter.api.Test;

import helpers.IframeProvider;
import pages.Quartier;
import service.NavigationService;

public class QuartierTest extends BaseTest{

	protected NavigationService nav;
	protected Quartier quartier;

	
	
	@Test
	public void testExprimezvous() throws InterruptedException {
	
		
		quartier=new Quartier(this.page,this.iframeProvider);
		double i=Math.random();
		quartier.publishContent(i+"");
		
		Thread.sleep(5000);
		//make commenatire
		quartier.publishComment("ceci est un commentaire automatiser pour" + i);
		
		Thread.sleep(5000);
		quartier.makeReaction();
		
		quartier.communicateWithPartisan();
		
		
		Thread.sleep(5000);
		
		quartier.seenParticapant();
		
		
		Thread.sleep(5000);
		quartier.sendInvitation();
		
		
		Thread.sleep(5000);
		quartier.gotoAdhesion();
		
		Thread.sleep(5000);
		quartier.makeActionOnPhotoMenu();
		
		Thread.sleep(5000);
		quartier.gotoAttributionRoles();
	}
	
	
}
