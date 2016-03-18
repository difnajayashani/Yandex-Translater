package servelet;

import translater.HttpClientClass;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * servelet which recieves the data entered to the rtanslater form
 */
public class MyServletTranslate extends HttpServlet {

    HttpClientClass get_reply = new HttpClientClass();


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String text_output;


        try {
            // get the parameters entered in translater.jsp form and send it to the language translate function
            String ol = request.getParameter("original-lang");
            String tl = request.getParameter("translate-lang");
            String text_input = request.getParameter("original-text");



            //call the language translate function and catch the translated text
            text_output = HttpClientClass.translate_text(ol, tl, text_input);


            ArrayList<String> list = new ArrayList<String>();
            list = get_reply.getLangs();
            request.setAttribute("language_list",list);



            request.setAttribute("translated", text_output);
            RequestDispatcher rd=getServletContext().getRequestDispatcher("/translater.jsp");
            rd.forward(request,response);
        } catch (Exception e) {
            throw new ServletException(e);
        }




    }


}
