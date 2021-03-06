/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skeletonization;

/**
 *
 * @author Kamila
 */
public class PointsMatrix {

    int width;
    int height;
    myPoint matrix[][];

    public PointsMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new myPoint[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.matrix[i][j] = new myPoint(i, j);
            }
        }
    }

    //wypisanie macierzy punktów
    public static void print_pointsmatrix(PointsMatrix matrix) {
        for (int i = 0; i < matrix.width; i++) {
            for (int j = 0; j < matrix.height; j++) {
                System.out.print(" " + matrix.matrix[i][j].p + " ");
            }
            System.out.println();
        }

    }

    //zamiana macierz na kontener elementów
    public static PointsMatrixesContainer points_matrix_to_elements_container(PointsMatrix matrix) {

        int width;
        int height;
        width = matrix.width;
        height = matrix.height;

        PointsMatrix pointsMatrix;
        PointsMatrixesContainer elementsContainer = new PointsMatrixesContainer(width, height);

        int color = 0;

        int pixelColor = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pointsMatrix = new PointsMatrix(3, 3);
                int maxX = height - 1;
                int maxY = width - 1;
                int posX = i;
                int posY = j;

                int startPosX = posX - 1;
                int startPosY = posY - 1;
                int endPosX = posX + 1;
                int endPosY = posY + 1;
                int temp;
                int w = 0;
                int k = 0;

                for (int row = startPosX; row <= endPosX; row++) {
                    for (int col = startPosY; col <= endPosY; col++) {
                        temp = row * (maxY + 1) + col;

                        if (row >= 0 && col >= 0 && row < width && col < height) {
                            pixelColor = matrix.matrix[row][col].p;
                            color = pixelColor;
                        } else {
                            color = 255;
                        }
                        pointsMatrix.matrix[w][k].p = color;
                        k++;
                    }
                    k = 0;
                    w++;
                }
                elementsContainer.tab[i][j] = pointsMatrix;
            }
        }

        return elementsContainer;
    }

    //zamiana kontenera na macierz 
    public static PointsMatrix elements_container_to_points_matrix(PointsMatrixesContainer container) {
        PointsMatrix matrix = new PointsMatrix(container.width, container.height);
        for (int i = 0; i < container.width; i++) {
            for (int j = 0; j < container.height; j++) {
                matrix.matrix[i][j] = container.tab[i][j].matrix[1][1];
            }
        }
        return matrix;

    }

    //rotacja ellementu w lewo o 90 stopni
    public static PointsMatrix rotate_points_matrix(PointsMatrix matrix) {
        PointsMatrix matrix2 = new PointsMatrix(matrix.width, matrix.height);
        matrix2.matrix[0][0].p = matrix.matrix[0][2].p;
        matrix2.matrix[0][1].p = matrix.matrix[1][2].p;
        matrix2.matrix[0][2].p = matrix.matrix[2][2].p;
        matrix2.matrix[1][0].p = matrix.matrix[0][1].p;
        matrix2.matrix[1][1].p = matrix.matrix[1][1].p;
        matrix2.matrix[1][2].p = matrix.matrix[2][1].p;
        matrix2.matrix[2][0].p = matrix.matrix[0][0].p;
        matrix2.matrix[2][1].p = matrix.matrix[1][0].p;
        matrix2.matrix[2][2].p = matrix.matrix[2][0].p;
        return matrix2;

    }

    //porównianie macierzy punktów z elementem strukturalnym
    public static boolean compare(PointsMatrix matrix) {
        int[][] tab = new int[3][3];
        tab[0][0] = 255;
        tab[0][1] = 255;
        tab[0][2] = 255;
        tab[1][0] = 4;
        tab[1][1] = 0;
        tab[1][2] = 4;
        tab[2][0] = 0;
        tab[2][1] = 0;
        tab[2][2] = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != 1 && i != 0) {
                    if (tab[i][j] != matrix.matrix[i][j].p) {
                        return false;
                    }
                } else if (j != 0 && j != 2) {

                    if (tab[i][j] != matrix.matrix[i][j].p) {
                        return false;
                    }

                }
            }
        }

        return true;

    }

    // czterokrotne wywołanie funkcji porównującj i rotowanie elementu
    public static boolean is_similar(PointsMatrix matrix) {
        boolean is = false;
        for (int pom = 0; pom < 4; pom++) {
            is = compare(matrix);
            if (is == true) {
                return true;
            }
            matrix = rotate_points_matrix(matrix);
        }
        return false;
    }

    // szkieletyzacja
    public static Pair make_skeleton(PointsMatrixesContainer container) {
        Pair pair = new Pair(container.width, container.height);
        pair.change = false;

        int pom = 0;
        boolean is;

        for (int i = 0; i < container.width; i++) {
            for (int j = 0; j < container.height; j++) {
                PointsMatrix matrix = new PointsMatrix(3, 3);

                matrix = container.tab[i][j];
                pom = matrix.matrix[1][1].p;
                is = true;
                if (pom == 0) {
                    is = is_similar(matrix);
                    if (is == true) {
                        pom = 255;
                        pair.change = true;

                    }
                }
                pair.image.matrix[i][j].p = pom;
                pair.image.matrix[i][j].x = i;
                pair.image.matrix[i][j].y = j;

            }
        }
        return pair;

    }

}
