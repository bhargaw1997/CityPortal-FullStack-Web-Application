<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
<!-- jQuery 2.1.4 -->
	<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
<!-- jQuery UI 1.11.4 -->
    <script src="<%=request.getContextPath() %>/resources/admin/js/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
<!-- Bootstrap 3.3.4 -->
    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
<!-- Slimscroll -->
    <script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
    <script src="<%=request.getContextPath() %>/resources/admin/js/fastclick.min.js"></script>
<!-- AdminLTE App -->
    <script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>