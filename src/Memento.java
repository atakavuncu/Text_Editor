public class Memento {
    public String[] filePath = new String[1];

    Memento(String filePath){
        this.filePath[0] = filePath;
    }

    public String[] getFilePath(){
        return filePath;
    }
}
