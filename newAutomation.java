import java.io.*;
import java.util.*;
public class newAutomation{
  public static void main(String[] args){
    Runtime rt = Runtime.getRuntime();
    Process pr = rt.exec("netsh wlan show profile");
    InputStream input = pr.getIntputStream();
    InputStreamReader inputReader = new InputStreamReader(input);
    BufferedReader input = new BufferedReader(new InputStreamReader((pr.getInputStream())));
  }
}
