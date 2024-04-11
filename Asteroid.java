/*
CLASS: Asteroid
DESCRIPTION: Extending Polygon, represents the actual Asteroid object (targets)
*/

import java.awt.*;
public class Asteroid extends Polygon {
    private double xVelocity;
    private double yVelocity;
    private double scale = 0.1;

    public Asteroid(Point[] _vertices, Point _position, double _heading) {
        super(_vertices, _position, _heading);
        xVelocity = Math.random() * scale;
        yVelocity = Math.random() * scale;
    }

    public void paint(Graphics brush) {
        Point[] astPoints = this.getPoints();
        int numPoints = astPoints.length;
        int[] xPositions = new int[numPoints];
        int[] yPositions = new int[numPoints];
        for (int i = 0; i < numPoints; i++) {
            xPositions[i] = (int)astPoints[i].x;
            yPositions[i] = (int)astPoints[i].y;
        }
        brush.setColor(Color.WHITE);
        brush.drawPolygon(xPositions, yPositions, numPoints);
    }

    public boolean move() {
        return super.move(this.xVelocity * Math.cos(Math.toRadians(this.getHeading())), this.yVelocity * Math.sin(Math.toRadians(this.getHeading())));
    }
}
