<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>			  	
	
			<div class="content-box-large">
				<c:if test="${!empty  message}">
					<div class="alert alert-success">
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
				<br />
				
				<div class="panel-heading">
					<div class="panel-title">Report Templates</div>
				</div>
  				<div class="panel-body">
  					
  					<c:forEach var="paramlist" items="${paramList}">
						${paramlist} : <input type="text" class="param" name="${paramlist}" id="id_${paramlist}"/><br/><br/>
					</c:forEach>
					
					<form id="hiddenForm" action="getReportParam.htm" method="post">
						<input type="hidden" id="fileName" name="fileName" value="${fileName}" />
						<input type="hidden" id="hiddenFieldValue" name="finalString" value=""/>
					</form>
					
					<button onclick="processReport();">Process</button>
  					
				</div>					
		  	</div>
		  	
		  	
<script type="text/javascript">

function processReport(){

	var finalString = "";
	$(".param").each(
		function(){
			finalString = finalString + $(this).attr('name') + "_" + $(this).val() + "#";
		}		
	);
	
	//alert(finalString);
	$("#hiddenFieldValue").val(finalString);
	
	$("#hiddenForm").submit();
	
}

</script>
