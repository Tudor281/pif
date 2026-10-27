package tudor.pif;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
    static void main() throws IOException, URISyntaxException {

        Path jarDir = Paths.get(
                App.class
                        .getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI()
        ).getParent();

        String script = Files.readString(jarDir.resolve("ai.txt"));
        String[] lines = script.split(System.lineSeparator());

        Map<String, List<String>> sections = new HashMap<>();

        String currentSection = null;
        for (String line : lines) {
//            System.out.println(line);
            if (line.startsWith("//") || line.isBlank()) continue;
            if (line.startsWith("# ")) {
                currentSection = line.replace("# ", "");
                continue;
            }
            sections.computeIfAbsent(currentSection, _ -> new ArrayList<>()).add(line);
        }

        random(sections, "Live4", true, 1, 2, 3, 4, 5, 6, 7);
        random(sections, "Pif", true, 1, 2, 3, 4, 5, 6, 7);
        random(sections, "Weight", true, 1, 2, 3, 4, 5, 6, 7);

        random(sections, "Work", true,1, 2, 3, 4, 5);
        random(sections, "Gym", true, 1, 2, 3, 4, 5);

        random(sections, "Porn", false, 1);

        random(sections, "Junk Food", false, 1, 2, 3, 4, 5);

        random(sections, "Cleaning", false, 4, 7);

        random(sections, "Alcohol", false, 5);


        random(sections, "Church", true,5, 6);
        random(sections, "Coffee", false, 6);

        random(sections, "Skydiving", true, 7);
        random(sections, "Family", true, 7);

        general(sections, 2);
    }

    static void random(Map<String, List<String>> sections, String section, boolean remove, int... days) {
        LocalDateTime now = LocalDateTime.now();

        boolean matchedDay = false;
        for (int day : days) {
            if (now.getDayOfWeek().getValue() == day) {
                matchedDay = true;
                break;
            }
        }

        if (matchedDay) {
            Random rand = new Random();
            System.out.println(sections.get(section).get(rand.nextInt(sections.get(section).size())) + " (" + section + ")");
        }

        sections.remove(section);
    }

    static void general(Map<String, List<String>> sections, int counts) {
        List<String> headers = new ArrayList<>(sections.keySet());
        Random rand = new Random();
        for (int i = 0; i < counts; i++) {
            int headerIndex = rand.nextInt(headers.size());
            System.out.println(sections.get(headers.get(headerIndex)).get(rand.nextInt(sections.get(headers.get(headerIndex)).size())) + " (General)");

            headers.remove(headerIndex);
        }
    }
}
