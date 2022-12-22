package UI.utils;

import java.awt.*;

public class Helper {
    public static void centerWindow(Window frame) {
        Rectangle bounds = frame.getGraphicsConfiguration().getBounds();
        Dimension dimension = bounds.getSize();
        int x = (int) (((dimension.getWidth() - frame.getWidth()) / 2) + bounds.getMinX());
        int y = (int) (((dimension.getHeight() - frame.getHeight()) / 2) + bounds.getMinY());
        frame.setLocation(x, y);
    }
}
