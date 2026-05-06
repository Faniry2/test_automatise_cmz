package pages;

import com.microsoft.playwright.Page;

import helpers.HelpersInputFile;
import helpers.IframeProvider;
import helpers.WaitHelper;
import helpers.IframeProvider.IframeType;

public class Tribus extends BasePageObject{
	
	public static final String EXPRIMEZ_VOUS="#btnShowModalAddPub";
	protected WaitHelper waitHelper;
	
	//cette tribu existe seulement sur le compte en prod de Faniry
	public static final String FIRST_TRIBU= "[data-table-name=\"tribu_t_3_feuille_blanche\"]";
	public static final String SHOW_ACTUALITE= "#ulActualites";
	public static final String BTN_COMMUNICATION= "#fetch_new_letter_fans_tribuT_jheo_js";
	public static final String BTN_PARTISAN= "#partisan_jheo_js";
	public static final String BTN_SHOW_PARTICIPATION= "#navBarTribu > div.slick-list.draggable > div > li.listNavBarTribu.invitation.slick-slide > a";
	public static final String BTN_ADHESION= "#fetch_adhesion_tribuT_elie_js";
	public static final String BTN_GALLERY=	"#see-gallery";
	public static final String BTN_ATTRIBUTION_ROLE ="#privilege_management_jheo_js";
	public static final String BTN_="#fetch_sous_tribuT_jheo_js";
	public static final String BTN_SHOW_INVITATIONS="#navBarTribu > div.slick-list.draggable > div > li.listNavBarTribu.invitation.slick-slide > a";
	
	
	// ══════════════════════════════════════════
	// Sélecteurs Communication
	// ══════════════════════════════════════════
	private static final String TAB_NOUVELLE_COMMUNICATION  = "a[data-element='new-letter-create']";
	private static final String INPUT_DESTINATAIRE          = "#exampleFormControlInput1";
	private static final String BTN_ADD_DESTINATAIRE        = "#cmzAddBtn";
	private static final String LIST_MEMBRES                = "#cmzMembersList";
	private static final String INPUT_OBJET                 = "#newLetter_object_input";
	private static final String EDITOR_DESCRIPTION         = ".ck-editor__editable";
	private static final String BTN_SEND_EMAIL             = ".btn_submit_sendNewsLetter_jheo_js";
	private static final String BTN_SEND_MESSAGE_INTERNE   = ".btn_submit_sendNewsLetter_by_message_jheo_js";
	private static final String INPUT_PIECE_JOINTE         = "#piece_joint_new_letter";
	private static final String INPUT_IMAGE_JOINTE         = "#piece_joint_image_new_letter";

	
	// ══════════════════════════════════════════
	// Sélecteurs Invitation
	// ══════════════════════════════════════════
	private static final String TAB_PARTISANS_CMZ    = "a[data-element='table-tribuG-member']";
	private static final String TAB_SUIVI_INVITATION = "a[onclick*='historique']";
	private static final String TABLE_PARTISANS      = "#tribuGMemberTable";
	private static final String BTN_INVITER_PARTISAN = ".dts-btn[onclick*='inviteUser']";
	private static final String SEARCH_PARTISAN      = "#tribuGMemberTable_filter input";
	private static final String BLOC_SUIVI           = "#blockHistInvitation";
	
	
	// ══════════════════════════════════════════
	// Sélecteurs Galerie
	// ══════════════════════════════════════════
	private static final String BTN_GALLERY_MENU       = "#see-gallery";
	private static final String GALLERY_WRAPPER        = "#gallery";

	// Onglets galerie
	private static final String TAB_ALBUMS             = "[data-status='gal-album-t']";
	private static final String TAB_PUBLICATIONS       = "[data-status='gal-publication-t']";
	private static final String TAB_AGENDA             = "[data-status='gal-agenda-t']";
	private static final String TAB_TELECHARGEMENT     = "[data-status='gal-telechargement-t']";

	// Création album
	private static final String BTN_CREATE_ALBUM       = ".create-album";
	private static final String MODAL_CREATE_ALBUM     = "#createAlbumTribuT";
	private static final String INPUT_NOM_ALBUM        = ".value-name-album-g-tomm-js";
	private static final String BTN_CONFIRM_ALBUM      = "#creation-album-g";

	// Album et images
	private static final String ALBUM_CARD             = ".album-card-clickable";
	private static final String INPUT_UPLOAD_IMAGE     = "input[type='file']";
	
