/*
CLASS: Star
DESCRIPTION: Represents Star (non-interactive background object)
NOTE: move() in this class returns a boolean, note the BULLET_SPEED and MAX_DISTANCE
*/

import java.awt.*;
public class Star extends Circle{

    public Star(Point _position, double _heading) {
        super(_position, _heading);
    }

    public void paint(Graphics brush) {
        super.paint(brush, 5, 5);
    }

    public boolean move() {
        double heading = getHeading();
        return super.move((0.01 * Math.cos(Math.toRadians(heading))), (0.01 * Math.sin(Math.toRadians(heading))));
    }
}
