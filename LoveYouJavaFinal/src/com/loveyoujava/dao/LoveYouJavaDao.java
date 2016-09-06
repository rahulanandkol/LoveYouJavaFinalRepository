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
	
	
	public void deleteFileListFromDb(int fileId)
	{ 
		String deleteByIdSql="delete from file_list_detail where file_id=?";
		jdbcTemplate.update(deleteByIdSql,new Object[]{fileId});
				
	}
	
	public void updateTitleDetail(int fileId,String fileTitle,String fileContent)
	{ 
		String updateSql="update file_list_detail set file_title=?, file_content=? where file_id=?";
		jdbcTemplate.update(updateSql,new Object[]{fileTitle,fileContent,fileId});
				
	}
	
	public List<LoveYouJavaOutputModel> fetchFileListFromDb()
	{ 
		String fetchFileListSql="select file_id,file_path,file_title,file_content from file_list_detail";
		List<LoveYouJavaOutputModel> loveYouJavaFileList=jdbcTemplate.query(fetchFileListSql, new RowMapper<LoveYouJavaOutputModel>(){  
		    @Override  
		    public LoveYouJavaOutputModel mapRow(ResultSet rs, int rownumber) throws SQLException {  
		    	LoveYouJavaOutputModel loveYouJavaOutputModel=new LoveYouJavaOutputModel();  
		    	loveYouJavaOutputModel.setFileId(rs.getInt("file_id"));  
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
