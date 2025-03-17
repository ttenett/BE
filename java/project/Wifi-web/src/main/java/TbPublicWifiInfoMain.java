import java.sql.Connection;
import java.util.List;

import com.utils.RdbmsConnector;
import com.wifi.model.Wifi;
import com.wifi.service.TbPublicWifiInfoService;

public class TbPublicWifiInfoMain {

	public static void main(String[] args) {
		TbPublicWifiInfoService wifiService = new TbPublicWifiInfoService();

		RdbmsConnector rdbmsConnector = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
		Connection conn = rdbmsConnector.getRdbmsConnection();

		// 2. 공공 와이파이 데이터 총 row 수
		int count = wifiService.getTbPublicWifiAllCount(conn);
		if (count == 0) {
			// 1. OPEN API 데이터 삽입
			conn = rdbmsConnector.getRdbmsConnection();
			wifiService.createTbPublicWifiBatch(conn);
			count = wifiService.getTbPublicWifiAllCount(conn);
		}
		System.out.println("-------------------------------------------");
		System.out.println("1. 와이파이 총 데이터 수 구하기");
		System.out.println("-------------------------------------------");
		System.out.println("allCount: " + count);
		System.exit(0);

		// 3. 공공 와이파이 좌표 근처 리스트
		conn = rdbmsConnector.getRdbmsConnection();
		List<Wifi> list = wifiService.getTbPublicWifiList(conn, 37.5544069, 126.8998666);
		System.out.println("-------------------------------------------");
		System.out.println("2. 와이파이 근처 정보 구하기");
		for (Wifi l : list) {
			System.out.println("-------------------------------------------");
			System.out.print("distance: " + Math.round(l.getDistance() * 10000) / 10000.0);
			System.out.print(", x_swifi_merg_no: " + l.getxSwifiMergNo().replace("-", ""));
			System.out.print(", x_swifi_wrdofc: " + l.getxSwifiWrdofc());
			System.out.print(", x_swifi_main_nm: " + l.getxSwifiMainNm());
			System.out.print(", x_swifi_adres1: " + l.getxSwifiAdres1());
			System.out.print(", x_swifi_adres2: " + l.getxSwifiAdres2());
			System.out.print(", x_swifi_instl_floor: " + l.getxSwifiInstlFloor());
			System.out.println();
			System.out.print(", x_swifi_instl_ty: " + l.getxSwifiInstlTy());
			System.out.print(", x_swifi_instl_mby: " + l.getxSwifiInstlMby());
			System.out.print(", x_swifi_svc_se: " + l.getxSwifiSvcSe());
			System.out.print(", x_swifi_cmcwr: " + l.getxSwifiCmcwr());
			System.out.print(", x_swifi_cnstc_year: " + l.getxSwifiCnstcYear());
			System.out.print(", x_swifi_inout_door: " + l.getxSwifiInoutDoor());
			System.out.print(", x_swifi_remars3: " + l.getxSwifiRemars3());
			System.out.print(", lnt: " + l.getLnt());
			System.out.print(", lat: " + l.getLat());
			System.out.print(", work_dttm: " + l.getWorkDttm());
			System.out.println();
		}

		// 4. 상세 정보
		conn = rdbmsConnector.getRdbmsConnection();
		Wifi wifi = wifiService.getTbPublicWifiDetail(conn, "SC22080009");
		System.out.println("-------------------------------------------");
		System.out.println("3. 와이파이 상세 정보 구하기");
		System.out.println("-------------------------------------------");
		System.out.print("distance: " + String.format("%.4f", wifi.getDistance()));
		System.out.print(", x_swifi_merg_no: " + wifi.getxSwifiMergNo().replace("-", ""));
		System.out.print(", x_swifi_wrdofc: " + wifi.getxSwifiWrdofc());
		System.out.print(", x_swifi_main_nm: " + wifi.getxSwifiMainNm());
		System.out.print(", x_swifi_adres1: " + wifi.getxSwifiAdres1());
		System.out.print(", x_swifi_adres2: " + wifi.getxSwifiAdres2());
		System.out.print(", x_swifi_instl_floor: " + wifi.getxSwifiInstlFloor());
		System.out.println();
		System.out.print(", x_swifi_instl_ty: " + wifi.getxSwifiInstlTy());
		System.out.print(", x_swifi_instl_mby: " + wifi.getxSwifiInstlMby());
		System.out.print(", x_swifi_svc_se: " + wifi.getxSwifiSvcSe());
		System.out.print(", x_swifi_cmcwr: " + wifi.getxSwifiCmcwr());
		System.out.print(", x_swifi_cnstc_year: " + wifi.getxSwifiCnstcYear());
		System.out.print(", x_swifi_inout_door: " + wifi.getxSwifiInoutDoor());
		System.out.print(", x_swifi_remars3: " + wifi.getxSwifiRemars3());
		System.out.print(", lnt: " + wifi.getLnt());
		System.out.print(", lat: " + wifi.getLat());
		System.out.print(", work_dttm: " + wifi.getWorkDttm());
		System.out.println();
	}

}
