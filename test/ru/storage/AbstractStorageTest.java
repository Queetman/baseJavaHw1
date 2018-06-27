package ru.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.model.*;

import java.time.LocalDate;
import java.util.*;

//этот клвсс не тестируется. тестируются наследники
public abstract class AbstractStorageTest {

    Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume resume1 = new Resume(UUID_1,"fullName1");
    private static final Resume resume2 = new Resume(UUID_2,"fullName2");
    private static final Resume resume3 = new Resume(UUID_3,"fullName3");
    private static final Resume resume4 = new Resume(UUID_4,"fullName4");

    private static Resume res = new Resume("Unknown");

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume2);
        storage.save(resume3);
        storage.save(resume1);

        //Contact
        res.setContact("Мобилка", Contacts.TELEPHONE);
        res.setContact("Скуре", Contacts.SKYPE);
        res.setContact("Мыло", Contacts.MAIL);
        res.setContact("Линкин", Contacts.LINKEDIN);
        res.setContact("Хаб", Contacts.GITHUB);
        res.setContact("Стак", Contacts.STATCKOVERFLOW);
        res.setContact("Домашняя страница ", Contacts.HOMEPAGE);

        //TextSection Personal
        res.setSectionType(new TextSection("Паровозик, который смог"), SectionType.PERSONAL);

        //TextSection Objective
        res.setSectionType(new TextSection("Позитивная, смог же)"), SectionType.OBJECTIVE);

        //ListSection achievements
        List<String> achievements = new ArrayList<>();

        achievements.add("Leeroy");
        achievements.add("Jenkins");

        res.setSectionType(new ListSection(achievements), SectionType.ACHIEVEMENT);

        //ListSection qualification
        List<String> qualification = new ArrayList<>();

        qualification.add("digging manager");
        qualification.add("not digging top  manager");

        res.setSectionType(new ListSection(qualification), SectionType.QUALIFICATIONS);

        //OrganisationSection experience
        Organisation organisationOne = new Organisation("Рога и копыта", "РогаИКопыта.РФ",
                LocalDate.now(), LocalDate.now(), "Фирма-однодневка");
        Organisation organisationTwo = new Organisation("ШахматныйТурнир", "Васюки.РФ",
                LocalDate.now(), LocalDate.now(), "турнир 1927 года. 1 место.");

        List<Organisation> qualifications = new ArrayList<>();

        qualifications.add(organisationOne);
        qualifications.add(organisationTwo);

        res.setSectionType(new OrganisationSection(qualifications), SectionType.EXPERIENCE);

        //Organisation section Education
        Organisation school = new Organisation("Сельмаш №1", "нет",
                LocalDate.now(), LocalDate.now(), "Уверенный хорошист по выгулу коров, твердая пятерка по рыбалке ");
        Organisation univercity = new Organisation("Тракторный завод имени Васюткина", "SpBsmdb.РФ",
                LocalDate.now(), LocalDate.now(), "Создание трактора- это тонки и очень кропотливый труд!");
      univercity.addNewWork(LocalDate.now(),LocalDate.now(),"Аспирантура. Тема диссертации: исслеование использования " +
              "турбированных тракторов на качестов засева полей");

        List<Organisation> education = new ArrayList<>();

        education.add(school);
        education.add(univercity);

        res.setSectionType(new OrganisationSection(education), SectionType.EDUCATION);

    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getExistResume() throws Exception {
        Assert.assertEquals(resume1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResume() throws Exception {
        Assert.assertEquals(resume1, storage.get("uuid4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(resume4);
    }

    @Test
    public void updateExist() throws Exception {
        Resume copyResume1 = new Resume("uuid1","fullName1");
        storage.update(resume1);
        Assert.assertEquals(storage.get("uuid1"), copyResume1);
    }

    @Test
    public void save() throws Exception {
        storage.save(resume4);
        Assert.assertEquals(storage.get(UUID_4), resume4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(resume1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteExist() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(storage.size(), 2);
       storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("notUuid1");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(3, storage.size());

        Assert.assertEquals(storage.get(UUID_1), resume1);
        Assert.assertEquals(storage.get(UUID_2), resume2);
        Assert.assertEquals(storage.get(UUID_3), resume3);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();

        Assert.assertEquals(list.get(0).getUuid(), resume1.getUuid());
        Assert.assertEquals(list.get(1).getUuid(), resume2.getUuid());
        Assert.assertEquals(list.get(2).getUuid(), resume3.getUuid());
    }

}