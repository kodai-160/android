package es.exsample;

//リストに提示する項目を扱うクラス
public class SampleListItem {
    private long id = 0;  //項目のID
    private String title = null;  //項目のタイトル
    private String tag = null;  //項目のタグ
    private String desc = null;  //項目の説明
    //get系メソッド
    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getTag() { return tag; }
    public String getDesc() { return desc; }
    //set系メソッド
    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setTag(String tag) { this.tag = tag; }
    public void setDesc(String desc) { this.desc = desc; }
}
