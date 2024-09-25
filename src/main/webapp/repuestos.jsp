<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Repuestos</title>
</head>
<body>
<h1>Lista de Repuestos</h1>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${repuestos}" var="repuesto">
            <tr>
                <td>${repuesto.id}</td>
                <td>${repuesto.nombre}</td>
                <td>${repuesto.precio}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h2>Añadir Nuevo Repuesto</h2>
<form action="repuestos" method="post">
    Nombre: <input type="text" name="nombre" required><br>
    Precio: <input type="number" name="precio" required step="0.01"><br>
    <button type="submit">Añadir</button>
</form>

</body>
</html>