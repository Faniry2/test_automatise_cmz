package pages;

public enum ArticleTestCase {

	SIMPLE,                    // article basique
    AVEC_PROMO,                // article avec promotion
    PUBLIC_ACTIF,              // public + activé
    PRIVE_ACTIF,               // privé + activé (défaut)
    SEMI_PUBLIC_ACTIF,         // semi-public + activé
    PUBLIC_INACTIF,            // public + désactivé
    PRIVE_INACTIF,             // privé + désactivé
    SEMI_PUBLIC_INACTIF,       // semi-public + désactivé
    AVEC_PROMO_PUBLIC,         // promo + public
    AVEC_PROMO_PRIVE,          // promo + privé
    AVEC_PROMO_SEMI_PUBLIC,    // promo + semi-public
    AVEC_MEDIA,                // avec galerie photos
    COMPLET
}
