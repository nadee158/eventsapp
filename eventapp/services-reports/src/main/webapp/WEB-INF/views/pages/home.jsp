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
  					<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="reportTables">
						<thead>
							<tr>
								<th>Report Template ID</th>
								<th>Report Name</th>
								<th>Description</th>
								<th>Original File Name</th>
								<th>Uploaded File Name</th>
								<th>Modify</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${reportTemplateList}" var="reportTemplate">
								<tr>
									<td class="center"><c:out value="${reportTemplate.id}"/></td>
									<td><c:out value="${reportTemplate.reportName}"/></td>
									<td><c:out value="${reportTemplate.reportDescription}"/></td>
									<td><c:out value="${reportTemplate.originalFileName}"/></td>
									<td class="center"><c:out value="${reportTemplate.uploadedFileName}"/></td>
									<td class="center">
										<a href="editReportTemplate?id=${reportTemplate.id}">
											Edit
										</a>
										<a href="getReportParam?jasperName=${reportTemplate.uploadedFileName}" target="_blank">
											<button>Run</button>
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>					
		  	</div>
