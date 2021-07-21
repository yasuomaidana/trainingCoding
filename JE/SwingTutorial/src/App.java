import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame();//creating JFrame

        JButton b = new JButton("Button");

        b.setBounds(130,100,100,40);//x,y axis width and height
        f.add(b);
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
