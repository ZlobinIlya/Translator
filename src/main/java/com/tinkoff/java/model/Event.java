package com.tinkoff.java.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

public class Event implements Translatable {
    private static final String AND = "&";
    private static final String KEY = "trnsl.1.1.20190519T092157Z.ceecc02dc94cb0e9.7e9e61ed26bc1c0ffbb8ef3dc73811c873413b40";
    private static final String ADRESS = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
    private String ip;
    private Date date;
    private String lang;
    private String firstText;
    private String secondText;
    private String req;

    public Event(String ip, Date date, String lang, String firstText) {
        this.ip = ip;
        this.date = date;
        this.firstText = firstText;
        this.lang = lang;

    }

    public Event() {
    }

    public Event(String ip, String first_text) {
        this.ip = ip;
        this.firstText = first_text;
    }


    public void translate() throws IOException {

        req = ADRESS + "key=" + KEY + AND + "text=" + firstText + AND + "lang=" + lang;
        parse();

    }

    private void parse() throws IOException {
        String translateText = requestToYandex();


        Gson gson = new Gson();
        ParseResalt parseResalt = gson.fromJson(translateText, ParseResalt.class);
        String[] text = parseResalt.getText();
        setSecondText(text[0]);

    }

    private String requestToYandex() {
        HttpURLConnection connect = null;
        BufferedReader bufferedReader = null;
        StringBuilder strbild = new StringBuilder();
        try {
            connect = (HttpURLConnection) new URL(req).openConnection();
            connect.setRequestMethod("POST");
            connect.setUseCaches(false);
            connect.setConnectTimeout(200);
            connect.connect();
            String line;
            if (HttpURLConnection.HTTP_OK == connect.getResponseCode()) {
                bufferedReader = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                while ((line = bufferedReader.readLine()) != null) {
                    strbild.append(line);
                }
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connect != null) {
                connect.disconnect();
            }
        }
        return strbild.toString();
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFirstText() {
        return firstText;
    }

    public void setFirstText(String firstText) {
        this.firstText = firstText;
    }

    public String getSecondText() {
        return secondText;
    }

    public void setSecondText(String secondText) {
        this.secondText = secondText;
    }
}
