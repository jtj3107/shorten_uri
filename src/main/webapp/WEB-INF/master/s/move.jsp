<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String originUri = (String)request.getAttribute("originUri");


response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
response.setHeader("Location", originUri);
%> 