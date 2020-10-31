package NBody.CelestialModel;
/*
    This class holds all the data and methods necessary for a single celestial body.  This class contains data members
    and methods that are used to determine the position of itself relative to other celestial bodies.
*/
public class Celestial {
    public static final double GC = 6.67E-11; //gravitational constant
    //array of fields that is used to ensure the correct data is obtained when instantiating this class
    public static final String[] FIELDS = {
            "name",
            "mass",
            "x",
            "y",
            "xVel",
            "yVel",
            "size"
    };

    private String name; //name of the celestial
    //data members for calculating position of the celestial body relative to other celestial bodies
    private double mass;
    private double x,y;
    private double xVel, yVel;
    private double size;
    private double netForceX, netForceY;
    private double accelX, accelY;
    private double scaleFactor;

    //constructor; takes in params that correspond to the data from the CSV file and sets the data members
    public Celestial(String name, double mass, double x, double y, double xVel, double yVel, double size, double scaleFactor) {
        this.name = name; this.mass = mass; this.x = x; this.y = y; this.xVel = xVel; this.yVel = yVel; this.size = size; this.scaleFactor = scaleFactor;
    }
    //toString() method to get a visual representation of important data members
    @Override
    public String toString() {
        return this.name+", "+this.mass+", "+this.x+", "+this.y+", "+this.xVel+", "+this.yVel+", "+this.size;
    }

    //GETTERS

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

    //PHYSICS CALCULATIONS

    /*
        * PARAMS: Container<Object> celestials - an instantiation of a class that implements the Container interface
                  that is defined in NBody.CelestialModel
        * DESCRIPTION: takes in an iterable data structure and calculates the net x and y forces acting on this object
        * RETURN: none
    */
    public void calculateNetForces(Container<Object> celestials) {
        this.netForceX = this.netForceY = 0; //initialize forces to 0
        //loop through all celestial bodies and update this object's net forces relative to the other celestial bodies
        for(int i = 0; i < celestials.size(); i++) {
            Celestial c = (Celestial) celestials.get(i);
            //this if statement is to ensure that this celestial is only being compared to OTHER celestials
            if(c != this) {
                //set this object's new net forces
                this.netForceX = this.netForceX + this.calculateForceX(c);
                this.netForceY = this.netForceY + this.calculateForceY(c);
            }
        }
    }
    /*
        * PARAMS: none
        * DESCRIPTION: makes some calculations to the data members in order to update this object's position relative to other celestials
        * RETURN: none
    */
    public void updateCelestial() {
        this.accelX = this.netForceX/this.mass;
        this.accelY = this.netForceY/this.mass;
        this.xVel = (this.xVel + this.accelX);
        this.yVel = (this.yVel + this.accelY);
        this.x = this.x + this.xVel;
        this.y = this.y + this.yVel;
    }

    //THE METHODS BELOW ARE PRIVATE AND ARE ONLY CALLED WITHIN A LOOP IN THE PUBLIC METHOD calculateNetForces()
    //THESE METHODS ARE FOR MAKING CALCULATIONS ONLY BETWEEN THIS OBJECT AND ANOTHER CELESTIAL

    /*
        * PARAMS: Celestial c - another celestial that this object is being compared to
        * DESCRIPTION: calculates the force in the x direction/axis that is acting on this object in comparison
                       to another celestial
        * RETURN: double - a numerical representation of the force acting on this object in the x direction/axis
    */
    private double calculateForceX(Celestial c) {
        double force = this.calculateForce(c);
        double distance = this.calculateDistance(c);
        return force*((c.getX()-this.x)/distance);
    }
    /*
        * PARAMS: Celestial c - another celestial that this object is being compared to
        * DESCRIPTION: calculates the force in the y direction/axis that is acting on this object in comparison
                       to another celestial
        * RETURN: double - a numerical representation of the force acting on this object in the y direction/axis
    */
    private double calculateForceY(Celestial c) {
        double force = this.calculateForce(c);
        double distance = this.calculateDistance(c);
        return force*((c.getY()-this.y)/distance);
    }
    /*
        * PARAMS: Celestial c - another celestial that this object is being compared to
        * DESCRIPTION: calculates the force in the z direction/axis that is acting on this object in comparison
                       to another celestial (by z direction/axis I mean the diagonal line between the two celestials).
                       This method is used within the calculateForceY() and calculateForceX()
                       because in order to calculate the x and y forces, this force must be solved for first.
        * RETURN: double - a numerical representation of the force acting on this object in the z direction/axis
    */
    private double calculateForce(Celestial c) {
        double distance = this.calculateDistance(c);
        return GC*(this.mass*c.getMass())/(distance*distance);
    }
    /*
        * PARAMS: Celestial c - another celestial that this object is being compared to
        * DESCRIPTION: calculates the distance between this object in relation to the celestial it is being compared to
                       by using the pythagorean theorem: d^2 = x^2 + y^2
        * RETURN: double - the distance between this object and another celestial
    */
    private double calculateDistance(Celestial c) {
        double x = c.getX();
        double y = c.getY();
        return Math.sqrt((this.x*x)+(this.y*y));
    }
}
