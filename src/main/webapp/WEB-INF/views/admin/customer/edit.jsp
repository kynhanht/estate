<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="transactionAPI" value="/api/transaction"/>

<html>
<head>
    <title>Chỉnh sửa khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin/home" />'>Home</a>
                </li>
                <li class="active">Chỉnh sửa toà nhà</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">


            <div class="row">

                <div class="col-xs-12">
                    <form:form modelAttribute="model" id="formEdit" cssClass="form-horizontal">
                        <div class="form-group">
                            <div class="col-xs-9">
                                <form:hidden path="id" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="fullName">Tên khách hàng</label>
                            <div class="col-xs-9">
                                <form:input path="fullName" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="phone">Số điện thoại</label>
                            <div class="col-xs-9">
                                <form:input path="phone" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="email">Email</label>
                            <div class="col-xs-9">
                                <form:input path="email" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="company">Tên công ty</label>
                            <div class="col-xs-9">
                                <form:input path="company" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="demand">Nhu cầu</label>
                            <div class="col-xs-9">
                                <form:input path="demand" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="note">Ghi chú</label>
                            <div class="col-xs-9">
                                <form:input path="note" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-9 col-xs-push-3">
                                <c:choose>
                                    <c:when test="${not empty model.id}">
                                        <input type="button" class="btn btn-md btn-primary"
                                               value="Cập nhật khách hàng" id="updateCustomerBtn"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="button" class="btn btn-md btn-primary"
                                               value="Thêm mới khách hàng" id="createCustomerBtn"/>
                                    </c:otherwise>
                                </c:choose>
                                <input type="button" class="btn btn-md btn-warning"
                                       value="Huỷ" id="cancelCustomerBtn"/>
                                <img src="/static/img/loading.gif" style="display: none; height: 100px"
                                     id="loading_image">
                            </div>
                        </div>
                    </form:form>
                </div>


            </div><!-- /.row -->

            <c:forEach items="${transactionTypeList}" var="transactionType">
                <br>
                <div class="row">
                    <div class="col-xs-12">
                        <h4 style="display: inline-block; color: #3a87ad">
                                ${transactionType.transactionValue}
                            <button type="button"
                                    class="btn btn-white btn-info btn-bold"
                                    data-toggle="tooltip"
                                    onclick="createTraction(${transactionType.code})"
                                    title="thêm giao dịch">
                                <span><i class="fa fa-plus-circle bigger-110 purple"></i></span>
                            </button>
                        </h4>
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Ngày tạo</th>
                                <th>Ghi chú</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${transactionType.transactions}" var="item">
                                <tr>
                                    <td>${item.createdBy}</td>
                                    <td>${item.note}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td>
                                    <form id="${transactionType.code}">
                                        <input type="hidden" name="code" value="${transactionType.code}"/>
                                        <input type="hidden" name="customerId" value="${model.id}"/>
                                        <input class="form-control" name="note"/>
                                    </form>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:forEach>


        </div> <!-- PAGE CONTENT ENDS -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->

<script type="text/javascript">
    // convertData
    function convertData(data) {
        var formData = $("#formEdit").serializeArray();
        $.each(formData, function (indexInArray, element) {
            data["" + element.name + ""] = element.value;
        });
    };
    // Create customer
    $("#createCustomerBtn").click(function (e) {
        e.preventDefault();
        var data = {};
        convertData(data);
        createCustomer(data);
    });

    function createCustomer(data) {
        $('#loading_image').show();
        // API
        $.ajax({
            type: "POST",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                showAlertAfterCreateSuccess(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/customer-edit/" + response.id;
                });
            },
            error: function (response) {
                showAlertAfterUpdateSuccess(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/customer-edit";
                })
            }
        });
    }

    // Update customer
    $("#updateCustomerBtn").click(function (e) {
        e.preventDefault();
        var data = {};
        convertData(data);
        const customerId = data['id'];
        updateCustomer(data, customerId);

    });

    function updateCustomer(data, id) {
        $('#loading_image').show();
        // API
        $.ajax({
            type: "PUT",
            url: "${customerAPI}/" + id,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                showAlertAfterUpdateSuccess(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/customer-edit/" + response.id;
                });
            },
            error: function (response) {
                showAlertAfterFail(function () {
                    $('#loading_image').hide();
                    window.location.href = "/admin/customer-edit/" + id;
                })
            }
        });
    }

    // Cancel
    $("#cancelCustomerBtn").click(function () {
        window.location.href = "/admin/customer-list"
    });


    function createTraction(formId) {

        showAlertBeforeConfirm(function () {
            var data = {};
            var formData = $(formId).serializeArray();
            $.each(formData, function (indexInArray, element) {
                data["" + element.name + ""] = element.value;
            });
            // API
            $.ajax({
                type: "POST",
                url: "${transactionAPI}",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    showAlertAfterSuccess(function () {
                        window.location.href = "/admin/customer-edit/" + data["customerId"];
                    })
                },
                error: function (response) {
                    showAlertAfterFail(function () {
                        window.location.href = "/admin/customer-edit";
                    });
                }
            });
        });

    }
</script>

</body>
</html>
