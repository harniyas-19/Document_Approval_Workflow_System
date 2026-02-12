package com.wipro.document.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wipro.document.bean.DocumentBean;
import com.wipro.document.util.DBUtil;

public class DocumentDAO {
	public String createRecord(DocumentBean bean) {
		Connection connection=DBUtil.getDBConnection();
		String query="insert into Document_tb values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, bean.getDocumentId());
			ps.setString(2, bean.getTitle());
			ps.setString(3, bean.getType());
			ps.setString(4, bean.getSubmitter());
			ps.setDate(5,new Date(bean.getSubmitDate().getTime()));
			ps.setString(6, bean.getStatus());
			ps.setString(7, bean.getRemarks());
			int rows=ps.executeUpdate();
			if(rows>0) {
				return "SUCCESS";
			}
			return "FAILS";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public DocumentBean fetchRecord(String title,Date submitDate) {
		DocumentBean bean=null;
		Connection connection=DBUtil.getDBConnection();
		String query="select * from Document_tb where Title=? AND SubmitDate=? ";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,title);
			ps.setDate(2, submitDate);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				bean=new DocumentBean();
				bean.setDocumentId(rs.getString(1));
				bean.setTitle(rs.getString(2));
				bean.setType(rs.getString(3));
				bean.setSubmitter(rs.getString(4));
				bean.setSubmitDate(rs.getDate(5));
				bean.setStatus(rs.getString(6));
				bean.setRemarks(rs.getString(7));
				
			}
			return bean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String generateDocumentID(String title,Date submitDate) {
		String documentId=null;
		Connection connection=DBUtil.getDBConnection();
		String query="SELECT DOCUMENT_SEQ.NEXTVAL FROM DUAL";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int seq = rs.getInt(1);
                SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
                String temp = f.format(submitDate);
                String title1 = title.substring(0, 2).toUpperCase();
                documentId = temp + title1 + seq; 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documentId;
	}
	public boolean recordExists(String title,Date submitDate) {
		boolean exists=false;
		Connection connection=DBUtil.getDBConnection();
		String query="select * from Document_tb where Title=? AND SubmitDate=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,title);
			ps.setDate(2,submitDate);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				exists=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}
	public List<DocumentBean> fetchAllRecords(){
		List<DocumentBean> res=new ArrayList<>();
		Connection connection=DBUtil.getDBConnection();
		String query="select * from Document_tb";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				DocumentBean bean=new DocumentBean();
				bean.setDocumentId(rs.getString(1));
				bean.setTitle(rs.getString(2));
				bean.setType(rs.getString(3));
				bean.setSubmitter(rs.getString(4));
				bean.setSubmitDate(rs.getDate(5));
				bean.setStatus(rs.getString(6));
				bean.setRemarks(rs.getString(7));
				res.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}














