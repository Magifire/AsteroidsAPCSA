public abstract class Shape {
    private Point position;
    private double heading;
    public Shape(Point _position, double _heading) {
        // Write the constructor
        position = _position;
        heading = _heading;
    }
    public Point getPosition() {
        return position;
    }
    public double getHeading() {
        return heading;
    }
    public void setHeading(double newHeading) {
        this.heading = newHeading;
    }
    // This version is used when a Shape has
    // the same x and y velocity (note that it calls
    // the overloaded version)
    public boolean move(double velocity) {
        move(velocity, velocity);
        return true;
    }
    public boolean move(double xVelocity, double yVelocity) {
        position.x += xVelocity;
        position.y += yVelocity;

        if (this.position.x > 800) {
            this.position.x = 0;
        }
        if (this.position.x < 0) {
            this.position.x = 800;
        }
        if (this.position.y > 600) {
            this.position.y = 0;
        }
        if (this.position.y < 0) {
            this.position.y = 600;
        }
        return true;
    }
}
