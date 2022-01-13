package ajanda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import techproed.jdbcOrnekler.SqLConnect;



public class Data {
	SqLConnect sql=new SqLConnect();
	
	
	static double saatler[] = { 08.00, 09.00, 10.00, 11.00, 12.00, 13.00, 14.00, 15.00, 16.00, 17.00, 18.00, 19.00 };
	static List<String> etkinlikler = new ArrayList<>(Arrays.asList("Piknik", "Tatil", "Is Gorusmesi", "Ozel Gorusme", "Seyahat", "Ziyaret","Diger"));
	 static List<String>etkinlikTarihleri=new ArrayList<>(Arrays.asList("10.10.2010", "11.11.2011","12.12.12012"));
	 static List<String>etkinlikKimIerle=new ArrayList<>(Arrays.asList("onunla", "bununla","sununla"));
	 static List<String>etkinlikNotlari=new ArrayList<>(Arrays.asList("selam soyle", "beni arasin","katilamayacagim"));
	
	static List<String> adSoyad = new ArrayList<>(Arrays.asList("Ahmet CALISKAN", "Mehmet DOGRU", "Ayse Nur GUZEL", "Hasan ARKAS", "Dilara HANCI"));
	static List<String> telefon = new ArrayList<>(Arrays.asList("0 555 555 55 51", "0 555 555 55 52","0 555 555 55 53","0 555 555 55 54","0 555 555 55 55"));
	static List<String> eMail = new ArrayList<>(Arrays.asList("ahmetcaliskan@gmail.com", "mehmetdogru@hotmail.com","aysenurguzel@yandex.com","hasanarkas@gmail.com","dilarahanci@hanci.com"));
	static List<String> adres = new ArrayList<>(Arrays.asList("Paris/Fransa", "İstanbul/ TÜRKİYE", "Hatay / TÜRKİYE", "İzmir / TÜRKİYE", "Almanya/"));
	
	void sqlBaglan() throws ClassNotFoundException, SQLException {
		sql.kodRun();
	}
	
	void rehberTabloOlustur(String tabloAdi) throws SQLException, ClassNotFoundException {
		sql.kodRun();
		sql.tabloSil(tabloAdi);
		String tabloDegiskenleri=" "+"(adSoyad varchar(40), kisiTel varchar(20), kisiEmail varchar(40), kisiAdres varchar(40))"; //"(id int primary key, birim varchar(10), maas int)"--->bu sekilde tablo kodunu yaz..
		sql.tabloCreate(tabloAdi, tabloDegiskenleri);
//		System.out.println(tabloAdi+" tablosu basariyla olusturulmustur.");
	}
//	etkinlikAdi, etkinlikTarihi, etkinlikSaati, etkinlikKimIle, etkinlikNotu
	void etkinlikTabloOlustur(String tabloAdi) throws SQLException, ClassNotFoundException {
		sql.kodRun();
		sql.tabloSil(tabloAdi);
		String tabloDegiskenleri=" "+"(etkinlikAdi varchar(40), etkinlikTarihi varchar(20), etkinlikSaati double, etkinlikKimIle varchar(40), etkinlikNotu varchar(40))"; //"(id int primary key, birim varchar(10), maas int)"--->bu sekilde tablo kodunu yaz..
		sql.tabloCreate(tabloAdi, tabloDegiskenleri);
//		System.out.println(tabloAdi+" tablosu basariyla olusturulmustur.");
	}
//	void tabloyaVerileriYukle(String tabloAdi, List<Rehber>list) throws SQLException {	
//	sql.rehberTabloInsert(tabloAdi, list);
//	System.out.println(tabloAdi+" tablosuna veriler basariyla eklenmistir..");
//	}
	void tabloyaVeriEkle(String tabloAdi, String...degiskenler) throws SQLException {
		sql.tabloInsert(tabloAdi, degiskenler);
	}
	void tabloGoster(String tabloAdi) throws SQLException {
		sql.tabloYazdir(tabloAdi);
	}
	void degiskenYazdir(String tabloAdi, String degiskenAdi, String degiskenDegeri) throws SQLException {
		sql.degiskenYazdir(tabloAdi, degiskenAdi, degiskenDegeri);
	}
	void degiskenIcerigiGuncelle(String tabloAdi, String degiskenAdi, String eskiDeger, String yeniDeger) throws SQLException {
		sql.degiskenIcerigiGuncelle(tabloAdi, degiskenAdi, eskiDeger, yeniDeger);
	}
	void sayisalDegiskenGuncelle(String tabloAdi, String degiskenAdi, String yeniDeger, String eskiDeger) throws SQLException {
		sql.sayisalDegiskenGuncelle(tabloAdi, degiskenAdi, yeniDeger,eskiDeger);
	}
	void degiskenSil(String tabloAdi, String degiskenAdi, String silinecekDegiskenDegeri) throws SQLException {
		sql.degiskenSil(tabloAdi, degiskenAdi, silinecekDegiskenDegeri);
	}

}
