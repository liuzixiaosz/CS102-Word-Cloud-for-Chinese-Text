package app;

import app.controllers.WordCloudController;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import wordcount.StringFreqType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LabelCreator {
    private final static int MODIFIER = 9;
    private final static int MAXNUM = 234;
    public static final String[] MY_FONTS = {
            "Kaiti TC",
            "Weibei TC",

    };

    public static List<Label> getLabels(List<StringFreqType> wordFreqList, List<Circle> circles) {
        List<Label> labelList = new LinkedList<>();
        int terms = (wordFreqList.size() >= MAXNUM) ? MAXNUM : wordFreqList.size();
        int word_idx = 0;
        Collections.shuffle(circles);
        xml:
        for (int i = 0; i < circles.size(); i++) {
            // deal circles, get positions
            int seg = i / MODIFIER;
            Circle parent_c = circles.get(i);

            CircleArea circleArea = new CircleArea(parent_c.getCenterX(), parent_c.getCenterY(), parent_c.getRadius(), seg);
            List<ChildrenCircle> areas = circleArea.getChildAreas();
            for (int j = 0; j < areas.size(); j++) {
                String tmp = wordFreqList.get(word_idx).str;
                String s = tmp.substring(0, tmp.lastIndexOf("/"));
                Label l = new Label(s);
                int this_freq = wordFreqList.get(word_idx).freq;
                double size = Math.pow(this_freq, 0.75) * 30 / Math.pow(wordFreqList.get(0).freq, 0.75) + 0.5;

//                l.getBoundsInLocal();
                double offset_x = -size * s.length() / 2;
                double offset_y = -size / 2;
                l.setLayoutX(areas.get(j).x + offset_x);
                l.setLayoutY(areas.get(j).y + offset_y);
                Random r = new Random();
                l.setRotate(90 - r.nextInt(181));
                int rlbd = 0, rhbd = 256, glbd = 0, ghbd = 256, blbd = 0, bhbd = 256;
                if (WordCloudController.backGroundColor.getBlue()
                        + WordCloudController.backGroundColor.getRed()
                        + WordCloudController.backGroundColor.getGreen() < 1) {
                    blbd = 200;
                    glbd = 200;
                    rlbd = 200;
                    bhbd = 56;
                    ghbd = 56;
                    rhbd = 56;
                } else {
                    blbd = 0;
                    glbd = 0;
                    rlbd = 0;
                    bhbd = 80;
                    ghbd = 80;
                    rhbd = 80;
                }

                l.setTextFill(Color.rgb(rlbd + r.nextInt(rhbd), glbd + r.nextInt(ghbd), blbd + r.nextInt(bhbd)));
                l.setFont(new Font( getRandomFont(), size));
                labelList.add(l);
                word_idx++;
                if (word_idx >= terms) break xml;
            }
        }
        return labelList;
    }

    public static String getRandomFont() {

        return MY_FONTS[(new Random()).nextInt(MY_FONTS.length)];
    }
}
