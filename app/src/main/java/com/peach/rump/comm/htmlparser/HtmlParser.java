package com.peach.rump.comm.htmlparser;

import com.peach.rump.bean.ObservationData;
import com.peach.rump.bean.Pic;
import com.peach.rump.comm.url.HttpConstant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/6/20.
 * email : huangjinping@hdfex.com
 */


//  git init
//          git add README.md
//          git commit -m "first commit"
//          git remote add origin git@github.com:huangjinping/PeachRump.git
//        git push -u origin master
//
//git remote rm origin


public class HtmlParser {
//    https://bbb397.com/htm/index.htm
    //    http://1024.stv919.rocks/pw/thread.php?fid=16
//    http://1024.stv919.rocks/pw/thread.php?fid=16
    public static void main(String[] paramArrayOfString) {
//        parserRockIndex(HttpConstant.ROCKS);
//        parserParentpic("http://1024.stv919.rocks/pw/thread.php?fid=15");

        parserYinqi(HttpConstant.YINQI_91);
        parserRun("https://bbb397.com/htm/index.htm");

    }

    public  static void  parserRun(String parseUrl){
        try {
            Document document = null;
            document = Jsoup.connect(parseUrl).timeout(50000).get();
            Elements elements= document.select("a");
            System.out.println(elements);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static List<ObservationData> parserParentpic(String parseUrl) {
        List<ObservationData> dataList = new ArrayList<>();
        try {
            Document document = null;
            document = Jsoup.connect(parseUrl).timeout(50000).get();
            String base = document.select("base#headbase").attr("href");
            Elements url = document.select("table").select("a");
            for (int i = 0; i < url.size(); i++) {
                Element element = url.get(i);
                String id = element.attr("id");
                if (id.startsWith("a_ajax_") && element.attr("href").endsWith(".html")) {
                    String href = element.attr("href");
                    ObservationData observationData = new ObservationData();
                    observationData.setTitle(element.text());
                    observationData.setUrl(base + href);
                    System.out.println(element.text() + base + href);
                    dataList.add(observationData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }


    public static List<Pic> parserpic(String parseUrl) {
        List<Pic> picList = new ArrayList<>();
        try {
            Document document = null;
            document = Jsoup.connect(parseUrl).timeout(50000).get();
            String base = document.select("base#headbase").attr("href");
            Elements url = document.select("div.tpc_content").select("img");
            for (int i = 0; i < url.size(); i++) {
                Element element = url.get(i);
                String id = element.attr("src");
//                System.out.println(id);
                Pic pic = new Pic();
                pic.setUrl(id);
                picList.add(pic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return picList;

    }


    public static List<ObservationData> parserRockIndex(String parseUrl) {
        List<ObservationData> dataList = new ArrayList<>();

        try {
            Document document = null;
            document = Jsoup.connect(parseUrl).timeout(50000).get();
            String base = document.select("base#headbase").attr("href");
            System.out.println(base);
            Elements url = document.select("table").select("a");
            for (int i = 0; i < url.size(); i++) {
                Element element = url.get(i);
                String href = element.attr("href");
                if (href.startsWith("thread.php?fid=") && element.text().length() > 1) {
                    ObservationData data = new ObservationData();
                    data.setTitle(element.text());
                    data.setUrl(base + href);
                    dataList.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(dataList.toString());
        return dataList;
    }

    public static List<ObservationData> parserYinqi(String parseUrl) {
        List<ObservationData> dataList = new ArrayList<>();
        try {
            Document document = null;
            document = Jsoup.connect(parseUrl).timeout(50000).get();
            Elements base = document.getElementsByAttributeValue("class", "subject new");

            for (int i = 0; i < base.size(); i++) {
                Elements a = base.select("a");
                System.out.println(a);
//                if (a.text().length()>2){
//                    System.out.println(a.attr("href"));
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(dataList.toString());
        return dataList;
    }
}