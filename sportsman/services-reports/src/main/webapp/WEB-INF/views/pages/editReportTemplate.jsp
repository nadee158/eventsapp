<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>				
			<div class="content-box-large">
				<div class="panel-heading">
		            <div class="panel-title">Add report template</div>
		        </div>
				<div class="panel-body">
  					<form:form modelAttribute="reportTemplate" action="updateReportTemplate" class="form-horizontal" role="form" enctype="multipart/form-data">
					  <form:hidden path="id" />
					  <div class="form-group">
					    <label for="reportName" class="col-sm-2 control-label">Report Name</label>
					    <div class="col-sm-10">
					      <form:input path="reportName" cssClass="form-control" id="reportName" placeholder="Report Name" />
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-2 control-label">Description</label>
					    <div class="col-sm-10">
					      <form:textarea path="reportDescription" cssClass="form-control" id="reportDescription" placeholder="Description" />
					    </div>
					  </div>
					  <div class="form-group">
						<label class="col-md-2 control-label">Report Template</label>
						<div class="col-md-10">
							<form:input type="file" class="btn btn-default" path="file" id="file" />
						</div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <input type="submit" class="btn btn-primary" value="Save"/>
					    </div>
					  </div>
					</form:form>
  				</div>
					
		  	</div>