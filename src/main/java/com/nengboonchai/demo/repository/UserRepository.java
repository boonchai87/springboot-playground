package com.nengboonchai.demo.repository;

import com.thaiitwork.model.IdsOnly;
import com.thaiitwork.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);

    User findByUserName(String username);
    //List<User> findAll();

   /*
   // native query
   @Query(
            value =
                    "insert into Users (name, age, email, status) values (:name, :age, :email, :status)",
            nativeQuery = true)
    void insertUser(@Param("name") String name, @Param("age") Integer age,
                    @Param("status") Integer status, @Param("email") String email);*/

    // jpql
    /*@Query("update User u set u.status = :status where u.name = :name")
    int updateUserSetStatusForName(@Param("status") Integer status,
                                   @Param("name") String name);*/

    // https://stackoverflow.com/questions/70668990/get-result-from-native-query-using-java-interface
    @Query(value = " select userName as userName,email as email  from User ")// must use alias
    //List<IdsOnly> getAllOnlyIds(@Param("offset") int offset, @Param("limit") int limit);
    List<IdsOnly> getAllOnlyIds();

}
