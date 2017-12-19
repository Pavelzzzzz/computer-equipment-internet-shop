<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <h2 id="fh5co-logo"><a href="index.jsp">Computer equipment<span>  internet-shop</span></a></h2>
        </div>
        <li class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="fh5co-primary-menu">
                <li>
                    <a href="${pageContext.request.contextPath}/index">Main</a>
                </li>

                <li><a href="${pageContext.request.contextPath}/about">About us</a></li>
                <c:if test="${pageContext.request.remoteUser == null}">
                    <li><a href="${pageContext.request.contextPath}/logon">Log in</a></li>
                    <li><a href="${pageContext.request.contextPath}/registration">Registration</a></li>
                </c:if>

                <c:if test="${pageContext.request.remoteUser != null}">
                <li><a href="#" onclick="document.getElementById('logoutForm').submit();">Log out</a></li>
                </c:if>
                
                <li>
                    <a class="fh5co-sub-ddown">Administration</a>
                    <ul class="fh5co-sub-menu">
                        <li><a href="${pageContext.request.contextPath}/administration?pageName=users">Users</a></li>
                        <li><a href="${pageContext.request.contextPath}/administration?pageName=roles">Roles</a></li>
                        <li><a href="${pageContext.request.contextPath}/administration?pageName=category">Category</a></li>
                        <li><a href="${pageContext.request.contextPath}/administration?pageName=products">Products</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>