import java.awt.Polygon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Task2 {

    public static void main(String[] args) {
        File file1 = new File(args[1]);
        File file2 = new File(args[2]);
        List<Point> polygon = new ArrayList<>();
        List<String> vertices = new ArrayList<>();
        List<String> points = new ArrayList<>();


        readFile(vertices, file1);
        readFile(points, file2);

        String[] coordinatesVerticeA = vertices.get(0).split(" ");
        String[] coordinatesVerticeB = vertices.get(1).split(" ");
        String[] coordinatesVerticeC = vertices.get(2).split(" ");
        String[] coordinatesVerticeD = vertices.get(3).split(" ");

        polygon.add(new Point(Float.parseFloat(coordinatesVerticeA[0]), Float.parseFloat(coordinatesVerticeA[1])));
        polygon.add(new Point(Float.parseFloat(coordinatesVerticeB[0]), Float.parseFloat(coordinatesVerticeB[1])));
        polygon.add(new Point(Float.parseFloat(coordinatesVerticeC[0]), Float.parseFloat(coordinatesVerticeC[1])));
        polygon.add(new Point(Float.parseFloat(coordinatesVerticeD[0]), Float.parseFloat(coordinatesVerticeD[1])));

        float[][] arrayPoints = new float[points.size()][2];
        for (int i = 0; i < points.size(); i++) {
            String[] coordinatesPoints = points.get(i).split(" ");
            arrayPoints[i][0] = Float.parseFloat(coordinatesPoints[0]);
            arrayPoints[i][1] = Float.parseFloat(coordinatesPoints[1]);

        }

        checkLocation(polygon, arrayPoints);
    }


    public static List readFile(List<String> list, File file) {

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                list.add(str);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return list;
    }

    public static void checkLocation(List<Point> polygon, float[][] arrayPoints) {

        for (int i = 0; i < arrayPoints.length; i++) {
            Point point = new Point(arrayPoints[i][0], arrayPoints[i][1]);

            if (checkVertices(polygon, point) == true) {    //проверка вершин
                System.out.println("0");

            } else if (checkRibs(polygon, point) == true) {  //проверка сторон
                System.out.println("1");

            } else if (checkIn(polygon, point) == true) {    //поверка внутри
                System.out.println("2");

            } else {
                System.out.println("3");
            }
        }

    }


    private static boolean checkVertices(List<Point> polygon, Point point) {
        boolean result = false;

        for (int i = 0; i < polygon.size(); i++) {
            if (polygon.get(i).x == point.x && polygon.get(i).y == point.y) {
                result = true;
            }
        }
        return result;
    }

    private static boolean checkRibs(List<Point> polygon, Point point) {
        boolean result = false;

        for (int i = 0; i < polygon.size(); i++) {
            int i1 = i + 1;
            if (i == 3) {
                i1 = 0;
            }
            if ((
                    //проверка ребра AB, CD
                    ((polygon.get(i).x == point.x) &&
                            (((Float.compare(polygon.get(i).y, point.y)) < 0 && (Float.compare(point.y, polygon.get(i1).y)) < 0) ||
                                    (Float.compare(polygon.get(i).y, point.y)) > 0 && (Float.compare(point.y, polygon.get(i1).y)) > 0)) ||

                            // проверка ребра BC, AD
                            ((polygon.get(i).y == point.y) &&
                                    (((Float.compare(polygon.get(i).x, point.x)) < 0 && (Float.compare(point.x, polygon.get(i1).x)) < 0) ||
                                            (Float.compare(polygon.get(i).x, point.x)) > 0 && (Float.compare(point.x, polygon.get(i1).x)) > 0)))) {
                result = true;
            }
        }

        return result;
    }

    private static boolean checkIn(List<Point> polygon, Point point) {

        int xPoints[] = new int [polygon.size()];
        int yPoints[] = new int [polygon.size()];

        for(int i = 0; i < polygon.size(); i++) {
            xPoints[i] = (int) (polygon.get(i).x*100);
            yPoints[i] = (int) (polygon.get(i).y*100);
        }

        Polygon polygon1 = new Polygon(xPoints, yPoints, polygon.size() );


        return polygon1.contains(point.x*100, point.y*100);
    }

}


class Point {
    public float x;
    public float y;

    Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
}



