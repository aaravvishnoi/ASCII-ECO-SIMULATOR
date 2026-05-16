**ASCII ECO SIMULATOR**
What problems does your application solve?
The Ecosystem Simulator is a text-based Java application that models a living, evolving ecosystem. The simulation runs autonomously across multiple generations. Each generation, creatures feed, compete, reproduce, and die, with their offspring inheriting mutated stats. Over time, natural selection drives the population to evolve.
The simulation is non-interactive by design. The Driver class runs the ecosystem and prints detailed output to the console each generation, including ASCII art for each species, population statistics, and evolution logs. Results are stored in a file at the end.

Description of the structure of your program

Creature: Represents one individual animal. It stores creature metadata such as stats, parent reference, age, health, and ASCII art.

Species: Defines species-wide properties. It includes base stats, species name, food chain role, and ASCII art template.

Population: Manages all creatures of one species. It contains an ArrayList of type Creature and methods such as reproduce(), which initiates reproduction between creatures, cull(), which removes creatures with health less than or equal to 0, and avgStats(), which updates statistics inherited from the parent.

Ecosystem: Holds all populations for each species. It has an ArrayList of type Population, and the runGeneration() method calls the five phases: 1. Plant Growth, 2. Feeding, 3. Survival Check, 4. Reproduction, and 5. Stats and Logging.

FamilyTree: Performs recursive tree operations. It has methods such as getDepth(), which returns the number of ancestors, and getAncestors(), which returns an ArrayList of type Creature containing all ancestors of the calling object.

File Manager: Handles all file input and output. It reads the config file, which contains the values necessary for running the generation loop, and writeLog(), which prints the final values for each generation.

Simulation Driver: This is the entry point and demonstrates the app. It contains the main() method, sets up the ecosystem, and then runs the generations.

How to run

Ensure Java is installed
Navigate to the project root folder in terminal
Compile with javac src/*.java -cp lib/junit-4.13.2.jar
Run with java -cp src SimulationDriver
Config can be adjusted in data/Input/config.txt
Output log is written to data/Output/ecosystem_log.txt
