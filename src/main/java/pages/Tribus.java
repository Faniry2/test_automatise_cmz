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
	public static final String BTN_SOUS_TRIBU="#fetch_sous_tribuT_jheo_js";
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
	
	
	// ══════════════════════════════════════════
	// Sélecteurs Sous-Tribu
	// ══════════════════════════════════════════

	private static final String SEARCH_SOUS_TRIBU      = "#tableSousTrib input[type='text']";
	
	// ══════════════════════════════════════════
	// Sélecteurs Modal Création Sous-Tribu
	// ══════════════════════════════════════════
	private static final String BTN_CREATE_SOUS_TRIBU = "[data-bs-target='#ModalCreationTribuT']";
	private static final String MODAL_CREATION_TRIBU  = "#ModalCreationTribuT";
	private static final String INPUT_IMAGE_TRIBU     = "#tribu_creation_upload";
	private static final String INPUT_NOM_TRIBU       = "#tribu_creation_tribuTName";
	private static final String INPUT_DESC_TRIBU      = "#tribu_creation_description";
	private static final String BTN_SUBMIT_TRIBU      = ".submit_create_tribu_jheo_js";
	private static final String BTN_CLOSE_MODAL       = "#ModalCreationTribuT .btn-close";
	private static final String TABLE_SOUS_TRIBU      = "#content_list_sub_tribuT_jheo_js";
	
	
	// ══════════════════════════════════════════
	// Sélecteurs Rattachement
	// ══════════════════════════════════════════
	private static final String TABLE_RATTACHEMENT        = "#tribuTable";
	private static final String SEARCH_RATTACHEMENT       = "#dt-search-0";
	private static final String BTN_ENVOYER_DEMANDE       = ".cta_request_parrainer_jheo_js";
	private static final String BTN_ANNULER_DEMANDE       = "[class*='cta_cancel_tribu']";
	private static final String BTN_DEMANDE_EN_ATTENTE    = "[class*='cta_request_tribu']:not([class*='cta_cancel'])";
	private static final String BTN_SHOW_RATACHEMENT	  ="#action_tribuT_parrainer_jheo_js";
	
	
	// ══════════════════════════════════════════
	// Sélecteurs Modal Sondage
	// ══════════════════════════════════════════
	private static final String MODAL_SONDAGE            = ".reload-sondage-tomm-js";
	private static final String INPUT_QUESTION           = "#inputQuestion";
	private static final String INPUT_REPONSE            = ".input-reponse-tomm-js";
	private static final String BTN_AJOUTER_REPONSE      = ".ajout-reponse-tomm-js";
	private static final String LIST_REPONSES            = ".list-reponse-tomm-js";
	private static final String BTN_TYPE_SAISIR          = "button[for='saisie']";
	private static final String BTN_TYPE_RESTAURANT      = "button[for='showresto']";
	private static final String BTN_TYPE_MARCHE          = "button[for='showmarche']";
	private static final String SWITCH_AUTRE_REPONSE     = "#flexSwitchAutreReponse";
	private static final String SWITCH_REPONSE_MULTIPLE  = "#flexSwitchReponseMultiplie";
	private static final String INPUT_DATE_EXPIRATION    = ".date-expiration-tomm-js";
	private static final String BTN_TOUS_PARTISANS       = "#all_partisan_select";
	private static final String BTN_MENTION_MANUELLE     = "#manual_partisan_select";
	private static final String BTN_ENVOYER_SONDAGE      = ".sondage-tomm-js";
	private static final String ALERT_PREMIUM            = "#shareSondageTribuTMessage";

	
	// ══════════════════════════════════════════
	// Sélecteurs Sondage
	// ══════════════════════════════════════════
	private static final String BTN_CREER_SONDAGE        = "button[onclick*='createSondageT']";
	private static final String TAB_SONDAGES_ACTIFS      = "a.active_sondage";
	private static final String TAB_HISTORIQUE_SONDAGE   = "a.historique_sondage";
	private static final String CONTAINER_ACTIFS         = ".reload-vos-sondage-tomm-js";
	private static final String CONTAINER_HISTORIQUE     = ".historique-vos-sondage-elie-js";
	private static final String MSG_AUCUN_SONDAGE        = ".reload-vos-sondage-tomm-js .alert-warning";
	private static final String CARTE_SONDAGE            = ".card.border-primary";
	private static final String TITRE_SONDAGE            = ".card-title";
	private static final String BADGE_EXPIRE             = ".badge.text-bg-danger";
	private static final String BTN_REPUBLIER            = "[onclick*='relanceSondageTribuT']";
	// ══════════════════════════════════════════
	// Enum type de réponse
	// ══════════════════════════════════════════
	public enum TypeReponseSondage {
	    SAISIR,
	    RESTAURANT,
	    MARCHE
	}
	
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
		// ✅ Ouvrir le modal
		// ══════════════════════════════════════════
		public void ouvrirCreationSousTribu() {
		    iframe.click(BTN_SOUS_TRIBU);
		    waitHelper.waitForElementInFrame(iframe, TABLE_SOUS_TRIBU);
		    iframe.click(BTN_CREATE_SOUS_TRIBU);
		    waitHelper.waitForElementInFrame(iframe, INPUT_NOM_TRIBU);
		    System.out.println("✅ Modal création sous-tribu ouvert");
		}
	
		// ══════════════════════════════════════════
		// ✅ Créer une sous-tribu complète
		// ══════════════════════════════════════════
		public void creerSousTribu(String nom, String description, String image) {
	
		    // 1️⃣ Ouvrir le modal
		    ouvrirCreationSousTribu();
	
		    // 2️⃣ Image (optionnel)
		    if (image != null) {
		        HelpersInputFile.putFileFromResources(INPUT_IMAGE_TRIBU, image, iframe);
		    }
	
		    // 3️⃣ Nom
		    iframe.fill(INPUT_NOM_TRIBU, nom);
	
		    // 4️⃣ Description
		    iframe.fill(INPUT_DESC_TRIBU, description);
	
		    // 5️⃣ Soumettre
		    iframe.click(BTN_SUBMIT_TRIBU);
		    page.waitForLoadState();
	
		    System.out.println("✅ Sous-tribu créée : " + nom);
		}
	
		// Version sans image
		public void creerSousTribu(String nom, String description) {
		    creerSousTribu(nom, description, null);
		}
	
		// Version rapide données aléatoires
		public void creerSousTribu() {
		    creerSousTribu(
		        "Sous-tribu-" + Math.random(),
		        "Description automatisée",
		        null
		    );
		}
	
		// ══════════════════════════════════════════
		// ✅ Annuler la création
		// ══════════════════════════════════════════
		public void annulerCreationSousTribu() {
		    iframe.click(BTN_CLOSE_MODAL);
		    page.waitForLoadState();
		    System.out.println("❌ Création sous-tribu annulée");
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
	// ══════════════════════════════════════════
	// ✅ Ouvrir le rattachement
	// ══════════════════════════════════════════
	public void ouvrirRattachement() {
	    iframe.click(BTN_SHOW_RATACHEMENT);
	    waitHelper.waitForElementInFrame(iframe, TABLE_RATTACHEMENT);
	    System.out.println("✅ Section rattachement ouverte");
	}

	// ══════════════════════════════════════════
	// ✅ Envoyer demande au premier disponible
	// ══════════════════════════════════════════
	public void envoyerDemandeRattachement() {
	    waitHelper.waitForElementInFrame(iframe, BTN_ENVOYER_DEMANDE);
	    iframe.locator(BTN_ENVOYER_DEMANDE).first().click();
	    page.waitForLoadState();
	    System.out.println("✅ Demande de rattachement envoyée");
	}

	// ══════════════════════════════════════════
	// ✅ Envoyer demande par nom de tribu
	// ══════════════════════════════════════════
	public void envoyerDemandeRattachementParNom(String nomTribu) {

	    // 1️⃣ Rechercher la tribu
	    waitHelper.waitForElementInFrame(iframe, SEARCH_RATTACHEMENT);
	    iframe.fill(SEARCH_RATTACHEMENT, nomTribu);
	    waitHelper.waitSeconds(1);

	    // 2️⃣ Cliquer sur le bouton demande
	    waitHelper.waitForElementInFrame(iframe, BTN_ENVOYER_DEMANDE);
	    iframe.locator(BTN_ENVOYER_DEMANDE).first().click();
	    page.waitForLoadState();

	    System.out.println("✅ Demande envoyée à : " + nomTribu);
	}

	// ══════════════════════════════════════════
	// ✅ Annuler une demande en attente
	// ══════════════════════════════════════════
	public void annulerDemandeRattachement() {
	    waitHelper.waitForElementInFrame(iframe, BTN_ANNULER_DEMANDE);
	    iframe.locator(BTN_ANNULER_DEMANDE).first().click();
	    page.waitForLoadState();
	    System.out.println("❌ Demande de rattachement annulée");
	}

	// ══════════════════════════════════════════
	// ✅ Annuler demande par nom de tribu
	// ══════════════════════════════════════════
	public void annulerDemandeRattachementParNom(String nomTribu) {

	    // 1️⃣ Rechercher la tribu
	    iframe.fill(SEARCH_RATTACHEMENT, nomTribu);
	    waitHelper.waitSeconds(1);

	    // 2️⃣ Annuler
	    waitHelper.waitForElementInFrame(iframe, BTN_ANNULER_DEMANDE);
	    iframe.locator(BTN_ANNULER_DEMANDE).first().click();
	    page.waitForLoadState();

	    System.out.println("❌ Demande annulée pour : " + nomTribu);
	}

	// ══════════════════════════════════════════
	// ✅ Vérifier si demande en attente
	// ══════════════════════════════════════════
	public boolean hasDemandeEnAttente() {
	    return iframe.isVisible(BTN_ANNULER_DEMANDE);
	}
	
	// ══════════════════════════════════════════
	// ✅ Créer un sondage complet
	// ══════════════════════════════════════════
	public void creerSondage(String question,
	                          TypeReponseSondage typeReponse,
	                          boolean autreReponse,
	                          boolean reponsesMultiples,
	                          String dateExpiration,
	                          boolean tousLesPartisans,
	                          String... reponses) {

	    // 1️⃣ Ouvrir le modal
	    ouvrirCreationSondage();
	    waitHelper.waitForElementInFrame(iframe, INPUT_QUESTION);

	    // 2️⃣ Remplir la question
	    iframe.fill(INPUT_QUESTION, question);

	    // 3️⃣ Choisir le type de réponse
	    switch (typeReponse) {
	        case SAISIR:
	            iframe.click(BTN_TYPE_SAISIR);
	            break;
	        case RESTAURANT:
	            iframe.click(BTN_TYPE_RESTAURANT);
	            break;
	        case MARCHE:
	            iframe.click(BTN_TYPE_MARCHE);
	            break;
	    }

	    // 4️⃣ Ajouter les réponses
	    for (String reponse : reponses) {
	        iframe.fill(INPUT_REPONSE, reponse);
	        iframe.click(BTN_AJOUTER_REPONSE);
	        waitHelper.waitSeconds(1);
	        System.out.println("✅ Réponse ajoutée : " + reponse);
	    }

	    // 5️⃣ Options
	    setCheckbox(SWITCH_AUTRE_REPONSE, autreReponse);
	    setCheckbox(SWITCH_REPONSE_MULTIPLE, reponsesMultiples);

	    // 6️⃣ Date d'expiration
	    if (dateExpiration != null) {
	        iframe.fill(INPUT_DATE_EXPIRATION, dateExpiration);
	    }

	    // 7️⃣ Partisans
	    if (tousLesPartisans) {
	        iframe.click(BTN_TOUS_PARTISANS);
	    } else {
	        iframe.click(BTN_MENTION_MANUELLE);
	    }

	    // 8️⃣ Vérifier si premium requis
	    if (iframe.isVisible(ALERT_PREMIUM)) {
	        System.out.println("⚠️ Compte premium requis pour créer un sondage !");
	        return;
	    }

	    // 9️⃣ Envoyer
	    iframe.click(BTN_ENVOYER_SONDAGE);
	    page.waitForLoadState();
	    System.out.println("✅ Sondage créé : " + question);
	}

	// ══════════════════════════════════════════
	// ✅ Version simplifiée
	// ══════════════════════════════════════════
	public void creerSondageSimple(String question, String dateExpiration,
	                                String... reponses) {
	    creerSondage(
	        question,
	        TypeReponseSondage.SAISIR,
	        false,
	        false,
	        dateExpiration,
	        true,
	        reponses
	    );
	}

	// ✅ Utilitaire checkbox (déjà dans Boutique, à mettre dans BasePageObject)
	private void setCheckbox(String selector, boolean shouldBeChecked) {
	    boolean isChecked = iframe.isChecked(selector);
	    if (shouldBeChecked && !isChecked) {
	        iframe.click(selector);
	    } else if (!shouldBeChecked && isChecked) {
	        iframe.click(selector);
	    }
	}
	
	// ══════════════════════════════════════════
	// ✅ Ouvrir la section sondage
	// ══════════════════════════════════════════
	public void ouvrirSondage() {
	    iframe.click(SHOW_SONDAGE);
	    waitHelper.waitForElementInFrame(iframe, BTN_CREER_SONDAGE);
	    System.out.println("✅ Section sondage ouverte");
	}

	// ══════════════════════════════════════════
	// ✅ Créer un sondage — ouvre le modal
	// ══════════════════════════════════════════
	public void ouvrirCreationSondage() {
	    iframe.click(BTN_CREER_SONDAGE);
	    page.waitForLoadState();
	    System.out.println("✅ Modal création sondage ouvert");
	}

	// ══════════════════════════════════════════
	// ✅ Voir les sondages actifs
	// ══════════════════════════════════════════
	public void voirSondagesActifs() {
	    iframe.click(TAB_SONDAGES_ACTIFS);
	    waitHelper.waitForElementInFrame(iframe, CONTAINER_ACTIFS);
	}

	public boolean hasAucunSondageActif() {
	    return iframe.isVisible(MSG_AUCUN_SONDAGE);
	}

	// ══════════════════════════════════════════
	// ✅ Voir l'historique des sondages
	// ══════════════════════════════════════════
	public void voirHistoriqueSondages() {
	    iframe.click(TAB_HISTORIQUE_SONDAGE);
	    waitHelper.waitForElementInFrame(iframe, CONTAINER_HISTORIQUE);
	}

	public int getNombreSondagesHistorique() {
	    return iframe.locator(CARTE_SONDAGE).count();
	}

	// ══════════════════════════════════════════
	// ✅ Republier le premier sondage expiré
	// ══════════════════════════════════════════
	public void republierPremierSondage() {
	    voirHistoriqueSondages();
	    waitHelper.waitForElementInFrame(iframe, BTN_REPUBLIER);
	    iframe.locator(BTN_REPUBLIER).first().click();
	    page.waitForLoadState();
	    System.out.println("✅ Premier sondage republié");
	}

	// ══════════════════════════════════════════
	// ✅ Lire les titres des sondages historique
	// ══════════════════════════════════════════
	public void logTitresSondages() {
	    voirHistoriqueSondages();
	    int count = iframe.locator(TITRE_SONDAGE).count();
	    System.out.println("=== SONDAGES HISTORIQUE (" + count + ") ===");
	    for (int i = 0; i < count; i++) {
	        String titre = iframe.locator(TITRE_SONDAGE).nth(i).textContent();
	        System.out.println("[" + i + "] " + titre.trim());
	    }
	}
	@Override
    public boolean isPageLoaded() {
        return iframe.isVisible(EXPRIMEZ_VOUS);
    }
}
