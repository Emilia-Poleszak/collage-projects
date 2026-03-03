import java.io.*;
import java.util.*;

/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

public class CSVConvert {
    private String input;
    private String output;
    private String separator1;
    private String separator2;
    private final List<String[]> csv = new ArrayList<>();

    public String getInput(){return input;}
    public String getOutput(){return output;}
    public String getSeparator1(){return separator1;}
    public String getSeparator2(){return separator2;}

    public void setInput(String input){this.input = input;}
    public void setOutput(String output){this.output = output;}
    public void setSeparator1(String separator1) {this.separator1 = separator1;}
    public void setSeparator2(String separator2) {this.separator2 = separator2;}

    public void read() {
        try (Scanner read = new Scanner(new File(this.getInput()))){
            String row;
            while(read.hasNextLine()){
                row = read.nextLine();
                String[] data = row.split(getSeparator1());
                csv.add(data);
            }
        } catch (IOException e){System.out.println("File not found.");}
    }

    public void write() {
        try (FileWriter write = new FileWriter(this.getOutput())){
            for (String[] row : csv) {
                write.append(String.join(getSeparator2(), row));
                write.append("\n");
            }
        } catch (IOException e){System.out.println("File not found.");}
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInput(), getSeparator1(), getOutput(), getSeparator2());
    }

    public String toString(){
        if(csv.isEmpty()) return "Data from CSV file was not loaded.";
        else{
            StringBuilder csvData = new StringBuilder("CSV file content:\n");
            for (String[] row : csv) {
                for(String value : row){
                    csvData.append(value).append(";");
                }
                csvData.append("\n");
            }
            return csvData.toString();
        }
    }

    public String getValue(int row, int column) {
        if(csv.isEmpty()) return "Data from CSV file was not loaded.";
        else {
            String[] value = new String[0];
            String s = "";
            try {
                row--;
                value = csv.get(row);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("File does not contain this many rows.");
            }
            try {
                column--;
                s = value[column];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File does not contain this many columns.");
            }
            return "Requested element: " + s + "\n";
        }
    }

    public static void main(String[] args) {
        CSVConvert csvConvert = new CSVConvert();

        if(args.length < 1) {System.out.println("Invalid input arguments.");}
        else {
            try{
                csvConvert.setInput(args[0]);
                csvConvert.setSeparator1(args[1]);
                csvConvert.setOutput(args[2]);
                csvConvert.setSeparator2(args[3]);
            } catch(IllegalArgumentException e){System.out.println("Invalid input arguments.");}

            csvConvert.read();
            csvConvert.write();
            Scanner s = new Scanner(System.in);
            System.out.print("Enter the row number: ");
            int row = s.nextInt();
            System.out.print("Enter the column number: ");
            int column = s.nextInt();
            System.out.println(csvConvert.getValue(row, column));
            System.out.println(csvConvert);
        }
    }
}
