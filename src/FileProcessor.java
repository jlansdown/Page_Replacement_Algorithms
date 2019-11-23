import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;

public class FileProcessor {
    private File file;
    private int numberOfReferences;
    private int arrayPosition = 0;
    private boolean testRandomTrials = false;
    String numberOfFramesForRandomTrials = "";

    /**
     * @param jobFile
     */
    public FileProcessor(File jobFile) {
        this.file = jobFile;
        this.numberOfReferences = 31;
    }

    public FileProcessor(File jobFile, String numberOfFrames, boolean randomTrials) {
        this.file = jobFile;
        this.numberOfReferences = 31;
        testRandomTrials = true;
        this.numberOfFramesForRandomTrials = numberOfFrames;
    }

    /**
     * @return referencesAsIntegers
     * @throws FileNotFoundException if file not found
     */
    public int[] convertJobFileToArray() throws IOException {
        int[] referencesAsIntegers = new int[this.numberOfReferences];

        if (testRandomTrials)
            createRandomReferenceString(this.numberOfFramesForRandomTrials);

        try {
            byte[] references = Files.readAllBytes(this.file.toPath());
            for (int byt : references) {
                if (Character.isDigit((char) byt)) {
                    int referenceInt = Character.getNumericValue((char) byt);
                    referencesAsIntegers[arrayPosition] = referenceInt;
                    arrayPosition++;
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return referencesAsIntegers;
    }

    private void createRandomReferenceString(String numberOfFrames) throws IOException {
        String referenceString = "";
        Integer reference;
        ArrayList<String> fileContent = new ArrayList<>();

        fileContent.add("NumberOfPageFramevalue:");
        fileContent.add(numberOfFrames);
        fileContent.add("Reference String:");

        Random random = new Random();
        for (int i = 0; i < this.numberOfReferences - 1; i++) {
            reference = random.nextInt(8);
            referenceString += reference.toString();
        }

        fileContent.add(referenceString);

        try {
            FileWriter fileWriter = new FileWriter(this.file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (String content : fileContent) {
                printWriter.println(content);
            }
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
