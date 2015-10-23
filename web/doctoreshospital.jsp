<%-- 
    Document   : doctoreshospital
    Created on : 23-oct-2015, 12:27:10
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Doctores del Hospital</title>
  </head>
  <body>
    <h1>Doctores del Hospital</h1>
    <form action="doctoreshospital.jsp">
      <%
        String[] valores = request.getParameterValues("hospital");
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conexion = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:XE", "system", "javaoracle");

        String query = "SELECT HOSPITAL_COD, NOMBRE FROM HOSPITAL";
        Statement sql = conexion.createStatement();
        ResultSet hospitales = sql.executeQuery(query);
        
        while (hospitales.next()) {
          int codigo = hospitales.getInt(1);
          String nombre = hospitales.getString(2);
          String checked = "";
          if (valores != null) {
            for (int i = 0; i < valores.length; i++) {
              if (codigo == Integer.parseInt(valores[i])) {
                checked = " checked";
                break;
              }
            }
          }
      %>
      <input type="checkbox" name="hospital" value=<%= codigo %> <%= checked%>/>
      <%= nombre %>
      <br/>
      <%
        }
      %>
      <input type="submit" value="Mostrar doctores"/>
    </form>
    <hr/>
    <%
      if (valores != null) {
        String condicion = "(";
        for (int i = 0; i<valores.length; i++) {
          if (i > 0) 
            condicion += ", ";
          condicion += valores[i];
        }
        condicion += ")";

        query = "SELECT APELLIDO, ESPECIALIDAD, SALARIO FROM DOCTOR WHERE " +
            "HOSPITAL_COD IN " + condicion;
        ResultSet doctores = sql.executeQuery(query);
      %>
      <table>
      <%
        while (doctores.next()) {
      %>
        <tr>
          <td><%= doctores.getString(1) %></td>
          <td><%= doctores.getString(2) %></td>
          <td><%= doctores.getInt(3) %></td>
        </tr>
      <%  
        }
      }
    %>
    </table>
  </body>
</html>
