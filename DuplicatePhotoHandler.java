import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DuplicatePhotoHandler {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the folder containing duplicate photos:");
        String folderPath = scanner.nextLine();

        // Create a File object from the user-specified folder path
        File originalFolder = new File(folderPath);

        // Check if the specified path exists and if it's a directory
        if (!originalFolder.exists() || !originalFolder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        // Create a new folder named "DuplicatePhotos" in the parent directory of the original folder
        File duplicateFolder = new File(originalFolder.getParent(), "DuplicatePhotos");
        duplicateFolder.mkdir();

        // Create a HashMap to store file checksums to identify duplicates
        HashMap<String, String> fileChecksums = new HashMap<>();

        // Use AtomicInteger to track the number of processed files
        AtomicInteger processedFileCount = new AtomicInteger(0);

        // List all files in the directory using Java NIO
        try {
            System.out.println("Processing files...");
            Files.walk(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            // Calculate the checksum of the current file
                            String checksum = calculateChecksum(filePath.toString());

                            // Check if the checksum is already in the map (indicating a duplicate)
                            if (fileChecksums.containsValue(checksum)) {
                                // Create a File object for the duplicate photo in the "DuplicatePhotos" folder
                                File duplicateFile = new File(duplicateFolder, filePath.getFileName().toString());
                                try {
                                    // Move the duplicate photo to the "DuplicatePhotos" folder
                                    Files.move(filePath, duplicateFile.toPath());
                                    System.out.println("Duplicate photo found and moved: " + filePath);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                // Add the checksum to the map (indicating a unique photo)
                                fileChecksums.put(filePath.toString(), checksum);
                            }

                            // Increment the processed file count
                            int count = processedFileCount.incrementAndGet();
                            // Print progress every 100 files to improve responsiveness
                            if (count % 100 == 0) {
                                System.out.println("Processed " + count + " files...");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if the "DuplicatePhotos" folder is empty
        File[] duplicateFiles = duplicateFolder.listFiles();
        if (duplicateFiles == null || duplicateFiles.length == 0) {
            System.out.println("No duplicates found.");
            // If no duplicates were found, delete the "DuplicatePhotos" folder
            duplicateFolder.delete();
        } else {
            // Print the path to the "DuplicatePhotos" folder
            System.out.println("Duplicate photos have been moved to: " + duplicateFolder.getAbsolutePath());
        }
    }

    // Helper method to calculate a simple checksum of a file
    private static String calculateChecksum(String filePath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        int checksum = 0;

        for (byte b : fileBytes) {
            checksum += b; // Add up the byte values
        }

        return String.valueOf(checksum);
    }
}
