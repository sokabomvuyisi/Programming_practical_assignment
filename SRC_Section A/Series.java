import java.util.ArrayList;
import java.util.Scanner;

public class Series {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<SeriesModel> seriesList = new ArrayList<>();

    // Capture a new TV series
    public static void captureTVSeries() {
        System.out.println("\n--- Capture Series ---");

        System.out.print("Enter the series ID: ");
        String seriesId = scanner.nextLine();

        System.out.print("Enter the series name: ");
        String seriesName = scanner.nextLine();

        int age;
        while (true) {
            try {
                System.out.print("Enter age restriction (2–18): ");
                age = Integer.parseInt(scanner.nextLine());
                if (age >= 2 && age <= 18) break;
                System.out.println("Invalid age. Please enter between 2 and 18.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        int episodes;
        while (true) {
            try {
                System.out.print("Enter number of episodes: ");
                episodes = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        seriesList.add(new SeriesModel(seriesId, seriesName, age, episodes));
        System.out.println("✅ Series captured successfully!");
    }

    // Search for a series by ID
    public static void searchSeries() {
        System.out.println("\n--- Search Series ---");
        System.out.print("Enter the series ID to search: ");
        String seriesId = scanner.nextLine();

        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(seriesId)) {
                System.out.println("Series Name: " + series.seriesName);
                System.out.println("Age Restriction: " + series.seriesAge);
                System.out.println("Number of Episodes: " + series.seriesNumberOfEpisode);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ No series found with ID: " + seriesId);
        }
    }

    // Update a series by ID
    public static void updateSeries() {
        System.out.println("\n--- Update Series ---");
        System.out.print("Enter the series ID to update: ");
        String seriesId = scanner.nextLine();

        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(seriesId)) {
                System.out.print("Enter new series name: ");
                series.seriesName = scanner.nextLine();

                System.out.print("Enter new age restriction: ");
                series.seriesAge = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter new number of episodes: ");
                series.seriesNumberOfEpisode = Integer.parseInt(scanner.nextLine());

                System.out.println("✅ Series updated successfully!");
                return;
            }
        }

        System.out.println("❌ Series not found.");
    }

    // Delete a series by ID
    public static void deleteSeries() {
        System.out.println("\n--- Delete Series ---");
        System.out.print("Enter the series ID to delete: ");
        String seriesId = scanner.nextLine();

        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(seriesId)) {
                System.out.print("Are you sure you want to delete this series? (Y/N): ");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    seriesList.remove(series);
                    System.out.println("✅ Series deleted successfully.");
                } else {
                    System.out.println("❌ Series not deleted.");
                }
                return;
            }
        }

        System.out.println("❌ Series not found.");
    }

    // Print series report
    public static void seriesReport() {
        System.out.println("\n--- Series Report ---");
        System.out.printf("%-15s %-30s %-20s %-25s%n", "Series ID", "Series Name", "Age Restriction", "Episodes");

        for (SeriesModel series : seriesList) {
            System.out.printf("%-15s %-30s %-20d %-25d%n",
                    series.seriesId, series.seriesName, series.seriesAge, series.seriesNumberOfEpisode);
        }
    }

    // Exit application
    public static void exitSeries() {
        System.out.println("👋 Exiting Series Application...");
        System.exit(0);
    }
}
