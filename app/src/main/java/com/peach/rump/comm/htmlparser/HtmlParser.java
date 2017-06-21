package com.peach.rump.comm.htmlparser;

import com.peach.rump.comm.url.HttpConstant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * author Created by harrishuang on 2017/6/20.
 * email : huangjinping@hdfex.com
 */

public class HtmlParser {


    public static void main(String[] paramArrayOfString) {
        parserHtml(HttpConstant.ROCKS);

//        parserParentpic("http://1024.stv919.rocks/pw/thread.php?fid=15");
    }

    public static void parserParentpic(String parseUrl) {
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

                    parserpic(base + href);
                    System.out.println(base + href);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void parserpic(String parseUrl) {
        try {
            Document document = null;
            document = Jsoup.connect(parseUrl).timeout(50000).get();
            String base = document.select("base#headbase").attr("href");
            Elements url = document.select("div.tpc_content").select("img");
            for (int i = 0; i < url.size(); i++) {
                Element element = url.get(i);
                String id = element.attr("src");
                System.out.println(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void parserHtml(String parseUrl) {
        try {

            Document document = null;
            document = Jsoup.connect(parseUrl).timeout(50000).get();
            String base = document.select("base#headbase").attr("href");
            System.out.println(base);
            Elements url = document.select("table").select("a");

            for (int i = 0; i < url.size(); i++) {

                if ("网友自拍".equals(url.get(i).text())) {
                    String attr = url.get(i).attr("href");
                    System.out.println(base + attr);
                    parserHtml(base + attr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}