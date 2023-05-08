package db;

import java.sql.*;

public class WifiDbService {
    public void showAllWifi() {
        String url = "jdbc:mariadb://localhost:3306/public_wifi_search";
        String dbUserId = "wifiuser";
        String dbPassword = "zerobase";

        try {
            // 1. 드라이버 로드
            Class.forName("org.mariadb.jdbc.driver");
        } catch (ClassNotFoundException e) {
            e.getException();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String wifiName = "";
        float lat;
        float lnt;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select *" +
                    "from PUBLIC_WIFI_DATA";

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            // 5. SQL 실행 결과 처리
            while (rs.next()) {
                String xSwifiMgrNo= rs.getString("X_SWIFI_MGR-NO"); // 관리번호
                String xSwifiWrdofc= rs.getString("X_SWIFI_WRDOFC"); // 자치구
                String xSwifiMainNm= rs.getString("X_SWIFI_MAIN_NM"); // 와이파이명
                String xSwifiAdres1= rs.getString("X_SWIFI_ADRES1"); // 도로명 주소
                String xSwifiAdres2= rs.getString("X_SWIFI_ADRES2");// 상세 주소
                String xSwifiInstlFloor= rs.getString("X_SWIFI_INSTL_FLOOR"); // 설치위치(층)
                String xSwifiInstlTy= rs.getString("X_SWIFI_INSTL_TY"); // 설치유형
                String xSwifiInstlMby= rs.getString("X_SWIFI_INSTL_MBY"); // 설치기관
                String xSwifiSvcSe= rs.getString("X_SWIFI_SVC_SE"); // 서비스구분
                String xSwifiCmcwr= rs.getString("X_SWIFI_CMCWR"); //망종류
                String xSwifiCnstcYear= rs.getString("X_SWIFI_CNSTC_YEAR"); //설치년도
                String xSwifiInoutDoor= rs.getString("X_SWIFI_INOUT_DOOR"); // 실내외구분
                String xSwifiRemars3= rs.getString("X_SWIFI_REMARS3"); // wifi 접속환경
                float LAT= rs.getFloat("LAT"); //Y좌표
                float LNT= rs.getFloat("LNT"); //X좌표
                String workDttm = rs.getString("WORK_DTTM"); //작업일자

                System.out.println(xSwifiMgrNo + ", " + xSwifiWrdofc + ", " + xSwifiMainNm + ", " + xSwifiAdres1 + ", " + xSwifiAdres2 + ", " + xSwifiInstlFloor + ", " + xSwifiInstlTy + ", " + xSwifiInstlMby + ", " + xSwifiSvcSe + ", " + xSwifiCmcwr + ", " + xSwifiCnstcYear + ", " + xSwifiInoutDoor + ", " + xSwifiRemars3 + ", " + LAT + ", " + LNT + ", " + workDttm);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public void dbInsert(PublicWifiData publicWifiData) {
//        String url = "jdbc:mariadb://localhost:3306/testdb1";
//        String dbUserId = "testuser1";
//        String dbPassword = "zerobase";
//
//        try {
//            // 1. 드라이버 로드
//            Class.forName("org.mariadb.jdbc.driver");
//        } catch (ClassNotFoundException e) {
//            e.getException();
//        }
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet rs = null;
//
//
//        try {
//            // 2. 커넥션 객체 생성
//            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
//
//            // 쿼리 작성
//            // 각 문장마다 앞,뒤에 공백을 넣어줘야 실행할 때 띄어쓰기가 적용되어 정상적으로 작동한다.
//            String sql = " insert into member (member_type, user_id, password, name) "
//                    + "VALUES (?, ?, ?, ?); ";
//
//            // 3. 스테이트먼트 객체 생성
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, publicWifiData.getMemberType());
//            preparedStatement.setString(2, publicWifiData.getUserId());
//            preparedStatement.setString(3, publicWifiData.getPassword());
//            preparedStatement.setString(4, publicWifiData.getName());
//
//            // 4. 쿼리 실행.
//            int affected = preparedStatement.executeUpdate();
//
//            // 5. 결과 처리
//            if (affected > 0) {
//                System.out.println(" 저장 성공 ");
//            } else {
//                System.out.println(" 저장 실패 ");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // 6. 객체 연결 해제
//            // 연결 해제 부분은 중간에 오류가 나서 멈추더라도 꼭 이뤄져야 하므로 finally를 이용해 가장 마지막에 꼭 실행될 수 있도록 해준다.
//            try {
//                if (rs != null && !rs.isClosed()) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                if (preparedStatement != null && !preparedStatement.isClosed()) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                if (connection != null && !connection.isClosed()) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//    public void dbUpdate() {
//        String url = "jdbc:mariadb://localhost:3306/testdb1";
//        String dbUserId = "testuser1";
//        String dbPassword = "zerobase";
//
//        try {
//            Class.forName("org.mariadb.jdbc.driver");
//        } catch (ClassNotFoundException e) {
//            e.getException();
//        }
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet rs = null;
//
//        String memberTypeValue = "email";
//        String userIdValue = "zerobase@naver.com";
//        String passwordValue = "9999";
//
//        try {
//            // 2. 커넥션 객체 생성
//            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
//
//            // 쿼리 작성
//            String sql = "update member " +
//                    "set password = ? " +
//                    "where member_type = ? and user_id = ?;";
//
//            // 3. 스테이트먼트 객체 생성
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, passwordValue);
//            preparedStatement.setString(2, memberTypeValue);
//            preparedStatement.setString(3, userIdValue);
//
//            // 4. 쿼리 실행.
//            int affected = preparedStatement.executeUpdate();
//
//            // 5. 결과 처리
//            if (affected > 0) {
//                System.out.println("업데이트 성공");
//            } else {
//                System.out.println("업데이트 실패");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // 6. 객체 연결 해제
//            // 연결 해제 부분은 중간에 오류가 나서 멈추더라도 꼭 이뤄져야 하므로 finally를 이용해 가장 마지막에 꼭 실행될 수 있도록 해준다.
//            try {
//                if (rs != null && !rs.isClosed()) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                if (preparedStatement != null && !preparedStatement.isClosed()) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                if (connection != null && !connection.isClosed()) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//    public void dbDelete() {
//        String url = "jdbc:mariadb://localhost:3306/testdb1";
//        String dbUserId = "testuser1";
//        String dbPassword = "zerobase";
//
//        try {
//            Class.forName("org.mariadb.jdbc.driver");
//        } catch (ClassNotFoundException e) {
//            e.getException();
//        }
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet rs = null;
//
//        String memberTypeValue = "email";
//        String userIdValue = "zerobase@naver.com";
//
//        try {
//            // 2. 커넥션 객체 생성
//            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
//
//            // 쿼리 작성
//            String sql = "delete from member " +
//                    "where user_id = ? and member_type = ?;";
//
//            // 3. 스테이트먼트 객체 생성
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, userIdValue);
//            preparedStatement.setString(2, memberTypeValue);
//
//            // 4. 쿼리 실행.
//            int affected = preparedStatement.executeUpdate();
//
//            // 5. 결과 처리
//            if (affected > 0) {
//                System.out.println("삭제 성공");
//            } else {
//                System.out.println("삭제 실패");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // 6. 객체 연결 해제
//            // 연결 해제 부분은 중간에 오류가 나서 멈추더라도 꼭 이뤄져야 하므로 finally를 이용해 가장 마지막에 꼭 실행될 수 있도록 해준다.
//            try {
//                if (rs != null && !rs.isClosed()) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                if (preparedStatement != null && !preparedStatement.isClosed()) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                if (connection != null && !connection.isClosed()) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
