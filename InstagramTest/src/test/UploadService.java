package test;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
/**
 * Servlet implementation class UploadService
 */
@WebServlet("/UploadService")
@MultipartConfig
public class UploadService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadService() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FileDAO filedao = new FileDAO();
		
		ServletContext context = getServletContext();
		Member m = (Member)request.getSession().getAttribute("user");
		String userId = m.getId();
		System.out.println("userId = " + userId);
		if(userId == null || userId.length() == 0) {
			request.getRequestDispatcher("test/login.jsp").forward(request, response);
			return;
		}
		//String userId = "gaon";
		String saveDir = context.getRealPath("Upload/" + userId); // 절대 경로를 가져옴
		File isDir = new File(saveDir);
		String author = userId;
		String memo = "";
		String moveUrl = "";
		List<FileItem> filelist = new ArrayList<FileItem>();
		if (!isDir.isDirectory()) {
			System.out.println("폴더 없음 새로 만듦");
			isDir.mkdir();
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy년MM월dd일HH시mm분ss초");
		Calendar cal = Calendar.getInstance();
		String time = format.format(cal.getTime());	

		
		boolean isMulti = ServletFileUpload.isMultipartContent(request);

		if (isMulti) {
			int maxSize = 3 * 1024 * 1024; // 3MB
			String encoding = "utf-8";

			if (ServletFileUpload.isMultipartContent(request)) { // 다중 파일 가져오기 
				try {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
					System.out.println("items.size = " + items.size());
					
					String uploadUri = "Upload/" + userId + "/" + time;
					String dir = request.getSession().getServletContext().getRealPath(uploadUri);
					File folder = new File(dir);
					if(!folder.exists()) {
						try {
							folder.mkdir();
							System.out.println("폴더 생성");
						} catch(Exception e2) {
							e2.printStackTrace();
						}
					}
										
					for(FileItem item : items) {
						if(item.isFormField()) { // 텍스트 입력의 경우
							String name = item.getFieldName();
							String value = item.getString("utf-8");
							switch(name) {
							case "author": author = userId; break;
							case "memo": memo = value; break;
							}
							System.out.println("일반 폼 필드 : " + name + "-" + value);
						} else {
							String fileName = item.getName(); // 파일 이름
							
							System.out.println("파일 이름 : " + fileName);
							filelist.add(item);	
						}
					}
					
					try {
						filedao.uploadFile(author, time, memo, filelist.size());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
					
		
					
					for(FileItem item : filelist) {
						filedao.uploadFiles(time, item.getName());
						byte[] data = item.get();
						FileOutputStream fos = new FileOutputStream(folder + "/" + item.getName());
						fos.write(data);
						fos.close();
						System.out.println("저장 성공");
					}
					moveUrl = "select.do";
					
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("저장 실패");
					moveUrl = "test/upload.jsp";
				}
			}

			System.out.println("경로 >> " + saveDir);
			request.getRequestDispatcher(moveUrl).forward(request, response);

		}

		else {
			System.out.println("일반 전송 form");
		}

	}
}
