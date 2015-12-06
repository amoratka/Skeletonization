package skeletonization;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;

public class Wyświetlacz extends javax.swing.JFrame {

    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Open;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JFileChooser fileChooser;
    //public Siatka siatka = new Siatka();
    RysujPanel panel = new RysujPanel();
    public JButton start;
   // public ObsługaSiatki obsługa = new ObsługaSiatki();

    public Wyświetlacz() {
        initComponents();
    }

    private void initComponents() {
 this.setLayout(new FlowLayout());
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        fileChooser = new JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jMenu.setText("Opcje");

        Open.setText("Otwórz");
        Open.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                OpenActionPerformed(evt);

            } catch (IOException ex) {
                Logger.getLogger(Wyświetlacz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jMenu.add(Open);

        Exit.setText("Wyjdź");
        jMenu.add(Exit);

        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        start = new JButton("start");
        start.setPreferredSize(new Dimension(100, 100));
        this.add(start);

        start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    startMouseClicked(evt);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Wyświetlacz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

      //  panel.siatka = siatka;
        

        
        
        panel.setBorder(new LineBorder(Color.black, 1, true));
        panel.setBounds(0, 0, 100, 100);
        panel.setLayout(new GridLayout());
        panel.setPreferredSize(new Dimension(1000, 500));
        this.add(panel);

        pack();
    }

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //FileReader f = new FileReader(file);
            File imageFile;
            //imageFile = new File("first_test.bmp"  );
            imageFile = file ;
		try {
			panel.image = ImageIO.read(imageFile);
                        //this.add(panel);
		} catch (IOException e) {
			System.err.println("Blad odczytu obrazka");
		}
        } else {
            System.out.println("File access cancelled by user.");
        }
    }

    private void startMouseClicked(java.awt.event.MouseEvent evt) throws InterruptedException {
   /*/     new Thread() {
            @Override
            public void run() {
                int generacje = 1000;
                int czas = 1;
                for (int i = 1; i < generacje + 1; i++) {
                    try {
                        
                            Siatka nowaSiatka = ObsługaSiatki.nowaSiatka(panel.siatka);
                            panel.siatka = nowaSiatka;
                            TimeUnit.SECONDS.sleep(czas);
                            panel.repaint();

                            ObsługaSiatki.zapisz(nowaSiatka, "plik", i);
                       
                    } catch (InterruptedException | IOException ex) {
                        Logger.getLogger(Wyświetlacz.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }.start();*/
    }

}
