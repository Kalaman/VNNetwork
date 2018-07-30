package UI;

import AI.Connection;
import AI.Neuron;
import AI.NeuronLayer;
import AI.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;


public class MainPanel extends JPanel{
    NeuronLayer inputLayer;
    NeuronLayer outputLayer;
    NeuronLayer [] hiddenLayer;

    int neuronSize = 40;
    int neuronXGap = 200;
    int neuronYGap = 100;
    int inputLayerX = 200;

    JButton forwardProp;


    public MainPanel(NeuronLayer inputLayer, NeuronLayer outputLayer, NeuronLayer... hiddenLayer) {
        super();
        this.inputLayer = inputLayer;
        this.outputLayer = outputLayer;
        this.hiddenLayer = hiddenLayer;

        int highestNeuroCount = inputLayer.getNeurons().length;
        if (highestNeuroCount < outputLayer.getNeurons().length)
            highestNeuroCount = outputLayer.getNeurons().length;

        for (int i=0;i<hiddenLayer.length;++i){
            if (hiddenLayer[i].getNeurons().length > highestNeuroCount)
                highestNeuroCount = hiddenLayer[i].getNeurons().length;
        }
        forwardProp = new JButton("Propagate");
        forwardProp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forwardPropagation();
            }
        });
        add(forwardProp);

        calculateNeuronPositions(highestNeuroCount);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i=0;i<inputLayer.getNeurons().length;++i){
            drawNeuron(inputLayer.getNeurons()[i],(Graphics2D)g);
            drawConnection(inputLayer.getNeurons()[i],(Graphics2D)g);
        }

        for (NeuronLayer currentLayer : hiddenLayer){
            for (int i=0;i<currentLayer.getNeurons().length;++i){
                drawNeuron(currentLayer.getNeurons()[i],(Graphics2D)g);
                drawConnection(currentLayer.getNeurons()[i],(Graphics2D)g);
            }
        }

        for (int i=0;i<outputLayer.getNeurons().length;++i){
            drawNeuron(outputLayer.getNeurons()[i],(Graphics2D)g);
        }
    }

    public void drawNeuron(Neuron neuron,Graphics2D graphics) {
        int x = neuron.getX();
        int y = neuron.getY();

        graphics.fillOval(x,y,neuronSize,neuronSize);
        graphics.drawString( String.format( Locale.ENGLISH,"%.2f", neuron.getActivatedValue() ),x + 9,y + neuronSize + 13);
    }

    public void drawConnection(Neuron neuron,Graphics2D graphics) {
        int x = neuron.getX();
        int y = neuron.getY();

        for (Connection currentConnection : neuron.getConnections()){
            graphics.drawLine(x + (neuronSize / 2),y + (neuronSize / 2),currentConnection.getNeuron_b().x + (neuronSize / 2),currentConnection.getNeuron_b().y + (neuronSize / 2));
        }
    }

    public void calculateNeuronPositions (int highestNeuroCount) {

        for (int i=0;i<inputLayer.getNeurons().length;++i){
            Neuron currentNeuron = inputLayer.getNeurons()[i];
            currentNeuron.x = inputLayerX;
            currentNeuron.y = (i+1) * neuronYGap + ((highestNeuroCount * 100 - inputLayer.getNeurons().length * 100) / 2);
        }

        for (int i=0;i<hiddenLayer.length;++i) {
            for (int j=0;j<hiddenLayer[i].getNeurons().length;++j){
                Neuron currentNeuron = hiddenLayer[i].getNeurons()[j];
                currentNeuron.x = inputLayerX + (neuronXGap * (i+1));
                currentNeuron.y = (j+1) * neuronYGap + ((highestNeuroCount * 100 - hiddenLayer[i].getNeurons().length * 100) / 2);
            }
        }

        for (int i=0;i<outputLayer.getNeurons().length;++i){
            Neuron currentNeuron = outputLayer.getNeurons()[i];
            currentNeuron.x = inputLayerX + ((hiddenLayer.length + 1) * neuronXGap);
            currentNeuron.y = (i+1) * neuronYGap + ((highestNeuroCount * 100 - outputLayer.getNeurons().length * 100) / 2);
        }
    }

    public void forwardPropagation () {
        for (Neuron currentNeuron : inputLayer.getNeurons()){
            for (Connection connection : currentNeuron.getConnections()) {
                Neuron neuronB = connection.getNeuron_b();
                neuronB.setValue(neuronB.getValue() + currentNeuron.getActivatedValue() * connection.getWeight());
            }
        }

        for (NeuronLayer neuronLayer : hiddenLayer) {
            for (Neuron currentNeuron : neuronLayer.getNeurons()) {
                currentNeuron.activateValue();

                for (Connection connection : currentNeuron.getConnections()) {
                    Neuron neuronB = connection.getNeuron_b();
                    neuronB.setValue(neuronB.getValue() + currentNeuron.getActivatedValue() * connection.getWeight());
                }
            }
        }

        for (Neuron currentNeuron : outputLayer.getNeurons()){
            currentNeuron.activateValue();
        }

        repaint();
        backwardPropagation();
    }

    public void backwardPropagation () {
        double predicted_value = outputLayer.getNeurons()[0].getActivatedValue();

        double o_error = 1 - predicted_value;
        double o_delta = o_error * Utilities.sigmoidPrime(predicted_value);

        System.out.println("Error: " + o_error + "    Delta: "+ o_delta);

    }
}
