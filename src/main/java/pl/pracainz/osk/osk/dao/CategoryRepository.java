package pl.pracainz.osk.osk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
