package skeletonization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static skeletonization.Image.to_grey_scale;
import static skeletonization.Image.write_image;
import static skeletonization.Image.grey_scale_image_to_point_matrix;
import static skeletonization.Image.point_matrix_to_image;
import static skeletonization.PointsMatrix.points_matrix_to_elements_container;
import static skeletonization.PointsMatrix.make_skeleton;

public class GUI extends javax.swing.JFrame {

    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Open;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JFileChooser fileChooser;

    DrawPanel panel = new DrawPanel();
    DrawPanel panel2 = new DrawPanel();

    public JButton togreyscale;
    public JButton skeleton;
    public JButton write;
    public JButton bin;
    public JButton returner;
    RadioListener RadioListener;
    JRadioButton white;
    JRadioButton black;

    PointsMatrix matrix;

    Image originalImage;
    Image greyImage;
    Image binImage;

    public int backgroundColor = 255;
    public int threshold = 128;

    public JLabel headerLabel;
    public JPanel northpanel;
    public JPanel northpanel1;

    public GUI() throws IOException {
        originalImage = new Image();
        greyImage = new Image();
        binImage = new Image();
        //tymczasowo wyłaczone do zakończenia fazy prób i błędów
        initComponents();

    }

    private void initComponents() {
        this.setLayout(new FlowLayout());

        jMenuBar = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        fileChooser = new JFileChooser();

        //panel z wyborem koloru tła
        JPanel northPanel = new JPanel(new GridLayout(2, 1));

        JPanel backgroundColorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backgroundColorPanel.add(new JLabel("Kolor tła : "));
        northPanel.add(backgroundColorPanel);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        white = new JRadioButton("biały");
        black = new JRadioButton("czarny");
        white.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(white);
        group.add(black);

        radioPanel.add(white);
        radioPanel.add(black);

        northPanel.add(radioPanel);

        RadioListener = new RadioListener();
        white.addActionListener(RadioListener);
        black.addActionListener(RadioListener);

        //JMenuBar 
        jMenu.setText("Opcje");

        Open.setText("Otwórz");
        Open.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                OpenActionPerformed(evt);

            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jMenu.add(Open);

        Exit.setText("Wyjdź");
        Exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jMenu.add(Exit);

        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //przyciska zamiany do skali szarości
        togreyscale = new JButton("Skala szarośći");
        togreyscale.setPreferredSize(new Dimension(150, 100));
        togreyscale.setEnabled(false);
        togreyscale.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    startMouseClicked(evt);

                } catch (InterruptedException | IOException | IllegalArgumentException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //przycisk wyświetlenia obrazka po binaryzacji
        bin = new JButton("Obraz zbinaryzowany");
        bin.setEnabled(false);
        bin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    startMouseClickedBin(evt);

                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //przycisk wyświetlenia obrazka w orginalnych kolorach
        returner = new JButton("Obraz orginalny");
        returner.setEnabled(false);
        returner.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    startMouseClickedReturner(evt);

                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //przycisk wywołujący szkieletyzację
        skeleton = new JButton("Szkieletyzacja");
        skeleton.setEnabled(false);
        skeleton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startMouseClickedSkeleton(evt);
            }

        });

        //przycisk zapisujący obrazek do folderu pod nazwą greyscale.bmp
        write = new JButton("Zapis do pliku");
        write.setEnabled(false);
        write.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startMouseClickedWrite(evt);
            }

        });

        //suwak do skaly szarości
        JPanel southPanel = new JPanel(new GridLayout(1, 1));
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.add(new JLabel("Wartość progowa : "));
        southPanel.add(labelPanel);

        JSlider slider = new JSlider(JSlider.VERTICAL, 0, 255, threshold);
        slider.setMinorTickSpacing(17);
        slider.setMajorTickSpacing(85);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        southPanel.add(slider);
        slider.addChangeListener(new SliderListener());

        //panel z przyciskami
        JPanel middlePanel = new JPanel(new GridLayout(3, 2));
        middlePanel.add(togreyscale);
        middlePanel.add(bin);
        middlePanel.add(skeleton);
        middlePanel.add(write);
        middlePanel.add(returner);

        //panel z obrazkami 
        panel.setBorder(new LineBorder(Color.white, 1, true));
        panel.setBounds(0, 0, 100, 100);
        panel.setLayout(new GridLayout());
        panel.setPreferredSize(new Dimension(500, 500));

        panel2.setBorder(new LineBorder(Color.white, 1, true));
        panel2.setBounds(0, 0, 100, 100);
        panel2.setLayout(new GridLayout());
        panel2.setPreferredSize(new Dimension(500, 500));

        //układanie paneli  
        //panel z obrazkami
        JPanel imagePanel = new JPanel(new FlowLayout());
        imagePanel.add(panel);
        imagePanel.add(panel2);
        this.add(imagePanel, BorderLayout.WEST);

        //panel do kontroli
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(southPanel);
        controlPanel.add(middlePanel);
        controlPanel.add(northPanel);
        this.add(controlPanel, BorderLayout.EAST);
        this.setPreferredSize(new Dimension(1800, 700));
        this.pack();
    }

    // wczytywanie obrazka
    private void OpenActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            File imageFile;
            imageFile = file;

            try {
                originalImage.image = ImageIO.read(imageFile);
                String name = imageFile.getName();
                originalImage.name = name;
                panel.image = originalImage.image;

                greyImage = new Image();
                panel2.image = greyImage.image;
                panel2.repaint();

                togreyscale.setEnabled(true);
                skeleton.setEnabled(false);
                write.setEnabled(false);
                bin.setEnabled(false);
                returner.setEnabled(false);

            } catch (IOException e) {
                System.err.println("Blad odczytu obrazka");
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        panel.repaint();
    }

    //zmiana na skalę szarości
    private void startMouseClicked(java.awt.event.MouseEvent evt) throws InterruptedException, IOException {

        if (togreyscale.isEnabled()) {
            greyImage.image = to_grey_scale(originalImage.image);

            panel.image = greyImage.image;

            panel.setPreferredSize(new Dimension(panel.image.getWidth(), panel.image.getHeight()));
            panel.repaint();

            bin.setEnabled(true);
            returner.setEnabled(true);
            skeleton.setEnabled(true);
        }

    }

    // wyświetlenie orginalne obrazka
    private void startMouseClickedReturner(java.awt.event.MouseEvent evt) throws InterruptedException, IOException {
        if (returner.isEnabled()) {

            panel.image = originalImage.image;

            panel.setPreferredSize(new Dimension(panel.image.getWidth(), panel.image.getHeight()));

            panel.repaint();
        }
    }

    //wyświetla obrazek zbinaryzowany
    private void startMouseClickedBin(java.awt.event.MouseEvent evt) throws InterruptedException, IOException {
        if (bin.isEnabled()) {

            matrix = new PointsMatrix(panel.image.getWidth(), panel.image.getHeight());

            matrix = grey_scale_image_to_point_matrix(greyImage.image, backgroundColor, threshold);
            binImage.image = point_matrix_to_image(matrix);

            panel.image = binImage.image;

            panel.setPreferredSize(new Dimension(panel.image.getWidth(), panel.image.getHeight()));

            panel.repaint();
        }
    }

    // szkieletyzacja
    private void startMouseClickedSkeleton(MouseEvent evt) {
        if (skeleton.isEnabled()) {
            PointsMatrixesContainer elementsContainer = new PointsMatrixesContainer(matrix.width, matrix.height);

            elementsContainer = points_matrix_to_elements_container(matrix);

            boolean change = true;
            Pair pair = new Pair(matrix.width, matrix.height);
            while (pair.change == true) {
                pair = make_skeleton(elementsContainer);
                elementsContainer = points_matrix_to_elements_container(pair.image);

            }

            panel2.image = point_matrix_to_image(pair.image);

            panel2.setPreferredSize(new Dimension(panel2.image.getWidth(), panel2.image.getHeight()));

            panel2.repaint();//To change body of generated methods, choose Tools | Templates.

            write.setEnabled(true);

        }
    }

    // wybor tła
    class RadioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (white.isSelected()) {
                backgroundColor = 255;
            } else if (black.isSelected()) {
                backgroundColor = 0;
            }
        }
    }

    //zapis do pliku
    private void startMouseClickedWrite(MouseEvent evt) {
        if (write.isEnabled()) {
            try {
                write_image(panel2.image, originalImage.name);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // suwak wartości progu 
    class SliderListener implements ChangeListener {

        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            threshold = (int) source.getValue();
        }
    }
}
