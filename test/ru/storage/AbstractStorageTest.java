package ru.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.model.*;

import java.time.LocalDate;
import java.util.*;

//этот класс не тестируется. тестируются наследники
public abstract class AbstractStorageTest {

    Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume resume1 = new Resume(UUID_1, "fullName1");
    private static final Resume resume2 = new Resume(UUID_2, "fullName2");
    private static final Resume resume3 = new Resume(UUID_3, "fullName3");
    private static final Resume resume4 = new Resume(UUID_4, "fullName4");

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume2);
        storage.save(resume3);
        storage.save(resume1);

        Resume[] resumeArray = {resume1, resume2, resume3, resume4};


        //Contact
        for (int i = 0; i < resumeArray.length; i++) {

            resumeArray[i].setContact("Мобилка " + i, Contacts.TELEPHONE);
            resumeArray[i].setContact("Скуре " + i, Contacts.SKYPE);
            resumeArray[i].setContact("Мыло " + i, Contacts.MAIL);
            resumeArray[i].setContact("Линкин " + i, Contacts.LINKEDIN);
            resumeArray[i].setContact("Хаб " + i, Contacts.GITHUB);
            resumeArray[i].setContact("Стак " + i, Contacts.STATCKOVERFLOW);
            resumeArray[i].setContact("Домашняя страница " + i, Contacts.HOMEPAGE);

            //TextSection Personal
            resumeArray[i].setSectionType(new TextSection("Паровозик" + i), SectionType.PERSONAL);

            //TextSection Objective
            resumeArray[i].setSectionType(new TextSection("Позитивная" + i), SectionType.OBJECTIVE);

            //ListSection achievements
            List<String> achievements = new ArrayList<>();

            achievements.add("Leeroy" + i);
            achievements.add("Jenkins" + i);

            resumeArray[i].setSectionType(new ListSection(achievements), SectionType.ACHIEVEMENT);

            //ListSection qualification
            List<String> qualification = new ArrayList<>();

            qualification.add("digging manager" + i);
            qualification.add("not digging top  manager" + i);

            resumeArray[i].setSectionType(new ListSection(qualification), SectionType.QUALIFICATIONS);

            //OrganisationSection experience

            OrganisationPosition topManager = new OrganisationPosition(LocalDate.now(), LocalDate.now(), "ПарадМенеджер" + i);
            OrganisationPosition megaManager = new OrganisationPosition(LocalDate.now(), LocalDate.now(), "КомбинаторМенеджер" + i);

            Organisation organisationOne = new Organisation("Рога и копыта" + i, "РогаИКопыта.РФ+i", topManager);
            Organisation organisationTwo = new Organisation("ШахматныйТурнир" + i, "Васюки.РФ+i", megaManager);

            List<Organisation> qualifications = new ArrayList<>();

            qualifications.add(organisationOne);
            qualifications.add(organisationTwo);

            resumeArray[i].setSectionType(new OrganisationSection(qualifications), SectionType.EXPERIENCE);

            System.out.println(resumeArray[i].getSectionTypeData(SectionType.EXPERIENCE));

            //Organisation section Education

            OrganisationPosition schoolChild = new OrganisationPosition(LocalDate.now(), LocalDate.now(),
                    "Отпетый Ботан " + i);
            OrganisationPosition student = new OrganisationPosition(LocalDate.now(), LocalDate.now(), "Брутальный Магистр." + i);
            OrganisationPosition postGraduateStudent = new OrganisationPosition(LocalDate.now(), LocalDate.now(),
                    "Мегалютый Аспирант" + i);

            Organisation school = new Organisation("Школа №" + i, "нет", schoolChild);
            Organisation university = new Organisation("Тракторный завод имени Васюткина" + i, "SpBsmdb.РФ" + i, student);

            university.addOrganisationPosition(postGraduateStudent);

            List<Organisation> education = new ArrayList<>();

            education.add(school);
            education.add(university);

            resumeArray[i].setSectionType(new OrganisationSection(education), SectionType.EDUCATION);

            System.out.println(resumeArray[i].getSectionTypeData(SectionType.EDUCATION));
        }
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
        Resume copyResume1 = new Resume("uuid1", "fullName1");
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