<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Clientes</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>
<body>
    <div class="container my-2">
        <div class="card">
            <div class="card-body">
                <div class="container my-5">
                    <p class="my-5">
                        <a href="/customer/add" class="btn btn-primary">
                            <i class="fas fa-user-plus ml-2"> Agregar cliente </i>
                        </a>
                    </p>
                    <div class="col-md-10">
                        
                        <c:if test="${customers.size() < 1}">
                            <h2>No record found !!</h2>
                        </c:if>
                        <c:if test="${customers.size() > 0 }">
                            <div>
                                <table class="table table-striped table-responsive-md">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Nombre</th>
                                            <th>Edad</th>
                                            <th>Contacto</th>
                                            <th>Dirección</th>
                                            <th>Editar</th>
                                            <th>Borrar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="customer" items="${customers}">
                                            <tr>
                                                <td>${customer.id}</td>
                                                <td>${customer.name}</td>
                                                <td>${customer.age}</td>
                                                <td>${customer.phoneNumber}</td>
                                                <td>${customer.address}</td>
                                                <td>
                                                    <c:if test="${customer.id > 0}">
                                                        <a href="/customer/edit/${customer.id}" class="btn btn-primary">
                                                             <i class="fas fa-user-edit ml-2"></i></a>
                                                    </c:if>
                                               </td>
                                                <td>
                                                    <c:if test="${customer.id > 0}">
                                                        <a href="/customer/delete/${customer.id}" class="btn btn-primary">
                                                        <i class="fas fa-user-times ml-2"></i>
                                                        </a>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
