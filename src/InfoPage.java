import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class InfoPage extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JLabel lb;
    private JButton bttt;
    private JTextArea textArea1;

    DefaultListModel listModel = new DefaultListModel();
    List<String> list = new ArrayList<>();

    InfoPage(String username){
        setContentPane(mainPanel);
        setSize(600,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            String fileName = username + ".txt";
            String actual = null;
            actual = Files.readString(Path.of(fileName));
            String [] account = actual.split("\\n");
            //System.out.println("___________________________________");
            for (int i = 0; i<account.length ; i++){
                String[] ap = account[i].split(",");
                String user = ap[0];

                String password = ap[1];
                String url = ap[2];
                String com = ap[3];

//                System.out.println( user+
//                        password+
//                        url+
//                        com);

                textArea1.append("\n" + user+password+url+com);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bttt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textArea1.setText(" ");

                try {
                    String fileName = username + ".txt";
                    String actual = null;
                    actual = Files.readString(Path.of(fileName));
                    String[] account = actual.split("\\n");
                    for (int i = 0; i < account.length; i++) {
                        String[] ap = account[i].split(",");
                        String user = ap[0];

                        String password = ap[1];
                        String url = ap[2];
                        String comm = ap[3];
                        String keya = ap[4];
                        String iv1 = ap[5];

                        Decrypter decrypter = new Decrypter("ABCDEFGHIJKL");
                        String a = decrypter.aaa(user,password,url,comm, keya,iv1 );

                        textArea1.append("\n" + a);

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }


            }
        });
    }
}
