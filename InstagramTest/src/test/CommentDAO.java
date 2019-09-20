package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

	public List<Comment> getComment(int num) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Comment> comments = new ArrayList<>();
		String sql = "select * from comment_tb where postNum = ?";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setPostNum(num);
				comment.setCommentNum(rs.getInt("Postnum"));
				comment.setCommentId(rs.getString("commentId"));
				comment.setMemo(rs.getString("memo"));
				comment.setDay(rs.getString("day"));
				
				comments.add(comment);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
			DBUtil.close(rs);
		} 
		return comments;
	}
	
	public void commentUp(Comment comment) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "insert into comment_tb values(?, ?, default, ?, ?)";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getPostNum());
			ps.setString(2, comment.getCommentId());
			ps.setString(3, comment.getMemo());
			ps.setString(4, comment.getDay());
			
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
		}
	}
}
