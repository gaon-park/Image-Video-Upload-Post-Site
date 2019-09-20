package test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileDAO {
	
	public void update(int num, String memo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "update fileboard set memo = ? where num = ?";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, memo);
			ps.setInt(2, num);
			
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}
	
	public void remove(int num) throws Exception {
		FileVO file = this.selectFiles(num);
		removeInFileboard(num);
		removeInFiletable(file.getDay());
	}
	
	public void removeInFileboard(int num) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "delete from fileboard where num = ?";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}
	
	public void removeInFiletable(String day) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "delete from filetable where day = ?";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, day);
			
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}

	public void uploadFile(String author, String day, String memo, int count) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		// fileboard(default num, author, day, memo, filecount)
		String sql = "insert into fileboard values(default, ?, ?, ?, ?)";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, author);
			ps.setString(2, day);
			ps.setString(3, memo);
			ps.setInt(4, count);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
		}
	}
	

	public String getUploadDay(String author, String memo, int filecount) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select day from fileboard where author=? and memo=? and filecount=?";

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, author);
			ps.setString(2, memo);
			ps.setInt(3, filecount);

			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("day");
			} else
				throw new SQLException();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
			DBUtil.close(rs);
		}
	}

	public void uploadFiles(String day, String file) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into filetable values(?, ?)";

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, day);
			ps.setString(2, file);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
		}
	}

	public List<FileVO> selectAll(String userId) throws Exception {
		List<FileVO> list = new ArrayList<FileVO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from fileboard where author = ?";

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				FileVO file = new FileVO();
				file.setNum(rs.getInt("num"));
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setMemo(rs.getString("memo"));
				file.setFilecount(rs.getInt("filecount"));
				file.setComments(new CommentDAO().getComment(file.getNum()));

				list.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}

		return list;

	}
	
	public List<FileVO> selectAll() throws Exception {
		List<FileVO> list = new ArrayList<FileVO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from fileboard";

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				FileVO file = new FileVO();
				file.setNum(rs.getInt("num"));
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setMemo(rs.getString("memo"));
				file.setFilecount(rs.getInt("filecount"));
				file.setComments(new CommentDAO().getComment(file.getNum()));

				list.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}

		return list;

	}
	
	protected List<String> selectFiles(String day) throws Exception {
		List<String> list = new ArrayList<String>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select fileName from filetable where day=?";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, day);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("fileName"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return list;
	}
	
	public FileVO selectFiles(int num) throws Exception {
		FileVO file = new FileVO();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from fileboard where num = ?";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				file.setNum(num);
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setMemo(rs.getString("memo"));
				file.setFiles(this.selectFiles(file.getDay()));
				file.setComments(new CommentDAO().getComment(num));
			}
			else throw new Exception();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		
		return file;
	}
	
	
}
