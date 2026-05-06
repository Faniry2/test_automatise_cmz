package pages;

import com.microsoft.playwright.Page;

public class Boutique extends Tribus{

	public Boutique(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public void createBoutique() {
		String btn_create_boutique="#tribu_t_conteuneur > div:nth-child(2) > button";
		String input_boutique_name="#Shop_name";
		String delivry_adresse_input="#Shop_location";
		String btn_make_create_boutique="[data-action-name=\"saveAndAddAnother\"]";
		iframe.click(btn_create_boutique);
		
		iframe.fill(input_boutique_name,""+Math.random());
		iframe.fill(delivry_adresse_input,"analakely eo am izay maika");
		iframe.click(btn_make_create_boutique);
		
	}
	
	public void createArticle() {
		String create_new_product="[data-bs-target=\"#newProduct_ea\"]";
		String description_input="#Article_description";
		
		
		iframe.click(create_new_product);
	}
	
	public void publicateArticle() {
		
	}

}
