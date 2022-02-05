package application;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


 public class LifeGameController {
	 int count=0;//helps me to know if it's the first time that the user press on the button
	 int[][] grid= new int[10][10];// initialize the matrix
	 Random random = new Random();
     int cellSize;
	 int rows = 10;
     int cols = 10;
     GraphicsContext gc;

     @FXML
     private Button myButton;

     @FXML
     private Canvas myCanvas;

     @FXML
     private Label myLable;

    
    //initialize the matrix in the first time randomly
    public void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.grid[i][j] = this.random.nextInt(2);//random initialize with 1 or 0 which mean life or die
            }
        }
        draw();//draw the matrix 
    }
    //draw the matrix base on the initialize or base on the former matrix
    private void draw() {
        // clear graphics
        gc.setFill(Color.LAVENDER);
        gc.fillRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());

        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 1)
                {
                    // first rect will end up becoming the border
                    gc.setFill(Color.gray(0.5, 0.5));
                    gc.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    gc.setFill(Color.PURPLE);//fill the cell with the color
                    gc.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                }
                else
                {
                    gc.setFill(Color.gray(0.5, 0.5));
                    gc.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    gc.setFill(Color.LAVENDER);//fill the cell with the color
                    gc.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                }
            }
        }
    }
  
    //initialize the grid for the next generation
    public void nextGeneration() {
        int[][] next = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = countAliveNeighbors(i, j,this.grid);

                if (neighbors == 3) {
                    next[i][j] = 1;
                }else if (neighbors < 2 || neighbors > 3) {
                    next[i][j] = 0;
                }else {
                    next[i][j] = this.grid[i][j];
                }
            }
        }
        this.grid = next;
        draw();
    }
    //count the live neighbors of the cell
    private int countAliveNeighbors(int i, int j,int [][]grid) {
        int sum = 0;
        int iStart = i == 0 ? 0 : -1;
        int iEnd = i == grid.length - 1 ? 0 : 1;
        int jStart = j == 0 ? 0 : -1;
        int jEnd = j == grid[0].length - 1 ? 0 : 1;

        for (int k = iStart; k <= iEnd; k++) {
            for (int l = jStart; l <= jEnd; l++) {
                sum += grid[i + k][l + j];
            }
        }

        sum -= grid[i][j];

        return sum;
    }


    @FXML
    void ActionEvent(ActionEvent event) {
    	gc = myCanvas.getGraphicsContext2D();
        
        //size for each cell
        this.cellSize=(int) ((myCanvas.getWidth())/10);
    	if(count==0)//the first time of pressing the button mean that we need to initialize the mat
    	{
    		init();
    		count+=1;
    		
    	}
    	else//the next generation
    	{
    		nextGeneration();
    		count+=1;
    	}
    	}

}