/**
 * Created by Chris on 11/17/2016.
 *
 * Algorithm to find specific edge on undirected graph
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import static java.lang.System.out;
import java.lang.String;
import java.lang.Integer;
import java.lang.Object.*;
import java.lang.NumberFormatException;


public class Grapher {
    public static void main(String args[]) throws IOException {
        Grapher G = new Grapher();
        Scanner input = new Scanner(System.in);
        HashMap<String,String> [] list = new HashMap[1000000];
        out.println("Please enter the name of the Graph file. Please include \".txt\"");
        String filename = input.nextLine();
        G.reader(filename, list);
        out.println("To find if a path exists between x and y please provide the x and y vertices.");
        out.println("What is the x coordinate?");
        int x = input.nextInt();
        out.println("What is the y coordinate?");
        int y = input.nextInt();
        G.finder(x,y,list);
    }


    public HashMap<String,String> [] reader(String file_name, HashMap<String,String> [] list)throws IOException{
        String temp = "";
        FileReader file = new FileReader(file_name);
        BufferedReader reader = new BufferedReader(file);
        String[] edge = new String[2];
        while ((temp = reader.readLine()) != null) {
            edge = temp.split(",");
            input(edge, list);
        }
        return list;
    }

    public HashMap<String,String> [] input(String[] edge, HashMap<String,String> [] list ){
        int edge0 = Integer.valueOf(edge[0]);
        int edge1 = Integer.valueOf(edge[1]);
        if(list[edge0] != null){
        list[edge0].put(edge[1],null);
        }
        if(list[edge1] != null){
            list[edge1].put(edge[0],null);
        }
        if(list[edge0] == null){
        HashMap<String,String> hmap = new HashMap<String,String>();
        hmap.put(edge[1],null);
        list[edge0] = hmap;
        }
        if(list[edge1] == null){
            HashMap<String,String> hmap = new HashMap<String,String>();
            hmap.put(edge[0],null);
            list[edge1] = hmap;
        }
        return list;
    }

    public HashMap<String,String> [] finder(int x, int y, HashMap<String,String> [] list) {
        String ystring = Integer.toString(y);
        String xstring = Integer.toString(x);
        Boolean t = false;
        Stack<Integer> st = new Stack<Integer>();
        st.push(x);


        while(!st.isEmpty()){
            int d = st.peek();
            if(list[d] == null){
                st.pop();
            }
            String dstring = Integer.toString(d);
            if(list[d].containsKey(ystring)){
                out.println("The path between " + x + " and " + y + " exists.");
                t = true;
                break;
            }
            if(list[d].entrySet().isEmpty() != true) {
                for (int i = 0; i < (list[d].entrySet().size()); i++) {
                        Object[] temp = list[d].entrySet().toArray();
                        String tempstring = temp[i].toString();
                        int ind = tempstring.lastIndexOf("=");
                        String correcttemp = tempstring.substring(0,(ind));
                        int temp2 = Integer.parseInt(correcttemp);
                    if(list[temp2] != null) {
                        if (list[temp2].containsKey(ystring)) {
                            out.println("The path between " + x + " and " + y + " exists.");
                            t = true;
                            break;
                        }
                    }
                        st.push(temp2);
                }
            }
            st.pop();
            if(t==true) break;
        }

        if(t == false){
            out.println("The path between " + x + " and " + y + " does not exist.");
        }

       /* if(list[x] != null) {  //check if a pair exists in graph
            if (list[x].containsKey(ystring)) {
                out.println("The path between " + x + " and " + y + " exists.");
                t = false;
            }
        }else if (list[y] != null) {
            if (list[y].containsKey(xstring)) {
                out.println("The path between " + x + " and " + y + " exists.");
                t = false;
            }
        }
        if (t == true){
            out.println("The path between " + x + " and " + y + " does not exist.");
        }*/

        return list;
    }
}
