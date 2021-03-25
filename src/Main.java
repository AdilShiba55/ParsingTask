import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        int numberForQuery = 0; //Число подставляется в запросе. Интервал должен быть строго в 12
        List<Product> productList = new ArrayList<>(); //Лист, куда мы будем складывать все Товары
        boolean hardMode = false; //Если true - Товар будет содержать Лист с комментариями(если они у него имеются). Внимание! Занимает гораздо больше времени.
        int productCount = 100; //Нужное количество Товаров

        Date start = new Date();

        String query = String.format(Constants.MY_QUERY, numberForQuery);
        String pageHTML = getHTMLDocument(query);

        while (productList.size() < productCount) {

            Matcher matcher = Pattern.compile("\"productId\"(.*?)\"gmtCreate\"").matcher(pageHTML); //Беру блок каждого товара по очереди

            while (matcher.find()) {
                String currentPart = matcher.group();

                long productId = Long.parseLong(getValues(currentPart, "productId"));
                String unActualMinPrice = getValues(currentPart, "oriMinPrice");
                String unActualMaxPrice = getValues(currentPart, "oriMaxPrice");
                String productTitle = getValues(currentPart, "productTitle");
                String actualMinPrice = getValues(currentPart, "minPrice");
                String actualMaxPrice = getValues(currentPart, "maxPrice");
                int discount = Integer.parseInt(getValues(currentPart, "discount"));
                int orders = Integer.parseInt(getValues(currentPart, "orders"));
                String productImageLink = getValues(currentPart, "productImage");
                String productDetailLink = getValues(currentPart, "productDetailUrl");
                double productRate = Double.parseDouble(getValues(currentPart, "productPositiveRate"));
                double productAverageStar = Double.parseDouble(getValues(currentPart, "productAverageStar"));

                Product product = new Product(productId, unActualMinPrice, unActualMaxPrice, productTitle, actualMinPrice,
                        actualMaxPrice, discount, orders, productImageLink, productDetailLink, productRate, productAverageStar, hardMode);

                System.out.println(product);
                for(Comment comment : product.getComments()) {
                    System.out.println(comment.toString());
                }


                productList.add(product);

                if (productList.size() == productCount) {
                    break;
                }
            }

            numberForQuery += 12;
        }

        Date finish = new Date();

        System.out.println("Конечный Размер Листа с Товарами: " + productList.size());

        long time = (finish.getTime() - start.getTime()) / 1000;

        System.out.println("Заняло: " + time);
    }


    public static String getValues(String currentPartOfHTMLCode, String keyName) { //Метод, вытаскивающий данные-значение по ключу. Кладем Блок данных Товара и название ключа.
        int firstIndex = currentPartOfHTMLCode.indexOf("\"" + keyName + "\"");
        int secondIndex = currentPartOfHTMLCode.indexOf(",", firstIndex);
        return currentPartOfHTMLCode.substring(firstIndex + keyName.length() + 3, secondIndex).replaceAll("\"", "").replaceAll("//", "");
    }


    public static String getHTMLDocument(String pageLink) {
        StringBuilder builder = new StringBuilder();

        HttpURLConnection urlConnection = null; //Для видимости в блоке finally.
        try {
            URL url = new URL(pageLink);
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Thread.sleep(5000);
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append("\n");
                line = reader.readLine();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return builder.toString();
    }
}
