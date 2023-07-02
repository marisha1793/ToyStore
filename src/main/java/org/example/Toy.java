package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Toy {
    public int id;
    public String nameToy;
    public double frequency;

    public Toy(int id, String nameToy, double frequency){
        this.id = id;
        this.nameToy = nameToy;
        this.frequency = frequency;
    }
    public int getId(){
        return id;
    }
    public String getNameToy(){
        return nameToy;
    }
    public double getFrequency(){
        return frequency;
    }
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(new File("toys.txt"));
        List ids = new ArrayList<>();
        List nameToys = new ArrayList<>();
        List frequencies = new ArrayList<>();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            ids.add(Integer.parseInt(tokens[0]));
            nameToys.add(tokens[1]);
            frequencies.add(Double.parseDouble(tokens[2]));
        }
        PriorityQueue toysQueue = new PriorityQueue(frequencies.size());
        Comparator.comparingDouble(Toy::getFrequency);
        for (int i = 0; i < frequencies.size(); i++){
            Toy toy = new Toy(ids.get(i), nameToys.get(i), frequencies.get(i));
            toysQueue.add(toy);
        }
        Queue queue = new LinkedList();
        queue.addAll(toysQueue);
        writeToFile("result.txt", queue);

    }

    public static void writeToFile(String filename, Queue queue) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < 10; i++) {
            Toy toy = queue.poll();
            bw.write(toy.getId() + "," + toy.getNameToy() + "," + toy.getFrequency() + "n");
        }
        bw.close();
}
}
