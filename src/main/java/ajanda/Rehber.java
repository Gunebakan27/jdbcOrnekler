package ajanda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rehber {
	static Data dataObj=new Data();
	
	private String kisiAdSoyad;
	private String kisiTel;
	private String kisiEmail;
	private String kisiAdres;
	
	public static List<Rehber> rehberListesi = new ArrayList<>();
	
	public Rehber() {
		
	}

	public Rehber(String rhbAdSoyad, String rhbTel, String rhbEmail, String rhbAdres) {
		super();
		this.kisiAdSoyad = rhbAdSoyad;
		this.kisiTel = rhbTel;
		this.kisiEmail = rhbEmail;
		this.kisiAdres = rhbAdres;
	}


	public String getKisiAdSoyad() {
		return kisiAdSoyad;
	}

	public void setKisiAdSoyad(String kisiAdSoyad) {
		this.kisiAdSoyad = kisiAdSoyad;
	}

	public String getKisiTel() {
		return kisiTel;
	}

	public void setKisiTel(String kisiTel) {
		this.kisiTel = kisiTel;
	}

	public String getKisiEmail() {
		return kisiEmail;
	}

	public void setKisiEmail(String kisiEmail) {
		this.kisiEmail = kisiEmail;
	}

	public String getKisiAdres() {
		return kisiAdres;
	}

	public void setKisiAdres(String kisiAdres) {
		this.kisiAdres = kisiAdres;
	}

	public void rehberMenu(){

		Scanner scan = new Scanner(System.in);
		System.out.println(
				     "╔═════════════════════════╗\r\n"
				  + "║  REHBER (KISI) MENUSU     ║\r\n"
				  + "╠═════════════════════════╣\r\n"
				  + "║ 1- KISI EKLE                         ║\r\n"
				  + "║ 2- KISI SİL                            ║\r\n"
				  + "║ 3- KISI DUZENLE                 ║\r\n"
				  + "║ 4- KISI BILGILERI                 ║\r\n"
				  + "║ 5- ANA MENUYE DON       ║\r\n"
				  + "╚════════════════════════╝\r\n"
				+ "Lütfen yapmak istenilen işlem numarasını giriniz");
		try {
			int secim = scan.nextInt();

			switch (secim) {
			case 1:
				rehberKisiEkle();
				break;

			case 2:
				rehberKisiSil();
				break;

			case 3:
				rehberDuzenleme();
				break;

			case 4:
				rehberKisiBilgileri();
				break;

			case 5:
				Ajanda.ajandaMenu();
				break;

			default:
				break;
			}
			scan.close();
		} catch (Exception e) {
			Ayarlar.bildirimYaz("LUTFEN MENU DISINDA BIR GIRIS YAPMAYINIZ");
			rehberMenu();
		}
	}


	private void rehberKisiBilgileri() throws SQLException, ClassNotFoundException {
		dataObj.tabloGoster("rehber");
		if (!rehberListesi.isEmpty()) {
			for (int i = 0; i < rehberListesi.size(); i++) {
				System.out.println((i + 1) + ". Kişi : " + rehberListesi.get(i).toString());
			}
			
		}else {
			Ayarlar.bildirimYaz("REHBERDE KAYITLI  KISI YOK");
			rehberMenu();
		}
		
		rehberMenu();
		
		
	}


	private void rehberDuzenleme() throws SQLException {
		
		if (!rehberListesi.isEmpty()) {
			for (int i = 0; i < rehberListesi.size(); i++) {
				System.out.println((i + 1) + ". Kişi : " + rehberListesi.get(i).toString());
			}
			dataObj.tabloGoster("Rehber");
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Lutfen duzenlemek istediginiz kisinin nosunu giriniz \nÇıkış için Sıfır (0) basınız");
			int secim1 = scan.nextInt();

			if (secim1 == 0) {
				rehberMenu();
			}

			if (secim1 > rehberListesi.size() || secim1 < 0) {
				System.out.println("Lütfen Listeden birini seçin");
				rehberDuzenleme();
			}

			System.out.println(""
					+ "╔══════════════════════════╗\r\n"
					+ "║ REHBER DUZENLEME MENU ║\r\n"
					+ "╠══════════════════════════╣\r\n" 
					+ "║ 1- Kisi Adi Soyadı      			  ║\r\n"
					+ "║ 2- Kisi Telefonu        			  ║\r\n" 
					+ "║ 3- Kisi E-Mail Adresi  			  ║\r\n"
					+ "║ 4- Posta Adresi       				  ║\r\n" 
					+ "║ 5- Rehber Menusune Git   	  ║\r\n" 
					+ "╚══════════════════════════╝\r\n"
					+ "Lutfen yapmak istenilen islem numarasini giriniz");

			int secim = scan.nextInt();

			switch (secim) {
			case 1:
				System.out.println("Kisinin Yeni Adini Soyadini Girin");
				String ad=scan.nextLine();
				ad=scan.nextLine();
				dataObj.degiskenIcerigiGuncelle("rehber", "adSoyad",  rehberListesi.get(secim1 - 1).kisiAdSoyad,ad) ;
				rehberListesi.get(secim1 - 1).kisiAdSoyad = ad;
				
//				System.out.println(new String(new char[70]).replace("\0", "\r\n"));	//Ekranı temizleme
				
				Ayarlar.bildirimYaz("KISI DUZENLEMESI BASARILI YAPILMISTIR.");
				
				rehberDuzenleme();
				
				break;

			case 2:
				System.out.println("Kisinin Yeni Telefonunu Girin");
				String tel=scan.nextLine();
				tel=scan.nextLine();
				dataObj.degiskenIcerigiGuncelle("rehber", "kisiTel", rehberListesi.get(secim1 - 1).kisiTel, tel) ;
				rehberListesi.get(secim1 - 1).kisiTel = tel;
			
				rehberDuzenleme();

				break;

			case 3:
				System.out.println("Kisinin Yeni e-mail adresini Girin");
				String yeniKisiEmail = scan.nextLine();

				while (!yeniKisiEmail.contains("@") || !yeniKisiEmail.contains(".")) {

					System.out.println("Lütfen email adresini doğru giriniz. '@' veya '.' karakterlerine dikkat ediniz.");
					String TkrkisiEmail = scan.nextLine();
					yeniKisiEmail = TkrkisiEmail;
				}
				dataObj.degiskenIcerigiGuncelle("rehber", "kisiEmail", rehberListesi.get(secim1 - 1).kisiEmail, yeniKisiEmail) ;
				rehberListesi.get(secim1 - 1).kisiEmail = yeniKisiEmail;
				rehberListesi.get(secim1 - 1).kisiEmail = yeniKisiEmail;
				rehberDuzenleme();
				break;

			case 4:
				System.out.println("Kisinin Yeni Adres Bilgilerini girin");
				String adres=scan.nextLine();
				adres=scan.nextLine();
				dataObj.degiskenIcerigiGuncelle("rehber", "kisiAdres", rehberListesi.get(secim1 - 1).kisiAdres, adres) ;
				rehberListesi.get(secim1 - 1).kisiAdres = adres;
			
				rehberDuzenleme();

				break;

			case 5:
				rehberMenu();
				break;

			default:
				rehberDuzenleme();
				break;
			}

			scan.close();

		} catch (Exception e) {
			Ayarlar.bildirimYaz("LUTFERN MENU DISINDA BIR SECIM YAPMAYIN");
			rehberDuzenleme();

			
		}	
			
	
		}else {
			Ayarlar.bildirimYaz("REHBERDE KAYITLI  KISI YOK");

			rehberMenu();
		}
	}


	public void rehberKisiEkle() throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Ad Soyadı giriniz ");
		String kisiAdSoyad = scan.nextLine();

		if (Data.adSoyad.contains(kisiAdSoyad)) {
			System.out.println("Girilen Ad Soyad kayıtlı kişi var. Lütfen başka bir isim ile kayıt ekleyiniz.");
			rehberKisiEkle();
		}

		System.out.println("Telefon numarası giriniz ");
		String kisiTelefon = scan.nextLine();

		System.out.println("e-mail adresini giriniz ");
		String kisiEmail = scan.nextLine();
		
		while (!kisiEmail.contains("@") || !kisiEmail.contains(".")) {

			System.out.println("Lütfen email adresini doğru giriniz. '@' veya '.' karakterlerine dikkat ediniz.");
			String TkrkisiEmail = scan.nextLine();
			kisiEmail = TkrkisiEmail;
			
		}
		

		System.out.println("Adres Bilgilerini giriniz ");
		String kisiAdres = scan.nextLine();

		Rehber rehberObj = new Rehber(kisiAdSoyad, kisiTelefon, kisiEmail, kisiAdres);
		dataObj.tabloyaVeriEkle("rehber",kisiAdSoyad, kisiTelefon, kisiEmail, kisiAdres);
		
		rehberListesi.add(rehberObj);

