package org.apache.jsp.axis2_002dweb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.axis2.Constants;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.description.Parameter;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.axis2.util.JavaUtils;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

public final class listService_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "include/adminheader.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("<h1>Available Services</h1>\r\n");
 String prefix = request.getAttribute("frontendHostUrl") + (String)request.getSession().getAttribute(Constants.SERVICE_PATH) + "/";

      out.write('\r');
      out.write('\n');

    HashMap serviceMap = (HashMap) request.getSession().getAttribute(Constants.SERVICE_MAP);
    request.getSession().setAttribute(Constants.SERVICE_MAP, null);
    Hashtable errornessservice = (Hashtable) request.getSession().getAttribute(Constants.ERROR_SERVICE_MAP);
    boolean status = false;
    if (serviceMap != null && !serviceMap.isEmpty()) {
        Iterator operations;
        String serviceName;
        Collection servicecol = serviceMap.values();
        for (Iterator iterator = servicecol.iterator(); iterator.hasNext();) {
            AxisService axisService = (AxisService) iterator.next();
            operations = axisService.getOperations();
            serviceName = axisService.getName();

      out.write("<h2><font color=\"blue\"><a href=\"");
      out.print(prefix + axisService.getName());
      out.write("?wsdl\">");
      out.print(serviceName);
      out.write("</a></font></h2>\r\n");

    // do we need to enable REST in the main servlet so that it handles both REST and SOAP messages
    boolean disableREST = false;
    AxisConfiguration axisConfiguration = axisService.getAxisConfiguration();

    Parameter parameter ;

    // do we need to completely disable REST support
    parameter = axisConfiguration.getParameter(Constants.Configuration.DISABLE_REST);
    if (parameter != null) {
        disableREST = !JavaUtils.isFalseExplicitly(parameter.getValue());
    }
    if (!disableREST) {

      out.write("\r\n");
      out.write("\r\n");


      out.write('\r');
      out.write('\n');

    }


    String serviceDescription = axisService.getServiceDescription();
    if (serviceDescription == null || "".equals(serviceDescription)) {
        serviceDescription = "No description available for this service";
    }

      out.write("\r\n");
      out.write("<p>Service Description : ");
      out.print(serviceDescription);
      out.write("<br/>\r\n");
      out.write("Service EPR : ");
      out.print(prefix + axisService.getName());
      out.write("<br/>\r\n");
      out.write("Service Status : ");
      out.print(axisService.isActive() ? "Active" : "InActive");
      out.write("\r\n");
      out.write("Actions : <a href=\"axis2-admin/deleteService?serviceName=");
      out.print(serviceName);
      out.write("\">Remove Service</a></p><br>\r\n");

    Collection engagedModules = axisService.getEngagedModules();
    String moduleName;
    boolean modules_present = false;
    if (engagedModules.size() > 0) {

      out.write("\r\n");
      out.write("<i>Engaged modules for the service</i>\r\n");

    for (Iterator iteratorm = engagedModules.iterator(); iteratorm.hasNext();) {
        AxisModule axisOperation = (AxisModule) iteratorm.next();
        moduleName = axisOperation.getName();
        if (!modules_present) {
            modules_present = true;

      out.write("\r\n");
      out.write("<ul>\r\n");
      out.write("    ");
 }
    
      out.write("<li>");
      out.print(moduleName);
      out.write(" :: <a href=\"axis2-admin/disengageModule?type=service&serviceName=");
      out.print(serviceName);
      out.write("&module=");
      out.print(moduleName);
      out.write("\">Disengage</a></li>\r\n");
      out.write("    <br>\r\n");
      out.write("    ");

        }
        if (modules_present) {
      out.write("\r\n");
      out.write("</ul>\r\n");

        }
    }
    if (operations.hasNext()) {

      out.write("<br><i>Available operations</i>");

} else {

      out.write("<i> There are no Operations specified</i>");

    }

      out.write("<ul>");

    operations = axisService.getOperations();
    while (operations.hasNext()) {
        AxisOperation axisOperation = (AxisOperation) operations.next();

      out.write("<li>");
      out.print(axisOperation.getName().getLocalPart());
      out.write("</li>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    ");

        engagedModules = axisOperation.getEngagedModules();
        if (engagedModules.size() > 0) {
    
      out.write("\r\n");
      out.write("    <br><i>Engaged Modules for the Operation</i><ul>\r\n");
      out.write("    ");

        for (Iterator iterator2 = engagedModules.iterator(); iterator2.hasNext();) {
            AxisModule moduleDecription = (AxisModule) iterator2.next();
            moduleName = moduleDecription.getName();
    
      out.write("<li>");
      out.print(moduleName);
      out.write(" :: <a href=\"axis2-admin/disengageModule?type=operation&serviceName=");
      out.print(serviceName);
      out.write("&operation=");
      out.print(axisOperation.getName().getLocalPart());
      out.write("&module=");
      out.print(moduleName);
      out.write("\">Disengage</a></li><br>");

    }

      out.write("</ul>");

        }

    }

      out.write("</ul>\r\n");

            status = true;
        }
    }
    if (errornessservice != null) {
        if (errornessservice.size() > 0) {
            request.getSession().setAttribute(Constants.IS_FAULTY, Constants.IS_FAULTY);

      out.write("\r\n");
      out.write("<h3><font color=\"red\">Faulty Services</font></h3>\r\n");

    Enumeration faultyservices = errornessservice.keys();
    while (faultyservices.hasMoreElements()) {
        String faultyserviceName = (String) faultyservices.nextElement();

      out.write("<h3><font color=\"blue\"><a href=\"services/ListFaultyServices?serviceName=");
      out.print(faultyserviceName);
      out.write("\">\r\n");
      out.write("    ");
      out.print(faultyserviceName);
      out.write("</a></font></h3>\r\n");

            }
        }
        status = true;
    }
    if (!status) {

      out.write(" No services listed! Try hitting refresh. ");

    }

      out.write('\r');
      out.write('\n');
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
