import ru.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainTestResume {

    private static Resume res = new Resume("Unknown");

    public static void main(String[] args) {

        //Contact test
        res.setContact("Мобилка", Contacts.TELEPHONE);
        res.setContact("Скуре", Contacts.SKYPE);
        res.setContact("Мыло", Contacts.MAIL);
        res.setContact("Линкин", Contacts.LINKEDIN);
        res.setContact("Хаб", Contacts.GITHUB);
        res.setContact("Стак", Contacts.STATCKOVERFLOW);
        res.setContact("Домашняя страница ", Contacts.HOMEPAGE);

        System.out.println(res.getContact(Contacts.TELEPHONE));
        System.out.println(res.getContact(Contacts.SKYPE));
        System.out.println(res.getContact(Contacts.MAIL));
        System.out.println(res.getContact(Contacts.LINKEDIN));
        System.out.println(res.getContact(Contacts.GITHUB));
        System.out.println(res.getContact(Contacts.STATCKOVERFLOW));
        System.out.println(res.getContact(Contacts.HOMEPAGE));
        System.out.println(res.getContact(Contacts.GITHUB) + "\n");

        //TextSection Personal
        res.setSectionType(new TextSection("Паровозик, который смог"), SectionType.PERSONAL);

        print(SectionType.PERSONAL);

        //TextSection Objective
        res.setSectionType(new TextSection("Позитивная, смог же)"), SectionType.OBJECTIVE);

        print(SectionType.OBJECTIVE);

        //ListSection achievements
        List<String> achievements = new ArrayList<>();

        achievements.add("Leeroy");
        achievements.add("Jenkins");

        res.setSectionType(new ListSection(achievements), SectionType.ACHIEVEMENT);

        print(SectionType.ACHIEVEMENT);

        //ListSection qualification
        List<String> qualification = new ArrayList<>();

        qualification.add("digging manager");
        qualification.add("not digging top  manager");

        res.setSectionType(new ListSection(qualification), SectionType.QUALIFICATIONS);

        print(SectionType.QUALIFICATIONS);

        //OrganisationSection experience
        Organisation organisationOne = new Organisation("Рога и копыта", "РогаИКопыта.РФ",
                LocalDate.now(), LocalDate.now(), "Фирма-однодневка");
        Organisation organisationTwo = new Organisation("ШахматныйТурнир", "Васюки.РФ",
                LocalDate.now(), LocalDate.now(), "турнир 1927 года. 1 место.");

        List<Organisation> qualifications = new ArrayList<>();

        qualifications.add(organisationOne);
        qualifications.add(organisationTwo);

        res.setSectionType(new OrganisationSection(qualifications), SectionType.EXPERIENCE);

        System.out.println(res.getSectionTypeData(SectionType.EXPERIENCE));

        //Organisation section Education

        Organisation school = new Organisation("Сельмаш №1", "нет",
                LocalDate.now(), LocalDate.now(), "Уверенный хорошист по выгулу коров, твердая пятерка по рыбалке ");
        Organisation univercity = new Organisation("Тракторный завод имени Васюткина", "SpBsmdb.РФ",
                LocalDate.now(), LocalDate.now(), "Создание трактора- это тонки и очень кропотливый труд!");
        univercity.addNewWork(LocalDate.now(),LocalDate.now(),"Аспирантура. Тема диссертации: исследование использования " +
                "турбированных тракторов на качестов засева полей");
        List<Organisation> education = new ArrayList<>();

        education.add(school);
        education.add(univercity);

        res.setSectionType(new OrganisationSection(education), SectionType.EDUCATION);

        System.out.println(res.getSectionTypeData(SectionType.EDUCATION));
    }

    private static void print(SectionType type) {
        System.out.println(type.getTitle() + ": " + res.getSectionTypeData(type) + "\n");
    }
}
