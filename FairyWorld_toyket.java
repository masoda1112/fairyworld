// ここから開発しましょう。
interface HumanoidToyKitFactory{
    abstract public Head createHead(Person person);
    abstract public Body createBody(Person person);
    abstract public Arms createArms(Person person);
    abstract public Legs createLegs(Person person);
    abstract public Humanoid createHumanoid(Head h,Body b, Arms a,Legs l);
    abstract public int totalCost(Head head, Body body, Arms arms, Legs legs);
}

class RobotToykitFactory implements HumanoidToyKitFactory{

    public RobotHead createHead(Person person){
        RobotHead head = new RobotHead(80,5);
        if(head.getTargetAge() > person.getAge()){
            System.out.println("Headの取り付けには保護者の方の協力が必要です。");
        }
        return head;
    }

    public RobotBody createBody(Person person){
        RobotBody body = new RobotBody(120, 4);
        if(body.getTargetAge() > person.getAge()){
            System.out.println("Bodyの取り付けには保護者の方の協力が必要です。");            
        }
        return body;
    }

    public RobotArms createArms(Person person){
        RobotArms arms = new RobotArms(30, 10);
        if(arms.getTargetAge() > person.getAge()){
            System.out.println("Armsの取り付けには保護者の方の協力が必要です。");            
        }
        return arms;
    }

    public RobotLegs createLegs(Person person){
        RobotLegs legs = new RobotLegs(50, 10);
        if(legs.getTargetAge() > person.getAge()){
            System.out.println("Legsの取り付けには保護者の方の協力が必要です。");
        }
        return legs;
    }

    public Robot createHumanoid(Head head, Body body, Arms arms, Legs legs){
        return new Robot(head,body,arms,legs);
    }

    public int totalCost(Head head, Body body, Arms arms, Legs legs){
        return head.getCost() + body.getCost() + arms.getCost() + legs.getCost();
    }
}

interface Humanoid{
    abstract public void sound();
    abstract public void walk();
}

class Robot implements Humanoid{
    protected Head head;
    protected Body body;
    protected Arms arms;
    protected Legs legs;

    public Robot(Head head, Body body, Arms arms, Legs legs){
        this.head = head;
        this.body = body;
        this.legs = legs;
        this.arms = arms;
    }

    public void sound(){
        System.out.println("robotSound");
    }

    public void walk(){
        System.out.println("walk");
    }
}

interface Product{
    abstract public int getCost();
    abstract public int getTargetAge();
}

class RobotProduct implements Product{
    protected int cost;
    protected int age;

    public RobotProduct(int cost, int age){
        this.cost = cost;
        this.age = age;
    }

    public int getCost(){
        return this.cost;
    }

    public int getTargetAge(){
        return this.age;
    }
}

interface Head extends Product{
    abstract public String getHeadAction();
}

class RobotHead extends RobotProduct implements Head{
    public RobotHead(int cost, int age){
        super(cost,age);
    }

    public String getHeadAction(){
        return "head";
    }
}

interface Body extends Product{
    abstract public String getBodyAction();
}

class RobotBody extends RobotProduct implements Body{
    public RobotBody(int cost, int age){
        super(cost,age);
    }
    public String getBodyAction(){
        return "body";
    }
}

interface Arms extends Product{
    abstract public String getArmsAction();
}

class RobotArms extends RobotProduct implements Arms{
    public RobotArms(int cost, int age){
        super(cost,age);
    }
    public String getArmsAction(){
        return "arms";
    }
}

interface Legs extends Product{
    abstract public String getLegsAction();
}

class RobotLegs extends RobotProduct implements Legs{
    public RobotLegs(int cost, int age){
        super(cost,age);
    }
    public String getLegsAction(){
        return "legs";
    }
}

class Person{
    private String name;
    private int age;
    private int money;
    public Person(String name, int age, int money){
        this.name = name;
        this.age = age;
        this.money = money;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public int getStockMoney(){
        return this.money;
    }
    public void payment(int price){
        this.money -= price;
    }
}

class FairyWorld{
    private HumanoidToyKitFactory humanoid;
    public Humanoid createAHumanoidToyByKit(HumanoidToyKitFactory factory,Person person){
        Head head = factory.createHead(person);
        Body body = factory.createBody(person);
        Arms arms = factory.createArms(person);
        Legs legs = factory.createLegs(person);
        Humanoid humanoid = factory.createHumanoid(head,body,arms,legs);
        int cost = factory.totalCost(head,body,arms,legs);
        System.out.println("総額は$" + cost);
        person.payment(cost);
        System.out.println(person.getStockMoney());

        return humanoid;
    }
}

class Main{
    public static void main(String args[]){
        FairyWorld park = new FairyWorld();
        Person person1 = new Person("bob",8,1000);
        Humanoid robot = park.createAHumanoidToyByKit(new RobotToykitFactory(),person1);
        robot.sound();
        robot.walk();
    }
}