package src.util;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by z on 2018/9/19.
 */
public class ConnectionManager {
    private ConnectionManager(){}
    private static Properties ps = new Properties();
    private static int maxSize = 10;
    private static List<Connection> pools = Collections.synchronizedList(new ArrayList<Connection>());

    public static Connection getConn() throws Exception{
        Connection conn = null ;
        if(pools.isEmpty()){
            loadProperties();
            String url = ps.getProperty("url");
            String user = ps.getProperty("username");
            String password = ps.getProperty("password");
            conn = DriverManager.getConnection(url,user,password);
        } else {
            conn = pools.remove(pools.size()-1);
        }
        return conn;
    }

    public static void closeConn(Connection conn) throws Exception {
        if(conn!=null) {
            if(pools.size()<maxSize){
                pools.add(conn);
            } else {
                conn.close();
            }
        }
    }

    private static void loadProperties() throws Exception {
        if (ps.isEmpty()) {
            InputStream is = ConnectionManager.class.getResourceAsStream("db.properties");
            ps.load(is);
            String className = ps.getProperty("driver");
            Class.forName(className);
        }
    }

    public static void main(String [] args) throws Exception{
        System.out.print(getConn());
    }
}