	public Tribus(Page page, IframeProvider iframeProvider) {
		//TODO Auto-generated constructor stub
		super(page);
		this.setIframe(iframeProvider, IframeType.ID, "iframe_right");
		this.waitHelper=new WaitHelper(page);
	}

	public void goToPartisan() {
		this.iframe.click(BTN_ADHESION);
	}
	
	
	public void ouvrirAdhesion() {
		this.iframe.click(BTN_ADHESION);
	}
	
	
	public void gotoTribu() {
		iframe.click(FIRST_TRIBU);
	}
	
	
	
	// ══════════════════════════════════════════
	// ✅ Ouvrir la section invitation
	// ══════════════════════════════════════════
	public void ouvrirInvitation() {
	    iframe.click(BTN_SHOW_INVITATIONS);
	    waitHelper.waitForElementInFrame(iframe, TABLE_PARTISANS);
	}

	// ══════════════════════════════════════════
	// ✅ Inviter le premier partisan disponible
	// ══════════════════════════════════════════
	public void inviterPremierPartisan() {
	    waitHelper.waitForElementInFrame(iframe, BTN_INVITER_PARTISAN);
	    iframe.locator(BTN_INVITER_PARTISAN).first().click();
	    page.waitForLoadState();
	    System.out.println("✅ Invitation envoyée au premier partisan");
	}

	// ══════════════════════════════════════════
	// ✅ Inviter un partisan par nom
	// ══════════════════════════════════════════
	public void inviterPartisanParNom(String nom) {
	    waitHelper.waitForElementInFrame(iframe, SEARCH_PARTISAN);
	    iframe.fill(SEARCH_PARTISAN, nom);
	    waitHelper.waitSeconds(1);
	    iframe.locator(BTN_INVITER_PARTISAN).first().click();
	    page.waitForLoadState();
	    System.out.println("✅ Invitation envoyée à : " + nom);
	}

	// ══════════════════════════════════════════
	// ✅ Annuler — fermer sans inviter
	// ══════════════════════════════════════════
	public void annulerInvitation() {
	    // Vider la recherche et revenir à l'état initial
	    if (iframe.isVisible(SEARCH_PARTISAN)) {
	        iframe.fill(SEARCH_PARTISAN, "");
	    }
	    System.out.println("❌ Invitation annulée");
	}

	// ══════════════════════════════════════════
	// ✅ Voir le suivi des invitations
	// ══════════════════════════════════════════
	public void voirSuiviInvitations() {
	    iframe.click(TAB_SUIVI_INVITATION);
	    waitHelper.waitForElementInFrame(iframe, BLOC_SUIVI);
	}
	
	// ══════════════════════════════════════════
	// ✅ Nouvelle Communication — email
	// ══════════════════════════════════════════
	public void nouvellesCommunication(String destinataire,
	                                    String objet,
	                                    String description,
	                                    boolean sendByEmail) {

	    // 1️⃣ Ouvrir le bouton communication
	    iframe.click(BTN_COMMUNICATION);

	    // 2️⃣ Cliquer sur onglet "Nouvelle communication"
	    waitHelper.waitForElementInFrame(iframe, TAB_NOUVELLE_COMMUNICATION);
	    iframe.click(TAB_NOUVELLE_COMMUNICATION);

	    // 3️⃣ Destinataire — email direct
	    waitHelper.waitForElementInFrame(iframe, INPUT_DESTINATAIRE);
	    iframe.fill(INPUT_DESTINATAIRE, destinataire);

	    // 4️⃣ Objet
	    waitHelper.waitForElementInFrame(iframe, INPUT_OBJET);
	    iframe.fill(INPUT_OBJET, objet);

	    // 5️⃣ Description dans CKEditor
	    waitHelper.waitForElementInFrame(iframe, EDITOR_DESCRIPTION);
	    iframe.click(EDITOR_DESCRIPTION);
	    // Sélectionner tout et remplacer
	    iframe.evaluate("document.querySelector('.ck-editor__editable').focus()");
	    iframe.press(EDITOR_DESCRIPTION, "Control+a");
	    iframe.type(EDITOR_DESCRIPTION, description);

	    // 6️⃣ Envoyer
	    if (sendByEmail) {
	        iframe.click(BTN_SEND_EMAIL);
	    } else {
	        iframe.click(BTN_SEND_MESSAGE_INTERNE);
	    }

	    page.waitForLoadState();
	}

