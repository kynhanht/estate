<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <div class="main-content">
        <div class="form">
            <c:choose>
                <c:when test="${param.incorrectAccount != null}">
                    <div class="alert alert-danger">
                        Mật khẩu hoặc tài khoản không đúng!
                    </div>
                </c:when>
                <c:when test="${param.accessDenied != null}">
                    <div class="alert alert-danger">
                        Bạn không có quyền vào trang này!
                    </div>
                </c:when>
                <c:when test="${param.expired != null}">
                    <div class="alert alert-danger">
                        Hãy đăng nhập lại!
                    </div>
                </c:when>
                <c:when test="${param.logout != null}">
                    <div class="alert alert-success">
                        Đã đăng xuất thành công!
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info">
                        Đăng nhập tài khoản!
                    </div>
                </c:otherwise>
            </c:choose>
            <form action="spring_security_check" id="formLogin" method="post">
                <div class="form-group">
                    <label for="userName">Tên đăng nhập</label>
                    <input type="text" class="form-control" id="userName" name="username" placeholder="Tên đăng nhập">
                </div>

                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu">
                </div>
                <div class="form-group">
                    <input type="checkbox" name="remember-me"/> Nhớ mật khẩu
                </div>
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
            </form>
        </div>
    </div>
</body>
</html>