import java.sql.*;

public class BookRepository {

    // This class will be connected to DB. (Connection, statement, parameterised/prepared statement)


    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    // Creating connection with method:

    private void getConnection (){

        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Book_db","user123","user123");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    // Method for statement:
    private void getStatement (){

        try {

            this.statement = connection.createStatement();

        } catch (SQLException e){

            e.printStackTrace();

        }

    }

    // Method for prepared statement:

    private void getPreparedStatement (String query){

        try {

            this.preparedStatement = connection.prepareStatement(query);

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    // Method for creating data

    public void createTable (){

        getConnection();
        getStatement();

        String query="CREATE TABLE IF NOT EXISTS tbl_book " +
                "(id SERIAL," +
                "title VARCHAR(100)," +
                "author VARCHAR(50)," +
                "genre VARCHAR(50)," +
                "pageCount INT)";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    public void save (Book newBook){
        getConnection();
        String query = "INSERT INTO tbl_book (title, author, genre, pageCount) VALUES (?,?,?,?)";
        getPreparedStatement(query);

        try {

            preparedStatement.setString(1, newBook.getTitle());
            preparedStatement.setString(2, newBook.getAuthor());
            preparedStatement.setString(3, newBook.getGenre());
            preparedStatement.setInt(4, newBook.getPageCount());
            preparedStatement.executeUpdate();
            System.out.println("Book is registered successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                preparedStatement.close();
                connection.close();

            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void findAllBook(){

        getConnection();
        String query = "SELECT * FROM tbl_book";
        getStatement();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){

                System.out.println( "ID: " + resultSet.getInt("id"));
                System.out.println( "Title: " + resultSet.getString("title"));
                System.out.println( "Author: " + resultSet.getString("author"));
                System.out.println( "Genre: " + resultSet.getString("genre"));
                System.out.println( "Page Count: " + resultSet.getInt("pageCount"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    // find book by id:

    public Book findBookByID(int id) {

        getConnection();

        Book book = null;

        String query = "SELECT * FROM tbl_book WHERE id = ?";

        try {
            getPreparedStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                book = new Book();

                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setPageCount(resultSet.getInt("pageCount"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return book;

    }


    public void delete(int id) {

        getConnection();

        String query = "DELETE FROM tbl_book WHERE id = ?";

        getPreparedStatement(query);

        try {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0){
                System.out.println("Book with ID: " + id + " is deleted successfully.");
            } else {
                System.out.println("Book with ID: " + id + " is not found.");

            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void update(Book existBook) {

        getConnection();
        String query = "UPDATE tbl_book SET title = ?, author = ?, genre = ?,pageCount = ? WHERE id = ? ";
        getPreparedStatement(query);
        try {

            preparedStatement.setString(1, existBook.getTitle());
            preparedStatement.setString(2, existBook.getAuthor());
            preparedStatement.setString(3, existBook.getGenre());
            preparedStatement.setInt(4, existBook.getPageCount());
            preparedStatement.setInt(5, existBook.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0){
                System.out.println(rowsUpdated + " book is updated successfully");
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
