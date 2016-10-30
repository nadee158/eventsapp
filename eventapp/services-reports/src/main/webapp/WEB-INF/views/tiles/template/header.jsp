<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
			<div class="header">
			     <div class="container">
			        <div class="row">
			           <div class="col-md-5">
			              <!-- Logo -->
			              <div class="logo">
			                 <h1><a href="home">RIMS Report Service</a></h1>
			              </div>
			           </div>
			           <div class="col-md-5">
			              <div class="row">
			                <div class="col-lg-12">
			                  
			                </div>
			              </div>
			           </div>
			           <div class="col-md-7">
			              <div class="navbar navbar-inverse" role="banner">
			                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
			                    <ul class="nav navbar-nav">
			                    	<sec:authorize access="isAuthenticated()">
			                      		<li><a href="#">Welcome <%= request.getUserPrincipal().getName() %> </a></li>
			                      		<li><a href="logout">Logout</a></li>
			                      	</sec:authorize>
			                    </ul>
			                  </nav>
			              </div>
			           </div>
			        </div>
			     </div>
			</div>