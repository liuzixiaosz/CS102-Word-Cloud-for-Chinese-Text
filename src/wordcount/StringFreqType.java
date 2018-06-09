package wordcount;

public class StringFreqType {
    public String str;
    public Integer freq;

    public StringFreqType(String str, Integer freq) {
        this.str = str;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return str + ": " + freq;
    }

}