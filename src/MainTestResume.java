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

        OrganisationPosition topManager=new OrganisationPosition(LocalDate.now(), LocalDate.now(), "Большой опыт командованиия парадами");
        OrganisationPosition megaManager=new OrganisationPosition(LocalDate.now(), LocalDate.now(), "Великиий шахматист и комбинатор");

        Organisation organisationOne = new Organisation("Рога и копыта", "РогаИКопыта.РФ",topManager);
        Organisation organisationTwo = new Organisation("ШахматныйТурнир", "Васюки.РФ", megaManager);

        List<Organisation> qualifications = new ArrayList<>();

        qualifications.add(organisationOne);
        qualifications.add(organisationTwo);

        res.setSectionType(new OrganisationSection(qualifications), SectionType.EXPERIENCE);

        System.out.println(res.getSectionTypeData(SectionType.EXPERIENCE));

        //Organisation section Education

        OrganisationPosition schoolChild = new OrganisationPosition(LocalDate.now(), LocalDate.now(),
                "Уверенный хорошист по выгулу коров, твердая пятерка по рыбалке ");
        OrganisationPosition student = new OrganisationPosition(LocalDate.now(), LocalDate.now(), "Магистр. Тема: Исследование влияния магнитного" +
                "поля на форму мечей пр перековке их на орала в печах с индуктивным нагревом.");
        OrganisationPosition postGraduateStudent = new OrganisationPosition(LocalDate.now(), LocalDate.now(),
                 "Аспирантура. Тема диссертации: исследование использования турбированных тракторов на качество засева полей");

        Organisation school = new Organisation("Школа №1", "нет", schoolChild);
        Organisation university = new Organisation("Тракторный завод имени Васюткина", "SpBsmdb.РФ", student);

        university.addOrganisationPosition(postGraduateStudent);

        List<Organisation> education = new ArrayList<>();

        education.add(school);
        education.add(university);

        res.setSectionType(new OrganisationSection(education), SectionType.EDUCATION);

        System.out.println(res.getSectionTypeData(SectionType.EDUCATION));
    }

    private static void print(SectionType type) {
        System.out.println(type.getTitle() + ": " + res.getSectionTypeData(type) + "\n");
    }
}
