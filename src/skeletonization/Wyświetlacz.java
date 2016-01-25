package skeletonization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import static skeletonization.PointsMatrixesContainer.print_container;
import static skeletonization.Image.to_grey_scale;
import static skeletonization.Image.write_image;
import static skeletonization.Image.grey_scale_image_to_point_matrix;
import static skeletonization.Image.point_matrix_to_image;
import static skeletonization.PointsMatrix.points_matrix_to_elements_container;
import static skeletonization.PointsMatrix.print_pointsmatrix;
import static skeletonization.PointsMatrix.elements_container_to_points_matrix;
import static skeletonization.PointsMatrix.is_similar;
import static skeletonization.PointsMatrix.make_skeleton;
import static skeletonization.PointsMatrix.rotate_points_matrix;

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
    public JButton skeleton;
    public int background_color =255;
    // public ObsługaSiatki obsługa = new ObsługaSiatki();
    private JButton returner;
    Image originalImage;
    Image greyImage;

    public Wyświetlacz() throws IOException {
        originalImage = new Image();
        greyImage = new Image();
        //tymczasowo wyłaczone do zakończenia fazy prób i błędów
        initComponents();

        //do rozbicia na przyciski i w ogole do GUI, robocze
        /* File imageFile;
        //   imageFile = new File("lenna.bmp");
        imageFile = new File("reka.jpg");

       originalImage.image = ImageIO.read(imageFile);

        panel.image = originalImage.image;

        // write_image(originalImage.image);
        greyImage.image = to_grey_scale(panel.image);
        panel.image = greyImage.image;
        // write_image(panel.image);
        panel.repaint();

        PointsMatrix matrix;

        matrix = new PointsMatrix(panel.image.getWidth(), panel.image.getHeight());

        matrix = grey_scale_image_to_point_matrix(panel.image);
        PointsMatrixesContainer elementsContainer = new PointsMatrixesContainer(matrix.width, matrix.height);
        elementsContainer = points_matrix_to_elements_container(matrix);
        boolean change = true;
        Pair pair = new Pair(matrix.width, matrix.height);

      while (pair.change == true)  
        //for (int i = 0; i < 200; i++) 
      {
            pair = make_skeleton(elementsContainer);
            elementsContainer = points_matrix_to_elements_container(pair.image);
            // System.out.println(pair.change);
        }
            //print_container(elementsContainer);

        panel.image = point_matrix_to_image(pair.image);*/
       // write_image(panel.image);

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

        /*jTextArea1.setColumns(20);
         jTextArea1.setRows(5);
         jScrollPane1.setViewportView(jTextArea1);
         */
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

        start = new JButton("To greyscale");
        start.setPreferredSize(new Dimension(100, 100));
        this.add(start);

        start.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    startMouseClicked(evt);

                } catch (InterruptedException | IOException | IllegalArgumentException ex ) {
                    Logger.getLogger(Wyświetlacz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        returner = new JButton("return to normal");
        returner.setPreferredSize(new Dimension(100, 100));
        this.add(returner);

        returner.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    startMouseClickedReturn(evt);

                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(Wyświetlacz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        skeleton = new JButton("To skeleton");
        skeleton.setPreferredSize(new Dimension(100, 100));
        this.add(skeleton);

        skeleton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startMouseClickedSkeleton(evt);
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
            File imageFile;
            imageFile = file;
            try {
                originalImage.image = ImageIO.read(imageFile);
                //    write_image(originalImage.image);
                panel.image = originalImage.image;
            } catch (IOException e) {
                System.err.println("Blad odczytu obrazka");
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        panel.repaint();
    }

    private void startMouseClicked(java.awt.event.MouseEvent evt) throws InterruptedException, IOException {

        write_image(originalImage.image);
        greyImage.image = to_grey_scale(panel.image);
        panel.image = greyImage.image;
        write_image(originalImage.image);
        panel.repaint();

    }

    private void startMouseClickedReturn(java.awt.event.MouseEvent evt) throws InterruptedException, IOException {
        panel.image = originalImage.image;
        write_image(originalImage.image);
        panel.repaint();
    }

    private void startMouseClickedSkeleton(MouseEvent evt) {
         PointsMatrix matrix;

        matrix = new PointsMatrix(panel.image.getWidth(), panel.image.getHeight());

        matrix = grey_scale_image_to_point_matrix(panel.image);
        PointsMatrixesContainer elementsContainer = new PointsMatrixesContainer(matrix.width, matrix.height);
        elementsContainer = points_matrix_to_elements_container(matrix);
        boolean change = true;
        Pair pair = new Pair(matrix.width, matrix.height);

      while (pair.change == true)  
        //for (int i = 0; i < 200; i++) 
      {
            pair = make_skeleton(elementsContainer);
            elementsContainer = points_matrix_to_elements_container(pair.image);
            // System.out.println(pair.change);
        }
            //print_container(elementsContainer);

        panel.image = point_matrix_to_image(pair.image);
panel.repaint();//To change body of generated methods, choose Tools | Templates.
    }
}
