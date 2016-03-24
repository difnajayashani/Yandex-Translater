package servelet;


import translater.HttpClientClass;
import validate.LoginValidate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // HttpClientClass lang_obj=null;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        /** username and password entered to the form are captured*/
        String n = request.getParameter("username");
        String p = request.getParameter("password");


        /*HttpSession session = request.getSession(false);
        if (session != null)
            session.setAttribute("name", n);*/


        /**validate the login */
        boolean valid = LoginValidate.validate(n, p);

        if (valid) {
            try {
                /** to load the Yandex language list to the form dropdowns using a HttpClient object */
                HttpClientClass lang_obj = new HttpClientClass();
                ArrayList<String> load = new ArrayList<String>();
                load = lang_obj.getLangs();
                request.setAttribute("language_list", load);


                /**set the logged in user's name */
                HttpSession session = request.getSession(false);
                if (session != null)
                    session.setAttribute("name", n);

                //navigate to the translater page in case login is valid
                RequestDispatcher rd = request.getRequestDispatcher("translater.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                throw new ServletException(e);
            }
            //HttpServletRequest languagelist = null;

        } else {
/**/
            request.setAttribute("error", "Sorry username or password error");
            //out.print("<p style=\"color:blue\">Sorry username or password error</p>");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        }


        out.close();
    }
}