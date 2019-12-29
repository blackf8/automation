import java.io.*;
import java.util.*;
/**
*Author: Prabu Gugagantha
*Date: 12/28/2019
*Details: This program retrieves and stores wifi
*profile information into a specified text file.
**/
public class newAutomation{
  public static void main(String[] args)throws IOException{
    Runtime rt = Runtime.getRuntime();
    Process pr = rt.exec("netsh wlan show profile");
    InputStream input = pr.getInputStream();
    InputStreamReader inputReader = new InputStreamReader(input);
    BufferedReader inputBufferReader = new BufferedReader(new InputStreamReader((pr.getInputStream())));
    HashSet<String> wifiProfile = new HashSet<String>();
    Map<String, String> wifiPasswords = new HashMap<String, String>();
    wifiProfile = wifiProfileToString(inputBufferReader);
    wifiPasswords = passwordMatcher(wifiProfile);
    //stringParse(finalReturn);
  }
  /**
  * Method Name: wifiProfileToString
  * Details: Uses the bufferedReader object to
  * convert the wifiprofile into a string
  * @param input : This is an bufferedReader object that holds the
  * wifi profile as a sequence of data
  * @return total: the wifi profile concatinated as a string
  **/
  public static HashSet<String> wifiProfileToString(BufferedReader input)throws IOException{
    String current = null;
    HashSet<String> wifiProfile= new HashSet<String>();
    while((current = input.readLine()) != null){
      if(current.contains("All User Profile")){
        wifiProfile.add(current.substring(27));
      }
    }
    return wifiProfile;
  }
  public static Map<String, String> passwordMatcher(HashSet<String> wifiProfile){
    for(String network : wifiProfile){
      passwordMatcher.put(wifiProfile, password(wifiProfile));
    }
  }

  /**
  * Method Name: stringParse
  * Details: Takes the wifiprofile as a string and
  * parses through the data returning a set of wifi names
  * @param finalReturn : the wifi profile concatinated as a string
  **/
  /**
  public static void stringParse(String finalReturn){
    Set<String> wifiProfile= new HashSet<String>();
    Scanner stringReader = new Scanner(finalReturn);
    while(stringReader.next() != null){
    }
  }
  */
}
