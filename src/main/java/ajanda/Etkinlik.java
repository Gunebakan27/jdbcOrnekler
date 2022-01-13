package ajanda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Etkinlik {

	private String etkinlikAdi;
	private String etkinlikTarihi;
	private double etkinlikSaati;
	private String etkinlikKimIle;
	private String etkinlikNotu;

	static List<Etkinlik> etkinlikListesi = new ArrayList<>();
	static 	Data dataObj=new Data();
	public Etkinlik() {

	}

	public Etkinlik(String etkAdi, String etkTarihi, double etkSaati, String etkKimIle, String etkNotu) {
		super();
		this.etkinlikAdi = etkAdi;
		this.etkinlikTarihi = etkTarihi;
		this.etkinlikSaati = etkSaati;
		this.etkinlikKimIle = etkKimIle;
		this.etkinlikNotu = etkNotu;
	}

	public String getEtkinlikAdi() {
		return etkinlikAdi;
	}

	public void setEtkinlikAdi(String etkinlikAdi) {
		this.etkinlikAdi = etkinlikAdi;
	}

	public String getEtkinlikTarihi() {
		return etkinlikTarihi;
	}

	public void setEtkinlikTarihi(String etkinlikTarihi) {
		this.etkinlikTarihi = etkinlikTarihi;
	}

	public double getEtkinlikSaati() {
		return etkinlikSaati;
	}

	public void setEtkinlikSaati(double etkinlikSaati) {
		this.etkinlikSaati = etkinlikSaati;
	}

	public String getEtkinlikKimIle() {
		return etkinlikKimIle;
	}

	public void setEtkinlikKimIle(String etkinlikKimIle) {
		this.etkinlikKimIle = etkinlikKimIle;
	}

	public String getEtkinlikNotu() {
		return etkinlikNotu;
	}

	public void setEtkinlikNotu(String etkinlikNotu) {
		this.etkinlikNotu = etkinlikNotu;
	}

	public void etkinlikMenu() throws SQLException, ClassNotFoundException {
		Scanner scan = new Scanner(System.in);
		System.out.println(
				  "╔════════════════════════════╗\r\n"
			   + "║  ETKİNLİK(RANDEVU) MENU    ║\r\n"
			   + "╠════════════════════════════╣\r\n"
			    + "║ 1- Etkinlik Ekle                          ║\r\n"
				+ "║ 2- Etkinlik Sil                             ║\r\n"
			    + "║ 3- Etkinlik Düzenle                   ║\r\n"
				+ "║ 4- Etkinlik Durumu                   ║\r\n"
			    + "║ 5- Çıkış (Ana Menüye Dön)     ║\r\n"
				+ "╚═══════════════════════════╝\r\n"
			    + "Lütfen yapmak istenilen işlem numarasını giriniz");

		int secim = scan.nextInt();

		switch (secim) {
		case 1:
			etkinlikEkle();
			break;

		case 2:
			etkinlikSil();
			break;

		case 3:

			etkinlikDuzenlemeMenu();

			break;

		case 4:
			dataObj.tabloGoster("Etkinlik");
			for (int i = 0; i < etkinlikListesi.size(); i++) {
				System.out.println((i + 1) + ". " + etkinlikListesi.get(i).toString());
			}
			etkinlikMenu();

			break;

		case 5:
			Ajanda.ajandaMenu();
			break;

		default:
			break;
		}

		scan.close();
	}

	private void etkinlikEkle() throws SQLException, ClassNotFoundException {
		dataObj.tabloGoster("etkinlik");
		Scanner scan = new Scanner(System.in);

		for (int i = 0; i < Data.etkinlikler.size(); i++) {
			System.out.println((i + 1) + "- " + Data.etkinlikler.get(i));
		}
		System.out.println("Lütfen bir etkinlik seçiniz");
		int secim = scan.nextInt();
		
		if (secim==7) {
			System.out.println("yeni etkinlik Adı giriniz");
			Data.etkinlikler.set(secim-1, scan.nextLine());
			Data.etkinlikler.set(secim-1, scan.nextLine());
		}
		
		etkinlikAdi = Data.etkinlikler.get(secim - 1);
		
		System.out.println("Lütfen etkinlik tarihini giriniz [gg/aa/yyyy]");
		etkinlikTarihi = scan.next();

		System.out.println("Lütfen etkinlik saatini giriniz [ss.dd]");
		etkinlikSaati = scan.nextDouble();

		System.out.println("Lütfen etkinlik Kim ile olduğunu giriniz");
		etkinlikKimIle = scan.nextLine();
		etkinlikKimIle = scan.nextLine();

		System.out.println("Lütfen varsa etkinlik notunu giriniz.");
		etkinlikNotu = scan.nextLine();

		Etkinlik etkinlikobj = new Etkinlik(etkinlikAdi, etkinlikTarihi, etkinlikSaati, etkinlikKimIle, etkinlikNotu);
		
		etkinlikListesi.add(etkinlikobj);
		dataObj.tabloyaVeriEkle("Etkinlik", etkinlikAdi, etkinlikTarihi, String.valueOf(etkinlikSaati), etkinlikKimIle, etkinlikNotu);
		dataObj.tabloGoster("Etkinlik");
		
//		System.out.println(new String(new char[70]).replace("\0", "\r\n"));
		
		System.out.println("************** ETKINLIKLERE BASARI ILE EKLENMISTIR **************");
	
		for (int i = 0; i < etkinlikListesi.size(); i++) {

			System.out.println((i + 1) + ". " + etkinlikListesi.get(i).toString());
		}
		
		etkinlikMenu();

		scan.close();
	}

	private void etkinlikSil() throws SQLException, ClassNotFoundException {
		if (!etkinlikListesi.isEmpty()) {
			for (int i = 0; i < etkinlikListesi.size(); i++) {
				System.out.println((i + 1) + ". Etkinlik : " + etkinlikListesi.get(i).toString());
			}
			dataObj.tabloGoster("Etkinlik");
			Scanner scan = new Scanner(System.in);
			System.out.println("Etkinlikden Silmek istediginiz etkinligin adini giriniz ");

			String etkinlikAdiSec=scan.nextLine();
			dataObj.degiskenYazdir("Etkinlik", "etkinlikAdi", etkinlikAdiSec);
			dataObj.degiskenSil("Etkinlik", "etkinlikAdi", etkinlikAdiSec);
			etkinlikMenu();
			for (Etkinlik each : etkinlikListesi) {			
				if (each.etkinlikAdi.equalsIgnoreCase(etkinlikAdiSec)) {
					System.out.println(each);
					etkinlikListesi.remove(each);
					Ayarlar.bildirimYaz("Etkinlik LISTEDEN BASARILI OLARAK SILINDI");
					etkinlikMenu();
				}
				
		}
			
//			int secim = Integer.valueOf(etkinlikAdiSec);
//			if (secim < rehberListesi.size() || secim > 0) {
//				System.out.println(rehberListesi.remove(secim - 1).toString() + "\n " + secim
//						+ ". numarali Kisi Basari ile silinmistir.");
//				for (int i = 0; i < rehberListesi.size(); i++) {
//					System.out.println((i + 1) + ". Kişi : " + rehberListesi.get(i).toString());
//				}
//				rehberMenu();
//			}
		} else {
			Ayarlar.bildirimYaz("Bu isimde kayitli etkinlik bulunamadi");

			etkinlikMenu();
		}
	}
	
//		Scanner scan = new Scanner(System.in);
//dataObj.tabloGoster("etkinlik");
//		for (int i = 0; i < etkinlikListesi.size(); i++) {
//
//			System.out.println((i + 1) + ". " + etkinlikListesi.get(i).toString());
//		}
//
//		System.out.println("Silmek istediğiniz etkinligin adini giriniz ");
//		int secim = scan.nextInt();
//
//		if (secim > etkinlikListesi.size() || secim <= 0) {
//			System.out.println("Lütfen Listeden birini seçin");
//			etkinlikSil();
//		} else {
//			dataObj.degiskenYazdir("Etkinlik","etkinlikAdi", etkinlikListesi.get(secim-1).etkinlikAdi);
//			dataObj.degiskenSil("Etkinlik", "etkinlikAdi",etkinlikListesi.get(secim-1).etkinlikAdi);
//			System.out.println(etkinlikListesi.remove(secim - 1).toString() + "\n" + "Basari ile silinmistir.");
//		
////			System.out.println(new String(new char[70]).replace("\0", "\r\n"));	//Ekranı temizleme
//			etkinlikMenu();
//		}
//
//	}

	public void etkinlikDuzenlemeMenu() throws SQLException, ClassNotFoundException {

		Scanner scan = new Scanner(System.in);
		dataObj.tabloGoster("etkinlik");
		for (int i = 0; i < etkinlikListesi.size(); i++) {
			System.out.println((i + 1) + ". " + etkinlikListesi.get(i).toString());
		}
		System.out.println("Lutfen duzenlemek istediginiz etkinligin nosunu giriniz \nÇıkış için Sıfır (0) basınız");
		int secim1 = scan.nextInt();
		if (secim1 == 0) {
			etkinlikMenu();
		}

		if (secim1 > etkinlikListesi.size() || secim1 < 0) {
			System.out.println("Lütfen Listeden birini seçin");
			etkinlikDuzenlemeMenu();
		}

		System.out.println(""
				+ "╔════════════════════════════╗\r\n" 
				+ "║ ETKINLIK DUZENLEME MENU   ║\r\n"
				+ "╠════════════════════════════╣\r\n" 
				+ "║ 1- Etkinlik Adi            				  ║\r\n"
				+ "║ 2- Etkinlik Tarihi                         ║\r\n" 
				+ "║ 3- Etkinlik Saati                          ║\r\n"
				+ "║ 4- Etkinligin Kim ile                   ║\r\n" 
				+ "║ 5- Aciklama Notu                      ║\r\n"
				+ "║ 6- Etkinlik Menusune Git           ║\r\n" 
				+ "╚════════════════════════════╝\r\n"
				+ "Lutfen yapmak istenilen islem numarasini giriniz");

		int secim = scan.nextInt();

		switch (secim) {
		case 1:
			System.out.println("Yeni etkinliğin adını girin");
			String ad=scan.nextLine();
			ad=scan.nextLine();
			dataObj.degiskenIcerigiGuncelle("etkinlik", "etkinlikAdi", etkinlikListesi.get(secim1 - 1).etkinlikAdi, ad) ;
			etkinlikListesi.get(secim1 - 1).etkinlikAdi = ad;
			dataObj.tabloGoster("etkinlik");
			etkinlikDuzenlemeMenu();
			break;

		case 2:
			System.out.println("Yeni etkinliğin tarihini girin");
			String tarih=scan.next();
			dataObj.degiskenIcerigiGuncelle("etkinlik", "etkinlikTarihi", etkinlikListesi.get(secim1 - 1).etkinlikTarihi, tarih) ;
			etkinlikListesi.get(secim1 - 1).etkinlikTarihi = tarih;
			dataObj.tabloGoster("etkinlik");
			etkinlikDuzenlemeMenu();

			break;

		case 3:
			System.out.println("Yeni etkinliğin saatini girin");
			
			double saat=scan.nextDouble();
			dataObj.degiskenIcerigiGuncelle("etkinlik", "etkinlikSaati", String.valueOf(etkinlikListesi.get(secim1 - 1).etkinlikSaati ), String.valueOf(saat)); 
			etkinlikListesi.get(secim1 - 1).etkinlikSaati = saat;
			dataObj.tabloGoster("etkinlik");
			etkinlikDuzenlemeMenu();

			break;

		case 4:
			System.out.println("Yeni etkinliğin Kim ile olduğunu girin");
			String kimle= scan.nextLine();
			kimle=scan.nextLine();
			dataObj.degiskenIcerigiGuncelle("etkinlik", "etkinlikKimIle",etkinlikListesi.get(secim1 - 1).etkinlikKimIle,kimle);
			etkinlikListesi.get(secim1 - 1).etkinlikKimIle =kimle;
			dataObj.tabloGoster("etkinlik");
			etkinlikDuzenlemeMenu();

			break;

		case 5:
			System.out.println("Yeni etkinliğin Açıklama notunu girin");
			String not=scan.nextLine();
			not=scan.nextLine();
			dataObj.degiskenIcerigiGuncelle("etkinlik", "etkinlikNotu",etkinlikListesi.get(secim1 - 1).etkinlikNotu,not);
			etkinlikListesi.get(secim1 - 1).etkinlikNotu = not;
			dataObj.tabloGoster("etkinlik");
			etkinlikDuzenlemeMenu();

			break;

		case 6:
			etkinlikMenu();
			break;

		default:
			break;
		}

		scan.close();
	}

	@Override
	public String toString() {
		return 	"Etkinlik [etkinlikAdi=" + etkinlikAdi + ", etkinlikTarihi=" + etkinlikTarihi + ", etkinlikSaati="
				+ etkinlikSaati + ", etkinlikKimIle=" + etkinlikKimIle + ", etkinlikNotu=" + etkinlikNotu + "]";
	}

	public String toString1() {
		return 	" " + etkinlikTarihi + " " + etkinlikSaati + " " + etkinlikAdi + " etkiliğini yapılacaktır.";
	}
	
}
