package translater;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.util.ArrayList;


public class HttpClientClass {

    /** URL to send the request to the API to obtain the language list*/
    static final String PostUrl = "https://translate.yandex.net/api/v1.5/tr/getLangs?key=trnsl.1.1.20160314T055200Z.07d596d9ea107355.aed848e09a0ba5d5104ae740cc1b2dc0d6a33363&ui=en";


    public static void main(String[] args) throws Exception {


        /** for testing purpose of this class*/
        String  ex2 = translate_text("english", "arabic", "Hello");
        System.out.println(ex2);
        System.out.println("TRANS DONE");
    }


    /** function to get the language list */
    public  ArrayList<String> getLangs() throws Exception {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(PostUrl);
        HttpResponse response = httpClient.execute(request);

        /** Get the response */
        InputStream input = response.getEntity().getContent();


        /**creating DOM object to parse the XML returned in response*/
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

        Document doc = builder.parse(input);

        /** get the elements in the Item TAG*/
        NodeList nameNodesList = doc.getElementsByTagName("Item");

        /**create a new array list */
        ArrayList<String> listValues = new ArrayList<String>();

        /** get the value of the attribute "value" in the Item TAG and put it in to the above created arraylist*/
        for (int i = 0; i < nameNodesList.getLength(); i++) {

            listValues.add(nameNodesList.item(i).getAttributes().getNamedItem("value").getNodeValue());

        }

        return listValues;

    }

    /** function to translate a input string to the given language
     * o_lan => language of the original string to be translated
     * t_lan => language for the string to be translated
     * text_input => input string
     * */

    public static String translate_text(String o_lan,String t_lan,String text_input) throws Exception {


        String output;
        /** URL sent to the API to get the string translated*/
        String transUrl="https://translate.yandex.net/api/v1.5/tr/translate?key=trnsl.1.1.20160314T055200Z.07d596d9ea107355.aed848e09a0ba5d5104ae740cc1b2dc0d6a33363&lang="+o_lan+"-"+t_lan+"&text="+text_input;

        /**send the request to the server thorough HttpClient*/
        HttpClient httpClient_translate = new DefaultHttpClient();
        HttpGet request = new HttpGet(transUrl);
        HttpResponse response2 = httpClient_translate.execute(request);


        /**Get the response*/
        InputStream input2 = response2.getEntity().getContent();

        /**creating DOM object*/
        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder2 = dbf2.newDocumentBuilder();
        Document doc = builder2.parse(input2);

        NodeList text_tag = doc.getElementsByTagName("text");

        /** get the string value of the content in the text TAG*/
        output = String.valueOf(text_tag.item(0).getTextContent());



        return output;
    }

}