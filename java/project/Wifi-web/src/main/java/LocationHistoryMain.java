import java.sql.Connection;
import java.util.List;

import com.utils.SqliteConnector;
import com.wifi.model.LocationHistory;
import com.wifi.service.LocationHistoryService;

public class LocationHistoryMain {

	public static void main(String[] args) {
		SqliteConnector sqlConnector = new SqliteConnector();
		LocationHistoryService lhService = new LocationHistoryService();
		List<LocationHistory> lhList = null;

		Connection conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);

		// 1. 히스토리 데이터 생성
		System.out.println("-------------------------------------------");
		System.out.println("1. 위치 히스토리 저장");
		System.out.println("-------------------------------------------");
		lhService.createLocationHistory(conn, 37.62364, 126.91670);

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		lhService.createLocationHistory(conn, 37.61855, 126.91592);

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		lhService.createLocationHistory(conn, 37.61418, 126.92082);

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		lhService.createLocationHistory(conn, 37.59642, 126.91535);

		// 3. 히스토리 데이터 조회
		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		lhList = lhService.getLocationHistoryList(conn);
		System.out.println("-------------------------------------------");
		System.out.println("2. 위치 히스토리 생성 후 목록 구하기");
		for (LocationHistory lh : lhList) {
			System.out.println("-------------------------------------------");
			printLocationHistory(lh);
		}

		// 2. 히스토리 데이터 삭제
		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		lhService.deleteLocationHistory(conn, 1);
		System.out.println("-------------------------------------------");
		System.out.println("3. 위치 히스토리 삭제");
		System.out.println("-------------------------------------------");

		// 3. 히스토리 데이터 조회
		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		lhList = lhService.getLocationHistoryList(conn);
		System.out.println("-------------------------------------------");
		System.out.println("4. 위치 히스토리 삭제 후 목록 구하기");
		for (LocationHistory lh : lhList) {
			System.out.println("-------------------------------------------");
			printLocationHistory(lh);
		}
	}

	public static void printLocationHistory(LocationHistory lh) {
		System.out.println("id: " + lh.getId());
		System.out.println("lat: " + lh.getLat());
		System.out.println("lnt: " + lh.getLnt());
		System.out.println("created_at: " + lh.getCreatedAt());

	}

}
