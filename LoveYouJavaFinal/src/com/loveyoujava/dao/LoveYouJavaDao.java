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
		String fetchFileListSql="select file_id,file_path,file_title from file_list_detail";
		List<LoveYouJavaOutputModel> loveYouJavaFileList=jdbcTemplate.query(fetchFileListSql, new RowMapper<LoveYouJavaOutputModel>(){  
		    @Override  
		    public LoveYouJavaOutputModel mapRow(ResultSet rs, int rownumber) throws SQLException {  
		    	LoveYouJavaOutputModel loveYouJavaOutputModel=new LoveYouJavaOutputModel();  
		    	loveYouJavaOutputModel.setFileId(rs.getString("file_id"));  
		    	loveYouJavaOutputModel.setFilePath(rs.getString("file_path"));
		    	loveYouJavaOutputModel.setFileTitle(rs.getString("file_title"));
		        return loveYouJavaOutputModel;  
		    }  
		    });  
		return loveYouJavaFileList;
			
	}
	public void insertUploadFileEntryToDb(String filePath,String fileTitle)
	{
		String insertUploadFileEntrySql="insert into file_list_detail(file_path,file_title)values(?,?)";
		jdbcTemplate.update(insertUploadFileEntrySql,filePath,fileTitle);		
	}

}
