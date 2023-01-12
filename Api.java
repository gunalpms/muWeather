import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Api {

    public static String returnData(String cityname) throws Exception{

        String cityName = cityname;  
            String filePath = "deneme.py";      
            ProcessBuilder pb = new ProcessBuilder().command("python", "-u", filePath, cityName);        
            Process p = pb.start(); 
            BufferedReader in = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            StringBuilder buffer = new StringBuilder();     
            String line = null;
            while ((line = in.readLine()) != null){           
                buffer.append(line);
            }
            //int exitCode = p.waitFor();
            in.close();
            return buffer.toString();
    }
}
