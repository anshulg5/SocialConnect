package org.example.configRule;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.internal.util.$ImmutableCollection;
import org.example.MediatorApp;
import org.example.model.AppMessage;
import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.Map;

import static java.lang.Thread.sleep;

@Singleton
public class GetNextMsg implements Node<String> {

    MediatorApp app;
    GetNextMsg(){
    }

    public GetNextMsg(MediatorApp app) {
        this.app = app;
    }

    @Override
    public String apply(Map<String, ?> input) {

        String inputmsg = (String) input.get("msg");
        while(true) {
            System.out.println(Thread.currentThread().getName() + inputmsg);
            try {
                Thread.sleep(4000);
            }
            catch(Exception e){

            }
            if(!inputmsg.equals(app.getCurrAppmsg().getText()))return app.getCurrAppmsg().getText();
        }

//
//        while (true) {
//            AppMessage msg = app.getCurrAppmsg();
//            if (!msg.equals(inputmsg)) {
//                System.out.println("fadsfasdf");
//                return msg.getText();
//            }
//        }
    }
}
