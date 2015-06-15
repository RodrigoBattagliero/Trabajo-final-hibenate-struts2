
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rodrigo
 */
public class ConexionKakan {
    private static ConexionKakan instance;
    private Connection cnn;
    
    private ConexionKakan(){
        try{
            Class.forName("org.postgresql.Driver");
            cnn = (Connection) DriverManager.getConnection("jdbc:postgresql://170.210.152.43:5432/kakan", "ogagtd", "rorodrigo");
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Error: " +e.getMessage());
        }
    }
    
    public synchronized static ConexionKakan estado(){
        if(instance == null) instance = new ConexionKakan();
        return instance;
    }
    
    public Connection getCnn(){
        return cnn;
    }
    
    public void closeConecxion(){
        instance = null;
    }
    
}
