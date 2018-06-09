package app;

import java.util.ArrayList;
import java.util.List;

public class CircleArea {
//
    public double x, y, radius;
    private List<ChildrenCircle> childAreas;

    public CircleArea(double x, double y, double radius, int seg_degree) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        initChildren(seg_degree);
    }

    public void initChildren(int seg_degree) {
        double child_radius = radius / (2 * seg_degree + 1);
        int children_cnt = 4 * seg_degree + 1;
        childAreas = new ArrayList<>(children_cnt);
        int term_sign = (children_cnt) / 2 + 1;
        double offset = -2 * seg_degree * child_radius;
        for (int i = 0; i < term_sign; i++) {
            double cx = x + 2 * i * child_radius + offset;
            childAreas.add(new ChildrenCircle(cx, y, child_radius));
        }
        for (int i = 0; i < term_sign; i++) {
            if (i != term_sign / 2) {
                double cy = y + 2 * i * child_radius + offset;
                childAreas.add(new ChildrenCircle(x, cy, child_radius));
            }
        }
    }

    public List<ChildrenCircle> getChildAreas() {
        return childAreas;
    }



}