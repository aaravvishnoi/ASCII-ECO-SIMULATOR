**ASCII ECO SIMULATOR**
What problems does your application solve?
The Ecosystem Simulator is a text-based Java application that models a living, evolving ecosystems. The simulation runs autonomously acorss multiple generations. Each generation, creatures feed,compete, reproduce, and die - with their offspring inherting mutated stats. Over time, natural slection drives the popluation to evolve.
The simulaton is non-interactive by desing. The Driver class runs the ecosystem and prints detailed output to the console each generation, including ASCII art for each species, population statistics, and evolution logs. Results are stored to a file at the end.

Description of the structure of your program

Creature: It represents one individual animal. It has metadata for the creature such as stats,parent reference, age, health, ASCII art stringWhat problems does your application solve?

Species: It defines the species wide properties. It includes the following data, base stats, Species name, the food chain role it has, ASCII art template.

Population: It manages all creatures of one species for each species. It contains an ArrayList of type creature, it also contains methods like reproduces() which initiates reproduction between creatures, cull() which removes creatures with health less then 0 health, avgStats() initiates mutation on the stats inheritated by parent.

Ecosystem: It holds all the population for each species, each species has 10 creatures each. It has an ArrayList of type Population, runGeneration() method which calls the 5 phases 1.Plant Growth, 2. Feeding, 3. Survival Check, 4. Reproduction. 5. Stats and Logging.

FamilyTree: It does the recursive tree opertations. It has methods getDepth() returns the number of ancestors, getAncestors() returns an ArrayList of type Creature of all the ancestors of the calling object.

File Manager: It handles all the file input and output. It reads the config file which consists of values neccesary for running the Generation loop, writelog() to print the final values of the generation after each generation.

Simulation Driver: This is the entry point and demonstrates the app. It consists of the main() class, sets up the ecosystem and then runs the generations.

How to run

Ensure Java is installed
Navigate to the project root folder in terminal
Compile with javac src/*.java -cp lib/junit-4.13.2.jar
Run with java -cp src SimulationDriver
Config can be adjusted in data/Input/config.txt
Output log is written to data/Output/ecosystem_log.txt
