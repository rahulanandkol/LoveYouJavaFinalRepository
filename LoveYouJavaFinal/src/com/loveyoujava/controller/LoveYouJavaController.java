package com.loveyoujava.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.loveyoujava.dao.LoveYouJavaDao;
import com.loveyoujava.model.LoveYouJavaOutputModel;
import com.loveyoujava.model.LoveYouJavaUploadModel;

@Controller
public class LoveYouJavaController {

	// ApplicationContext context= new
	// ClassPathXmlApplicationContext("spring-dispatcher-servlet.xml");
	@Autowired
	LoveYouJavaDao loveYouJavaDao;

	@RequestMapping("/")
	public ModelAndView displayFirstPage() {
		ModelAndView model = new ModelAndView("HomePage");
		List<LoveYouJavaOutputModel> fileDetailList = loveYouJavaDao.fetchFileListFromDb();
		model.addObject("fileDetailList", fileDetailList);
		return model;

	}

	@RequestMapping("/deletehome")
	public ModelAndView displayDeletePage() {
		ModelAndView model = new ModelAndView("deleteDetail");
		List<LoveYouJavaOutputModel> fileDetailList = loveYouJavaDao.fetchFileListFromDb();
		model.addObject("fileDetailList", fileDetailList);
		return model;

	}

	@RequestMapping(value = "/deleteDetail/{fileId}")
	public ModelAndView deleteDetail(@PathVariable("fileId") int fileId) {
		String titleContent = "";
		String title = "";
		String deleteStatusMessage = "deletion successful";
		loveYouJavaDao.deleteFileListFromDb(fileId);

		ModelAndView model = new ModelAndView("deleteDetail");
		model.addObject("deleteStatusMessage", deleteStatusMessage);

		return model;

	}

	@RequestMapping(value = "editDetail/{fileId}", method = RequestMethod.POST)
	public ModelAndView editDetail(
			@ModelAttribute("loveYouJavaUploadModel") LoveYouJavaUploadModel loveYouJavaUploadModel,
			@PathVariable("fileId") int fileId) {
		String titleContent = "";
		String title = "";
		String deleteStatusMessage = "update  successful";
		loveYouJavaDao.updateTitleDetail(fileId, loveYouJavaUploadModel.getEditTitle(),
				loveYouJavaUploadModel.getEditTitleContent());

		ModelAndView model = new ModelAndView("deleteDetail");
		model.addObject("deleteStatusMessage", deleteStatusMessage);

		return model;

	}

	@RequestMapping(value = "/uploadhome", method = RequestMethod.GET)
	public ModelAndView uploadFilePage() {

		ModelAndView model = new ModelAndView("UploadPage");

		return model;
	}

	@RequestMapping(value = "upload_old", method = RequestMethod.POST)
	public ModelAndView fileUploadProcess(
			@ModelAttribute("loveYouJavaUploadModel") LoveYouJavaUploadModel loveYouJavaUploadModel) {
		String fileMoveStatusMessage = "";
		try {
			String fileTitle = loveYouJavaUploadModel.getFileTitle();
			System.out.println(fileTitle);
			String originalFileName = loveYouJavaUploadModel.getMultipartFile().getOriginalFilename();
			File convertedFile = null;

			convertedFile = loveYouJavaUploadModel.multipartToFile(loveYouJavaUploadModel.getMultipartFile());
			// loveYouJavaUploadModel.getMultipartFile().transferTo(convertedFile);

			if (convertedFile.renameTo(
					new File("E:\\WORK\\LoveYouJavaFinalLocalGitRepository\\LoveYouJavaFinal\\WebContent\\resources\\"
							+ convertedFile.getName())))

			// if(convertedFile.renameTo(new
			// File("/appservers/apache-tomcat-7x/webapps/loveyoujavafinal/resources/"
			// + convertedFile.getName()))54+A

			{
				fileMoveStatusMessage = "File is moved successful";
				loveYouJavaDao.insertUploadFileEntryToDb(convertedFile.getName(), fileTitle);
				System.out.println("File moved successfully!");
			} else {
				fileMoveStatusMessage = "File is failed to move!";
				System.out.println("File failed to move!");
			}
		} catch (Exception e) {
			System.out.println("exception in upload controller=" + e);
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("UploadPage");
		model.addObject("message", fileMoveStatusMessage);
		return model;
	}

	@RequestMapping(value = "/showDetail/{fileId}")
	public ModelAndView showDetail(@PathVariable("fileId") int fileId) {
		String titleContent = "";
		String title = "";
		List<LoveYouJavaOutputModel> fileDetailList = loveYouJavaDao.fetchFileListFromDb();
		for (int i = 0; i < fileDetailList.size(); i++) {
			if (fileDetailList.get(i).getFileId() == fileId) {
				titleContent = fileDetailList.get(i).getFileContent();
				title = fileDetailList.get(i).getFileTitle();
				break;
			}

		}
		ModelAndView model = new ModelAndView("ShowDetail");
		model.addObject("title", title);
		if (titleContent.contains("#")) {
			model.addObject("titleContentList", Arrays.asList(titleContent.split("#")));
		} else {
			titleContent = titleContent + "#";
		}

		return model;

	}

	@RequestMapping(value = "/displayEditDetailPage/{fileId}")
	public ModelAndView editDetailDisplay(@PathVariable("fileId") int fileId,@ModelAttribute("loveYouJavaUploadModel") LoveYouJavaUploadModel loveYouJavaUploadModel) {
		String titleContent = "";
		String title = "";
		List<LoveYouJavaOutputModel> fileDetailList = loveYouJavaDao.fetchFileListFromDb();
		for (int i = 0; i < fileDetailList.size(); i++) {
			if (fileDetailList.get(i).getFileId() == fileId) {
				titleContent = fileDetailList.get(i).getFileContent();
				title = fileDetailList.get(i).getFileTitle();
				break;
			}

		}
		loveYouJavaUploadModel.setEditTitle(title);
		loveYouJavaUploadModel.setEditTitleContent(titleContent);
		
		ModelAndView model = new ModelAndView("editDetailDisplay");
		model.addObject("titleContent", titleContent);
		model.addObject("title", title);
		model.addObject("fileId", fileId);
		return model;

	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public ModelAndView fileUploadAndInsert(
			@ModelAttribute("loveYouJavaUploadModel") LoveYouJavaUploadModel loveYouJavaUploadModel) {
		String fileMoveStatusMessage = "";
		String fileContent = "";
		try {
			String fileTitle = loveYouJavaUploadModel.getFileTitle();
			System.out.println(fileTitle);
			String originalFileName = loveYouJavaUploadModel.getMultipartFile().getOriginalFilename();
			File uploadedFile = loveYouJavaUploadModel.multipartToFile(loveYouJavaUploadModel.getMultipartFile());
			BufferedReader br = new BufferedReader(new FileReader(uploadedFile));
			String lineContent = "";
			while ((lineContent = br.readLine()) != null) {
				fileContent = fileContent + lineContent;
			}

			loveYouJavaDao.insertUploadFileDetailToDb(uploadedFile.getName(), fileTitle, fileContent);
			fileMoveStatusMessage = "file uploaded successfully";

		} catch (Exception e) {
			fileMoveStatusMessage = "exception occured while uploading: " + e;
			System.out.println("exception in upload controller=" + e);
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("UploadPage");
		model.addObject("message", fileMoveStatusMessage);
		return model;
	}

}
