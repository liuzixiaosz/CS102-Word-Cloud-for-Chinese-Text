import fileutils.FileCharsetDetector;
import fileutils.FileContent;
import wordcloud.StringFreqType;
import wordcloud.WordFreq;

import java.util.Queue;

public class Client {
    public static void main(String[] args) {
        String url = "testres/test5_ASCII.txt";
        String[] charset = FileCharsetDetector.getProbableCharset(url);
        System.out.println(charset[0]);
        FileContent fc = new FileContent();
        fc.readFile(url, charset[0]);
        System.out.println(fc.getContent());
        WordFreq fq = new WordFreq(fc.getContent());
        Queue<StringFreqType> wordFreq = fq.getStringFreqQueue();

        for (StringFreqType sfc : wordFreq) {
            System.out.println(sfc.str + " " + sfc.freq);
        }
//        FileHeadPiece.extractTo("testres/test5_ASCII.txt", "testres/test5_ASCII_head.txt");
    }
}
