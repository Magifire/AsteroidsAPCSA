/*
CLASS: Bullet
DESCRIPTION: Represents Bullet (weapon)
NOTE: move() in this class returns a boolean, note the BULLET_SPEED and MAX_DISTANCE
*/

import java.awt.*;
public class Bullet extends Circle{
    public static final double BULLET_SPEED = .6;
    public static final int MAX_DISTANCE = 2000;
    private int distanceTraveled = 0;

    public Bullet(Point _position, double _heading) {
        super(_position, _heading);
    }

    public void paint(Graphics brush) {
        brush.setColor(Color.white);
        super.paint(brush, 20, 20);
    }

    public boolean move() {
        if (distanceTraveled < MAX_DISTANCE) {
            distanceTraveled++;
            Point position = super.getPosition();
            position.x += (BULLET_SPEED * Math.cos(Math.toRadians(super.getHeading())));
            position.y += (BULLET_SPEED * Math.sin(Math.toRadians(super.getHeading())));
            return super.move();
        } else {
            return false;
        }
    }
}
