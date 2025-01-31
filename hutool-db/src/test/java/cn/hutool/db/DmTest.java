package cn.hutool.db;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 达梦数据库单元测试
 *
 * @author wb04307201
 */
public class DmTest {

	private static final String DS_GROUP_NAME = "dm";

	//@BeforeAll
	public static void init() throws SQLException {
		Db db = Db.use(DS_GROUP_NAME);
		db.execute("CREATE TABLE test(a INTEGER, b INTEGER)");

		db.insert(Entity.create("test").set("a", 1).set("b", 11));
		db.insert(Entity.create("test").set("a", 2).set("b", 21));
		db.insert(Entity.create("test").set("a", 3).set("b", 31));
		db.insert(Entity.create("test").set("a", 4).set("b", 41));
	}

	@Test
	@Disabled
	public void upsertTest() throws SQLException {
		Db db = Db.use(DS_GROUP_NAME);
		db.upsert(Entity.create("test").set("a", 1).set("b", 111), "a");
		Entity a1 = db.get("test", "a", 1);
		assertEquals(Long.valueOf(111), a1.getLong("b"));
	}
}
