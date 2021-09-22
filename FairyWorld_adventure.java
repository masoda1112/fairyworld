// ここから開発しましょう。
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.stream.*;

class RandomWrapper{
    public static double getRanDouble(double min, double max){
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public static boolean ranBoolean(){
        return new Random().nextBoolean();
    }
}

class Name{
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString(){
        return this.firstName + " " + this.lastName;
    }
}

class BMI{
    private double heightM;
    private double weightKg;

    public BMI(double heightM, double weightKg){
        this.heightM = heightM;
        this.weightKg = weightKg;
    }

    public double getWeightKg(){
        return this.weightKg;
    }

    public double getValue(){
        return this.weightKg/(this.heightM*this.heightM);
    }

    public String toString(){
        return this.heightM + " meters, " + this.weightKg + "kg, BMI:" + this.getValue();
    }
}

class Animal{
    protected String species;
    protected BMI bmi;
    protected double lifeSpanDays;
    protected String biologicalSex;
    protected Date spawnTime;
    protected Date deathTime;
    protected int hungerPercent = 100;
    protected int sleepPercent = 100;

    public Animal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex){
        this.species = species;
        this.bmi = new BMI(heightM, weightKg);
        this.lifeSpanDays = lifeSpanDays;
        this.biologicalSex = biologicalSex;
        this.spawnTime = new java.util.Date();
    }

    public void eat(){
        if(!this.isAlive()) return;
        this.hungerPercent = 0;
    }

    public void setAsHungry(){
        if(!this.isAlive()) return;
        this.hungerPercent = 100;
    }

    public boolean isHungry(){
        return this.hungerPercent >= 70;
    }

    public void sleep(){
        if(!this.isAlive()) return;
        this.sleepPercent = 0;
    }

    public void setAsSleepy(){
        if(!this.isAlive()) return;
        this.sleepPercent = 100;
    }

    public boolean isSleepy(){
        return this.sleepPercent >= 70;
    }

    public void die(){
        this.sleepPercent = 0;
        this.hungerPercent = 0;
        this.deathTime = new java.util.Date();
    }

    public boolean isAlive(){
        return this.deathTime == null;
    }

    public String toString(){
        return this.species + " " + this.bmi + " lives " + this.lifeSpanDays + " days/" + "gender:" + this.biologicalSex + "." + this.status();
    }

    public String status(){
        return this.species + " status:" + " Hunger - " + this.hungerPercent + "%, " + "sleepiness:"+this.sleepPercent + "%" + ", Alive - " + this.isAlive() + ". First created at " + this.dateCreated();
    }

    public String dateCreated(){
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(this.spawnTime);
    }
}

class Mammal extends Animal{
    private double bodyTemperatureC;
    private double avgBodyTemperatureC;

    public Mammal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double avgBodyTemperatureC){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);

        this.avgBodyTemperatureC = avgBodyTemperatureC;
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }

    public void eat(){
        super.eat();
        System.out.println("this " + this.species + " is eating with its single lower jaw");
    }

    public String toString(){
        return super.toString() + " " + this.mammalInformation();
    }

    public void increaseBodyHeat(double celcius){
        this.bodyTemperatureC+=celcius;
    }

    public void decreaseBodyHeat(double celcius){
        this.bodyTemperatureC-=celcius;
    }

    public void adjustBodyHeat(){
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }

    public String mammalInformation(){
        return "This is a mammal with a temperature of: "+this.bodyTemperatureC;
    }
}

class Bird extends Animal{
    public Bird(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);
    }
    public String Fry(){
        return "I'm Flying!!!!";
    }
}

class Person extends Mammal{
    public static final String SPECIES = "Human";
    public static final double LIFE_EXPECTANCY = 30000;
    public static final double BODY_TEMPERATURE = 36;

    private Name name;
    private int age;

    public Person(String firstName, String lastName, int age, double heightM, double weightKg, String biologicalSex){
        super(Person.SPECIES, heightM, weightKg, Person.LIFE_EXPECTANCY, biologicalSex, Person.BODY_TEMPERATURE);
        this.name = new Name(firstName, lastName);
        this.age = age;
    }

    public String getName(){
        return this.name.toString();
    }

