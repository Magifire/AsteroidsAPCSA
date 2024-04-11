import java.awt.Graphics;

public class Circle {
    private Point position;
    private double heading;

    public Circle(Point _position, double _heading) {
        this.position = _position;
        this.heading = _heading;
    }
    public Point getPosition(){
        return this.position;
    }
    public double getHeading(){
        return this.heading;
    }
    public void setPosition(Point p){
        this.position = p;
    }
    public void setHeading(double h){
        this.heading = h;
    }
    public boolean move() {
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
    public void paint(Graphics brush, int w, int h){
        brush.fillOval((int)this.position.x, (int)this.position.y, w, h);
    }
}
