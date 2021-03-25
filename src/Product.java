import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product {
    private long productId;
    private String unActualMinPrice;
    private String unActualMaxPrice;
    private String productTitle;
    private String actualMinPrice;
    private String actualMaxPrice;
    private int discount;
    private int orders;
    private String productImageLink;
    private String productDetailLink;
    private double productRate;
    private double productAverageStar;

    private List<Comment> comments = new ArrayList<>();


    public Product(long productId, String unActualMinPrice, String unActualMaxPrice, String productTitle, String actualMinPrice, String actualMaxPrice, int discount, int orders, String productImageLink, String productDetailLink, double productRate, double productAverageStar, boolean hardMode) {
        this.productId = productId;
        this.unActualMinPrice = unActualMinPrice;
        this.unActualMaxPrice = unActualMaxPrice;
        this.productTitle = productTitle;
        this.actualMinPrice = actualMinPrice;
        this.actualMaxPrice = actualMaxPrice;
        this.discount = discount;
        this.orders = orders;
        this.productImageLink = productImageLink;
        this.productDetailLink = productDetailLink;
        this.productRate = productRate;
        this.productAverageStar = productAverageStar;

        if(hardMode) {
            this.comments = uploadComment();
        }
    }

    private List<Comment> uploadComment() {
        String commentQuery = String.format(Constants.COMMENTS_QUERY, productId); //Подставляю ID Товара в запрос
//        System.out.println(commentQuery);
        String document = Main.getHTMLDocument(commentQuery);
        Document doc = Jsoup.parse(document);
        Elements mainContainer = doc.getElementsByClass("feedback-item clearfix"); //Получаю все div'ы с комментарием
//        System.out.println(mainContainer.size());

        if (mainContainer.size() <= 0) return Collections.emptyList(); //Если комментариев нет, у Товара будет пустой список

        List<Comment> commentList = new ArrayList<>();

        for (int i = 0; i < mainContainer.size(); i++) {
            String name = mainContainer.get(i).select("a[name=member_detail]").text();
//            System.out.println(name);

            String comment = mainContainer.get(i).select(".buyer-feedback span:first-child").text();
//            System.out.println(comment);

            String starCount = mainContainer.get(i).select(".star-view span[style]").attr("style").replaceAll("width:", "");
//            System.out.println(starCount);

            String date = mainContainer.get(i).select(".buyer-feedback span:last-child").text();
//            System.out.println(date);

            Elements imageLinks = mainContainer.get(i).select(".r-photo-list ul li img");
            List<String> links = new ArrayList<>(imageLinks.eachAttr("src"));
            Map<Integer, List<String>> map = new LinkedHashMap<>();
            map.put(i, links);
//            System.out.println(links.size());

            commentList.add(new Comment(name, comment, starCount, date, map));
        }

        return commentList;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getUnActualMinPrice() {
        return unActualMinPrice;
    }

    public void setUnActualMinPrice(String unActualMinPrice) {
        this.unActualMinPrice = unActualMinPrice;
    }

    public String getUnActualMaxPrice() {
        return unActualMaxPrice;
    }

    public void setUnActualMaxPrice(String unActualMaxPrice) {
        this.unActualMaxPrice = unActualMaxPrice;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getActualMinPrice() {
        return actualMinPrice;
    }

    public void setActualMinPrice(String actualMinPrice) {
        this.actualMinPrice = actualMinPrice;
    }

    public String getActualMaxPrice() {
        return actualMaxPrice;
    }

    public void setActualMaxPrice(String actualMaxPrice) {
        this.actualMaxPrice = actualMaxPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public String getProductImageLink() {
        return productImageLink;
    }

    public void setProductImageLink(String productImageLink) {
        this.productImageLink = productImageLink;
    }

    public String getProductDetailLink() {
        return productDetailLink;
    }

    public void setProductDetailLink(String productDetailLink) {
        this.productDetailLink = productDetailLink;
    }

    public double getProductRate() {
        return productRate;
    }

    public void setProductRate(double productRate) {
        this.productRate = productRate;
    }

    public double getProductAverageStar() {
        return productAverageStar;
    }

    public void setProductAverageStar(double productAverageStar) {
        this.productAverageStar = productAverageStar;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID Товара='" + productId + '\'' +
                ", Неактуальная Мин Цена='" + unActualMinPrice + '\'' +
                ", Неактульная Макс Цена='" + unActualMaxPrice + '\'' +
                ", Заголовок='" + productTitle + '\'' +
                ", Мин Цена='" + actualMinPrice + '\'' +
                ", Макс Цена='" + actualMaxPrice + '\'' +
                ", Скидка='" + discount + '\'' +
                ", Заказов='" + orders + '\'' +
                ", Картинка Товара='" + productImageLink + '\'' +
                ", Страница Товара='" + productDetailLink + '\'' +
                ", Рейтинг='" + productRate + '\'' +
                ", Средняя Оценка='" + productAverageStar + '\'' +
                '}';
    }
}