    public String toString(){
        return super.toString() + ". The name of this Person is " + this.getName();
    }
}

interface PlayfulPet{
    abstract public String play();
    abstract public String playWithPerson(Person person);
    abstract public String playNoise();
    abstract public String getPetName();
    abstract public double getRentalCosts();
    abstract public boolean likesActivity(String activity);
    abstract public boolean dislikesActivity(String activity);
    abstract public String doActivity(String activity);
}

class Cat extends Mammal implements PlayfulPet{
    public static final String SPECIES = "Cat";
    public static final double LIFE_EXPECTANCY = 5500;
    public static final double BODY_TEMPERATURE = 37;

    private static final double PLAYFUL_HOURLY_COSTS = 50;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","groom","drink","crawl","explore","pet"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Cat(double heightM, double weightKg, String biologicalSex){
        super(Cat.SPECIES, heightM, weightKg, Cat.LIFE_EXPECTANCY, biologicalSex, Cat.BODY_TEMPERATURE);
    }

    public String toString(){
        return super.toString() + " this is a cat";
    }

    public void meow(){
        System.out.println("Meooow");
    }

    public String getPetName(){
        return this.species;
    }

    public String play(){
        return "This cat starts rolling on the floor, and pretends to play predator";
    }

    public String playWithPerson(Person person){
        String s = "The cat stares at " + person.getName();
        s+= ". After taking kin to " + person.getName() + ", " + person.getName() + " throws a mouse toy at this cat and the cat starts chasing the mouse toy";
        return s;
    }

    public String playNoise(){
        return "Meow";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The cat enjoyed eating food.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The cat took a good nap.";
        }
        else if(this.likesActivity(activity)) return "Meow. The cat really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "The cat really hated the " + activity + " activity.";
        return "The cat felt indiferent about the " + activity + " activity.";
    };
}

abstract class PlayfulPetAssistant{
    protected static final double DEFAULT_RENT_TIME = 1.0;
    protected static final String DEFAULT_TOUR = "all-rounder pack";

    protected Person currentPerson;
    protected double currentRentTime = PlayfulPetAssistant.DEFAULT_RENT_TIME;
    protected static String[] availableActivities = {"eat","walk","drink","nap","pet","run","explore"};
    protected static String[] availableTours = {"all-rounder pack", "deluxe rounder pack"};
    protected String petName = "Animal";

    public String[] getActivities(){
        return this.availableActivities;
    }

    public String[] getAvailableTours(){
        return this.availableTours;
    }

    public boolean isValidTour(String tour){
        return Arrays.asList(this.getAvailableTours()).contains(tour);
    }

    protected String getRandomActivity(){
        List<String> activities = Arrays.asList(this.getActivities());
        int ran = new Random().nextInt(activities.size());
        return activities.get(ran);
    }

    public void setPerson(Person person){
        this.currentPerson = person;
    }

    public void setHours(double hours){
        this.currentRentTime = hours;
    }

    public double getCurrentRentTime(){
        return this.currentRentTime;
    }

    public void reset(){
        this.currentPerson = null;
        this.currentRentTime = this.DEFAULT_RENT_TIME;
    }

    public double runAssistanceTour(Person person){
        return this.runAssistanceTour(person, this.DEFAULT_TOUR);
    }

    public double runAssistanceTour(Person person, int amount){
        return this.runAssistanceTour(person, amount, this.DEFAULT_TOUR);
    }