//		System.out.println(new String(new char[70]).replace("\0", "\r\n"));
		
		Ayarlar.bildirimYaz("KISI REHBERE BASARI ILE EKLENMISTIR");
		dataObj.tabloGoster("Rehber");
		for (int i = 0; i < rehberListesi.size(); i++) {

			System.out.println((i + 1) + ". " + rehberListesi.get(i).toString());
		}

		rehberMenu();

		scan.close();
	}


	private void rehberKisiSil() throws SQLException {

		if (!rehberListesi.isEmpty()) {
			for (int i = 0; i < rehberListesi.size(); i++) {
				System.out.println((i + 1) + ". Kişi : " + rehberListesi.get(i).toString());
			}
			dataObj.tabloGoster("Rehber");
			Scanner scan = new Scanner(System.in);
			System.out.println("Rehberden Silmek istediginiz kisinin adini giriniz ");

			String kisiAdiSec=scan.nextLine();
			dataObj.degiskenYazdir("rehber", "adSoyad", kisiAdiSec);
			dataObj.degiskenSil("Rehber", "adSoyad", kisiAdiSec);
			rehberMenu();
			for (Rehber each : rehberListesi) {			
				if (each.kisiAdSoyad.equalsIgnoreCase(kisiAdiSec)) {
					System.out.println(each);
					rehberListesi.remove(each);
					Ayarlar.bildirimYaz("KISI REHBERDEN BASARILI OLARAK SILINDI");
					rehberMenu();
				}
				
		}
			
//			int secim = Integer.valueOf(kisiAdiSec);
//			if (secim < rehberListesi.size() || secim > 0) {
//				System.out.println(rehberListesi.remove(secim - 1).toString() + "\n " + secim
//						+ ". numarali Kisi Basari ile silinmistir.");
//				for (int i = 0; i < rehberListesi.size(); i++) {
//					System.out.println((i + 1) + ". Kişi : " + rehberListesi.get(i).toString());
//				}
//				rehberMenu();
//			}
		} else {
			Ayarlar.bildirimYaz("REHBERDE KAYITLI  KISI YOK");

			rehberMenu();
		}
	}


	@Override
	public String toString() {
		return   "\n Adı Soyadı    : " + kisiAdSoyad+ " ║ "
				+" Telefonu      : " + kisiTel + " ║ "
				+" E-mail Adresi : " + kisiEmail + " ║ "
				+" Adresi        : " + kisiAdres + " ║ ";
	}
}
