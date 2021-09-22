// ここから開発しましょう。

//class（Person,FairyWorld,StateOfAffairs,RideExperience,XExperience,Attraction,xattraction)を作る
//interface Statusを作る
//各メソッドを実装する
//Person,xAttaraction,StateOfAffairsのメンバ変数を作る

interface Attraction{
    abstract public double averageSpeedM();
    abstract public double maximumSpeed();
    abstract public int friendliness();
    abstract public int scarriness();
    abstract public String description();
    abstract public String title();
    abstract public void rideNarration(StateOfAffairs parkState);
}

class FamilyCoaster implements Attraction{
    private final double AVERAGESPEED = 120.0;
    private final double MAXSPEED = 170.0;
    private final int FRIENDLINESS = 20;
    private final int SCARRINESS = 50;
    public double averageSpeedM(){
        return this.AVERAGESPEED;
    }

    public double maximumSpeed(){
        return this.MAXSPEED;
    }

    public int friendliness(){
        return this.FRIENDLINESS;
    }

    public int scarriness(){
        return this.SCARRINESS;
    }

    public String description(){
        return "FamilyCoaster is A little scary Attraction";
    }

    public String title(){
        return "FamilyCoaster";
    }

    public void rideNarration(StateOfAffairs affairs){
        if(affairs.getCountPerson() > 10){
            System.out.println("歓声が聞こえてきます！");
        }

        if(affairs.getTemperature() > 30.0){
            System.out.println("暖かい日に風を感じて気持ちがいいです");
        }else if(affairs.getTemperature() < 10.0){
            System.out.println("顔に冷たい風が当たって痛いです");
        }
    }
}

abstract class RideExperience{
    public void ride(Person person, StateOfAffairs affairs){
        Attraction atr = this.createRide();
        System.out.println(person.getName() + "は" + atr.title() + "に乗りました");
        atr.description();
        atr.rideNarration(affairs);
        person.setHappiness(atr.friendliness());
        person.setFrightful(atr.scarriness());
    }

    abstract public Attraction createRide();
}

class FamilyCoasterExperience extends RideExperience{
    public FamilyCoaster createRide(){
        return new FamilyCoaster();
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

interface Status{
    abstract public int getHappiness();
    abstract public void setHappiness(int value);
    abstract public int getEnergy();
    abstract public void setEnergy(int value);
    abstract public int getNausea();
    abstract public void setNausea(int value);
    abstract public int getBathroom();
    abstract public void setBathroom(int value);
    abstract public int getFrightful();
    abstract public void setFrightful(int value);
}

class Person implements Status{
    private Name name;
    private int age;
    private int happiness;
    private int energy;
    private int nausea;
    private int bathroom;
    private int frightful;

    public Person(String firstName, String lastName, int age){
        this.name = new Name(firstName, lastName);
        this.age = age;
    }

    public String getName(){
        return this.name.toString();
    }

    public String toString(){
        return super.toString() + ". The name of this Person is " + this.getName();
    }
    public int getHappiness(){
        return this.happiness;
    }
    public void setHappiness(int value){
        this.happiness += value;
    }
    public int getEnergy(){
        return this.energy;
    }
    public void setEnergy(int value){
        this.energy += value;
    }
    public int getNausea(){
        return this.nausea;
    }
    public void setNausea(int value){
        this.nausea += value;
    }
    public int getBathroom(){
        return this.bathroom;
    }
    public void setBathroom(int value){
        this.bathroom += value;
    }
    public int getFrightful(){
        return this.frightful;
    }
    public void setFrightful(int value){
        this.frightful += value;
    }
}


class StateOfAffairs{
    //現在の時刻、日付、人数、温度、音のレベル、雰囲気等
    // public LocalDateTime　currentTime = LocalDateTime.now();
    private int countPerson = 0;
    private double temperature = 30.0;
    private int volume = 5;

    public StateOfAffairs(int countPerson, double temperature, int volume){
        this.countPerson = countPerson;
        this.temperature = temperature;
        this.volume = volume;
    }

    public int getCountPerson(){
        return this.countPerson;
    }

    public double getTemperature(){
        return this.temperature;
    }

    public int getVolume(){
        return this.volume;
    }
}

class FairyWorld{
    public StateOfAffairs affairs;
    public int countPerson = 0;
    public StateOfAffairs createStateOfAffairs(double temperature, int volume){
        this.affairs = new StateOfAffairs(this.countPerson, temperature, volume);
        return this.affairs;
    }
    public Person createPerson(String firstName, String lastName, int age){
        this.countPerson += 1;
        return new Person(firstName,lastName,age);
    }
}

class Main{
    public static void main(String args[]){
        FairyWorld fairyWorld = new FairyWorld();
        StateOfAffairs affairs = fairyWorld.createStateOfAffairs(9.3,6);
        Person person1 = fairyWorld.createPerson("jone","cruise",25);
        Person person2 = fairyWorld.createPerson("jane","pattric",42);
        FamilyCoasterExperience coaster = new FamilyCoasterExperience();
        coaster.ride(person1,affairs);
    }
}