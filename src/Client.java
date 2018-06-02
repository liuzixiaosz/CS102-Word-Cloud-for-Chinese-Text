import fileutils.FileCharsetDetector;
import fileutils.FileContent;
import wordcloud.StringFreqType;
import wordcloud.WordFreq;

import java.util.Iterator;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        String url = "testres/test5_ASCII.txt";
        String[] charset = FileCharsetDetector.getProbableCharset(url);
        System.out.println(charset[0]);
        FileContent fc = new FileContent();
        fc.readFile(url, charset[0]);
        System.out.println(fc.getContent());
        WordFreq fq = new WordFreq(fc.getContent());


        List<StringFreqType> wordFreqList = fq.getStringFreqList();
        Iterator<StringFreqType> iter = wordFreqList.iterator();
        while (iter.hasNext()) {
            StringFreqType sft = iter.next();
            System.out.println(sft.str + " " + sft.freq);
        }
    }
}
