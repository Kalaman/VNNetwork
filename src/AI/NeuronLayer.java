package AI;

public class NeuronLayer {
    Neuron [] neurons;

    public NeuronLayer(Neuron... neurons){
        this.neurons = neurons;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }
}
