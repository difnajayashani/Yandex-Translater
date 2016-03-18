<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<html>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <script language="javascript" src="example.js"> </script>
        <title>Translator Page</title>

    </head>

    <body >



    <center>
         <h3>Translator Page</h3>
        <form class="form-2" name=login  method="post" action="MyServletTranslate" >
            <table>
                <tr>

                    <td> <textarea name="original-text" rows="15" cols="50"> </textarea></td>
                    <td><input type="submit" value=Translate id="event_handle" name="submit"/> </td>
                     <td> <textarea name="translated-text" id="translated-text" rows="15" cols="50">

                        <% String s= (String)request.getAttribute("translated");
                        if (s != null){
                            out.println(s);
                        }
                        %>
                    </textarea></td>
                </tr>
                <tr>
                   <td><select name="original-lang" id="original" style="z-index: 1; width: 200px; padding:0px; position:absolute;">

                            <%
                              ArrayList<String>  buffer=new ArrayList<String>();
                              buffer= (ArrayList<String>)request.getAttribute("language_list");
                            for(int i=0;i<buffer.size();i++)
                                out.println("<option>"+buffer.get(i)+"</option>");
                            %>



                       </select>

                   </td>

                   <td><select name="translate-lang" style="z-index: 1; width: 200px; padding:0px; position:absolute;">
                            <%
                              ArrayList<String>  buffer2=new ArrayList<String>();
                              buffer2= (ArrayList<String>)request.getAttribute("language_list");
                              for(int i=0;i<buffer2.size();i++)
                                 out.println("<option>"+buffer2.get(i)+"</option>");
                            %>

                        </select>

                   </td>

                </tr>


            </table>

        </form>

    </center>

    </body






</html>