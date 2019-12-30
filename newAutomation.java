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
    HashSet<String> wifiProfile = new HashSet<String>();
    Map<String, String> wifiPasswords = new HashMap<String, String>();
    BufferedReader inputBufferReader = bufferedReaderCreator("netsh wlan show profile");
    wifiProfile = wifiProfileToString(inputBufferReader);
    passwordMatcher(wifiProfile, wifiPasswords);
    for(String s : wifiPasswords.keySet())
    System.out.println(s+ " ==> " + wifiPasswords.get(s));
    //stringParse(finalReturn);
  }
  /**
  * Method Name: wifiProfileToString
  * Details: Uses the bufferedReader object to
  * convert the wifiprofile into a string
  * @param input : This is an bufferedReader object that holds the
  * wifi profile as a sequence of data
  * @return total : the wifi profile concatinated as a string
  **/
  public static HashSet<String> wifiProfileToString(BufferedReader input)throws IOException{
    String current = null;
    HashSet<String> wifiProfile= new HashSet<String>();
    while((current = input.readLine()) != null){
      if(current.contains("All User Profile")){
        wifiProfile.add(current.substring(26));
      }
    }
    return wifiProfile;
  }
  /**
  * Method Name: passwordMatcher
  * Details: Matches the password of the wifi network to its network name
  * @param wifiprofile : A set that holds the names of wifi networks
  * @param wifiPasswords : A map that maps network names to their respective passwords
  **/
  public static void passwordMatcher(HashSet<String> wifiProfile,
                                                    Map<String, String> wifiPasswords)throws IOException{
    for(String network : wifiProfile){
      wifiPasswords.put(network, password(network));
    }
  }
  /**
  * Method Name: passwords
  * Details: Retreives the password of a wifi network using a newly created bufferedReader
  * @param network : The wifi network name
  * @return password: The password for the specified wifi network
  **/
  public static String password(String network)throws IOException{
    String password = null;
    String current = null;
    BufferedReader reader = bufferedReaderCreator("netsh wlan show profile " + network + " key = clear");

    while((current = reader.readLine()) != null){
      if(current.contains("Key Content")){
        password = current.substring(29);
      }
    }
    return password;
  }
  /**
  * Method Name: bufferedReaderCreator
  * Details: Creates a bufferedReader for a specified execution in command prompt
  * @param execution: A string that contains the command that will be executed
  * @return inputBufferReader: An bufferedreader to read the data from the execution
  **/
  public static BufferedReader bufferedReaderCreator(String execution)throws IOException{
    Runtime rt = Runtime.getRuntime();
    Process pr = rt.exec(execution);
    InputStream input = pr.getInputStream();
    InputStreamReader inputReader = new InputStreamReader(input);
    BufferedReader inputBufferReader = new BufferedReader(new InputStreamReader((pr.getInputStream())));
    return inputBufferReader;
  }
}
