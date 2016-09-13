package contacts;

import java.io.*; 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
  
public class ReadWriteFile {   
	
	// Read all users from file
	public List<String> readFromfile() {
		
		Scanner input;
        List<String> user = new ArrayList<String>();
		try {
			// Read all users' information by delimiter 'tab'
			input = new Scanner(new FileReader("src/contacts/Users.txt")).useDelimiter("\\n*\t\\n*");
            //need to optimize                
            while(input.hasNext()) { 
            	user.add(input.next());
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// Write all users to file
	public void writeTofile(List<String> allUsers) {

		try{
            FileWriter writer = new FileWriter("src/contacts/Users.txt", true);
            //Clear the current file and then write it again
            BufferedWriter wr = new BufferedWriter(new FileWriter("src/contacts/Users.txt"));
            wr.flush(); 
            wr.close();
            
            int count = 1;
            for(String i : allUsers) {
          	  writer.write(i + "\t");
          	  if (count % 12 == 0) {
          		  writer.write('\n');
          	  }
          	  count++;
            }
            writer.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}
}  