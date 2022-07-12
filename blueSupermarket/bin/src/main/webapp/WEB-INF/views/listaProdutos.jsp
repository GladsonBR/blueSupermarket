<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, model.Produtos"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java Standard Taglib</title>
</head>
<body>

	<br>

	Lista de produtos: <br>

	<br>
	  <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>NOME</th>
                <th>CATEGORIA</th>
                <th>DESCRIÇÃO</th>
                <th>PREÇO</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${produtos}" var="produto">
                <tr>
                    <td>${produto.id }</td>
                    <td>${produto.nome}</td>
                    <td>${produto.categoria}</td>
                    <td>${produto.descricao}</td>
                    <td>${produto.preco}</td>
                    <td><a href="/carrinho/${produto.id }">Salvar no Carrinho</a></td>
                </tr>

           </c:forEach>
        </tbody>
    </table>
	<a href="/carrinho">Ir para o Carrinho</a>
</body>
</html>