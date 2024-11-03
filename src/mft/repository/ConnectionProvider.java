package mft.repository;

import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;


//Singleton
public class ConnectionProvider {
    @Getter
    private static ConnectionProvider provider = new ConnectionProvider();
    private static BasicDataSource basicDataSource = new BasicDataSource();

    private ConnectionProvider() {
    }

//    public static ConnectionProvider getInstance() {
//        return connectionProvider;
//    }

    public Connection getConnection() throws SQLException {
        basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("javase");
        basicDataSource.setPassword("java123");

        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxTotal(20);
        basicDataSource.setMaxWaitMillis(30000);

        return basicDataSource.getConnection();
    }
}
