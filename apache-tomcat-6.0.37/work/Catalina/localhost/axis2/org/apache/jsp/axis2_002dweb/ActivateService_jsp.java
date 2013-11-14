package org.apache.jsp.axis2_002dweb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.axis2.Constants;
import org.apache.axis2.description.AxisService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public final class ActivateService_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "include/adminheader.jsp", out, false);
      out.write("\r\n");
      out.write("<h1>Turn On Service</h1>\r\n");
      out.write("<form method=\"get\" name=\"serviceActivate\" action=\"axis2-admin/activateService\">\r\n");
      out.write("  <table width=\"100%\"  border=\"0\">\r\n");
      out.write("<tr>\r\n");
      out.write("  <td colspan=\"2\" >\r\n");
      out.write("     <p>The services that are inactive are listed below. Although you can activate the services from this page, once system is restarted the services will be inactive again</p>\r\n");
      out.write("  </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("  ");

HashMap services = (HashMap)request.getSession().getAttribute(Constants.SERVICE_MAP);
Collection col = services.values();
String html = "";
int count = 0;

for (Iterator iterator = col.iterator(); iterator.hasNext();) {
	AxisService axisServices = (AxisService) iterator.next();
	if(!axisServices.isActive()){
		count++;
		html += "<option value='" + axisServices.getName() + "'>";
		html += axisServices.getName() + "</option>";
	}
}
request.getSession().setAttribute(Constants.SERVICE_MAP,null);
if (count > 0) {

      out.write("\r\n");
      out.write("  \r\n");
      out.write("    <td width=\"20%\"> Select Service : </td>\r\n");
      out.write("    <td width=\"80%\">\r\n");
      out.write("       <select name=\"axisService\" class=\"selectBoxes\">\r\n");
      out.write("\t\t");
      out.print(html);
      out.write("\r\n");
      out.write("\t\t</select>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"20%\">Activate Service </td>\r\n");
      out.write("    <td width=\"80%\"><input type=\"checkbox\" name=\"turnon\">\r\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("  <td>&nbsp;</td>\r\n");
      out.write("  <td>\r\n");
      out.write("    <input name=\"submit\" type=\"submit\" value=\" Activate \" >\r\n");
      out.write("   <input name=\"reset\" type=\"reset\" value=\" Clear \" >\r\n");
      out.write("  </td>\r\n");

} else {
	
      out.write("\r\n");
      out.write("\t<td colspan=\"2\">No inactive services present.</td>\r\n");
      out.write("\t");

}

      out.write("\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "include/adminfooter.inc", out, false);
      out.write('\r');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
