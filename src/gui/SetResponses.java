/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;
import me.xdrop.fuzzywuzzy.FuzzySearch;
/**
 *
 * @author river
 */
public class SetResponses{
    public String response;
    /* Inputs */
    public String[][] inputs={
        {"hi","hello","howdy","hey"},
        {"what's up","what is up","how are you","how're you","howdy","sup","kaise ho","kaisan ba"},
        {"meaning of life","Answer to the Ultimate Question of Life, the Universe, and Everything"},
        {"what can you do","what you can do","what are you doing","watcha doing","whatya doing"},
        {"father","developer","created","creator","daddy","daddie"},
        {"haha","lol","rofl","lmfao","huehue","hehehe","lel","jaja",":)","(:"},
        {"bye","goodbye","good night","good day", "see ya","catch you later"},
        {"awesome","cool","nice","good","perfect","love","like"},
        {"thanks","thank you","gracias","arigato","arigatou"},
        {"bennett","university","bu"},
        {"sandwich"},
        {"nikhil","aarsh","soumya","ramakrishnan","vishen","deb","singh"},
        {"deepak","vipul","samayveer","suneet","tuli","yaj","medury","arpit"},
        {"your name","naam"},
        {"date","divas","din","tareekh","today","aaj"},
        {"time","samay","kaal"},
        {"sorry","kshama","shama","maaf karo"}
    };
    
   /* Responses */
    public String devinfo="\nAarsh Singh Vishen\nNikhil Ramakrishnan\nSoumya Deb";
    public String[][] responses={
        {"Hello! How can I help?","Hello! What can I do for you?","Hi there! How can I help?","Hi there! What can I do for you?"},
        {"I'm feeling good, and I'm here to help you!","I'm good, here to help!","Absolutely awesome! What can I help you with?","I'm feeling awesome! How can I help you?"},
        {"It's the number 42","42","It's 42, of course!","The number 42. My friend \"Deep Thought\" calculated it"},
        {"I can do a lot of things. Try these:\n"+getSuggestions()},
        {"You unlocked developer information! Here it is:"+devinfo},
        {"Glad you liked it!","I'm happy that you're happy :)","LOL","Yay! It's feels good to see people happy","I'm glad I could make you laugh :)"},
        {"Take care (:","Goodbye","See ya!","Catch you later!","See you later!","Later!","Adios!"},
        {"Thanks! :)","Yes! Nailed it","Thank you!","I try","Gracias","Arigatou gozaimasu :)"},
        {"No, thank YOU!", "You're welcome","You don't need to thank me, I was made to help you","Don't mention it :)"},
        {"I was made at Bennett University!","East or west, Bennett is the Best!"},
        {"Hmph! Go make it yourself"},
        {"Yay! You know us? That's cool","Psst! Don't tell anybody you know who the developers are","You unlocked developer information! Here it is:"+devinfo},
        {"Hello sir, how are you?","Good day, sir!","Hi there,sir!"},
        {"I'm NatLang","I am NatLang, but you can call me what you like!"},
        {"It's "+getDate(),getDate(),"My calendar says it's "+getDate()},
        {getTime(),"It's "+getTime(),"The time is "+getTime(),"The clock says it's "+getTime()},
        {"You don't need to apoligize, I'm just a program after all","Don't be! I'm not programmed to feel emotions","Please don't! I'm not programmed to feel anger/sadness","No need to apologize, I'm not angry"}
    };
    public String[] sorry={
        "Sorry, I don't understand.","Sorry, I didn't get that. Please try rephrasing.","Whoops! I didn't understand that", "Sorry, I didn't get that."
    };
    
    /* Functions */
    private String getTime(){
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time=timeFormat.format(date)+" ("+TimeZone.getDefault().getDisplayName()+")";
        return time;
        
    }
    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM YYYY");
        Date date = new Date();
        return dateFormat.format(date);
    }
    private boolean shutDown(){
        String cmd1="shutdown -s /c \"Shut down initiated by NatLang. Requested by user.\"";
        CMDSetter cs=new CMDSetter();
        cs.supplyToCMD(cmd1);
        return true;
    }
    private String getSuggestions(){
        String[] suggest={"Set brightness to 50","wifi not working","open facebook.com","open i.mp3",
            "sound chala do","wifi ko fix karo","speaker band","howdy","what's the time?",
            "what's today?","open cdrom","open wikipedia","search Bennett University",
            "who created you?","google Java Programming","set vol to 30","speaker ko mute kar",
            "what is the meaning of life?"};
        int[] nums=ThreadLocalRandom.current().ints(0, suggest.length).distinct().limit(3).toArray();
        String returnVals="";
        for(int i:nums){
            returnVals=returnVals+"\n\""+suggest[i]+"\"";
        }
        return returnVals;
    }
    private String getResponse(int index){
        int rand = new Random().nextInt(responses[index].length);
        return responses[index][rand];
    }
    private String getClosestResponse(String input){
        int greatest=0;
        int index=-1;
        String response=null;
        int match;
        for(int i=0;i<inputs.length;i++){
            for(String s:inputs[i]){
                match=FuzzySearch.weightedRatio(input,s);
                if(match>greatest){
                    greatest=match;
                    index=i;
                }
            }
        }
        if(greatest>75){
            response=getResponse(index);
        }
        return response;
    }
    private int findMatch(String query){
        boolean matchFound=false;
        int index=-1;
        for (int i=0;i<inputs.length;i++) {
            for (String s:inputs[i]) {
                if(query.contains(s)){
                    matchFound=true;
                    index=i;
                    break;
                }
            }
            if(matchFound) break;
        }
        return index;
    }
    public String returnResponse(String query){
        int index;
        index=findMatch(query);
        if(index!=-1){
            response=getResponse(index);
        }
        else{
            response=getClosestResponse(query);
        }
        return response;
    }
    public String getSorry(){
        int rand = new Random().nextInt(sorry.length);
        return sorry[rand];
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String inp;
        SetResponses res = new SetResponses();
        res.getSuggestions();
        while(true){
            System.out.print(">");
                inp=s.next();
                String response=res.returnResponse(inp);
                if(response!=null){
                    System.out.println(response);
        }
                System.out.println();
        }
    }
}
