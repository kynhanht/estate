<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/api/user"/>
<html>
<head>
    <title>Chỉnh sửa người dùng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa người dùng</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div id="profile">
                        <form:form id="formEdit" class="form-horizontal" modelAttribute="model">
                            <form:hidden path="id" id="userId"/>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                        <%--<spring:message code="label.username"/>--%>
                                    Tên đăng nhập
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="userName" id="userName" cssClass="form-control" disabled="true"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                        <%--<spring:message code="label.fullname"/>--%>
                                    Tên đầy đủ
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="fullName" id="fullName" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    Số điện thoại
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="phone" id="phone" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    Email
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="email" id="email" cssClass="form-control"/>
                                </div>
                            </div>
                            <!--Btn-->
                            <div class="col-sm-12">
                                <label class="col-sm-3 control-label no-padding-right message-info"></label>
                                <input type="button" class="btn btn-white btn-warning btn-bold"
                                       value="Cập nhật thông tin" id="updateBtn"/>
                            </div>
                            <!--Btn-->
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>


        function convertData(data) {
            var formData = $("#formEdit").serializeArray();
            $.each(formData, function (indexInArray, element) {
                data["" + element.name + ""] = element.value;
            });
        };

        $("#updateBtn").click(function (event) {
            event.preventDefault();
            var data = {};
            convertData(data);
            $('#loading_image').show();
            updateInfo(data, $('#userName').val());
        });

        function updateInfo(data, username) {
            $.ajax({
                url: '${formUrl}/profile/' + username,
                type: 'PUT',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (res) {
                    showAlertAfterCreateSuccess(function () {
                        $('#loading_image').hide();
                        window.location.href = "<c:url value='/admin/profile-"+res.userName+"?message=update_success'/>";
                    });
                },
                error: function (res) {
                     showAlertAfterFail(function () {
                         $('#loading_image').hide();
                         window.location.href = "<c:url value='/admin/profile-"+username+"?message=error_system'/>";
                     });
                }
            });
        }
    </script>
</div>
</body>
</html>
