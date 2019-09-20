package test;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectService
 */
@WebServlet("/SelectService")
public class SelectService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("this is select.do");
		System.out.println("select : " + action);
		if (action == null || action.length() == 0 || action.equalsIgnoreCase("")) {
			baseSelect(request, response);
			return;
		} else if (action.equalsIgnoreCase("BASE")) {
			baseSelect(request, response);
			return;
		} else if (action.equalsIgnoreCase("SEARCH")) {
			selectId(request, response);
			return;
		} else if (action.equalsIgnoreCase("ALL")) {
			baseSelect(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void selectId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FileDAO dao = new FileDAO();
		String id = request.getParameter("userId");

		try {
			List<FileVO> list = dao.selectAll(id);
			Collections.reverse(list);
			for (FileVO file : list) {
				file.setFiles(dao.selectFiles(file.getDay()));
			}

			if (list != null) {
				request.setAttribute("list", list);

			} else {
				System.out.println("비었습니다");
			}
			request.getRequestDispatcher("test/boardtest.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void baseSelect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("select base");
		FileDAO dao = new FileDAO();

		try {
			List<FileVO> list = dao.selectAll();
			Collections.reverse(list);
			for (FileVO file : list) {
				file.setFiles(dao.selectFiles(file.getDay()));
			}

			if (list != null) {
				request.setAttribute("list", list);

			} else {
				System.out.println("비었습니다");
			}
			request.getRequestDispatcher("test/boardtest.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
