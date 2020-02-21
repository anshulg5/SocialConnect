package org.example.configRule;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class FileReaderNode implements Node<String> {

    Node<Collection<String>> node;

    FileReaderNode(Map<Operator,Object> map, Map<String,Object> symbolTable){
        if(map.size()==1){
            Operator key = map.keySet().iterator().next();
            node = RuleApp.createNode(key,map.get(key),symbolTable);
        }
        else {
            System.out.println("Invalid rule");
        }
    }

    @Override
    public String apply(Map<String, ?> input) {
        Collection<String> path = node.apply(input);
        String url = "";
        for(String string : path){
            url += string;
        }
        String string = "";
        Scanner myReader = null;
        try {
            File file = new File(url);
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                string += data;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            try {
                if (myReader != null)
                    myReader.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return string;
    }
}
