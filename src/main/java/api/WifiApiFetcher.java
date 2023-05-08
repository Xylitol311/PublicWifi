package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WifiApiFetcher {
    public static void main(String[] args) throws IOException {
        // 1. URL을 만들기 위한 StringBuilder
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        String apiKey = "4d4c477171636b74313032724f6a4441";
        /*URL*/
        // 2. 오픈 API 규격에 맞는 파라미터 생성, 발급받은 인증키
        urlBuilder.append("/" + URLEncoder.encode(apiKey,"UTF-8") ); /*인증키
(sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") ); /*요청파일타입
(xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치
(sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("1000","UTF-8"));
        /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//        urlBuilder.append("/" + URLEncoder.encode("서대문구","UTF-8")); /* 서비스별 추가 요청인자들*/

        // 3. URL 객체 생성
        URL url = new URL(urlBuilder.toString());

        //4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //5. 통신을 위한 메소드 SET
        conn.setRequestMethod("GET");
        //6. 통신을 위한 Content-type SET
        conn.setRequestProperty("Content-type", "application/json");
        //7. 통신 응답 코드 확인
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결
자체에 대한 확인이 필요하므로 추가합니다.*/

        //8. 전달받은 데이터를 BufferedReader 객체로 저장
        BufferedReader rd; // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        //9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        //10. 객체 해제
        rd.close();
        conn.disconnect();
        //11. 전달받은 데이터 String 형태로 저장
        String strResult = sb.toString();
        System.out.println(sb.toString());

        // 12. text로 저장된 응답메세지를 list 혹은 map으로 변환.
        // 응답 메세지가 map 타입이면 map으로, list 타입이면 새 map을 생성하여 "data"라는 키의 value로 넣어준다.
//        ObjectMapper
//
//        try {
//            Map<String, Object> map = mapper.readvalue(text)
//        }
    }
}
