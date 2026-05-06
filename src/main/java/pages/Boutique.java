package pages;

import com.microsoft.playwright.Page;
import helpers.HelpersInputFile;
import helpers.HelpersSelectElement;
import helpers.IframeProvider;

public class Boutique extends Tribus {

    // ──────────────────────────────────────────
    // Sélecteurs boutique
    // ──────────────────────────────────────────
    private static final String BTN_CREATE_BOUTIQUE    = "#tribu_t_conteuneur > div:nth-child(2) > button";
    private static final String INPUT_BOUTIQUE_NAME    = "#Shop_name";
    private static final String INPUT_DELIVERY_ADDRESS = "#Shop_location";
    private static final String BTN_SUBMIT_BOUTIQUE    = "[data-action-name=\"saveAndAddAnother\"]";

    // ──────────────────────────────────────────
    // Sélecteurs article
    // ──────────────────────────────────────────
    private static final String BTN_CREATE_NEW_PRODUCT = "[data-bs-target=\"#newProduct_ea\"]";
    private static final String INPUT_NOM_PRODUIT      = "#Article_name";
    private static final String DESCRIPTION_INPUT      = "#Article_description";
    private static final String INPUT_CHOIS_IMAGE      = "#Article_featuredImage_file";
    private static final String SELECT_CATEGORIE       = "#Article_categoryChoice";
    private static final String SELECT_ETAT            = "#Article_statusChoice";
    private static final String INPUT_QUANTITE_STOCK   = "#Article_stock";
    private static final String INPUT_PRIX             = "#Article_price";
    private static final String BTN_MAKE_PUBLICATION   = "[form=\"new-Article-form\"]";

    // ──────────────────────────────────────────
    // Sélecteurs visibilité
    // ──────────────────────────────────────────
    private static final String CHECKBOX_IS_ACTIVE     = "#Article_isActive";
    private static final String CHECKBOX_IS_PUBLIC     = "#Article_isPublic";
    private static final String CHECKBOX_IS_PRIVATE    = "#Article_isPrivate";
    private static final String CHECKBOX_IS_SEMI_PUBLIC= "#Article_isSemiPublic";

    // ──────────────────────────────────────────
    // Sélecteurs promo
    // ──────────────────────────────────────────
    private static final String CHECKBOX_IS_PROMO      = "#Article_isPromotion";
    private static final String INPUT_PRIX_PROMO       = "#Article_lastPrice";

    // ──────────────────────────────────────────
    // Sélecteurs media
    // ──────────────────────────────────────────
    private static final String INPUT_MEDIA_FILE       = "#Article_media_1_file";
    private static final String BTN_ADD_MEDIA          = ".field-collection-add-button";

    // ──────────────────────────────────────────
    // Valeurs catégories
    // ──────────────────────────────────────────
    public static final String CAT_TELEPHONIE  = "10";
    public static final String CAT_INFORMATIQUE= "9";
    public static final String CAT_LIVRES      = "8";
    public static final String CAT_ALIMENTATION= "7";
    public static final String CAT_SPORT       = "6";
    public static final String CAT_MAISON      = "5";
    public static final String CAT_BEAUTE      = "4";
    public static final String CAT_MODE        = "3";
    public static final String CAT_ELECTRONIQUE= "2";

    // ──────────────────────────────────────────
    // Valeurs états
    // ──────────────────────────────────────────
    public static final String ETAT_BON_ETAT   = "4";
    public static final String ETAT_OCCASION   = "3";
    public static final String ETAT_NEUF       = "2";
    public static final String ETAT_AUTHENTIQUE= "1";

    // ──────────────────────────────────────────
    // Constructeur
    // ──────────────────────────────────────────
    public Boutique(Page page, IframeProvider iframeProvider) {
        super(page, iframeProvider);
    }

    // ══════════════════════════════════════════
    // ✅ BOUTIQUE
    // ══════════════════════════════════════════

