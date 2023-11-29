public class Planet {

    public static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double m1Bym2 = this.mass * p.mass;
        double distance = this.calcDistance(p);
        return (m1Bym2 * G) / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        return force * dx / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        return force * dy / distance;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceByX = 0;

        for (Planet p : allPlanets) {
            if (this.equals(p))
                continue;
            netForceByX += this.calcForceExertedByX(p);
        }

        return netForceByX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceByY = 0;

        for (Planet p : allPlanets) {
            if (this.equals(p))
                continue;
            netForceByY += this.calcForceExertedByY(p);
        }

        return netForceByY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
        StdDraw.show();
    }
}