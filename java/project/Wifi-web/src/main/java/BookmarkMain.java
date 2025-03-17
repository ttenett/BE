import java.sql.Connection;
import java.util.List;

import com.bookmark.model.Bookmark;
import com.bookmark.service.BookmarkService;
import com.utils.SqliteConnector;

public class BookmarkMain {

	public static void main(String[] args) {
		BookmarkService bmService = new BookmarkService();
		SqliteConnector sqlConnector = new SqliteConnector();

		System.out.println("-------------------------------------------");
		System.out.println("1. 북마크 생성");
		System.out.println("-------------------------------------------");
		Connection conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		int groupBookmarkId = 3;
		String xSwifiMainNm = "갈현1동주민센터";
		bmService.createBookmark(conn, groupBookmarkId, xSwifiMainNm);

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		xSwifiMainNm = "갈현2동주민센터";
		bmService.createBookmark(conn, groupBookmarkId, xSwifiMainNm);

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		List<Bookmark> bmList = bmService.getBookmarkList(conn);
		System.out.println("-------------------------------------------");
		System.out.println("3. 북마크 생성 후 목록 조회");
		for (Bookmark bm : bmList) {
			printBookmark(bm);
		}

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		int id = 1;
		bmService.deleteBookmark(conn, id);
		System.out.println("-------------------------------------------");
		System.out.println("4. 북마크 삭제");
		System.out.println("-------------------------------------------");

		conn = sqlConnector.getSqliteConnection(sqlConnector.sqlitePath);
		bmList = bmService.getBookmarkList(conn);
		System.out.println("-------------------------------------------");
		System.out.println("5. 북마크 삭제 후 목록 조회");
		for (Bookmark bm : bmList) {
			System.out.println("-------------------------------------------");
			printBookmark(bm);
		}

	}

	public static void printBookmark(Bookmark bm) {
		System.out.println("id: " + bm.getId());
		System.out.println("name: " + bm.getName());
		System.out.println("x_swifi_main_nm: " + bm.getxSwifiMainNm());
		System.out.println("created_at: " + bm.getCreatedAt());

	}

}
