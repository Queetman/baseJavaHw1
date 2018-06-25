import ru.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MainTestResume {
    public static void main(String[] args) {
        Resume res = new Resume("Unknown");

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
        System.out.println(res.getContact(Contacts.GITHUB));
        System.out.println();

        //TextSection test
        res.setSectionType(new TextSection("Паровозик, который смог"), SectionType.PERSONAL);

        System.out.println(res.getSectionTypeData(SectionType.PERSONAL));
        System.out.println();

        //ListSection test
        List<String> achievements = new ArrayList<>();

        achievements.add("Leeroy");
        achievements.add("Jenkins");

        res.setSectionType(new ListSection(achievements), SectionType.ACHIEVEMENT);

        System.out.println(res.getSectionTypeData(SectionType.ACHIEVEMENT));

        //QualificationSection test

        Qualification qualificationOne = new Qualification("Рога и копыта", "РогаИКопыта.РФ",
                LocalDate.now(), LocalDate.now(), "Фирма-однодневка");
        Qualification qualificationTwo = new Qualification("ШахматныйТурнир", "Васюки.РФ",
                LocalDate.now(), LocalDate.now(), "турнир 1927 года");

        List<Qualification> qualifications = new ArrayList<>();

        qualifications.add(qualificationOne);
        qualifications.add(qualificationTwo);

        res.setSectionType(new QualificationSection(qualifications), SectionType.QUALIFICATIONS);

        System.out.println(res.getSectionTypeData(SectionType.QUALIFICATIONS));
    }


}
