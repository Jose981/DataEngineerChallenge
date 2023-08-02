package Laberinto;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Test3 {
	
		//Movimientos posibles 
	    private static int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	    //Metodo recursivo que va a ir resolviendo el laberinto celda a celda
	    public static int resolverLaberinto(char[][] laberinto, int startX, int startY, int endX, int endY) {
	        int rows = laberinto.length;
	        int cols = laberinto[0].length;

	        Queue<int[]> queue = new LinkedList<>();
	        queue.offer(new int[]{startX, startY, 0}); // Coordenadas iniciales y pasos

	        boolean[][] visited = new boolean[rows][cols];
	        visited[startX][startY] = true; // Marcamos la posición inicial como visitada

	        while (!queue.isEmpty()) {
	            int[] current = queue.poll();
	            int x = current[0];
	            int y = current[1];
	            int steps = current[2];

	            if (x == endX && y == endY) { // Llegamos a la posición final del laberinto
	                return steps;
	            }

	            // Intentar moverse en las direcciones arriba, abajo, izquierda y derecha
	            for (int[] move : moves) {
	                int newX = x + move[0];
	                int newY = y + move[1];

	                if (esMovimientoValido(newX, newY, laberinto, visited)) {
	                    queue.offer(new int[]{newX, newY, steps + 1});
	                    visited[newX][newY] = true;
	                }
	            }
	        }

	        return -1; // No se encontró un camino
	    }

	    public static boolean esMovimientoValido(int x, int y, char[][] laberinto, boolean[][] visited) {
	        int rows = laberinto.length;
	        int cols = laberinto[0].length;

	        if (x < 0 || x >= rows || y < 0 || y >= cols) {
	            return false; // Fuera de límites
	        }

	        if (laberinto[x][y] == '#' || visited[x][y]) {
	            return false; // Choque con obstáculo o ya visitado
	        }

	        return true;
	    }

	    //Metodo para mostrar el laberinto resuelto
	    public static void mostrarLaberintoConCamino(char[][] laberinto) {
	        for (char[] fila : laberinto) {
	            for (char celda : fila) {
	                System.out.print(celda + " ");
	            }
	            System.out.println();
	        }
	    }
	public static void main(String[] args) {
	     char[][] laberinto = {
	    		 {'.', '.', '.'},
	             {'.', '.', '.'},
	             {'.', '.', '.'}
	       };

	        int startX = 0; // Coordenadas iniciales
	        int startY = 0;

	        int endX = laberinto.length - 1; // Coordenadas finales
	        int endY = laberinto[0].length - 1;

	        int result = resolverLaberinto(laberinto, startX, startY, endX, endY);
	        if (result == -1) {
	            System.out.println("No se puede llegar a la meta.");
	        } else {
	            System.out.println("Se encontró el camino en " + result + " pasos:");
	            mostrarLaberintoConCamino(laberinto);
	        }
	    }
	}