    public double runAssistanceTour(Person person, String tour){
        if(!this.isValidTour(tour)) System.out.println("The tour guide does not accept the " + tour + " tour.");
        // ここを動的配列に対応させる
        ArrayList<PlayfulPet>playfulPets = this.createPlayfulPets(1);

        System.out.println("");
        System.out.println("Booting up... Playful Pet Assistance robot at your service.");
        System.out.println("Printing information about the Person to service..." + person);
        System.out.println("");

        // ここを動的配列に対応させる。（繰り返し処理）
        // System.out.println("Printing information about the Playful Pet - " + playfulPet.getPetName() + " to service..." + playfulPet);
        for(int i = 0; i < playfulPets.size(); i++){
            System.out.println("Printing information about the Playful Pet - " + playfulPets.get(i).getPetName() + " to service..." + playfulPets.get(i));
            if(tour == "all-rounder pack" || tour == "deluxe rounder pack"){
                int count = tour == "all-rounder pack" ? 1 : 3;
                this.genericRounderTour(count, person, playfulPets.get(i));
            }else{
                System.out.println("The tour assistant robot for " + playfulPets.get(i).getPetName() + " and " + person.getName() + " did nothing.");
            }
        }


        //playfulPet.getRentalCosts() を繰り返し処理して合計値を算出
        double totalCosts = playfulPets.get(0).getRentalCosts() * playfulPets.size();
        // double rentalCosts = playfulPet.getRentalCosts() * this.getCurrentRentTime();
        double rentalCosts = totalCosts * this.getCurrentRentTime();

        //第二引数を無くして、第一引数を動的配列にする
        // Invoice invoice = new Invoice(playfulPet.getPetName(),1,rentalCosts);
        this.reset();

        return rentalCosts;
    }
        
    public double runAssistanceTour(Person person, int amount, String tour ){
        if(!this.isValidTour(tour)) System.out.println("The tour guide does not accept the " + tour + " tour.");
        // ここを動的配列に対応させる
        ArrayList<PlayfulPet> playfulPets = this.createPlayfulPets(amount);
        
        System.out.println("");
        System.out.println("Booting up... Playful Pet Assistance robot at your service.");
        System.out.println("Printing information about the Person to service..." + person);
        System.out.println("");

        //ペットの名前を繰り返し処理
        for(int i = 0; i < playfulPets.size(); i++){
            System.out.println("Printing information about the Playful Pet - " + playfulPets.get(i).getPetName() + " to service..." + playfulPets.get(i));
            if(tour == "all-rounder pack" || tour == "deluxe rounder pack"){
                int count = tour == "all-rounder pack" ? 1 : 3;
                this.genericRounderTour(count, person, playfulPets.get(i));
            }else{
                System.out.println("The tour assistant robot for " + playfulPets.get(i).getPetName() + " and " + person.getName() + " did nothing.");
            }
        }


        //playfulPet.getRentalCosts() を繰り返し処理して合計値を算出
        double totalCosts = playfulPets.get(0).getRentalCosts() * playfulPets.size();
        double rentalCosts = totalCosts * this.getCurrentRentTime();
        this.reset();

        return rentalCosts;
    }

    private void genericRounderTour(int activityCount, Person person, PlayfulPet pet){
        String newLine = System.lineSeparator();
        System.out.println(newLine + "Now starting the generic rounder tour with " + activityCount + " activity(s)");
        System.out.println(person.getName() + " greets " + pet.getPetName() + newLine);
        System.out.println(pet.play() + newLine);
        System.out.println(pet.playNoise() + newLine);
        System.out.println(pet.playWithPerson(person) + newLine);
        for(int i = 0; i < activityCount; i++){
            String activity = this.getRandomActivity();
            System.out.println(pet.getPetName() + " will now " + activity);
            System.out.println("--------");
            System.out.println(pet.doActivity(activity));
            System.out.println("--------" + newLine);
        }
    }
    public abstract String getPetName();
    public abstract ArrayList<PlayfulPet> createPlayfulPets(int amount);
}

class PlayfulCatAssistant extends PlayfulPetAssistant{
    protected String petName = "Cat";
    public ArrayList<PlayfulPet> createPlayfulPets(int amount){
        ArrayList<PlayfulPet> res = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            res.add(new Cat(RandomWrapper.getRanDouble(0.15,0.3), RandomWrapper.getRanDouble(2.0,4.9), RandomWrapper.ranBoolean() ? "male" : "female"));
        }
        return res;
    }
    public String getPetName(){
        return this.petName;
    }
}

class Invoice{
    public String title;
    public String description;
    public double totalCost;
    public Invoice next;
    public Invoice prevent;
    // 第一引数を動的配列にする、第二引数を削除する
    //titleとdescriptionは配列.get(0)をgetName()して、配列.lengthをamountと代わりにする。
    public Invoice(String petName,int amount, double cost){
        this.title = petName + amount + "頭のレンタル";
        this.description = petName + amount + "頭のレンタルをしました。";
        this.totalCost = cost * amount;
    }

