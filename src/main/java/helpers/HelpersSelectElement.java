package helpers;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import java.util.List;

public class HelpersSelectElement {

    // ──────────────────────────────────────────
    // ✅ SÉLECTION PAR VALEUR (value="...")
    // ──────────────────────────────────────────

    public static void selectByValue(String selector, String value, Page page) {
        page.selectOption(selector, value);
    }

    public static void selectByValue(String selector, String value, Frame iframe) {
        iframe.selectOption(selector, value);
    }

    // ──────────────────────────────────────────
    // ✅ SÉLECTION PAR LABEL (texte visible)
    // ──────────────────────────────────────────

    public static void selectByLabel(String selector, String label, Page page) {
        page.selectOption(selector, new SelectOption().setLabel(label));
    }

    public static void selectByLabel(String selector, String label, Frame iframe) {
        iframe.selectOption(selector, new SelectOption().setLabel(label));
    }

    // ──────────────────────────────────────────
    // ✅ SÉLECTION PAR INDEX (0 = premier)
    // ──────────────────────────────────────────

    public static void selectByIndex(String selector, int index, Page page) {
        page.selectOption(selector, new SelectOption().setIndex(index));
    }

    public static void selectByIndex(String selector, int index, Frame iframe) {
        iframe.selectOption(selector, new SelectOption().setIndex(index));
    }

    // ──────────────────────────────────────────
    // ✅ SÉLECTION MULTIPLE PAR VALEURS
    // ──────────────────────────────────────────

    public static void selectMultipleByValue(String selector, Page page, String... values) {
        page.selectOption(selector, values);
    }

    public static void selectMultipleByValue(String selector, Frame iframe, String... values) {
        iframe.selectOption(selector, values);
    }

    // ──────────────────────────────────────────
    // ✅ SÉLECTION MULTIPLE PAR LABELS
    // ──────────────────────────────────────────

    public static void selectMultipleByLabel(String selector, Page page, String... labels) {
        SelectOption[] options = java.util.Arrays.stream(labels)
            .map(l -> new SelectOption().setLabel(l))
            .toArray(SelectOption[]::new);
        page.selectOption(selector, options);
    }

    public static void selectMultipleByLabel(String selector, Frame iframe, String... labels) {
        SelectOption[] options = java.util.Arrays.stream(labels)
            .map(l -> new SelectOption().setLabel(l))
            .toArray(SelectOption[]::new);
        iframe.selectOption(selector, options);
    }

    // ──────────────────────────────────────────
    // ✅ SÉLECTIONNER PREMIER ÉLÉMENT
    // ──────────────────────────────────────────

    public static void selectFirst(String selector, Page page) {
        page.selectOption(selector, new SelectOption().setIndex(0));
    }

    public static void selectFirst(String selector, Frame iframe) {
        iframe.selectOption(selector, new SelectOption().setIndex(0));
    }

    // ──────────────────────────────────────────
    // ✅ SÉLECTIONNER DERNIER ÉLÉMENT
    // ──────────────────────────────────────────

    public static void selectLast(String selector, Page page) {
        int count = getOptionsCount(selector, page);
        page.selectOption(selector, new SelectOption().setIndex(count - 1));
    }

    public static void selectLast(String selector, Frame iframe) {
        int count = getOptionsCount(selector, iframe);
        iframe.selectOption(selector, new SelectOption().setIndex(count - 1));
    }

    // ──────────────────────────────────────────
    // ✅ RÉCUPÉRER LA VALEUR SÉLECTIONNÉE
    // ──────────────────────────────────────────

    public static String getSelectedValue(String selector, Page page) {
        return (String) page.evaluate(
            "document.querySelector('" + selector + "').value"
        );
    }

    public static String getSelectedValue(String selector, Frame iframe) {
        return (String) iframe.evaluate(
            "document.querySelector('" + selector + "').value"
        );
    }

    // ──────────────────────────────────────────
    // ✅ RÉCUPÉRER LE LABEL SÉLECTIONNÉ
    // ──────────────────────────────────────────

    public static String getSelectedLabel(String selector, Page page) {
        return (String) page.evaluate(
            "document.querySelector('" + selector + "').selectedOptions[0].text"
        );
    }

    public static String getSelectedLabel(String selector, Frame iframe) {
        return (String) iframe.evaluate(
            "document.querySelector('" + selector + "').selectedOptions[0].text"
        );
    }

    // ──────────────────────────────────────────
    // ✅ COMPTER LES OPTIONS
    // ──────────────────────────────────────────

    public static int getOptionsCount(String selector, Page page) {
        return ((Number) page.evaluate(
            "document.querySelector('" + selector + "').options.length"
        )).intValue();
    }

    public static int getOptionsCount(String selector, Frame iframe) {
        return ((Number) iframe.evaluate(
            "document.querySelector('" + selector + "').options.length"
        )).intValue();
    }

    // ──────────────────────────────────────────
    // ✅ DEBUG — afficher toutes les options
    // ──────────────────────────────────────────

    public static void logAllOptions(String selector, Page page) {
        Object options = page.evaluate(
            "Array.from(document.querySelector('" + selector + "').options)" +
            ".map(o => '[' + o.index + '] value=' + o.value + ' label=' + o.text)"
        );
        System.out.println("=== OPTIONS DE " + selector + " ===");
        System.out.println(options);
    }

    public static void logAllOptions(String selector, Frame iframe) {
        Object options = iframe.evaluate(
            "Array.from(document.querySelector('" + selector + "').options)" +
            ".map(o => '[' + o.index + '] value=' + o.value + ' label=' + o.text)"
        );
        System.out.println("=== OPTIONS DE " + selector + " ===");
        System.out.println(options);
    }
}