package helpers;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;

public class IframeProvider {

    private final Page page;

    // ✅ Types de recherche possibles
    public enum IframeType {
        ID,
        NAME,
        URL,
        INDEX
    }

    public IframeProvider(Page page) {
        this.page = page;
    }

    // ✅ getMainIframe flexible — choisir le type et la valeur
    public Frame getMainIframe(IframeType type, String value) {

        switch (type) {

            case ID:
                Frame byId = getIframeById(value);
                if (byId != null) return byId;
                throw new RuntimeException("Iframe introuvable par ID : " + value);

            case NAME:
                Frame byName = getIframeByName(value);
                if (byName != null) return byName;
                throw new RuntimeException("Iframe introuvable par nom : " + value);

            case URL:
                Frame byUrl = getIframeByUrl(value);
                if (byUrl != null) return byUrl;
                throw new RuntimeException("Iframe introuvable par URL : " + value);

            case INDEX:
                Frame byIndex = getIframeByIndex(Integer.parseInt(value));
                if (byIndex != null) return byIndex;
                throw new RuntimeException("Iframe introuvable par index : " + value);

            default:
                throw new RuntimeException("Type d'iframe inconnu : " + type);
        }
    }

    // ✅ Par ID
    public Frame getIframeById(String id) {
        page.waitForSelector("#" + id);
        ElementHandle element = page.querySelector("#" + id);
        if (element != null) {
            Frame f = element.contentFrame();
            if (f != null) return f;
        }
        return null;
    }

    // ✅ Par nom
    public Frame getIframeByName(String name) {
        Frame f = page.frame(name);
        if (f != null) return f;
        return null;
    }

    // ✅ Par URL
    public Frame getIframeByUrl(String urlPattern) {
        Frame f = page.frameByUrl("**/" + urlPattern + "**");
        if (f != null) return f;
        return null;
    }

    // ✅ Par index
    public Frame getIframeByIndex(int index) {
        if (page.frames().size() > index) {
            return page.frames().get(index);
        }
        return null;
    }

    // ✅ Debug — affiche tous les iframes
    public void logAllIframes() {
        System.out.println("=== IFRAMES DISPONIBLES ===");
        System.out.println("Total : " + page.frames().size());
        page.frames().forEach(frame -> {
            System.out.println("Nom   : " + frame.name());
            System.out.println("URL   : " + frame.url());
            System.out.println("---");
        });
    }
}