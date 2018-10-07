package ru.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.Config;
import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.model.*;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

//этот класс не тестируется. тестируются наследники
public abstract class AbstractStorageTest {

   protected Storage storage;
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

     static final String UUID_1 = "uuid1";
     static final String UUID_2 = "uuid2";
     static final String UUID_3 = "uuid3";
     static final String UUID_4 = "uuid4";

     static final Resume resume1 = new Resume(UUID_1, "fullName1");
     static final Resume resume2 = new Resume(UUID_2, "fullName2");
     static final Resume resume3 = new Resume(UUID_3, "fullName3");
     static final Resume resume4 = new Resume(UUID_4, "fullName4");

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume2);
        storage.save(resume3);
        storage.save(resume1);
/*
        Resume[] resumeArray = {resume1, resume2, resume3, resume4};


        //Contact
        for (int i = 0; i < resumeArray.length; i++) {

            resumeArray[i].addContact(Contacts.TELEPHONE, "Мобилка " + i);
            resumeArray[i].addContact(Contacts.SKYPE, "Скуре " + i);
            resumeArray[i].addContact(Contacts.MAIL, "Мыло " + i);
            resumeArray[i].addContact(Contacts.LINKEDIN, "Линкин " + i);
            resumeArray[i].addContact(Contacts.GITHUB, "Хаб " + i);
            resumeArray[i].addContact(Contacts.STATCKOVERFLOW, "Стак " + i);
            resumeArray[i].addContact(Contacts.HOMEPAGE, "Домашняя страница " + i);

            //TextSection Personal
            resumeArray[i].addSection(SectionType.PERSONAL, new TextSection("Паровозик" + i));

            //TextSection Objective
            resumeArray[i].addSection(SectionType.OBJECTIVE, new TextSection("Позитивная" + i));

            //ListSection achievements
            List<String> achievements = new ArrayList<>();

            achievements.add("Leeroy" + i);
            achievements.add("Jenkins" + i);

            resumeArray[i].addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));

            //ListSection qualification
            List<String> qualification = new ArrayList<>();

            qualification.add("digging manager" + i);
            qualification.add("not digging top  manager" + i);

            resumeArray[i].addSection(SectionType.QUALIFICATIONS, new ListSection(qualification));

            //OrganizationSection experience

            Organization.Position topManager = new Organization.Position(LocalDate.now(), LocalDate.now(), "ПарадМенеджер" + i, "Описание" + i);
            Organization.Position megaManager = new Organization.Position(LocalDate.now(), LocalDate.now(), "КомбинаторМенеджер" + i, "Описание" + i);

            Organization organizationOne = new Organization("Рога и копыта" + i, "РогаИКопыта.РФ+i", topManager);
            Organization organizationTwo = new Organization("ШахматныйТурнир" + i, "Васюки.РФ+i", megaManager);

            List<Organization> qualifications = new ArrayList<>();

            qualifications.add(organizationOne);
            qualifications.add(organizationTwo);

            resumeArray[i].addSection(SectionType.EXPERIENCE, new OrganizationSection(qualifications));

            System.out.println(resumeArray[i].getSectionTypeData(SectionType.EXPERIENCE));

            //Organization section Education

            Organization.Position schoolChild = new Organization.Position(LocalDate.now(), LocalDate.now(),
                    "Отпетый Ботан " + i, "С борордой" + i);
            Organization.Position student = new Organization.Position(LocalDate.now(), LocalDate.now(), "Брутальный Магистр." + i, "С борордой" + i);
            Organization.Position postGraduateStudent = new Organization.Position(LocalDate.now(), LocalDate.now(),
                    "Мегалютый Аспирант" + i, "С борордой" + i);

            Organization school = new Organization("Школа №" + i, "нет", schoolChild);
            Organization university = new Organization("Тракторный завод имени Васюткина" + i, "SpBsmdb.РФ" + i, student);

            university.addPosition(postGraduateStudent);

            List<Organization> education = new ArrayList<>();

            education.add(school);
            education.add(university);

            resumeArray[i].addSection(SectionType.EDUCATION, new OrganizationSection(education));

            System.out.println(resumeArray[i].getSectionTypeData(SectionType.EDUCATION));
        }*/
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getExistResume() throws Exception {
        Assert.assertEquals(resume1.toString(), storage.get("uuid1").toString());
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
        storage.update(copyResume1);
        Assert.assertEquals(storage.get("uuid1"), copyResume1);
    }

    @Test
    public void save() throws Exception {
        storage.save(resume4);
        Assert.assertEquals(storage.get(UUID_4).toString(), resume4.toString());
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

        Assert.assertEquals(storage.get(UUID_1).toString(), resume1.toString());
        Assert.assertEquals(storage.get(UUID_2).toString(), resume2.toString());
        Assert.assertEquals(storage.get(UUID_3).toString(), resume3.toString());
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();

        Assert.assertEquals(list.get(0).getUuid(), resume1.getUuid());
        Assert.assertEquals(list.get(1).getUuid(), resume2.getUuid());
        Assert.assertEquals(list.get(2).getUuid(), resume3.getUuid());
    }

}