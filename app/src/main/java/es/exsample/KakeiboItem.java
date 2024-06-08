package es.exsample;

public class KakeiboItem {
    private String category;
    private int amount;

    public KakeiboItem(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "KakeiboItem{category='" + category + "', amount=" + amount + '}';
    }
}
