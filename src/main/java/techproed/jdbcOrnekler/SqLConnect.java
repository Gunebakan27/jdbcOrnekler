package techproed.jdbcOrnekler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ajanda.Etkinlik;
import ajanda.Rehber;

public class SqLConnect {
static Statement st;
static Connection con;
static ResultSet tablo;
static ResultSetMetaData rsmd;

	public void kodRun() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");	
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
		st=con.createStatement();
	}
	public void tabloCreate(String tabloAdi,String degiskenler) throws SQLException {
		String olustur="create table "+tabloAdi +degiskenler;
		st.execute(olustur);
	}
	public void tabloInsert(String tabloAdi, String...degiskenler) throws SQLException {
		String numOfvalues="";
		for (int i = 0; i < degiskenler.length; i++) {
			numOfvalues+="?,";
		}
		numOfvalues=numOfvalues.substring(0, numOfvalues.length()-1);
		PreparedStatement veri=con.prepareStatement("insert into "+tabloAdi+" values("+numOfvalues+")");	
		for (int i = 0; i < degiskenler.length; i++) {		
			veri.setString((i+1), degiskenler[i]);	
		}
		veri.addBatch();	
		veri.executeBatch();
	}
	public void degiskenIcerigiGuncelle(String tabloAdi, String degiskenAdi,String eskiDeger, String yeniDeger) throws SQLException {
		st.executeUpdate("update "+tabloAdi+" set "+degiskenAdi+" ='"+yeniDeger+"'"+" where "+degiskenAdi+"='"+eskiDeger+"'");
	}
	public void sayisalDegiskenGuncelle(String tabloAdi, String degiskenAdi,String eskiDeger, String yeniDeger) throws SQLException {
		st.executeUpdate("update "+tabloAdi+" set "+degiskenAdi+" ="+yeniDeger+""+" where "+degiskenAdi+"='"+eskiDeger+"'");
	}
	public void degiskenSil(String tabloAdi, String degiskenAdi, String silinecekDegiskenDegeri) throws SQLException {
		st.execute( "delete from "+tabloAdi +" where "+degiskenAdi+"='"+silinecekDegiskenDegeri+"'");	
	}
	public void tabloSil(String tabloAdi) throws SQLException {
		st.execute("Drop table "+tabloAdi);
	}
	public void degiskenYazdir(String tabloAdi, String degiskenAdi, String degiskenDegeri) throws SQLException {
		tablo=st.executeQuery("select * from "+tabloAdi+" where "+degiskenAdi+"='"+degiskenDegeri+"'");
		rsmd=tablo.getMetaData();
		int sutunSayisi=rsmd.getColumnCount();
		while (tablo.next()) {      
			for(int i = 1 ; i <= sutunSayisi; i++){
			      System.out.print(tablo.getRow()+" ║ "+ tablo.getObject(i) + " ║ "); 
			}
		} System.out.println();
	}
	public void tabloYazdir(String tabloAdi) throws SQLException {
		String select="select * from "+tabloAdi;
		tablo=st.executeQuery(select);
		rsmd=tablo.getMetaData();
		int sutunSayisi=rsmd.getColumnCount();
		while (tablo.next()) {      
			for(int i = 1 ; i <= sutunSayisi; i++){
			      System.out.print(tablo.getRow()+" ║ "+tablo.getObject(i) + " ║ "); 
			}
			  System.out.println();         
		}
	}
	
}
