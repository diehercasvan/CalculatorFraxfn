package class_fraxfn;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by DIEGO CASALLAS on 14/09/2015.
 */
public class Xml {
    private Activity activity;
    private Resources resources;

    private XmlResourceParser xmlResourceParser;
    private ArrayList<String> list;

    public Xml(Activity a, Resources r) {
        this.activity = a;
        this.resources = r;
        this.xmlResourceParser = null;


    }

    public String getEventsFromAnXMLFile(String imc,String option,String tag,int archive) throws IOException, XmlPullParserException {

        {

            StringBuffer stringBuffer = new StringBuffer();
            resources = activity.getResources();
            xmlResourceParser = resources.getXml(archive);

            xmlResourceParser.next();
            String sNameTag = "";
            String sTagId = "";
            boolean bValidate = false;
            boolean bValidateText = false;

            int eventType = xmlResourceParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
//                        stringBuffer.append("******Start document******\n");
                       break;
                    case XmlPullParser.START_TAG:
                        sNameTag = xmlResourceParser.getName();

                        if (sNameTag.equals(tag)) {
                            sTagId = xmlResourceParser.getAttributeValue(null, "id");
                            if (sTagId.equals(option)) {
                               // stringBuffer.append("\n<" + xmlResourceParser.getName() + ">");
                                bValidate = true;
                            } else {

                                bValidate = false;
                            }
                        } else {
                            if (sNameTag.equals(imc) && bValidate) {


                                stringBuffer.append( xmlResourceParser.getName());
                                bValidateText = true;

                            } else {

                                bValidateText = false;
                            }
                        }


                        break;
                    case XmlPullParser.END_TAG:
                        if (bValidate && bValidateText) {
                            stringBuffer.append( xmlResourceParser.getName());
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (bValidateText) {
                            stringBuffer.append(xmlResourceParser.getText());
                        }
                        break;

                }

                eventType = xmlResourceParser.next();


            }//eof-while
           // stringBuffer.append("\n******End document******\n");
            return stringBuffer.toString();


        }

    }


}
