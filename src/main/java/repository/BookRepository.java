    package repository;

    import entity.BookEntity;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface BookRepository extends CrudRepository<BookEntity, Integer> {

        BookEntity findById(int bookID);

        void deleteById(int id);
    }