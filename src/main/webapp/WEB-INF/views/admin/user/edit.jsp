<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/api/user"/>
<html>
<head>
    <title>Chỉnh sửa người dùng</title>
</head>
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
                    <form:form id="formEdit" class="form-horizontal" modelAttribute="model">
                    <form:hidden path="id" id="userId"/>
                    <div id="profile">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Vai trò</label>
                            <div class="col-sm-9">
                                <form:select path="roleCode" id="roleCode">
                                    <form:options items="${roles}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                    <%--<spring:message code="label.username"/>--%> Tên đăng nhập
                            </label>
                            <div class="col-sm-9">
                                <c:choose>
                                    <c:when test="${not empty model.id}">
                                        <form:input path="userName" id="userName" cssClass="form-control"
                                                    disabled="true"/>
                                    </c:when>
                                    <c:otherwise>
                                        <form:input path="userName" id="userName" cssClass="form-control"/>
                                    </c:otherwise>
                                </c:choose>
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
                        <!--Btn-->
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right message-info"></label>
                            <c:choose>
                                <c:when test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Cập nhật người dùng" id="updateBtn"/>
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Reset mật khẩu" id="resetPasswordBtn"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Thêm mới người dùng" id="createBtn"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                        </div>
                        <!--Btn-->
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    function convertData(data){
        var formData = $("#formEdit").serializeArray();
        $.each(formData, function (indexInArray, element) {
            data["" + element.name + ""] = element.value;
        });
    }

    $('#createBtn').click(function (event){
        event.preventDefault();
        var data = {};
        convertData(data);
        $('#loading_image').show();
        createUser(data);
    });


    function createUser(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                showAlertAfterCreateSuccess(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/user-edit-" + res.id;
                });
            },
            error: function (res) {
                showAlertAfterFail(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/user-edit";
                });
            }
        });
    }

    $('#updateBtn').click(function (event){
        event.preventDefault();
        var data = {};
        convertData(data);
        $('#loading_image').show();
        updateUser(data, data['id']);
    });

    function updateUser(data, id) {
        $.ajax({
            url: '${formUrl}/' + id,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                showAlertAfterUpdateSuccess(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/user-edit-" + res.id;
                });
            },
            error: function (res) {
                showAlertAfterFail(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/user-edit-" + id;
                })
            }
        });
    }

    $('#resetPasswordBtn').click(function (event) {
        event.preventDefault();
        $('#loading_image').show();
        resetPassword($('#userId').val());
    });

    function resetPassword(id) {
        $.ajax({
            url: '${formUrl}/password/' + id + '/reset',
            type: 'PUT',
            dataType: 'json',
            success: function (res) {
                showAlertAfterUpdateSuccess(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/user-edit-" + res.id;
                })
            },
            error: function (res) {
                showAlertAfterFail(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/user-edit-" + id;
                })
            }
        });
    }
</script>
</body>
</html>
