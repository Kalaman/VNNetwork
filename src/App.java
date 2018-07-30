import AI.Neuron;
import AI.NeuronLayer;
import UI.MainFrame;
import UI.MainPanel;

public class App {

    public static void main (String args []) {

        NeuronLayer outputLayer = new NeuronLayer(
                new Neuron()
        );

        NeuronLayer hiddenLayer3 = new NeuronLayer(
                new Neuron(outputLayer.getNeurons()),
                new Neuron(outputLayer.getNeurons()),
                new Neuron(outputLayer.getNeurons()),
                new Neuron(outputLayer.getNeurons())
        );

        NeuronLayer hiddenLayer2 = new NeuronLayer(
                new Neuron(hiddenLayer3.getNeurons()),
                new Neuron(hiddenLayer3.getNeurons()),
                new Neuron(hiddenLayer3.getNeurons()),
                new Neuron(hiddenLayer3.getNeurons())
        );

        NeuronLayer hiddenLayer1 = new NeuronLayer(
                new Neuron(hiddenLayer2.getNeurons()),
                new Neuron(hiddenLayer2.getNeurons()),
                new Neuron(hiddenLayer2.getNeurons()),
                new Neuron(hiddenLayer2.getNeurons()),
                new Neuron(hiddenLayer2.getNeurons()),
                new Neuron(hiddenLayer2.getNeurons())
        );

        NeuronLayer inputLayer = new NeuronLayer(
                new Neuron(hiddenLayer1.getNeurons(),1),
                new Neuron(hiddenLayer1.getNeurons(),0)
        );

        MainFrame mainFrame = new MainFrame("VNNetwork");
        MainPanel neurolPanel = new MainPanel(inputLayer,outputLayer,hiddenLayer1,hiddenLayer2,hiddenLayer3);

        mainFrame.add(neurolPanel);
        mainFrame.setVisible(true);



    }
}
