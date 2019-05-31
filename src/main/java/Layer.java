import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Layer {
    double bias;
    List<Neuron> neurons = new ArrayList<Neuron>();
    String layerType;
    List<Double> outputs = new ArrayList<Double>();

    public Layer(double bias, String layerType, int neuronNumber){
        this.bias = bias;
        this.layerType = layerType;
        for(int i = 0; i < neuronNumber; i++){
            this.neurons.add(new Neuron(layerType));
        }
    }

    public void initializeWeights(int inputNumber){
        Random r = new Random();
        for(Neuron neuron : this.neurons){
            for(int i = 0; i < inputNumber; i++){
                neuron.weights.add(-0.5 + r.nextDouble());
            }
        }

    }

    public List<Double> feedForward(List<Double> inputs){
        List<Double> outputs = new ArrayList<Double>();
        for(Neuron neuron: this.neurons){
            outputs.add(neuron.calculateOutput(inputs));
        }
        return outputs;
    }
}
