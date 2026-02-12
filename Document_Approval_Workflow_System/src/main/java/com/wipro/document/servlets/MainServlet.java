package com.wipro.document.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wipro.document.bean.DocumentBean;
import com.wipro.document.dao.DocumentDAO;
import com.wipro.document.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Administrator admin=new Administrator();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
        if ("newRecord".equals(operation)) {
            String result = addRecord(request);
            if (result == null || result.equals("FAILS") || result.equals("INVALID INPUT") ||result.equals("INVALID") || result.equals("INVALID SUBMITTER NAME") ||
                result.equals("ALREADY EXISTS")) {
                response.sendRedirect("error.html");
            } else {
                response.sendRedirect("success.html");
            }
        }
        else if ("viewRecord".equals(operation)) {
            DocumentBean bean = viewRecord(request);
            if (bean == null) {
                request.setAttribute("message",
                        "No matching records exists! Please try again!");
            } else {
                request.setAttribute("document", bean);
            }
            RequestDispatcher rd =request.getRequestDispatcher("displayDocument.jsp");
            rd.forward(request, response);
        }
        else if ("viewAllRecords".equals(operation)) {
            List<DocumentBean> list = viewAllRecords(request);

            if (list == null || list.isEmpty()) {
                request.setAttribute("message", "No records available!");
            } else {
                request.setAttribute("documentList", list);
            }
            RequestDispatcher rd =request.getRequestDispatcher("DisplayAllDocument.jsp");
            rd.forward(request, response);
        }
    }

	public String addRecord(HttpServletRequest request) {
		DocumentBean bean=new DocumentBean();
		try {
            bean.setTitle(request.getParameter("title"));
            bean.setType(request.getParameter("type"));
            bean.setSubmitter(request.getParameter("submitter"));
            String dateStr = request.getParameter("submitDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date submitDate = sdf.parse(dateStr);
            bean.setSubmitDate(submitDate);
            bean.setRemarks(request.getParameter("remarks"));
            return admin.addRecord(bean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "FAILS";
	} 
	
	public DocumentBean viewRecord(HttpServletRequest request) {
	    try {
	        String title = request.getParameter("title");
	        String dateStr = request.getParameter("submitDate");
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date utilDate = sdf.parse(dateStr);
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	        return admin.viewRecord(title, sqlDate);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	public List<DocumentBean> viewAllRecords(HttpServletRequest request){
		return admin.viewAllRecords();
	}
}
