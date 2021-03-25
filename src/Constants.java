import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String PAGE_LINK = "https://flashdeals.aliexpress.com/en.htm?";
    public static final String MY_QUERY = "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery183025577889709553114_1615386484711&widget_id=5547572&platform=pc&limit=12&offset=%s&phase=1&productIds2Top=&postback=3f9f55f1-400d-475a-97ff-7f9d3f383604&_=1615386487662";
    public static final String COMMENTS_QUERY = "https://feedback.aliexpress.ru/display/productEvaluation.htm?v=2&productId=%s&ownerMemberId=228754193&companyId=238300534&memberType=seller&startValidDate=&i18n=true";

    public static final List<String> DATA_KEYS_NAME = Arrays.asList( //Название нужных мне ключей в полученном JSON'е
                                                                    "productId",
                                                                    "oriMinPrice",
                                                                    "oriMaxPrice",
                                                                    "productTitle",
                                                                    "minPrice",
                                                                    "maxPrice",
                                                                    "discount",
                                                                    "orders",
                                                                    "productImage",
                                                                    "productDetailUrl",
                                                                    "productPositiveRate",
                                                                    "productAverageStar");

    // ==================== Разбиение JSON данных с помощью Библиотеки org.jason =====================
    // Занимает чуть - чуть больше времени, чем самостоятельная работа со строками, поэтому, продолжать не стал, но оставлю здесь. На всякий случай.
    //            Matcher matcher = Pattern.compile("\"productTitle\":\"(.*?)\",").matcher(pageHTML);

//            System.out.println(pageHTML.substring(pageHTML.indexOf("\"productTitle\":\"")));
//            pageHTML = pageHTML.substring(pageHTML.indexOf("(") + 1, pageHTML.lastIndexOf(");"));
//
//            JSONObject jsonObject = new JSONObject(pageHTML);
//            JSONArray array = jsonObject.getJSONArray("results");
//            for (Object object : array) {
//                JSONObject json = (JSONObject) object;
//                System.out.println(json.getString("productTitle"));
//            }

}
