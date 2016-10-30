<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<div class="page-content container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="login-wrapper">
				        <div class="box">
				        
				        	 <form action="<c:url value='login' />" method="post" >
					            <div class="content-wrap">
					            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					                <h6>Sign In</h6>
					                <input class="form-control" type="text" placeholder="Username" name="username" />
					                <input class="form-control" type="password" placeholder="Password" name="password"/>
					                <div class="action">
					                    <input type="submit" value="Sign In" class="btn btn-primary signup" />
					                </div>                
					            </div>
				            </form>
				        </div>
	
				        <div class="already">
<!-- 				            <p>Don't have an account yet?</p> -->
<!-- 				            <a href="signup">Sign Up</a> -->
				        </div>
				    </div>
				</div>
			</div>
		</div>