package fileutils;

import java.io.*;

public class FileHeadPiece {
    private static String sTestLine;
    private static String sInitFilePath;
    private static String sTestFilePath;
    public static void setOutPath(String path) {
        sInitFilePath = path;
    }

    public static void extractTo(String file, String test_file_path) {
        sTestFilePath = test_file_path;
        sInitFilePath = file;
        readInitialBytes();
        outputTestLine();
    }

    private static void readInitialBytes() {
        InputStreamReader isr;
        int chars_read;

        char[] buffer = new char[200];
        try (
                FileInputStream input = new FileInputStream(sInitFilePath)
        ) {
            isr = new InputStreamReader(input);
            if ((chars_read = isr.read(buffer, 0, buffer.length)) != -1) {
                sTestLine = new String(java.util.Arrays.copyOfRange(buffer, 0, chars_read));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void outputTestLine() {
        File f = new File(sTestFilePath);
        byte buffer[] = sTestLine.getBytes();
        try (FileOutputStream output = new FileOutputStream(f)) {
            output.write(buffer, 0, buffer.length);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
