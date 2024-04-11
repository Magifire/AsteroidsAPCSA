/*
CLASS: Polygon
DESCRIPTION: A polygon is a sequence of points in space defined by a set of
             such points, an offset, and a rotation. The offset is the
             distance between the origin and the center of the shape.
             The rotation is measured in degrees, 0-360.
USAGE: You are intended to instantiate this class with a set of points that
       forever defines its shape, and then modify it by repositioning and
       rotating that shape. In defining the shape, the relative positions
       of the points you provide are used, in other words: {(0,1),(1,1),(1,0)}
       is the same shape as {(9,10),(10,10),(10,9)}.
NOTE: You don't need to worry about the "magic math" details.
*/

public class Polygon implements Cloneable {
    private Point position; // The absolute point in space of the center of the polgon
    private double heading; // Can be thought of as the "rotation." 0 is right/east. Increase rotates clockwise
    private Point[] vertices; // An array of points that comprise the vertices of the polygon. Drawn relative to the "position"

    public Point getPosition() {
        return this.position;
    }
    public double getHeading() {
        return this.heading;
    }
    public void setHeading(double _newHeading) {
        this.heading = _newHeading;
    }

    public boolean collidesWith(Polygon other) {
        Point[] otherPoints = other.getPoints();
        for (Point p : otherPoints) {
            if (this.contains(p)) {
                return true;
            }
        }
        return false;
    }

    public Polygon(Point[] _vertices, Point _position, double _heading) {
        this.position = _position;
        this.heading = _heading;
        vertices = _vertices;

        // First, we find the shape's top-most left-most boundary, its origin.
        Point origin = new Point(vertices[0].x, vertices[0].y);
        for (Point p : vertices) {
            if (p.x < origin.x)
                origin.x = p.x;
            if (p.y < origin.y)
                origin.y = p.y;
        }

        // Then, we orient all of its points relative to the real origin.
        for (Point p : vertices) {
            p.x -= origin.x;
            p.y -= origin.y;
        }
    }

    // "getPoints" applies the rotation and offset to the shape of the polygon.
    public Point[] getPoints() {
        Point center = findCenter();
        Point[] points = new Point[vertices.length];
        int i = 0;
        for (Point p : vertices) {
            double x = ((p.x - center.x) * Math.cos(Math.toRadians(this.heading)))
                    - ((p.y - center.y) * Math.sin(Math.toRadians(this.heading)))
                    + center.x / 2 + this.position.x;
            double y = ((p.x - center.x) * Math.sin(Math.toRadians(this.heading)))
                    + ((p.y - center.y) * Math.cos(Math.toRadians(this.heading)))
                    + center.y / 2 + this.position.y;
            points[i] = new Point(x, y);
            i++;
        }
        return points;
    }

    // "contains" implements some magical math (i.e. the ray-casting algorithm).
    public boolean contains(Point point) {
        Point[] points = getPoints();
        double crossingNumber = 0;
        for (int i = 0, j = 1; i < vertices.length; i++, j = (j + 1) % vertices.length) {
            if ((((points[i].x < point.x) && (point.x <= points[j].x)) ||
                    ((points[j].x < point.x) && (point.x <= points[i].x))) &&
                    (point.y > points[i].y + (points[j].y - points[i].y) /
                            (points[j].x - points[i].x) * (point.x - points[i].x))) {
                crossingNumber++;
            }
        }
        return crossingNumber % 2 == 1;
    }

    public void rotate(int degrees) {
        this.heading = ((this.heading + degrees) % 360);
    }

    /*
     * The following methods are private access restricted because, as this access
     * level always implies, they are intended for use only as helpers of the
     * methods in this class that are not private. They can't be used anywhere else.
     */

    // "findArea" implements some more magic math.
    private double findArea() {
        double sum = 0;
        for (int i = 0, j = 1; i < vertices.length; i++, j = (j + 1) % vertices.length) {
            sum += vertices[i].x * vertices[j].y - vertices[j].x * vertices[i].y;
        }
        return Math.abs(sum / 2);
    }

    // "findCenter" implements another bit of math.
    private Point findCenter() {
        Point sum = new Point(0, 0);
        for (int i = 0, j = 1; i < vertices.length; i++, j = (j + 1) % vertices.length) {
            sum.x += (vertices[i].x + vertices[j].x)
                    * (vertices[i].x * vertices[j].y - vertices[j].x * vertices[i].y);
            sum.y += (vertices[i].y + vertices[j].y)
                    * (vertices[i].x * vertices[j].y - vertices[j].x * vertices[i].y);
        }
        double area = findArea();
        return new Point(Math.abs(sum.x / (6 * area)), Math.abs(sum.y / (6 * area)));
    }
}