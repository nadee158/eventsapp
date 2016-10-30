<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>				
			<div class="content-box-large">
				<div class="panel-heading">
		            <div class="panel-title">Add report template</div>
		        </div>
				<div class="panel-body">
  					<form:form modelAttribute="reportTemplate" action="saveReportTemplate" class="form-horizontal" role="form" enctype="multipart/form-data">
					  <form:errors path="*" cssClass="alert alert-danger" element="div" />
					  <div class="form-group">
					    <label for="reportName" class="col-sm-2 control-label">Report Name</label>
					    <div class="col-sm-6">
					      <form:input path="reportName" cssClass="form-control" id="reportName" placeholder="Report Name" />
					    </div>
					    <div class="col-sm-4">
							<span><form:errors path="reportName" cssClass="alert alert-danger" /></span>
						</div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-2 control-label">Report Type</label>
					    <div class="col-sm-6">
					      <select class="form-control" id="reportType" name="reportType" >
					      	<option value="CUSTOM">Custom</option>
					      	<option value="OTHER">Other</option>
					      </select>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-2 control-label">Description</label>
					    <div class="col-sm-6">
					      <form:textarea path="reportDescription" cssClass="form-control" id="reportDescription" placeholder="Description" />
					    </div>
					  </div>
					  <div class="form-group">
						<label class="col-md-2 control-label">Report Template</label>
						<div class="col-md-6">
							<form:input type="file" class="btn btn-default" path="file" id="file" />
						</div>
						<div class="col-md-4">
							<span><form:errors path="file" cssClass="alert alert-danger" /></span>
						</div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <input type="submit" class="btn btn-primary" value="Save"/>
					    </div>
					  </div>
					</form:form>
					<input type="button" onclick="createReport()" value="Test Report"/>
					<a href="ReportSerive/findReport?fileName=" id="reportUrl">Get Report</a>
  				</div>
					
		  	</div>
		  	
<script type="text/javascript">
function createReport(){
	var createReportRequest={reportId:7,params:{test:'Hi Sri Lanka!'}}
	$.ajax({
        type: "POST",
        url: "ReportSerive/CreateReport",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(createReportRequest),
        success: function (data) {
        	alert(data);
        	$('#reportUrl').attr('href','ReportSerive/findReport?fileName=' + data.fileName);
        	
        }
    });

}


</script>