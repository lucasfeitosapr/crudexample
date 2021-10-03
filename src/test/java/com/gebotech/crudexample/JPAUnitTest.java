package com.gebotech.crudexample;

import com.gebotech.crudexample.model.Tutorial;
import com.gebotech.crudexample.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//need to change below annotation to use in memory database.
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TutorialRepository tutorialRepository;

    @Test
    public void shouldFindNoTutorialsIfRepositoryIsEmpty() {
        Iterable<Tutorial> tutorials = tutorialRepository.findAll();

        assertThat(tutorials).isEmpty();
    }

    @Test
    public void shouldStoreATutorial() {
        Tutorial tutorial = tutorialRepository.save(new Tutorial("Tut title", "Tut desc", true));

        assertThat(tutorial).hasFieldOrPropertyWithValue("title", "Tut title");
        assertThat(tutorial).hasFieldOrPropertyWithValue("description", "Tut desc");
        assertThat(tutorial).hasFieldOrPropertyWithValue("published", true);
    }

    @Test
    public void shouldFindAllTutorials() {
        Tutorial t1 = new Tutorial("T#1", "D#1", true);
        entityManager.persist(t1);

        Tutorial t2 = new Tutorial("T#2", "D#2", false);
        entityManager.persist(t2);

        Tutorial t3 = new Tutorial("T#3", "D#3", true);
        entityManager.persist(t3);

        Iterable<Tutorial> tutorials = tutorialRepository.findAll();

        assertThat(tutorials).hasSize(3).contains(t1,t2,t3);
    }

    @Test
    public void shouldFindTutorialById() {
        Tutorial t1 = new Tutorial("T#1", "D#1", true);
        entityManager.persist(t1);

        Tutorial t2 = new Tutorial("T#2", "D#2", false);
        entityManager.persist(t2);

        Tutorial foundTutorial = tutorialRepository.findById(t2.getId()).get();

        assertThat(foundTutorial).isEqualTo(t2);
    }

    @Test
    public void shouldFindPublishedTUtorials() {
        Tutorial t1 = new Tutorial("T#1", "D#1", true);
        entityManager.persist(t1);

        Tutorial t2 = new Tutorial("T#2", "D#2", false);
        entityManager.persist(t2);

        Tutorial t3 = new Tutorial("T#3", "D#3", true);
        entityManager.persist(t3);

        Iterable<Tutorial> tutorials = tutorialRepository.findByPublished(true);

        assertThat(tutorials).hasSize(2).contains(t1, t3);
    }

    @Test
    public void shouldUpdateTutorialById() {
        Tutorial t1 = new Tutorial("T#1", "D#1", true);
        entityManager.persist(t1);

        Tutorial t2 = new Tutorial("T#2", "D#2", false);
        entityManager.persist(t2);

        Tutorial updatedTut = new Tutorial("updated T#2", "updated D#2", true);

        Tutorial t = tutorialRepository.findById(t2.getId()).get();
        t.setTitle(updatedTut.getTitle());
        t.setDescription(updatedTut.getDescription());
        t.setPublished(updatedTut.isPublished());
        tutorialRepository.save(t);

        Tutorial checkT = tutorialRepository.findById(t2.getId()).get();

        assertThat(checkT.getId()).isEqualTo(t2.getId());
        assertThat(checkT.getTitle()).isEqualTo(updatedTut.getTitle());
        assertThat(checkT.getDescription()).isEqualTo(updatedTut.getDescription());
        assertThat(checkT.isPublished()).isEqualTo(updatedTut.isPublished());

    }

    @Test
    public void shouldDeleteTutorialById() {
        Tutorial t1 = new Tutorial("T#1", "D#1", true);
        entityManager.persist(t1);

        Tutorial t2 = new Tutorial("T#2", "D#2", false);
        entityManager.persist(t2);

        Tutorial t3 = new Tutorial("T#3", "D#3", true);
        entityManager.persist(t3);

        tutorialRepository.deleteById(t2.getId());

        Iterable<Tutorial> tutorials = tutorialRepository.findAll();

        assertThat(tutorials).hasSize(2).contains(t1, t3);

    }

    @Test
    public void shouldDeleteAllTutorials() {
        entityManager.persist(new Tutorial("T#1", "D#1", true));
        entityManager.persist(new Tutorial("T#2", "D#2", false));

        tutorialRepository.deleteAll();

        assertThat(tutorialRepository.findAll()).isEmpty();
    }

}
