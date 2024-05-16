package com.nd.tepia.resources;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static enum CompiledPatterns{
        NAME(Pattern.compile("^([a-zA-Z ]*)$", Pattern.CASE_INSENSITIVE)),
        USER_NAME(Pattern.compile("^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{0,29}$",Pattern.CASE_INSENSITIVE)),
        EMAIL_V1(Pattern.compile("\\b[\\w\\S\\.\\-]+@[\\w\\S\\.\\-]+\\.\\w{2,15}\\b")),
        EMAIL_V2(Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")),
        PHONE_BR(Pattern.compile("(?:^\\([0]?[1-9]{2}\\)|^[0]?[1-9]{2}[\\.\\-\\s]?)[9]?[1-9]\\d{3}[\\.\\-\\s]?\\d{4}$")),
        PASSWORD(Pattern.compile("^([\\w\\-\\.\\@\\#\\~\\^\\ \\%\\!\\?\\+\\*\\;\\:\\´\\`\\{\\[\\}\\]\'\"\\$\\&,]{5,})$"));

        private Pattern p;
        private  CompiledPatterns(Pattern p){
            this.p = p;
        }

        public Pattern getValue(){
            return p;
        }
    }

    public static Properties valide(Pattern pattern, String text){
        Properties result = new Properties();
        try {
            Matcher matcher = pattern.matcher(text);
            if(matcher.find()){
                if(pattern.flags() == (Pattern.MULTILINE | Pattern.DOTALL) || pattern.flags() == Pattern.MULTILINE || pattern.flags() == Pattern.DOTALL){
                    do{
                        result.setProperty("result", matcher.group());
                    }while(matcher.find());
                }else{
                    result.setProperty("result", matcher.group());
                }
            }
            return result;
        } catch (Exception e) {
            result.setProperty("error", text + " does not match with the passed pattern.");
            return result;
        }
    }
}
