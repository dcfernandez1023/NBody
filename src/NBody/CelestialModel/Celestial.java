package NBody.CelestialModel;

import java.util.HashMap;

public class Celestial {
    public static final double GC = 6.67E-11;
    public static final String[] FIELDS = {
            "name",
            "mass",
            "x",
            "y",
            "xVel",
            "yVel",
            "size"
    };

    private String name;
    private double mass;
    private double x,y;
    private double xVel, yVel;
    private double size;
    private double netForceX, netForceY;
    private double accelX, accelY;

    public Celestial(String name, double mass, double x, double y, double xVel, double yVel, double size) {
        this.name = name; this.mass = mass; this.x = x; this.y = y; this.xVel = xVel; this.yVel = yVel; this.size = size;
    }
    @Override
    public String toString() {
        return this.name+", "+this.mass+", "+this.x+", "+this.y+", "+this.xVel+", "+this.yVel+", "+this.size;
    }
    //getters
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getMass() {
        return this.mass;
    }
    public double getSize() {
        return this.size;
    }
    //physics calculations
    public void calculateNetForces(Container<Object> celestials) {
        this.netForceX = this.netForceY = 0;
        for(int i = 0; i < celestials.size(); i++) {
            Celestial c = (Celestial) celestials.get(i);
            if(c != this) {
                this.netForceX = this.netForceX + this.calculateForceX(c);
                this.netForceY = this.netForceY + this.calculateForceY(c);
            }
        }
    }
    public void updateCelestial() {
        this.accelX = this.netForceX/this.mass;
        this.accelY = this.netForceY/this.mass;
        this.xVel = this.xVel + this.accelX;
        this.yVel = this.yVel + this.accelY;
        this.x = this.x + this.xVel;
        this.y = this.y + this.yVel;
    }

    private double calculateForceX(Celestial c) {
        double force = this.calculateForce(c);
        double distance = this.calculateDistance(c);
        return force*((c.getX()-this.x)/distance);
    }
    private double calculateForceY(Celestial c) {
        double force = this.calculateForce(c);
        double distance = this.calculateDistance(c);
        return force*((c.getY()-this.y)/distance);
    }
    private double calculateForce(Celestial c) {
        double distance = this.calculateDistance(c);
        return GC*(this.mass*c.getMass())/(distance*distance);
    }
    private double calculateDistance(Celestial c) {
        double x = c.getX();
        double y = c.getY();
        return Math.sqrt((this.x*x)+(this.y*y));
    }
}
