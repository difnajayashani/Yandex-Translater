<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<html>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Translator Page</title>
        <link rel="stylesheet" href="style.css">

    </head>

    <body >



    <center>
         <h3>Translator Page</h3>
         <form class="form-3" name=login  method="post" action="MyServletTranslate" >
            <table>
                <tr>

                    <td>
                    <%
                   out.println("<textarea name=\"original-text\"  rows=\"15\" cols=\"50\">");
                            String s1= (String)request.getAttribute("original");
                                        if (s1 != null){
                                            out.println(s1);
                                        }

                    out.println("</textarea>");
                    %>
                    </td>



                    <td> </td>
                     <td> <textarea name="translated-text" id="translated-text" rows="15" cols="50">

                        <% String s2= (String)request.getAttribute("translated");
                        if (s2 != null){
                           out.println(s2);
                        }


                        %>
                    </textarea></td>
                </tr>
                <tr>
                   <td><select name="original-lang" id="original" style="z-index: 1; width: 200px; padding:0px; position:absolute;">

                            <%

                              String buf_sel1=(String)request.getAttribute("selected_ol");
                              ArrayList<String>  buffer=new ArrayList<String>();
                              buffer= (ArrayList<String>)request.getAttribute("language_list");

                              for(int i=0;i<buffer.size();i++){

                                if( buf_sel1 !=null ){
                                        out.println("<option>" + buf_sel1 + "</option>");
                                        break;
                                }
                                else

                                        out.println("<option>"+buffer.get(i)+"</option>");

                              }


                            %>



                       </select>

                   </td>
                   <td></td>

                   <td><select name="translate-lang" style="z-index: 1; width: 200px; padding:0px; position:absolute;">
                            <%

                              String buf_sel2=(String)request.getAttribute("selected_tl");
                              ArrayList<String>  buffer2=new ArrayList<String>();
                              buffer2= (ArrayList<String>)request.getAttribute("language_list");
                              for(int i=0;i<buffer2.size();i++){
                                    if( buf_sel2 !=null ){
                                       out.println("<option>" + buf_sel2 + "</option>");
                                          break;
                                    }
                                    else

                                        out.println("<option>"+buffer.get(i)+"</option>");



                              }
                            %>

                        </select>

                   </td>

                </tr>
                <tr><td></td><td></td><td></td></tr>
                <tr><td></td><td></td><td></td></tr>

                <tr>
                    <td><input type="submit" value=Translate id="event_handle" name="submit"/>
                      <input type="reset" value="Reset"  name="reset"/></td>


                </tr>

                <tr>
                    <td></td>
                    <td>
                        <a href="http://localhost:8080/Listener/LogoutServlet">Logout</a>
                    </td>
                </tr>
                <tr>
                    <td>
                       <p style="color:blue"> Logged in as
                     <%=session.getAttribute("name")%>

                        </p>
                    </td>

                </tr>


             </table>

        </form>


    </center>
    <p>&nbsp;</p>
    <p> Powered by Yandex.Translate <a href="http://translate.yandex.com/.">http://translate.yandex.com/.</a></p>

    </body>






</html>