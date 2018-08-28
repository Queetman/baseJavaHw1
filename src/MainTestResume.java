import ru.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainTestResume {

    private static Resume res = new Resume("Unknown");

    public static void main(String[] args) {

        //Contact test
        res.addContact(Contacts.TELEPHONE, "Мобилка");
        res.addContact(Contacts.SKYPE, "Скуре");
        res.addContact(Contacts.MAIL, "Мыло");
        res.addContact(Contacts.LINKEDIN, "Линкин");
        res.addContact(Contacts.GITHUB, "Хаб");
        res.addContact(Contacts.STATCKOVERFLOW, "Стак");
        res.addContact(Contacts.HOMEPAGE, "Домашняя страница ");

        System.out.println(res.getContact(Contacts.TELEPHONE));
        System.out.println(res.getContact(Contacts.SKYPE));
        System.out.println(res.getContact(Contacts.MAIL));
        System.out.println(res.getContact(Contacts.LINKEDIN));
        System.out.println(res.getContact(Contacts.GITHUB));
        System.out.println(res.getContact(Contacts.STATCKOVERFLOW));
        System.out.println(res.getContact(Contacts.HOMEPAGE));
        System.out.println(res.getContact(Contacts.GITHUB) + "\n");

        //TextSection Personal
        res.addSection(SectionType.PERSONAL, new TextSection("Паровозик, который смог"));

        print(SectionType.PERSONAL);

        //TextSection Objective
        res.addSection(SectionType.OBJECTIVE, new TextSection("Позитивная, смог же)"));

        print(SectionType.OBJECTIVE);

        //ListSection achievements
        List<String> achievements = new ArrayList<>();

        achievements.add("Leeroy");
        achievements.add("Jenkins");

        res.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));

        print(SectionType.ACHIEVEMENT);

        //ListSection qualification
        List<String> qualification = new ArrayList<>();

        qualification.add("digging manager");
        qualification.add("not digging top  manager");

        res.addSection(SectionType.QUALIFICATIONS, new ListSection(qualification));

        print(SectionType.QUALIFICATIONS);

        //OrganizationSection experience

        Organization.Position topManager = new Organization.Position(LocalDate.now(), LocalDate.now(), "Большой опыт командованиия парадами", "Командир");
        Organization.Position megaManager = new Organization.Position(LocalDate.now(), LocalDate.now(), "Великиий шахматист и комбинатор", "Дада");

        Organization organizationOne = new Organization("Рога и копыта", "РогаИКопыта.РФ", topManager);
        Organization organizationTwo = new Organization("ШахматныйТурнир", "Васюки.РФ", megaManager);

        List<Organization> qualifications = new ArrayList<>();

        qualifications.add(organizationOne);
        qualifications.add(organizationTwo);

        res.addSection(SectionType.EXPERIENCE, new OrganizationSection(qualifications));

        System.out.println(res.getSectionTypeData(SectionType.EXPERIENCE));

        //Organization section Education

        Organization.Position schoolChild = new Organization.Position(LocalDate.now(), LocalDate.now(),
                "Уверенный хорошист по выгулу коров, твердая пятерка по рыбалке ", "Бытовые навыки");
        Organization.Position student = new Organization.Position(LocalDate.now(), LocalDate.now(), "Магистр. Тема: Исследование влияния магнитного" +
                "поля на форму мечей пр перековке их на рала в печах с индуктивным нагревом.", "О да!");
        Organization.Position postGraduateStudent = new Organization.Position(LocalDate.now(), LocalDate.now(),
                "Аспирантура. Тема диссертации: исследование использования турбированных тракторов на качество засева полей", "Моя любимая");

        Organization school = new Organization("Школа №1", "нет", schoolChild);

        Organization university = new Organization("Тракторный завод имени Васюткина", "SpBsmdb.РФ", student);

        List<Organization> education = new ArrayList<>();

        education.add(school);
        education.add(university);

        res.addSection(SectionType.EDUCATION, new OrganizationSection(education));

        System.out.println(res.getSectionTypeData(SectionType.EDUCATION));
    }

    private static void print(SectionType type) {
        System.out.println(type.getTitle() + ": " + res.getSectionTypeData(type) + "\n");
    }
}
