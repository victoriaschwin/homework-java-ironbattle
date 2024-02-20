
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        int choiceMenu;
        Warrior newWarrior;
        Wizard newWizard;
        List<Warrior> warriors = new ArrayList<>();
        List<Wizard> wizards = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        do {
            displayMenu();
            choiceMenu = scanner.nextInt();

            switch (choiceMenu) {
                case 1:
                    newWarrior = createWarrior(scanner);
                    warriors.add(newWarrior);
                    break;
                case 2:
                    newWizard = createWizard(scanner);
                    wizards.add(newWizard);
                    break;
                case 3:
                    if(warriors.size() == 1 && wizards.size() == 1){
                        //Opuestos
                        battleOpposite(warriors.get(0),wizards.get(0));
                    }else if(warriors.size() == 2){
                        //Caso ambos warriors
                        battleWarriors(warriors.get(0), warriors.get(1));
                    }else if(wizards.size() == 2) {
                        //Caso ambos wizards
                        battleWizards(wizards.get(0), wizards.get(1));
                    }else{
                        throw new IllegalArgumentException("Please create two characters to start a battle.");
                    }

                case 4:

                    Generator generator = new Generator();

                    //Random class selection
                    List<String> person = new ArrayList<>();
                    person.add("Warrior");
                    person.add("Wizard");

                    int randomIndex1= random.nextInt(person.size());
                    int randomIndex2= random.nextInt(person.size());

                    //Characters
                    String character1 = person.get(randomIndex1);
                    String character2 = person.get(randomIndex2);

                    //Class declaration
                    if (character1.equals("Wizard")){
                        wizards.add(new Wizard( "Character1", generator.generatorWizard(), generator.generatorMana(), generator.generatorIntelligence() ));
                    } else {
                        warriors.add( new Warrior( "Character2", generator.generatorWarrior(), generator.generatorMana(), generator.generatorStrength()));
                    }

                    if (character2.equals("Wizard")){
                        wizards.add(new Wizard( "Character1", generator.generatorWizard(), generator.generatorMana(), generator.generatorIntelligence() ));
                    } else {
                        warriors.add(new Warrior( "Character2", generator.generatorWarrior(), generator.generatorMana(), generator.generatorStrength()));
                    }

                case 5:
                    warriors.clear();
                    wizards.clear();
                    System.out.println("Closing program.");
                    scanner.close();
            }
        }while(choiceMenu != 5);

    }

    private static void displayMenu() {
        System.out.println("Welcome to Battlefield. \nBefore starting a battle please create two characters.");
        System.out.println("\nMenu:");
        System.out.println("1. Create Warrior");
        System.out.println("2. Create Wizard");
        System.out.println("3. Start Battle");
        System.out.println("4. Create random battle");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static Warrior createWarrior(Scanner scanner){
        int hp;
        int stamina;
        int strength;
        System.out.println("Enter your warrior's name:");
        scanner.nextLine();
        String name = scanner.nextLine();

        do{
            System.out.println("Enter your warrior's hp (100-200):");
            hp = scanner.nextInt();
            if(hp < 100 || hp > 200){
                System.out.println("Sorry the number you enter is wrong. Please, enter a number between 100 and 200.");
            }
        }while(hp < 100 || hp > 200);
        System.out.println(hp);
        do{
            System.out.println("Enter your warrior's stamina (10-50):");
            stamina = scanner.nextInt();
            if(stamina < 10 || stamina > 50){
                System.out.println("Sorry the number you enter is wrong. Please, enter a number between 10 and 50.");
            }
        }while (stamina < 10 || stamina > 50);

        do {
            System.out.println("Enter your warrior's strength (1-10)");
            strength = scanner.nextInt();
            if(strength < 1 || strength > 10){
                System.out.println("Sorry the number you enter is wrong. Please, enter a number between 1 and 10.");
            }
        }while (strength < 1 || strength > 10);

        Warrior warrior = new Warrior(name, hp, stamina, strength);
        System.out.println("Congratulations! Your warrior "+warrior.getName()+" is created.");
        return warrior;
    }

    private static Wizard createWizard(Scanner scanner){
        int hp;
        int mana;
        int intelligence;

        System.out.println("Enter your wizard's name:");
        scanner.nextLine();
        String name = scanner.nextLine();

        do{
            System.out.println("Enter your wizards's hp (50-100):");
            hp = scanner.nextInt();
            if(hp < 50 || hp > 100){
                System.out.println("Sorry the number you enter is wrong. Please, enter a number between 50 and 100.");
            }
        }while(hp < 50 || hp > 100);

        do{
            System.out.println("Enter your wizard's mana (10-50):");
            mana = scanner.nextInt();
            if(mana < 10 || mana > 50){
                System.out.println("Sorry the number you enter is wrong. Please, enter a number between 10 and 50.");
            }
        }while (mana < 10 || mana > 50);

        do {
            System.out.println("Enter your wizard's intelligence (1-50)");
            intelligence = scanner.nextInt();
            if(intelligence < 1 || intelligence > 50){
                System.out.println("Sorry the number you enter is wrong. Please, enter a number between 1 and 50.");
            }
        }while (intelligence < 1 || intelligence > 50);

        Wizard wizard = new Wizard(name, hp, mana, intelligence);
        System.out.println("Congratulations! Your wizard "+wizard.getName()+" is created.");
        return wizard;
    }

    private static void battleOpposite(Warrior warrior, Wizard wizard){
        String wizardName = wizard.getName();
        String warriorName = warrior.getName();
        int i = 1;
        int wizardHp;
        int warriorHp;
        int damageWizard;
        int damageWarrior;
        String winner;

        System.out.println(wizardName+" and "+warriorName+" get ready, the battle is going to start.");

        do{
            System.out.println("Round "+i);
            wizardHp = wizard.getHp();
            warriorHp = warrior.getHp();
            wizard.Attack(warrior);
            warrior.Attack(wizard);

            damageWizard = wizardHp - wizard.getHp();
            damageWarrior = warriorHp - warrior.getHp();

            System.out.println(wizard+" attacks and receives "+damageWizard+" damage.");
            System.out.println(warrior+" attacks and receives "+damageWarrior+" damage.");

            i++;}while(wizard.checkIsAlive() && warrior.checkIsAlive());

        if(!wizard.getIsAlive() && !wizard.getIsAlive()){
            System.out.println("Oh no! Is a tie. Try a new battle and see who wins it all!");
        }else{
            if(wizard.getIsAlive()){
                winner = wizardName;
            }else{
                winner = warriorName;
            }
            System.out.println("Congratulations, "+winner+" You won!");
        }

    }

    private static void battleWarriors(Warrior warriorOne, Warrior warriorTwo){
        String warriorOneName = warriorOne.getName();
        String warriorTwoName = warriorTwo.getName();
        int i = 1;
        int warriorOneHp;
        int warriorTwoHp;
        int damageWarriorOne;
        int damageWarriorTwo;
        String winner;

        System.out.println(warriorOneName+" and "+warriorTwoName+" get ready, the battle is going to start.");

        do{
            System.out.println("Round "+i);
            warriorOneHp = warriorOne.getHp();
            warriorTwoHp = warriorTwo.getHp();
            warriorOne.Attack(warriorTwo);
            warriorTwo.Attack(warriorOne);

            damageWarriorOne = warriorOneHp - warriorOne.getHp();
            damageWarriorTwo = warriorTwoHp - warriorTwo.getHp();

            System.out.println(warriorOneName+" attacks and receives "+damageWarriorOne+" damage.");
            System.out.println(warriorTwoName+" attacks and receives "+damageWarriorTwo+" damage.");
            warriorOne.checkIsAlive();
            warriorTwo.checkIsAlive();
            i++;
        }while(warriorOne.getIsAlive() && warriorTwo.getIsAlive());

        if(!warriorOne.getIsAlive() && !warriorOne.getIsAlive()){
            System.out.println("Oh no! Is a tie. Try a new battle and see who wins it all!");
        }else{
            if(warriorOne.getIsAlive()){
                winner = warriorOneName;
            }else{
                winner = warriorTwoName;
            }
            System.out.println("Congratulations, "+winner+" You won!");
        }

    }

    private static void battleWizards(Wizard wizardOne, Wizard wizardTwo){
        String wizardOneName = wizardOne.getName();
        String wizardTwoName = wizardTwo.getName();
        int i = 1;
        int wizardOneHp;
        int wizardTwoHp;
        int damageWizardOne;
        int damageWizardTwo;
        String winner;

        System.out.println(wizardOneName+" and "+wizardTwoName+" get ready, the battle is going to start.");

        do{
            System.out.println("Round "+i);
            wizardOneHp = wizardOne.getHp();
            wizardTwoHp = wizardTwo.getHp();
            wizardOne.Attack(wizardTwo);
            wizardTwo.Attack(wizardOne);

            damageWizardOne = wizardOneHp - wizardOne.getHp();
            damageWizardTwo = wizardTwoHp - wizardTwo.getHp();

            System.out.println(wizardOne+" attacks and receives "+damageWizardOne+" damage.");
            System.out.println(wizardTwo+" attacks and receives "+damageWizardTwo+" damage.");

            i++;}while(wizardOne.checkIsAlive() && wizardTwo.checkIsAlive());

        if(!wizardOne.getIsAlive() && !wizardOne.getIsAlive()){
            System.out.println("Oh no! Is a tie. Try a new battle and see who wins it all!");
        }else{
            if(wizardOne.getIsAlive()){
                winner = wizardOneName;
            }else{
                winner = wizardTwoName;
            }
            System.out.println("Congratulations, "+winner+" You won!");
        }
    }
}
