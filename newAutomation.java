import java.io.*;
import java.util.*;
public class newAutomation{
  public static void main(String[] args){
    Runtime rt = Runtime.getRuntime();
    Process pr = rt.exec("netsh wlan show profile");
    InputStream input = pr.getIntputStream();
    InputStreamReader inputReader = new InputStreamReader(input);
    BufferedReader input = new BufferedReader(new InputStreamReader((pr.getInputStream())));
    String finalReturn = null;
    finalReturn = FileToString(input);
  }
  public static String FileToString(BufferedReader input){
    String current = null;
    while((current = input.readLine()) != null){

      
    }
  }
}
