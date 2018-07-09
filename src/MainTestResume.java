import ru.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainTestResume {

    private static Resume res = new Resume("Unknown");

    public static void main(String[] args) {

        //Contact test
        res.addContact("Мобилка", Contacts.TELEPHONE);
        res.addContact("Скуре", Contacts.SKYPE);
        res.addContact("Мыло", Contacts.MAIL);
        res.addContact("Линкин", Contacts.LINKEDIN);
        res.addContact("Хаб", Contacts.GITHUB);
        res.addContact("Стак", Contacts.STATCKOVERFLOW);
        res.addContact("Домашняя страница ", Contacts.HOMEPAGE);

        System.out.println(res.getContact(Contacts.TELEPHONE));
        System.out.println(res.getContact(Contacts.SKYPE));
        System.out.println(res.getContact(Contacts.MAIL));
        System.out.println(res.getContact(Contacts.LINKEDIN));
        System.out.println(res.getContact(Contacts.GITHUB));
        System.out.println(res.getContact(Contacts.STATCKOVERFLOW));
        System.out.println(res.getContact(Contacts.HOMEPAGE));
        System.out.println(res.getContact(Contacts.GITHUB) + "\n");

        //TextSection Personal
        res.addSection(new TextSection("Паровозик, который смог"), SectionType.PERSONAL);

        print(SectionType.PERSONAL);

        //TextSection Objective
        res.addSection(new TextSection("Позитивная, смог же)"), SectionType.OBJECTIVE);

        print(SectionType.OBJECTIVE);

        //ListSection achievements
        List<String> achievements = new ArrayList<>();

        achievements.add("Leeroy");
        achievements.add("Jenkins");

        res.addSection(new ListSection(achievements), SectionType.ACHIEVEMENT);

        print(SectionType.ACHIEVEMENT);

        //ListSection qualification
        List<String> qualification = new ArrayList<>();

        qualification.add("digging manager");
        qualification.add("not digging top  manager");

        res.addSection(new ListSection(qualification), SectionType.QUALIFICATIONS);

        print(SectionType.QUALIFICATIONS);

        //OrganizationSection experience

        Organization.Position topManager=new Organization.Position(LocalDate.now(), LocalDate.now(), "Большой опыт командованиия парадами");
        Organization.Position megaManager=new Organization.Position(LocalDate.now(), LocalDate.now(), "Великиий шахматист и комбинатор");

        Organization organizationOne = new Organization("Рога и копыта", "РогаИКопыта.РФ",topManager);
        Organization organizationTwo = new Organization("ШахматныйТурнир", "Васюки.РФ", megaManager);

        List<Organization> qualifications = new ArrayList<>();

        qualifications.add(organizationOne);
        qualifications.add(organizationTwo);

        res.addSection(new OrganizationSection(qualifications), SectionType.EXPERIENCE);

        System.out.println(res.getSectionTypeData(SectionType.EXPERIENCE));

        //Organization section Education

        Organization.Position schoolChild = new Organization.Position(LocalDate.now(), LocalDate.now(),
                "Уверенный хорошист по выгулу коров, твердая пятерка по рыбалке ");
        Organization.Position student = new Organization.Position(LocalDate.now(), LocalDate.now(), "Магистр. Тема: Исследование влияния магнитного" +
                "поля на форму мечей пр перековке их на орала в печах с индуктивным нагревом.");
        Organization.Position postGraduateStudent = new Organization.Position(LocalDate.now(), LocalDate.now(),
                 "Аспирантура. Тема диссертации: исследование использования турбированных тракторов на качество засева полей");

        Organization school = new Organization("Школа №1", "нет", schoolChild);
        Organization university = new Organization("Тракторный завод имени Васюткина", "SpBsmdb.РФ", student);

        university.addOrganisationPosition(postGraduateStudent);

        List<Organization> education = new ArrayList<>();

        education.add(school);
        education.add(university);

        res.addSection(new OrganizationSection(education), SectionType.EDUCATION);

        System.out.println(res.getSectionTypeData(SectionType.EDUCATION));
    }

    private static void print(SectionType type) {
        System.out.println(type.getTitle() + ": " + res.getSectionTypeData(type) + "\n");
    }
}
