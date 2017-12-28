package com.verma.mobile.android.demoretrofitandroid.service.quotes;

/**
 * Created by verma on 26-12-2017.
 */

public class Quotes {
    //    http://quotesondesign.com/wp-json/posts?filter[orderby]=rand
    //[{"ID":1222,"title":"Don Norman","content":"<p>The inventors will invent, for that is what inventors do. The technology will come first, the products second, and then the needs will slowly appear, as new applications become luxuries, then &#8220;needs,&#8221; and finally, essential.  <\/p>\n","link":"https:\/\/quotesondesign.com\/don-norman-3\/"}]

    private int ID;
    private String title;
    private String content;
    private String link;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



}
