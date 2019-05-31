import sun.nio.ch.Net;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]){
        Network network = new Network(2,5,2,0,0);
        try {
            Scanner sc = new Scanner(new File("/Users/ugurcan/IdeaProjects/neuralnetwork/src/main/resources/points2d.dat"));
            List<List<Double>> points = new ArrayList<List<Double>>();
            List<Double> outputs = new ArrayList<Double>();
            while(sc.hasNextLine()){
                List<Double> point = new ArrayList<Double>();
                point.add(sc.nextDouble());
                point.add(sc.nextDouble());
                points.add(point);
                outputs.add(sc.nextDouble());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
