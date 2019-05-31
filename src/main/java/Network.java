import java.util.ArrayList;
import java.util.List;

public class Network {
    double alpha = 0.1;

    Layer hiddenLayer;
    Layer outputLayer;

    public Network(int inputNeuronNumber, int hiddenNeuronNumber, int outputNeuronNumber, double hiddenBias, double outputBias){
        this.hiddenLayer = new Layer(hiddenBias, "hidden", hiddenNeuronNumber);
        this.hiddenLayer.initializeWeights(inputNeuronNumber);
        this.outputLayer = new Layer(outputBias, "output", outputNeuronNumber);
        this.outputLayer.initializeWeights(hiddenNeuronNumber);
    }

    public List<Double> feedForward(List<Double> inputs){
        return this.outputLayer.feedForward(this.hiddenLayer.feedForward(inputs));
    }

    public void backpropagate(List<Double> inputs, List<Integer> desiredOutputs){
        this.feedForward(inputs);
        List<Double> outputDeltas = new ArrayList<Double>();
        for(int i = 0; i < this.outputLayer.neurons.size(); i++){
            outputDeltas.add(this.outputLayer.neurons.get(i).calculateDelta(desiredOutputs.get(i)));
        }
        List<Double> hiddenDeltas = new ArrayList<Double>();
        for(int i = 0; i < this.hiddenLayer.neurons.size(); i++){
            double sum = 0.0;
            for(int j = 0; j < this.outputLayer.neurons.size(); j++){
                sum += outputDeltas.get(j) * this.outputLayer.neurons.get(j).weights.get(i);
            }
            hiddenDeltas.add((1 - this.hiddenLayer.neurons.get(i).output * this.hiddenLayer.neurons.get(i).output) * sum);
        }
        for(int i = 0; i < this.outputLayer.neurons.size(); i++){
            for(int j = 0; j < this.outputLayer.neurons.get(i).weights.get(j); j++){
                this.outputLayer.neurons.get(i).weights.set(j,this.outputLayer.neurons.get(i).weights.get(j) - this.alpha * outputDeltas.get(i) * this.outputLayer.neurons.get(i).inputs.get(j));
            }
        }
        for(int i = 0; i < this.hiddenLayer.neurons.size(); i++){
            for(int j = 0; j < this.hiddenLayer.neurons.get(i).weights.get(j); j++){
                this.hiddenLayer.neurons.get(i).weights.set(j,this.hiddenLayer.neurons.get(i).weights.get(j) - this.alpha * hiddenDeltas.get(i) * this.hiddenLayer.neurons.get(i).inputs.get(j));
            }
        }
    }

    public void train(List<List<Double>> inputs, List<List<Integer>> outputs){
        for(int i = 0; i < inputs.size(); i++){
            this.backpropagate(inputs.get(i), outputs.get(i));
        }
    }

    public double calculateError(List<Integer> desiredOutputs){
        double totalError = 0.0;
        for(int i = 0; i < this.outputLayer.neurons.size(); i++){
            totalError += this.outputLayer.neurons.get(i).calculateError(desiredOutputs.get(i));
        }
        return totalError / this.outputLayer.neurons.size();
    }
}
