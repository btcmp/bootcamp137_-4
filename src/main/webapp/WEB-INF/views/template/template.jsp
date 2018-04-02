<script>
var outlet = "${outlet.name}";
if (outlet=="") {
	window.location = "${pageContext.request.contextPath}/free-autentication/choose-outlet";
}
$(document).ready(function(){
	var firstName = "${employee.firstName}";
	var lastName = "${employee.lastName}";
	var roleName = "${employee.user.role.name}";
	roleName = roleName.slice(5);
	roleName = roleName.replace("_", " ");
	$('.name').text(firstName+" "+lastName);
	$('.name-role').text(firstName+" "+lastName+" - "+roleName);
	$('.role').text(roleName);
})
</script>

<header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo" style="background-color: #c93422;">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Admin</b>LTE</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" style="background-color: #dd4b39;">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
        
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${url}/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs name">Alexander Pierce</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header" style="background-color: #dd4b39;">
                <img src="${url}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p class="name-role">
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-footer">
                <div class="text-center">
	                <!-- csrt for log out-->
					<form action="${logoutUrl}" method="post" id="logoutForm">
					  <input type="hidden"
						name="${_csrf.headerName}"
						value="${_csrf.token}" />
						<input value="Sign out" type="submit" name="logout" class="btn btn-default btn-flat"/>
					</form>
                </div>
                	
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar" style="background-color: #7a2419;">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${url}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p class="name">Alexander Pierce</p>
          <a href="#" class="role"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li id="treeview-master" class="treeview">
          <a href="#" style="background-color: #5b160d;">
            <i class="fa fa-table"></i> <span>Master</span>
            <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
          </a>
          <ul class="treeview-menu" style="background-color: #872619;">
            <li id="employee-side-option"><a href="${pageContext.request.contextPath}/master/employee"><i class="fa fa-circle-o"></i> Employee</a></li>
            <li id="item-side-option"><a href="${pageContext.request.contextPath}/master/item"><i class="fa fa-circle-o"></i> Item</a></li>
            <li id="outlet-side-option"><a href="${pageContext.request.contextPath}/master/outlet"><i class="fa fa-circle-o"></i> Outlet</a></li>
            <li id="supplier-side-option"><a href="${pageContext.request.contextPath}/master/supplier"><i class="fa fa-circle-o"></i> Supplier</a></li>
            <li id="category-side-option"><a href="${pageContext.request.contextPath}/master/category"><i class="fa fa-circle-o"></i> Category</a></li>
          </ul>
        </li>
        <li id="treeview-transaction" class="treeview">
          <a href="#" style="background-color: #5b160d;">
            <i class="fa fa-table"></i> <span>Transaction</span>
            <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
          </a>
          <ul class="treeview-menu" style="background-color: #872619;">
            <li id="purchase-request-side-option"><a href="${pageContext.request.contextPath}/transaction/purchase-order"><i class="fa fa-circle-o"></i> Purchase Order</a></li>
            <li id="purchase-order-side-option"><a href="${pageContext.request.contextPath}/transaction/purchase-request"><i class="fa fa-circle-o"></i> Purchase Request</a></li>
            <li id="adjustment-side-option"><a href="${pageContext.request.contextPath}/transaction/adjustment"><i class="fa fa-circle-o"></i> Adjustment</a></li>
            <li id="transfer-stock-side-option"><a href="${pageContext.request.contextPath}/transaction/transfer-stock"><i class="fa fa-circle-o"></i> Transfer Stock</a></li>
            <li id="sales-order-side-option"><a href="${pageContext.request.contextPath}/transaction/sales-order"><i class="fa fa-circle-o"></i> Sales Order</a></li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>