    public void createBoutique(String nom, String adresse) {
        iframe.click(BTN_CREATE_BOUTIQUE);
        waitHelper.waitForElementInFrame(iframe, INPUT_BOUTIQUE_NAME);
        iframe.fill(INPUT_BOUTIQUE_NAME, nom);
        iframe.fill(INPUT_DELIVERY_ADDRESS, adresse);
        iframe.click(BTN_SUBMIT_BOUTIQUE);
        waitHelper.waitForElementToDisappear(iframe, INPUT_BOUTIQUE_NAME);
    }

    public void createBoutique() {
        createBoutique("Boutique-" + Math.random(), "Analakely");
    }

    // ══════════════════════════════════════════
    // ✅ ARTICLE — méthode principale
    // ══════════════════════════════════════════

    public void createArticle(String nom, String description,
                               String image, String categorie,
                               String etat, String stock, String prix,
                               ArticleVisibility visibility,
                               boolean isActive,
                               boolean isPromo, String prixPromo,
                               String... mediaFiles) {

        // 1️⃣ Ouvrir formulaire
        iframe.click(BTN_CREATE_NEW_PRODUCT);
        waitHelper.waitForElementInFrame(iframe, INPUT_NOM_PRODUIT);

        // 2️⃣ Infos de base
        iframe.fill(INPUT_NOM_PRODUIT, nom);
        iframe.fill(DESCRIPTION_INPUT, description);

        // 3️⃣ Image principale
        HelpersInputFile.putFileFromResources(INPUT_CHOIS_IMAGE, image, iframe);

        // 4️⃣ Catégorie et état
        HelpersSelectElement.selectByValue(SELECT_CATEGORIE, categorie, iframe);
        HelpersSelectElement.selectByValue(SELECT_ETAT, etat, iframe);

        // 5️⃣ Stock et prix
        iframe.fill(INPUT_QUANTITE_STOCK, stock);
        iframe.fill(INPUT_PRIX, prix);

        // 6️⃣ Promo
        if (isPromo && prixPromo != null) {
            setPromo(true, prixPromo);
        }

        // 7️⃣ Visibilité
        setVisibility(visibility);

        // 8️⃣ Actif/Inactif
        setActive(isActive);

        // 9️⃣ Médias galerie
        if (mediaFiles != null && mediaFiles.length > 0) {
            addMultipleMedia(mediaFiles);
        }

        // 🔟 Soumettre
        publicateArticle();
    }

    // ══════════════════════════════════════════
    // ✅ SIMULER TOUS LES CAS — méthode centrale
    // ══════════════════════════════════════════

    public void simulateTestCase(ArticleTestCase testCase) {

        String nom  = "Article-" + testCase.name() + "-" + Math.random();
        String desc = "Description cas : " + testCase.name();
        String img  = "image1.jpg";
        String cat  = CAT_ELECTRONIQUE;
        String etat = ETAT_NEUF;

        switch (testCase) {

            case SIMPLE:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PRIVATE,
                    true, false, null);
                break;

            case AVEC_PROMO:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PRIVATE,
                    true, true, "19.99");
                break;