    public String get(){
        return title + " " + "$" + totalCost + " " + description + " ";
    }
}

class InvoiceList{
    private Invoice head;
    private Invoice tail;

    public void set(Invoice invoice){
        if(this.head == null) this.head = invoice;
        else if(this.tail == null){
            this.tail = invoice;
            this.head.next = this.tail;
            this.tail.prevent = this.head;
        }else{
            Invoice preTail = this.tail;
            this.tail = invoice;
            this.tail.prevent = preTail;
            this.tail.prevent.next = this.tail;
        }
    }

    public Invoice get(){
        return this.head;
    }
}

class FairyWorld{
    public HashMap<String, PlayfulPetAssistant> animalHash = new HashMap<>();
    public InvoiceList invoiceList = new InvoiceList();
    public void rentPet(PlayfulPetAssistant assistant, Person person){
        System.out.println("Thank you for your pet rental!");
        double costs = assistant.runAssistanceTour(person);
        // ペット（assistantから追加？）、コスト＝cost;
        System.out.println(costs + " dollars were charged to " + person.getName() + "'s credit card.");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + System.lineSeparator());
        setInvoice(assistant.getPetName(), 1, costs);
    }

    public void rentPet(String petKey, Person person, int amount, String tour){
        PlayfulPetAssistant robot = this.animalHash.get(petKey);
        System.out.println("Thank you for your pet rental!");
        double costs = robot.runAssistanceTour(person,amount);
        System.out.println(costs + " dollars were charged to " + person.getName() + "'s credit card.");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + System.lineSeparator());
        // ここにInvoice作成する。robotから請求書のタイトル、説明を取得できるようにする。
        this.setInvoice(robot.getPetName(), amount, costs);
    }

    public void rentPet(String petKey, Person person, int amount){
        PlayfulPetAssistant robot = this.animalHash.get(petKey);
        System.out.println("Thank you for your pet rental!");
        double costs = robot.runAssistanceTour(person,amount);
        System.out.println(costs + " dollars were charged to " + person.getName() + "'s credit card.");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + System.lineSeparator());
        // ここにInvoice作成する。robotから請求書のタイトル、説明を取得できるようにする。
        this.setInvoice(robot.getPetName(), amount, costs);
    }

    public void addPlayfulPetAssistant(String key, PlayfulPetAssistant robot){
        this.animalHash.put(key, robot);
    }
    // petの名前、頭数（amount）、cost（costs）を取得する
    public void setInvoice(String petName, int amount, double cost){
        this.invoiceList.set(new Invoice(petName, amount, cost));
    }

    public void getRentedPetsInvoice(){
        //InvoiceListのheadを取得して、繰り返し処理で表示
        Invoice currentInvoice = this.invoiceList.get();
        while(currentInvoice != null){
            System.out.println(currentInvoice.get());
            currentInvoice = currentInvoice.next;
        }
    }
}

// これでシステムが整ったので、あとは作成したい個々のPlayfulPetにクラスを追加して、Factory Methodを含むクライアントシステムをサブクラス化するだけです。今回のケースでは、PlayfulPetAssistantになります。
// PlayfulPetであるDogと、PlayfulDogAssistant（Factory MethodがDogを生成します）作成してください。
class Dog extends Mammal implements PlayfulPet{
    public static final String SPECIES = "Dog";
    public static final double LIFE_EXPECTANCY = 4800;
    public static final double Body_TEMPERATURE = 39;
    
    private static final double PLAYFUL_HOURLY_COSTS = 35;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","chase","swim","drink","run","explore","pet"};
    private static final String[] DISLIKED_ACTIVITIES = {"hug","dressup"};

    public Dog(double heightM, double weightKg, String biologicalSex){
        super(Dog.SPECIES, heightM, weightKg, Dog.LIFE_EXPECTANCY, biologicalSex, Dog.LIFE_EXPECTANCY);
    }

    public String toString(){
        return super.toString() + " this is a dog";
    }

    public void woof(){
        System.out.println("Woof Woof");
    }

    public String getPetName(){
        return this.species;
    }

