package test;

import java.sql.*;

public class MemberDAO {
	public Member search(String id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from member_tb where id=?";
		Member m = null;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				m = new Member();
				m.setId(rs.getString("id"));
				m.setPw(rs.getString("pw"));
				m.setName(rs.getString("name"));
			}
			else throw new SQLException();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}

		return m;
	}

	public Member search(String id, String pw) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from member_tb where id = ? and pw = ?";
		Member m = null;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			if (rs.next()) { // pw 는 보일 수 있기 때문에 저장하지 않음
				m = new Member();
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
			}
			else throw new SQLException();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}

		return m;
	}

	public void add(Member m) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into member_tb values(?, ?, ?)";

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, m.getId());
			ps.setString(2, m.getPw());
			ps.setString(3, m.getName());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}
}
