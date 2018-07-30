package AI;

public class Neuron {
    public int x,y;
    private double value;
    private double activatedValue;
    public Connection [] connections;

    public Neuron() {
        this.activatedValue = 0;
        connections = new Connection[0];
    }

    public Neuron(Neuron [] connection_neurons, double [] connection_neurons_weight) {
        this.activatedValue = 0;
        connections = new Connection[connection_neurons.length];

        for (int i=0;i<connections.length;++i)
            connections[i] = new Connection(this,connection_neurons[i],connection_neurons_weight[i]);
    }

    public Neuron(Neuron [] connection_neurons,int activation) {
        this(connection_neurons);
        this.activatedValue = activation;
    }

    public Neuron(Neuron [] connection_neurons) {
        this.activatedValue = Math.random();
        connections = new Connection[connection_neurons.length];

        for (int i=0;i<connections.length;++i){
            connections[i] = new Connection(this,connection_neurons[i],Math.random());
            System.out.println(connections[i].getWeight());
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getActivatedValue() {
        return activatedValue;
    }

    public Connection[] getConnections() {
        return connections;
    }

    public void setXY (int x,int y){
        this.x = x;
        this.y = y;
    }

    public double activateValue () {
        activatedValue = Utilities.sigmoid(value);
        value = 0;
        return activatedValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
