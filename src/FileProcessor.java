import java.io.*;
import java.nio.file.Files;

public class FileProcessor {
    private File file;

    /**
     * @param jobFile
     */
    public FileProcessor(File jobFile) {
        this.file = jobFile;
    }

    /**
     * @return referencesAsIntegers
     * @throws FileNotFoundException if file not found
     */
    public int[] convertJobFileToArray() throws IOException {
        int numberOfReferences = 31;
        int arrayPosition = 0;
        int[] referencesAsIntegers = new int[numberOfReferences];

        boolean nameFlag = true;

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

    public int[] createRandomReferences() {
        int numberOfReferences = 30;
        int arrayPosition = 0;
        int[] referencesAsIntegers = new int[numberOfReferences];

        return referencesAsIntegers;
    }


}
