package pages;

import com.microsoft.playwright.Page;
import helpers.IframeProvider;
import helpers.IframeProvider.IframeType;
import helpers.WaitHelper;

public class Quartier extends BasePageObject {

    private WaitHelper waitHelper;
    
    //EMAILS
    public static final String INVITATION_EMAIL ="christellerandriamalala@gmail.com";

    // Sélecteurs
    public static final String EXPRIMEZ_VOUS    = "#btnShowModalAddPub";
    private static final String CHAMP_TEXTE     = "#publication_legend";
    private static final String BTN_ENVOYER     = ".send_new_pub_js_jheo";
    private static final String BTN_SHOW_COMMENT		= "span.content_nbr_comment_jheo_js";
    private static final String CHAMP_COMMENT		= ".textarea_content_jheo_js";
    private static final String BTN_SEND_COMMENT 	=".cta_persitNewComment_jheo_js";
    private static final String BTN_MAKE_REACTION 	=".bi-heart.like ";
    private static final String BTN_COMMUNIQUER		="#fetch_new_letter_fans_tribug_jheo_js";
    private static final String BTN_COMMUNIQUER_ADD_CONTACT="#cmzAddBtn";
    private static final String BTN_CHOOSE_CONTACT ="#cmzMembersList > div";
    private static final String BTN_SEND_NEWSLETTER = ".btn_submit_sendNewsLetter_jheo_js";
    private static final String BTN_PARTICIPAN   ="#fetch_member_tribug_jheo_js";
    private static final String BTN_SHOW_INVITATION= "#fetch_invitation_tribug_nanta_js"; 
    private static final String INVITATION_ADD_EMAIL ="#exampleFormControlInput1";
    private static final String BTN_SEND_INVITATION =".btn_send_invitation_js_jheo";
    private static final String ADHESION_BTN ="#fetch_adhesion_tribug_elie_js";
    private static final String GO_TO_PHOTO= "#fetch_photo_tribug_jheo_js";
    private static final String SEE_AGENDA_PHOTO= "#gal-agenda-g";
    private static final String SEE_ALBUM_G= "#gal-album-g"; 
    private static final String CREATE_ALBUM  =".create-album";
    private static final String INPUT_NAME_ALBUM= ".value-name-album-g-tomm-js";
    private static final String BTN_CREATE_ALBUM="#creation-album-g";
    private static final String ADD_IMAGE_SELECTOR=  ".album-card-clickable";
    private static final String SHOW_ATTRIBUTION_ROLES ="#privilege_management_nanta_js";
    private static final String SHOW_SONDAGE= ".votre_sondage";
    
    //#tribug_01_apremont_apremont_32 > div.card-reaction > p > 
    public Quartier(Page page, IframeProvider iframeProvider) {
        super(page);

        // ✅ Initialise l'iframe avec l'ID
        this.setIframe(iframeProvider, IframeType.ID, "iframe_right");

        this.waitHelper = new WaitHelper(page);
    }

    public void makeAnPublication(String texteToPublish) {
        // ✅ iframe au lieu de page
        iframe.fill(CHAMP_TEXTE, texteToPublish);
        iframe.click(BTN_ENVOYER);
    }

    public void publishContent(String publication) {
        // ✅ iframe au lieu de page
        iframe.click(EXPRIMEZ_VOUS);
        waitHelper.waitForElementInFrame(iframe, CHAMP_TEXTE);
        this.makeAnPublication(publication);
    }
    
    public void publishComment(String comment) {
    	iframe.locator(BTN_SHOW_COMMENT).first().click();
    	waitHelper.waitForElementInFrame(iframe, CHAMP_COMMENT);
    	this.makeComment(comment);
    }
    
    public void makeReaction() {
    	iframe.locator(BTN_MAKE_REACTION).first().click();
    }

    public void seenParticapant() {
    	iframe.click(BTN_PARTICIPAN);
    }
    
    public void sendInvitation() {
    	this.gotoInvitation(INVITATION_EMAIL);
    }
    
    
    
    public void communicateWithPartisan() {
    	this.gotoCommunication();
    }
    
    public void gotoAdhesion() {
    	iframe.click(ADHESION_BTN);
    	
    	
    }
    
    public void makeActionOnPhotoMenu() throws InterruptedException {
    	this.gotoPhotos();
    }
    
    private void gotoCommunication() {
    	iframe.click(BTN_COMMUNIQUER);
    	
    	waitHelper.waitForElementInFrame(iframe, BTN_COMMUNIQUER_ADD_CONTACT);
    	
    	//add contact
    	iframe.click(BTN_COMMUNIQUER_ADD_CONTACT);
    	iframe.locator(BTN_CHOOSE_CONTACT).first().click();
    	
    	//send 
    	iframe.click(BTN_SEND_NEWSLETTER);
    	
    }
    
