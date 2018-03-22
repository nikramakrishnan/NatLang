/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author river
 */
public class Interpreter {

    public void Inp(String st) {
        alg a = new alg();
        List<Integer> primaryKey = new ArrayList<>();
        List<String> primaryWords = new ArrayList<>();
        List<String> secondaryStr = new ArrayList<>();

        //Get node 1
        Node1Read n1 = new Node1Read();
        Map<Integer, List<String>> node1;
        node1 = n1.getNode1();

        //Get Node 2
        Node2Read n2 = new Node2Read();
        Map<String, List<Integer>> node2;
        node2 = n2.getMap();
           
        // create map to store
        // iterate and display values
      {
        a.in(st);
        a.split();
        //System.out.println("Fetching Keys and corresponding [Multiple] Values n");

        //New loop - Primary
        /* Finds, stores key in PrimaryKey, and removes the element from the input ArrayList */
        for (String word : a.inp) {
            for (Map.Entry<Integer, List<String>> entry : node1.entrySet()) {
                Integer key = entry.getKey();
                List<String> values = entry.getValue();
                if (values.contains(word)) {
                    primaryKey.add(key); //Add key to list
                    primaryWords.add(word); //Add the word to primaryWords - Human readability
                }
            }
        }
        
        //System.out.println("Primary Keys:" + primaryKey);

        //New Loop End - Primary
        //We first check if the string can be processed by the Regex implementations
        FirstScan scan = new FirstScan();
        boolean isReg=false;
        int execType=scan.check(a.strcpy);
        if(execType!=0){
            isReg=true;
        }
        //Here, we check number of primaries and do as we decided6
        
        //This variable checks if anything happened. Defaults to false
        boolean actionTaken=false;
            
        int numPri = primaryKey.size();
        if (numPri == 0) {
            SetResponses sr=new SetResponses();
            String response=sr.returnResponse(a.strcpy);
            if(response!=null){
                System.out.println(response);
            }
            else{
                System.out.println(sr.getSorry());
            }
        }
        else if (isReg) {
            /*
            This will do the "set/increase/decrease" functions
            Method:
            Step 1: Perform a FirstScan to know what top-level function to perform (for ex. 1 for volume)
            Step 2: Perform a regex to find whether it is a Setter or Inc/Dec
            Step 3: If setter, get the value and pass to CMDSetter
                    If inc/dec, get value and pass to CMDChanger
            */
            
            //Perform first scan to check specific functionality
            //FirstScan fs = new FirstScan();
            //int execType = fs.check(a.strcpy);
            if(execType!=0){    //if there is something (redundant as map will already scan it)
                //Perform RegexParse to check if function is a setter or inc/dec
                RegexParse rp = new RegexParse();
                boolean isSetter = rp.isSetter(a.strcpy);
                boolean isIncDec = rp.isIncDec(a.strcpy);
                boolean isOpen=rp.isOpen(a.strcpy);
                boolean isSearch=rp.isSearch(a.strcpy);
                
                //If string is a set function
                if(isSetter && !isIncDec){
                    //Get Value to change to
                    int setVal=rp.getValue(a.strcpy);
                    //System.out.println("setval="+setVal+";check="+execType);
                    CMDSetter cmd = new CMDSetter();
                    actionTaken=cmd.excecute(execType,setVal); //Will be set to true if action was performed
                }
                
                //If string is an increment/decrement function
                else if(isIncDec){
                    //Get specific increase OR Decrease
                    int IncOrDec=rp.IncOrDec(a.strcpy);
                    //Get Value to change to
                    int setVal=rp.getValue(a.strcpy);
                    CMDChanger cmd2 = new CMDChanger();
                    actionTaken=cmd2.excecute(execType,IncOrDec,setVal); //Will be set to true if action was performed
                }
                //Opening stuff
                else if(isOpen){
                    FileLocation fl=new FileLocation();
                    String path=rp.getPath(a.strcpy);
                    String path2=fl.ReturnPath(path);
                    if(path2!=null){
                     CMDSetter cmd3 = new CMDSetter();
                    actionTaken=cmd3.open(path2); //Will be set to true if action was performed
                    }
                    else{
                        System.out.println("File not found.");
                        actionTaken=true;
                    }
                }
                //Searching stuff
                else if(isSearch){
                    String searchTerm=rp.getSearchQ(a.strcpy);
                    CMDSetter cmd4 = new CMDSetter();
                    actionTaken=cmd4.search(searchTerm);
                }
            }
        }
        if(numPri>0 && !actionTaken){
            a.inp.removeAll(primaryWords); //Remove primary words to find secondaries
            //System.out.println("Input List after Primary Scan:" + a.inp);

        //New Loop Start - Secondary - Relate every primary to ONLY 1 secondary
        int secFound = 0;
        for (int priKey : primaryKey) {
            for (String word2 : a.inp) {
                List<Integer> priAssoc = node2.get(word2);
                
                if(priAssoc==null){
                    //This will scan and resolve synonyms
                    SynonymFinder sf = new SynonymFinder();
                    String newWord=sf.find(word2);
                    priAssoc = node2.get(newWord);
                    word2=newWord;
                }
                if (priAssoc != null) {
                    if (priAssoc.contains(priKey)) {
                        secondaryStr.add(word2);
                        secFound += 1;
                        a.inp.remove(word2);
                        break;
                    }
                }
            }

        }
        //System.out.println("Secondary List:" + secondaryStr);

        //New Loop End - Secondary
        //Show what we interpreted
        if(numPri==secFound){
        //Pass this to map on off
        for (int i = 0; i < primaryKey.size(); i++) {
            List<String> primList = node1.get(primaryKey.get(i));
            String prim = primList.get(0);
            primaryWords.set(i, prim);
            //System.out.print(prim + " " + secondaryStr.get(i) + " ");
        }
            MapOnOff mof = new MapOnOff();
            mof.execute(primaryWords, secondaryStr);
        }
        else{
            System.out.println("The input was confusing.");
        }
    }
        //Done. Clear everything
        primaryKey.clear();
        secondaryStr.clear();
        primaryWords.clear();
    }
    }
}
