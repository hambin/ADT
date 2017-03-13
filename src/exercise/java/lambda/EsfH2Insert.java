package exercise.java.lambda;
/**
 *
 */

import java.sql.*;
import java.util.List;
import java.io.*;
import java.util.UUID;

/**
 * <p>ClassName: H2ConnTest1<p>
 * <p>Description: Java通过JDBC方式连接H2数据库<p>
 *
 * @author xudp
 * @version 1.0 V
 * @createTime 2014-12-18 上午11:22:12
 */
public class EsfH2Insert {
    //数据库连接URL，通过使用TCP/IP的服务器模式（远程连接），当前连接的是E:/H2目录下的gacl数据库
//    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/D:/H2/gacl";
    //private static final String JDBC_URL = "jdbc:h2:tcp://127.0.0.1/E:/H2/gacl";
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/wcf_new";
    //连接数据库时使用的用户名
    private static final String USER = "sa";
    //连接数据库时使用的密码
    private static final String PASSWORD = "";
    //连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
    private static final String DRIVER_CLASS = "org.h2.Driver";

    public static void main(String[] args) throws Exception {
        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        //如果存在USER_INFO表就先删除USER_INFO表
        stmt.execute("DROP TABLE IF EXISTS BILLING_CSE_DETAIL");
//        stmt.execute("create Index BRAND_INDEX  on BILLING_CSE_DETAIL(BRAND,BRAND_DESC);" +
//                "create Index GRP5_INDEX  on BILLING_CSE_DETAIL(GRP5,GRP5_DESC);" +
//                "create Index L2_INDEX  on BILLING_CSE_DETAIL(L2,L2_DESC);" +
//                "create Index GRP1_INDEX  on BILLING_CSE_DETAIL(GRP1,GRP1_DESC);" +
//                "create Index L3_INDEX  on BILLING_CSE_DETAIL(L3,L3_DESC);" +
//                "create Index SOLAR_INDEX  on BILLING_CSE_DETAIL(SOLAR);" +
//                "create Index OFFICE_INDEX  on BILLING_CSE_DETAIL(OFFICE,OFFICE_DESC);" +
//                "create Index PAYER_INDEX  on BILLING_CSE_DETAIL(PAYER,PAYER_DESC);" +
//                "create Index DATE_INDEX  on BILLING_CSE_DETAIL(YEAR,MONTH);");
        //创建USER_INFO表
        stmt.execute("CREATE TABLE BILLING_CSE_DETAIL (\n" +
                "  ID int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  BRAND varchar(10) DEFAULT NULL,\n" +
                "  GRP5 varchar(10) DEFAULT NULL,\n" +
                "  L1 varchar(10) DEFAULT NULL,\n" +
                "  L2 varchar(20) DEFAULT NULL,\n" +
                "  GRP1 varchar(10) DEFAULT NULL,\n" +
                "  L3 varchar(30) DEFAULT NULL,\n" +
                "  MATERIAL varchar(20) DEFAULT NULL,\n" +
                "  SOLAR varchar(10) DEFAULT NULL,\n" +
//                "   YEAR varchar(10) DEFAULT NULL,\n" +
//                "\tMONTH varchar(10) DEFAULT NULL,\n" +
                "  OFFICE varchar(10) DEFAULT NULL,\n" +
                "  PAYER varchar(10) DEFAULT NULL,\n" +
                "  VALUE double DEFAULT NULL,\n" +
                "  PRIMARY KEY (ID),\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=4898009 DEFAULT CHARSET=utf8;");
     stmt.executeUpdate("INSERT INTO BILLING_CSE_DETAIL  ( SELECT  * FROM  CSVREAD('E:\\H2\\BILLING_CSE_DETAIL.csv'))");

//        //查询
//        ResultSet rs = stmt.executeQuery("SELECT ID ,SOLAR FROM BILLING_CSE_DETAIL");
//        //遍历结果集
//        while (rs.next()) {
//            Statement stmt1 = conn.createStatement();
//            System.out.println(rs.getString("ID"));
//            String date = rs.getString("SOLAR");
//            Integer year = Integer.parseInt(date.substring(0, 4));
//            Integer month = Integer.parseInt(date.substring(date.indexOf("/") + 1, date.lastIndexOf("/")));
//            stmt1.executeUpdate("UPDATE BILLING_CSE_DETAIL SET YEAR=" + year + ",MONTH=" + month + "WHERE id=" + rs.getString("ID") + "");
//
//
//////      for (int i=1;i<=1048575;i++){
//////          stmt.executeUpdate("UPDATE BILLING_CSE_DETAIL SET YEAR=(SELECT SUBSTRING(SOLAR,1,4) FROM BILLING_CSE_DETAIL WHERE id=1),MONTH=(SELECT SUBSTRING(SOLAR,1,4) FROM BILLING_CSE_DETAIL WHERE id=1)  WHERE id=1");
//////
//////      }
//     }


        //释放资源
        stmt.close();
        //关闭连接
        conn.close();
    }
}