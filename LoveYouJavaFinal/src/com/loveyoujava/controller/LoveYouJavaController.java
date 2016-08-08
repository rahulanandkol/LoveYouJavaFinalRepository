package com.loveyoujava.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.loveyoujava.dao.LoveYouJavaDao;
import com.loveyoujava.model.LoveYouJavaOutputModel;
import com.loveyoujava.model.LoveYouJavaUploadModel;

@Controller
public class LoveYouJavaController {
	
	//ApplicationContext context= new ClassPathXmlApplicationContext("spring-dispatcher-servlet.xml");
	@Autowired
	LoveYouJavaDao loveYouJavaDao;
	@RequestMapping("/welcome")
	public ModelAndView displayFirstPage()
	{
		ModelAndView model= new ModelAndView("HomePage");
		List<LoveYouJavaOutputModel> fileDetailList=loveYouJavaDao.fetchFileListFromDb();
		model.addObject("fileDetailList", fileDetailList);
		return model;
		
	}
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public ModelAndView uploadFilePage()
	{
		
		ModelAndView model=new ModelAndView("UploadPage");
		
		return model;
	}
	
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public ModelAndView fileUploadProcess(@ModelAttribute("loveYouJavaUploadModel") LoveYouJavaUploadModel loveYouJavaUploadModel) throws Exception {
		String fileMoveStatusMessage="";
	
		String fileTitle=loveYouJavaUploadModel.getFileTitle();
		System.out.println(fileTitle);
		String originalFileName=loveYouJavaUploadModel.getMultipartFile().getOriginalFilename();
		File convertedFile=null;;
		
			 convertedFile=loveYouJavaUploadModel.multipartToFile(loveYouJavaUploadModel.getMultipartFile());
			loveYouJavaUploadModel.getMultipartFile().transferTo(convertedFile);
			
		//if(convertedFile.renameTo(new File("E:\\WORK\\PROJECTS\\LoveYouJavaFinalWorkSpace\\LoveYouJavaFinal\\WebContent\\resources\\" + convertedFile.getName())))
		
		if(convertedFile.renameTo(new File("/home/edflszri/public_html/gokolkata.net/get/resources/" + convertedFile.getName())))
			
		{
			fileMoveStatusMessage="File is moved successful";
			loveYouJavaDao.insertUploadFileEntryToDb(convertedFile.getName(), fileTitle);
    		System.out.println("File moved successfully!");
    	   }
		else
		{
			fileMoveStatusMessage="File is failed to move!";
    		System.out.println("File failed to move!");
    	   }
		
		ModelAndView model=new ModelAndView("UploadPage");
		model.addObject("message",fileMoveStatusMessage);
		return model;
	}

}
