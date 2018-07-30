package AI;

public class Connection {
    Neuron neuron_a;
    Neuron neuron_b;

    double weight;

    public Connection (Neuron neuron_a,Neuron neuron_b, double weight) {
        this.neuron_a = neuron_a;
        this.neuron_b = neuron_b;
        this.weight = weight;
    }

    public Neuron getNeuron_a() {
        return neuron_a;
    }

    public Neuron getNeuron_b() {
        return neuron_b;
    }

    public double getWeight() {
        return weight;
    }
}
