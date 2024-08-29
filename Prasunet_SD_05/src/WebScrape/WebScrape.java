package WebScrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static jdk.internal.org.jline.utils.InfoCmp.Capability.columns;

public class WebScrape {
    public static void main(String[] args) {
        final String url =
                "https://groww.in/stocks/sectors";

        try{
            final Document document = Jsoup.connect(url).get();

            System.out.printf("%-30s %-20s %-20s%n", "Name", "Market Price", "Closing Price");
            System.out.println("-----------------------------------------------------------------------");

            Elements rows = document.select("table.tb10Table tr");

            for (Element row : rows){
                Elements columns = row.select("td");
            if (columns.size() < 4){
                continue;
            }else{
                final String name = columns.get(0).text();
                final String marketPrice = columns.get(2).text(); // Assuming 3rd column is Market Price
                final String closingPrice = columns.get(3).text();
                System.out.printf("%-30s %-20s %-20s%n", name, marketPrice, closingPrice);
            }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
