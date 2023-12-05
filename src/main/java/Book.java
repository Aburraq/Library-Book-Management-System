public class Book {

    // Create a POJO

    // field to be created : id, title, author, genre, pageCount

    private int id;
    private String title;
    private String author;
    private String genre;
    private int pageCount;

    // constructors with parameters
    public Book(String title, String author, String genre, int pageCount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount;
    }
    public Book() {
    }

    // getter &  setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    // .toString method


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }
}
