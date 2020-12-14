package com.LSJ.review.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SQLInterUpdate {

	void proc(PreparedStatement pstmt) throws SQLException;
}
