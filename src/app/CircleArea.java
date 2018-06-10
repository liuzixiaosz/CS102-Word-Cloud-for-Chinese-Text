package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

        /* add children */

        /* x, y*/
        for (int i = 0; i < term_sign; i++) {
            double cx = x + 2 * i * child_radius + offset;
            childAreas.add(new ChildrenCircle(cx, y, child_radius));
        }
        for (int i = 0; i < term_sign; i++) {
            if (i != term_sign / 2) {
                /* vertical */
                double tmp = 2 * i * child_radius + offset;
                double cy = y + tmp;
                childAreas.add(new ChildrenCircle(x, cy, child_radius));
                /* 45 degrees branch*/
                if (i != 0 && i != term_sign - 1) {
                    childAreas.add(new ChildrenCircle(x - tmp, cy, child_radius));
                    childAreas.add(new ChildrenCircle(x + tmp, cy, child_radius));
                }
            }
        }

        /* rotate */
        Random r = new Random();
        double deg = Math.PI / 4 * r.nextDouble();

        for (ChildrenCircle c : childAreas) {
            if (c.x - x == 0 && c.y - y == 0) continue;
            if (c.x - x == 0) {
                c.y = (c.y - y) * Math.cos(deg) + y;
                c.x = (c.y - y) * Math.sin(deg) + x;
            } else if (c.y - y == 0) {
                c.y = -(c.x - x) * Math.sin(deg) + y;
                c.x = (c.x - x) * Math.cos(deg) + x;
            } else {
                double this_radius = Math.sqrt(2) * Math.abs(c.x - x);

                if (c.y - y > 0) {

                    if (c.x - x < 0) {
                        c.x = x - this_radius * Math.cos(deg + Math.PI / 4);
                        c.y = y + this_radius * Math.sin(deg + Math.PI / 4);
                    } else {
                        c.x = x + this_radius * Math.cos(Math.PI / 4 - deg);
                        c.y = y + this_radius * Math.sin(Math.PI / 4 - deg);
                    }
                } else {
                    if (c.x - x < 0) {
                        c.x = x - this_radius * Math.cos(Math.PI / 4 - deg);
                        c.y = y - this_radius * Math.sin(Math.PI / 4 - deg);
                    } else {
                        c.x = x + this_radius * Math.cos(deg + Math.PI / 4);
                        c.y = y - this_radius * Math.sin(deg + Math.PI / 4);
                    }
                }
            }
        }

        Collections.shuffle(childAreas);

    }

    public List<ChildrenCircle> getChildAreas() {
        return childAreas;
    }


}