    public String play(){
        return "This dog starts running on the park and chases a ball.";
    }

    public String playWithPerson(Person person){
        String s = "The dog runs towards " + person.getName();
        s+= ". After the dog taking kin to " + person.getName() + ", " + person.getName() + " throws a frisbee disk and the dog chases it.";
        return s;
    }

    public String playNoise(){
        return "Wooooof Woooof";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The dog ate the entire food in a few bites.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The dog took a quick nap.";
        }
        else if(this.likesActivity(activity)) return "Woof Woof. The dog really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "The dog did not like " + activity + " activity. The dog walked away";
        return "The dog felt indiferent about the " + activity + " activity.";
    };
}

// PlayfulPetであるRabbitと、PlayfulRabbitAssistant（Factory MethodがRabbitを生成します）作成してください。
class Rabbit extends Mammal implements PlayfulPet{
    public static final String SPECIES = "Rabbit";
    public static final double LIFE_EXPECTANCY = 3000;
    public static final double Body_TEMPERATURE = 40;
    
    private static final double PLAYFUL_HOURLY_COSTS = 30;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","chase","drink","jump","dig"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath","dressup"};

    public Rabbit(double heightM, double weightKg, String biologicalSex){
        super(Rabbit.SPECIES, heightM, weightKg, Rabbit.LIFE_EXPECTANCY, biologicalSex, Rabbit.LIFE_EXPECTANCY);
    }

    public String toString(){
        return super.toString() + " this is a rabbit";
    }

    public void woof(){
        System.out.println("Squeak Squeak");
    }

    public String getPetName(){
        return this.species;
    }

    public String play(){
        return "This rabbit starts jumping around and chases an insect on the grass.";
    }

    public String playWithPerson(Person person){
        String s = "The bunny hops towards " + person.getName();
        s+= ". After the rabbit stares at " + person.getName() + ", " + person.getName() + " makes the rabbit chase a small carrot. The rabbit hops towards it.";
        return s;
    }

    public String playNoise(){
        return "Squeak";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The rabbit chew on the food bit by bit taking tiny bites.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The rabbit took a long nap.";
        }
        else if(this.likesActivity(activity)) return ".... The Rabbit really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "Squeeeak. The Rabbit did not like " + activity + " activity. The rabbit quickly hopped away";
        return "The rabbit felt indiferent about the " + activity + " activity.";
    };
}

class Pony extends Mammal implements PlayfulPet{
    public static final String SPECIES = "Pony";
    public static final double LIFE_EXPECTANCY = 3000;
    public static final double Body_TEMPERATURE = 40;
    
    private static final double PLAYFUL_HOURLY_COSTS = 30;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","chase","drink","run"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath","dressup"};

    public Pony(double heightM, double weightKg, String biologicalSex){
        super(Rabbit.SPECIES, heightM, weightKg, Rabbit.LIFE_EXPECTANCY, biologicalSex, Rabbit.LIFE_EXPECTANCY);
    }

    public String getPetName(){
        return this.species;
    }

    public String play(){
        return "This Pony running&hopping";
    }

    public String playWithPerson(Person person){
        String s = "The Pony stares at " + person.getName();
        s+= ". After taking kin to " + person.getName() + ", " + person.getName() + " throws a mouse toy at this cat and the cat starts chasing the mouse toy";
        return s;
    }

    public String playNoise(){
        return "Bow";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The cat enjoyed eating food.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The cat took a good nap.";
        }
        else if(this.likesActivity(activity)) return "Meow. The cat really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "The cat really hated the " + activity + " activity.";
        return "The cat felt indiferent about the " + activity + " activity.";
    };
}

class Chicken extends Bird implements PlayfulPet{
    public static final String SPECIES = "Chicken";
    public static final double LIFE_EXPECTANCY = 3000;
    public static final double Body_TEMPERATURE = 40;
    
