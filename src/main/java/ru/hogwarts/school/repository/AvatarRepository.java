package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Avatar;

import java.util.Collection;
import java.util.Optional;


public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findAvatarByStudent_Id(Long id);
    Optional<Avatar> findByStudent_Id(Long id);

//    @Query
//    Collection<Avatar> getAllAvatar(Integer pageNumber, Integer pageSize);
}
