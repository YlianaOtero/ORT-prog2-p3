/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practico3;

/**
 *
 * @author ylian
 */
public class Practico3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] matAux = {{10, 8, 12}, {5, 1, 12}, {12, 12, 6}};
        int[][] mat = {{1, 4, 1}, {2, 2, 2}, {1, 0, 3}};
        int[][] matBinaria = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}}; 
        int[][] matt = {{5, 8, 1}, {4, 8, 10}};
        
        int[][] acumulada = acumulada(matt);
        
        imprimirMatrizInt(matt);
        System.out.println();
        imprimirMatrizInt(acumulada);       
    }
    
    public static void imprimirArregloInt(int[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i] + " ");
        }
    }
    
    public static void imprimirMatrizInt(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] +" ");
            }
            System.out.println();
        }
    }
    
    /*1) Implementar un método que recibe una matriz que corresponde a las notas de 30 alumnos en 6
    materias y retorna el promedio general de calificaciones (considerando los 6 exámenes).*/
    public static int promedioGeneral(int[][] notas) {
        int sumaNotas = 0;
        int dimensiones = notas.length * notas[0].length;
        
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[0].length; j++) {
                sumaNotas+= notas[i][j];
            }
        }
        
        int resultado = sumaNotas/dimensiones;
        return resultado;
    }
    
    /*2) Dada una matriz rectangular, retornar un array que contenga la suma de sus columnas.
    Encabezado:*/
    
    public static int[] sumas(int[][] mat) {
        int[] resultado = new int[mat[0].length];
        
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = 0;
        }
        
        for (int j = 0; j < mat[0].length; j++) {
            for (int i = 0; i < mat.length; i++) {
                resultado[i] += mat[i][j];
            }
        }
        
        return resultado;
    }
    
    /*3) Se desea implementar un método que detecte si una matriz es “especial”. Una matriz es especial
    si se cumplen estas propiedades:
    - la suma de todos los valores de una fila, es la misma para todas las filas.
    - la suma de todos los valores de una columna, es la misma para todas las columnas.
    - las dos sumas anteriores son iguales entre sí y además es igual a la suma de los valores de la
    diagonal principal. */
    
    public static boolean especial(int[][] mat) {
        boolean esEspecial = false;
        
        int[] sumaDeTodasLasColumnas = sumas(mat);
        int[] sumaDeTodasLasFilas = sumasFilas(mat);
        
        if (sumaDeTodasLasColumnas[0] == sumaDeTodasLasFilas[0] &&
            arregloDeElementosIguales(sumaDeTodasLasColumnas) && 
            arregloDeElementosIguales(sumaDeTodasLasFilas)) {
            
            int sumaDiagonal = sumaDiagonalPrincipal(mat);
            esEspecial = sumaDiagonal == sumaDeTodasLasColumnas[0];
        }
        
        return esEspecial;
    }

    public static int[] sumasFilas(int[][] mat) {
        int[] resultado = new int[mat.length];
        
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = 0;
        }
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                resultado[i] += mat[i][j];
            }
        }
        
        return resultado;
    }
    
    public static int sumaDiagonalPrincipal(int[][] mat) {
        int resultado = 0;
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (i == j) {
                    resultado += mat[i][j];
                }
            }
        }
        
        return resultado;
    }
    
    public static boolean arregloDeElementosIguales(int[] arreglo) {
        boolean sonIguales = true;
        int anterior = arreglo[0];
        for (int i = 1; i < arreglo.length && sonIguales; i++) {
            if  (arreglo[i] != anterior) {
                sonIguales = false;
            }
        }
        
        return sonIguales;
    }
    
    
    
    /*4) Dada una matriz y una posición en esa matriz (dada por la fila y la columna) indicar si el elemento
    que se encuentra en esa posición está repetido o no en alguna de las diagonales que pasan por esa
    posición.*/
    public static boolean repetidos(int[][] mat, int fila, int col) {
        boolean repetido = false;
        int elem = mat[fila][col];
        
        String valor = String.valueOf(elem) + " ";
        
        String diagonalIzqDer = diagonalIzquierdaDerecha(mat, fila, col);
        
        if (diagonalIzqDer.contains(valor)) {
            repetido = true;
        } else {
            String diagonalDerIzq = diagonalDerechaIzquierda(mat, fila, col);
            repetido = diagonalDerIzq.contains(valor);
        }
        
        return repetido;
    }
    
    //Devuelve la diagonal de izq a der que pasa por [fila][col] de la matriz
    public static String diagonalIzquierdaDerecha(int[][] mat, int fila, int col) {
        String diagonal = " ";
        int filaAux = fila;
        int colAux = col;
        
        while (filaAux > 0 && colAux > 0) {
            filaAux--;
            colAux--;
        }
        
        for (int i = filaAux, j = colAux; i < mat.length && j < mat[0].length; i++, j++) {
            if (!(i == fila && j == col)) {
                diagonal += mat[i][j] + " ";
            }
        }
        
        return diagonal;
    }
    
    //Devuelve la diagonal de izq a der que pasa por [fila][col] de la matriz
    public static String diagonalDerechaIzquierda(int[][] mat, int fila, int col) {
        String diagonal = " ";
        int filaAux = fila;
        int colAux = col;
        
        while (filaAux > 0 && colAux < mat[0].length - 1) {
            filaAux--;
            colAux++;
        }
        
        for (int i = filaAux, j = colAux; i < mat.length && j >= 0; i++, j--) {
            if (!(i == fila && j == col)) {
                diagonal += mat[i][j] + " ";
            }
        }
        
        return diagonal;
    }
    
    /*5) Se recibe un mapa en una matriz rectangular de hasta 100 * 100. Representa a una única isla,
    donde 1 representa tierra y 0 representa agua. Las posiciones de tierra están conectadas
    horizontal/verticalmente. La isla está completamente rodeada de agua. Cada posición tiene lado 1.
    Determinar el perímetro de la isla.*/
    public static int calcularPerimetro(int[][] mat) {
        int perimetro = 0;
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    if (i == 0) {
                        perimetro++;
                    }
                    if (j == 0) {
                        perimetro++;
                    }
                    if (i == mat.length - 1 ) {
                        perimetro++;
                    }
                    if (j == mat[0].length - 1 ) {
                        perimetro++;
                    }
                    if (i < mat.length - 1 && mat[i+1][j] == 0) {
                        perimetro++;
                    }
                    if (i > 0 && mat[i-1][j] == 0) {
                        perimetro++;
                    }
                    if (j < mat[0].length - 1 && mat[i][j+1] == 0) {
                        perimetro++;
                    }
                    if (j > 0 && mat[i][j-1] == 0) {
                        perimetro++;
                    }
                }
            }   
        }
        
        return perimetro;
    }
    
    /* 6) Dada una matriz cualquiera, retornar la matriz acumulada. La matriz acumulada es el resultado de
    ir sumando los elementos de la matriz*/
    public static int[][] acumulada(int[][] mat) {
        int[][] acumulada = new int[mat.length][mat[0].length];
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                acumulada[i][j] = sumaValores(mat, i, j);
            }
        }
        
        return acumulada;
    }
    
    //Devuelve la suma de todos los valores de la matriz desde [0][0] hasta [fila][col]
    public static int sumaValores(int[][] mat, int fila, int col) {
        int suma = 0;
        
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                suma += mat[i][j];
            }
        }
        
        for (int j = 0; j <= col; j++) {
            suma += mat[fila][j];
        }
        
        return suma;
    }
    
    public static int[][] copiarMatrizInt(int[][] mat) {
        int[][] copia = new int[mat.length][mat[0].length];
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                copia[i][j] = mat[i][j];
            }
        }
        
        return copia;
    }
}
