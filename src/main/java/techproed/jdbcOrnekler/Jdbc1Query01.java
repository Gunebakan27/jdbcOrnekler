package techproed.jdbcOrnekler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc1Query01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
// 1) ilgili driver i yüklemeliyiz. Örnek: TVnin fisini tak, baska alet calismasin, ne calisacagini bilsin
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
// 2) Baglanti olusturmaliyiz. Örnek: Uydu sifrelerini girmeliyiz
		
		Connection con = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
		
// 3) SQL komutlari icin bir statement nesnesi olustur. Örnek: Her kanal icin kanal no atamak gibi
		
		Statement st=con.createStatement();
		
// 4) SQL ifadeleri yazip calistirabiliriz. Örnek: kumanda da 1e basinca trt1 gelir
		
		ResultSet veri=st.executeQuery("select isim,maas from personel where id=123456789");
//		ResultSet veri1=st.executeQuery("select * from personel");
		
// 5) Sonuclari aldik ve isledik
		while (veri.next()) {
			System.out.println(veri.getString("isim")+veri.getInt("maas"));
			System.out.println("Personel Adi : "+veri.getString(1)+"\nMaas : "+veri.getInt(2));
			
		}
// 6) olusturulan nesneleri bellekten kaldiralim
		con.close();
		st.close();
		veri.close();
		
	}

}
