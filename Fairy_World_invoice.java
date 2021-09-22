// ここから開発しましょう。

class VacationInvoice{
    private int days;
    private int amountOfPeople;
    private String hotelType;
    public VacationInvoice(int days, int amountOfPeople, String hotelType){
        this.days = days;
        this.amountOfPeople = amountOfPeople;
        this.hotelType = hotelType;
    }

    public String invoiceToString(){
        String res = "";
        if(this.days != 0){
            Integer i = Integer.valueOf(this.days);
            res += i.toString() + "days,";
        }
        if(this.amountOfPeople != 0){
            Integer i = Integer.valueOf(this.amountOfPeople);
            res += i.toString() + "people,";
        }
        if(this.hotelType != "") res += this.hotelType; 
        return res;
    }
}

class VacationInvoiceBuilder{
    private int days;
    private int amountOfPeople;
    private String hotelType;
    public VacationInvoiceBuilder(){
        this.reset();
    }
    public VacationInvoiceBuilder setDays(int days){
        this.days = days;
        return this;
    }
    public VacationInvoiceBuilder setAmountOfPeople(int amount){
        this.amountOfPeople = amount;
        return this;
    }
    public VacationInvoiceBuilder setHotelType(String hotelType){
        this.hotelType = hotelType;
        return this;
    }
    public VacationInvoice build(){
        return new VacationInvoice(this.days, this.amountOfPeople, this.hotelType);
    }

    public void reset(){
        this.days = 0;
        this.amountOfPeople = 0;
        this.hotelType = "";
    }
}

class VacationDirector{
    public static VacationInvoiceBuilder coupleWeekendRetreat(VacationInvoiceBuilder builder){
        builder.setDays(2).setAmountOfPeople(2).setHotelType("Luxury");
        return builder;
    }
    public static VacationInvoiceBuilder familyWeekendRetreat(VacationInvoiceBuilder builder){
        builder.setDays(2).setAmountOfPeople(6).setHotelType("Economy");
        return builder;
    }
    public static VacationInvoiceBuilder familyWeekDeluxe(VacationInvoiceBuilder builder){
        builder.setDays(7).setAmountOfPeople(6).setHotelType("Luxury");
        return builder;
    }
}

class FairyWorld{
    public VacationInvoice planMyVacation(String plan){
        //swicthで切り替える
        //vacationBuilderも渡すのか？
        VacationInvoiceBuilder builder = new VacationInvoiceBuilder();
        switch(plan){
            case "COUPLE_WEEKEND_RETREAT": VacationDirector.coupleWeekendRetreat(builder);break;
            case "FAMILY_WEEKEND_RETREAT": VacationDirector.familyWeekendRetreat(builder);break;
            case "FAMILY_WEEK_DELUXE": VacationDirector.familyWeekDeluxe(builder);break;
        }
        return builder.build();
    }
}

class Main{
    public static void main(String args[]){
        FairyWorld park = new FairyWorld();
        System.out.println(park.planMyVacation("COUPLE_WEEKEND_RETREAT").invoiceToString());
    }
}