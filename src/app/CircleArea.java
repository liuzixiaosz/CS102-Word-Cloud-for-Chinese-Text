package app;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Area {

    public int width, height;
    private List<Area> childAreas;
    private List<String> textsList;

    public Area(String... texts) {
        if (texts.length != 0) {
            childAreas = new ArrayList<>(texts.length + 1);
            textsList = new ArrayList<>(texts.length + 1);
            for (String t : texts) textsList.add(t);
        }
    }

    public void initChildAreas() {
        if (childAreas != null) {
            Label label = new Label();
            label.setText(textsList.get(0));
            label.setTextFill(Paint.valueOf());
        }
    }

    
}
