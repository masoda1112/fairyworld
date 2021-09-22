// ここから開発しましょう。
interface FairyWorldThemeFactory{
    abstract Theme createTheme();
    abstract Poster createPoster();
    abstract StringOfLights createStringOfLights();
    abstract LightShow createLightShow();
    abstract MusicSong createMusicSong();
}

class SummerThemeFactory implements FairyWorldThemeFactory{
    public Theme createTheme(){
        Poster p = this.createPoster();
        StringOfLights s = this.createStringOfLights();
        LightShow l = this.createLightShow();
        MusicSong m = this.createMusicSong();
        return new SummerTheme(p,s,l,m);
    }

    public Poster createPoster(){
        return new SummerPoster();
    }
    public StringOfLights createStringOfLights(){
        return new SummerStringOfLights();
    }
    public LightShow createLightShow(){
        return new SummerLightShow();
    }
    public MusicSong createMusicSong(){
        return new SummerMusicSong();
    }
}

class WinterThemeFactory implements FairyWorldThemeFactory{
    public Theme createTheme(){
        Poster p = this.createPoster();
        StringOfLights s = this.createStringOfLights();
        LightShow l = this.createLightShow();
        MusicSong m = this.createMusicSong();
        return new WinterTheme(p,s,l,m);
    }
    public Poster createPoster(){
        return new WinterPoster();
    }
    public StringOfLights createStringOfLights(){
        return new WinterStringOfLights();
    }
    public LightShow createLightShow(){
        return new WinterLightShow();
    }
    public MusicSong createMusicSong(){
        return new WinterMusicSong();
    }
}

interface Theme{
    abstract public Poster getPoster();
    abstract public StringOfLights getStringOfLights();
    abstract public LightShow getLightShow();
    abstract public MusicSong getMusicSong();
}

class SummerTheme implements Theme{
    private Poster poster;
    private StringOfLights stringOfLights;
    private LightShow lightShow;
    private MusicSong musicSong;

    public SummerTheme(Poster poster, StringOfLights stringOfLights, LightShow lightShow, MusicSong musicSong){
        this.poster = poster;
        this.stringOfLights = stringOfLights;
        this.lightShow = lightShow;
        this.musicSong = musicSong;
    }

    public Poster getPoster(){
        return this.poster;
    }
    public StringOfLights getStringOfLights(){
        return this.stringOfLights;
    }
    public LightShow getLightShow(){
        return this.lightShow;
    }
    public MusicSong getMusicSong(){
        return this.musicSong;
    }
}

class WinterTheme implements Theme{
    private Poster poster;
    private StringOfLights stringOfLights;
    private LightShow lightShow;
    private MusicSong musicSong;

    public WinterTheme(Poster poster, StringOfLights stringOfLights, LightShow lightShow, MusicSong musicSong){
        this.poster = poster;
        this.stringOfLights = stringOfLights;
        this.lightShow = lightShow;
        this.musicSong = musicSong;
    }
    public Poster getPoster(){
        return this.poster;
    }
    public StringOfLights getStringOfLights(){
        return this.stringOfLights;
    }
    public LightShow getLightShow(){
        return this.lightShow;
    }
    public MusicSong getMusicSong(){
        return this.musicSong;
    }

}

interface Poster{
    abstract public String getPoster();
}

class SummerPoster implements Poster{
    public String getPoster(){
        return "beach & turtle & Cameron Michelle Diaz";
    }
}

class WinterPoster implements Poster{
    public String getPoster(){
        return "snow & penguin & Anne Jacqueline Hathaway,";
    }
}

interface StringOfLights{
    abstract public String getStringOfLights();
}

class SummerStringOfLights implements StringOfLights{
    public String getStringOfLights(){
        return "白、青、白、青、白、青、白、青";
    }
}

class WinterStringOfLights implements StringOfLights{
    public String getStringOfLights(){
        return "赤、緑、赤、緑、赤、緑、赤";
    }
}

interface LightShow{
    abstract public String narration();
}

class SummerLightShow implements LightShow{
    public String narration(){
        return "青い空、白い海、亀にハイビスカス、そしてキャメロンディアス！";
    }
}

class WinterLightShow implements LightShow{
    public String narration(){
        return "白銀のゲレンデには、アンハサウェイ！";
    }
}

interface MusicSong{
    abstract public String getMusicSong();
}

class SummerMusicSong implements MusicSong{
    public String getMusicSong(){
        return "ブラジルのボサノバ的なやつ";
    }
}

class WinterMusicSong implements MusicSong{
    public String getMusicSong(){
        return "wamのLast Christmas";
    }
}

class FairyWorld{
    private Theme theme;
    public void demoTheme(FairyWorldThemeFactory factory){
        this.theme = factory.createTheme();
        System.out.println(this.theme.getPoster().getPoster());
        System.out.println(this.theme.getStringOfLights().getStringOfLights());
        System.out.println(this.theme.getLightShow().narration());
        System.out.println(this.theme.getMusicSong().getMusicSong());
    }
}

class Main{
    public static void main(String args[]){
        FairyWorld park = new FairyWorld();
        park.demoTheme(new SummerThemeFactory());
    }
}