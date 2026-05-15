package ecosystem;

class SimulationDriver {
    public static void main(String[] args) throws Exception {
        FileManager fm = new FileManager();
        Config config = fm.readConfig();

        Species Plant = new Species("Plant", "Producer", 4, 0, config.getCarryCapacity());
        Plant.setAscii("      _.--'--._\n    .'. ':. ' :'.\n   '`' : `. `: ':'\n  : : '.'. .'.':.`:\n  :' -`= `+ .= - `:\n  :.' .'.' :.'.`:':\n   : : .:`' .:`.': \n    '.: . :`. :.' \n      `'--,--'`\n          y\n         ( \n          \\       _\n           \\    /`/\n     _      |__/:/_  \n     \\`\\    |\\ :  /\n     _\\::\\_  /\\ : /_\n     \\ : /_/\\  :  /\n     _\\ : /_/ : /`\n     \\  :  /.'/`\n jgs  `\\ :/'/``\n`^^`^`^``^^`^^^`^^`^^^`^");
        
        Species Worm = new Species("Worm", "Herbivore S", 5, 4, 20);
        Worm.setAscii("                                                        /~~\\\n          ____                                         /'o  |\n        .~  | `\\             ,-~~~\\~-_               ,'  _/'|\n        `\\_/   /'\\         /'`\\    \\  ~,             |     .'\n            `,/'  |      ,'_   |   |   |`\\          ,'~~\\  |\n             |   /`:     |  `\\ /~~~~\\ /   |        ,'    `.'  \n             | /'  |     |   ,'      `\\  /`|      /'\\    /\n             `|   / \\_ _/ `\\ |         |'   `----\\   |  /'\n              `./'  | ~ |   ,'         |    |     |  |/'\n               `\\   |   /  ,'           `\\ /      |/~'\n                 `\\/_ /~ _/               `~------'\n                     ~~~~\n");
        
        Species Rabbit = new Species("Rabbit", "Herbivore L", 8, 6, 15);
        Rabbit.setAscii("               /|      __\n             +      / |   ,-~ /             +\n     .              Y :|  //  /                .         *\n         .          | jj /( .^     *\n               *    >-\"~\"-v\"              .        *        .\n*                  /       Y\n   .     .        jo  o    |     .            +\n                 ( ~T~     j                     +     .\n      +           >._-' _./         +\n               /| ;-\"~ _  l\n  .           / l/ ,-\"~    \\     +\n              \\//\\/      .- \\\n       +       Y        /    Y\n               l       I     !\n               ]\\      _\\    /\"\\\n              (\" ~----( ~   Y.  )\n          ~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        
        Species Fox = new Species("Fox", "Predator S", 10, 7, 10);
        Fox.setAscii("   /|_/|\n  / ^ ^(_o\n /    __.' \n /     \\\\\n(_) (_) '._\n   '.__     '. .-''-'.\n     ( '.   ('.____.'' \n     _) )'_, )mrf\n    (__/ (__/\n");
        
        Species Wolf = new Species("Wolf", "Predator L", 12, 8, 8);
        Wolf.setAscii("        _\n       / \\      _-'\n     _/|  \\-''- _ /\n__-' { |          \\\n    /             \\\n    /       \"o.  |o }\n    |            \\ ;\n                  ',\n       \\_         __\\\n         ''-_    \\./\\/ \n           / '-____'\n          /\n        _'\n      _-'\n");
        
        Species Eagle = new Species("Eagle", "Apex", 15, 9, 5);
        Eagle.setAscii("       .   ,\n       '. '.  \\  \\\n      ._ '-.'. `\\  \\\n        '-._; .'; `-.''.  \n       `~-.; '.       '. \n        '--,`           '.\n           -='.          ;\n .--=~~=-,    -.;        ;\n .-=`;    `~,_.;        /\n `  ,-`'    .-;         |\n    .-~`.    .;         ;\n     .;.-   .-;         ,\\\n       `.'   ,=;     .-'  `~.-._\n        .';   .';  .'      .'   '-. \n          .\\  ;  ;        ,.' _  a',\n         .'~\";-`   ;      ;\"~` `'-=.)\n       .' .'   . _;  ;',  ;\n       '-.._ `~`.'  \\  ; ; :\n            `~'    _'\\_ \\_\n                  /=`^^=`\"\"/`)-.\n                  \\ =  _ =     =\\\n                   `\"\"` `~-. =   ; ");

        // Print ASCII art for all species
        System.out.println("=== PLANT ===");
        System.out.println(Plant.getASCII());
        System.out.println("\n=== WORM ===");
        System.out.println(Worm.getASCII());
        System.out.println("\n=== RABBIT ===");
        System.out.println(Rabbit.getASCII());
        System.out.println("\n=== FOX ===");
        System.out.println(Fox.getASCII());
        System.out.println("\n=== WOLF ===");
        System.out.println(Wolf.getASCII());
        System.out.println("\n=== EAGLE ===");
        System.out.println(Eagle.getASCII());
        System.out.println("\n=== STARTING SIMULATION ===\n");

        Worm.addPrey("Plant");
        Rabbit.addPrey("Plant");
        Fox.addPrey("Rabbit");
        Fox.addPrey("Worm");
        Wolf.addPrey("Fox");
        Wolf.addPrey("Rabbit");
        Eagle.addPrey("Wolf");
        Eagle.addPrey("Fox");
        Eagle.addPrey("Rabbit");

        Population plantPop = new Population(Plant, config.getMutationRate());
        Population wormPop = new Population(Worm, config.getMutationRate());
        Population rabbitPop = new Population(Rabbit, config.getMutationRate());
        Population foxPop = new Population(Fox, config.getMutationRate());
        Population wolfPop = new Population(Wolf, config.getMutationRate());
        Population eaglePop = new Population(Eagle, config.getMutationRate());

        for (int i = 0; i < config.getStartingPopulation(); i++) {
            plantPop.getAllCreatures().add(new Creature(0, 0, 0, 0, null, "Plant", 100, Plant));
            wormPop.getAllCreatures().add(new Creature(3, 2, 2, 3, null, "Worm", 100, Worm));
            rabbitPop.getAllCreatures().add(new Creature(6, 4, 4, 5, null, "Rabbit", 100, Rabbit));
            foxPop.getAllCreatures().add(new Creature(7, 6, 4, 6, null, "Fox", 100, Fox));
            wolfPop.getAllCreatures().add(new Creature(6, 8, 6, 7, null, "Wolf", 100, Wolf));
            eaglePop.getAllCreatures().add(new Creature(9, 10, 5, 10, null, "Eagle", 100, Eagle));
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
