import java.util.*;

public class Comment {
    private String buyerName;
    private String comment;
    private String starCount;
    private String date;
    private Map<Integer, List<String>> commentsImagesList = new LinkedHashMap<>();

    public Comment(String buyerName, String comment, String starCount, String date, Map<Integer, List<String>> commentsImagesList) {
        this.buyerName = buyerName;
        this.comment = comment;
        this.starCount = starCount;
        this.date = date;
        this.commentsImagesList = commentsImagesList;
    }


    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<Integer, List<String>> getCommentsImagesList() {
        return commentsImagesList;
    }

    public void setCommentsImagesList(Map<Integer, List<String>> commentsImagesList) {
        this.commentsImagesList = commentsImagesList;
    }

    public String getStarCount() {
        return starCount;
    }

    public void setStarCount(String starCount) {
        this.starCount = starCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "Покупатель='" + buyerName + '\'' +
                ", Комментарии='" + comment + '\'' +
                ", Звезд='" + starCount + '\'' +
                ", Дата='" + date + '\'' +
                ", Ссылки на фотографии=" + commentsImagesList +
                '}';
    }
}
