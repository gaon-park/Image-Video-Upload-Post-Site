package test;

public class Comment {
	private int postNum;
	private String commentId;
	private int commentNum;
	private String memo;
	private String day;
	
	public Comment() {
		super();
	}

	public Comment(int postNum, String commentId, int commentNum, String memo, String day) {
		super();
		this.postNum = postNum;
		this.commentId = commentId;
		this.commentNum = commentNum;
		this.memo = memo;
		this.day = day;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
