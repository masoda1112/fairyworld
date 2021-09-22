// ここから開発しましょう。
import java.util.Map;
import java.util.HashMap;

interface Prototype<E>{
    abstract E clone();
}

interface Stamp extends Prototype<Stamp>{
    abstract String getTitle();
    abstract String getRender();
    abstract String getColor();
    abstract Stamp clone();
}

class GenericStamp implements Stamp{
    private final static String TITLE = "Generic Stamp";
    private final static String RENDER = "❔";

    protected String title;
    protected String render;
    protected String color;
    
    public GenericStamp(){};

    public GenericStamp(String color){
        this.title = GenericStamp.TITLE;
        this.render = GenericStamp.RENDER;
        this.color = color;
    }

    public GenericStamp(Stamp stamp){
        this.title = GenericStamp.TITLE;
        this.render = GenericStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle(){
        return this.title;
    }

    public String getRender(){
        return this.render;
    }

    public String getColor(){
        return this.color;
    }

    public Stamp clone(){
        return new GenericStamp(this);
    }

    public String toString(){
        return this.title + " - " + this.render + " (" + this.color + ")"; 
    }
}

class StarStamp extends GenericStamp{
    private final static String TITLE = "Star";
    private final static String RENDER = "⭐";
    
    public StarStamp(String color){
        this.title = StarStamp.TITLE;
        this.render = StarStamp.RENDER;
        this.color = color;
    } 

    public StarStamp(Stamp stamp){
        this.title = StarStamp.TITLE;
        this.render = StarStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone(){
        return new StarStamp(this.getColor());
    }
}

class HeartStamp extends GenericStamp{
    private final static String TITLE = "Heart";
    private final static String RENDER = "💖";
    
    public HeartStamp(String color){
        this.title = HeartStamp.TITLE;
        this.render = HeartStamp.RENDER;
        this.color = color;
    } 

    public HeartStamp(Stamp stamp){
        this.title = HeartStamp.TITLE;
        this.render = HeartStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone(){
        return new HeartStamp(this);
    }
}

class PeaceStamp extends GenericStamp{
    private final static String TITLE = "Peace";
    private final static String RENDER = "☮️";
    
    public PeaceStamp() {}

    public PeaceStamp(String color){
        this.title = PeaceStamp.TITLE;
        this.render = PeaceStamp.RENDER;
        this.color = color;
    } 

    public PeaceStamp(Stamp stamp){
        this.title = PeaceStamp.TITLE;
        this.render = PeaceStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone(){
        return new PeaceStamp(this);
    }
}

class TreeStamp extends GenericStamp{
    private final static String TITLE = "Tree";
    private final static String RENDER = "🌲";
    
    public TreeStamp(String color){
        this.title = TreeStamp.TITLE;
        this.render = TreeStamp.RENDER;
        this.color = color;
    } 

    public TreeStamp(Stamp stamp){
        this.title = TreeStamp.TITLE;
        this.render = TreeStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone(){
        return new TreeStamp(this);
    }
}

class RainbowStamp extends GenericStamp{
    private final static String TITLE = "Rainbow";
    private final static String RENDER = "🌈";
    
    public RainbowStamp(String color){
        this.title = RainbowStamp.TITLE;
        this.render = RainbowStamp.RENDER;
        this.color = color;
    } 

    public RainbowStamp(Stamp stamp){
        this.title = RainbowStamp.TITLE;
        this.render = RainbowStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone(){
        return new RainbowStamp(this);
    }
}

class BearStamp extends GenericStamp{
    private final static String TITLE = "Bear";
    private final static String RENDER = "🧸";
    
    public BearStamp(String color){
        this.title = BearStamp.TITLE;
        this.render = BearStamp.RENDER;
        this.color = color;
    } 

    public BearStamp(Stamp stamp){
        this.title = BearStamp.TITLE;
        this.render = BearStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone(){
        return new BearStamp(this);
    }
}

class HorseStamp extends GenericStamp{
    private final static String TITLE = "Horse";
    private final static String RENDER = "🐎";
    
    public HorseStamp(String color){
        this.title = HorseStamp.TITLE;
        this.render = HorseStamp.RENDER;
        this.color = color;
    } 

    public HorseStamp(Stamp stamp){
        this.title = HorseStamp.TITLE;
        this.render = HorseStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone(){
        return new HorseStamp(this);
    }
}

class RingStamp extends GenericStamp{
    private final static String TITLE = "Ring";
    private final static String RENDER = "💍";
    