	// ✅ Ajouter destinataire depuis la liste membres
	public void ajouterDestinataireDepuisListe() {
	    waitHelper.waitForElementInFrame(iframe, BTN_ADD_DESTINATAIRE);
	    iframe.click(BTN_ADD_DESTINATAIRE);

	    // Prendre le premier membre de la liste
	    waitHelper.waitForElementInFrame(iframe, LIST_MEMBRES + " > div");
	    iframe.locator(LIST_MEMBRES + " > div").first().click();
	}

	// ✅ Ajouter pièce jointe
	public void ajouterPieceJointe(String fileName) {
	    HelpersInputFile.putFileFromResources(INPUT_PIECE_JOINTE, fileName, iframe);
	}

	// ✅ Ajouter image
	public void ajouterImage(String fileName) {
	    HelpersInputFile.putFileFromResources(INPUT_IMAGE_JOINTE, fileName, iframe);
	}
	
	public void ouvrirGalerie() {
	    iframe.click(BTN_GALLERY_MENU);
	    waitHelper.waitForElementInFrame(iframe, GALLERY_WRAPPER);
	}

	// ══════════════════════════════════════════
	// ✅ Créer un album
	// ══════════════════════════════════════════
	public void creerAlbum(String nomAlbum) {

	    // 1️⃣ Aller sur l'onglet Albums
	    waitHelper.waitForElementInFrame(iframe, BTN_CREATE_ALBUM);

	    // 2️⃣ Cliquer sur "Nouvel Album"
	    iframe.click(BTN_CREATE_ALBUM);

	    // 3️⃣ Attendre le modal
	    waitHelper.waitForElementInFrame(iframe, INPUT_NOM_ALBUM);

	    // 4️⃣ Remplir le nom
	    iframe.fill(INPUT_NOM_ALBUM, nomAlbum);

	    // 5️⃣ Confirmer
	    iframe.click(BTN_CONFIRM_ALBUM);

	    // 6️⃣ Attendre que l'album apparaisse
	    waitHelper.waitForElementInFrame(iframe, ALBUM_CARD);
	    System.out.println("✅ Album créé : " + nomAlbum);
	}

	// ══════════════════════════════════════════
	// ✅ Ajouter une image dans le premier album
	// ══════════════════════════════════════════
	public void ajouterImageDansAlbum(String imageFile) {

	    // 1️⃣ Cliquer sur le premier album
	    waitHelper.waitForElementInFrame(iframe, ALBUM_CARD);
	    iframe.locator(ALBUM_CARD).first().click();

	    // 2️⃣ Attendre l'input file
	    waitHelper.waitForElementInFrame(iframe, INPUT_UPLOAD_IMAGE);

	    // 3️⃣ Upload
	    HelpersInputFile.putFileFromResources(INPUT_UPLOAD_IMAGE, imageFile, iframe);
	    page.waitForLoadState();
	    System.out.println("✅ Image ajoutée : " + imageFile);
	}

	// ══════════════════════════════════════════
	// ✅ Ajouter plusieurs images dans un album
	// ══════════════════════════════════════════
	public void ajouterImagesAlbum(String... images) {
	    waitHelper.waitForElementInFrame(iframe, ALBUM_CARD);
	    iframe.locator(ALBUM_CARD).first().click();

	    for (String image : images) {
	        waitHelper.waitForElementInFrame(iframe, INPUT_UPLOAD_IMAGE);
	        HelpersInputFile.putFileFromResources(INPUT_UPLOAD_IMAGE, image, iframe);
	        waitHelper.waitSeconds(1);
	        System.out.println("✅ Image ajoutée : " + image);
	    }
	}

	// ══════════════════════════════════════════
	// ✅ Créer album ET ajouter images — tout en un
	// ══════════════════════════════════════════
	public void creerAlbumAvecImages(String nomAlbum, String... images) {

	    // 1️⃣ Créer l'album
	    creerAlbum(nomAlbum);

	    // 2️⃣ Ajouter les images
	    ajouterImagesAlbum(images);

	    System.out.println("✅ Album '" + nomAlbum + "' créé avec " + images.length + " image(s)");
	}
	@Override
    public boolean isPageLoaded() {
        return iframe.isVisible(EXPRIMEZ_VOUS);
    }
}
