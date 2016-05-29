package servlet;

import business.LogicAction;
import exception.LogicException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by monkey_d_asce on 16-5-29.
 */
@WebServlet("/Logic")
public class Logic extends HttpServlet
{
    private static final String ACTION = "action";
    private static final String ORDERDATA = "orderData";
    private static final String FILTER = "filter";
    private static final int ERRORCODE = 520;

    @EJB
    LogicAction orderAction;


    private HttpServletRequest request;

    private String val(String key)
    {
        String temp = request.getParameter(key);
        return (temp == null) ? "" : temp;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.request = request;
        request.getSession(true);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        try
        {
            switch (val(ACTION))
            {
                case "createOrder":
                    orderAction.createOrder(val(ORDERDATA));
                    break;
                case "cancel":
                    break;

                case "view":
                    break;

                default:
                    throw new LogicException("invalid action");
            }
        }
        catch (LogicException e)
        {
            String t = e.getMessage();
            System.out.println(t);
            response.setStatus(ERRORCODE);
            writer.write(t);
        }
        catch (Exception e)  //内部未知错误,一般是数据库操作错误
        {
            response.setStatus(500);
            writer.write("operation failed");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}