    public RingStamp(String color){
        this.title = RingStamp.TITLE;
        this.render = RingStamp.RENDER;
        this.color = color;
    } 

    public RingStamp(Stamp stamp){
        this.title = RingStamp.TITLE;
        this.render = RingStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone(){
        return new RingStamp(this);
    }
}

class AppleStamp extends GenericStamp{
    private final static String TITLE = "Apple";
    private final static String RENDER = "🍎";
    
    public AppleStamp(String color){
        this.title = AppleStamp.TITLE;
        this.render = AppleStamp.RENDER;
        this.color = color;
    } 

    public AppleStamp(Stamp stamp){
        this.title = AppleStamp.TITLE;
        this.render = AppleStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone(){
        return new AppleStamp(this);
    }
}

class StampPrototypeFactory{
     
    // 静的メモリへの格納
    private static Map<Integer, Stamp> stampRegistry;

    private final static String DEFAULT_COLOR = "#e74c3c";

    // 静的ブロックは、クラスがロードされたときに実行されるコードです。これは、問題が最初に実行されたときに静的データを初期化するのに便利です。
    static {
        
        // レジストリの最初のデータを初期化します。キーは必ずしも整数である必要はありません。
        StampPrototypeFactory.stampRegistry = new HashMap<Integer, Stamp>();

        // star、heart、peaceスタンプを持ちます。
        StampPrototypeFactory.stampRegistry.put(0, new GenericStamp(StampPrototypeFactory.DEFAULT_COLOR));
        StampPrototypeFactory.stampRegistry.put(1, new StarStamp(StampPrototypeFactory.DEFAULT_COLOR));
        StampPrototypeFactory.stampRegistry.put(2, new HeartStamp(StampPrototypeFactory.DEFAULT_COLOR));
        StampPrototypeFactory.stampRegistry.put(3, new PeaceStamp(StampPrototypeFactory.DEFAULT_COLOR));
        StampPrototypeFactory.stampRegistry.put(4, new BearStamp(StampPrototypeFactory.DEFAULT_COLOR));
        StampPrototypeFactory.stampRegistry.put(5, new HorseStamp(StampPrototypeFactory.DEFAULT_COLOR));
    }

    // より多くの項目を登録または変更できるようにします。見つからない場合は、プロトタイプに追加します。
    public static void updatePrototype(Integer key, Stamp stamp){
        StampPrototypeFactory.stampRegistry.put(key, stamp);
    }

    // オブジェクトを取得します。これはレジスタ内にあるもののクローン（ディープコピーを作成）を作成することに注意してください。
    public static Stamp get(Integer key){
        Stamp p = StampPrototypeFactory.stampRegistry.get(key);
        if(p != null) return p.clone();
        return null;
    }
}

class FairyWorld{
    public void photoBoothShoot(Integer[] stampSlots){
        String endl = System.lineSeparator();

        for(int i = 0; i < stampSlots.length; i++){
            System.out.println("Stamp - " + StampPrototypeFactory.get(stampSlots[i]) + " used in the photoshoot!");
        }
    }
}

class Main{
    public static void main(String[] args){
        String endl = System.lineSeparator();

        // オブジェクトを作成するのは呼び出し元の仕事です。実際のシステムでは、このプロセスはすでに組み込まれている可能性があることに注意してください。これは動的でテスト可能なものになります。
        System.out.println(StampPrototypeFactory.get(1));
        System.out.println(StampPrototypeFactory.get(0));
        System.out.println(StampPrototypeFactory.get(2));

        // プロトタイプレジストリ内のアイテムを動的に追加または置換します。
        // スロット4をstarスタンプに変更します。
        StampPrototypeFactory.updatePrototype(6, new StarStamp("#f1c40f"));
        StampPrototypeFactory.updatePrototype(7, new TreeStamp("#2980b9"));
        StampPrototypeFactory.updatePrototype(8, new RainbowStamp("#ecf0f1"));
        StampPrototypeFactory.updatePrototype(9, new PeaceStamp("#95a5a6"));
        StampPrototypeFactory.updatePrototype(10, new RingStamp("#ecf0f1"));
        StampPrototypeFactory.updatePrototype(11, new AppleStamp("#95a5a6"));

        FairyWorld fw = new FairyWorld();
        fw.photoBoothShoot(new Integer[]{3,4,2,10,11,11,2,3,4,5,5,6,6,7,7,0,1,4});
    }
}