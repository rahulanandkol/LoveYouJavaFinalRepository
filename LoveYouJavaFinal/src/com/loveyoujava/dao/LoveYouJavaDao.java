package com.loveyoujava.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.loveyoujava.model.LoveYouJavaOutputModel;

public class LoveYouJavaDao {
	JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	} 
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public List<LoveYouJavaOutputModel> fetchFileListFromDb()
	{ 
		String fetchFileListSql="select file_id,file_path,file_title,file_content from file_list_detail";
		List<LoveYouJavaOutputModel> loveYouJavaFileList=jdbcTemplate.query(fetchFileListSql, new RowMapper<LoveYouJavaOutputModel>(){  
		    @Override  
		    public LoveYouJavaOutputModel mapRow(ResultSet rs, int rownumber) throws SQLException {  
		    	LoveYouJavaOutputModel loveYouJavaOutputModel=new LoveYouJavaOutputModel();  
		    	loveYouJavaOutputModel.setFileId(rs.getString("file_id"));  
		    	loveYouJavaOutputModel.setFilePath(rs.getString("file_path"));
		    	loveYouJavaOutputModel.setFileTitle(rs.getString("file_title"));
		    	loveYouJavaOutputModel.setFileContent(rs.getString("file_content"));
		        return loveYouJavaOutputModel;  
		    }  
		    });  
		System.out.println("fetched content list size="+loveYouJavaFileList.size());
		return loveYouJavaFileList;
			
	}
	public void insertUploadFileDetailToDb(String filePath,String fileTitle,String fileContent)
	{
		String insertUploadFileEntrySql="insert into file_list_detail(file_path,file_title,file_content)values(?,?,?)";
		jdbcTemplate.update(insertUploadFileEntrySql,filePath,fileTitle,fileContent);		
	}
	
	
	
	public void insertUploadFileEntryToDb(String filePath,String fileTitle)
	{
		String insertUploadFileEntrySql="insert into file_list_detail(file_path,file_title)values(?,?)";
		jdbcTemplate.update(insertUploadFileEntrySql,filePath,fileTitle);		
	}

}
