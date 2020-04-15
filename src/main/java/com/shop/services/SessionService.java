package com.shop.services;

import com.shop.model.persistence.ISessionRepository;
import com.shop.model.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class SessionService{

    private final String COOKIE = "cookieFile.txt";
    private String cookieContent;
    private final String COOKIE_PATH = ".\\";

    @Autowired
    ISessionRepository sessionRepository;

    public void writeCookieFile (Session pSession){

        this.cookieContent = pSession.getSessionCustomer().getEmailAddress() + "\n" + pSession.getId();
        try{

            File cookie = new File(COOKIE);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(cookie));

            bufferedWriter.write(cookieContent);
            bufferedWriter.close();

        }catch (Exception e){

            System.out.println("LOG: " + e.getMessage());
        }

    }

    public String readEmailFromCookie() throws IOException {

        File file = new File(COOKIE);
        BufferedReader readCookie = new BufferedReader(new FileReader(file));
        String cookie = readCookie.readLine();
        readCookie.close();

        return cookie;
    }

    public void deleteCookie(){

        new File(COOKIE_PATH + COOKIE).delete();
    }

    public boolean checkIfCookieExists(){

        return new File(COOKIE_PATH + COOKIE).exists();
    }

    public static String apply(Session s) {

        return s.getSessionCustomer().getEmailAddress();
    }

}
