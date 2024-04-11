/*
CLASS: Ship
DESCRIPTION: Extending Polygon, represents a Ship (player) that is controlled by the keyboarde
NOTE: Implements ActionListener to use a timer to represent "drag" (deceleration)
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
public class Ship extends Polygon implements ActionListener {
    Timer timer = new Timer(300, this);
    private double velocity = 0;
    private double customAccel = 5;
    private double friction = 1;

    public Ship(Point[] _vertices, Point _position, double _heading) {
        
        super(_vertices, _position, _heading);
        timer.start();
    }

    public void paint(Graphics brush) {
        // Get the rotation-adjusted array of points from the superclass
        Point[] shipPoints = this.getPoints();
        int numPoints = shipPoints.length;
        int[] xPositions = new int[numPoints];
        int[] yPositions = new int[numPoints];
        for (int i = 0; i < numPoints; i++) {
            xPositions[i] = (int)shipPoints[i].x;
            yPositions[i] = (int)shipPoints[i].y;
        }

        // Draw the ship on the canvas
        brush.setColor(Color.GREEN);
        brush.drawPolygon(xPositions, yPositions, numPoints);
    }

    public void accelerate() {
        if (this.velocity < 1) {
            this.velocity += 0.0001 * this.customAccel;
        }
    }

    public void move() {
        this.getPosition().x += (this.velocity * Math.cos(Math.toRadians(this.getHeading())));
        this.getPosition().y += (this.velocity * Math.sin(Math.toRadians(this.getHeading())));
        if (this.getPosition().x > 800) {
            this.getPosition().x = 0;
        }
        if (this.getPosition().x < 0) {
            this.getPosition().x = 800;
        }
        if (this.getPosition().y > 600) {
            this.getPosition().y = 0;
        }
        if (this.getPosition().y < 0) {
            this.getPosition().y = 600;
        }
    }

    public void rotateLeft() {
        this.setHeading(this.getHeading() - 0.25);
    }

    public void rotateRight() {
        this.setHeading(this.getHeading() + 0.25);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            if (this.velocity > 0.01) {
                this.velocity -= 0.01 * friction;
            } else {
                this.velocity = 0;
            }
        }
    }
}
