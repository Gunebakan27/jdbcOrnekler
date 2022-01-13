package techproed.jdbcOrnekler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


public class Jdbc1Qery02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");	
				Connection con = 
						DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
				Statement st=con.createStatement();
				/*=======================================================================
//				 ORNEK1: Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.
//				========================================================================*/ 
				ResultSet veri1=st.executeQuery("select * from bolumler");
				ResultSetMetaData rsmd=veri1.getMetaData();
				int sutunSayisi=rsmd.getColumnCount();
				while (veri1.next()) {      
					for(int i = 1 ; i <= sutunSayisi; i++){
					      System.out.print(veri1.getObject(i) + "  "); 
					}
					  System.out.println();         
				}
				
			
//				while (veri1.next()) {			
//					System.out.println(veri1.getObject(1)+" "+veri1.getObject(2)+" "+veri1.getObject(3));				
//				}	
				System.out.println("=====================================");
		
				/*=======================================================================
//				 ORNEK2: SATIS ve MUHASEBE bolumlerinde calisan personelin isimlerini ve 
//		 		 maaslarini, maas ters sirali olarak listeleyiniz
//				========================================================================*/ 
				String sorgu="Select isim, maas from personel "
						+ "where bolum_id in(10,30) order by maas desc";
				ResultSet veri2=st.executeQuery(sorgu);
					while (veri2.next()) {				
					System.out.println(veri2.getRow()+" "+veri2.getObject(1)+" "+veri2.getObject(2));				
				}
					System.out.println("=====================================");
//					/*=======================================================================
//					  ORNEK3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini 
//					  ve maaslarini, bolum ve maas sirali listeleyiniz. NOT: calisani olmasa 
//					  bile bolum ismi gosterilmelidir.
//					========================================================================*/ 
String sorgu2="select  b.bolum_isim, p.isim, p.maas "
		+ "from bolumler b  left join personel p on b.bolum_id=p.bolum_id order by b.bolum_isim, p.maas";
ResultSet veri3=st.executeQuery(sorgu2);
while (veri3.next()) {				
System.out.println(veri3.getRow()+" "+veri3.getObject(1)+" "+veri3.getObject(2)+" "+veri3.getObject(3));				
}
System.out.println("=====================================");
/*=======================================================================
//ORNEK4: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz
//========================================================================*/ 
String sorgu3="select b.bolum_isim, p.isim, p.maas from personel p left join  "
		+ "bolumler b on b.bolum_id=p.bolum_id order by maas desc limit 10";
ResultSet veri4=st.executeQuery(sorgu3);
while (veri4.next()) {				
System.out.println(veri4.getRow()+" "+veri4.getObject(1)+" "+veri4.getObject(2)+" "+veri4.getObject(3));			
}


//con.close();
//st.close();
//veri1.close();
//veri2.close();
//veri3.close();
//veri4.close();
	}

}
