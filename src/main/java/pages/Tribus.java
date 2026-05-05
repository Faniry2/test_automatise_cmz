package pages;

import com.microsoft.playwright.Page;

public class Tribus extends BasePageObject{
	
	public static final String EXPRIMEZ_VOUS="#btnShowModalAddPub";
	
	//cette tribu existe seulement sur le compte en prod de Faniry
	public static final String FIRST_TRIBU= "[data-table-name=\"tribu_t_3_feuille_blanche\"]";
	public static final String SHOW_ACTUALITE= "#ulActualites";
	public static final String BTN_COMMUNICATION= "#fetch_new_letter_fans_tribuT_jheo_js";
	public static final String BTN_PARTISAN= "#partisan_jheo_js";
	public static final String BTN_SHOW_PARTICIPATION= "#navBarTribu > div.slick-list.draggable > div > li.listNavBarTribu.invitation.slick-slide > a";
	public static final String BTN_ADHESION= "#fetch_adhesion_tribuT_elie_js";
	
	
	
	public Tribus(Page page) {
		//TODO Auto-generated constructor stub
		super(page);
	}

	
	public void gotoTribu() {
		iframe.click(FIRST_TRIBU);
	}
	
	@Override
    public boolean isPageLoaded() {
        return iframe.isVisible(EXPRIMEZ_VOUS);
    }
}
