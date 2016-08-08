<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
   <title>
   </title>
   <style>
      
      table#image1 {
      width:100%;
      border=0;
      height:40px;
      cellspacing=0px;
      }
      
      div#upload1 
      {
         margin: auto;
         width: 50%;
         
         padding: 10px  
      }
      
      .center{
      margin: auto;}
    </style>
</head>
<body>
<div style="position: relative; left: 0; top: 0;">
       	
       	<table id="image1" >
              <tr>
                <td width="100%" bgcolor="#001F5C" align="right" >
                </td>
              </tr>
		  </table>
         
</div>
     <div id="upload1">
         	
       	<form action="/LoveYouJavaFinal/upload" method="post" enctype="multipart/form-data">  
         <table class="center">
             <tr>
                <td>
                   Name Of the Topic:--	<input type="text" name="fileTitle" value="${loveYouJavaUploadModel.fileTitle}">
                </td>  
             </tr>
             
              <tr>
                <td>
                    
					Select a File:--<input type="file" name="multipartFile" value="${loveYouJavaUploadModel.multipartFile}">
					<input type="submit" value="UPLOAD">
					
                </td>
              </tr>
		  </table>
         </form>
     
     </div>
     <h1>${message}</h1>


</body>
</html>