package src.main.java.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create a scanner to read from standard input.
        Scanner sc = new Scanner(System.in);

        // Create a simulation.  The src.main.java.main.BankSimulation
        // constructor will read the simulation parameters
        // and initial events using the scanner.
        Simulation simulation = new BankSimulation(sc);

        // Create a new simulator and run the simulation
        new Simulator(simulation).run();

        // Clean up the scanner.
        sc.close();
    }
}
