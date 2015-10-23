package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class doctoreshospital_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("    <title>Doctores del Hospital</title>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("    <h1>Doctores del Hospital</h1>\r\n");
      out.write("    <form action=\"doctoreshospital.jsp\">\r\n");
      out.write("      ");

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
      
      out.write("\r\n");
      out.write("      <input type=\"checkbox\" name=\"hospital\" value=");
      out.print( codigo );
      out.write(' ');
      out.print( checked);
      out.write("/>\r\n");
      out.write("      ");
      out.print( nombre );
      out.write("\r\n");
      out.write("      <br/>\r\n");
      out.write("      ");

        }
      
      out.write("\r\n");
      out.write("      <input type=\"submit\" value=\"Mostrar doctores\"/>\r\n");
      out.write("    </form>\r\n");
      out.write("    <hr/>\r\n");
      out.write("    ");

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
      
      out.write("\r\n");
      out.write("      <table>\r\n");
      out.write("      ");

        while (doctores.next()) {
      
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td>");
      out.print( doctores.getString(1) );
      out.write("</td>\r\n");
      out.write("          <td>");
      out.print( doctores.getString(2) );
      out.write("</td>\r\n");
      out.write("          <td>");
      out.print( doctores.getInt(3) );
      out.write("</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      ");
  
        }
      }
    
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
