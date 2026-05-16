package ecosystem;

class SimulationDriver {
    public static void main(String[] args) throws Exception {
        FileManager fm = new FileManager();
        Config config = fm.readConfig();

        Species plantSpecies = new Species("Plant", "Producer", 4, 0, config.getCarryCapacity());
        plantSpecies.setAsciiArt("      _.--'--._\n    .'. ':. ' :'.\n   '`' : `. `: ':'\n  : : '.'. .'.':.`:\n  :' -`= `+ .= - `:\n  :.' .'.' :.'.`:':\n   : : .:`' .:`.': \n    '.: . :`. :.' \n      `'--,--'`\n          y\n         ( \n          \\       _\n           \\    /`/\n     _      |__/:/_  \n     \\`\\    |\\ :  /\n     _\\::\\_  /\\ : /_\n     \\ : /_/\\  :  /\n     _\\ : /_/ : /`\n     \\  :  /.'/`\n jgs  `\\ :/'/``\n`^^`^`^``^^`^^^`^^`^^^`^");
        
        Species wormSpecies = new Species("Worm", "Herbivore S", 5, 4, 20);
        wormSpecies.setAsciiArt("                                                        /~~\\\n          ____                                         /'o  |\n        .~  | `\\             ,-~~~\\~-_               ,'  _/'|\n        `\\_/   /'\\         /'`\\    \\  ~,             |     .'\n            `,/'  |      ,'_   |   |   |`\\          ,'~~\\  |\n             |   /`:     |  `\\ /~~~~\\ /   |        ,'    `.'  \n             | /'  |     |   ,'      `\\  /`|      /'\\    /\n             `|   / \\_ _/ `\\ |         |'   `----\\   |  /'\n              `./'  | ~ |   ,'         |    |     |  |/'\n               `\\   |   /  ,'           `\\ /      |/~'\n                 `\\/_ /~ _/               `~------'\n                     ~~~~\n");
        
        Species rabbitSpecies = new Species("Rabbit", "Herbivore L", 8, 6, 15);
        rabbitSpecies.setAsciiArt("               /|      __\n             +      / |   ,-~ /             +\n     .              Y :|  //  /                .         *\n         .          | jj /( .^     *\n               *    >-\"~\"-v\"              .        *        .\n*                  /       Y\n   .     .        jo  o    |     .            +\n                 ( ~T~     j                     +     .\n      +           >._-' _./         +\n               /| ;-\"~ _  l\n  .           / l/ ,-\"~    \\     +\n              \\//\\/      .- \\\n       +       Y        /    Y\n               l       I     !\n               ]\\      _\\    /\"\\\n              (\" ~----( ~   Y.  )\n          ~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        
        Species foxSpecies = new Species("Fox", "Predator S", 10, 7, 10);
        foxSpecies.setAsciiArt("   /|_/|\n  / ^ ^(_o\n /    __.' \n /     \\\\\n(_) (_) '._\n   '.__     '. .-''-'.\n     ( '.   ('.____.'' \n     _) )'_, )mrf\n    (__/ (__/\n");
        
        Species wolfSpecies = new Species("Wolf", "Predator L", 12, 8, 8);
        wolfSpecies.setAsciiArt("        _\n       / \\      _-'\n     _/|  \\-''- _ /\n__-' { |          \\\n    /             \\\n    /       \"o.  |o }\n    |            \\ ;\n                  ',\n       \\_         __\\\n         ''-_    \\./\\/ \n           / '-____'\n          /\n        _'\n      _-'\n");
        
        Species eagleSpecies = new Species("Eagle", "Apex", 15, 9, 5);
        eagleSpecies.setAsciiArt("       .   ,\n       '. '.  \\  \\\n      ._ '-.'. `\\  \\\n        '-._; .'; `-.''.  \n       `~-.; '.       '. \n        '--,`           '.\n           -='.          ;\n .--=~~=-,    -.;        ;\n .-=`;    `~,_.;        /\n `  ,-`'    .-;         |\n    .-~`.    .;         ;\n     .;.-   .-;         ,\\\n       `.'   ,=;     .-'  `~.-._\n        .';   .';  .'      .'   '-. \n          .\\  ;  ;        ,.' _  a',\n         .'~\";-`   ;      ;\"~` `'-=.)\n       .' .'   . _;  ;',  ;\n       '-.._ `~`.'  \\  ; ; :\n            `~'    _'\\_ \\_\n                  /=`^^=`\"\"/`)-.\n                  \\ =  _ =     =\\\n                   `\"\"` `~-. =   ; ");

        // Print ASCII art for all species
        System.out.println("=== PLANT ===");
        System.out.println(plantSpecies.getAsciiArt());
        System.out.println("\n=== WORM ===");
        System.out.println(wormSpecies.getAsciiArt());
        System.out.println("\n=== RABBIT ===");
        System.out.println(rabbitSpecies.getAsciiArt());
        System.out.println("\n=== FOX ===");
        System.out.println(foxSpecies.getAsciiArt());
        System.out.println("\n=== WOLF ===");
        System.out.println(wolfSpecies.getAsciiArt());
        System.out.println("\n=== EAGLE ===");
        System.out.println(eagleSpecies.getAsciiArt());
        System.out.println("\n=== STARTING SIMULATION ===\n");

        wormSpecies.addPrey("Plant");
        rabbitSpecies.addPrey("Plant");
        foxSpecies.addPrey("Rabbit");
        foxSpecies.addPrey("Worm");
        wolfSpecies.addPrey("Fox");
        wolfSpecies.addPrey("Rabbit");
        eagleSpecies.addPrey("Wolf");
        eagleSpecies.addPrey("Fox");
        eagleSpecies.addPrey("Rabbit");

        Population plantPop = new Population(plantSpecies, config.getMutationRate());
        Population wormPop = new Population(wormSpecies, config.getMutationRate());
        Population rabbitPop = new Population(rabbitSpecies, config.getMutationRate());
        Population foxPop = new Population(foxSpecies, config.getMutationRate());
        Population wolfPop = new Population(wolfSpecies, config.getMutationRate());
        Population eaglePop = new Population(eagleSpecies, config.getMutationRate());

        for (int i = 0; i < config.getStartingPopulation(); i++) {
            plantPop.getAllCreatures().add(new Creature(0, 0, 0, 0, null, "Plant", 100, plantSpecies));
            wormPop.getAllCreatures().add(new Creature(3, 2, 2, 3, null, "Worm", 100, wormSpecies));
            rabbitPop.getAllCreatures().add(new Creature(6, 4, 4, 5, null, "Rabbit", 100, rabbitSpecies));
            foxPop.getAllCreatures().add(new Creature(7, 6, 4, 6, null, "Fox", 100, foxSpecies));
            wolfPop.getAllCreatures().add(new Creature(6, 8, 6, 7, null, "Wolf", 100, wolfSpecies));
            eaglePop.getAllCreatures().add(new Creature(9, 10, 5, 10, null, "Eagle", 100, eagleSpecies));
        }

        Ecosystem eco = new Ecosystem(config.getPlantGrowthRate());
        eco.addPopulation(plantPop);
        eco.addPopulation(wormPop);
        eco.addPopulation(rabbitPop);
        eco.addPopulation(foxPop);
        eco.addPopulation(wolfPop);
        eco.addPopulation(eaglePop);

        for (int i = 0; i < config.getGenerationCount(); i++) {
            eco.runGeneration(fm, i);
        }
        
    }

}
