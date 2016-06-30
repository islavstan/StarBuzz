package ua.dashan.starbuzz;

public class Food {
    private String name;
    private String description;
    private int imageResorceId;
    private int cost;

    //массив с элементами drink
  public static final Food[]food={
            new Food("Пицца «Сатурадо»","Куриное филе, нежная ветчина, шампиньоны, зеленые оливки, болгарский перец, свежий помидор, горячий сыр",R.drawable.pizza,80),
            new Food("Салат «Греческий»","Это вкусное, сытное, а также полезное блюдо, приготовленное из свежайших ингредиентов",R.drawable.greches,40),
            new Food("Блины","Это вкусное, сытное, а также полезное блюдо, приготовленное из свежих продуктов",R.drawable.bliny,30)};


    private Food(String name, String description, int imageResorceId,int cost){
        this.name=name;
        this.description=description;
        this.imageResorceId=imageResorceId;
        this.cost=cost;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getImageResorceId() {
        return imageResorceId;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getCost() {
        return cost;
    }
}
