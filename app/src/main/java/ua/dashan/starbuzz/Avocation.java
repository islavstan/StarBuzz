package ua.dashan.starbuzz;


public class Avocation {
    private String name;
    private String description;
    private int imageResourceId;
    private int cost;

    private Avocation(String name,String description, int imageResourceId,int cost){
        this.name=name;
        this.description=description;
        this.imageResourceId=imageResourceId;
        this.cost=cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return this.name;
    }
    public static final Avocation[]avocations= {new Avocation("Бильярд","Весёлая игра",R.drawable.bil,50),
    new Avocation("Картинг","Быстрая езда на маленьких машинках",R.drawable.karting,100),
    new Avocation("Покер","Карточная игра",R.drawable.poker,30)};

}
