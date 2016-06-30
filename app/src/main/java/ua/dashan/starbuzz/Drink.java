package ua.dashan.starbuzz;


public class Drink {
    private String name;
    private String description;
    private int imageResourceId;
    private int cost;
    private Drink(String name,String description,int imageResourceId,int cost){
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

    public static final Drink[]drinks={
            new Drink("Coca-cola","Это вкусное, сытное, а также полезное блюдо, сделанное из свежих ингредиентов",R.drawable.kokakola,15),
            new Drink("Вода «Моршинская» без газа","Это вода с Днепра в фирменной упаковке",R.drawable.morshin_bez_gaza,8),
            new Drink("Сок «Rich» вишневый","Отличный сок",R.drawable.sok,16)
    };
}
