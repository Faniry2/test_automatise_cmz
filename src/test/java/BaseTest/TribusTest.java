package BaseTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pages.Tribus;

public class TribusTest extends BaseTest {

	 private Tribus tribus;
	 
	 @BeforeEach
    public void init() {
        tribus = new Tribus(page, iframeProvider);
        tribus.gotoTribu();
    }
	 
	@Test
    @DisplayName("Nouvelle communication par email")
    public void testNouvellesCommunicationEmail() {

        tribus.nouvellesCommunication(
            "destinataire@email.com",
            "Objet test automatisé",
            "Bonjour, ceci est un message automatisé.",
            true  // ✅ envoyer par email
        );
    }

    @Test
    @DisplayName("Nouvelle communication par message interne")
    public void testNouvellesCommunicationMessageInterne() {

        tribus.nouvellesCommunication(
            "destinataire@email.com",
            "Objet test automatisé",
            "Bonjour, ceci est un message automatisé.",
            false  // ✅ envoyer par message interne
        );
    }

    @Test
    @DisplayName("Nouvelle communication avec membre de la liste")
    public void testNouvellesCommunicationAvecMembre() {

        // 1️⃣ Ouvrir communication
        tribus.iframe.click(Tribus.BTN_COMMUNICATION);

        // 2️⃣ Ajouter depuis la liste
        tribus.ajouterDestinataireDepuisListe();

        // 3️⃣ Envoyer
        tribus.nouvellesCommunication(
            "",                           // email vide car membre ajouté
            "Message pour la tribu",
            "Bonjour à tous les membres !",
            true
        );
    }

    @Test
    @DisplayName("Nouvelle communication avec pièce jointe")
    public void testNouvellesCommunicationAvecPieceJointe() {

        tribus.nouvellesCommunication(
            "destinataire@email.com",
            "Objet avec pièce jointe",
            "Veuillez trouver ci-joint le document.",
            true
        );

        tribus.ajouterPieceJointe("document.pdf");
	    }
    @Test
    @DisplayName("Inviter premier partisan")
    public void testInviterPremierPartisan() {
        tribus.ouvrirInvitation();
        tribus.inviterPremierPartisan();
    }

    @Test
    @DisplayName("Inviter partisan par nom")
    public void testInviterPartisanParNom() {
        tribus.ouvrirInvitation();
        tribus.inviterPartisanParNom("jheo");
    }

    @Test
    @DisplayName("Ouvrir invitation puis annuler")
    public void testOuvrirInvitationEtAnnuler() {
        tribus.ouvrirInvitation();
        tribus.annulerInvitation();
    }

    @Test
    @DisplayName("Voir suivi des invitations")
    public void testVoirSuiviInvitations() {
        tribus.ouvrirInvitation();
        tribus.voirSuiviInvitations();
    }
    @Test
    @DisplayName("Créer un album vide")
    public void testCreerAlbum() {
        tribus.ouvrirGalerie();
        tribus.creerAlbum("Album-" + Math.random());
    }

    @Test
    @DisplayName("Créer album et ajouter une image")
    public void testCreerAlbumAvecUneImage() {
        tribus.ouvrirGalerie();
        tribus.creerAlbum("Mon Album Test");
        tribus.ajouterImageDansAlbum("image1.jpg");
    }

    @Test
    @DisplayName("Créer album et ajouter plusieurs images")
    public void testCreerAlbumAvecPlusieursImages() {
        tribus.ouvrirGalerie();
        tribus.creerAlbumAvecImages(
            "Album Vacances",
            "image1.jpg",
            "image2.jpg",
            "image3.jpg"
        );
    }
    @Test
    @DisplayName("Créer une sous-tribu simple")
    public void testCreerSousTribu() {
        tribus.creerSousTribu(
            "Ma sous-tribu test",
            "Description de ma sous-tribu"
        );
    }

    @Test
    @DisplayName("Créer une sous-tribu avec image")
    public void testCreerSousTribyAvecImage() {
        tribus.creerSousTribu(
            "Sous-tribu avec image",
            "Description avec image",
            "image1.jpg"
        );
    }

    @Test
    @DisplayName("Ouvrir modal et annuler")
    public void testAnnulerCreationSousTribu() {
        tribus.ouvrirCreationSousTribu();
        tribus.annulerCreationSousTribu();
    }

    @Test
    @DisplayName("Créer sous-tribu données aléatoires")
    public void testCreerSousTribyRapide() {
        tribus.creerSousTribu();
    }
    



}
