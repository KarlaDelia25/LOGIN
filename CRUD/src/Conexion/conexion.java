package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {




Connection cx = null;

public Connection conectar() {
try {
Class.forName("org.sqlite.JDBC");
cx = DriverManager.getConnection("jdbc:sqlite:Sistema.db");
System.out.println("CONEXION EXITOSA");
} catch (ClassNotFoundException e) {

e.printStackTrace();
} catch (SQLException e) {

e.printStackTrace();
}
return cx;

}

public void desconectar() {
try {
cx.close();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
public static void main(String[] args) {
conexion cx = new conexion();
cx.conectar();
}

}