            case PUBLIC_ACTIF:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PUBLIC,
                    true, false, null);
                break;

            case PRIVE_ACTIF:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PRIVATE,
                    true, false, null);
                break;

            case SEMI_PUBLIC_ACTIF:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.SEMI_PUBLIC,
                    true, false, null);
                break;

            case PUBLIC_INACTIF:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PUBLIC,
                    false, false, null);
                break;

            case PRIVE_INACTIF:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PRIVATE,
                    false, false, null);
                break;

            case SEMI_PUBLIC_INACTIF:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.SEMI_PUBLIC,
                    false, false, null);
                break;

            case AVEC_PROMO_PUBLIC:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PUBLIC,
                    true, true, "19.99");
                break;

            case AVEC_PROMO_PRIVE:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PRIVATE,
                    true, true, "19.99");
                break;

            case AVEC_PROMO_SEMI_PUBLIC:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.SEMI_PUBLIC,
                    true, true, "19.99");
                break;

            case AVEC_MEDIA:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PRIVATE,
                    true, false, null,
                    "image1.jpg", "image2.jpg");
                break;

            case COMPLET:
                createArticle(nom, desc, img, cat, etat,
                    "10", "25.99",
                    ArticleVisibility.PUBLIC,
                    true, true, "19.99",
                    "image1.jpg", "image2.jpg");
                break;
        }
    }

    // ══════════════════════════════════════════
    // ✅ VISIBILITÉ
    // ══════════════════════════════════════════

    public void setVisibility(ArticleVisibility visibility) {

        // Décocher tous d'abord
        uncheckAll();

        switch (visibility) {

            case PUBLIC:
                // Décocher privé → cocher public
                setCheckbox(CHECKBOX_IS_PUBLIC, true);
                setCheckbox(CHECKBOX_IS_PRIVATE, false);
                setCheckbox(CHECKBOX_IS_SEMI_PUBLIC, false);
                break;

            case PRIVATE:
                // Cocher privé → décocher les autres
                setCheckbox(CHECKBOX_IS_PRIVATE, true);
                setCheckbox(CHECKBOX_IS_PUBLIC, false);
                setCheckbox(CHECKBOX_IS_SEMI_PUBLIC, false);
                break;

            case SEMI_PUBLIC:
                // Cocher semi-public → décocher les autres
                setCheckbox(CHECKBOX_IS_SEMI_PUBLIC, true);
                setCheckbox(CHECKBOX_IS_PUBLIC, false);
                setCheckbox(CHECKBOX_IS_PRIVATE, false);
                break;
        }
    }

    public void setActive(boolean active) {
        setCheckbox(CHECKBOX_IS_ACTIVE, active);
    }

    // ══════════════════════════════════════════
    // ✅ PROMO
    // ══════════════════════════════════════════

    public void setPromo(boolean active, String prixPromo) {
        setCheckbox(CHECKBOX_IS_PROMO, active);
        if (active && prixPromo != null) {
            waitHelper.waitForElementInFrame(iframe, INPUT_PRIX_PROMO);
            iframe.fill(INPUT_PRIX_PROMO, prixPromo);
        }
    }

    // ══════════════════════════════════════════
    // ✅ MÉDIAS
    // ══════════════════════════════════════════

    public void addMedia(String imageFile) {
        waitHelper.waitForElementInFrame(iframe, INPUT_MEDIA_FILE);
        HelpersInputFile.putFileFromResources(INPUT_MEDIA_FILE, imageFile, iframe);
    }

    public void addMultipleMedia(String... imageFiles) {
        for (String image : imageFiles) {
            iframe.click(BTN_ADD_MEDIA);
            waitHelper.waitForElementInFrame(iframe, INPUT_MEDIA_FILE);
            HelpersInputFile.putFileFromResources(INPUT_MEDIA_FILE, image, iframe);
        }
    }

    // ══════════════════════════════════════════
    // ✅ SOUMETTRE
    // ══════════════════════════════════════════

    public void publicateArticle() {
        waitHelper.waitForElementInFrame(iframe, BTN_MAKE_PUBLICATION);
        iframe.click(BTN_MAKE_PUBLICATION);
        waitHelper.waitForElementToDisappear(iframe, INPUT_NOM_PRODUIT);
    }

    // ══════════════════════════════════════════
    // Méthodes privées utilitaires
    // ══════════════════════════════════════════

    private void setCheckbox(String selector, boolean shouldBeChecked) {
        boolean isChecked = iframe.isChecked(selector);
        if (shouldBeChecked && !isChecked) {
            iframe.click(selector);
        } else if (!shouldBeChecked && isChecked) {
            iframe.click(selector);
        }
    }

    private void uncheckAll() {
        setCheckbox(CHECKBOX_IS_PUBLIC, false);
        setCheckbox(CHECKBOX_IS_PRIVATE, false);
        setCheckbox(CHECKBOX_IS_SEMI_PUBLIC, false);
    }

    @Override
    public boolean isPageLoaded() {
        return iframe.isVisible(BTN_CREATE_NEW_PRODUCT);
    }
}