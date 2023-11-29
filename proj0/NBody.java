public class NBody {
    public static String imageToDraw = "images/starfield.jpg";

    public static double readRadius(String fileName) {
        In in = new In(fileName);

        in.readInt();
		double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        int i = 0;
        Planet[] result = new Planet[5];

        In in = new In(fileName);
        int number = in.readInt();
        double radius = in.readDouble();

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
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        StdDraw.show();

        for (Planet p : planets) {
            p.draw();
        }
    }
}
