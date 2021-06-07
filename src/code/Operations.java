package code;

import javafx.scene.Node;
import java.util.Random;

public class Operations {
    int [][] tab;
    int size;
    public Operations(int size){
        tab = new int[size][size];
        this.size = size;
    }
    public int [][] randomizer()
    {
        int x_poz,y_poz;
        int bombAmount;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++)
            {
                tab[i][j] = 0;
            }
        }
        Random random = new Random();
        if(size == 8) bombAmount = 10;
        else if(size == 16) bombAmount = 40;
        else bombAmount = 99;
        while(bombAmount > 0)
        {
            x_poz = random.nextInt(size);
            y_poz = random.nextInt(size);
            if(tab[x_poz][y_poz]!=-1)
            {
                placeMine(x_poz,y_poz);
                --bombAmount;
            }
        }
        return tab;
    }
    public void placeMine(int x, int y)
    {
        tab[x][y] = -1;
        for (int k = -1; k<2; k++)
            for (int l = -1; l<2; l++)
                {
                    if ((x+l)<0 || (y+k)<0 ) continue; //wyjdz bo krawedz
                    if ((x+l)>size-1 || (y+k)>size-1 ) continue; //wyjdz bo krawedz

                    if (tab[x+l][y+k]==-1) continue; //wyjdz bo mina
                    ++tab[x+l][y+k]; //zwieksz o 1
                }
    }
}
