import java.util.Scanner;

public class Main {
    static char[][] board=new char[2][5];
    static Scanner sc=new Scanner(System.in);
    static boolean playing=true;
    static boolean computer;
    public static void main(String[] args) {
        System.out.println("Enter 1 for a player and 0 for a computer");
        if(sc.nextInt()==0) computer=true;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                board[i][j]=' ';
            }
        }
        if(Math.random()>0.5&&computer) {
            computerPlay('O');
        } else display();
        while(playing) {
            playerPlay('X');
            if(!playing) break;
            if(computer) {
                computerPlay('O');
            } else {
                playerPlay('O');
            }
        }
        System.out.println("Game end");
    }
    public static void playerPlay(char player) {
        System.out.println("Enter a set of 2 numbers");
        while(true) {
            String input=sc.nextLine();
            String[] list=input.split(" ");
            try {
                if(list.length>=2&&Integer.parseInt(list[0])<board[0].length&&Integer.parseInt(list[1])<board.length&&Integer.parseInt(list[0])>=0&&Integer.parseInt(list[1])>=0) {
                    if(board[Integer.parseInt(list[1])][Integer.parseInt(list[0])]==' ') {
                        playPiece(player, Integer.parseInt(list[0]), Integer.parseInt(list[1]));
                    } else Integer.parseInt("a");
                } else Integer.parseInt("a");
                break;
            } catch(NumberFormatException e) {
                System.out.println("Please enter a valid set of 2 numbers");
            }
        }
    }
    public static void computerPlay(char player) {
        System.out.println("Computer's turn");
        int temp=0;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j]==' ') temp++;
            }
        }
        temp*=Math.random();
        int count=0;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j]==' ') count++;
                if(count==temp+1) {
                    playPiece(player, j, i);
                    count++;
                }
            }
        }
    }
    public static void playPiece(char player, int x, int y) {
        board[y][x]=player;
        display();
        boolean won=true;
        for(int i=0; i<board.length; i++) {
            if(board[i][x]!=player) won=false;
        }
        if(won) win(player);
        won=true;
        for(int i=0; i<board[0].length; i++) {
            if(board[y][i]!=player) won=false;
        }
        if(won) win(player);
        if(board.length==board[0].length) {
            won=true;
            for(int i=0; i<board.length; i++) {
                if(board[i][i]!=player) won=false;
            }
            if(won) win(player);
            won=true;
            for(int i=0; i<board.length; i++) {
                if(board[board.length-1-i][i]!=player) won=false;
            }
            if(won) win(player);
        }
    }
    public static void display() {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                System.out.print(" "+board[i][j]+" ");
                if(j!=board[i].length-1) System.out.print("|");
            }
            if(i!=board.length-1) {
                System.out.print("\n---");
                for(int j=0; j<board[0].length-1; j++) {
                    System.out.print("+---");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
    public static void win(char player) {
        if(playing) System.out.println(player+" has won!");
        playing=false;
    }
}
