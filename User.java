
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.io.*;

//after removing testing printing blocks, no need to import these:
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

//class definition
public class User {
    //vars
    private String email;
    private String password;
    private int userType;

    //make name more meaningful, but apart from that keep filepath separate to allow it to be modified in future
    String filePath = "users.ser";

    //constructor
    public User(){
        userType=0; //0 will be Customer
    }

    //deserialize & get existing hashmap
    public HashMap getUsersHashMap(){
        HashMap<String, String> users;
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
                users = (HashMap<String, String>) inputStream.readObject();
                return users;
            } 

            //need to go back and fix exception handling
            catch (IOException | ClassNotFoundException e) {
                // If the file doesn't exist, create a new HashMap
                users = new HashMap<>();
                return users;
            }
        }


    //create account
    public void createAccount(String email, String password){
       HashMap<String,String> users= getUsersHashMap();

       //print HashMap for testing purposes Only. Remove this block of Code
       System.out.println("Deserialized HashMap..");
            // Display content using Iterator
            Set set = users.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                System.out.print("key: "+ mentry.getKey() + " & Value: ");
                System.out.println(mentry.getValue());
            }
        
        //check if email already exists in the system
        if(users.containsKey(email)){
            System.out.println("Account already exists");
        }

        //otherwise register user and update the serialised Hashmap
        else{
            users.put(email,password);
            System.out.println("Yay");
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
                outputStream.writeObject(users);
                System.out.println("HashMap updated and saved successfully!");
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
                
    }

    //login
    public void login(String email, String password){
        HashMap<String,String> users= getUsersHashMap();

        //print HashMap for testing purposes Only. Remove this block of code
        System.out.println("Deserialized HashMap..");
            // Display content using Iterator
            Set set = users.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                System.out.print("key: "+ mentry.getKey() + " & Value: ");
                System.out.println(mentry.getValue());
            }

        //return no account found if email does not exist in the system
        if(!users.containsKey(email)){
            System.out.println("No account found");
        }

        //return invalid password if email exists but does not match the password in the system
        else if(!users.get(email).equals(password)){
            System.out.println("Invalid password");
        }

        //login user after validating correct email and password
        else{
            System.out.println("Login successful");
        }    }
}
