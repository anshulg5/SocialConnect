//package org.configRule.Node;
//
//import org.example.Node;
//import java.util.Map;
//
//import static java.lang.Thread.sleep;
//
////public class GetNextMsg implements Node<String> {
////
////    private MediatorApp app;
////    GetNextMsg(){
////    }
////
////    public GetNextMsg(MediatorApp app) {
////        this.app = app;
////    }
////
////    @Override
////    public String apply(Map<String, ?> input) {
////        String inputmsg = (String) input.get("msg");
////        while(inputmsg.equals(app.getCurrAppmsg().getText())) {
////            try{
////                System.out.println("Waiting for next massage not equals to MSG: " + inputmsg);
////                sleep(2000);
////            }
////            catch (Exception e){
////
////            }
////        }
////        return app.getCurrAppmsg().getText();
////    }
//}
