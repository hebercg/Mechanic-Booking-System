import javax.swing.JOptionPane;

public class CustomerApp {
    public static void main(String[] args){
        //first select login/signup
        //then select menu

        //vars
        int signUpLoginSelect;
        String email;
        String password;

        //instantiate User class

        User user = new User();

        String [] signUpLoginOptions = {"Register","Login"};
        signUpLoginSelect = JOptionPane.showOptionDialog(null,"Please select","Register or Login",0,2,null,signUpLoginOptions,signUpLoginOptions[0]);
        email = JOptionPane.showInputDialog("Enter your email address");
        password = JOptionPane.showInputDialog("Enter your password");

        if(signUpLoginSelect==0){
            user.createAccount(email,password);
        }
        else if(signUpLoginSelect==1){
            user.login(email,password);
        }
        else{
            System.out.println("Operation cancelled");
        }

        //menuSelect to follow later
    }
}
