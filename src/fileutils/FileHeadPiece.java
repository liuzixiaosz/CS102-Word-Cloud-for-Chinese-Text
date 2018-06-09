package fileutils;

import java.io.*;

public class FileHeadPiece {
    private static byte[] sTestLine;
    private static String sInitFilePath;
    private static String sTestFilePath;
    public static void setOutPath(String path) {
        sInitFilePath = path;
    }

    public static void extractTo(String file_path, String test_file_path) {
        sTestFilePath = test_file_path;
        sInitFilePath = file_path;
        readInitialBytes();
        outputTestLine();
    }

    private static void readInitialBytes() {
        int chars_read;

        byte[] buffer = new byte[100];
        try (
                BufferedInputStream input = new BufferedInputStream(new FileInputStream(sInitFilePath))
        ) {
            if ((chars_read = input.read(buffer, 0, buffer.length)) != -1) {
                sTestLine = java.util.Arrays.copyOfRange(buffer, 0, chars_read);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void outputTestLine() {
        File f = new File(sTestFilePath);
        try (FileOutputStream output = new FileOutputStream(f)) {
            output.write(sTestLine, 0, sTestLine.length);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
