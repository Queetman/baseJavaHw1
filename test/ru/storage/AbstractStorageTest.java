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

            resumeArray[i].addContact("Мобилка " + i, Contacts.TELEPHONE);
            resumeArray[i].addContact("Скуре " + i, Contacts.SKYPE);
            resumeArray[i].addContact("Мыло " + i, Contacts.MAIL);
            resumeArray[i].addContact("Линкин " + i, Contacts.LINKEDIN);
            resumeArray[i].addContact("Хаб " + i, Contacts.GITHUB);
            resumeArray[i].addContact("Стак " + i, Contacts.STATCKOVERFLOW);
            resumeArray[i].addContact("Домашняя страница " + i, Contacts.HOMEPAGE);

            //TextSection Personal
            resumeArray[i].addSection(new TextSection("Паровозик" + i), SectionType.PERSONAL);

            //TextSection Objective
            resumeArray[i].addSection(new TextSection("Позитивная" + i), SectionType.OBJECTIVE);

            //ListSection achievements
            List<String> achievements = new ArrayList<>();

            achievements.add("Leeroy" + i);
            achievements.add("Jenkins" + i);

            resumeArray[i].addSection(new ListSection(achievements), SectionType.ACHIEVEMENT);

            //ListSection qualification
            List<String> qualification = new ArrayList<>();

            qualification.add("digging manager" + i);
            qualification.add("not digging top  manager" + i);

            resumeArray[i].addSection(new ListSection(qualification), SectionType.QUALIFICATIONS);

            //OrganizationSection experience

            Organization.Position topManager = new Organization.Position(LocalDate.now(), LocalDate.now(), "ПарадМенеджер" + i);
            Organization.Position megaManager = new Organization.Position(LocalDate.now(), LocalDate.now(), "КомбинаторМенеджер" + i);

            Organization organizationOne = new Organization("Рога и копыта" + i, "РогаИКопыта.РФ+i", topManager);
            Organization organizationTwo = new Organization("ШахматныйТурнир" + i, "Васюки.РФ+i", megaManager);

            List<Organization> qualifications = new ArrayList<>();

            qualifications.add(organizationOne);
            qualifications.add(organizationTwo);

            resumeArray[i].addSection(new OrganizationSection(qualifications), SectionType.EXPERIENCE);

            System.out.println(resumeArray[i].getSectionTypeData(SectionType.EXPERIENCE));

            //Organization section Education

            Organization.Position schoolChild = new Organization.Position(LocalDate.now(), LocalDate.now(),
                    "Отпетый Ботан " + i);
            Organization.Position student = new Organization.Position(LocalDate.now(), LocalDate.now(), "Брутальный Магистр." + i);
            Organization.Position postGraduateStudent = new Organization.Position(LocalDate.now(), LocalDate.now(),
                    "Мегалютый Аспирант" + i);

            Organization school = new Organization("Школа №" + i, "нет", schoolChild);
            Organization university = new Organization("Тракторный завод имени Васюткина" + i, "SpBsmdb.РФ" + i, student);

            university.addOrganisationPosition(postGraduateStudent);

            List<Organization> education = new ArrayList<>();

            education.add(school);
            education.add(university);

            resumeArray[i].addSection(new OrganizationSection(education), SectionType.EDUCATION);

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