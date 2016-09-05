<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
   <title>
   </title>
   


</head>
<body bgcolor="#e6e6ff">
<div class="heading" id="head">
    <!--     <h1 id="title" style="font-size: 2.5em">
          Love You Java
       </h1>
       <h2 id="title" style="font-size: 1.2em">
        Java Articles
       </h2>
       -->
       <p id="header">
           Love You Java
       </p>
       <p id="tag_line">
       <i> A junction for Java Lovers</i>
       </p>
       
       
    </div>
    
    <nav>
  <div id="menu" style="position: relative; left: 0; top: 0;">
     <table id="menu_table">
        <tr>
            <td height="40"><font size="4" color="white" style="padding-left:50px">Home</font></td>
            <td><font size="4" color="white" style="padding-left:50px">More</font></td>
            <td><font size="4" color="white" style="padding-left:50px">About</font></td>
        </tr>
     </table>
    </div>
    </nav>
    <!--  
<div style="position: relative; left: 0; top: 0;">
       	
       	<table id="image1" >
              <tr>
                <td width="100%" bgcolor="#001F5C" align="right" >
                </td>
              </tr>
		  </table>
		 
         
</div>
 -->
     <div id="upload1">
         	
       	<form action="/loveyoujavafinal/upload" method="post" enctype="multipart/form-data">  
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