package es.exsample;

public class KakeiboItem {
    private String category;
    private int amount;

    public KakeiboItem(int category, int amount) {
        this.category = String.valueOf(category);
        this.amount = amount;
    }

    public KakeiboItem(String category, Object amout) {
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }
}
