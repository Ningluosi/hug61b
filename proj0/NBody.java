public class NBody {
    private static String imageToDraw = "images/starfield.jpg";

    public static double readRadius(String fileName) {
        In in = new In(fileName);

        in.readInt();
		double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        int i = 0;

        In in = new In(fileName);
        int number = in.readInt();
        double radius = in.readDouble();
        Planet[] result = new Planet[number];

        while (i < number) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String planet = in.readString();
            result[i++] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, planet);
        }

        return result;
    }

    public static void main(String[] args) {
        double t = 0;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        while (t <= T) {
            double[] xNetForce = new double[planets.length];
            double[] yNetForce = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                xNetForce[i] = planets[i].calcNetForceExertedByX(planets);
                yNetForce[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xNetForce[i], yNetForce[i]);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            
            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
