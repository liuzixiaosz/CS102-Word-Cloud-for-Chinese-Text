package wordcloud;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.*;


public class WordFreq {
    private static String sPuncString = "[`~!@#$%^&*()-+_=|{}':;',\\[].<>/?~！@#￥「」《》·……&*（）——|{}【】‘；：”“'。，、？]";
    private static Set<Character> sPuncSet;
    private static final double EXPECTED_MODIFIER = 1.74;
    private static final int WORDS_MAX = 56008;
    private static final int OFFSET = 20;
    private String mCharSet;
    private int mMapSizeRef;
    private String mContent;
    private List<Term> mWords;
    private Map<String, Integer> mStringMap;
    private Queue<StringFreqType> mSortedWordQueue;

    private void parseWords() {
        mWords = HanLP.segment(mContent);
    }

    private void calFreq() {
        parseWords();
        String word;
        for (Term t_word : this.mWords) {
            word = t_word.toString();
            char c = word.charAt(0);

            /**
             * isLetter只能判断是否是有意义的字符，不能判断是否是中文。
             * isChinese最好能实现
             *
             */
//            if (!(!isPunc(c)
//                    && !Character.isSpaceChar(c)
//                    && !Character.isWhitespace(c)
//                    )) {
//                continue;
//            }
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                continue;
            }
            if (this.mStringMap.containsKey(word)) {
                Integer f = mStringMap.get(word);
                this.mStringMap.replace(word, ++f);
            } else {
                this.mStringMap.put(word, 1);
            }
        }
        mSortedWordQueue = new PriorityQueue<>(mStringMap.size(),
                (o1, o2) -> o2.freq.compareTo(o1.freq));
        Iterator iter = mStringMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry) iter.next();
            mSortedWordQueue.add(new StringFreqType(entry.getKey(), entry.getValue()));
        }
    }

    public String getContent() { return this.mContent; }
    public void setContent(String content) {
        this.mContent = content;

        /**
         * According to 《现代汉语常用词表》
         * Available
         * https://wenku.baidu.com/view/2bc33b0a581b6bd97f19ea28.html
         *
         *
         * ---
         * 4.3
         * _
         *
         * 本表共收录常用词语56 008个，包括单音节词3 181个，
         * 双音节词语40 351个，三音节词语6 459个，
         * 四音节词语5 855个，五音节和五音节以上词语162个。
         * 表内条目按频级升序排列，频级相同的按汉语拼音音序排列。
         * ---
         *
         * 设输入长度为x,
         * 已知:
         * 单字词频率约 0.45, 双字0.42, 三字0.0198, 四字0.0082，五字以上0.0015, 其他未收录
         * 根据频率，可得每个词期望长度1.39，将期望长度与频率赋予未收录，可得未收录词长 1.39, 频率0.1
         * c = x / 1.39 即为最大不重复词语的条目
         * Modifier = 1.39
         *
         * 考虑50个最常用词语
         id	词语	出现次数	频率（%）	累积频率（%）
         1	的	744863	7.7946	7.7946
         2	了	130191	1.3624	9.157
         3	在	118823	1.2434	10.4004
         4	是	118527	1.2403	11.6407
         5	和	83958	0.8786	12.5193
         6	一	81119	0.8489	13.3682
         7	这	65146	0.6817	14.0499
         8	有	53556	0.5604	14.6103
         9	他	52912	0.5537	15.164
         10	我	52728	0.5518	15.7158
         11	也	47908	0.5013	16.2171
         12	不	46965	0.4915	16.7086
         13	就	44947	0.4703	17.1789
         14	地	42332	0.443	17.6219
         15	着	41116	0.4303	18.0522
         16	中	40849	0.4275	18.4797
         17	上	38084	0.3985	18.8782
         18	说	35429	0.3707	19.2489
         19	都	34323	0.3592	19.6081
         20	人	33991	0.3557	19.9638
         ------------------------------
                                19.9638 = 0.2
         *
         * c = x / 1.39 * (1 - 0.2) + 20
         * 则 Modifier = 1.39 * 1 / (1 - 0.2) = 1.74
         * HashMap 初始长度为c
         *
         */

        int tmp_size = (int) (content.length() / EXPECTED_MODIFIER);
        this.mMapSizeRef = (tmp_size >= WORDS_MAX) ? WORDS_MAX : tmp_size;
        this.mStringMap = new HashMap(content.length() / mMapSizeRef + OFFSET);

    }

    public Queue<StringFreqType> getStringFreqQueue() {
        if (this.mSortedWordQueue == null) { calFreq(); }
        return this.mSortedWordQueue;
    }

    public void setCharset(String charset) {
        this.mCharSet = charset;
    }

    public WordFreq(String content) {
        this.setContent(content);

    }

    //判断是否是标点，不需要了
    public boolean isPunc(char c) {
        if (sPuncSet == null) {
            sPuncSet = new HashSet<>(sPuncString.length());
            for (int i = 0; i < sPuncString.length(); i++) {
                sPuncSet.add(sPuncString.charAt(i));
            }
        }
        return sPuncSet.contains(c);
    }
}
