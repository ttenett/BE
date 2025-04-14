package com.dayone;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);

        try {
            // connect 메소드는 connection이라는 인스턴스를 반환하게 됨.
            Connection connection = Jsoup.connect("https://finance.yahoo.com/quote/COKE/history/?frequency=1mo&period1=99153000&period2=1744608043");
            Document document = connection.get();

            // 파라미터 키 밸류는 야후 파이낸셜에서 콘솔창 켜고 html속성을 보면서 찾으면 됨.
            // 이 속성을 가진 엘리먼트가 한개가 아닐 수 있기 때문에 복수형.
            Elements eles = document.getElementsByAttributeValue("data-test", "historical-prices");
            // 그런 경우는 여러 엘리먼트를 찾아보기 때문에,
            // 엘리먼트 타입 반환함. 우리의 경우는 히스토리테이블 하나만 가져올거기 때문에 get(0) 리스트와 동일.
            Element ele = eles.get(0); // table 전체

            // thead는 (0) , tfoot 은(2)
            Element tbody = ele.children().get(1);
            for (Element e : tbody.children()) {
                String txt = e.text();
                if (!txt.endsWith("Dividend")) {
                    continue;
                }

                String[] splits = txt.split(" ");
                String month = splits[0];
                int day = Integer.valueOf(splits[1].replace("," ,""));
                int year = Integer.valueOf(splits[2]);
                String dividend = splits[3];

                System.out.println(year + "/" + month + "/" + day + " -> " + dividend);
            }

            // 이상태에서 출력 해보기
            System.out.println(ele);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
