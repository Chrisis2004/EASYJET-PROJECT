package IO;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
public class FileIO {
    private File file;
    public int nLines;
    private String[] fileContent;

    public FileIO(String fileName) throws IOException
    {
        this.file = new File(fileName);
        this.nLines = findLineAmount();
        if(nLines!=0){
            fileContent = new String[this.nLines];
            fileContent = readFromFile();
        }
        
    }
    public int findLineAmount() throws IOException {
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
        lineNumberReader.skip(Long.MAX_VALUE);
        int totalLines = lineNumberReader.getLineNumber();
        lineNumberReader.close();
        return totalLines;
    } 
    public String[] readFromFile() throws IOException{
        int nLines = findLineAmount();
        if (nLines!=0) {
            BufferedReader fileReader = new BufferedReader(new FileReader(this.file));
            String[] linesFromFile= new String[nLines];
            for(int i=0; i<nLines;i++)
                linesFromFile[i]=fileReader.readLine();
            fileReader.close();
            return linesFromFile;
        }
        else {
            System.out.println("The file is void");
            return null;
        }
        
    }

    public int howManySplit(String separatorChar) throws IOException{
        BufferedReader fileReader = new BufferedReader(new FileReader(this.file));
        String[] inLines = fileReader.readLine().split(separatorChar);
        int splitNumbers = inLines.length;
        fileReader.close();
        return splitNumbers;
    }

    public String[][] readFromCSV(String separatorChar) throws IOException{
        BufferedReader fileReader = new BufferedReader(new FileReader(this.file));
        int split = howManySplit(separatorChar);
        int nLines = findLineAmount();
        String[][] result = new String[nLines][split];
        if (nLines!=0) {
            for(int z=0; z<nLines;z++){
                String[] inLines = fileReader.readLine().split(separatorChar);
                for (int i=0;i<split;i++) {
                    result[z][i] = inLines[i];
                }
            }
        } 
        else
            result = null;
        fileReader.close();
        return result;
    }
    
    public void writeOnCSV(String[][] toWrite, int nLines, int nSplits, String separatorChar) throws IOException
    {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.file));
        for (int i=0;i<nLines;i++){
            for(int z=0;z<nSplits;z++)
                fileWriter.write(toWrite[i][z] + separatorChar);
            fileWriter.write("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public void delateFile(){
        this.file.delete();
    }

    public void writeOnCSVAddOnly(String[][] toWrite, int nLines, int nSplits, String separatorChar) throws IOException
    {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.file));
        for (int i=0;i<this.nLines;i++){
            fileWriter.write(fileContent[i]);
            fileWriter.write("\n");
        }
        for (int i=0;i<nLines;i++)
            for(int z=0;z<nSplits;z++)
                fileWriter.write(toWrite[i][z] + separatorChar);
        fileWriter.flush();
        fileWriter.close();
    }

    public int thereIsOnCSV(String toSearch, int splitToSearch) throws IOException{
        String[][] fileContent = readFromCSV(";");
        for (int i=0;i<findLineAmount();i++){
            if(fileContent[i][splitToSearch].equals(toSearch))
                return i;
        }
        return -1;
    }
    public boolean thereIsInLineCSV(String toSearch, int nLine, int splitToSearch) throws IOException
    {
        String[][] fileContent = readFromCSV(";");
        return fileContent[nLine][splitToSearch].equals(toSearch);
    }
    public String getFromCSV (int nLine, int nSplit) throws IOException{
        String[][] fileContent = readFromCSV(";");
        String toReturn = fileContent[nLine][nSplit];
            return toReturn;
    }
    public void delateLineFromCSV(int nLineToDelate) throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.file));
        for (int i=0;i<this.nLines;i++){
            if (this.nLines == nLineToDelate)
                continue;
            fileWriter.write(fileContent[i]);
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

}
