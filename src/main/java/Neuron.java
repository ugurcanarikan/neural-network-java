import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class Neuron {
    double bias;
    String layerType;
    List<Double> weights = new ArrayList<Double>();
    List<Double> inputs = new ArrayList<Double>();
    double output;

    public Neuron(String layerType){
        this(0, layerType);
    }

    /**
     * Constructor of the neuron object
     * @param bias bias of the neuron
     * @param layerType layer type of the neuron, either hidden or output
     */
    public Neuron(double bias, String layerType){
        this.bias = bias;
        this.layerType = layerType;
    }

    public double calculateOutput(List<Double> inputs){
        this.inputs = inputs;
        if(this.layerType.equals("hidden")){
            this.output = squashSigmoid(this.calculateNet());
        }
        else{
            this.squashTanh(this.calculateNet());

        }
        return this.output;
    }

    public double squashSigmoid(double net){
        return this.sigmoid(net);
    }

    public double squashTanh(double net){
        return Math.tanh(net);
    }

    public double calculateNet(){
        double net = 0.0;
        for(int i = 0; i < this.inputs.size(); i++){
            net += this.inputs.get(i) * this.weights.get(i);
        }
        net += this.bias;
        return net;
    }

    public double calculateDelta(double desiredOutput){
        return -1 * (desiredOutput - this.output) * this.output * (1 - this.output);
    }

    public double calculateError(double desiredOutput){
        return (desiredOutput * this.output) * (desiredOutput * this.output) / 2;
    }

    private double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }
}
