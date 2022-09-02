<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="userListURL" value="/admin/user-list"/>
<c:url var="userAPI" value="/api/user"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <%--<spring:message code="label.user.list"/>--%>
        Danh sách người dùng
    </title>
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
                    <a href="<c:url value="/admin/home"/>">
                        <%--<spring:message code="label.home"/>--%>
                        Trang chủ
                    </a>
                </li>
                <li class="active">
                    <%--<spring:message code="label.user.list"/>--%>
                    Danh sách người dùng
                </li>
            </ul>
            <!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <%--<c:if test="${messageResponse!=null}">--%>
                        <%--<div class="alert alert-block alert-${alert}">--%>
                            <%--<button type="button" class="close" data-dismiss="alert">--%>
                                <%--<i class="ace-icon fa fa-times"></i>--%>
                            <%--</button>--%>
                                <%--${messageResponse}--%>
                        <%--</div>--%>
                    <%--</c:if>--%>
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="widget-box table-filter">
                                <div class="widget-header">
                                    <h4 class="widget-title">
                                        <%--<spring:message code="label.search"/>--%>
                                        Tìm kiếm
                                    </h4>
                                    <div class="widget-toolbar">
                                        <a href="#" data-action="collapse">
                                            <i class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <form:form modelAttribute="modelSearch" action="${userListURL}" id="listForm"
                                                   method="GET">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                            <%--<spring:message code="label.search.value"/>--%>
                                                        Giá trị cần tìm
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="name"
                                                                        cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-8">
                                                        <button id="searchBtn" type="button"
                                                                class="btn btn-sm btn-success">
                                                                <%--spring:message code="label.search"/>--%>
                                                            Tìm kiếm
                                                            <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <security:authorize access="hasRole('MANAGER')">
                                            <a flag="info"
                                               class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                               data-toggle="tooltip"
                                            <%--title='<spring:message code="label.user.add"/>'--%>
                                               title="Thêm người dùng"
                                               href='<c:url value="/admin/user-edit"/>'>
                                                                <span>
                                                                    <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                                </span>
                                            </a>
                                            <button id="btnDelete" type="button" disabled
                                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                    data-toggle="tooltip"
                                                    title="Xóa người dùng">
															<span><i class="fa fa-trash-o bigger-110 pink"></i></span>
                                            </button>
                                        </security:authorize>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="table-responsive">
                                <display:table name="${modelResponse.listResult}" cellspacing="0" cellpadding="0"
                                               requestURI="${userListURL}" partialList="true" sort="external" defaultsort="2" defaultorder="ascending"
                                               id="${modelResponse.tableId}" size="${modelResponse.totalItems}" pagesize="${modelResponse.totalPageItems}"
                                               export="false"
                                               class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                               style="margin: 3em 0 1.5em;">
                                    <display:column title="<fieldset class='form-group'>
                                                        <input type='checkbox' id='checkAll' class='check-box-element'>
                                                        </fieldset>" class="center select-cell"
                                                    headerClass="center select-cell">
                                            <c:if test="${tableList.roleCode != 'MANAGER'}">
                                                <fieldset>
                                                    <input type="checkbox" name="checkList" value="${tableList.id}"
                                                           id="checkbox_${tableList.id}" class="check-box-element"/>
                                                </fieldset>
                                            </c:if>
                                    </display:column>
                                    <display:column headerClass="text-left" property="userName" title="Tên đăng nhập" sortName="userName" sortable="true"/>
                                    <display:column headerClass="text-left" property="fullName" title="Tên đầy đủ" sortName="fullName" sortable="true"/>
                                    <display:column headerClass="col-actions" title="Thao tác">
                                        <c:choose>
                                            <c:when test="${tableList.roleCode == 'MANAGER'}">
                                                <p>Không được thao tác</p>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                   title="Cập nhật người dùng"
                                                   href='<c:url value="/admin/user-edit-${tableList.id}"/>'>
                                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </display:column>
                                </display:table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#searchBtn').click(function () {
            $('#listForm').submit();
        });
    });


    // Delete buildings
    $('#btnDelete').click(function (e) {
        e.preventDefault();
        showAlertBeforeDelete(function () {
            var userIds = $('#tableList').find('tbody input[type=checkbox][name="checkList"]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteUsers(userIds);
        });
    });

    function deleteUsers(data) {
        $.ajax({
            url: '${userAPI}/',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                showAlertAfterDeleteSuccess(function () {
                    window.location.href = "/admin/user-list";
                });
            },
            error: function (res) {
                showAlertAfterFail(function () {
                    window.location.href = "/admin/user-list";
                })
            }
        });
    }

</script>
</body>

</html>