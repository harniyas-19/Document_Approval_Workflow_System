package com.wipro.document.service;

import com.wipro.document.util.InvalidInputException;

import java.sql.Date;
import java.util.List;

import com.wipro.document.bean.DocumentBean;
import com.wipro.document.dao.DocumentDAO;

public class Administrator {
	public String addRecord(DocumentBean bean) throws InvalidInputException {
		if(bean==null || bean.getTitle()==null || bean.getType()==null || bean.getSubmitter()==null || bean.getSubmitDate()==null) {
			throw new InvalidInputException();
		}
		if(bean.getTitle().length()<2 || bean.getType().length()<2) {
			return "INVALID";
		}
		if(bean.getSubmitter().length()<2) {
			return "INVALID SUBMITTER NAME";
		}
		DocumentDAO dao = new DocumentDAO();
	    java.util.Date utilDate = bean.getSubmitDate();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	    if (dao.recordExists(bean.getTitle(), sqlDate)) {
	        return "ALREADY EXISTS";
	    }

	    String docId = dao.generateDocumentID(bean.getTitle(), sqlDate);
	    bean.setDocumentId(docId);
	    bean.setStatus("Pending");

	    return dao.createRecord(bean);
	}
	public DocumentBean viewRecord(String title, Date submitDate) {
		DocumentDAO dao=new DocumentDAO();
		return dao.fetchRecord(title, submitDate);
	}
	public List<DocumentBean> viewAllRecords(){
		DocumentDAO dao=new DocumentDAO();
		return dao.fetchAllRecords();
	}
}
