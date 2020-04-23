package com.example.demo.Repository;

import com.example.demo.model.GitprojectEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface gitProjectRepository extends JpaRepository<GitprojectEntity, String> {

//    User findByUserName(String userName);
//
//    User findByUserNameOrEmail(String username, String email);
//
//    @Transactional(timeout = 10)
//    @Modifying
//    @Query("update User set userName = ?1 where id = ?2")
//    int modifyById(String userName, Long id);
//
//    @Transactional
//    @Modifying
//    @Query("delete from User where id = ?1")
//    void deleteById(Long id);
//
//    @Query("select u from User u where u.email = ?1")
//    User findByEmail(String email);
//
//    @Query("select u from User u")
//    Page<User> findALL(Pageable pageable);
//
//    Page<User> findByNickName(String nickName, Pageable pageable);
//
//    Slice<User> findByNickNameAndEmail(String nickName, String email, Pageable pageable);


}