    private void makeComment(String comment) {
    	iframe.fill(CHAMP_COMMENT, comment);
    	iframe.click(BTN_SEND_COMMENT);
    }
    
    private void gotoInvitation(String email) {
    	iframe.click(BTN_SHOW_INVITATION);
    	
    	waitHelper.waitForElementInFrame(iframe,INVITATION_ADD_EMAIL);
    	
    	iframe.fill(INVITATION_ADD_EMAIL, email);
    	iframe.locator(BTN_SEND_INVITATION).first().click();
    }
    
    private void gotoPhotos() throws InterruptedException {
    	iframe.click(GO_TO_PHOTO);
    	waitHelper.waitForElementInFrame(iframe,GO_TO_PHOTO);
    	
    	iframe.click(SEE_AGENDA_PHOTO);
    	
    	Thread.sleep(5000);
    	
    	iframe.click(SEE_ALBUM_G);
    	iframe.click(CREATE_ALBUM);
    	
    	Thread.sleep(5000);
    	iframe.fill(INPUT_NAME_ALBUM, "test-album"+Math.random());
    	iframe.click(BTN_CREATE_ALBUM);
    	Thread.sleep(5000);
    	iframe.locator(ADD_IMAGE_SELECTOR).first().click();
    	
    }
    
    public void gotoAttributionRoles() throws InterruptedException {
    	
    	String btn_attribute_role="#tablePrivilege > tbody > tr.even > td:nth-child(3) > button";
    	String btn_create_role="#content_btn_to_edit_jheo_js > button";
    	String input_role="#role_name_jheo_js";
    	String save_new_role_btn =".btn_action_save_edit_privillege_jheo_js";
    	
    	iframe.click(SHOW_ATTRIBUTION_ROLES);
    	
    	waitHelper.waitForElementInFrame(iframe,btn_attribute_role);
    	iframe.click(btn_attribute_role);
    	waitHelper.waitForElementInFrame(iframe,btn_create_role);
    	iframe.click(btn_create_role);
    	Thread.sleep(2000);
    	iframe.fill(input_role, "gigolo");
    	iframe.click(save_new_role_btn);
    	
    	
    	
    	
    }
    public void makeSondage(boolean useEtab) {
    	String btnSendSondage=".sondage-tomm-js";
    	if(useEtab) {
    		this.makeSondageDefault();
    	}else {
    		this.makeSondageDefault();
    		this.gotoSondageChooseRestaurant();
    	}
    	iframe.click(btnSendSondage);
    }
    
   
    
    private void makeSondageDefault() {
    	String modal="[data-bs-target=\"#sondageModal\"]";
    	String question_input="#inputQuestion";
    	String chooseAutreResponse="#flexSwitchReponseMultiplie";
    	String chooseMultipleQuestion="#flexSwitchReponseMultiplie";
    	String addResponseInput =".input-reponse-tomm-js";
    	String btnAddQuestionBtn="#button-addon2";
    	
    	
    	iframe.click(SHOW_SONDAGE);
    	
      	waitHelper.waitForElementInFrame(iframe,modal);
    	iframe.click(modal);
    	
    	iframe.fill(question_input, "le test il bon ou non");
    	iframe.fill(addResponseInput, "est que la methode de test automatique marche "+ Math.random());
    	
    	iframe.click(chooseAutreResponse);
    	iframe.click(chooseMultipleQuestion);
    	
    	iframe.click(btnAddQuestionBtn);
    	
    	iframe.click(btnAddQuestionBtn);
    	
    }
    
    private void gotoSondageChooseRestaurant() {
    	String showmodalRestaurant="[for=\"showresto\"]";
    	String chooseRestaurantBtn="#tableEtabCMZ > tbody > tr:nth-child(1) > td:nth-child(5) > button";
    	String applyChoice="#applyAction";
    	
    	
    	iframe.click(showmodalRestaurant);
    	iframe.click(chooseRestaurantBtn);
    	iframe.click(applyChoice);
    }
    
    public void showParametre(String newName, String description) {
    	String btnShowParametre= "#settingTribuG";
    	String btnSetChoice="#updateTribuInfo";
    	
    	iframe.click(btnShowParametre);
    	
    	this.changeName(newName);
    	this.changeDescription(description);
    	
    	iframe.click(btnSetChoice);
    }
    
    private void changeName(String newName) {
    	String input_change_name="#updateTribuTName";
    	iframe.fill(input_change_name, newName);
    }
    
    private void changeDescription(String description) {
    	String input_change_description="#update_description";
    	iframe.fill(input_change_description, description);
    }
    
    @Override
    public boolean isPageLoaded() {
        return iframe.isVisible(EXPRIMEZ_VOUS);
    }
}