    private static final double PLAYFUL_HOURLY_COSTS = 30;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","chase","drink","fly"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath","dressup"};

    public Chicken(double heightM, double weightKg, String biologicalSex){
        super(Chicken.SPECIES, heightM, weightKg, Chicken.LIFE_EXPECTANCY, biologicalSex);
    }

    public String getPetName(){
        return this.species;
    }

    public String play(){
        return "This chicken hopping";
    }

    public String playWithPerson(Person person){
        String s = "The Chicken stares at " + person.getName();
        s+= ". After taking kin to " + person.getName() + ", " + person.getName() + " throws a mouse toy at this cat and the cat starts chasing the mouse toy";
        return s;
    }

    public String playNoise(){
        return "Qwe";
    }

    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The cat enjoyed eating food.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The cat took a good nap.";
        }
        else if(this.likesActivity(activity)) return "Meow. The cat really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "The cat really hated the " + activity + " activity.";
        return "The cat felt indiferent about the " + activity + " activity.";
    };
}

// PlayfulDogAssistantとPlayfulRabbitAssistantは、サブクラス化することによって、PlayfulPetAssistantのFactory Methodを実装します。作成されるオブジェクトが異なる点以外は全く同じように動作します。
class PlayfulDogAssistant extends PlayfulPetAssistant{
    protected String petName = "dog";
    public ArrayList<PlayfulPet>createPlayfulPets(int amount){
        ArrayList<PlayfulPet>res = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            res.add(new Dog(RandomWrapper.getRanDouble(0.15,0.3), RandomWrapper.getRanDouble(2.0,4.9), RandomWrapper.ranBoolean() ? "male" : "female"));
        }
        return res;
    }

    public String getPetName(){
        return this.petName;
    }
}

class PlayfulRabbitAssistant extends PlayfulPetAssistant{
    protected String petName = "rabbit";
    public ArrayList<PlayfulPet>createPlayfulPets(int amount){
        ArrayList<PlayfulPet>res = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            res.add(new Rabbit(RandomWrapper.getRanDouble(0.15,0.3), RandomWrapper.getRanDouble(2.0,4.9), RandomWrapper.ranBoolean() ? "male" : "female"));
        }
        return res;
    }

    public String getPetName(){
        return this.petName;
    }
}

class PlayfulPonyAssistant extends PlayfulPetAssistant{
    protected String petName = "pony";
    public ArrayList<PlayfulPet>createPlayfulPets(int amount){
        ArrayList<PlayfulPet>res = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            res.add(new Pony(RandomWrapper.getRanDouble(0.15,0.3), RandomWrapper.getRanDouble(2.0,4.9), RandomWrapper.ranBoolean() ? "male" : "female"));
        }
        return res;
    }

    public String getPetName(){
        return this.petName;
    }
}

class PlayfulChickenAssistant extends PlayfulPetAssistant{
    protected String petName = "chicken";
    public ArrayList<PlayfulPet>createPlayfulPets(int amount){
        ArrayList<PlayfulPet>res = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            res.add(new Chicken(RandomWrapper.getRanDouble(0.15,0.3), RandomWrapper.getRanDouble(2.0,4.9), RandomWrapper.ranBoolean() ? "male" : "female"));
        }
        return res;
    }

    public String getPetName(){
        return this.petName;
    }
}

class Main{
    public static void main(String[] args){
        FairyWorld fairyWorld = new FairyWorld();
        Person jessica = new Person("Jessica", "Roller", 30, 1.65, 95, "female");

        fairyWorld.addPlayfulPetAssistant("cat", new PlayfulCatAssistant());
        fairyWorld.addPlayfulPetAssistant("dog", new PlayfulDogAssistant());
        fairyWorld.addPlayfulPetAssistant("rabbit", new PlayfulRabbitAssistant());
        fairyWorld.addPlayfulPetAssistant("pony", new PlayfulPonyAssistant());
        fairyWorld.addPlayfulPetAssistant("chicken", new PlayfulChickenAssistant());
        // その後、jessicaはdogやrabbitとも遊びます。サブクラス化してfactory methodを実装することで、異なるオブジェクトをサポートするようにコードを拡張しました。
        fairyWorld.rentPet("cat", jessica, 3);
        fairyWorld.rentPet("dog", jessica, 1);
        fairyWorld.rentPet("rabbit", jessica, 2);
        fairyWorld.rentPet("pony", jessica, 1);
        fairyWorld.rentPet("chicken", jessica, 2);
        fairyWorld.getRentedPetsInvoice();
    }
}