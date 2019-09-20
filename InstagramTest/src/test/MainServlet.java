package test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO dao = new MemberDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		if (action == null ||action.length() == 0 || action.equalsIgnoreCase("")) {
			System.out.println("nothing to do");
			return;
		} else if (action.equalsIgnoreCase("LOGIN")) {
			login(request, response);
			return;
		} else if (action.equalsIgnoreCase("UPLOAD")) {
			uploadPage(request, response);
			return;
		} else if (action.equalsIgnoreCase("LOGOUT")) {
			logout(request, response);
			return;
		} else if (action.equalsIgnoreCase("GOOGLELOGIN")) {
			googleLogin(request, response);
			return;
		} else if (action.equalsIgnoreCase("REMOVE")) {
			removePost(request, response);
			return;
		} else if (action.equalsIgnoreCase("MODIFY")) {
			modifyPage(request, response);
			return;
		} else if (action.equalsIgnoreCase("UPDATE")) {
			postUpdate(request, response);
			return;
		} else if (action.equalsIgnoreCase("DETAIL")) {
			detailPage(request, response);
			return;
		} else if (action.equalsIgnoreCase("COMMENT")) {
			commentUp(request, response);
			return;
		}

		else if (action.equalsIgnoreCase("JOIN")) {
			try {
				join(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		else if (action.equalsIgnoreCase("JOINPAGE")) {
			request.getRequestDispatcher("test/join.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void commentUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Member m = (Member) request.getSession().getAttribute("user");
		String memo = request.getParameter("memo");
		int num = Integer.parseInt(request.getParameter("num"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy년MM월dd일HH시mm분ss초");
		Calendar cal = Calendar.getInstance();
		String day = format.format(cal.getTime());

		Comment c = new Comment();
		c.setCommentId(m.getId());
		c.setDay(day);
		c.setMemo(memo);
		c.setPostNum(num);

		CommentDAO cDao = new CommentDAO();
		FileDAO filedao = new FileDAO();
		try {

			cDao.commentUp(c);
			FileVO file = filedao.selectFiles(num);

			request.setAttribute("post", file);
			request.getRequestDispatcher("test/detail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void detailPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt((String) request.getParameter("num"));
		FileDAO filedao = new FileDAO();

		try {
			FileVO file = filedao.selectFiles(num);

			request.setAttribute("post", file);
			request.getRequestDispatcher("test/detail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void postUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt((String) request.getParameter("num"));
		String memo = request.getParameter("memo");

		FileDAO filedao = new FileDAO();

		try {
			filedao.update(num, memo);
			request.setAttribute("msg", "update successed");
			request.getRequestDispatcher("/select.do?action=BASE").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifyPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt((String) request.getParameter("num"));
		FileDAO filedao = new FileDAO();

		try {
			FileVO file = filedao.selectFiles(num);

			request.setAttribute("post", file);
			request.getRequestDispatcher("test/modifyPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void removePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt((String) request.getParameter("num"));
		FileDAO filedao = new FileDAO();

		try {
			FileVO file = filedao.selectFiles(num);

			ServletContext context = getServletContext();
			String dir = context.getRealPath("Upload/" + file.getAuthor() + "/" + file.getDay()); // 절대 경로를 가져옴
			File isDir = new File(dir);
			if (isDir.isDirectory()) {
				File[] deleteFolderList = isDir.listFiles();
				for (File post : deleteFolderList) {
					post.delete();
				}

				isDir.delete();

				System.out.println("폴더 삭제 완료");
			}

			filedao.remove(num);
			System.out.println("DB 삭제 완료");

			request.setAttribute("msg", "remove successed");
			request.getRequestDispatcher("/select.do?action=BASE").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "remove failed");
			request.getRequestDispatcher("/select.do?action=BASE").forward(request, response);
		}
	}

	public void googleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		try {
			Member m = dao.search(id);
			request.getSession().setAttribute("user", m);
			System.out.println("session login!! -- google Id");
			response.sendRedirect("/InstagramTest/select.do");
		} catch (Exception e) {
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.getRequestDispatcher("test/join.jsp").forward(request, response);
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("test/login.jsp");
	}

	public void uploadPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("test/upload.jsp");
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		try {
			Member m = dao.search(id, pw);
			request.getSession().setAttribute("user", m);
			response.sendRedirect("/InstagramTest/select.do");
		} catch (Exception e) {
			request.setAttribute("msg", "로그인 실패");
			request.getRequestDispatcher("test/login.jsp").forward(request, response);
		}
	}

	public void join(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");

		try {
			Member m = dao.search(id);
			request.setAttribute("msg", "중복되는 아이디");
			request.getRequestDispatcher("test/join.jsp").forward(request, response);
		} catch (Exception e) { // 중복 x -> 생성 가능
			e.printStackTrace();
			Member m = new Member(id, pw, name);
			try {
				dao.add(m);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			request.setAttribute("msg", "로그인 해주세요");
			request.getRequestDispatcher("test/login.jsp").forward(request, response);
		}
	}
}
