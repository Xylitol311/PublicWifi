package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DbTestMain {
    public static void main(String[] args) throws IOException {
        WifiDbService wifiDbService = new WifiDbService();
        wifiDbService.showAllWifi();
//        dbTest.dbUpdate();
//        dbTest.dbDelete();
//
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.println("Member Type 입력 : ");
//        String memberType = bf.readLine();
//        System.out.println("User Id 입력 : ");
//        String userId = bf.readLine();
//        System.out.println("Password 입력 : ");
//        String password = bf.readLine();
//        System.out.println("Name 입력 : ");
//        String name = bf.readLine();
//
//        PublicWifiData publicWifiData = new PublicWifiData();
//        publicWifiData.setMemberType(memberType);
//        publicWifiData.setUserId(userId);
//        publicWifiData.setPassword(password);
//        publicWifiData.setName(name);
//
//        wifiDbService.dbInsert(publicWifiData);



    }
}
