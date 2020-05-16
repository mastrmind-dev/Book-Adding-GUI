package librarymanagementsystem.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Sapthaka
 */
public class DatabaseHandler {
    
    private static DatabaseHandler handler;
    
    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public DatabaseHandler(){
        createConnection();
        setupBookTable();
    }

    void createConnection(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void setupBookTable(){
        String TABLE_NAME = "BOOK";
        try {
            stmt = conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME, null);
            
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists. Ready for go!");
            } else{
                stmt.executeUpdate("CREATE TABLE " + TABLE_NAME + "(" /**
                 * executeUpdate is used for all the queries except SELECT queries. If you are hesitated about what method to use, you can use just "execute"
                 * method for "all the queries". (see next comment, not so far, scroll a little bit)
                 */ 
                        +"id varchar(200) primary key,\n"
                        +"title varchar(200),\n"
                        +"author varchar(200),\n"
                        +"publisher varchar(100),\n"
                        +"isAvail boolean default true"
                        +")");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + " ... setupDatabase");
        }
        finally{           
        }
    }
    
    public ResultSet execQuery(String query){
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);//executeQuery is only for SELECT queries or you can use "execute".
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally{
        }
        return result;
    }
    public boolean execAction(String qu){
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        }
        finally{
        }
    }
}
