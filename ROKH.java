package rokh;
import java.util.Scanner;
import java.util.ArrayList;
public class ROKH {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s=input.nextLine();
        String [] paravan=s.split(" ");
        int maxi=0,maxj=0,u=0,h=0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<paravan.length;i++) {
            paravan[i].trim();
            if(!paravan[i].isEmpty()){
                list.add(Integer.parseInt(paravan[i]));
                if(maxi<=list.get(i) && i%2==0){
                    maxi=list.get(i);
                    continue;
                }
                if(maxj<=list.get(i) && i%2==1){
                    maxj=list.get(i);
                }
            }
        }
        int [][] matrix = new int [maxi][maxj];
        
        for (int  i=0;i<list.size();i++) {
            if(i%2==0){
                list.set(i,maxi-list.get(i));
                u=list.get(i);
                continue;
            }else{
                list.set(i,list.get(i)-1);
                h=list.get(i);
            }
            matrix[u][h]=1;
        }
        
        System.out.println("Your coordinates:");
        for (int i = 0; i < maxi; i++) {
            for (int j = 0; j < maxj; j++) 
                System.out.print(matrix[i][j]+"\t");
            System.out.print("\n");
        }
           System.out.print("\n");
        
        //System.out.println(Rook(matrix, maxi, maxj));
        
        
        char [] res = Rook(matrix, maxi, maxj).toCharArray();
        int countx=0,countp=0;
        for (int i = 0;i < res.length; i++){
            if(res[i]=='x') countx++;
        }
        
        int [] co = new int [countx+1];
        
        for (int i = 0; i < res.length; i++) {
            
            if(res[i]=='+') {
                continue;
            }
            
            if(res[i]==')'){
                countp--;
                continue;
            }
            
            if(res[i]=='x' && i<=res.length-1 && res[i+1]!='('){
                co[countp+1]++;
            }
            
            if(res[i]=='x' && i<=res.length-1 && res[i+1]=='('){
                countp++;
            }
            
            if(res[i]=='1'){
                co[countp]++;
            }
        }
        
        String ans ="";
        for (int i = 1; i < co.length; i++) {
            if(co[i]>0){
                ans += "+" + co[i] + "x" + "^" + i ;
            }
        }
        System.out.println("Rook polynomial is : 1"+ans);
        
        
        System.out.println("Do you want to know there is how many possible ways to put n Rooks on the board?\nif yes press y else press n");
        String te=input.nextLine();
        if("Y".equals(te) || "y".equals(te)){
            System.out.print("Enter The Number Of Rooks :");
            int Rook = input.nextInt();
            if(Rook<co.length)
            System.out.printf("%d \n", co[Rook]);
            else
            System.out.println("0");
        }
         else if("N".equals(te) || "n".equals(te)){
             return ;
        }
    }// end of main
    
    
   public static String Rook(int Matrix[][] ,int maxi ,int maxj)
   {
        int counter = 0;
        boolean flag=false;
        for(int k1 = 0 ; k1 < maxi ; k1++){
            for(int k2 = 0 ; k2 < maxj ; k2++){
                if(Matrix[k1][k2] == 1){
                    counter++;
                    if(counter>1){
                        flag=true;
                        break;
                    }
                }
            }
            if(flag)
                break;
        }
        if(counter == 1)
           return "x+1";
       
        else if (counter == 0)
           return "1";
       
        int[][] First = new int[maxi][maxj];//omitting the coordinate itself;
        Copy(Matrix , First);
        int[][] Second = new int[maxi][maxj];//omiting the whole row and column together;
        Copy(Matrix , Second);
        CoordinateRemove(First,maxi,maxj);//this method removes the coordinate
        RemoverRowColumn(Second,maxi,maxj);//this method removes row and column of the coordinate
        return Rook(First , maxi , maxj) + "+" + "x(" + Rook(Second , maxi , maxj ) + ")";
    }
    
    public static int[][] CoordinateRemove(int First[][] , int Maxi,int Maxj)
    {
        for(int i = 0 ; i < Maxi ; i++){
            for(int j = Maxj - 1 ; j >= 0 ; j--){
                if(First[i][j] == 1){
                    First[i][j] = 0;//done;
                    return First;
                }
            }
        }
        return First;
    }
    
    public static int[][] RemoverRowColumn(int Second[][] , int Maxi,int Maxj)
    {
        for (int i = 0 ; i < Maxi ; i++){
            for (int j = Maxj - 1 ; j >= 0 ; j--)
            {
                if(Second[i][j] == 1)
                {                        
                    for(int k = 0 ; k < Maxj ; k++)                           
                        Second[i][k] = 0;
                        
                    for(int u = 0 ; u < Maxi ; u++)                       
                        Second[u][j] = 0;                     
                    
                    return Second;
                }
            }
        }
        return Second;
    }
    
    public static int[][] Copy(int Matrix1[][] , int Matrix2[][])
    {
        for(int i = 0 ; i < Matrix1.length ; i++)
            for(int j = 0 ; j < Matrix1[i].length ; j++)
                Matrix2[i][j] = Matrix1[i][j];
         
        return Matrix2;
    }
}