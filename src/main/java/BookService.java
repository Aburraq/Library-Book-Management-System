import java.util.Scanner;

public class BookService {

    // To access methods from repository class:

    BookRepository repository = new BookRepository();

    Scanner input = new Scanner(System.in);

    // Method to call the createTable from repository class.

    public void createTable (){

        repository.createTable();
    }

    // Method to register book:

    public void saveBook (){

        System.out.println("Title: ");
        String title = input.nextLine();

        System.out.println("Author: ");
        String author = input.nextLine();

        System.out.println("Genre: ");
        String genre = input.nextLine();

        System.out.println("Page Count: ");
        int pageCount = input.nextInt();

        input.nextLine(); // consume new line

        // After getting all data from the user we create a new object using constructor

        Book newBook = new Book(title, author, genre, pageCount);

        repository.save(newBook);


    }

    // bring all listed books
    public void getAllBooks (){
        repository.findAllBook();
    }

    // find book by id

    public Book getBookByID (int id){

        Book book = repository.findBookByID(id);
        if (book == null){
            System.out.println("There is not a book with this id: " + id);
        }
        return book;
    }

    // method to delete book by id:

    public void deleteBookByID (int id){

        repository.delete(id);

    }

    // method to update:

    public void updateBook (int id){

        // find book by id from the table:

        Book existBook = getBookByID(id);

        if (existBook == null){
            System.out.println("Book with ID: " + id + "is not found.");
        } else {
            // Ask all data from the user:

            System.out.println("Title: ");
            String title = input.nextLine();

            System.out.println("Author: ");
            String author = input.nextLine();

            System.out.println("Genre: ");
            String genre = input.nextLine();

            System.out.println("Page Count: ");
            int pageCount = input.nextInt();

            // Start updating existing book:

            existBook.setTitle(title);
            existBook.setAuthor(author);
            existBook.setGenre(genre);
            existBook.setPageCount(pageCount);

            // We can leave id unchanged.

            repository.update(existBook);

        }

    }


}
