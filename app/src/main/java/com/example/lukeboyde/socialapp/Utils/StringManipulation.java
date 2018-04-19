package com.example.lukeboyde.socialapp.Utils;

/**
 * Created by lukeboyde on 15/04/2018.
 */

public class StringManipulation {

    //all methods which manipulate strings in app
    public static String expandUsername(String username){
        return username.replace(".", "");
    }

    public static String condenseUsername(String username){
        return username.replace("",".");
    }

    public static String getTags(String string){
        //if tag exists
        if(string.indexOf("#") > 0){
            StringBuilder sb = new StringBuilder();
            char[] charArray = string.toCharArray();
            boolean foundWord = false;
            //iterate through character array looking for tags
            for (char c: charArray){
                if(c == '#'){
                    //if found boolean set to true and append character
                    foundWord = true;
                    sb.append(c);
                }else{
                    if(foundWord){
                        sb.append(c);
                    }
                }
                if(c == ' '){
                    foundWord = false;
                }
            }//replace any blank spaces with no spaces
            String s = sb.toString().replace(" ", "").replace("#", ",#");
            return s.substring(1, s.length());
        }
        return string;
    }

}
