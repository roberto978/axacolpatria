<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Borrar Cliente</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>
<body>
    <div class="container my-5">
        <h3>Borrar Cliente</h3>
        <div class="card">
            <div class="card-body">
                    <p class="my-5">
                        <a href="/customer" class="btn btn-primary">
                            <i class="fas fa-arrow-left ml-2 mr-2"> Regresar</i>
                        </a>
                    </p>
                <div class="col-md-10">
                    <c:if test="${errors.size() > 0 }">
                        <c:forEach var="error" items="${errors.entrySet()}">
                            <p class="text-danger"> ${error.getValue()} </p>
                        </c:forEach>
                    </c:if>    
                    <c:if test="${deleteOK != null }">
                            <p class="text-success"> ${deleteOK} </p>
                    </c:if>    
                    <form:form action="/customer/delete"
                        method="delete" modelAttribute="customer">
                        <form:hidden path="id" id="id" />
                        <div class="row">
                            <div class="form-group col-md-8">
                                <label for="name" class="col-form-label">Name</label>
                                <form:input type="text" class="form-control" id="name"
                                    path="name" />
                            </div>
                            <div class="form-group col-md-8">
                                <label for="age" class="col-form-label">Age</label>
                                <form:input type="number"  class="form-control" id="age"
                                    path="age"  />
                            </div>
                            <div class="form-group col-md-8">
                                <label for="phoneNumber" class="col-form-label">Phone Number</label>
                                <form:input type="text" class="form-control" id="phoneNumber"
                                    path="phoneNumber"  />
                            </div>
                            <div class="form-group col-md-8">
                                <label for="address" class="col-form-label">Address</label>
                                <form:input type="text" class="form-control" id="address"
                                    path="address"  />
                            </div>
                            <div class="col-md-6">
                                <input type="submit" class="btn btn-primary" value=" Borrar ">
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
