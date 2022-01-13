package techproed.jdbcOrnekler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlBaglan {
	static Statement st;
	static Connection con;
	static ResultSet veri1;
	static 	ResultSetMetaData rsmd;
	
	
public void sqlBagla() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");	
	con =DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
	st=con.createStatement();
}

public void tabloOlustur(String tabloAdi, String degiskenler) throws SQLException {	
	String tablo="CREATE TABLE "+tabloAdi+" "+degiskenler;	
	st.execute(tablo);
	System.out.println("tablo olustu");
}
public void tabloSil(String tabloAdi) throws SQLException {
	String sil="DROP TABLE "+tabloAdi;
	st.execute(sil);
	System.out.println("tablo silindi");	
}
public void tabloInsert(String tabloAdi, String...degiskenler) throws SQLException {
	String soruIsaretleri="";
	for (int i = 0; i < degiskenler.length; i++) {
		soruIsaretleri+="?,";
	}
	soruIsaretleri=soruIsaretleri.substring(0, soruIsaretleri.length()-1);
	
	PreparedStatement veri=con.prepareStatement("insert into "+tabloAdi +" values("+soruIsaretleri+")");
	
	for (int i = 0; i < degiskenler.length; i++) {
		veri.setString((i+1), degiskenler[i]);
	}	
		veri.addBatch();			
	veri.executeBatch();
	System.out.println("Eklendi");
}
public void tabloGoster(String tabloAdi) throws SQLException {
	veri1=st.executeQuery("select * from "+tabloAdi);
	rsmd=veri1.getMetaData();
	int sutunSayisi=rsmd.getColumnCount();
	while (veri1.next()) {      
		for(int i = 1 ; i <= sutunSayisi; i++){			
		      System.out.print(veri1.getObject(i) + "  "); 
		}
		  System.out.println();         
	}
}
public void degiskenIcerigiGuncelle(String tabloAdi, String degiskenAdi,String eskiDeger,String yeniDeger) throws SQLException {
   
    st.executeUpdate("UPDATE "+tabloAdi+" set "+degiskenAdi+"='"+yeniDeger+"' WHERE "+ degiskenAdi+"='"+eskiDeger+"'");
    
}
public void sayisalDegiskenGuncelle(String tabloAdi, String degiskenAdi,String eskiDeger,String yeniDeger) throws SQLException {
	 st.executeUpdate("UPDATE "+tabloAdi+" set "+degiskenAdi+"="+yeniDeger+" WHERE "+ degiskenAdi+"='"+eskiDeger+"'");
}
}

