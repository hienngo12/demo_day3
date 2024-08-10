package example;

import config.SpringConfig;
import entity.BookEntity;
import repository.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");

    private static void createNewBook() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Java A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("ISIBF1219323");
        bookEntity.setNumberOfPage(234);
        bookEntity.setPrice(20.5);
        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));
        BookEntity result = Main.bookRepository.save(bookEntity);

        if (result != null) {
            System.out.println("A new book saved successfully, book ID =" + bookEntity.getId());
        }
    }

    private static void readBook() {
        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
        System.out.println("Found " + bookList.size() + " book(s) in the table book");
        System.out.println("They are:");
        for (BookEntity book : bookList) {
            System.out.println(book.toString());
        }
    }

    private static void readBook(int bookID) {
        BookEntity bookEntity = bookRepository.findById(bookID);
        if (bookEntity != null) {
            System.out.println("Found a book with book ID =" + bookID);
            System.out.println(bookEntity.toString());
        } else {
            System.out.println("Not found any book with book ID =" + bookID);
        }
    }

    private static void updateBook() {
        BookEntity bookEntity = bookRepository.findById(1);
        if (bookEntity != null) {
            System.out.println("Book data before updating");
            System.out.println(bookEntity.toString());

            bookEntity.setAuthor("Jame");
            bookEntity.setNumberOfPage(199);
            bookEntity.setPrice(201);
            bookRepository.save(bookEntity);

            System.out.println("Book data after updating");
            System.out.println(bookEntity.toString());
        } else {
            System.out.println("Book not found for updating");
        }
    }

    private static void deleteBook() {
        bookRepository.deleteById(1);

        BookEntity bookEntity = bookRepository.findById(2);
        if (bookEntity != null) {
            bookRepository.delete(bookEntity);
        }
        bookRepository.deleteAll();
    }

    public static void main(String[] args) {
        createNewBook();
        readBook();
        readBook(1);
        updateBook();
        deleteBook();
    }
}