package helpers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;

public class HelpersInputFile {

    // 📁 Chemin de base des ressources de test
    private static final String BASE_PATH = "src/test/resources/";

    // ──────────────────────────────────────────
    // ✅ CLEAR INPUT
    // ──────────────────────────────────────────

    public static void clearInputFile(String selector, Page page) {
        page.setInputFiles(selector, new Path[0]);
    }

    public static void clearInputFile(String selector, Frame iframe) {
        iframe.setInputFiles(selector, new Path[0]);
    }

    // ──────────────────────────────────────────
    // ✅ UN SEUL FICHIER
    // ──────────────────────────────────────────

    // Chemin absolu
    public static void putFile(String selector, String path, Page page) {
        page.setInputFiles(selector, Paths.get(path));
    }

    public static void putFile(String selector, String path, Frame iframe) {
        iframe.setInputFiles(selector, Paths.get(path));
    }

    // Chemin relatif depuis src/test/resources/
    public static void putFileFromResources(String selector, String fileName, Page page) {
        page.setInputFiles(selector, Paths.get(BASE_PATH + fileName));
    }

    public static void putFileFromResources(String selector, String fileName, Frame iframe) {
        iframe.setInputFiles(selector, Paths.get(BASE_PATH + fileName));
    }

    // ──────────────────────────────────────────
    // ✅ PLUSIEURS FICHIERS — liste de chemins
    // ──────────────────────────────────────────

    public static void putMultipleFiles(String selector, Page page, String... fileNames) {
        Path[] paths = Arrays.stream(fileNames)
            .map(f -> Paths.get(BASE_PATH + f))
            .toArray(Path[]::new);
        page.setInputFiles(selector, paths);
    }

    public static void putMultipleFiles(String selector, Frame iframe, String... fileNames) {
        Path[] paths = Arrays.stream(fileNames)
            .map(f -> Paths.get(BASE_PATH + f))
            .toArray(Path[]::new);
        iframe.setInputFiles(selector, paths);
    }

    // ──────────────────────────────────────────
    // ✅ PLUSIEURS FICHIERS — depuis une List
    // ──────────────────────────────────────────

    public static void putMultipleFiles(String selector, Page page, List<String> fileNames) {
        Path[] paths = fileNames.stream()
            .map(f -> Paths.get(BASE_PATH + f))
            .toArray(Path[]::new);
        page.setInputFiles(selector, paths);
    }

    public static void putMultipleFiles(String selector, Frame iframe, List<String> fileNames) {
        Path[] paths = fileNames.stream()
            .map(f -> Paths.get(BASE_PATH + f))
            .toArray(Path[]::new);
        iframe.setInputFiles(selector, paths);
    }
}