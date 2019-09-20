package test;

import java.util.List;

public class FileVO {
	private int num;
	private String author;
	private String day;
	private String memo;
	private int filecount;

	private List<String> files;
	private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public FileVO() {

	}

	public FileVO(int num, String author, String day, String memo, int filecount) {
		super();
		this.num = num;
		this.author = author;
		this.day = day;
		this.memo = memo;
		this.filecount = filecount;
	}

	public static boolean isVideo(String file) {
		if (file.contains(".mp4")) {
			System.out.println(file + " is video");
			return true;
		}
		System.out.println(file + " is img");
		return false;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	public List<String> getFiles() {
		return files;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getFilecount() {
		return filecount;
	}

	public void setFilecount(int filecount) {
		this.filecount = filecount;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMemo() {
		return memo;
	}

}
