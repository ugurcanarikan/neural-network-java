import sun.nio.ch.Net;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]){
        Network network = new Network(2,5,2,0,0);
        try {
            Scanner sc = new Scanner(new File("/Users/ugurcan/IdeaProjects/neuralnetwork/src/main/resources/points2d.dat"));
            List<List<Double>> points = new ArrayList<List<Double>>();
            List<List<Integer>> outputs = new ArrayList<List<Integer>>();
            while(sc.hasNextLine()){
                List<Double> point = new ArrayList<Double>();
                point.add(sc.nextDouble());
                point.add(sc.nextDouble());
                points.add(point);
                List<Integer> output = new ArrayList<Integer>();
                int category = sc.nextInt();
                output.add(category / 2);
                output.add(category % 2);
                outputs.add(output);
            }
            System.out.println(outputs);
            List<Double> errors = new ArrayList<Double>();
            for(int i = 0; i < 1; i++){
                network.train(points, outputs);
                for(int j = 0; j < points.size(); j++){
                    network.feedForward(points.get(j));
                    errors.add(network.calculateError(outputs.get(j)));
                }
            }
            //System.out.println(errors);
        }

        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
