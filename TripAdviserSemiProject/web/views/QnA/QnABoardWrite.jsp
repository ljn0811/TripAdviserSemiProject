<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/boardStyle.css">
<%@ include file="/views/common/header.jsp" %>
<script>
	
	function fn_cancle(){
		if(confirm('취소하시겠습니까?')){
			location.href="<%=request.getContextPath()%>/QnA/QnAList";
		}else{
			return;
		}		
	}	
</script>     

<section id="noticeWrite-section" class="notice-section">	
	<div class="caption">글쓰기</div>
	<form name="qnaWriteFrm" action="<%=request.getContextPath()%>/QnA/insertQnA" onsubmit="return fn_validate()" method="post">
    <table align="center" class="write-tbl">    	
         	<tr>
            	<th>작성자</th>
            	<td>                
               		<input type="text" name="userId" value="semi" readonly/>               		
            	</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>
                 	<input type="text" name="title" id="title" required/>
                </td>
            </tr>             
            <tr>
                <th>내용</th>
                <td>
                 	<textarea name="content" id="content"></textarea>
                </td>
            </tr>                    
        </table>  
    <div id="btn-container">
        <input type="submit" value="등록" onclick="fn_submit()"/>
        <input type="button" value="취소" onclick="fn_cancle()"/>
    </div>
    </form>
</section>
<script>
function fn_validate(){
	var title=$('#title').val().trim();
	var content=$('#content').val().trim();
	
	if(title.length<1){
		alert("제목을 입력하세요");		
		return false;	
		} 
	}
	return true;		
}


$(function(){
	var text=document.getElementById('content');
	text.value+="관리자와 글쓴사람만 볼 수 있음";
	
})
</script>

<%@ include file="/views/common/footer.jsp" %>