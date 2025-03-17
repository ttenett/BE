import java.sql.Connection;
import java.util.List;

import com.bookmark.model.GroupBookmark;
import com.bookmark.service.GroupBookmarkService;
import com.utils.SqliteConnector;

public class GroupBookmarkMain {

	public static void main(String[] args) {
		GroupBookmarkService gbService = new GroupBookmarkService();
		SqliteConnector sqlConnector = new SqliteConnector();

		System.out.println("-------------------------------------------");
		System.out.println("1. 북마크 그룹 저장: ");
		System.out.println("-------------------------------------------");
		// 2. 북마크 생성
		Connection conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		String name = "내 집 근처";
		int order = 1;
		gbService.createGroupBookmark(conn, name, order);

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		name = "내 회사 근처";
		order = 2;
		gbService.createGroupBookmark(conn, name, order);

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		name = "자주 가는 카페 근처";
		order = 3;
		gbService.createGroupBookmark(conn, name, order);

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		name = "임시 장소";
		order = 4;
		gbService.createGroupBookmark(conn, name, order);

		// 3. 북마크 상세 조회
		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		int id = 2;
		GroupBookmark gbDetail = gbService.getGroupBookmarkDetail(conn, id);
		System.out.println("-------------------------------------------");
		System.out.println("2. 북마크 그룹 상세 조회");
		System.out.println("-------------------------------------------");
		System.out.println("id: " + gbDetail.getId());
		System.out.println("name: " + gbDetail.getName());
		System.out.println("order: " + gbDetail.getOrder());

		// 4. 북마크 수정
		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		id = 2;
		name = "내 집 근처 1";
		order = 5;
		gbService.updateGroupBookmark(conn, id, name, order);
		System.out.println("-------------------------------------------");
		System.out.println("3. 북마크 그룹 수정");
		System.out.println("-------------------------------------------");

		// 5. 북마크 목록 조회
		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		List<GroupBookmark> gbList = gbService.getGroupBookmarkList(conn);
		System.out.println("-------------------------------------------");
		System.out.println("4. 수정 후 북마크 그룹 목록 조회");
		for (GroupBookmark gb : gbList) {
			System.out.println("------------------------------------------");
			printGroupBookmark(gb);
		}

		// 6. 북마크 삭제
		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		id = 3;
		gbService.deleteGroupBookmark(conn, id);
		System.out.println("-------------------------------------------");
		System.out.println("5. 북마크 그룹 삭제");
		System.out.println("-------------------------------------------");

		// 7. 북마크 목록 조회
		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		gbList = gbService.getGroupBookmarkList(conn);
		System.out.println("-------------------------------------------");
		System.out.println("6. 삭제 후 북마크 그룹  목록 조회");
		for (GroupBookmark gb : gbList) {
			System.out.println("------------------------------------------");
			printGroupBookmark(gb);
		}

	}

	public static void printGroupBookmark(GroupBookmark gb) {
		System.out.println("id: " + gb.getId());
		System.out.println("name: " + gb.getName());
		System.out.println("order: " + gb.getOrder());
		System.out.println("created_at: " + gb.getCreatedAt());
		System.out.println("updated_at: " + gb.getUpdatedAt());

	}

}
