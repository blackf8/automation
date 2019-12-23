//date july 18, 2019
//Automated wifi discovery code. It calls command prompt and executes a command
//that will list the full wifi profile of the device
import java.io.*;
import java.util.*;

public class automation{
  public static void main(String[] args)throws Exception{
    Runtime rt = Runtime.getRuntime();
    String commandOne = "netsh wlan show profile";
    Process pr = rt.exec(commandOne);
    BufferedReader input = new BufferedReader(new InputStreamReader((pr.getInputStream())));
    String line = "";
    String finale = "";
    int placement = 1; // shows that after line 9 the wifi's are listed and ends in a space
    //minus 10 from the total line amout to get the number of wifi networks
    //uncovered that you need to minus one more because it increments after printing
    while((line = input.readLine()) != null){
      //System.out.println(placement + " " + line);
      placement = placement + 1;
      finale = finale + "\n" + line;
    }
    //System.out.println(placement-11); // total number of wifi's available
    placement = placement - 11; // 18-11 = 7
    String[] data = dataSort(finale);
    int numWifi = data.length;
    fileCreator(data);

    String[] enkryptedPassArray = new String[placement];
    for(int i = 0; i<placement; i++){
    enkryptedPassArray[i] = i + passwordBreaker(data, i);
    }
    //System.out.println(Arrays.toString(enkryptedPassArray));
    //System.out.println(enkryptedPassArray.length);
    //System.out.println(Arrays.toString(data));
    //System.out.println(data.length);
    String[][] finalData = arrayCombiner(data, enkryptedPassArray);
    fileWriter(finalData);


    System.out.println("Would you like to delete the data?(1 = yes | 2 = no)");
    Scanner sc = new Scanner(System.in);
    int result = sc.nextInt();
    if(result == 1){
      System.out.println("File deleted!");
      fileEraser();
    }else if(result == 2){
    System.out.println("Data is stored in alpha.txt file!");
    }
  }
  public static String[] dataSort(String finale){
    //System.out.println(finale.length());
    String wifislicer = finale.substring(166); //issue
    System.out.println(wifislicer);
    String[] finished = wifislicer.split("    All User Profile     :");
    //System.out.println(Arrays.toString(finished));
    //System.out.println(finished[0]);
    //System.out.println(finished[7]);
    return finished;
  }
  public static void fileCreator(String[] file){
    try{
      File f = new File("C://Users//prabu//OneDrive//Desktop//Work//alpha.txt");
      f.createNewFile();
    }
    catch(Exception e){
      System.out.println("There was a lie!");
      e.printStackTrace();
    }
  }
  public static void fileWriter(String[][] finalData)throws IOException{
      File f = new File("C://Users//prabu//OneDrive//Desktop//Work//alpha.txt");
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);
      String contents = "";
      for(int i = 0; i<8; i++){
        contents = contents + "\n" + finalData[0][i] + " " + finalData[1][i];
      }
      //System.out.println(contents);
      bw.write((contents));
      bw.close();
  }
  public static String passwordBreaker(String[] data, int i)throws Exception{
    Runtime rt = Runtime.getRuntime();
    String commandTwo = "netsh wlan show profile"+ data[i] + " key = clear";
    Process pr = rt.exec(commandTwo);
    // make i = some input value created in the main method.
    BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
    // First use the getRunTime method and place that object into the process and execute the directory
    String line = "";
    String enkryptedPass = "";
    //System.out.println(commandTwo);
    while((line = br.readLine()) != null){

      //System.out.println(passwordCounter + " " + line);
      if(line.contains("Key Content")){
        enkryptedPass = line;
      }
    }
    return enkryptedPass;
  }
  public static String[][] arrayCombiner(String[] data, String[] enkryptedPassArray){
    String[][] finalData = new String[2][data.length];
    for(int i = 0; i<data.length; i++){
      finalData[0][i] = data[i];
      finalData[1][i] = enkryptedPassArray[i];
    }
    //System.out.println(Arrays.toString(finalData[0]));
    //System.out.println(Arrays.toString(finalData[1]));
    return finalData;
  }
  public static void fileEraser(){
    File file = new File("C://Users//prabu//OneDrive//Desktop//Work//alpha.txt");
    file.delete();
  }
}
